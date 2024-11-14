package tandera.hackerspace.midnightcafe.extensions

fun List<String>.toRawString(): String {
    return this.joinToString(separator = "\n")
}
