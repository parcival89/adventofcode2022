package adventofcode2022.domain

class RockPaperScissorsSimulator {
    companion object {
        fun playRound(choiceOpponent: String, choicePlayer: String): Int {
            val opponent = mapInput(choiceOpponent)
            val player = mapInput(choicePlayer)
            return player.number() + player.fight(opponent).score
        }
        
        private fun mapInput(inputOpponent: String): Element {
            return when (inputOpponent) {
                "A", "X" -> Rock()
                "B", "Y" -> Paper()
                "C", "Z" -> Scissors()
                else -> throw Exception()
            }
        }
    }
}

