package minesweeper.domain

@JvmInline
value class AdjacentMineCount(
    private val value: Int,
) {
    init {
        require(value in MINIMUM_COUNT..MAXIMUM_COUNT) { "주변 지뢰 개수는 0개 이상 8개 이하여야 합니다." }
    }

    override fun toString(): String = value.toString()

    private companion object {
        const val MINIMUM_COUNT = 0
        const val MAXIMUM_COUNT = 8
    }
}
