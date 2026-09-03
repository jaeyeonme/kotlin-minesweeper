package minesweeper.domain

class MinePositions(
    private val values: List<Position>,
) {
    init {
        val uniquePositions = values.distinct()
        require(uniquePositions.size == values.size) { "지뢰 위치는 중복될 수 없습니다." }
    }

    fun count(): Int = values.size

    fun areWithin(boardSize: BoardSize): Boolean = values.all(boardSize::contains)

    fun forEach(action: (Position) -> Unit) {
        values.forEach(action)
    }
}
