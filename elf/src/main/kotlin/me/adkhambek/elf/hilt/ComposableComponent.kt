package me.adkhambek.elf.hilt

import dagger.hilt.DefineComponent
import dagger.hilt.android.components.ActivityComponent


@ComposableScoped
@DefineComponent(parent = ActivityComponent::class)
public interface ComposableComponent