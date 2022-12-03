package adventofcode2022.elf

import adventofcode2022.domain.Elf
import adventofcode2022.domain.ElfRepository
import adventofcode2022.domain.Rucksack
import java.io.File

class ElfRepositoryImpl : ElfRepository {
    override fun loadAllCalories(): List<Elf> {
        val elves = mutableListOf<Elf>()
        val calorieValues = mutableListOf<Int>()
        readInput("day1").forEach {
            if (it.isNotEmpty()) {
                calorieValues.add(it.toInt())
            } else {
                elves.add(Elf(calorieValues))
                calorieValues.clear()
            }
        }
        return elves
    }

    override fun loadAllRucksacks(): List<Rucksack> {
        return readInput("day3")
            .map { it.subSequence(0, it.length / 2) to it.subSequence(it.length / 2, it.length) }
            .map { it -> Rucksack(listOf(it.first, it.second)) }
    }

    private fun readInput(filename: String) = File("${resourcesDir}/${filename}.txt").readLines()

    companion object {
        private const val resourcesDir = "/Users/sander/projects/kunlabora/adventofcode2022/src/test/resources"
    }
}
