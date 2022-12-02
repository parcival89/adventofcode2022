package adventofcode2022.elf

import adventofcode2022.domain.Elf
import adventofcode2022.domain.ElfRepository
import java.io.File

class ElfRepositoryImpl : ElfRepository {
    override fun findAll(): List<Elf> {
        val elves = mutableListOf<Elf>()
        val calorieValues = mutableListOf<Int>()
        readInput("day1").forEach {
            if (it.isNotEmpty()) {
                calorieValues.add(it.toInt())
            } else {
                elves.add(Elf.from(calorieValues))
                calorieValues.clear()
            }
        }
        return elves
    }

    private fun readInput(filename: String) = File("${Companion.resourcesDir}/${filename}.txt").readLines()

    companion object {
        private const val resourcesDir = "/Users/sander/projects/kunlabora/adventofcode2022/src/test/resources"
    }
}
