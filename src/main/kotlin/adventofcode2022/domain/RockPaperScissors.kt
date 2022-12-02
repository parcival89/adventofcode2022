package adventofcode2022.domain

import adventofcode2022.domain.Element.Result
import adventofcode2022.domain.Element.Result.*

class RockPaperScissorsSimulator {
    companion object {
        fun playRound(choiceOpponent: String, choicePlayer: String): Int {
            val opponent = mapChoice(choiceOpponent)
            val player = mapChoice(choicePlayer)
            return player.number() + player.fight(opponent).score
        }

        fun playRoundConsciously(choiceOpponent: String, desiredOutcome: String): Int {
            val opponent = mapChoice(choiceOpponent)
            val outcome = mapDesiredOutcome(desiredOutcome)
            val player = acquirePlayerChoice(opponent, outcome)
            return player.number() + player.fight(opponent).score
        }

        private fun mapChoice(inputOpponent: String): Element {
            return when (inputOpponent) {
                "A", "X" -> Rock()
                "B", "Y" -> Paper()
                "C", "Z" -> Scissors()
                else -> throw Exception()
            }
        }

        private fun mapDesiredOutcome(input: String): Result {
            return when (input) {
                "X" -> LOSE
                "Y" -> DRAW
                "Z" -> WIN
                else -> throw Exception()
            }
        }

        private fun acquirePlayerChoice(opponent: Element, outcome: Result): Element = when (outcome) {
            WIN -> opponent.losesFrom()
            DRAW -> opponent
            LOSE -> opponent.winsFrom()
        }
    }
}

