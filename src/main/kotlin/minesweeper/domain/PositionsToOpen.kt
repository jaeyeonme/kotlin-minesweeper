package minesweeper.domain

class PositionsToOpen(
    firstPosition: Position,
) {
    private val values = ArrayDeque<Position>()

    init {
        values.add(firstPosition)
    }

    fun hasNext(): Boolean = values.isNotEmpty()

    fun removeFirst(): Position = values.removeFirst()

    fun addAll(positions: NeighborPositions) {
        positions.forEach(values::add)
    }
}
