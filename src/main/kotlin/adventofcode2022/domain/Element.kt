package adventofcode2022.domain

import adventofcode2022.domain.Element.Result.*
import adventofcode2022.domain.Element.Type.*

interface Element {
    fun number(): Int
    fun type(): Type
    fun fight(other: Element): Result

    enum class Result(val score: Int) {
        WIN(6),
        DRAW(3),
        LOSE(0)
    }

    enum class Type {
        ROCK,
        PAPER,
        SCISSORS
    }
}

class Rock : Element {
    override fun number(): Int = 1
    override fun type(): Element.Type = ROCK

    override fun fight(other: Element): Element.Result = when (other.type()) {
        ROCK -> DRAW
        PAPER -> LOSE
        SCISSORS -> WIN
    }
}

class Paper : Element {
    override fun number(): Int = 2
    override fun type(): Element.Type = PAPER

    override fun fight(other: Element): Element.Result = when (other.type()) {
        ROCK -> WIN
        PAPER -> DRAW
        SCISSORS -> LOSE
    }
}

class Scissors : Element {
    override fun number(): Int = 3
    override fun type(): Element.Type = SCISSORS

    override fun fight(other: Element): Element.Result = when (other.type()) {
        ROCK -> LOSE
        PAPER -> WIN
        SCISSORS -> DRAW
    }
}

