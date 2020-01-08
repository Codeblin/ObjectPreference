package com.codeblin.compiler.utils

import com.codeblin.compiler.templates.ListClassTemplate
import com.codeblin.compiler.templates.ObjectClassTemplate
import com.codeblin.compiler.templates.SharePrefManagerTemplate
import com.codeblin.compiler.templates.SimpleClassTemplate
import com.squareup.kotlinpoet.asTypeName
import java.io.File
import javax.annotation.processing.ProcessingEnvironment
import javax.lang.model.element.Element

class KotlinClassBuilder(
    private val element: Element,
    private val processingEnv: ProcessingEnvironment
) {

    companion object {
        const val KAPT_KOTLIN_GENERATED_OPTION_NAME = "kapt.kotlin.generated"
    }

    fun generateSharedPrefManager(){
        val packageName = processingEnv.elementUtils.getPackageOf(element).toString()
        val fileContent = SharePrefManagerTemplate.getTemplate(packageName)
        val kaptKotlinGeneratedDir = processingEnv.options[KAPT_KOTLIN_GENERATED_OPTION_NAME]
        val file = File(kaptKotlinGeneratedDir, "SharedPrefManager.kt")
        file.writeText(fileContent)
    }

    fun generate() {
        val className = "${element.simpleName}"
        val packageName = processingEnv.elementUtils.getPackageOf(element).toString()
        val fileName = "${className}StoreModel"
        val value = element.enclosedElements[0]
        val kaptKotlinGeneratedDir = processingEnv.options[KAPT_KOTLIN_GENERATED_OPTION_NAME]
        val file = File(kaptKotlinGeneratedDir, "$fileName.kt")

        val fileContent = if (value.asType().asTypeName().isPrimitive()) {
            SimpleClassTemplate.getTemplate(
                packageName,
                className,
                value.asType().asTypeName().getKotlinType().toString()
            )
        } else {
            if (value.asType().asTypeName().isCollection()) {
                ListClassTemplate.getTemplate(
                    packageName,
                    className,
                    "${value.asType().asTypeName().getKotlinType()}<${value.asType().asTypeName().extractCollectionType()}>",
                    value.asType().asTypeName().extractCollectionType()
                )
            } else {
                ObjectClassTemplate.getTemplate(
                    packageName,
                    className,
                    value.asType().asTypeName().getKotlinType().toString()
                )
            }

        }
        file.writeText(fileContent)
    }
}