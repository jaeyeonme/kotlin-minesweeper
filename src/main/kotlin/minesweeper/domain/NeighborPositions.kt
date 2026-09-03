package minesweeper.domain

class NeighborPositions private constructor(
    private val values: List<Position>,
) {
    fun count(): Int = values.size

    fun contains(position: Position): Boolean = values.contains(position)

    fun countMatching(predicate: (Position) -> Boolean): Int = values.count(predicate)

    companion object {
        fun around(
            center: Position,
            boardSize: BoardSize,
        ): NeighborPositions {
            val candidates = candidatesAround(center)
            val positions = candidates.filter(boardSize::contains)
            return NeighborPositions(positions)
        }

        private fun candidatesAround(center: Position): List<Position> =
            listOf(
                center.translatedBy(horizontalOffset = -1, verticalOffset = -1),
                center.translatedBy(horizontalOffset = 0, verticalOffset = -1),
                center.translatedBy(horizontalOffset = 1, verticalOffset = -1),
                center.translatedBy(horizontalOffset = -1, verticalOffset = 0),
                center.translatedBy(horizontalOffset = 1, verticalOffset = 0),
                center.translatedBy(horizontalOffset = -1, verticalOffset = 1),
                center.translatedBy(horizontalOffset = 0, verticalOffset = 1),
                center.translatedBy(horizontalOffset = 1, verticalOffset = 1),
            )
    }
}
