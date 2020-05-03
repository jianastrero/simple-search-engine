package search.states

import search.SimpleSearchEngine

class AddToHaystackState(engine: SimpleSearchEngine, private vararg val items: String) : State(engine) {

    override fun run() {
        val startIndex = engine.haystack.size

        engine.haystack.addAll(items)

        items.forEachIndexed { index, s ->
            val tokens = s.split("\\s+".toRegex())

            tokens.forEach {
                val list = engine.invertedIndex[it]?.toMutableList() ?: mutableListOf()

                list.add(startIndex + index)

                engine.invertedIndex[it] = list
            }
        }
    }
}