import java.io.File
import java.io.InputStream

val regex1 = "mul\\([0-9]{1,3},[0-9]{1,3}\\)".toRegex()
val regex2 = "mul\\([0-9]{1,3},[0-9]{1,3}\\)|do\\(\\)|don't\\(\\)".toRegex()

private fun part1(path: String) {
    val inputStream: InputStream = File(path).inputStream()
    val string = inputStream.bufferedReader().use { it.readText() }

    val matches = regex1.findAll(string)
    val numbers = matches.map { it.groupValues[0] }.joinToString().split(", ")
        .map { e -> e.removePrefix("mul(").removeSuffix(")").split(',').map { j -> j.toInt() } }

    var sum = 0
    numbers.map { e -> sum += e[0] * e[1] }
    println(sum)
}

private fun part2(path: String) {
    val inputStream: InputStream = File(path).inputStream()
    val string = inputStream.bufferedReader().use { it.readText() }

    val matches = regex2.findAll(string)
    val strings = matches.map { it.groupValues[0] }.joinToString().split(", ")
        .map { e -> e.removePrefix("mul(").removeSuffix(")").split(',').map { j -> j } }

    var sum = 0
    var counting = true
    for (i in strings) {
        if (i[0] == "do(")
            counting = true
        else if (i[0] == "don't(")
            counting = false
        else if (counting)
            sum += i[0].toInt() * i[1].toInt()
    }
    println(sum)
}

fun main() {
    val path = "C:\\Users\\tviki\\Desktop\\VS\\AOC\\example.txt"
    part1(path)
    part2(path)
}