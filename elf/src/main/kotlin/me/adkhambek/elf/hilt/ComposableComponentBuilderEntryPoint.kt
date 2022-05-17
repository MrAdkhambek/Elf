package me.adkhambek.elf.hilt

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent


@EntryPoint
@InstallIn(ActivityComponent::class)
public interface ComposableComponentBuilderEntryPoint {
    public val componentBuilder: ComposableComponent.Builder
}