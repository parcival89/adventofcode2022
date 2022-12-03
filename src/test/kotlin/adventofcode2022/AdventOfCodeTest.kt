package adventofcode2022

import adventofcode2022.domain.Elf
import adventofcode2022.domain.Expedition
import adventofcode2022.domain.RockPaperScissorsSimulator
import adventofcode2022.elf.ElfRepositoryImpl
import kotlin.test.Test

class AdventOfCodeTest {
    @Test
    fun `Day 1 - Calorie maxxer`() {
        val foodMaster = createExpedition()
            .findElvesWithMostFood(1)
            .map(Elf::countedCalories)
            .reduce(Int::plus)
        println(foodMaster)
    }

    @Test
    fun `Day 1 - Calorie triarch`() {
        val triarch = createExpedition()
            .findElvesWithMostFood(3)
            .map(Elf::countedCalories)
            .reduce(Int::plus)
        println(triarch)
    }

    private fun createExpedition(): Expedition {
        return Expedition(ElfRepositoryImpl().findAll())
    }

    @Test
    fun `Day 2 - Rock Paper Scissors - Follow the strategy guide by winning it all`() {
        val score = readFile("day2.txt")
            .map {
                val splittedLine = it.split(" ")
                splittedLine[0] to splittedLine[1]
            }
            .map { RockPaperScissorsSimulator.playRound(it.first, it.second) }
            .sum()
        println(score)
    }

    @Test
    fun `Day 2 - Rock Paper Scissors - Follow the strategy guide consciously`() {
        val score = readFile("day2.txt")
            .map {
                val splittedLine = it.split(" ")
                splittedLine[0] to splittedLine[1]
            }
            .map { RockPaperScissorsSimulator.playRoundConsciously(it.first, it.second) }
            .sum()
        println(score)
    }

    @Test
    fun `Day 3 - Rucksack prioritizing`() {
        val sum = readFile("day3.txt")
            .map { it.subSequence(0, it.length / 2) to it.subSequence(it.length / 2, it.length) }
            .map { rucksack -> rucksack.first.filter { rucksack.second.contains(it) }.first() }
            .map { "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(it) + 1 }
            .sum()
        println(sum)
    }

    @Test
    fun `Day 3 - Badge counting`() {
        val groups = mutableListOf<List<String>>()
        var group = mutableListOf<String>()
        readFile("day3.txt")
            .forEach {
                group.add(it)
                if (group.size == 3) {
                    groups.add(group)
                    group = mutableListOf()
                }
            }

        val sum = groups
            .map { items -> items[0].filter { item -> item in items[1] && item in items[2] }.first() }
            .map { "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(it) + 1 }
            .sum()
        println(sum)
    }

    fun readFile(fileName: String): List<String> =
        {}::class.java.classLoader.getResourceAsStream(fileName)?.reader()?.readLines()
            ?: error("Could not load $fileName")
}
