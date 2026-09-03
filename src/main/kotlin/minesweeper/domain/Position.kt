package minesweeper.domain

class Position(
    private val horizontal: Int,
    private val vertical: Int,
) {
    fun isWithin(
        height: Int,
        width: Int,
    ): Boolean = horizontal in 0 until width && vertical in 0 until height

    fun translatedBy(
        horizontalOffset: Int,
        verticalOffset: Int,
    ): Position = Position(horizontal + horizontalOffset, vertical + verticalOffset)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Position) return false
        return other.hasCoordinates(horizontal, vertical)
    }

    override fun hashCode(): Int = HASH_MULTIPLIER * horizontal + vertical

    override fun toString(): String = "Position(horizontal=$horizontal, vertical=$vertical)"

    private fun hasCoordinates(
        horizontal: Int,
        vertical: Int,
    ): Boolean = this.horizontal == horizontal && this.vertical == vertical

    companion object {
        private const val HASH_MULTIPLIER = 31

        fun fromIndex(
            index: Int,
            width: Int,
        ): Position = Position(horizontal = index % width, vertical = index / width)
    }
}
