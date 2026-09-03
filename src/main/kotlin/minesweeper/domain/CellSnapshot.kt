package minesweeper.domain

class CellSnapshot(
    private val position: Position,
    private val state: CellState,
) {
    fun matches(position: Position): Boolean = this.position == position

    fun isMine(): Boolean = state.isMine()

    fun isOpen(): Boolean = state.isOpen()

    fun <T> mapContent(transform: (CellContent) -> T): T = state.mapContent(transform)

    fun <T> mapByContent(
        onMine: () -> T,
        onSafe: (Position) -> T,
    ): T =
        state.mapContent { content ->
            when (content) {
                CellContent.MINE -> onMine()
                CellContent.SAFE -> onSafe(position)
            }
        }
}
