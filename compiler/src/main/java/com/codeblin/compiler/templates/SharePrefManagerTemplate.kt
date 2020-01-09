package com.codeblin.compiler.templates

object SharePrefManagerTemplate {
    fun getTemplate(packageName: String) =
        "package $packageName\n" +
                "\n" +
                "import android.app.Application\n" +
                "import android.content.Context\n" +
                "import android.content.SharedPreferences\n" +
                "import com.codeblin.annotations.GsonWrapper\n" +
                "\n" +
                "object SharedPrefManager {\n" +
                "    val gsonWrapper = GsonWrapper()\n" +
                "    var sharedPref: SharedPreferences? = null\n" +
                "        private set(value) {\n" +
                "            field = value\n" +
                "        }\n" +
                "\n" +
                "    fun initialize(app: Application) {\n" +
                "        sharedPref = app.getSharedPreferences(\"$packageName\", Context.MODE_PRIVATE)\n" +
                "        sharedPref?.edit()?.clear()?.apply()\n" +
                "    }\n" +
                "\n" +
                "    fun clear(){\n" +
                "        sharedPref?.edit()?.clear()?.apply()\n" +
                "    }\n" +
                "\n" +
                "    fun save(key: String, value: Any) {\n" +
                "        if (sharedPref == null) return\n" +
                "        when (value) {\n" +
                "            is String -> sharedPref?.edit()?.putString(key, value)?.apply()\n" +
                "            is Int -> sharedPref?.edit()?.putInt(key, value)?.apply()\n" +
                "            is Float -> sharedPref?.edit()?.putFloat(key, value)?.apply()\n" +
                "            is Boolean -> sharedPref?.edit()?.putBoolean(key, value)?.apply()\n" +
                "            is Long -> sharedPref?.edit()?.putLong(key, value)?.apply()\n" +
                "            else -> throw InvalidClassException()\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    inline fun <reified T> saveObject(key: String, value: T) = sharedPref?.edit()?.putString(key, gsonWrapper.getJsonString<T>(value))?.apply()\n" +
                "\n" +
                "    fun delete(key: String) = sharedPref?.edit()?.remove(key)?.apply()\n" +
                "\n" +
                "    inline fun <reified T> getObject(key: String): T {\n" +
                "        val value = get<String>(key)\n" +
                "        return gsonWrapper.getObject<T>(value)\n" +
                "    }\n" +
                "\n" +
                "    inline fun <reified T> getObjectList(key: String): List<T> {\n" +
                "        val value = get<String>(key)\n" +
                "        val list = gsonWrapper.getList<T>(value)\n" +
                "        return list ?: listOf()\n" +
                "    }\n" +
                "\n" +
                "    inline fun <reified T> get(key: String): T {\n" +
                "        return when (T::class) {\n" +
                "            String::class -> sharedPref?.getString(key, \"\") as T\n" +
                "            Int::class -> sharedPref?.getInt(key, -1) as T\n" +
                "            Float::class -> sharedPref?.getFloat(key, -1f) as T\n" +
                "            Boolean::class -> sharedPref?.getBoolean(key, false) as T\n" +
                "            Long::class -> sharedPref?.getLong(key, -1) as T\n" +
                "            else -> throw InvalidClassException()\n" +
                "        }\n" +
                "    }\n" +
                "}\n" +
                "\n" +
                "class InvalidClassException : Exception()"
}