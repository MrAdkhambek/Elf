package me.adkhambek.elf.hilt


import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.multibindings.Multibinds
import me.adkhambek.elf.container.MicroContainer
import me.adkhambek.elf.container.MicroContainerFactory


@Module
@InstallIn(ActivityRetainedComponent::class)
public interface OptionalMultiBindingsModule {

    @Multibinds
    public fun screenModels(): Map<Class<out MicroContainer>, MicroContainer>

    @Multibinds
    public fun screenModelFactories(): Map<Class<out MicroContainerFactory>, MicroContainerFactory>
}
