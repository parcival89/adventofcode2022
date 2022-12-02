package adventofcode2022.domain

import adventofcode2022.domain.Element.Result
import adventofcode2022.domain.Element.Result.*

data class Rock(private val number: Int = 1) : Element {
    override fun number(): Int = number
    override fun winsFrom(): Element = Scissors()
    override fun losesFrom(): Element = Paper()
    override fun fight(other: Element): Result = when (other) {
        winsFrom() -> WIN
        losesFrom() -> LOSE
        else -> DRAW
    }
}
