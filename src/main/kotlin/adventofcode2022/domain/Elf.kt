package adventofcode2022.domain
class Elf(val calories: List<Int>) {
    fun countedCalories(): Int{
        return calories.sum()
    }
}
