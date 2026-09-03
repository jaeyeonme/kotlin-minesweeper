package minesweeper.domain

class CellSnapshot(
    private val position: Position,
    private val content: CellContent,
) {
    fun matches(position: Position): Boolean = this.position == position

    fun isMine(): Boolean = content == CellContent.MINE

    fun <T> mapContent(transform: (CellContent) -> T): T = transform(content)
}
