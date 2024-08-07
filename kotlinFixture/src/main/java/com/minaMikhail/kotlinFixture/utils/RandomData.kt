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

import com.minaMikhail.kotlinFixture.config.CHAR_POOL
import com.minaMikhail.kotlinFixture.config.DUMMY_DURATION
import com.minaMikhail.kotlinFixture.config.DUMMY_REFERENCE_TIME
import com.minaMikhail.kotlinFixture.config.END_DUMMY_DAY
import com.minaMikhail.kotlinFixture.config.END_DUMMY_MONTH
import com.minaMikhail.kotlinFixture.config.END_DUMMY_YEAR
import com.minaMikhail.kotlinFixture.config.SMALL_CHARACTERS
import com.minaMikhail.kotlinFixture.config.START_DUMMY_DAY
import com.minaMikhail.kotlinFixture.config.START_DUMMY_MONTH
import com.minaMikhail.kotlinFixture.config.START_DUMMY_YEAR
import com.minaMikhail.kotlinFixture.exceptions.FixtureClassNotSupported
import com.minaMikhail.kotlinFixture.exceptions.FixtureClassifierNotSupported
import com.minaMikhail.kotlinFixture.resolver.resolveAny
import com.minaMikhail.kotlinFixture.resolver.resolveArray
import com.minaMikhail.kotlinFixture.resolver.resolveArrayList
import com.minaMikhail.kotlinFixture.resolver.resolveEnum
import com.minaMikhail.kotlinFixture.resolver.resolveHashMap
import com.minaMikhail.kotlinFixture.resolver.resolveInterface
import com.minaMikhail.kotlinFixture.resolver.resolveMap
import com.minaMikhail.kotlinFixture.resolver.resolveMutableList
import com.minaMikhail.kotlinFixture.resolver.resolveSealedClass
import com.minaMikhail.kotlinFixture.resolver.resolveSet
import java.io.File
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.LocalDate
import java.util.Date
import java.util.concurrent.TimeUnit
import kotlin.math.abs
import kotlin.random.Random
import kotlin.reflect.KClass
import kotlin.reflect.KType

internal fun randomChar(): Char = SMALL_CHARACTERS.random()

internal fun randomString(): String = (1..10)
    .map { Random.nextInt(0, CHAR_POOL.size) }
    .map(CHAR_POOL::get)
    .joinToString("")

internal fun randomBoolean(): Boolean = Random.nextBoolean()

internal fun randomInt(): Int = abs(Random.nextInt())

internal fun randomLong(): Long = abs(Random.nextLong())

internal fun randomShort(): Short = abs(Random.nextInt()).toShort()

internal fun randomByte(): Byte = abs(Random.nextInt()).toByte()

internal fun randomFloat(): Float = abs(Random.nextFloat())

internal fun randomDouble(): Double = abs(Random.nextDouble())

internal fun randomBigDecimal(): BigDecimal {
    var actualRandomDec = BigDecimal(Math.random()).multiply(BigDecimal(3))
    actualRandomDec = actualRandomDec.setScale(2, RoundingMode.DOWN)

    return actualRandomDec
}

internal fun randomLocalDate(): LocalDate = LocalDate.of(
    Random.nextInt(START_DUMMY_YEAR, END_DUMMY_YEAR),
    Random.nextInt(START_DUMMY_MONTH, END_DUMMY_MONTH),
    Random.nextInt(START_DUMMY_DAY, END_DUMMY_DAY)
)

internal fun randomDate(): Date = Date(
    Random.nextLong(
        DUMMY_REFERENCE_TIME - TimeUnit.DAYS.toMillis(DUMMY_DURATION),
        DUMMY_REFERENCE_TIME + TimeUnit.DAYS.toMillis(DUMMY_DURATION)
    )
)

internal fun randomFile(): File = File(randomString())

internal fun randomByteArray(repeatSize: Int = 1): ByteArray = Random.nextBytes(repeatSize)

internal fun randomIntArray(repeatSize: Int = 1): IntArray {
    var intArray = intArrayOf()

    repeat(repeatSize) {
        intArray += randomInt()
    }

    return intArray
}

internal fun getRandomParameterValue(
    parameterType: KType,
    repeatSize: Int = 1
): Any {
    val kotlinClass = parameterType.classifier as? KClass<*>

    kotlinClass?.let {
        return when {
            kotlinClass.isChar() -> randomChar()
            kotlinClass.isString() -> randomString()
            kotlinClass.isBoolean() -> randomBoolean()
            kotlinClass.isInt() -> randomInt()
            kotlinClass.isLong() -> randomLong()
            kotlinClass.isShort() -> randomShort()
            kotlinClass.isByte() -> randomByte()
            kotlinClass.isFloat() -> randomFloat()
            kotlinClass.isDouble() -> randomDouble()
            kotlinClass.isBigDecimal() -> randomBigDecimal()
            kotlinClass.isLocalDate() -> randomLocalDate()
            kotlinClass.isDate() -> randomDate()
            kotlinClass.isFile() -> randomFile()

            kotlinClass.isMap() -> resolveMap(parameterType, repeatSize)
            kotlinClass.isHashMap() -> resolveHashMap(parameterType, repeatSize)
            kotlinClass.isList() -> resolveMutableList(parameterType, repeatSize).toList()
            kotlinClass.isMutableList() -> resolveMutableList(parameterType, repeatSize)
            kotlinClass.isArray() -> resolveArray(parameterType, repeatSize)
            kotlinClass.isArrayList() -> resolveArrayList(parameterType, repeatSize)
            kotlinClass.isSet() -> resolveSet(parameterType)
            kotlinClass.isByteArray() -> randomByteArray(repeatSize)
            kotlinClass.isIntArray() -> randomIntArray(repeatSize)

            kotlinClass.isAny() -> resolveAny()

            kotlinClass.isInterface() -> resolveInterface(kotlinClass, repeatSize)

            kotlinClass.isSealedClass() -> resolveSealedClass(kotlinClass, repeatSize)

            kotlinClass.isEnum() -> resolveEnum(kotlinClass)

            kotlinClass.isDataClass() -> buildNestedDummyClass(kotlinClass, repeatSize)

            else -> throw FixtureClassNotSupported(kotlinClass)
        }
    } ?: throw FixtureClassifierNotSupported(parameterType.classifier)
}