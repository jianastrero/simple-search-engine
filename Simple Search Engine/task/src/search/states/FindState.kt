package search.states

import search.SimpleSearchEngine
import search.extensions.containsAll
import search.extensions.unaryPlus

class FindState(
    engine: SimpleSearchEngine,
    private vararg val needles: String,
    private val strategy: Strategy = Strategy.ANY
) : State(engine) {

    val strategyMap: (Int) -> String? = {
        if (
            when (strategy) {
                Strategy.ALL -> engine.haystack[it].containsAll(*needles)
                Strategy.ANY,
                Strategy.NONE -> true
            }
        ) {
            engine.haystack[it]
        } else {
            null
        }
    }
    val strategyAdd: (MutableCollection<String>, MutableCollection<String>) -> Unit = { consumer, list ->
        when (strategy) {
            Strategy.ALL,
            Strategy.ANY -> consumer.addAll(list)
            Strategy.NONE -> {
                consumer.addAll(engine.haystack.toMutableList().apply { removeAll(list) })
            }
        }
    }

    override fun run() {
        val xResult = mutableSetOf<String>()
        needles.forEach { needle ->
            engine.invertedIndex[needle.toLowerCase()]
                ?.mapNotNull {
                    strategyMap(it)
                }
                ?.let {
                    xResult.addAll(it)
                }
        }

        val result = mutableSetOf<String>()
        strategyAdd(result, xResult)

        +if (result.isEmpty()) {
            "No matching people found."
        } else {
            "${result.size} persons found:\n${ result.joinToString("\n") }"
        }
    }

    enum class Strategy {
        ALL, ANY, NONE;

        companion object {

            fun get(name: String): Strategy =
                when (name.toLowerCase()) {
                    ALL.name.toLowerCase() -> ALL
                    NONE.name.toLowerCase() -> NONE
                    else -> ANY
                }
        }
    }

    class Builder(private val engine: SimpleSearchEngine) {

        var strategy: Strategy? = null
        var needles: Array<String> = arrayOf()

        infix fun find(needles: Array<String>) {
            engine.state =
                this.apply { this.needles = needles }
                    .build()
        }

        fun build() = strategy?.let {
            FindState(engine, *needles, strategy = it)
        } ?: FindState(engine, *needles)
    }
}