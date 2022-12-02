package adventofcode2022.domain

import adventofcode2022.domain.Element.Result.*

data class Scissors(private val number: Int = 3) : Element {
    override fun number(): Int = number
    override fun winsFrom(): Element = Paper()
    override fun losesFrom(): Element = Rock()

    override fun fight(other: Element): Element.Result = when (other) {
        winsFrom() -> WIN
        losesFrom() -> LOSE
        else -> DRAW
    }

}
