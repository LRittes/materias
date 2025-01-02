
module trabalho {
  import basicSpells.* from "../spells/basicSpells"
  import rareSpells.* from "../spells/rareSpells"
  //A

  type Acao = Atacar |  RemoverParalisia | Paralisar | ImunizarTodos | Provocar | Nenhuma
  type Status =  Provocado | Paralisado | Nenhum | Imune | Morto
  type Personagem =  { name: str, hp: int, d20: int, Provocado: bool, Paralisado: bool,Imune:bool, Morto: bool}
  pure val d20 = 1.to(20)


type TurnDescription =
   | Attack({ attacker: Personagem, receiver: Personagem, dano: int })
   | ApplyCondition({ caster: Personagem, receiver: Personagem, condition: Status })
   | RemoveCondition({ caster: Personagem,receiver:Personagem, condition: Status })
   | PerdeuTurno({jogador:Personagem, condition:Status})
   | Morreu({jogador:Personagem})


var ultimoTurno: Option[TurnDescription]
var round: int
var personagens: str -> Personagem

  pure def damage(p: Personagem, d: int): Personagem = {
    if(p.Imune != true){
      { ...p, hp: p.hp - d }
    }
    else{
        { ...p, hp: p.hp}
    } 
  }

  pure def aplicaProvocar(p: Personagem): Personagem = {
    {...p, Provocado: true}
  }

  pure def removeStatus(p: Personagem): Personagem = {
    {...p,Provocado: false, Paralisado: false,Imune:false}
  }


  pure def removeImunidade(p: Personagem): Personagem = {
    {...p,Imune:false}
  }

  pure def removeParalisia(p: Personagem): Personagem = {
    {...p,Paralisado: false}
  }

  pure def removeParalisiaImunidade(p: Personagem): Personagem ={
      {...p,Paralisado: false, Imune: false}
  }
  pure def aplicaParalisia(p: Personagem): Personagem = {
    {...p,Paralisado:true}
  }

  pure def aplicaImunidade(p: Personagem): Personagem = {
    {...p,Imune:true}
  }

  pure def atribuiMorto(p: Personagem): Personagem = {
     {...p, Morto:true}
  }

  pure def mantemStatus(p: Personagem): Personagem ={
    {...p, Provocado: p.Provocado, Paralisado: p.Paralisado,Imune:p.Imune, Morto: p.Morto}
  }

  action ataqueHeroi(attacker: Personagem, receiver:Personagem): bool = all{
    personagens' = personagens.setBy(receiver.name, p => p.damage(10)),
    ultimoTurno' = Some(Attack({ attacker: attacker, receiver: receiver, dano: 10 })),
  }

  action ataqueClerigo(attacker: Personagem, receiver: Personagem): bool = all{
    if(attacker.Imune == true){
      all{
          personagens' =personagens.keys().mapBy(name => if(name != "Monstro" and name != "Monstro2") personagens.get(name).removeImunidade() else receiver.damage(10)),
          ultimoTurno' = Some(Attack({ attacker: attacker, receiver: receiver, dano: 10 })),
      }
    }else{
      all{
        personagens' = personagens.setBy(receiver.name, p => p.damage(10)),
          ultimoTurno' = Some(Attack({ attacker: attacker, receiver: receiver, dano: 10 })),
      }
    }
  }

  action provocar(attacker: Personagem, receiver:Personagem): bool = all{
    personagens' = personagens.setBy(receiver.name, p => p.aplicaProvocar()),
    ultimoTurno' = Some(ApplyCondition({ caster: attacker, receiver: receiver, condition: Provocado})),
  }

  action removerParalisia(attacker: Personagem): bool = all{
    nondet receiver = personagens.values().filter(p =>p.name != "Monstro" and p.name!="Monstro2" and p.Paralisado == true).oneOf()
    all{
      personagens' = personagens.setBy(receiver.name, p => p.removeParalisia()),
      ultimoTurno' = Some( RemoveCondition({ caster: attacker,receiver:receiver, condition: Paralisado })),
    }
  }

