package com.codeblin.compiler.utils

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.TypeName

fun TypeName.getKotlinType() = when {
    this.toString() == "java.lang.String" -> ClassName("kotlin", "String")
    this.toString().contains("java.util.List") -> ClassName("kotlin.collections", "List")
    else -> this
}

fun TypeName.isCollection() = this.toString().contains("java.util.List")

fun TypeName.extractCollectionType(): String = "<([^}]+)>".toRegex().find(this.toString())?.value
    ?.replace("<", "")?.replace(">", "")
    ?: ""

fun TypeName.isPrimitive(): Boolean {
    val options = setOf(
        "java.lang.String",
        "java.lang.Boolean",
        "java.lang.Long",
        "java.lang.Float",
        "kotlin.String",
        "kotlin.Boolean",
        "kotlin.Long",
        "kotlin.Float"
    )
    return this.toString() in options
}