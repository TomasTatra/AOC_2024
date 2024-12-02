import java.io.File
import java.io.InputStream
import kotlin.math.abs

fun part1(path: String) {
    val inputStream: InputStream = File(path).inputStream()
    val stringArray = inputStream.bufferedReader().use { it.readText().split("\r\n") }.map { e->e.split(' ') }
    val leftArray = stringArray.map { e -> e[0].toInt() }.toMutableList()
    val rightArray = stringArray.map { e -> e[e.lastIndex].toInt() }.toMutableList()
    var sum = 0

    val leftNumbers = leftArray.sorted()
    val rightNumbers = rightArray.sorted()

    for (i in 0 ..< leftArray.size) {
        val num1 = leftNumbers[i]
        val pos1 = leftArray.indexOf(num1)
        leftArray[pos1] = 0
        val num2 = rightNumbers[i]
        val pos2 = rightArray.indexOf(num2)
        rightArray[pos2] = 0
        sum += abs(pos1 - pos2)
    }

    println(sum)
}

fun part2(path: String) {
    val inputStream: InputStream = File(path).inputStream()
    val stringArray = inputStream.bufferedReader().use { it.readText().split("\r\n") }.map { e->e.split(' ') }
    val leftArray = stringArray.map { e -> e[0].toInt() }.toMutableList()
    val rightArray = stringArray.map { e -> e[e.lastIndex].toInt() }.toMutableList()
    var sum = 0

    val leftNumbers = leftArray.sorted()
    val rightNumbers = rightArray.sorted()

    for (i in 0 ..< leftArray.size) {
        val num1 = leftNumbers[i]
        val number = rightNumbers.count { e -> e == num1 };
        sum += num1 * number
    }

    println(sum)
}

fun main() {
    val path = "C:\\Users\\tviki\\Desktop\\VS\\AOC\\example.txt"
    part1(path)
    part2(path)
}