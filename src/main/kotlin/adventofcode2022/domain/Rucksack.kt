package adventofcode2022.domain

data class Rucksack(val compartments: List<CharSequence>) {

    fun findMatchingItem(): ItemType {
        return compartments
            .first()
            .filter { compartments.all { compartment -> compartment.contains(it) } }
            .map { ItemType(it) }
            .first()
    }

    fun containsBadge(badge: Char): Boolean {
        return compartments.any { it.contains(badge) }
    }

    fun items(): CharSequence {
        return compartments.joinToString()
    }
}
