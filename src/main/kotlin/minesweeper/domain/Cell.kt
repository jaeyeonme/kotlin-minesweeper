package minesweeper.domain

class Cell(
    private val position: Position,
) {
    private var content: CellContent = CellContent.SAFE

    fun placeMine() {
        content = CellContent.MINE
    }

    fun matches(position: Position): Boolean = this.position == position

    fun snapshot(): CellSnapshot = CellSnapshot(position, content)
}
