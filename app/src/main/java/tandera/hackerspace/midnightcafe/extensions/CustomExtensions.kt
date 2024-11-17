package tandera.hackerspace.midnightcafe.extensions

import tandera.hackerspace.midnightcafe.data.recipe.Recipe

fun List<String>.toRawString(): String {
    return this.joinToString(separator = "\n")
}

fun Recipe.equalsTo(other: Recipe): Boolean {
    return this.title == other.title && this.score == other.score
}