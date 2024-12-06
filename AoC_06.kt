import java.io.File
import java.io.InputStream

private val up = listOf(-1, 0)
private val right = listOf(0, 1)
private val down = listOf(1, 0)
private val left = listOf(0, -1)
private val movement = mutableListOf(up, right, down, left)

private fun findCycle(pos: List<Int>, map: MutableList<MutableList<Char>>): Boolean {
    val set = mutableSetOf<List<Int>>()
    var position = pos
    var direction = 0
    try {
        while (true) {
            val newPosition = listOf(position[0] + movement[direction][0], position[1] + movement[direction][1])
            if (map[newPosition[0]][newPosition[1]] == '#') {
                direction = (direction + 1) % 4
                continue
            }
            if (listOf(position[0], position[1], direction) in set)
                return true
            set.add(listOf(position[0], position[1], direction))
            position = newPosition
        }
    } catch (e: IndexOutOfBoundsException) {
        return false
    }
}

private fun part1(path: String) {
    val inputStream: InputStream = File(path).inputStream()
    val stringArray =
        inputStream.bufferedReader().use { it.readText().split("\r\n") }.map { e -> e.toMutableList() }.toMutableList()

    var position = listOf(-1)
    var direction = 0
    for (i in 0..stringArray.lastIndex) {
        if (stringArray[i].contains('^')) {
            position = listOf(i, stringArray[i].indexOf('^'))
        }
    }

    try {
        while (true) {
            val newPosition = listOf(position[0] + movement[direction][0], position[1] + movement[direction][1])
            if (stringArray[newPosition[0]][newPosition[1]] == '#') {
                direction = (direction + 1) % 4
                continue
            } else if (stringArray[newPosition[0]][newPosition[1]] == '.') {
                stringArray[newPosition[0]][newPosition[1]] = 'X'
            }
            position = newPosition
        }
    } catch (e: IndexOutOfBoundsException) {
    }


    val sum = stringArray.sumOf { e -> e.count { j -> j == 'X' } }
    println(sum + 1)  // starting position
}

private fun part2(path: String) {
    val inputStream: InputStream = File(path).inputStream()
    val stringArray =
        inputStream.bufferedReader().use { it.readText().split("\r\n") }.map { e -> e.toMutableList() }.toMutableList()

    var firstPosition = listOf(-1)
    for (i in 0..stringArray.lastIndex) {
        if (stringArray[i].contains('^')) {
            firstPosition = listOf(i, stringArray[i].indexOf('^'))
        }
    }
    var sum = 0

    for (i in 0..stringArray.lastIndex) {
        for (j in 0..stringArray.lastIndex) {
            if (stringArray[i][j] == '#')
                continue
            val map = stringArray.map { e -> e.map { j -> j }.toMutableList() }.toMutableList()
            map[i][j] = '#'
            if (findCycle(firstPosition, map))
                sum++
        }
    }
    println(sum)
}

fun main() {
    val path = "C:\\Users\\tviki\\Desktop\\VS\\AOC\\example.txt"
    part1(path)
    part2(path)
}