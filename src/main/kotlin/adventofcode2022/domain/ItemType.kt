package adventofcode2022.domain

data class ItemType(private val identifier: Char) {

    fun priorityScore(): Int {
        return "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(identifier) + 1
    }
}
