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

package com.minaMikhail.kotlinFixture.resolver

import com.minaMikhail.kotlinFixture.exceptions.FixtureParameterNotSupported
import com.minaMikhail.kotlinFixture.utils.getRandomParameterValue
import kotlin.reflect.KType

internal fun resolveSet(
    parameterType: KType,
    repeatSize: Int = 1
): Set<Any> {
    val elementType = parameterType.arguments.firstOrNull()?.type
        ?: throw FixtureParameterNotSupported(
            parentClassType = "Set",
            parameterType = parameterType
        )

    val mutableSet = mutableSetOf<Any>()

    repeat(repeatSize) {
        mutableSet.add(getRandomParameterValue(elementType))
    }

    return mutableSet.toSet()
}

internal fun resolveArrayList(
    parameterType: KType,
    repeatSize: Int = 1
): ArrayList<Any> {
    val elementType = parameterType.arguments.firstOrNull()?.type
        ?: throw throw FixtureParameterNotSupported(
            parentClassType = "ArrayList",
            parameterType = parameterType
        )

    val arrayList = arrayListOf<Any>()

    repeat(repeatSize) {
        arrayList.add(getRandomParameterValue(elementType))
    }

    return arrayList
}

internal fun resolveArray(
    parameterType: KType,
    repeatSize: Int = 1
): Array<Any> {
    val elementType = parameterType.arguments.firstOrNull()?.type
        ?: throw throw FixtureParameterNotSupported(
            parentClassType = "Array",
            parameterType = parameterType
        )

    var array = emptyArray<Any>()

    repeat(repeatSize) {
        array += getRandomParameterValue(elementType)
    }

    return array
}

internal fun resolveMutableList(
    parameterType: KType,
    repeatSize: Int = 1
): MutableList<Any> {
    val elementType = parameterType.arguments.firstOrNull()?.type
        ?: throw throw FixtureParameterNotSupported(
            parentClassType = "MutableList",
            parameterType = parameterType
        )

    val mutableList = mutableListOf<Any>()

    repeat(repeatSize) {
        mutableList.add(getRandomParameterValue(elementType))
    }

    return mutableList
}

internal fun resolveHashMap(
    parameterType: KType,
    repeatSize: Int = 1
): HashMap<Any, Any> {
    val keyType = parameterType.arguments.firstOrNull()?.type
        ?: throw throw FixtureParameterNotSupported(
            parentClassType = "HashMap",
            parameterType = parameterType
        )

    val valueType = parameterType.arguments.firstOrNull()?.type
        ?: throw FixtureParameterNotSupported(
            parentClassType = "HashMap",
            parameterType = parameterType
        )

    val hashMap = hashMapOf<Any, Any>()

    repeat(repeatSize) {
        hashMap[getRandomParameterValue(keyType)] = getRandomParameterValue(valueType)
    }

    return hashMap
}

internal fun resolveMap(
    parameterType: KType,
    repeatSize: Int = 1
): Map<Any, Any> {
    val keyType = parameterType.arguments.firstOrNull()?.type
        ?: throw FixtureParameterNotSupported(
            parentClassType = "Map",
            parameterType = parameterType
        )

    val valueType = parameterType.arguments.firstOrNull()?.type
        ?: throw FixtureParameterNotSupported(
            parentClassType = "Map",
            parameterType = parameterType
        )

    val mutableMap = mutableMapOf<Any, Any>()

    repeat(repeatSize) {
        mutableMap[getRandomParameterValue(keyType)] = getRandomParameterValue(valueType)
    }

    return mutableMap.toMap()
}