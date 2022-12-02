package adventofcode2022.domain

interface ElfRepository {
    fun findAll() : List<Elf>
}
