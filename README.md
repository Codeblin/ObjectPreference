![Bintray](https://img.shields.io/bintray/v/stamatisstiliatis/ObjectPreferencesCompiler/ObjectPreferencesCompiler) 
# ObjectPreference
Fast and easy Shared Preferences managing with object mapping annotations for simple or complex class structures


### How to use

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

* Now initialize `SharedPrefManager` at your Application class

```kotlin
SharedPrefManager.initialize(this)
```
**This will generate a class that you will be using for save/get/delete operations with the suffix 'StoreModel' and a manager that handles `SharedPreferences`**

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

#### Operations

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
