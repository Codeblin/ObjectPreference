package com.codeblin.compiler.templates

interface Template {
    fun getTemplate(
        packageName: String = "",
        className: String = "",
        type: String = "",
        listType: String = ""
    ): String
}