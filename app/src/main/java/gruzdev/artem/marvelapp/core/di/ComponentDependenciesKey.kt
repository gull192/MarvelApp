package gruzdev.artem.marvelapp.core.di

import dagger.MapKey
import gruzdev.artem.marvelapp.core.di.ComponentDependencies
import kotlin.reflect.KClass

@MapKey
@Target(AnnotationTarget.FUNCTION)
annotation class ComponentDependenciesKey(val value: KClass<out ComponentDependencies>)
