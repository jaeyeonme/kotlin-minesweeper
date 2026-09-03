package minesweeper.controller

import minesweeper.domain.Game
import minesweeper.domain.MinePlacementStrategy
import minesweeper.view.InputView
import minesweeper.view.OutputView

class MinesweeperGame(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun start(minePlacementStrategy: MinePlacementStrategy) {
        val configuration = inputView.readConfiguration()
        val game = configuration.createGame(minePlacementStrategy)
        outputView.displayStart()
        play(game)
    }

    private fun play(game: Game) {
        while (game.isInProgress()) {
            playTurn(game)
        }
    }

    private fun playTurn(game: Game) {
        val position = inputView.readPosition()
        game.open(position)
        if (game.isLost()) {
            outputView.displayLoss()
            return
        }
        val snapshot = game.snapshot()
        outputView.display(snapshot)
    }
}
