package adventofcode2022

import java.io.File
import kotlin.test.Test

private const val resourceDir = "/Users/sander/projects/kunlabora/adventofcode2022/app/src/test/resources"

class AdventOfCodeTest {
    @Test
    fun `Day 1 - Calorie maxxer`() {
        val input: List<String> = readInput("day1")
        val caloricElves = calculateCaloriesPerElf(input)
        println(caloricElves.max())
    }

    @Test
    fun `Day 1 - Calorie triarch`() {
        val input = readInput("day1")
        val caloricElves = calculateCaloriesPerElf(input)
        val triarch = caloricElves
            .sortedDescending()
            .take(3)
            .reduce(Int::plus)
        println(triarch)
    }

    private fun calculateCaloriesPerElf(input: List<String>): MutableList<Int> {
        var calories = 0
        val caloricElves = mutableListOf<Int>()
        input.forEach {
            if (it.isEmpty()) {
                caloricElves.add(calories)
                calories = 0
            } else calories += it.toInt()
        }
        return caloricElves
    }

    private fun readInput(filename: String) = File("${resourceDir}/${filename}.txt").readLines()
}
