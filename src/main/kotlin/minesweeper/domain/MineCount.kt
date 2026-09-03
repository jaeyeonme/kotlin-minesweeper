package minesweeper.domain

class MineCount(
    private val value: Int,
) {
    init {
        require(value >= MINIMUM_COUNT) { "지뢰 개수는 1 이상이어야 합니다." }
    }

    fun fitsWithin(cellCount: Int): Boolean = value <= cellCount

    fun matches(actualCount: Int): Boolean = value == actualCount

    fun takeFrom(positions: List<Position>): List<Position> = positions.take(value)

    private companion object {
        const val MINIMUM_COUNT = 1
    }
}
