package inteface

import domain.Board
import domain.Position

interface MineCounter {
    fun countMinesAround(board: Board, position: Position): Int
}
