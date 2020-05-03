package search.states

import search.SimpleSearchEngine

abstract class State(protected val engine: SimpleSearchEngine) {

    abstract fun run()
}