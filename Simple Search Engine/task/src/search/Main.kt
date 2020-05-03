package search

import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)

    val haystack = scanner.nextLine().split("\\s+".toRegex())
    val needle = scanner.nextLine()

    print(
        if (haystack.indexOf(needle) > -1)
            haystack.indexOf(needle) + 1
        else
            "Not found"
    )
}
