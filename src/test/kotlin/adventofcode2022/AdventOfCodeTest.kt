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

    @Test
    fun `Day4 - Count cleaning overlap`() {
        val overlappingAssignments = readFile("day4.txt")
            .map { it.split(",") }
            .count(::checkOverlap)
        println(overlappingAssignments)
    }

    @Test
    fun `Day4 - Count cleaning partial overlap`() {
        val overlappingAssignments = readFile("day4.txt")
            .map { it.split(",") }
            .count(::checkAnyOverlap)

        println(overlappingAssignments)
    }

    @Test
    fun `Day5 - rearranging`() {
        val input = readFile("day5.txt")
        val grid = mutableMapOf(
            1 to listOf<String>(),
            2 to listOf(),
            3 to listOf(),
            4 to listOf(),
            5 to listOf(),
            6 to listOf(),
            7 to listOf(),
            8 to listOf(),
            9 to listOf()
        )

        fillGrid(input, grid)
        println(grid)
        executeCommands(input, grid, false)

        println(grid)
        println(grid.map { it.value.first() })
    }

    @Test
    fun `Day5 - rearrange multiple crates at once`() {
        val input = readFile("day5.txt")
        val grid = mutableMapOf(
            1 to listOf<String>(),
            2 to listOf(),
            3 to listOf(),
            4 to listOf(),
            5 to listOf(),
            6 to listOf(),
            7 to listOf(),
            8 to listOf(),
            9 to listOf()
        )

        fillGrid(input, grid)
        println(grid)
        executeCommands(input, grid, true)

        println(grid)
        println(grid.map { it.value.firstOrNull() })
    }

    @Test
    fun `Day6 - detect message start`() {
        val input = readFile("day6.txt").first()
        val messageStart = findMessageStart(input)
        println(messageStart)
    }

    @Test
    fun `Day6 - detect message`() {
        val input = readFile("day6.txt").first()
        val messageStart = findMessage(input)
        println(messageStart)
    }

    private fun findMessageStart(input: String): Int{
        return allDifferentCharacters(input, 0, 4)
    }

    private fun findMessage(input: String): Int{
        return allDifferentCharacters(input, 0, 14)
    }

    private fun allDifferentCharacters(input: String, startAt: Int, length: Int) : Int{
        if(input.subSequence(startAt, startAt + length).toSet().size == length){
            return startAt + length
        }else return allDifferentCharacters(input, startAt + 1, length)
    }

    private fun executeCommands(input: List<String>, grid: MutableMap<Int, List<String>>, multipleCrates: Boolean) {
        input
            .filter { it.isNotBlank() && it.startsWith("move") }
            .forEach { command -> execute(command, grid, multipleCrates) }
    }

    private fun execute(command: String, grid: MutableMap<Int, List<String>>, multipleCrates: Boolean) {
        // move 8 from 8 to 5
        val split = command.split(" ")
        val numberOfCrates = split[1].toInt()
        val from = split[3].toInt()
        val to = split[5].toInt()
        println(command)

        if (multipleCrates) {
            val crates = grid[from]!!.subList(0, numberOfCrates)
            grid[from] = grid[from]!!.subList(numberOfCrates, grid[from]!!.size)
            grid[to] = crates + grid[to]!!
        } else {
            (1..numberOfCrates)
                .forEach {
                    val crate = grid[from]!!.first()
                    grid[from] = grid[from]!! - crate
                    grid[to] = listOf(crate) + grid[to]!!
                }
        }
        print(grid)
        println()
    }

    private fun fillGrid(input: List<String>, grid: MutableMap<Int, List<String>>) {
        input.takeWhile { it.isNotEmpty() && it.contains("[") }
            .forEach { value ->
                intArrayOf(1, 5, 9, 13, 17, 21, 25, 29, 33).mapIndexed { index, it ->
                    if (value.length > it && value[it].isLetter()) {
                        val crateValue = value[it].toString()
                        grid[index + 1] = (grid[index + 1]!!.plus(crateValue)).toList()
                    }
                }
            }
    }

    private fun checkOverlap(sections: List<String>): Boolean {
        val first = splitBoundaries(sections.first())
        val last = splitBoundaries(sections.last())
        return overlapsCompletely(first, last) || overlapsCompletely(last, first)
    }

    private fun checkAnyOverlap(sections: List<String>): Boolean {
        val first = splitBoundaries(sections.first())
        val last = splitBoundaries(sections.last())
        return !(hasNoOverlap(first, last) && hasNoOverlap(last, first))
    }

    private fun overlapsCompletely(overlapper: Pair<Int, Int>, overlappee: Pair<Int, Int>): Boolean {
        return overlappee.first >= overlapper.first && overlappee.second <= overlapper.second
    }

    private fun splitBoundaries(sections: String): Pair<Int, Int> {
        val boundaries = sections.split("-").map { it.toInt() }
        return boundaries.first() to boundaries.last()
    }

    // 29-29, 31-80
    private fun hasNoOverlap(first: Pair<Int, Int>, second: Pair<Int, Int>): Boolean {
        return (first.second < second.first && first.second < second.second) ||
                (first.first > second.first && first.first > second.second)
    }

    fun readFile(fileName: String): List<String> =
        {}::class.java.classLoader.getResourceAsStream(fileName)?.reader()?.readLines()
            ?: error("Could not load $fileName")
}
