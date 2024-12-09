import java.io.File
import java.io.InputStream

private fun swap(disk: MutableList<Pair<Int, Int>>, first: Int, last: Int): MutableList<Pair<Int, Int>> {
    if (disk[last].second == disk[first].second) {
        disk[first] = disk[last]
        disk.removeAt(last)
    } else if (disk[last].second < disk[first].second) {
        val diff = disk[first].second - disk[last].second
        disk[first] = disk[last]
        disk.removeAt(last)
        disk.add(first + 1, Pair(-1, diff))
    } else {
        val diff = disk[last].second - disk[first].second
        disk[first] = Pair(disk[last].first, disk[first].second)
        disk[last] = Pair(disk[last].first, diff)
    }
    return disk
}

private fun swapWhole(disk: MutableList<Pair<Int, Int>>, change: Int): MutableList<Pair<Int, Int>> {
    val where = disk.indexOfFirst { e -> e.first == -1 && e.second >= disk[change].second }
    if (where == -1 || where >= change)
        return disk
    if (disk[change].second == disk[where].second) {
        disk[where] = disk[change]
        disk[change] = Pair(-1, disk[change].second)
    } else if (disk[change].second < disk[where].second) {
        val diff = disk[where].second - disk[change].second
        disk[where] = disk[change]
        disk[change] = Pair(-1, disk[change].second)
        disk.add(where + 1, Pair(-1, diff))
    }
    return disk
}

private fun part1(path: String) {
    val inputStream: InputStream = File(path).inputStream()
    val intArray = inputStream.bufferedReader().use { it.readText().toMutableList().map { e -> e.toString().toInt() } }
    var disk = mutableListOf<Pair<Int, Int>>()
    for (i in 0..intArray.lastIndex) {
        if (i % 2 == 0)
            disk.add(Pair(i/2, intArray[i]))
        else
            disk.add(Pair(-1, intArray[i]))
    }
    while (disk.indexOfFirst { e -> e.first == -1 } < disk.indexOfLast { e -> e.first != -1 }) {
        disk = swap(disk, disk.indexOfFirst { e -> e.first == -1 }, disk.indexOfLast { e -> e.first != -1 })
    }

    var i = 0
    var sum: Long = 0
    for (pair in disk.filter { e -> e.first > -1 }){
        for (num in 0..<pair.second) {
            sum += i * pair.first
            i++
        }
    }
    println(sum)
}

private fun part2(path: String) {
    val inputStream: InputStream = File(path).inputStream()
    val intArray = inputStream.bufferedReader().use { it.readText().toMutableList().map { e -> e.toString().toInt() } }
    var disk = mutableListOf<Pair<Int, Int>>()
    for (i in 0..intArray.lastIndex) {
        if (i % 2 == 0)
            disk.add(Pair(i/2, intArray[i]))
        else
            disk.add(Pair(-1, intArray[i]))
    }
    var index = disk.last().first
    while (index > -1) {
        disk = swapWhole(disk, disk.indexOfFirst { e -> e.first == index })
        index--
    }

    var i = 0
    var sum: Long = 0
    for (pair in disk){
        for (num in 0..<pair.second) {
            if (pair.first > -1)
                sum += i * pair.first
            i++
        }
    }
    println(sum)
}

fun main() {
    val path = "C:\\Users\\tviki\\Desktop\\VS\\AOC\\example.txt"
    part1(path)
    part2(path)
}