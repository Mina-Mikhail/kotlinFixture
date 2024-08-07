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

import com.minaMikhail.kotlinFixture.exceptions.FixtureClassNotSupported
import com.minaMikhail.kotlinFixture.exceptions.FixtureSealedNotSupported
import com.minaMikhail.kotlinFixture.utils.getClassMainConstructor
import com.minaMikhail.kotlinFixture.utils.getConstructorDummyParameters
import com.minaMikhail.kotlinFixture.utils.getRandomParameterValue
import com.minaMikhail.kotlinFixture.utils.invokeClassConstructor
import com.minaMikhail.kotlinFixture.utils.isBoolean
import com.minaMikhail.kotlinFixture.utils.isByte
import com.minaMikhail.kotlinFixture.utils.isDouble
import com.minaMikhail.kotlinFixture.utils.isFloat
import com.minaMikhail.kotlinFixture.utils.isInt
import com.minaMikhail.kotlinFixture.utils.isLong
import com.minaMikhail.kotlinFixture.utils.isShort
import com.minaMikhail.kotlinFixture.utils.randomBigDecimal
import com.minaMikhail.kotlinFixture.utils.randomBoolean
import com.minaMikhail.kotlinFixture.utils.randomByte
import com.minaMikhail.kotlinFixture.utils.randomChar
import com.minaMikhail.kotlinFixture.utils.randomDate
import com.minaMikhail.kotlinFixture.utils.randomDouble
import com.minaMikhail.kotlinFixture.utils.randomFloat
import com.minaMikhail.kotlinFixture.utils.randomInt
import com.minaMikhail.kotlinFixture.utils.randomLocalDate
import com.minaMikhail.kotlinFixture.utils.randomLong
import com.minaMikhail.kotlinFixture.utils.randomShort
import com.minaMikhail.kotlinFixture.utils.randomString
import io.github.classgraph.ClassGraph
import kotlin.math.abs
import kotlin.random.Random
import kotlin.reflect.KClass
import kotlin.reflect.KFunction
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.memberProperties

@Suppress("UNCHECKED_CAST")
internal fun <T : Any> resolvePrimitiveType(kotlinClass: KClass<T>): T {
    return when {
        kotlinClass.isBoolean() -> randomBoolean() as T
        kotlinClass.isInt() -> randomInt() as T
        kotlinClass.isLong() -> randomLong() as T
        kotlinClass.isShort() -> randomShort() as T
        kotlinClass.isByte() -> randomByte() as T
        kotlinClass.isFloat() -> randomFloat() as T
        kotlinClass.isDouble() -> randomDouble() as T

        else -> throw FixtureClassNotSupported(kotlinClass)
    }
}

internal fun <T : Any> resolveEnum(kotlinClass: KClass<T>): T {
    val enumConstants = kotlinClass.java.enumConstants
    return enumConstants?.get(abs(Random.nextInt(enumConstants.size))) as T
}

@Suppress("UNCHECKED_CAST")
internal fun <T : Any> resolveInterface(
    kotlinClass: KClass<T>,
    repeatSize: Int,
    overrides: () -> Map<String, Any?> = { emptyMap() }
): T {
    val interfaceClassInfo = ClassGraph()
        .enableSystemJarsAndModules()
        .enableClassInfo()
        .enableMethodInfo()
        .scan()
        .getClassInfo(kotlinClass.java.name)

    val subClasses = if (interfaceClassInfo.isInterface) {
        // To get latest child that implements the interface
        interfaceClassInfo.classesImplementing.filter {
            !it.isInterface
                    && !it.isAbstract
                    && !it.sourceFile.lowercase().contains("test")
        }
    } else {
        interfaceClassInfo.subclasses
    }

    val randomClass = subClasses[abs(Random.nextInt(subClasses.size))].loadClass()

    return if (randomClass.constructors.isNotEmpty()) { // Data Class
        resolveDataClass(randomClass.kotlin.constructors, repeatSize, overrides) as T
    } else { // Object
        resolveObject(randomClass) as T
    }
}

@Suppress("UNCHECKED_CAST")
internal fun <T : Any> resolveChar(): T = randomChar() as T

@Suppress("UNCHECKED_CAST")
internal fun <T : Any> resolveString(): T = randomString() as T

@Suppress("UNCHECKED_CAST")
internal fun <T : Any> resolveBigDecimal(): T = randomBigDecimal() as T

@Suppress("UNCHECKED_CAST")
internal fun <T : Any> resolveDate(): T = randomDate() as T

@Suppress("UNCHECKED_CAST")
internal fun <T : Any> resolveLocalDate(): T = randomLocalDate() as T

@Suppress("UNCHECKED_CAST")
internal fun <T : Any> resolveSealedClass(
    kotlinClass: KClass<T>,
    repeatSize: Int,
    overrides: () -> Map<String, Any?> = { emptyMap() }
): T {
    if (kotlinClass.sealedSubclasses.isNotEmpty()) {
        val subclasses = kotlinClass.sealedSubclasses
        val randomSubClass = subclasses[abs(Random.nextInt(subclasses.size))]

        return if (randomSubClass.objectInstance != null) { // InCase object
            randomSubClass.objectInstance as T
        } else if (randomSubClass.isSealed) { // InCase sealed class
            if (randomSubClass.nestedClasses.isNotEmpty()) {
                val nestedClasses = randomSubClass.nestedClasses.toList()
                val randomNestedClass = nestedClasses[abs(Random.nextInt(nestedClasses.size))]

                if (randomNestedClass.objectInstance != null) { // InCase object
                    randomNestedClass.objectInstance as T
                } else {
                    resolveDataClass(randomNestedClass.constructors, repeatSize, overrides) as T
                }
            } else {
                throw FixtureSealedNotSupported(randomSubClass)
            }
        } else {
            if (randomSubClass.constructors.isNotEmpty()) { // InCase data class
                resolveDataClass(randomSubClass.constructors, repeatSize, overrides)
            } else {
                throw FixtureClassNotSupported(randomSubClass)
            }
        }
    } else {
        throw FixtureClassNotSupported(kotlinClass)
    }
}

internal fun <T : Any> resolveJavaClass(
    kotlinClass: KClass<T>,
    repeatSize: Int
): T {
    val classInstance = kotlinClass.createInstance()

    kotlinClass.java.declaredFields.forEach { privateField ->
        privateField.isAccessible = true

        kotlinClass.memberProperties.firstOrNull { it.name == privateField.name }
            ?.returnType?.let { kType ->
                privateField.set(classInstance, getRandomParameterValue(kType, repeatSize))
            }
    }

    return classInstance
}

internal fun resolveObject(
    objectClass: Class<*>
): Any? = objectClass.getDeclaredField("INSTANCE").get(null)

internal fun <T : Any> resolveDataClass(
    constructors: Collection<KFunction<T>>,
    repeatSize: Int,
    overrides: () -> Map<String, Any?> = { emptyMap() }
): T {
    val constructor = getClassMainConstructor(constructors)
    val parameters = getConstructorDummyParameters(constructor, repeatSize, overrides)

    return invokeClassConstructor(constructor, parameters)
}

internal fun resolveAny(): Any = Any()