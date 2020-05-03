package search

import search.states.AddToHaystackState
import search.states.FindState
import search.states.IdleState
import search.states.PrintAllState
import search.states.State

class SimpleSearchEngine {

    private lateinit var _state: State

    val haystack = mutableListOf<String>()
    val invertedIndex = mutableMapOf<String, List<Int>>()

    var state: State
        get() = _state
        set(value) {
            _state = value
            _state.run()
        }

    init {
        idle()
    }

    fun idle() {
        state = IdleState(this)
    }

    fun addToHaystack(vararg items: String) {
        state = AddToHaystackState(this, *items)
    }

    operator fun plusAssign(item: String) = addToHaystack(item)

    operator fun plusAssign(items: Array<String>) = addToHaystack(*items)

    operator fun plusAssign(items: List<String>) = addToHaystack(*items.toTypedArray())

    infix fun find(needle: String) {
        state = FindState(this, needle)
    }

    infix fun find(needles: Array<String>) {
        state = FindState(this, *needles)
    }

    operator fun div(needle: String) = find(needle)

    fun printAll() {
        state = PrintAllState(this)
    }

    operator fun unaryPlus() = printAll()
}