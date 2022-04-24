package me.adkhambek.elf.container

import dagger.MapKey
import kotlin.reflect.KClass


@MustBeDocumented
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
public annotation class MicroContainerKey(val value: KClass<out MicroContainer>)