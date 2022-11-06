import java.lang.Long
import java.util.regex.Pattern

// AOC-2015, day-11

class Day11 : Data<String?> {

    // checking if the string includes include one increasing straight of at least three letters.
     fun threeLetters(word: String?): Boolean {
        return Pattern.matches(".*(abc|bcd|cde|def|efg|fgh|pqr|qrs|rst|stu|tuv|uvw|vwx|wxy|xyz).*",word)
    }

    // checking that string shouldn’t be containing the letters i, o, l.
     fun mistakenLetters(word: String?): Boolean {
        return !Pattern.matches(".*[iol]",word)
    }

    // checking that the string must contain at least two different and non-overlapping pairs of letters.
     fun overlappingPairs(word: String?): Boolean {
        return Pattern.matches(".*(.)\\1.*(.)\\2.*",word)
    }

    // combining above three fun here and getting combined true/false from the cases.
     fun isCorrect(word: String?): Boolean {
        return threeLetters(word) && mistakenLetters(word) && overlappingPairs(word)
    }

    // objective of this function is the incrementation of the input string chars.
     fun addition(word: String?): String {
        return Long.toString(word!!.toLong(36) + 1, 36).replace('0', 'a')
    }

    // getting first password.
    override fun part1(dataInput: List<String?>?): String {
        var passCode = addition(dataInput!![0])
        while (!isCorrect(passCode)) {
            passCode = addition(passCode)
        }
        return passCode
    }

    // getting second password.
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


fun main(){
    Day11()
}
