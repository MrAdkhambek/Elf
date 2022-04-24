package me.adkhambek.elf

import androidx.activity.componentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import dagger.hilt.EntryPoints
import dagger.hilt.android.EntryPointAccessors
import me.adkhambek.elf.hilt.composable.ComposableComponentBuilderEntryPoint


@Composable
public inline fun <reified T : Any> rememberSingletonEntryPoint(): T {
    val context = LocalContext.current
    return remember {
        EntryPointAccessors.fromApplication(context, T::class.java)
    }
}

@Composable
public inline fun <reified T : Any> rememberActivityEntryPoint(): T {
    val context = LocalContext.current
    return remember {
        EntryPointAccessors.fromApplication(context.componentActivity, T::class.java)
    }
}

@Composable
public inline fun <reified T : Any> rememberComposableEntryPoint(): T {
    val context = LocalContext.current
    return remember {
        val entryPoint = EntryPointAccessors.fromActivity(
            context.componentActivity,
            ComposableComponentBuilderEntryPoint::class.java
        )

        EntryPoints.get(entryPoint.componentBuilder.build(), T::class.java)
    }
}