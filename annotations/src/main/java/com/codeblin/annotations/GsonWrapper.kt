package com.codeblin.annotations

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GsonWrapper {
    val gson = Gson()

    inline fun <reified T>getJsonString(value: T?) = gson.toJson(value, T::class.java)

    inline fun <reified T>getObject(value: String) = gson.fromJson(value, T::class.java)

    inline fun <reified T>getList(value: String) = gson.fromJson<List<T>>(value, getItemType<T>())

    inline fun <T>getItemType() = object : TypeToken<List<T>>() {}.type
}