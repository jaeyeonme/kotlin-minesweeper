package domain

import enum.CellStatus

data class Cell(val position: Position, var status: CellStatus = CellStatus.EMPTY) {

    fun placeMine() {
        status = CellStatus.MINE
    }

    fun isMine(): Boolean = status == CellStatus.MINE
}
