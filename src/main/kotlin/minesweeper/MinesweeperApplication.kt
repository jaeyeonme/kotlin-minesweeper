package minesweeper

import minesweeper.controller.MinesweeperGame
import minesweeper.domain.RandomMinePlacementStrategy
import minesweeper.view.InputView
import minesweeper.view.OutputView

fun main() {
    val inputView = InputView()
    val outputView = OutputView()
    val game = MinesweeperGame(inputView, outputView)
    val minePlacementStrategy = RandomMinePlacementStrategy()
    game.start(minePlacementStrategy)
}
