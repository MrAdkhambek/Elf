package me.adkhambek.elf.hilt.composable

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent


@EntryPoint
@InstallIn(ActivityComponent::class)
public interface ComposableComponentBuilderEntryPoint {

    val componentBuilder: ComposableComponentBuilder
}