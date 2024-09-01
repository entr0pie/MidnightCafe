package tandera.hackerspace.midnightcafe.ui.components.common

enum class Score(val rate: Int) {
    ZERO(0),
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5);

    fun value() = rate

    companion object {
        fun of(rate: Int): Score? = entries.find { it.rate == rate }
    }
}