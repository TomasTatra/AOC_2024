import java.io.File
import java.io.InputStream

private fun blink(inLine: Map<Long, Long>): MutableMap<Long, Long> {
    val outLine = mutableMapOf<Long, Long>()
    for (i in inLine) {
        if (i.key == (0).toLong()) {
            if (outLine[1] == null)
                outLine[1] = i.value
            else
                outLine[1] = outLine[1]!! + i.value
        }
        else if (i.key.toString().length % 2 == 0) {
            val number = i.key.toString()
            val index1 = number.slice(IntRange(0, number.length / 2 - 1)).toLong()
            val index2 = number.slice(IntRange(number.length / 2, number.length - 1)).toLong()
            if (outLine[index1] == null)
                outLine[index1] = i.value
            else
                outLine[index1] = outLine[index1]!! + i.value
            if (outLine[index2] == null)
                outLine[index2] = i.value
            else
                outLine[index2] = outLine[index2]!! + i.value
        }
        else {
            if (outLine[i.key * 2024] == null)
                outLine[i.key * 2024] = i.value
            else
                outLine[i.key * 2024] = outLine[i.key * 2024]!! + i.value
        }
    }
    return outLine
}

private fun part1(path: String) {
    val inputStream: InputStream = File(path).inputStream()
    val line = inputStream.bufferedReader().use { it.readText().split(" ").map { e -> e.toLong() } }
    var dict = mutableMapOf<Long, Long>()
    line.map { e -> dict.put(e, line.count { j -> j == e }.toLong()) }

    for (i in 1..25) {
        dict = blink(dict)
    }
    println(dict.map { e -> e.value }.sum())
}


private fun part2(path: String) {
    val inputStream: InputStream = File(path).inputStream()
    val line = inputStream.bufferedReader().use { it.readText().split(" ").map { e -> e.toLong() } }
    var dict = mutableMapOf<Long, Long>()
    line.map { e -> dict.put(e, line.count { j -> j == e }.toLong()) }

    for (i in 1..75) {
        dict = blink(dict)
    }
    println(dict.map { e -> e.value.toULong() }.sum())
}

fun main() {
    val path = "C:\\Users\\tviki\\Desktop\\VS\\AOC\\example.txt"
    part1(path)
    part2(path)
}