 action removerParalisiaClerigo(attacker: Personagem): bool = all{
    nondet receiver = personagens.values().filter(p =>p.name != "Monstro"  and p.name != "Monstro2" and p.Paralisado == true).oneOf()
    if(attacker.Imune == true){
      all{
          personagens' =personagens.keys().mapBy(name => if(name == receiver.name) personagens.get(receiver.name).removeParalisiaImunidade() else personagens.get(name).removeImunidade()),
          ultimoTurno' = Some(RemoveCondition({ caster: attacker,receiver:receiver, condition: Paralisado })),
      }
    }else{
      all{
        personagens' = personagens.setBy(receiver.name, p => p.removeParalisia()),
        ultimoTurno' = Some( RemoveCondition({ caster: attacker,receiver:receiver, condition: Paralisado })),
      }
    }
  }

  action paralisar(attacker: Personagem, receiver: Personagem): bool = all{
    personagens' = personagens.setBy(receiver.name, p => p.aplicaParalisia()),
    ultimoTurno' = Some(ApplyCondition({ caster: attacker, receiver: receiver, condition: Paralisado})),
  }

  action imunizar(attacker: Personagem, receiver: Personagem):bool = all{
    personagens' = personagens.keys().mapBy(name => if(name != "Monstro" and name != "Monstro2") personagens.get(name).aplicaImunidade() else personagens.get(name).mantemStatus()),
    ultimoTurno' = Some(ApplyCondition({caster: attacker, receiver: receiver, condition: Imune})),
  }

  action ataqueMonstro(attacker: Personagem, receiver: Personagem):bool = all{
    if(round >= personagens.values().size()){
      all{
        personagens' = personagens.keys().mapBy(name => if (name == receiver.name) receiver.damage(20) else if (name==attacker.name) attacker.removeStatus() else personagens.get(name).mantemStatus()),
        ultimoTurno' = Some(Attack({ attacker: attacker, receiver: receiver, dano: 20 })),
      }
    }else{
      all{
        personagens' = personagens.keys().mapBy(name => if (name == receiver.name) receiver.damage(10) else if (name==attacker.name) attacker.removeStatus() else personagens.get(name).mantemStatus()),
        ultimoTurno' =  Some(Attack({ attacker: attacker, receiver: receiver, dano: 10 })),
      } 
    },
  }

  action acaoMonstro(monstro: Personagem): bool = all{
    if(monstro.Paralisado != true){
      if(monstro.Provocado == true){
        val receiver = personagens.get("Barbaro")
        any{
          ataqueMonstro(monstro,receiver),
          paralisar(monstro,receiver),
        }
      }else{
        nondet receiver = personagens.values().filter(p =>p.name != "Monstro" and p.name!= "Monstro2").oneOf()
        any{
          ataqueMonstro(monstro,receiver),
          paralisar(monstro,receiver),
        }
      }
    }else{
      all{
          personagens' = personagens.setBy(monstro.name, p => p.removeStatus()),
          ultimoTurno' = Some(PerdeuTurno({jogador:monstro, condition:Paralisado}))
        }
      }
  }

    action acaoBarbaro(barbaro: Personagem): bool = all{
        if(barbaro.hp > 0 and barbaro.Morto != true and barbaro.Paralisado != true){
          nondet receiver = personagens.values().filter(p =>p.name == "Monstro" or p.name == "Monstro2").oneOf()
          any{
            if(personagens.values().exists(p => p.Paralisado != true and (p.name == "Monstro" or p.name == "Monstro2"))){
              provocar(barbaro,receiver)
            }else{
              ataqueHeroi(barbaro,receiver)
              /*
              if(personagens.values().exists(p => p.Paralisado != true and (p.name == "Monstro" or p.name == "Monstro2" ))){
                provocar(barbaro,receiver)
              }else{
                  removerParalisia(barbaro)
                ataqueHeroi(barbaro,receiver)
              }
              */
            }
          }
        }else{
          if(barbaro.hp <= 0){
            all{
                personagens' = personagens.setBy(barbaro.name, p => p.atribuiMorto()),
                ultimoTurno' = Some(Morreu({jogador:barbaro})),
            }
          }else{
            all{
              personagens' = personagens.setBy(barbaro.name, p => p.mantemStatus()),
              ultimoTurno' =  Some(PerdeuTurno({jogador:barbaro, condition:Paralisado})),
            }
          }
           
        }
  
    }

