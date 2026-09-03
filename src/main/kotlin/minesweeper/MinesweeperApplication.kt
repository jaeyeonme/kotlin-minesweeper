package minesweeper

import minesweeper.domain.RandomMinePlacementStrategy
import minesweeper.view.InputView
import minesweeper.view.OutputView

fun main() {
    val inputView = InputView()
    val configuration = inputView.readConfiguration()
    val minePlacementStrategy = RandomMinePlacementStrategy()
    val board = configuration.createBoard(minePlacementStrategy)
    val snapshot = board.snapshot()
    val outputView = OutputView()
    outputView.display(snapshot)
}
