package com.codeblin.compiler.utils

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.TypeName
import javax.annotation.processing.ProcessingEnvironment
import javax.tools.Diagnostic

fun TypeName.getKotlinType() = when {
    this.toString() == "java.lang.String" -> ClassName("kotlin", "String")
    this.toString().contains("java.util.List") -> ClassName("kotlin.collections", "List")
    else -> this
}

fun String.getKotlinType() = when {
    this == "java.lang.String" -> ClassName("kotlin", "String").toString()
    this.contains("java.util.List") -> ClassName("kotlin.collections", "List").toString()
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
        "java.lang.Double",
        "java.lang.Short",
        "java.lang.Byte",
        "java.lang.Character",
        "kotlin.Char",
        "kotlin.Byte",
        "kotlin.Short",
        "kotlin.String",
        "kotlin.Double",
        "kotlin.Boolean",
        "kotlin.Long",
        "kotlin.Float"
    )
    return this.toString() in options
}

fun ProcessingEnvironment.printWarning(message: String) {
    messager.printMessage(Diagnostic.Kind.WARNING, "Process Info: $message")
}