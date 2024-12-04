import java.io.File
import java.io.InputStream

private val word = "XMAS".toRegex()

private fun part1(path: String) {
    val inputStream: InputStream = File(path).inputStream()
    val stringArray = inputStream.bufferedReader().use { it.readText().split("\r\n") }.map { e -> e.toList() }

    val x = stringArray.map { e -> e.joinToString("") }.toMutableList()
    x.addAll(stringArray.map { e -> e.joinToString("").reversed() }.toMutableList())

    for (i in stringArray.indices) {
        var string = ""
        for (element in stringArray)
            string += element[i]
        x.add(string)
        x.add(string.reversed())
    }

    for (i in -(stringArray.size + 1) .. stringArray.lastIndex) {
        var string = ""
        for (j in stringArray.indices)
            if (i + j < stringArray.size && i + j >= 0)
                string += stringArray[j][i+j]
        x.add(string)
        x.add(string.reversed())
    }

    for (i in -(stringArray.size + 1) .. stringArray.lastIndex) {
        var string = ""
        for (j in stringArray.indices)
            if (stringArray.lastIndex - i - j < stringArray.size && stringArray.lastIndex - i - j >= 0)
                string += stringArray[j][stringArray.lastIndex - i - j]
        x.add(string)
        x.add(string.reversed())
    }

    var sum = 0
    x.map { e -> sum += word.findAll(e).count() }
    println(sum)
}

private fun part2(path: String) {
    val inputStream: InputStream = File(path).inputStream()
    val stringArray = inputStream.bufferedReader().use { it.readText().split("\r\n") }

    var sum = 0
    for (i in 1..<stringArray.lastIndex){
        for (j in 1..<stringArray.lastIndex){
            if (stringArray[i][j] == 'A'){
                val lu = stringArray[i-1][j-1]
                val ld = stringArray[i-1][j+1]
                val ru = stringArray[i+1][j-1]
                val rd = stringArray[i+1][j+1]
                if ((lu == ld || lu == ru) && (rd == ld || rd == ru) && lu in "MS" && rd in "MS" && lu!=rd)
                    sum++
            }
        }
    }
    println(sum)}

fun main() {
    val path = "C:\\Users\\tviki\\Desktop\\VS\\AOC\\example.txt"
    part1(path)
    part2(path)
}