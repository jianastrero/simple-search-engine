package search.states

import search.SimpleSearchEngine
import search.extensions.unaryPlus

class FindState(engine: SimpleSearchEngine, private val needle: String) : State(engine) {

    override fun run() {
        val result = engine.haystack.filter { it.contains(needle, true) }

        +if (result.isEmpty()) {
            "No matching people found."
        } else {
            "Found people:\n${ result.joinToString("\n") }"
        }
    }
}