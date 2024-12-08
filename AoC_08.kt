import java.io.File
import java.io.InputStream

private fun position(first: List<Int>, second: List<Int>): List<Int> {
    return listOf(second[0] - (first[0] - second[0]), second[1] - (first[1] - second[1]))
}

private fun positions(first: List<Int>, second: List<Int>, size: Int): Collection<List<Int>> {
    val result = mutableSetOf<List<Int>>()
    var i = 0
    var x = second[0] - i * (first[0] - second[0])
    var y = second[1] - i * (first[1] - second[1])
    while (x in 0..<size && y in 0..<size) {
        result.add(listOf(x, y))
        i++
        x = second[0] - i * (first[0] - second[0])
        y = second[1] - i * (first[1] - second[1])
    }
    return result
}

private fun part1(path: String) {
    val inputStream: InputStream = File(path).inputStream()
    val stringArray = inputStream.bufferedReader().use { it.readText().split("\r\n") }.map { e->e.toList() }

    val positions = mutableMapOf<Char, MutableList<List<Int>>>()
    for (i in 0..stringArray.lastIndex) {
        for (j in 0..stringArray.lastIndex) {
            if (stringArray[i][j] != '.'){
                if (positions.containsKey(stringArray[i][j]))
                    positions[stringArray[i][j]]!!.add(listOf(i, j))
                else
                    positions[stringArray[i][j]] = mutableListOf(listOf(i, j))
            }
        }
    }
    val antennas = mutableSetOf<List<Int>>()
    for (i in positions.keys) {
        val same = positions[i]!!
        for (first in same) {
            for (second in same) {
                if (first != second) {
                    antennas.add(position(first, second))
                    antennas.add(position(second, first))
                }
            }
        }
    }
    val valid = antennas.filter { e -> e[0] >= 0 && e[0] < stringArray.size && e[1] >= 0 && e[1] < stringArray.size }
    println(valid.size)
}

private fun part2(path: String) {
    val inputStream: InputStream = File(path).inputStream()
    val stringArray = inputStream.bufferedReader().use { it.readText().split("\r\n") }.map { e->e.toList() }

    val positions = mutableMapOf<Char, MutableList<List<Int>>>()
    for (i in 0..stringArray.lastIndex) {
        for (j in 0..stringArray.lastIndex) {
            if (stringArray[i][j] != '.'){
                if (positions.containsKey(stringArray[i][j]))
                    positions[stringArray[i][j]]!!.add(listOf(i, j))
                else
                    positions[stringArray[i][j]] = mutableListOf(listOf(i, j))
            }
        }
    }
    val antennas = mutableSetOf<List<Int>>()
    for (i in positions.keys) {
        val same = positions[i]!!
        for (first in same) {
            for (second in same) {
                if (first != second) {
                    antennas.addAll(positions(first, second, stringArray.size))
                    antennas.addAll(positions(second, first, stringArray.size))
                }
            }
        }
    }
    println(antennas.size)
}

fun main() {
    val path = "C:\\Users\\tviki\\Desktop\\VS\\AOC\\example.txt"
    part1(path)
    part2(path)
}