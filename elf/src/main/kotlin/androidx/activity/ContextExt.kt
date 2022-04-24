package androidx.activity

import android.content.Context
import android.content.ContextWrapper


internal fun Context.contextWrappers(): Sequence<Context> = sequence {
    var innerContext = this@contextWrappers

    while (innerContext is ContextWrapper) {
        yield(innerContext)
        innerContext = innerContext.baseContext
    }
}

internal inline fun <reified T> findOwner(context: Context): T? {
    return context.contextWrappers()
        .filterIsInstance<T>()
        .singleOrNull()
}

@PublishedApi
internal val Context.componentActivity: ComponentActivity
    get() = findOwner<ComponentActivity>(this)
        ?: error("Context must be a androidx.activity.ComponentActivity. Current is $this")