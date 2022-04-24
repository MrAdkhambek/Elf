package me.adkhambek.elf.hilt.composable

import dagger.hilt.DefineComponent


@DefineComponent.Builder
public interface ComposableComponentBuilder {
    fun build(): ComposableComponent
}