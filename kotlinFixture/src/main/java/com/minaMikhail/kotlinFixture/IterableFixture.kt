/*
 * Copyright 2024 Mina Mikhail
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.minaMikhail.kotlinFixture

import com.minaMikhail.kotlinFixture.utils.buildDummyClass

inline fun <reified T : Any> fixtureList(
    repeatSize: Int = 1,
    noinline overrides: () -> Map<String, Any?> = { emptyMap() }
): List<T> {
    val list = mutableListOf<T>()

    repeat(repeatSize) {
        list.add(
            buildDummyClass(
                kotlinClass = T::class,
                constructors = T::class.constructors,
                overrides = overrides
            )
        )
    }

    return list.toList()
}

inline fun <reified T : Any> fixtureArray(
    repeatSize: Int = 1,
    noinline overrides: () -> Map<String, Any?> = { emptyMap() }
): Array<T> {
    var array = emptyArray<T>()

    repeat(repeatSize) {
        array += buildDummyClass(
            kotlinClass = T::class,
            constructors = T::class.constructors,
            overrides = overrides
        )
    }

    return array
}

inline fun <reified T : Any> fixtureSet(
    repeatSize: Int = 1,
    noinline overrides: () -> Map<String, Any?> = { emptyMap() }
): Set<T> {
    val mutableSet = mutableSetOf<T>()

    repeat(repeatSize) {
        mutableSet.add(
            buildDummyClass(
                kotlinClass = T::class,
                constructors = T::class.constructors,
                overrides = overrides
            )
        )
    }

    return mutableSet.toSet()
}

inline fun <reified K : Any, reified V : Any> fixtureMap(
    repeatSize: Int = 1,
    noinline overrides: () -> Map<String, Any?> = { emptyMap() }
): Map<K, V> {
    val mutableMap = mutableMapOf<K, V>()

    repeat(repeatSize) {
        val key = buildDummyClass(
            kotlinClass = K::class,
            constructors = K::class.constructors,
            overrides = overrides
        )
        val value = buildDummyClass(
            kotlinClass = V::class,
            constructors = V::class.constructors,
            overrides = overrides
        )

        mutableMap[key] = value
    }

    return mutableMap.toMap()
}