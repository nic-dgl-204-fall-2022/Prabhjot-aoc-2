import java.lang.Long
import java.util.regex.Pattern


class Day11 : Data<String?> {

    private fun threeLetters(word: String?): Boolean {
        return Pattern.matches(".*(abc|bcd|cde|def|efg|fgh|pqr|qrs|rst|stu|tuv|uvw|vwx|wxy|xyz).*",word)
    }

     fun mistakenLetters(word: String?): Boolean {
        return !Pattern.matches(".*[iol]",word)
    }

     fun overlappingPairs(word: String?): Boolean {
        return Pattern.matches(".*(.)\\1.*(.)\\2.*",word)
    }

     fun isCorrect(word: String?): Boolean {
        return threeLetters(word) && mistakenLetters(word) && overlappingPairs(word)
    }

     fun addition(word: String?): String {
        return Long.toString(word!!.toLong(36) + 1, 36).replace('0', 'a')
    }

    override fun part1(dataInput: List<String?>?): String {
        var passCode = addition(dataInput!![0])
        while (!isCorrect(passCode)) {
            passCode = addition(passCode)
        }
        return passCode
    }

    override fun part2(dataInput: List<String?>?): String {
        var passCode = addition(dataInput!![0])
        while (!isCorrect(passCode)) {
            passCode = addition(passCode)
        }
        passCode = addition(passCode)
        while (!isCorrect(passCode)) {
            passCode = addition(passCode)
        }
        return passCode
    }
}

interface Data<T> {
    fun part1(dataInput: List<String?>?): T
    fun part2(dataInput: List<String?>?): T
}
fun main(){
    Day1()
}
