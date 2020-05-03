package search.states

import search.SimpleSearchEngine
import search.extensions.unaryPlus

class FindState(engine: SimpleSearchEngine, private vararg val needles: String) : State(engine) {

    override fun run() {
        val result = mutableListOf<String>()
        needles.forEach { needle ->
            engine.invertedIndex[needle]
                ?.map { engine.haystack[it] }
                ?.let {
                    result.addAll(it)
                }
        }

        +if (result.isEmpty()) {
            "No matching people found."
        } else {
            "${result.size} persons found:\n${ result.joinToString("\n") }"
        }
    }
}