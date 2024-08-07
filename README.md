<h1 align="center">
Tool for generating dummy values for unit test in android
</h1>

<br>

<div align="center">
<a name="code_factor" href="https://www.codefactor.io/repository/github/mina-mikhail/kotlinFixture">
  <img src="https://www.codefactor.io/repository/github/mina-mikhail/kotlinFixture/badge?style=for-the-badge">
</a>  
<a name="platform">
  <img src="https://img.shields.io/badge/Platform-Android-success?style=for-the-badge">
</a>
<a name="language">
  <img src="https://img.shields.io/badge/Language-Kotlin---?style=for-the-badge">
</a>
<a name="stars">
  <img src="https://img.shields.io/github/stars/Mina-Mikhail/kotlinFixture?style=for-the-badge"></a>
<a name="forks">
  <img src="https://img.shields.io/github/forks/Mina-Mikhail/kotlinFixture?logoColor=green&style=for-the-badge">
</a>
<a name="contributions">
  <img src="https://img.shields.io/github/contributors/Mina-Mikhail/kotlinFixture?logoColor=green&style=for-the-badge">
</a>
<a name="last_commit">
  <img src="https://img.shields.io/github/last-commit/Mina-Mikhail/kotlinFixture?style=for-the-badge">
</a>
<a name="issues">
  <img src="https://img.shields.io/github/issues-raw/Mina-Mikhail/kotlinFixture?style=for-the-badge">
</a>
<a name="license">
  <img src="https://img.shields.io/github/license/sadanandpai/javascript-code-challenges?style=for-the-badge">
</a>
<a name="linked_in" href="https://www.linkedin.com/in/minasamirgerges/">
  <img src="https://img.shields.io/badge/Support-Recommed%2FEndorse%20me%20on%20Linkedin-yellow?style=for-the-badge&logo=linkedin" alt="Recommend me on LinkedIn"/>
</a>
</div>

<br>
<br>
<br>


:point_right: Getting Started:
-----------------
Include the following dependency in your `build.gradle.kts` file:

```kotlin
testImplementation("io.github.mina-mikhail.kotlinFixture:kotlinFixture:<latest-version>")
```

