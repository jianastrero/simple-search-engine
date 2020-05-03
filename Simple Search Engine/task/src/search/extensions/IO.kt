package search.extensions

operator fun String?.unaryPlus() = println(this)

operator fun String?.unaryMinus() = print(this)