# Prabhjot-aoc-2

## Overview of the problem

For my Advent of Code assignment- 2 I decided to solve the [Advent of Code- 2015, Day- 11th](https://adventofcode.com/2015/day/11) problem. In this problem, I have to find the new password for Santa. The problem imposes three restrictions to find new password which is as following-
-	Passwords must include one increasing straight of at least three letters, like ```abc, bcd, cde, and so on, up to xyz```. They cannot skip letters; abd doesn't count.
-	Passwords may not contain the letters ```i, o, or l```, as these letters can be mistaken for other characters and are therefore confusing.
-	Passwords must contain at least two different, non-overlapping pairs of letters, like ```aa, bb, or zz```.

Under these conditions, the password should be made or created. 

Also for part 2 we have to find the next password, after taking the correct password from part 1. We will take it as input for part 2.

## First Solution

I approached this problem by sequentially by simply implementing the code according to the question. At first I made three functions ```threeLetters```, ```mistakenPattern``` and ```overlappingPairs```. Each of them have an argument ```word ```string and returns a ```boolean``` value at the end of the function. These function’s work is to check the three cases in order or to check the string against rules. 

In first function i.e., ```threeLetters``` we are checking if the string includes include one increasing straight of at least three letters, like ```abc```, ```bcd```, ```cde```, and so on, up to ```xyz```.

In second function i.e., ```mistakenPattern``` we are checking that string shouldn’t be containing the letters ```i, o, l```.

In third function i.e, ```overlappingPairs``` we are checking that the string must contain at least two different and non-overlapping pairs of letters, like ```aa, bb, or zz. ```

In all of three functions we are returning a ```boolean``` value true or false by which all of the three cases will be validated.

.
    
    
    private fun threeLetters(word: String): Boolean {
        val letters = ".*(abc|bcd|cde|def|efg|fgh|pqr|qrs|rst|stu|tuv|uvw|vwx|wxy|xyz).*"
        val pattern = Pattern.matches(letters, word)
        return pattern
    }
.

    private fun mistakenLetters(word: String): Boolean {
        val letters = ".*[iol]"
        val pattern = Pattern.matches(letters, word)
        return !pattern
    }
.

    private fun overlappingPairs(word: String): Boolean {
        val q = ".*(.)\\1.*(.)\\2.*"
        val pattern = Pattern.matches(q, word)
        return pattern
    }


Now, we are achieving this with the help of ```pattern``` class regular expressions which is basically used to match a pattern in an input text. To gain more clarity I looked up this [Kotlin documentation on regular expressions](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/-regex/). Here also we are taking the regular expressions pattern in the ```letters``` variable. 



- For ```threeletters``` function the pattern is –

        val letters = ".*(abc|bcd|cde|def|efg|fgh|pqr|qrs|rst|stu|tuv|uvw|vwx|wxy|xyz).*"
        
   Because we have to include at least three adjacent letters that’s why we have a regex pattern like that which basically have three letters altered with ```|```         symbol which acts like OR operator.  

- For ```mistakenLetters``` function the pattern is-

        val letters = ".*[iol]"       
        
   Because we have to exclude the ```i,o,l``` letters from the string where ``` []``` states that it contains a character set. 

- For ```overLappingPairs``` function the pattern is-

         val q = ".*(.)\\1.*(.)\\2.*"
         
   Because we have to put two different and non overlapping pairs of letters in the string so I found this pattern [regexr.com](https://regexr.com/) & [Kotlin Regex   documentation](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/-regex/).


The ```pattern``` class provides the property ```.matches()``` which has the ability to match the arbitrary character sequences against the regular expression. So, here also we added the matches property which takes two arguments as ```letters ```which is the regular expression string and ```word ```which is our input char sequence and we stored it in ```boolean``` pattern variable. This process is same in all three of the above functions.	

Moving on with keeping in mind that we have to run the program only if the three of the conditions to be true so we used ```&&``` operator in ```m```, ```n```, ```o``` which are returned ```boolean``` results of those three functions and we are returning this combined ```boolean```  value in the ```isCorrect``` function. 

Further the ```addition``` function’s work is to return the ```letter``` string and the main objective of this function is the incrementation of the input string chars. Finally, this function returns string ```letters``` which we will be requiring in the further methods. 

```part1``` and ```part2```  are abstracted functions of the interface ```Data``` and both the functions require ```dataInput``` parameters. The interface is implemented in our class ```Day11``` so that we got these overridden methods ```part1``` and ```part2``` in the class. 

At first we are taking passcode variable to store the ```addition```method’s returned value and entering the ```dataInput``` with 0th index. Then the loop is responsible to check different passwords by using the``` isCorrect ```method that is if the``` isCorrect ```method returns false the then loop will run again and the passcode will update or incremented with the ```addition``` method. Whenever we will get the correct password ( satisfying all the conditions or cases) the loop will finish and the ```part1```  will return the passcode. 

      override fun part1(dataInput: List<String>): String {
        var passCode = addition(dataInput[0])
        while (!isCorrect(passCode)) {
            passCode = addition(passCode)
        }
        return passCode
         }


In ```part2``` function we are doing the same process two times because whenever we get the correct passcode with the end of the loop, we will again update or increment the string run that with the loop again to get another passcode. Therefore, ```part2 ```returns us another validate or correct password which Santa can use. 

       override fun part2(dataInput: List<String>): String {
        var passCode = addition(dataInput[0])
        while (!isCorrect(passCode)) {
            passCode = addition(passCode)
        }
        passCode = addition(passCode)
        while (!isCorrect(passCode)) {
            passCode = addition(passCode)
        }
        return passCode
       }


## Second Solution

**Refactor**

**1) Added 'not null'**- I added ```?``` to different datatypes of parameters like:-

   ```word: String?```
          
   ```(dataInput: List<String?>?)```

As it is always a good practice to put ```?```` which basically states that the data type shouldn’t be null and if the compiler finds a null value, it will show error. 

**2) Removed extra variables and inlined some of the functions**

To make code more idiomatic and Kotlin friendly I reduced most of the variables in the functions and made 3-4 lined code to one line. After reducing the functions to one line I realised that there was no need to add so many variables in the code. Also, having fewer variables helps to reduce the memory allocation in the program due to which the processing time will also decrease.

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

**3) 3)	Removed access modifiers

I had ```private``` access modifiers before [this commit](https://github.com/nic-dgl-204-fall-2022/Prabhjot-aoc-2/blob/main/src/main/kotlin/Day11.kt). I put private before every function because I had the variables with same name like ```pattern``` in every function.([commit](https://github.com/nic-dgl-204-fall-2022/Prabhjot-aoc-2/blob/6de8e039ab1fc09f2f793c7a33be25865935e393/src/main/kotlin/Day11.kt)) Its always a good practise to encapsulate the functions so that there properties and variable would not affect the others but now I feel no need of ```private``` as most of the functions are inlined and same named variables or you can say most of variables are removed. 




