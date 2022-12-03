package adventofcode2022.domain

class BadgeGroup(private val rucksacks: List<Rucksack>) {

    fun findBadge(): ItemType {
        return rucksacks
            .map { it.items() }
            .first()
            .filter { item -> rucksacks.all { rucksack -> rucksack.containsBadge(item) } }
            .map { ItemType(it) }
            .first()
    }
}
