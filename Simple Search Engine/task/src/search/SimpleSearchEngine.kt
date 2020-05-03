package search

import search.extensions.states.State

class SimpleSearchEngine {

    private lateinit var _state: State

    var state: State
        get() = _state
        set(value) {
            _state = value
            _state.run()
        }
}