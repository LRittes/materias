module test3 {
    type Class = Mage | Warrior | Human
    type Player =  { name: str, class: Class, hp: int, attack: int, dead: bool }
    var players: str -> Player


    action init = all {
        players' = Map(
            "Mage" -> {name: "Let", class: Mage, hp: 10, attack: 10, dead: false},
            "Warrior" -> {name: "Gu", class: Warrior, hp: 20, attack: 5, dead: false}, 
            "Human" -> {name: "Leo", class: Human, hp: 15, attack: 3, dead: false}
        )
    }

    // action attackTo(attacker: Player, receiver: Player): bool = {
    //     receiver' = players.get(receiver) 
    // }

    action p(attacker) = all {
        players.filter()
    }
}