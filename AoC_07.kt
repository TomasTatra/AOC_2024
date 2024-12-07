import java.io.File
import java.io.InputStream

private fun isValid(product: Long, actual: Long, numbers: List<Long>): Boolean {
    if (product == actual && numbers.isEmpty())
        return true
    if (product < actual || numbers.isEmpty())
        return false
    if (isValid(product, actual + numbers[0], numbers.subList(1, numbers.size)))
        return true
    if (isValid(product, actual * numbers[0], numbers.subList(1, numbers.size)))
        return true
    return false
}

private fun isValid2(product: Long, actual: Long, numbers: List<Long>): Boolean {
    if (product == actual && numbers.isEmpty())
        return true
    if (product < actual || numbers.isEmpty())
        return false
    if (isValid2(product, actual + numbers[0], numbers.subList(1, numbers.size)))
        return true
    if (isValid2(product, actual * numbers[0], numbers.subList(1, numbers.size)))
        return true
    if (isValid2(product, concat(actual, numbers[0]), numbers.subList(1, numbers.size)))
        return true
    return false
}

private fun concat(actual: Long, numbers: Long): Long {
    return (actual.toString()+numbers.toString()).toLong()
}

private fun part1(path: String) {
    val inputStream: InputStream = File(path).inputStream()
    val stringArray = inputStream.bufferedReader().use { it.readText().split("\r\n") }.map { e->e.split(": ") }
    val products = stringArray.map { e -> e[0].toLong() }
    val operants = stringArray.map { e -> e[1].split(' ').map { j-> j.toLong() } }

    var sum: Long = 0
    for (i in 0 .. products.lastIndex) {
        if (isValid(products[i], 0, operants[i]))
            sum += products[i]
    }

    println(sum)
}

private fun part2(path: String) {
    val inputStream: InputStream = File(path).inputStream()
    val stringArray = inputStream.bufferedReader().use { it.readText().split("\r\n") }.map { e->e.split(": ") }
    val products = stringArray.map { e -> e[0].toLong() }
    val operants = stringArray.map { e -> e[1].split(' ').map { j-> j.toLong() } }

    var sum: Long = 0
    for (i in 0 .. products.lastIndex) {
        if (isValid2(products[i], 0, operants[i]))
            sum += products[i]
    }

    println(sum)
}

fun main() {
    val path = "C:\\Users\\tviki\\Desktop\\VS\\AOC\\example.txt"
    part1(path)
    part2(path)
}