You can check the latest version from [HERE](https://central.sonatype.com/artifact/io.github.mina-mikhail.kotlinFixture/kotlinFixture)


:point_right: How to Use:
-----------------
You can easily generate dummy values using `fixture<DATA_TYPE>()` as the following:

```kotlin
// Creates a dummy string
val dummyString = fixture<String>()

// Create a dummy date
val dummyDate = fixture<Date>()

// Create a dummy file
val dummyFile = fixture<File>()
```

<br>

Also you can generate dummy iterable values using `fixtureITERABLE_TYPE<DATA_TYPE>()` as the following:

```kotlin
// Creates a list of dummy strings
val dummyStringList = fixtureList<String>()

// Also you can adjust size of your preferred iterable, the default size is : 1
val dummyStringList = fixtureList<Long>(repeatSize = 5)
```

<br>

What about data classes ?!

```kotlin
// Random Data Class for Product Item
data class Product(
    val id: Int,
    val price: Int,
    val quantity: Int
)

// Creates a dummy product item
val product = fixture<Product>()
```

<br>

Need to override some class parameters, no problem it's pretty easy?!

```kotlin
// Creates a dummy product item
val product = fixture<Product> {
    mapOf("price" to 350)
}
```

<br>

Worry about sealed classes ?! let's see what `fixture<>()` can do:

```kotlin
// Random Data Class for Product Item
data class Product(
    val id: Int,
    val price: Int,
    val quantity: Int,
    val productType: ProductType
)
sealed class ProductType {
    data class Electronics(val maxVoltage: Int) : ProductType()
    data object Food : ProductType()
}

// Creates a dummy product item, and fixture will pick random nested sealed class of ProductType
val product = fixture<Product>()
```


:point_right: Supported Types:
-----------------
Here is the supported data types:
| Type | Example | Description |
| --- | --- | --- |
| Boolean | `fixture<Boolean>()` | Creates dummy boolean value |
| Int | `fixture<Int>()` | Creates dummy integer value |
| Long | `fixture<Long>()` | Creates dummy long value |
| Short | `fixture<Short>()` | Creates dummy short value |
| Byte | `fixture<Byte>()` | Creates dummy byte value |
| Float | `fixture<Float>()` | Creates dummy float value |
| Double | `fixture<Double>()` | Creates dummy double value |
| Char | `fixture<Char>()` | Creates dummy character value |
| String | `fixture<String>()` | Creates dummy string value |
| BigDecimal | `fixture<BigDecimal>()` | Creates dummy big decimal value |
| Date | `fixture<Date>()` | Creates dummy date value |
| LocalDate | `fixture<LocalDate>()` | Creates dummy local date value |
| File | `fixture<File>()` | Creates dummy file value |
| Interface | `fixture<INTERFACE>()` | Creates random dummy value of the provided interface implementations |
| Enum | `fixture<ENUM>()` | Creates random dummy value of the provided enum class |
| Sealed Class | `fixture<SEALED_CLASS>()` | Creates random dummy value of the sealed class nested classes |
| Java Model Class | `fixture<JAVA_CLASS>()` | Creates dummy java class instance using primary constructor |


:point_right: Supported Iterable Types:
-----------------
Here is the supported iterable data types:
| Type | Example | Description |
| --- | --- | --- |
| List | `fixtureList<DATA_TYPE>()` | Creates list of dummy values of provided data type |
| Array | `fixtureArray<DATA_TYPE>()` | Creates array of dummy values of provided data type |
| Set | `fixtureSet<DATA_TYPE>()` | Creates set of dummy values of provided data type |
| Map | `fixtureMap<KEY_TYPE,VALUE_TYPE>()` | Creates map of dummy keys of provided type and dummy values of provided type |


:point_right: Modules:
-----------------
- [kotlinFixture](https://github.com/Mina-Mikhail/kotlinFixture/tree/main/kotlinFixture) : Kotlin Fixture Library Module.


:point_right: Code Style:
-----------
- Following official kotlin code style.


:point_right: Local Development:
-----------
- Here are some useful Gradle commands for executing this example:
  - `./gradlew clean` - Deletes build directory.


:point_right: Have an issue?:
-----------
- If you faced any issue with the library or if you want to add support for missing data type, please open an issue with it to help me make this library supports more data types.


:point_right: Contributing to Project:
-----------
- Just fork this repository and contribute back using pull requests.
- Any contributions, large or small, major features, bug fixes, are welcomed and appreciated but
  will be thoroughly reviewed.


:point_right: Find this project useful ? :heart:
-----------
- Support it by clicking the :star: button on the upper right of this page. :v:


:point_right: Stargazers: :star:
-----------
[![Stargazers repo roster for @sadanandpai/javascript-code-challenges](https://reporoster.com/stars/Mina-Mikhail/kotlinFixture)](https://github.com/Mina-Mikhail/kotlinFixture/stargazers)


:point_right: Forkers: :hammer_and_pick:
-----------
[![Forkers repo roster for @sadanandpai/javascript-code-challenges](https://reporoster.com/forks/Mina-Mikhail/kotlinFixture)](https://github.com/Mina-Mikhail/kotlinFixture/network/members)


:point_right: Donation:
-----------
If this project help you reduce time to develop, you can give me a cup of coffee :)

<a href="https://www.buymeacoffee.com/mina.mikhail" target="_blank"><img src="https://bmc-cdn.nyc3.digitaloceanspaces.com/BMC-button-images/custom_images/orange_img.png" alt="Buy Me A Coffee" style="height: auto !important;width: auto !important;" ></a>

<br>
<br>

:warning: License:
--------

```
   Copyright (C) 2024 MINA MIKHAIL PRIVATE LIMITED

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```