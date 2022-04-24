package me.adkhambek.elf.hilt

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import me.adkhambek.elf.container.MicroContainer
import me.adkhambek.elf.container.MicroContainerFactory
import me.adkhambek.elf.hilt.composable.ComposableComponent
import javax.inject.Provider


@EntryPoint
@InstallIn(ComposableComponent::class)
public interface MicroContainerEntryPoint {

    public fun microContainers():
            Map<Class<out MicroContainer>, @JvmSuppressWildcards Provider<MicroContainer>>

    public fun microContainerFactories():
            Map<Class<out MicroContainerFactory>, @JvmSuppressWildcards Provider<MicroContainerFactory>>
}