package adventofcode2022

import java.io.File
import kotlin.test.Test

private const val resourcesDir = "/Users/sander/projects/kunlabora/adventofcode2022/src/test/resources"

class AdventOfCodeTest {
    @Test
    fun `Day 1 - Calorie maxxer`() {
        val input: List<String> = readInput("day1")
        val expedition = createExpedition(input)
        val foodMaster = expedition
            .findElvesWithMostFood(1)
            .map(Elf::calories)
            .reduce(Int::plus)
        println(foodMaster)
    }

    @Test
    fun `Day 1 - Calorie triarch`() {
        val input = readInput("day1")
        val expedition = createExpedition(input)
        val triarch = expedition
            .findElvesWithMostFood(3)
            .map(Elf::calories)
            .reduce(Int::plus)
        println(triarch)
    }

    private fun createExpedition(input: List<String>): Expedition {
        val calorieValues = mutableListOf<Int>()
        val elves = mutableListOf<Elf>()
        input.forEach {
            if (it.isNotEmpty()) {
                calorieValues.add(it.toInt())
            } else {
                elves.add(Elf.from(calorieValues))
                calorieValues.clear()
            }
        }
        return Expedition(elves)
    }

    private fun readInput(filename: String) = File("${resourcesDir}/${filename}.txt").readLines()
}
