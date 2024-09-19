package tandera.hackerspace.midnightcafe.services

/**
 * A item retriever, step-by-step.
 *
 * @param T type of item.
 */
interface Stepper<T> {
    /**
     * Get the current item, without changing the current step.
     *
     * @return the current item.
     */
    fun current(): T

    /**
     * Get the next item, without changing the current step.
     *
     * @return the next item.
     */
    fun next(): T

    /**
     * Step for the next item, changing the current for the next in the sequence.
     *
     * @return the next (now current).
     */
    fun step(): T
}

/**
 * Create a stepper based on a list of elements, repeating the sequence after the end.
 *
 * @param T type of list item.
 * @property values values for the list.
 */
open class LoopStepper<T>(private vararg val values: T) : Stepper<T> {
    private var index = 0;

    override fun current(): T {
        return values[index]
    }

    override fun next(): T {
        return if (index == values.size - 1) values[0] else values[index]
    }

    override fun step(): T {
        index = if (index == values.size - 1) 0 else index + 1
        return values[index]
    }
}