import java.util.regex.Pattern



class Day11 : Data<String?> {

    private fun threeLetters(word: String?): Boolean {
        val letters = ".*(abc|bcd|cde|def|efg|fgh|pqr|qrs|rst|stu|tuv|uvw|vwx|wxy|xyz).*"
        val pattern = Pattern.matches(letters, word)
        return pattern
    }

    private fun mistakenLetters(word: String?): Boolean {
        val letters = ".*[iol]"
        val pattern = Pattern.matches(letters, word)
        return !pattern
    }

    private fun overlappingPairs(word: String?): Boolean {
        val q = ".*(.)\\1.*(.)\\2.*"
        val pattern = Pattern.matches(q, word)
        return pattern
    }

    private fun isCorrect(word: String?): Boolean {

        val m = threeLetters(word)
        val n = mistakenLetters(word)
        val o = overlappingPairs(word)

        val bool = m && n && o

        return bool
    }

    private fun addition(word: String?): String {

        val letters = java.lang.Long.toString(word!!.toLong(36) + 1, 36)
            .replace('0', 'a')

        return letters
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
