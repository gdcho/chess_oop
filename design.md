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
    Chess --> Board: 
    Chess --> Player: 
    MoveListener --> Chess: 
    MoveListener --> Square: 
    Piece --> Player: 
    Piece --> Square: 
    Square --> Piece: 
    Chess "+1" -- "+0..*" Piece : 
    Chess "+1" -- "+0..*" Square : 
    Chess "+1" -- "+0..*" Player : 
    Square "+1" -- "+0..1" Piece : 
```

[![](https://mermaid.ink/img/pako:eNqNVV1v2jAU_SuWn8IaEAlfbR4mtayaJo21Wt9W-mCSC1hNYmY7sBbx3-ePEOIk1caDic85vvY99zo54pglgCMcp0SIL5RsOMmWOVI_g6A7RniCjhbSv_7T74JweH55fkHCPIoLeUVzKr0F28N3KiTkwHs1cgPSLr6VHs2lj9TQi5DFXN2PIlsBf1j_ZAfhKY1SdgvmLBWejVNpTvUE5ltQYz0Bm9JKjzX0MSVvwNFhSyXY5za5Skn8-hEZF5xDLpu0taTXAB4pxCAcWIBFK2t8ZOaOZlvIhB1yk3ENj9XGHJxwmSqCZ631S4sbtTA-aHPvXCs0N68nozWttMSBynhr4SoRx_l6GzgFsCXZkAxabYWEJFy2OiJjhYB5SuNXSFR7qcn9HioLnF2NZ27DSk7zDaIZ2UC7bMpNp5rnc3Q05TcdQZthI7rkg47T7dS57b3OMpRc10XYk5Qmi3ohTamUdMVYCiTvyt-m5fjNUqb6U40F71hRZlxfodoLcXZoICpC3T9j9E6PNbQ8GKLiNpZ0D64PFvNKUd2IHVE7eF852W1pLBoWqbdA50tAJdbGq3vUuj2bM6OrdDl3aYbtyn7_szU5Qk3YOnvGne7WtBF-yFqTz7T1rh30grv6skRmgZa4h1viq2CJFaufhoPBJzWxkf6tKyP_R0DbV40TNZWBszX2cQY8IzRRXxjTX0sst6DuPVY0TmBNilQu8TI_KSkpJHt6y2McSV6Aj4tdQiSU3yQcrUkqKvQ-oZLxCtyR_Bdj2XmlmuLoiP_gqB_MBpNwFkxGw2ASjKaz0MdvGh6PB-FoOAuvg_HNOBgPpycfv5sQwWA4mkzD4Ww2GYU3YXg99TGY7Rblp1L_nf4CQX8csA?type=png)](https://mermaid.live/edit#pako:eNqNVV1v2jAU_SuWn8IaEAlfbR4mtayaJo21Wt9W-mCSC1hNYmY7sBbx3-ePEOIk1caDic85vvY99zo54pglgCMcp0SIL5RsOMmWOVI_g6A7RniCjhbSv_7T74JweH55fkHCPIoLeUVzKr0F28N3KiTkwHs1cgPSLr6VHs2lj9TQi5DFXN2PIlsBf1j_ZAfhKY1SdgvmLBWejVNpTvUE5ltQYz0Bm9JKjzX0MSVvwNFhSyXY5za5Skn8-hEZF5xDLpu0taTXAB4pxCAcWIBFK2t8ZOaOZlvIhB1yk3ENj9XGHJxwmSqCZ631S4sbtTA-aHPvXCs0N68nozWttMSBynhr4SoRx_l6GzgFsCXZkAxabYWEJFy2OiJjhYB5SuNXSFR7qcn9HioLnF2NZ27DSk7zDaIZ2UC7bMpNp5rnc3Q05TcdQZthI7rkg47T7dS57b3OMpRc10XYk5Qmi3ohTamUdMVYCiTvyt-m5fjNUqb6U40F71hRZlxfodoLcXZoICpC3T9j9E6PNbQ8GKLiNpZ0D64PFvNKUd2IHVE7eF852W1pLBoWqbdA50tAJdbGq3vUuj2bM6OrdDl3aYbtyn7_szU5Qk3YOnvGne7WtBF-yFqTz7T1rh30grv6skRmgZa4h1viq2CJFaufhoPBJzWxkf6tKyP_R0DbV40TNZWBszX2cQY8IzRRXxjTX0sst6DuPVY0TmBNilQu8TI_KSkpJHt6y2McSV6Aj4tdQiSU3yQcrUkqKvQ-oZLxCtyR_Bdj2XmlmuLoiP_gqB_MBpNwFkxGw2ASjKaz0MdvGh6PB-FoOAuvg_HNOBgPpycfv5sQwWA4mkzD4Ww2GYU3YXg99TGY7Rblp1L_nf4CQX8csA)

## Sequence Diagram

```
sequenceDiagram
    participant Chess as Chess
    participant MoveListener as MoveListener
    participant Square as Square
    participant Piece as Piece
    participant Player as Player
    participant Board as Board
    
    Note over Chess,MoveListener: Game Starts
    Chess->>MoveListener: init(MoveListener)
    MoveListener->>Square: mouseClicked(MouseEvent)
    activate Square
    Square->>Piece: getPiece()
    activate Piece
    Piece->>Player: getOwner()
    activate Player
    Player-->>Piece: Return Player Info
    deactivate Player
    Piece->>Board: validMove(Square, Board)
    activate Board
    Board-->>Piece: Validate Move
    deactivate Board
    Piece-->>Square: Return Validation Result
    deactivate Piece
    Square->>Chess: move(Square, Square)
    deactivate Square
    Chess->>Chess: switchPlayers()
    Note over Chess,MoveListener: Next Player's Turn
