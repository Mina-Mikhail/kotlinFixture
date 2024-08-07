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

import java.io.File
import java.math.BigDecimal
import java.time.LocalDate
import java.util.Date
import kotlin.reflect.KClass

internal fun KClass<*>.isBoolean(): Boolean {
    return this == Boolean::class
}

internal fun KClass<*>.isInt(): Boolean {
    return this == Int::class
}

internal fun KClass<*>.isLong(): Boolean {
    return this == Long::class
}

internal fun KClass<*>.isShort(): Boolean {
    return this == Short::class
}

internal fun KClass<*>.isByte(): Boolean {
    return this == Byte::class
}

internal fun KClass<*>.isFloat(): Boolean {
    return this == Float::class
}

internal fun KClass<*>.isDouble(): Boolean {
    return this == Double::class
}

internal fun KClass<*>.isChar(): Boolean {
    return this == Char::class
}

internal fun KClass<*>.isString(): Boolean {
    return this == String::class
}

internal fun KClass<*>.isBigDecimal(): Boolean {
    return this == BigDecimal::class
}

internal fun KClass<*>.isDate(): Boolean {
    return this == Date::class
}

internal fun KClass<*>.isLocalDate(): Boolean {
    return this == LocalDate::class
}

internal fun KClass<*>.isFile(): Boolean {
    return this == File::class
}

internal fun KClass<*>.isByteArray(): Boolean {
    return this == ByteArray::class
}

internal fun KClass<*>.isIntArray(): Boolean {
    return this == IntArray::class
}

internal fun KClass<*>.isMap(): Boolean {
    return this == Map::class
}

internal fun KClass<*>.isHashMap(): Boolean {
    return this == HashMap::class
}

internal fun KClass<*>.isList(): Boolean {
    return this == List::class
}

internal fun KClass<*>.isMutableList(): Boolean {
    return this == MutableList::class
}

internal fun KClass<*>.isArray(): Boolean {
    return this == Array::class
}

internal fun KClass<*>.isArrayList(): Boolean {
    return this == ArrayList::class
}

internal fun KClass<*>.isSet(): Boolean {
    return this == Set::class
}

internal fun KClass<*>.isJavaClass(): Boolean {
    return !(this.java.annotations.any { it.annotationClass.simpleName == "Metadata" })
}

internal fun KClass<*>.isInterface(): Boolean {
    return this.java.isInterface && !this.isSealed
}

internal fun KClass<*>.isPrimitive(): Boolean {
    return this.javaPrimitiveType?.isPrimitive == true
}

internal fun KClass<*>.isEnum(): Boolean {
    return this.java.isEnum
}

internal fun KClass<*>.isDataClass(): Boolean {
    return this.isData
}

internal fun KClass<*>.isSealedClass(): Boolean {
    return this.isSealed && this.sealedSubclasses.isNotEmpty()
}

internal fun KClass<*>.isAny(): Boolean {
    return this == Any::class
}