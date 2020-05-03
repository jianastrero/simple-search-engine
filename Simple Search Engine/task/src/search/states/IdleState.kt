package search.states

import search.SimpleSearchEngine

class IdleState(engine: SimpleSearchEngine) : State(engine) {

    override fun run() {
        // Do nothing
    }
}