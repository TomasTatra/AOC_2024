import java.io.File
import java.io.InputStream

private fun safe(list: List<Int>): Boolean {
    var input = list
    if (list[0] > list[list.lastIndex])
        input = list.reversed().toMutableList()
    for (i in 0..<input.lastIndex) {
        val dif = input[i] - input[i+1]
        if (-3 <= dif && dif <= -1)
            continue
        return false
    }
    return true
}

private fun removed(list: List<Int>): Boolean {
    for (i in 0..list.lastIndex) {
        val newList = list.toMutableList()
        newList.removeAt(i)
        if (safe(newList))
            return true
    }
    return false
}

private fun part1(path: String) {
    val inputStream: InputStream = File(path).inputStream()
    val stringArray = inputStream.bufferedReader().use { it.readText().split("\r\n") }.map { e->e.split(' ').map { e -> e.toInt() } }

    var sum = 0
    stringArray.map { e ->
        if (safe(e))
            sum += 1
    }
    println(sum)
}

private fun part2(path: String) {
    val inputStream: InputStream = File(path).inputStream()
    val stringArray = inputStream.bufferedReader().use { it.readText().split("\r\n") }.map { e->e.split(' ').map { e -> e.toInt() } }

    var sum = 0
    stringArray.map { e ->
        if (safe(e) || removed(e))
            sum += 1
    }
    println(sum)}

fun main() {
    val path = "C:\\Users\\tviki\\Desktop\\VS\\AOC\\example.txt"
    part1(path)
    part2(path)
}