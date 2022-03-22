fun main() {
    println("Комиссия ${calcDiscount(card = "Visa", transactionHistory = 150000, transactionSum = 150000)}")
    println("Комиссия ${calcDiscount(transactionHistory = 15000000, transactionSum = 15000000)}")
    println("Комиссия ${calcDiscount(card = "Mastercard", transactionHistory = 3500, transactionSum = 3500)}")
    println("Комиссия ${calcDiscount(card = "Mastercard", transactionHistory = 7500000, transactionSum = 7500000)}")
    println("Комиссия ${calcDiscount(card = "Mastercard", transactionHistory = 7500, transactionSum = 7500)}")
}

fun calcDiscount(card: String = "Vk Pay", transactionHistory: Int = 0, transactionSum: Int): Int? {
    if (inLimits(card, transactionHistory, transactionSum)) {
        return when (card) {
            "Mastercard", "Maestro" -> return if ((transactionHistory < 7500000) && (transactionSum < 7500000)) {
                0
            } else {
                ((transactionSum * 0.006 + 2000) / 100).toInt()
            }
            "Visa", "Мир" -> when {
                transactionSum * 0.0075 > 3500 -> transactionSum * 0.0075.toInt()
                else -> 3500
            } else -> 0
        }
    } else {
        return null
    }
}

fun inLimits(card: String, transactionHistory: Int, transactionSum: Int): Boolean {
    return when {
        card == "Vk Pay" -> return when {
            transactionHistory < 4000000 && transactionSum < 1500000 -> true
            else -> false
        }
        transactionSum < 15000000 && transactionHistory < 60000000 -> true
        else -> false
    }
}
