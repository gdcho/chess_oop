## Class Diagram

```
classDiagram
    class Board {
        -Square[][] squares
        +init(MoveListener)
        +getSquareAt(int, int): Square
        +getNumberOfRows(): int
        +getNumberOfCols(int): int
    }
    class Chess {
        -Board board
        -Player whitePlayer
        -Player blackPlayer
        -Player currentPlayer
        +init()
        +initPieces()
        +setPiece(int, int, Piece)
        +shutdown(int)
        +centre()
        +move(Square, Square)
        +getBoard(): Board
        +getCurrentPlayer(): Player
        +switchPlayers()
    }
    class MoveListener {
        -Chess game
        -Square startSquare
        +mouseClicked(MouseEvent)
    }
    class Piece {
        -String image
        -Player owner
        -Square square
        +getImage(): String
        +getOwner(): Player
        +setSquare(Square)
        +getSquare(): Square
        +validMove(Square, Board): boolean
    }
    class Player {
        -Color colour
    }
    class Square {
        -int row
        -int col
        -Piece piece
        -boolean isActive
        +setActive(boolean)
        +paint(Graphics)
        +getRow(): int
        +getCol(): int
        +setPiece(Piece)
        +getPiece(): Piece
    }
    Chess --> Board
    Chess --> Player
    MoveListener --> Chess
    MoveListener --> Square
    Piece --> Player
    Piece --> Square
    Square --> Piece
    Chess "1" *-- "0..*" Piece
    Board "1" *-- "8x8" Square
    Chess "1" *-- "0..*" Player
    Square "1" o-- "0..1" Piece
```
[![](https://mermaid.ink/img/pako:eNp1VVFv2jAQ_iuRn9IuIAJpgDxMalk1TVrXan1b6YNJDrCaxMx2oB3iv-9iB7BJmgfH_u7L-e67s7MnKc-AJCTNqZTfGF0JWsxLDx-NeHeciszbG6h-es9_Kyrg5fXl1ZN6Ks_GL6xkyn_gW_jJpIISxJVlXIEyH98qn5Uq8HC4SjyDubxfVbEA8bj8zXfSRw4yuwkznkvf-DlxDnYCszXgaCdgUlrUo4U-5fQDhLdbMwVm3jYucpq-fWZMKyGgVJdmI8nVBfDEIAXpwBIMepIm8PTa4awrlfFdqTO28BQ3FuC4K7AIvpE2aCS-qIXWoRb3zpWits3sZGpOKy25YypdG_iUiKO83QZOAUxJVrSAVlt5UlGhWh1R8ErCLGfpG2TYXri438JJAmdXrZnbsEqwcuWxgq6gXTZU06nmMY6OpvxRe6jFMB5d42Ptp1upY9v7nWVobF0HYUtzlj3YhdSlQuqC8xxo2ZW_ScvRm-cc-xPHSnR80WRsf4Ht5Qm-u0DQg62fFnpTjxbaBOYxeZsqtgVXB4P5DckWYkNxB_-7oJs1S-WFRHgLdF4CmFgbP52j1ulZHS11lc5xN2KYruz1vtrn4QzaZXU6uzZq2ic2u6pGs0t3Z9TmNmXR5HOwJqA5CefEu-71cDbo969xYXHMBWdzJu8TnNveP_djBdaEYFj8yAqPu5GAFCAKyjL8gej2mRO1BjzWJMFpBkta5WpO5uUBqbRS_PmjTEmiRAUBqTYZVdD8clzwPmOKC5IsaS4R3NDyD-cnDi5JsifvJAnjYX8yuLkJw2gcxYPpaBKQD5L0JmF_Gk_HcTQeTKPxMI4PAfmnPYT98c14WKOjKBpFg1EcENC7PTT_wfp1-A9VFhb3?type=png)](https://mermaid.live/edit#pako:eNp1VVFv2jAQ_iuRn9IuIAJpgDxMalk1TVrXan1b6YNJDrCaxMx2oB3iv-9iB7BJmgfH_u7L-e67s7MnKc-AJCTNqZTfGF0JWsxLDx-NeHeciszbG6h-es9_Kyrg5fXl1ZN6Ks_GL6xkyn_gW_jJpIISxJVlXIEyH98qn5Uq8HC4SjyDubxfVbEA8bj8zXfSRw4yuwkznkvf-DlxDnYCszXgaCdgUlrUo4U-5fQDhLdbMwVm3jYucpq-fWZMKyGgVJdmI8nVBfDEIAXpwBIMepIm8PTa4awrlfFdqTO28BQ3FuC4K7AIvpE2aCS-qIXWoRb3zpWits3sZGpOKy25YypdG_iUiKO83QZOAUxJVrSAVlt5UlGhWh1R8ErCLGfpG2TYXri438JJAmdXrZnbsEqwcuWxgq6gXTZU06nmMY6OpvxRe6jFMB5d42Ptp1upY9v7nWVobF0HYUtzlj3YhdSlQuqC8xxo2ZW_ScvRm-cc-xPHSnR80WRsf4Ht5Qm-u0DQg62fFnpTjxbaBOYxeZsqtgVXB4P5DckWYkNxB_-7oJs1S-WFRHgLdF4CmFgbP52j1ulZHS11lc5xN2KYruz1vtrn4QzaZXU6uzZq2ic2u6pGs0t3Z9TmNmXR5HOwJqA5CefEu-71cDbo969xYXHMBWdzJu8TnNveP_djBdaEYFj8yAqPu5GAFCAKyjL8gej2mRO1BjzWJMFpBkta5WpO5uUBqbRS_PmjTEmiRAUBqTYZVdD8clzwPmOKC5IsaS4R3NDyD-cnDi5JsifvJAnjYX8yuLkJw2gcxYPpaBKQD5L0JmF_Gk_HcTQeTKPxMI4PAfmnPYT98c14WKOjKBpFg1EcENC7PTT_wfp1-A9VFhb3)

## Sequence Diagram

```
sequenceDiagram
    participant Chess as Chess
    participant Board as Board
    participant Piece as Piece
    participant MoveListener as MoveListener
    participant Square as Square
    participant Player as Player

    Note over Chess,Board: Initialization
    Chess->>Board: initBoard()
    activate Board
    Board->>Square: initSquares(8x8)
    deactivate Board
    Chess->>Piece: initPieces()

    Note over Chess,MoveListener: Game Starts
    Chess->>MoveListener: init(MoveListener)
    MoveListener->>Square: mouseClicked(MouseEvent)
    activate Square
    Square->>Piece: getPiece()
    activate Piece
    Piece->>Player: getOwner()
    activate Player
    Chess->>Player: getCurrentPlayer()
    Player-->>Chess: Return Current Player
    deactivate Player
    Piece->>Board: validMove(Square, Board)
    activate Board
    alt Move is Valid
        Board-->>Piece: Confirm Move
        Square->>Piece: setPiece(Piece)
    else Move is Invalid
        Board-->>Piece: Reject Move
    end
    deactivate Board
    deactivate Piece
    Square->>Chess: move(Square, Square)
    deactivate Square
    Chess->>Chess: switchPlayers()
    Note over Chess,MoveListener: Next Player's Turn
    Chess->>Chess: endGame()

```
[![](https://mermaid.ink/img/pako:eNp9VMlu2zAQ_RWCl7qAYsi7pEMOdYMiQJMWcdFDoQshjWO2EumQlGMn8L-Xmyw6MmwgznD43ixvxnzHBS8BZ1jCSwOsgK-UPAtS5wzpz5YIRQu6JUyh5QakREQ6o3__hRNRmntr9O9_UijA3Fujf__Ad_CdSgUMhIGF5z569dIQYcM560K-ihxcJGflzGEeuQKkYwvXSGTLzdA9o4qSir4RRbmHWsDN7a2HUA2x5uCzuyeFojuiwwUtW1NzXFmO5Gw5SPaJZ5Zwidvmswo5qjWlSXi5-lClDH0jNaCV0irI84jnMBN4ELp8VaEraKHmjYRlRYt_UGqaPtztgKmPIoSDcHbXyjO4TnrKBctgTUOx47KcH6-6kj7Hz_NMso60bITQ5TlPS3anG420jAw9gWoEQx58FjMYTuhu6_PbsNPLUhrFBq7ZyE3yymqQym05ohL9Nmzn7ram02vJ2ZqK2sI71EdVZauq_faZoZJwSnPPdtcTPcFfKFSQB1h5bUVDbbrRnQrz4tahLO5_f_HDfWnH6Pnylapi48SX7Qivr_4j7NspfpLol57txci6PfMjMTFxhGsQNaGlfv7eDTrHagM15DjTZglr0lQqxzk7aihpFF8dWIEzJRqIcLMtdRP-tcTZmmjZW-9dSRUXJ6d-jv5wXrdMfcTZO97jbDwZD5N0HE-n8XwxScZJhA84G8XzYToZzRbpLEmT6WQxO0b4zQaIh7M0ThfxdDHXf7PRaB5hsMke3CtuH_Pjf2w17O0?type=png)](https://mermaid.live/edit#pako:eNp9VMlu2zAQ_RWCl7qAYsi7pEMOdYMiQJMWcdFDoQshjWO2EumQlGMn8L-Xmyw6MmwgznD43ixvxnzHBS8BZ1jCSwOsgK-UPAtS5wzpz5YIRQu6JUyh5QakREQ6o3__hRNRmntr9O9_UijA3Fujf__Ad_CdSgUMhIGF5z569dIQYcM560K-ihxcJGflzGEeuQKkYwvXSGTLzdA9o4qSir4RRbmHWsDN7a2HUA2x5uCzuyeFojuiwwUtW1NzXFmO5Gw5SPaJZ5Zwidvmswo5qjWlSXi5-lClDH0jNaCV0irI84jnMBN4ELp8VaEraKHmjYRlRYt_UGqaPtztgKmPIoSDcHbXyjO4TnrKBctgTUOx47KcH6-6kj7Hz_NMso60bITQ5TlPS3anG420jAw9gWoEQx58FjMYTuhu6_PbsNPLUhrFBq7ZyE3yymqQym05ohL9Nmzn7ram02vJ2ZqK2sI71EdVZauq_faZoZJwSnPPdtcTPcFfKFSQB1h5bUVDbbrRnQrz4tahLO5_f_HDfWnH6Pnylapi48SX7Qivr_4j7NspfpLol57txci6PfMjMTFxhGsQNaGlfv7eDTrHagM15DjTZglr0lQqxzk7aihpFF8dWIEzJRqIcLMtdRP-tcTZmmjZW-9dSRUXJ6d-jv5wXrdMfcTZO97jbDwZD5N0HE-n8XwxScZJhA84G8XzYToZzRbpLEmT6WQxO0b4zQaIh7M0ThfxdDHXf7PRaB5hsMke3CtuH_Pjf2w17O0)

## Collaboration Diagram

```
@startuml

object "Chess" as Chess
object "Board" as Board
object "Piece" as Piece
object "Square" as Square
object "Player" as Player

Square --> Piece : getPiece(), setPiece()
Chess --> Player : getCurrentPlayer()
Piece -> Board : validMove(Square, Board)
Board --> Square : has-a
Piece -> Player : getOwner()

Square -> Chess : move(Square, Square)
@enduml
```

![](https://user-images.githubusercontent.com/48280799/275385025-f9789c01-66f7-47e3-ab3d-9ff8361fa42a.png)


## Use case Diagram

```
@startuml
left to right direction
  actor "Player" as p1
  
  rectangle "Chess Game" {
    usecase "Start Game" as UC1
    usecase "Click Square" as UC2
    usecase "Make Move" as UC3
    usecase "End Game" as UC4
    usecase "Validate Move" as UC5
    usecase "Switch Player" as UC6
  }
  
  p1 -- UC1
  p1 -- UC2
  p1 -- UC3
  p1 -- UC4
  p1 -- UC5
  p1 -- UC6
@enduml
```
![](https://private-user-images.githubusercontent.com/48280799/275301923-0b3ae8a2-3c54-4b91-bd51-0ed46f8e6734.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTEiLCJleHAiOjE2OTczNDU0MjIsIm5iZiI6MTY5NzM0NTEyMiwicGF0aCI6Ii80ODI4MDc5OS8yNzUzMDE5MjMtMGIzYWU4YTItM2M1NC00YjkxLWJkNTEtMGVkNDZmOGU2NzM0LnBuZz9YLUFtei1BbGdvcml0aG09QVdTNC1ITUFDLVNIQTI1NiZYLUFtei1DcmVkZW50aWFsPUFLSUFJV05KWUFYNENTVkVINTNBJTJGMjAyMzEwMTUlMkZ1cy1lYXN0LTElMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1EYXRlPTIwMjMxMDE1VDA0NDUyMlomWC1BbXotRXhwaXJlcz0zMDAmWC1BbXotU2lnbmF0dXJlPTE5MzNmODhkZjkzOWNkYTM5YmY3ODQ0N2Y1N2QzMTE3NmIyMmE4OTYwZWM3NjI3NzczNjZlM2IxMGZlNWZlNWImWC1BbXotU2lnbmVkSGVhZGVycz1ob3N0JmFjdG9yX2lkPTAma2V5X2lkPTAmcmVwb19pZD0wIn0.T3pLz2UqrcgZP6x94nxrIB34EpxJQMd9klCfDa5qUMc)
