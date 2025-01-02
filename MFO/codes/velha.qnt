module velha {
    type Player = X | O
    type Square = Occupied(Player) | Empty

    var board: int -> int -> Square
    var nextTurn: Player

    pure val boardCoordinates = tuples(1.to(3), 1.to(3))

    def square(coordinates: (int, int)): Square = board.get(coordinates._1).get(coordinates._2)

    def hasPlayer(coordinate, player) = match square(coordinate){
        | Empty => false
        | Occupied(p) => player == p
    }    

    def isEmpty(coordinate) = match square(coordinate){
        | Empty => true
        | _ => false
    }

    val boardEmpty = boardCoordinates.forall(isEmpty)
    val boardFull = not(boardCoordinates.exists(isEmpty))

    pure val winningPatters = Set(
        Set((1,1), (1,2), (1,3)),
        Set((2,1), (2,2), (2,3)),
        Set((3,1), (3,2), (3,3)),
        Set((1,1), (2,1), (3,1)),
        Set((1,2), (2,2), (3,2)),
        Set((1,3), (2,3), (3,3)),
        Set((1,1), (2,2), (3,3)),
        Set((1,3), (2,2), (3,1))
    )

    def won(player) = winningPatters.exists(pattern => pattern.forall(coordinate => hasPlayer(coordinate, player)))

    val gameOver = won(X) or won(O) or boardFull

    action Move(player, coordinate) = all {
        isEmpty(coordinate),
        board' = board.setBy(
            coordinate._1,
            row => row.set(coordinate._2, Occupied(player))
        )
    }

    action MoveToEmpty(player) = all {
        not(gameOver),
        nondet coordinate = boardCoordinates.filter(isEmpty).oneOf()
        Move(player, coordinate)
    }

    action MoveO = all {
        nextTurn == O,
        MoveToEmpty(O),
        nextTurn' = X
    }

    // action MoveX = all {
    //     nextTurn == X,
    //     MoveToEmpty(X),
    //     nextTurn' = O
    // }

    action MoveX = all {
        nextTurn == X,
        if(boardEmpty) StartInCorner else
        if(canWin) Win else
        if(canBlock) Block else
        if(canTakeCenter) TakeCenter else
        if(canSetupWin) SetupWin else
        MoveToEmpty(X),
        nextTurn' = O
    }

    action init = all {
        nextTurn' = X,
        board' = 1.to(3).mapBy(_ => 1.to(3).mapBy(_ => Empty))
    }

    action step = any {
        MoveX,
        MoveO,
        all {
            gameOver, board' = board, nextTurn' = nextTurn
        }
    }

    val stalemate = boardFull and not(won(X)) and not(won(O))

    val NotStalemate = not(stalemate)

    pure val coners = Set((1,1),(1,3),(3,1), (3,3))

    action StartInCorner = {
        nondet corner = oneOf(coners)
        Move(X, corner)
    }

    val canWin = winningPatters.exists(canWinWithPattern)
    val canBlock = winningPatters.exists(canBlockWithPattern)
    val canTakeCenter = isEmpty((2,2))
    val canSetupWin = winningPatters.exists(canSetupWinWithPattern)

    def canWinWithPattern(pattern) = and {
        pattern.filter(coordinate => coordinate.hasPlayer(X)).size() == 2,
        pattern.filter(coordinate => coordinate.isEmpty()).size() == 1,
    }

    def canBlockWithPattern(pattern) = and {
        pattern.filter(coordinate => coordinate.hasPlayer(O)).size() == 2,
        pattern.filter(coordinate => coordinate.isEmpty()).size() == 1,
    }

    def canSetupWinWithPattern(pattern) = and {
        pattern.filter(coordinate => coordinate.hasPlayer(X)).size() == 1,
        pattern.filter(coordinate => coordinate.isEmpty()).size() == 2,
    }

    action Win = all {
        canWin,
        nondet pattern = winningPatters.filter(canWinWithPattern).oneOf()
        nondet coordinate = pattern.filter(isEmpty).oneOf()
        Move(X,coordinate)
    }

    action Block = all {
        canBlock,
        nondet pattern = winningPatters.filter(canBlockWithPattern).oneOf()
        nondet coordinate = pattern.filter(isEmpty).oneOf()
        Move(X,coordinate)
    }

    action TakeCenter = Move(X, (2,2))

    action SetupWin = all {
        canWin,
        nondet pattern = winningPatters.filter(canSetupWinWithPattern).oneOf()
        nondet coordinate = pattern.filter(isEmpty).oneOf()
        Move(X,coordinate)
    }

    val XHasNotWin = not(won(X))
    val OHasNotWin = not(won(O))

    temporal XMustEventuallyWin = eventually(won(X))

}