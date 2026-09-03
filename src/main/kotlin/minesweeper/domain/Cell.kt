package minesweeper.domain

class Cell(
    private val position: Position,
) {
    private var state: CellState = CellState.CLOSED_SAFE

    fun placeMine() {
        state = state.placeMine()
    }

    fun open() {
        state = state.open()
    }

    fun matches(position: Position): Boolean = this.position == position

    fun isMine(): Boolean = state.isMine()

    fun isOpen(): Boolean = state.isOpen()

    fun snapshot(): CellSnapshot = CellSnapshot(position, state)
}