  action acaoClerigo(clerigo: Personagem): bool = all {
    if(clerigo.hp > 0 and clerigo.Morto != true and clerigo.Paralisado != true){
      nondet receiver = personagens.values().filter(p =>(p.name == "Monstro" or p.name == "Monstro2")and p.hp > 0).oneOf()
      any{
        if(personagens.values().exists(p => p.name != "Monstro" and p.name != "Monstro2" and p.Paralisado == true)){
          removerParalisiaClerigo(clerigo)
        }else{
          if(personagens.values().exists(p => p.name == "Monstro" and p.name != "Monstro2" and (p.Paralisado != true or p.Provocado == true))){
            ataqueClerigo(clerigo,receiver)
          }else{
            imunizar(clerigo,receiver)
          }
        }
      }
    }else{
      if(clerigo.hp <= 0){
        all{
            personagens' = personagens.setBy(clerigo.name, p => p.atribuiMorto()),
            ultimoTurno' = Some(Morreu({jogador:clerigo})),
          }
      }else{
        all{
          personagens' = personagens.setBy(clerigo.name, p => p.mantemStatus()),
          ultimoTurno' =  Some(PerdeuTurno({jogador:clerigo, condition:Paralisado})),
        }
      }
    }
  }
    
  action acaoMago(mago: Personagem): bool = all{
    if(mago.hp > 0 and mago.Morto != true and mago.Paralisado != true){
      nondet receiver = personagens.values().filter(p =>(p.name == "Monstro" or p.name == "Monstro2" )and p.hp > 0).oneOf()
      any{
        if(personagens.values().exists(p => p.name != "Monstro" and p.name =="Monstro2" and p.Paralisado == true)){
          removerParalisia(mago)
        }else{
          if(personagens.values().exists(p => (p.name == "Monstro" or p.name == "Monstro2")and p.hp >= 0)){
            paralisar(mago,receiver)
          }else{
            ataqueHeroi(mago,receiver)
          }
        }
      }
    }else{
      if(mago.hp <= 0){
        all{
          personagens' = personagens.setBy(mago.name, p => p.atribuiMorto()),
          ultimoTurno' = Some(Morreu({jogador:mago})),
        }
      }else{
        all{
          personagens' = personagens.setBy(mago.name, p => p.mantemStatus()),
          ultimoTurno' =  Some(PerdeuTurno({jogador:mago, condition:Paralisado})),
        }
      }
     }  
    }

  action turno(attacker: Personagem): bool = {
    all {
      if(attacker.name == "Monstro" or attacker.name == "Monstro2"){
        acaoMonstro(attacker)
      }else{
        if(attacker.name == "Barbaro"){
          acaoBarbaro(attacker)
        }else{
          if(attacker.name == "Clerigo"){
            acaoClerigo(attacker)
          }else{
            acaoMago(attacker)
          }
        }
      }
    }
  }

  action init = {
    nondet d20Mago    = d20.oneOf()
    nondet d20Barbaro = d20.oneOf()
    nondet d20Clerigo = d20.oneOf()
    nondet d20Monstro = d20.oneOf()
    nondet d20Monstro2 = d20.oneOf()
    all {
      personagens' = Map(
        "Mago" -> {name:"Mago",hp:20,d20: d20Mago,Provocado: false, Paralisado: false,Imune:false, Morto: false},
        "Barbaro" -> {name:"Barbaro",hp:150,d20: d20Barbaro,Provocado: false, Paralisado: false,Imune:false, Morto: false},
        "Clerigo" -> {name:"Clerigo",hp:20,d20: d20Clerigo,Provocado: false, Paralisado: false,Imune:false, Morto: false},
        //"Monstro2" -> {name:"Monstro2", hp:100,d20: d20Monstro2,Provocado: false, Paralisado: false,Imune:false, Morto: false},
        "Monstro" -> {name:"Monstro", hp:100,d20: d20Monstro,Provocado: false, Paralisado: false,Imune:false, Morto: false}
      ),
      ultimoTurno' = None,
      round' = 0,
    }
  }

  action step = {
    val jogadoresValidos = personagens.values().toList((p1 , p2) => intCompare (p2.d20 , p1.d20))
    val jogador = jogadoresValidos[round % personagens.keys().size()]
    all {
      turno(jogador),
      round' = round + 1,
    }
  }

  
  val monstroNaoMorre = personagens.values().filter(p=> p.name == "Monstro" or p.name=="Monstro2").forall(p => p.hp >= 0)
  val ninguemMorre = personagens.values().filter(p => p.name != "Monstro" and p.name != "Monstro2").forall(p => p.hp >= 0)
  
  val inv = personagens.values().forall(p => p.hp >= 0)
  
}