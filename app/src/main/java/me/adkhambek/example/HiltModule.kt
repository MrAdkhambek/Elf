package me.adkhambek.example

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.multibindings.IntoMap
import me.adkhambek.elf.container.MicroContainerFactory
import me.adkhambek.elf.container.MicroContainerFactoryKey
import me.adkhambek.elf.hilt.composable.ComposableComponent


@Module
@InstallIn(ComposableComponent::class)
interface HiltModule {

    @Binds
    @IntoMap
    @MicroContainerFactoryKey(HiltMicroContainer.Factory::class)
    fun bindHiltDetailsScreenModelFactory(
        hiltDetailsScreenModelFactory: HiltMicroContainer.Factory
    ): MicroContainerFactory
}
