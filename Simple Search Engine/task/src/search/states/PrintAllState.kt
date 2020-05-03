package search.states

import search.SimpleSearchEngine
import search.extensions.unaryPlus

class PrintAllState(engine: SimpleSearchEngine) : State(engine) {

    override fun run() {
        +"=== List of people ===\n${ engine.haystack.joinToString("\n") }"
    }
}