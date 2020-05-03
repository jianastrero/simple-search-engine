package search.extensions

fun String.containsAll(vararg items: String) =
    items.all { this.contains(it, true) }

fun String.containsNone(vararg items: String) =
    items.all { !this.contains(it, true) }