```
[![](https://mermaid.ink/img/pako:eNp9U8tuwjAQ_BXLl4IUfsAHDqWoqtTSqlQ95WI5S7Ga2OBHKEL8e21vABejcgiT9cw-Zp0DFboByqiFrQcl4EHyL8O7WpHw23DjpJAbrhyZrcFawi2C8vxF9_AsrQMFJtLy95K93HpuIPIQlYw3CSIRErhx3vI9VkJUMu41N00kJIDn-FxoByT0Z3CYKu-VkUfeAVm6kGkYM5Em0-lfmlTSjfLQGNl5KIhwPkY67S3MWim-oQmy8DLvQblBxIWTPQ9d5XYgDjmSBYx8gUtodC3KLEowSpIpSfO6C52Umsw1xJNLpXdw3qiTx09qpZHYwG39UDQZzUjPW9lEG0Y4QYUbuO4gW0uCWf3PmCFyYpaidCbEypnNQ-dDAqlViFjfurL_i2dnn9Oi46qy1vF_XOjzRZ0uyKC3O-nEGg2yJ-P_v3ML-Dld6TtLPsIItaIV7cB0XDbh-zzELDV1a-igpizABlY8DkZrdQxU7p1e7pWgzBkPFfWbaODwOVO24q09R-eNdNoMweMvBEteWg?type=png)](https://mermaid.live/edit#pako:eNp9U8tuwjAQ_BXLl4IUfsAHDqWoqtTSqlQ95WI5S7Ga2OBHKEL8e21vABejcgiT9cw-Zp0DFboByqiFrQcl4EHyL8O7WpHw23DjpJAbrhyZrcFawi2C8vxF9_AsrQMFJtLy95K93HpuIPIQlYw3CSIRErhx3vI9VkJUMu41N00kJIDn-FxoByT0Z3CYKu-VkUfeAVm6kGkYM5Em0-lfmlTSjfLQGNl5KIhwPkY67S3MWim-oQmy8DLvQblBxIWTPQ9d5XYgDjmSBYx8gUtodC3KLEowSpIpSfO6C52Umsw1xJNLpXdw3qiTx09qpZHYwG39UDQZzUjPW9lEG0Y4QYUbuO4gW0uCWf3PmCFyYpaidCbEypnNQ-dDAqlViFjfurL_i2dnn9Oi46qy1vF_XOjzRZ0uyKC3O-nEGg2yJ-P_v3ML-Dld6TtLPsIItaIV7cB0XDbh-zzELDV1a-igpizABlY8DkZrdQxU7p1e7pWgzBkPFfWbaODwOVO24q09R-eNdNoMweMvBEteWg)

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
