fun main() {
    println("The smallest odd composite that cannot be written as the sum of a prime number and a doubled" +
            " square is equal to ${getSmallestOddCompositeThatCannotBeWrittenAsTheSumOfPrimeAndTwiceSquare()}")
}

fun getSmallestOddCompositeThatCannotBeWrittenAsTheSumOfPrimeAndTwiceSquare(): Int {
    var result = 0
    var counter = 1
    var numberIsSearched = true
    while (numberIsSearched) {
        val isOddnessNumber = checkToOddness(counter)
        if (isOddnessNumber) {
            val isCompositeNumber = checkToCompositeNumber(counter)
            if (isCompositeNumber) {
                var isSearched = false
                createList(counter).forEachIndexed { _, i ->
                    if (isSearched) return@forEachIndexed
                    val isPrimaryNumber = checkToPrimeNumber(i)
                    if (isPrimaryNumber) {
                        isSearched = checkTheHypothesisForOddCompositeNumber(counter, i)
                        if (i == 2 && !isSearched) {
                            result = counter
                            numberIsSearched = false
                        }
                    }
                }
            }
        }
        if (numberIsSearched) counter++
    }
    return result
}

fun createList(number: Int): MutableList<Int> {
    val list = mutableListOf<Int>()
    for (i in number - 2 downTo 2) {
        list.add(i)
    }
    return list
}

fun checkTheHypothesisForOddCompositeNumber(compositeNumber: Int, primaryNumber: Int): Boolean {
    var powNumber = 1
    var result = false
    var hypothesisIsConfirmedForCurrentNumbers = true
    while (hypothesisIsConfirmedForCurrentNumbers) {
        when (primaryNumber + (2 * (powNumber * powNumber))) {
            in 0 until compositeNumber -> {
                powNumber++
            }

            in compositeNumber + 1..Int.MAX_VALUE -> hypothesisIsConfirmedForCurrentNumbers = false
            compositeNumber -> {
                hypothesisIsConfirmedForCurrentNumbers = false
                result = true
            }
        }
    }
    return result
}

fun checkToCompositeNumber(number: Int): Boolean {
    var result = false
    for (i in 2 until number) {
        if (number % i == 0) result = true
    }
    return result
}

fun checkToPrimeNumber(number: Int): Boolean {
    return !checkToCompositeNumber(number)
}

fun checkToOddness(number: Int): Boolean {
    return number % 2 != 0
}