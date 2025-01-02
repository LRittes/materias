module rpg_strategies{
    import basicSpells.* from "/spells/basicSpells"
    import rareSpells.* from "/spells/rareSpells"

    type RoleTypes = CharacterMage| CharacterCleric | CharacterBarbarian | Monster

    type Creature = {
        name: str,
        hp: int,
        initiative: int,
        role: RoleTypes,
        team: str,
        Immune: bool,
        Paralized: bool,
        Taunt: str,
    }

    pure val Creatures = Set(
        {name: "Boris", hp: 150, initiative: 0, role: CharacterBarbarian, team: "Characters", Immune: false, Paralized: false, Taunt: "None"},
        {name: "Billy", hp: 20, initiative: 0, role: CharacterCleric,team: "Characters", Immune: false, Paralized: false, Taunt: "None"},
        {name: "Belle", hp: 20, initiative: 0, role: CharacterMage,team: "Characters", Immune: false, Paralized: false, Taunt: "None"},
        {name: "Monster", hp: 100, initiative: 0, role: Monster,team: "Monster", Immune: false, Paralized: false, Taunt: "None"}
    )

    var round: int
    var creatures: str -> Creature
    var last_turn: Option[{ attacker: Creature, receiver: Creature, move: str}]


    pure def damage(c: Creature, d: int): Creature = {
        { ...c, hp: c.hp - d }
    }

    pure def paralize(c: Creature): Creature = {
        { ...c, Paralized: true  }
    }

    pure def unparalize(c: Creature): Creature = {
        { ...c, Paralized: false  }
    }

    pure def taunt(c1: Creature, c2:Creature): Creature = {
        {...c1, Taunt: c2.name}
    }

    pure def detaunt(c: Creature): Creature = {
        {...c, Taunt: "None"}
    }

    pure def immune(c: Creature): Creature = {
        {...c, Immune: true}
    }

    pure def deimmune(c: Creature): Creature = {
        {...c, Immune: false}
    }

    action attack(attacker: Creature, receiver: Creature): bool = all{
            attacker.hp > 0,
            receiver.hp > 0,
            last_turn' = Some({ attacker: attacker, receiver: receiver, move: "Attack"}),
            //if attacker is cleric, remove immunity and then attack
            if(attacker.role == CharacterCleric)all{
                creatures' = creatures.transformValues(c => c.deimmune()).setBy(receiver.name, c => c.damage(10))
            }
            //if the attacker is taunted, attack the creature who taunted it
            else if(attacker.Taunt != "None"){
                //if the creature who taunted is immune, it doesnt receive damage and loses immune, and attacker is not taunted anymore
                if(receiver.Immune == true)
                    creatures' = creatures.setBy(receiver.name, c => c.damage(0)).setBy(receiver.name, c => c.deimmune()).setBy(attacker.name, c => c.detaunt())

                //else just attack the creature who taunted and lose taunt
                else
                    creatures' = creatures.setBy(attacker.Taunt, c => c.damage(10)).setBy(attacker.name, c => c.detaunt())
            }
            //if receiver is immune, cancel damage and loses immune
            else if(receiver.Immune == true){
                creatures' = creatures.setBy(receiver.name, c => c.damage(0)).setBy(receiver.name, c => c.deimmune())
            } 
            //if its the first round, monster does less damage
            else if (attacker.role == Monster){
                if (round == 0){
                    creatures' = creatures.setBy(receiver.name, c => c.damage(10))
                } else{
                    creatures' = creatures.setBy(receiver.name, c => c.damage(20))
                } 
            } 
            //else just do the damage
            else {
                creatures' = creatures.setBy(receiver.name, c => c.damage(10))
            }
    }

    
    action paralize_action(caster: Creature, receiver: Creature): bool = all{
        creatures' = creatures.setBy(receiver.name, c => c.paralize()),
        last_turn' = Some({ attacker: caster, receiver: receiver, move: "Paralize"}), 
    }
    
    action removeParalize(caster: Creature, receiver: Creature): bool = all{
        caster.name != receiver.name,
        if(caster.role == CharacterCleric) all{
            creatures' = creatures.transformValues(c => c.deimmune()).setBy(receiver.name, c => c.unparalize()),
            last_turn' = Some({ attacker: caster, receiver: receiver, move: "Remove Paralize" })
        }else all{
        creatures' = creatures.setBy(receiver.name, c => c.unparalize()),   
        last_turn' = Some({ attacker: caster, receiver: receiver, move: "Remove Paralize" }),
        }
             
    }

    action taunt_action(caster:Creature, receiver: Creature): bool = all{
        caster.team != receiver.team,
        creatures'= creatures.setBy(receiver.name, c => c.taunt(caster)),
        last_turn' = Some({ attacker: caster, receiver: receiver, move: "Taunt" }), 
    }

    action immune_action(caster:Creature, receiver: Creature): bool = all{
        caster.team == receiver.team,
        creatures' = creatures.transformValues(c => c.immune()),
        last_turn' = Some({ attacker: caster, receiver: receiver, move: "Immune" }), 
    }

    action remove_immune(caster: Creature): bool = all{
        creatures' = creatures.transformValues(c => c.deimmune()),
    }


    action init = all{
        round' = 0,
        nondet D20_mage = (1.to(20)).oneOf()
        nondet D20_barbarian = (1.to(20)).oneOf()
        val D20_cleric = 21
        nondet D20_monster = (1.to(20)).oneOf()
        creatures' = Map(
            "Boris" -> {name: "Boris", hp: 150, initiative: D20_barbarian, role: CharacterBarbarian, team: "Characters", Immune: false, Paralized: false, Taunt: "None"},
            "Billy" -> {name: "Billy", hp: 20, initiative: D20_cleric, role: CharacterCleric, team: "Characters", Immune: false, Paralized: false, Taunt: "None"},
            "Belle" -> {name: "Belle", hp: 20, initiative: D20_mage, role: CharacterMage, team: "Characters",Immune: false, Paralized: false, Taunt: "None"},
            "Monster" -> {name: "Monster", hp: 100, initiative: D20_monster, role: Monster, team: "Monsters", Immune: false, Paralized: false, Taunt: "None"}
        ),
        last_turn' = None

    }       
    
    val isParalized = creatures.values().exists(c => c.Paralized == true and c.team == "Characters")
    val monsterParalized = creatures.values().exists(c => c.Paralized== true and c.team == "Monster")
    val monsterNotTaunted = creatures.values().exists(c => c.Taunt == "None" and c.team == "Monsters")
    val charactersNotImmune = creatures.values().filter(c => c.team == "Characters").exists(c => c.Immune != true )

    action step = {
        val attackers_by_initiative = creatures.values().toList((c1, c2) => intCompare(c2.initiative, c1.initiative))
        val attacker = attackers_by_initiative[round % creatures.keys().size()]
        nondet receiver = creatures.values().filter(c => attacker != c and c.team != attacker.team ).oneOf()
        nondet receiver_allie = creatures.values().filter(c => c.team == attacker.team).oneOf()
        all {
            if(attacker.role == CharacterMage and attacker.Paralized == false){  
                all{
                    if (isParalized) removeParalize(attacker, receiver_allie) else 
                    if (monsterParalized) attack(attacker, receiver)
                    else paralize_action(attacker, receiver),
                }
            }else if(attacker.role == CharacterBarbarian and attacker.Paralized == false){
                all{
                    
                    if (monsterNotTaunted) taunt_action(attacker, receiver)
                    else if (isParalized) removeParalize(attacker, receiver_allie)
                    else attack(attacker, receiver)
                }
            } else if(attacker.role == CharacterCleric and attacker.Paralized == false){
                all{
                    
                    if (charactersNotImmune) immune_action(attacker, receiver_allie)
                    else if (isParalized) removeParalize(attacker, receiver_allie)
                    else attack(attacker, receiver) 
                   
                }
            } else if(attacker.role == Monster){
                //if the monster is paralized, it uses the turn to unparalize itself
                if(attacker.Paralized == true){
                    all{
                        removeParalize(attacker, attacker)
                    }
                } else
                    any{
                        attack(attacker, receiver),
                        paralize_action(attacker, receiver) 
                    }
            } else{
                attack(attacker, receiver)
            },
            round' = round+1
        }
    }

    //invariants
    val monster_doesnt_die = creatures.values().filter(c => c.team == "Monsters").forall(c => c.hp > 0)

    val characters_doesnt_die = creatures.values().filter(c=> c.team == "Characters").forall(c => c.hp > 0)

    val no_one_dies = creatures.values().forall(c => c.hp > 0 )

    val immune_test = creatures.values().forall(c => c.Immune == false)


}