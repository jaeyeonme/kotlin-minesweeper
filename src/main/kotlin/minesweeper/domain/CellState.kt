package minesweeper.domain

enum class CellState {
    CLOSED_SAFE,
    CLOSED_MINE,
    OPEN_SAFE,
    OPEN_MINE,
    ;

    fun placeMine(): CellState {
        require(this == CLOSED_SAFE) { "닫힌 안전 칸에만 지뢰를 배치할 수 있습니다." }
        return CLOSED_MINE
    }

    fun open(): CellState =
        when (this) {
            CLOSED_SAFE -> OPEN_SAFE
            CLOSED_MINE -> OPEN_MINE
            OPEN_SAFE -> OPEN_SAFE
            OPEN_MINE -> OPEN_MINE
        }

    fun isMine(): Boolean = this == CLOSED_MINE || this == OPEN_MINE

    fun isOpen(): Boolean = this == OPEN_SAFE || this == OPEN_MINE

    fun <T> mapContent(transform: (CellContent) -> T): T =
        when (this) {
            CLOSED_SAFE, OPEN_SAFE -> transform(CellContent.SAFE)
            CLOSED_MINE, OPEN_MINE -> transform(CellContent.MINE)
        }

    fun <T> mapVisibility(
        onClosed: () -> T,
        onMine: () -> T,
        onSafe: () -> T,
    ): T =
        when (this) {
            CLOSED_SAFE, CLOSED_MINE -> onClosed()
            OPEN_MINE -> onMine()
            OPEN_SAFE -> onSafe()
        }
}
