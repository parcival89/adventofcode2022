package adventofcode2022
class Elf(val calories: Int) {
    companion object {
        fun from(calorieValues: MutableList<Int>): Elf {
            return Elf(calorieValues.reduce(Int::plus))
        }
    }
}
