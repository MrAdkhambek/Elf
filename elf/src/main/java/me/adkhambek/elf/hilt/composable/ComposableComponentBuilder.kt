package me.adkhambek.elf.hilt.composable

import dagger.hilt.DefineComponent


@DefineComponent.Builder
interface ComposableComponentBuilder {
    fun build(): ComposableComponent
}