package adventofcode2022.domain

interface Element {
    fun number(): Int
    fun fight(other: Element): Result
    fun winsFrom(): Element
    fun losesFrom(): Element

    enum class Result(val score: Int) {
        WIN(6),
        DRAW(3),
        LOSE(0)
    }
}

