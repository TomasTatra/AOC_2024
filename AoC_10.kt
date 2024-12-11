import java.io.File
import java.io.InputStream

private fun findTrails(position: Pair<Int, Int>, map: List<List<Int>>): MutableList<Pair<Int, Int>> {
    val ret = mutableListOf<Pair<Int, Int>>()
    val nextMove = mutableListOf(position)

    while (nextMove.isNotEmpty()) {
        val move = nextMove.removeAt(0)
        val number = map[move.first][move.second]
        if (number == 9) {
            ret.add(move)
            continue
        }
        if (move.first > 0 && number + 1 == map[move.first - 1][move.second])
            nextMove.add(Pair(move.first - 1, move.second))
        if (move.second > 0 && number + 1 == map[move.first][move.second - 1])
            nextMove.add(Pair(move.first, move.second - 1))
        if (move.first < map.lastIndex && number + 1 == map[move.first + 1][move.second])
            nextMove.add(Pair(move.first + 1, move.second))
        if (move.second < map.lastIndex && number + 1 == map[move.first][move.second + 1])
            nextMove.add(Pair(move.first, move.second + 1))
    }

    return ret
}

private fun part1(path: String) {
    val inputStream: InputStream = File(path).inputStream()
    val map = inputStream.bufferedReader().use { it.readText().split("\r\n").map { e -> e.toMutableList().map { j -> j.toString().toInt() } } }

    val zeros = mutableListOf<Pair<Int, Int>>()
    for (i in 0..map.lastIndex) {
        for (j in 0..map.lastIndex) {
            if (map[i][j] == 0)
                zeros.add(Pair(i, j))
        }
    }
    var sum: Long = 0
    sum += zeros.sumOf { e -> findTrails(e, map).toMutableSet().size }
    println(sum)
}


private fun part2(path: String) {
    val inputStream: InputStream = File(path).inputStream()
    val map = inputStream.bufferedReader().use { it.readText().split("\r\n").map { e -> e.toMutableList().map { j -> j.toString().toInt() } } }

    val zeros = mutableListOf<Pair<Int, Int>>()
    for (i in 0..map.lastIndex) {
        for (j in 0..map.lastIndex) {
            if (map[i][j] == 0)
                zeros.add(Pair(i, j))
        }
    }
    var sum: Long = 0
    sum += zeros.sumOf { e -> findTrails(e, map).size }
    println(sum)
}

fun main() {
    val path = "C:\\Users\\tviki\\Desktop\\VS\\AOC\\example.txt"
    part1(path)
    part2(path)
}
