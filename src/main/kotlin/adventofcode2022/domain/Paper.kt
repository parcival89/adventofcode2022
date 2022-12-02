package adventofcode2022.domain

import adventofcode2022.domain.Element.Result.*

data class Paper(private val number: Int = 2) : Element {
    override fun number(): Int = number
    override fun winsFrom(): Element = Rock()
    override fun losesFrom(): Element = Scissors()

    override fun fight(other: Element): Element.Result = when (other) {
        winsFrom() -> WIN
        losesFrom() -> LOSE
        else -> DRAW
    }
}
