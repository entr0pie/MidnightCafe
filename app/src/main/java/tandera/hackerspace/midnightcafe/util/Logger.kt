package tandera.hackerspace.midnightcafe.util

import android.util.Log
import kotlin.reflect.KClass

class Logger(private val tag: String) {

    fun d(msg: String) = Log.d(tag, msg)
    fun i(msg: String) = Log.i(tag, msg)
    fun e(msg: String) = Log.e(tag, msg)
    fun e(msg: String, throwable: Throwable?) = Log.e(tag, msg, throwable)

    companion object {
        fun fromClass(clazz: KClass<*>): Logger {
            val clazzName = clazz.simpleName ?: clazz.toString()
            return Logger(clazzName)
        }
    }
}