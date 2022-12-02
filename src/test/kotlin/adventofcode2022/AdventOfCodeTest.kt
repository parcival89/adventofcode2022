package adventofcode2022

import adventofcode2022.domain.Elf
import adventofcode2022.domain.Expedition
import adventofcode2022.elf.ElfRepositoryImpl
import kotlin.test.Test

class AdventOfCodeTest {
    @Test
    fun `Day 1 - Calorie maxxer`() {
        val foodMaster = createExpedition()
            .findElvesWithMostFood(1)
            .map(Elf::calories)
            .reduce(Int::plus)
        println(foodMaster)
    }

    @Test
    fun `Day 1 - Calorie triarch`() {
        val triarch = createExpedition()
            .findElvesWithMostFood(3)
            .map(Elf::calories)
            .reduce(Int::plus)
        println(triarch)
    }

    private fun createExpedition(): Expedition {
        return Expedition(ElfRepositoryImpl().findAll())
    }
}
