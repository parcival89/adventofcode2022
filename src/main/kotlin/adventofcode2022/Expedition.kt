package adventofcode2022

class Expedition(val elves: List<Elf>) {

    fun findElvesWithMostFood(amount: Int): List<Elf> {
        return elves
            .sortedByDescending { elf -> elf.calories }
            .take(amount)
    }
}
