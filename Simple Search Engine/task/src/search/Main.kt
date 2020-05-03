package search

import search.extensions.unaryPlus
import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)
    val engine = SimpleSearchEngine()

    +"Enter the number of people:"
    val count = scanner.nextLine().toInt()

    +"Enter all people:"
    engine += Array(count) { scanner.nextLine() }

    mainLoop@while (true) {
        +"""
            
            === Menu ===
            1. Find a person
            2. Print all people
            0. Exit
        """.trimIndent()

        val action = scanner.nextLine().toInt()

        +""
        when (action) {
            1 -> {
                +"Enter a name or email to search all suitable people."
                engine / scanner.nextLine()
            }
            2 -> {
                +engine
            }
            0 -> {
                break@mainLoop
            }
            else -> {
                +"Incorrect option! Try again."
            }
        }
    }

    +"\nBye!"
}