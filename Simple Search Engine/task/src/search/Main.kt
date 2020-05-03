package search

import search.extensions.unaryPlus
import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)

    +"Enter the number of people:"
    val count = scanner.nextLine().toInt()

    +"Enter all people:"

    val haystack = Array(count) { scanner.nextLine() }

    +"""
        
        Enter the number of search queries:
    """.trimIndent()
    val queries = scanner.nextLine().toInt()

    repeat(queries) {
        +"""

            Enter data to search people:
        """.trimIndent()
        val needle = scanner.nextLine()

        val result = haystack.filter { it.contains(needle, true) }

        +if (result.isEmpty()) {
            "No matching people found."
        } else {
            "Found people:\n${result.joinToString("\n")}"
        }
    }


}