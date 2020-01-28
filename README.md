![Bintray](https://img.shields.io/bintray/v/stamatisstiliatis/ObjectPreferencesCompiler/ObjectPreferencesCompiler) 
[![Android Arsenal]( https://img.shields.io/badge/Android%20Arsenal-ObjectPreference-green.svg?style=flat )]( https://android-arsenal.com/details/1/8030 )
# ObjectPreference
Fast and easy Shared Preferences managing with object mapping annotations for simple or complex class structures


### How to use

* app build.gradle

```gradle
implementation 'com.codeblin.annotations:ObjectPreferences:<latest-version>'
kapt 'com.codeblin.compiler:ObjectPreferencesCompiler:<latest-version>'
```

ObjectPreferences uses java 8 so you need to set compile  options to target Java 8
```
android{
	compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}
```

* First create you model and annotate with `@Document` Annotation

_Example_

``` kotlin
@Document
data class User(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val age: Int,
    val transactions: List<Transaction>
)

data class Transaction(
    val id: Int,
    val date: Date,
    val amount: Double
)
```

* **Build project**. Now initialize `SharedPrefManager` at your Application class

```kotlin
SharedPrefManager.initialize(this)
```

This will generate the class you will be using which is the name of your class with the suffix 'StoreModel'

```kotlin
class UserStoreModel(
    private val value: User
) {
    fun save() {
        com.codeblin.objectpreference.SharedPrefManager.saveObject<User>(
            "User",
            value
        )
    }

    fun get(): User =
        com.codeblin.objectpreference.SharedPrefManager.getObject<User>(
            "User"
        )

    fun delete() {
        com.codeblin.objectpreference.SharedPrefManager.delete("User")
    }
}
```

### :sparkles::sparkles:That's it!:sparkles::sparkles:

#### Features

Use \<your-annotated-class-name\>StoreModel class to operate

* Save 

```kotlin
val user = UserStoreModel(User(..))
user.save()
```
* Get 

```kotlin
user.get()
```
* Delete 

```kotlin
user.delete()
```
* Create an instance with the default empty constructor to reuse your StoreModel across your app 

```kotlin
user = UserStoreModel()
```

* Clear all 

```kotlin
SharedPrefManager.clear()
```
