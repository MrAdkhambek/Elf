package me.adkhambek.elf

import androidx.activity.componentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import dagger.hilt.EntryPoints
import dagger.hilt.android.EntryPointAccessors
import me.adkhambek.elf.container.MicroContainer
import me.adkhambek.elf.container.MicroContainerFactory
import me.adkhambek.elf.hilt.MicroContainerEntryPoint
import me.adkhambek.elf.hilt.composable.ComposableComponentBuilderEntryPoint


@Composable
public inline fun <reified T : MicroContainer> rememberMicroContainer(): T {
    val context = LocalContext.current
    return remember {
        val entryPoint = EntryPointAccessors.fromActivity(
            context.componentActivity,
            ComposableComponentBuilderEntryPoint::class.java
        )

        val microContainers = EntryPoints
            .get(entryPoint.componentBuilder.build(), MicroContainerEntryPoint::class.java)
            .microContainers()

        val microContainer = microContainers[T::class.java] ?: error(
            "${T::class.java} not found in hilt graph.\nPlease, check if you have a Multibinding " +
                    "declaration to your MicroContainer using @IntoMap and " +
                    "@MicroContainerKey(${T::class.qualifiedName}::class)"
        )

        microContainer.get() as T
    }
}


@Composable
public inline fun <reified T : MicroContainer, reified F : MicroContainerFactory> rememberMicroContainer(
    noinline factory: (F) -> T
): T {
    val context = LocalContext.current
    return remember {
        val entryPoint = EntryPointAccessors.fromActivity(
            context.componentActivity,
            ComposableComponentBuilderEntryPoint::class.java
        )

        val microContainerFactories = EntryPoints
            .get(entryPoint.componentBuilder.build(), MicroContainerEntryPoint::class.java)
            .microContainerFactories()

        val microFactory = microContainerFactories[F::class.java] ?: error(
            "${T::class.java} not found in hilt graph.\nPlease, check if you have a Multibinding " +
                    "declaration to your MicroContainer using @IntoMap and " +
                    "@MicroContainerFactoryKey(${F::class.qualifiedName}::class)"
        )

        factory.invoke(microFactory.get() as F)
    }
}
