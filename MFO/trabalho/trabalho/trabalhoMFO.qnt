module trabalho {
  import basicSpells.* from "../spells/basicSpells"
  import rareSpells.* from "../spells/rareSpells"
  
  type Acao = Atacar | RemoverParalisia | Paralisar | Cegar | TransformarEmUrso | CriarIlusao | Nenhuma
  type Status = Cego | Paralisado | Nenhum | Morto
  type Personagem = {
    name: str,
    hp: int,
    hpOriginal: int, // Adicionado para o Druida
    d20: int,
    Cego: bool,      // Adicionado para indicar cegueira nos monstros
    Paralisado: bool,
    Morto: bool
  }
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
      { ...p, hp: p.hp - d }
  }

  /*
  pure def semIlusao(p: Personagem): Personagem = {
    { ...p, name: "semIlusao", hp : 0 }
  }*/

  pure def aplicaCegueira(p: Personagem): Personagem = {
    {...p, Cego: true}
  }

  pure def transformaEmUrso(p: Personagem): Personagem = {
    { ...p, hpOriginal: p.hp, hp: 60, name : "Urso" }
  }

  pure def reverterUrso(p: Personagem): Personagem = {
    {...p, hp: p.hpOriginal }
  }

  /*
  pure def aplicaProvocar(p: Personagem): Personagem = {
    {...p, Provocado: true}
  }*/

  pure def removeStatus(p: Personagem): Personagem = {
    {...p, Paralisado: false}
  }

  pure def removeParalisia(p: Personagem): Personagem = {
    {...p,Paralisado: false}
  }

  pure def aplicaParalisia(p: Personagem): Personagem = {
    {...p,Paralisado:true}
  }

  /*
  pure def aplicaImunidade(p: Personagem): Personagem = {
    {...p,Imune:true}
  }*/
  
  /*
  pure def atribuiMorto(p: Personagem): Personagem = {
     {...p, Morto:true}
  }*/

  pure def mantemStatus(p: Personagem): Personagem ={
    {...p, Paralisado: p.Paralisado, Morto: p.Morto}
  }

  action ataqueHeroi(attacker: Personagem, receiver:Personagem): bool = all{
    personagens' = personagens.setBy(receiver.name, p => p.damage(10)),
    ultimoTurno' = Some(Attack({ attacker: attacker, receiver: receiver, dano: 10 })),
  }

  /*
  action ataqueClerigo(attacker: Personagem, receiver: Personagem): bool = all{
    if(attacker.Imune == true){
      all{
          personagens' =personagens.keys().mapBy(name => if(name != "Monstro") personagens.get(name).removeStatus() else receiver.damage(10)),
          ultimoTurno' = Some(Attack({ attacker: attacker, receiver: receiver, dano: 10 })),
      }
    }else{
      all{
        personagens' = personagens.setBy(receiver.name, p => p.damage(10)),
          ultimoTurno' = Some(Attack({ attacker: attacker, receiver: receiver, dano: 10 })),
      }
    }
  }
  */

  /*
  action provocar(attacker: Personagem, receiver:Personagem): bool = all{
    personagens' = personagens.setBy(receiver.name, p => p.aplicaProvocar()),
    ultimoTurno' = Some(ApplyCondition({ caster: attacker, receiver: receiver, condition: Provocado})),
  }*/

  action removerParalisia(attacker: Personagem): bool = all{
    nondet receiver = personagens.values().filter(p =>p.name != "Monstro" and p.name != attacker.name /*and p.Paralisado == true*/).oneOf()
    all{
      personagens' = personagens.setBy(receiver.name, p => p.removeParalisia()),
      ultimoTurno' = Some( RemoveCondition({ caster: attacker,receiver:receiver, condition: Paralisado })),
    }
  }

  action paralisar(attacker: Personagem, receiver: Personagem): bool = all{
    personagens' = personagens.setBy(receiver.name, p => p.aplicaParalisia()),
    ultimoTurno' = Some(ApplyCondition({ caster: attacker, receiver: receiver, condition: Paralisado})),
  }
  
  action criarIlusao(mago: Personagem): bool = all {
    personagens' = personagens.set("Ilusao", { name: "Ilusao", hp: 1, hpOriginal: 1, d20: 0, Cego: false, Paralisado: false, Morto: false }),
    ultimoTurno' = Some(ApplyCondition({ caster: mago, receiver: personagens.get("Ilusao"), condition: Nenhum }))
  }

  action cegueira(attacker: Personagem, receiver: Personagem): bool = all {
    personagens' = personagens.setBy(receiver.name, p => p.aplicaCegueira()),
    ultimoTurno' = Some(ApplyCondition({ caster: attacker, receiver: receiver, condition: Cego}))
  }
  /*
  action imunizar(attacker: Personagem, receiver: Personagem):bool = all{
    personagens' = personagens.keys().mapBy(name => if(name != "Monstro") personagens.get(name).aplicaImunidade() else personagens.get(name).mantemStatus()),
    ultimoTurno' = Some(ApplyCondition({caster: attacker, receiver: receiver, condition: Imune})),
  }*/

  action ataqueMonstro(attacker: Personagem, receiver: Personagem):bool = all{
    if(round >= personagens.values().size()){
      all{
        personagens' = personagens.keys().mapBy(name => if (name == receiver.name) receiver.damage(20) else if (name=="Monstro") attacker.removeStatus() else personagens.get(name).mantemStatus()),
        ultimoTurno' = Some(Attack({ attacker: attacker, receiver: receiver, dano: 20 })),
      }
    }else{
      all{
        personagens' = personagens.keys().mapBy(name => if (name == receiver.name) receiver.damage(10) else if (name=="Monstro") attacker.removeStatus() else personagens.get(name).mantemStatus()),
        ultimoTurno' =  Some(Attack({ attacker: attacker, receiver: receiver, dano: 10 })),
      } 
    },
  }

  action acaoMonstro(monstro: Personagem): bool = all {
    if (personagens.values().exists(p => p.name == "Ilusao" or p.name == "Urso")) {
        // Se existir Ilusão ou Urso, escolhe aleatoriamente entre eles
        nondet receiver = personagens.values().filter(p => p.name == "Ilusao" or p.name == "Urso").oneOf()
        any {
            ataqueMonstro(monstro, receiver),
            paralisar(monstro, receiver)
        }
    } else {
        // Se não houver Ilusão nem Urso, ataca um personagem qualquer
        nondet receiver = personagens.values().filter(p => p.name != "Monstro").oneOf()
        any {
            ataqueMonstro(monstro, receiver),
            paralisar(monstro, receiver)
        }
    }
  }

    action acaoDruida(druida: Personagem): bool = all {
    if (druida.hp > 0 and druida.Morto != true and druida.Paralisado != true) {
        // Seleciona um alvo para o ataque
        nondet receiver = personagens.values().filter(p => p.name == "Monstro").oneOf()
        // Ações possíveis do Druida
        any{
          ataqueHeroi(druida, receiver),
          removerParalisia(druida),
          all {
          personagens' = personagens.setBy(druida.name, p => p.transformaEmUrso()),
          ultimoTurno' = Some(PerdeuTurno({ jogador: druida, condition: Paralisado }))
          }
        }
    } else {
    // Mantém o status caso o druida não possa agir
      all {
        personagens' = personagens.setBy(druida.name, p => p.mantemStatus()),
        ultimoTurno' = Some(PerdeuTurno({ jogador: druida, condition: Paralisado }))
      }
    }
  }

  action acaoCacador(cacador: Personagem): bool = all {
    if(cacador.hp > 0 and cacador.Morto != true and cacador.Paralisado != true) {
      nondet receiver = personagens.values().filter(p => p.name == "Monstro").oneOf()
      any {
        ataqueHeroi(cacador, receiver),
        paralisar(cacador, receiver),
        removerParalisia(cacador),
        cegueira(cacador, receiver)
      }
    } else {
      all {
        personagens' = personagens.setBy(cacador.name, p => p.mantemStatus()),
        ultimoTurno' = Some(PerdeuTurno({jogador: cacador, condition: Paralisado}))
      }
    }
  } 

    /*
    action acaoBarbaro(barbaro: Personagem): bool = all{
        if(barbaro.hp > 0 and barbaro.Morto != true and barbaro.Paralisado != true){
          nondet receiver = personagens.values().filter(p =>p.name == "Monstro").oneOf()
          any{
            ataqueHeroi(barbaro,receiver),
            provocar(barbaro,receiver),
            removerParalisia(barbaro),
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
  
    }*/
  /*
  action acaoClerigo(clerigo: Personagem): bool = all {
    if(clerigo.hp > 0 and clerigo.Morto != true and clerigo.Paralisado != true){
      nondet receiver = personagens.values().filter(p =>p.name == "Monstro").oneOf()
      any{
        ataqueClerigo(clerigo,receiver),
        imunizar(clerigo,receiver),
        removerParalisia(clerigo),
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
  }*/
  
  action acaoMago(mago: Personagem): bool = all {
    // Remove a ilusão se já existir no turno do mago
    if (personagens.values().exists(p => p.name == "Ilusao")) {
      // transformando Map em Set, aplicando o filter e transformando de volta em Map
      personagens' = personagens.keys().map(k => personagens.get(k)).filter(x => x.name != "Ilusao").fold(Set(),(acc,i) => acc.union(Set((i.name,i)))).setToMap()
    } else {
      personagens' = personagens // Mantém os personagens inalterados se não houver ilusão
    },
    // Ações disponíveis para o mago
    nondet receiver = personagens.values().filter(p => p.name == "Monstro").oneOf()
    any {
      ataqueHeroi(mago, receiver),
      paralisar(mago, receiver),
      removerParalisia(mago),
      criarIlusao(mago)
    }
  }


  action turno(attacker: Personagem): bool = {
    
      if(attacker.name == "Monstro") {
        acaoMonstro(attacker)
      } else if(attacker.name == "Cacador") {
        acaoCacador(attacker)
      } else if(attacker.name == "Druida") {
        acaoDruida(attacker)
      } else {
        acaoMago(attacker)
      }
  }

  action init = {
    nondet d20Mago = d20.oneOf()
    nondet d20Cacador = d20.oneOf()
    nondet d20Druida = d20.oneOf()
    nondet d20Monstro = d20.oneOf()

    all {
      personagens' = Map(
        "Mago" -> {name: "Mago", hp: 20, hpOriginal: 20, d20: d20Mago, Cego: false, Paralisado: false, Morto: false},
        "Cacador" -> {name: "Cacador", hp: 20, hpOriginal: 20, d20: d20Cacador, Cego: false, Paralisado: false, Morto: false},
        "Druida" -> {name: "Druida", hp: 20, hpOriginal: 20, d20: d20Druida, Cego: false, Paralisado: false, Morto: false},
        "Monstro" -> {name: "Monstro", hp: 100, hpOriginal: 100, d20: d20Monstro, Cego: false, Paralisado: false, Morto: false}//,
        //"Ilusao" -> { name: "semIlusao", hp: 0, hpOriginal: 0, d20: 0, Cego: false, Paralisado: false, Morto: false }
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

  val monstroNaoMorre = personagens.values().filter(p=> p.name == "Monstro" or p.name=="Monstro2").forall(p => p.hp > 0)
  val ninguemMorre = personagens.values().filter(p => p.name != "Monstro" and p.name != "Monstro2").forall(p => p.hp >= 0)
  val barbaroNaoMorre = personagens.values().filter(p => p.name == "Barbaro").forall(p => p.hp >= 0)
  val inv = personagens.values().forall(p => p.hp >= 0)
  
}