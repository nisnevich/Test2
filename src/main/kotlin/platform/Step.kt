package platform

import com.jetbrains.test.log

inline fun <O> step(text: String, crossinline block: () -> O): O {
    try {
        indent.set(indent.get().plus(1))
        log.info("${indents()}$text")
        return block()
    } catch (e: Throwable) {
        log.warn("${indents()}Failed on step: $text")
        throw e
    } finally {
        indent.set(indent.get().minus(1))
    }
}

val indent = ThreadLocal.withInitial { 0 }!!

fun indents(): String {
    val bar = StringBuilder()
    for (i in 1..indent.get()) {
        bar.append("    ")
    }
    return bar.toString()
}
