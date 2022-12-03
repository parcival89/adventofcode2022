package adventofcode2022

import adventofcode2022.domain.*
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
        return Expedition(ElfRepositoryImpl().loadAllCalories())
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
        val newSum = ElfRepositoryImpl().loadAllRucksacks()
            .map(Rucksack::findMatchingItem)
            .map(ItemType::priorityScore)
            .sum()
        println(newSum)
    }

    @Test
    fun `Day 3 - Badge counting`() {
        val groups = mutableListOf<BadgeGroup>()
        var group = mutableListOf<Rucksack>()
        ElfRepositoryImpl().loadAllRucksacks()
            .forEach {
                group.add(it)
                if (group.size == 3) {
                    groups.add(BadgeGroup(group.toList()))
                    group = mutableListOf()
                }
            }

        val sum = groups
            .map(BadgeGroup::findBadge)
            .map(ItemType::priorityScore)
            .sum()
        println(sum)
    }

    fun readFile(fileName: String): List<String> =
        {}::class.java.classLoader.getResourceAsStream(fileName)?.reader()?.readLines()
            ?: error("Could not load $fileName")
}
