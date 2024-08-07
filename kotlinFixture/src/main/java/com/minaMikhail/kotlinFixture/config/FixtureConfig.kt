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

package com.minaMikhail.kotlinFixture.config

import java.time.ZoneId
import java.time.ZonedDateTime
import kotlin.random.Random

internal const val DUMMY_DURATION = 3650L

internal const val START_DUMMY_YEAR = 1900
internal const val END_DUMMY_YEAR = 2030

internal const val START_DUMMY_MONTH = 1
internal const val END_DUMMY_MONTH = 12

internal const val START_DUMMY_DAY = 1
internal const val END_DUMMY_DAY = 28

internal const val START_DUMMY_HOUR = 0
internal const val END_DUMMY_HOUR = 23

internal const val START_DUMMY_MINUTE = 0
internal const val END_DUMMY_MINUTE = 59

internal const val START_DUMMY_SECOND = 0
internal const val END_DUMMY_SECOND = 59

internal const val START_DUMMY_NANO_SECOND = 0
internal const val END_DUMMY_NANO_SECOND = 1000

internal val SMALL_CHARACTERS = 'a'..'z'
internal val CAPITAL_CHARACTERS = 'A'..'Z'

internal val CHAR_POOL = SMALL_CHARACTERS + CAPITAL_CHARACTERS

internal val DUMMY_REFERENCE_TIME = ZonedDateTime.of(
    Random.nextInt(START_DUMMY_YEAR, END_DUMMY_YEAR),
    Random.nextInt(START_DUMMY_MONTH, END_DUMMY_MONTH),
    Random.nextInt(START_DUMMY_DAY, END_DUMMY_DAY),
    Random.nextInt(START_DUMMY_HOUR, END_DUMMY_HOUR),
    Random.nextInt(START_DUMMY_MINUTE, END_DUMMY_MINUTE),
    Random.nextInt(START_DUMMY_SECOND, END_DUMMY_SECOND),
    Random.nextInt(START_DUMMY_NANO_SECOND, END_DUMMY_NANO_SECOND),
    ZoneId.systemDefault()
).toEpochSecond()