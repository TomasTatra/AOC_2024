import java.io.File
import java.io.InputStream

private var order = mutableMapOf<Int, MutableList<Int>>()
private var brokenLines = mutableListOf<MutableList<Int>>()

private fun wrongOrder(num: Int, list: List<Int>): Int {
    for (later in list){
        if (order[num]?.contains(later) == true)
            return list.indexOf(later)
    }
    return -1
}

private fun fixOrder(row: MutableList<Int>): MutableList<Int> {
    for (index in 0..<row.lastIndex){
        val replace = wrongOrder(row[index], row.subList(index, row.size))
        if (replace > -1) {
            val swap = row[index]
            row[index] = row[replace + index]
            row[replace + index] = swap
        }
    }
    return row
}

private fun part1(path: String) {
    val inputStream: InputStream = File(path).inputStream()
    val inputArray = inputStream.bufferedReader().use { it.readText().split("\r\n\r\n") }
    val orderList = inputArray[0].split("\r\n").map { e -> e.split('|').map { j -> j.toInt() } }
    val queue = inputArray[1].split("\r\n").map { e -> e.split(',').map { j -> j.toInt() }.toMutableList() }

    orderList.map { e -> if (e[1] in order) order[e[1]]!!.add(e[0]) else order.put(e[1], mutableListOf(e[0]))}

    var sum = 0
    var count: Boolean
    for (row in queue){
        count = true
        for (index in 0..row.lastIndex){
            val number = row[index]
            if (wrongOrder(number, row.subList(index, row.size)) > -1){
                count = false
                brokenLines.add(row)
                break
            }
        }
        if (count)
            sum += row[row.size/2]
    }
    println(sum)
}

private fun part2() {
    var sum = 0
    for (row in brokenLines){
        var newRow = fixOrder(row)
        var check = newRow
        while (check.map { e -> wrongOrder(e, newRow.subList(newRow.indexOf(e), newRow.size)) }.any { e -> e > -1 }){
            newRow = fixOrder(newRow)
            check = newRow
        }
        sum += newRow[row.size/2]
    }
    println(sum) //6228
}

fun main() {
    val path = "C:\\Users\\tviki\\Desktop\\VS\\AOC\\example.txt"
    part1(path)
    part2()
}