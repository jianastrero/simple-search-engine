package search.states

import search.SimpleSearchEngine

class AddToHaystackState(engine: SimpleSearchEngine, private vararg val items: String) : State(engine) {

    override fun run() {
        engine.haystack.addAll(items)
    }
}