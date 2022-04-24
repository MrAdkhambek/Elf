package me.adkhambek.elf.hilt.composable

import dagger.hilt.DefineComponent
import dagger.hilt.android.components.ActivityComponent


@ComposableScoped
@DefineComponent(parent = ActivityComponent::class)
public interface ComposableComponent