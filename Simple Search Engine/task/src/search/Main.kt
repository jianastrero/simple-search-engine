package search

import search.extensions.unaryPlus
import java.io.File
import java.util.Scanner

fun main(vararg args: String) {
    if (args.size != 2 || args[0] != "--data") {
        +"Invalid arguments"
        return
    }

    val file = File(args[1])
    if (!file.exists() || file.isDirectory || !file.canRead()) {
        +"Invalid file"
        return
    }

    val scanner = Scanner(System.`in`)
    val engine = SimpleSearchEngine()

    file.forEachLine {
        engine += it
    }

    mainLoop@while (true) {
        +"""
            
            === Menu ===
            1. Find a person
            2. Print all people
            0. Exit
        """.trimIndent()
        engine.idle()

        val action = scanner.nextLine().toInt()

        +""
        when (action) {
            1 -> {
                +"Enter a name or email to search all suitable people."
                engine find scanner.nextLine().split("\\s+".toRegex()).toTypedArray()
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