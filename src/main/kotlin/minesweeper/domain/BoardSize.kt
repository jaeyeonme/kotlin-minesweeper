package minesweeper.domain

class BoardSize(
    private val height: Int,
    private val width: Int,
) {
    init {
        require(height >= MINIMUM_LENGTH) { "높이는 1 이상이어야 합니다." }
        require(width >= MINIMUM_LENGTH) { "너비는 1 이상이어야 합니다." }
    }

    fun numberOfCells(): Int = height * width

    fun positions(): List<Position> =
        (0 until numberOfCells()).map { index ->
            Position.fromIndex(index, width)
        }

    fun contains(position: Position): Boolean = position.isWithin(height, width)

    fun canContain(mineCount: MineCount): Boolean = mineCount.fitsWithin(numberOfCells())

    fun <T> splitIntoRows(values: List<T>): List<List<T>> {
        require(values.size == numberOfCells()) { "칸 수가 보드 크기와 일치하지 않습니다." }
        return values.chunked(width)
    }

    private companion object {
        const val MINIMUM_LENGTH = 1
    }
}
