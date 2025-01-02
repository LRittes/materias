module teste{
  
  type Acao = Atacar | RemoverParalisia | Paralisar | Cegar | TransformarEmUrso | CriarIlusao | Nenhuma
  type Status = Cego | Paralisado | Nenhum | Morto
  type Personagem = {
    name: str,
    hp: int,
    hpOriginal: int, // Adicionado para o Druida
    Cego: bool,      // Adicionado para indicar cegueira nos monstros
    Paralisado: bool,
    Morto: bool
  }

  
    var round: int
    var personagens: str -> Personagem


    action init = {
        personagens' = Map(
            "Mago" -> {name: "Mago", hp: 20, hpOriginal: 20, Cego: false, Paralisado: false, Morto: false},
            "Cacador" -> {name: "Cacador", hp: 20, hpOriginal: 20, Cego: false, Paralisado: false, Morto: false},
            "Druida" -> {name: "Druida", hp: 20, hpOriginal: 20, Cego: false, Paralisado: false, Morto: false},
            "Monstro" -> {name: "Monstro", hp: 100, hpOriginal: 100, Cego: false, Paralisado: false, Morto: false}
        )
    }

    // transformar sem Set
    // personagens.keys().map(k => personagens.get(k))

    // transformar de volta em Map
    // personagens.keys().map(k => personagens.get(k)).fold(Set(),(acc,i) => acc.union(Set((i.name,i)))).setToMap()

    // filtrar pra tirar mago
    // personagens.keys().map(k => personagens.get(k)).filter(x => x.name != "Mago")

}