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

inline fun <reified T : Any> fixture(
    repeatSize: Int = 1,
    noinline overrides: () -> Map<String, Any?> = { emptyMap() }
): T = buildDummyClass(
    kotlinClass = T::class,
    constructors = T::class.constructors,
    repeatSize = repeatSize,
    overrides = overrides
)