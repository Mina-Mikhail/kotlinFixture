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

package com.minaMikhail.kotlinFixture.utils

import com.minaMikhail.kotlinFixture.resolver.resolveBigDecimal
import com.minaMikhail.kotlinFixture.resolver.resolveChar
import com.minaMikhail.kotlinFixture.resolver.resolveDataClass
import com.minaMikhail.kotlinFixture.resolver.resolveDate
import com.minaMikhail.kotlinFixture.resolver.resolveEnum
import com.minaMikhail.kotlinFixture.resolver.resolveInterface
import com.minaMikhail.kotlinFixture.resolver.resolveJavaClass
import com.minaMikhail.kotlinFixture.resolver.resolveLocalDate
import com.minaMikhail.kotlinFixture.resolver.resolvePrimitiveType
import com.minaMikhail.kotlinFixture.resolver.resolveSealedClass
import com.minaMikhail.kotlinFixture.resolver.resolveString
import kotlin.reflect.KClass
import kotlin.reflect.KFunction

fun <T : Any> buildDummyClass(
    kotlinClass: KClass<T>,
    constructors: Collection<KFunction<T>>,
    repeatSize: Int = 1,
    overrides: () -> Map<String, Any?>
): T = when {
    kotlinClass.isInterface() -> resolveInterface(kotlinClass, repeatSize, overrides)
    kotlinClass.isPrimitive() -> resolvePrimitiveType(kotlinClass)
    kotlinClass.isEnum() -> resolveEnum(kotlinClass)
    kotlinClass.isSealedClass() -> resolveSealedClass(kotlinClass, repeatSize, overrides)
    kotlinClass.isChar() -> resolveChar()
    kotlinClass.isString() -> resolveString()
    kotlinClass.isBigDecimal() -> resolveBigDecimal()
    kotlinClass.isDate() -> resolveDate()
    kotlinClass.isLocalDate() -> resolveLocalDate()
    kotlinClass.isJavaClass() -> resolveJavaClass(kotlinClass, repeatSize)

    else -> resolveDataClass(constructors, repeatSize, overrides)
}

internal fun <T : Any> buildNestedDummyClass(
    kotlinClass: KClass<T>,
    repeatSize: Int = 1,
    overrides: () -> Map<String, Any?> = { emptyMap() }
): T = buildDummyClass(
    kotlinClass = kotlinClass,
    constructors = kotlinClass.constructors,
    repeatSize = repeatSize,
    overrides = overrides
)