package adventofcode2022.domain

class Expedition(private val elves: List<Elf>) {

    fun findElvesWithMostFood(amount: Int): List<Elf> {
        return elves
            .sortedByDescending(Elf::countedCalories)
            .take(amount)
    }
}
