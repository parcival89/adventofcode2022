package adventofcode2022.domain

interface ElfRepository {
    fun loadAllCalories() : List<Elf>
    fun loadAllRucksacks() : List<Rucksack>
}
