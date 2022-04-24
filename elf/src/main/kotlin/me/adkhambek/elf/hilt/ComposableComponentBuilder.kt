package me.adkhambek.elf.hilt

import dagger.hilt.DefineComponent


@DefineComponent.Builder
public interface ComposableComponentBuilder {
    fun build(): ComposableComponent
}