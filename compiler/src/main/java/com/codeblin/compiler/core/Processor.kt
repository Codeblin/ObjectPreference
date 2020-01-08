package com.codeblin.compiler.core

import com.codeblin.annotations.Document
import com.codeblin.compiler.utils.KotlinClassBuilder
import com.codeblin.compiler.utils.extractCollectionType
import com.google.auto.service.AutoService
import com.squareup.kotlinpoet.asTypeName
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.Processor
import javax.annotation.processing.RoundEnvironment
import javax.annotation.processing.SupportedSourceVersion
import javax.lang.model.SourceVersion
import javax.lang.model.element.TypeElement
import javax.tools.Diagnostic

@AutoService(Processor::class)
@SupportedSourceVersion(SourceVersion.RELEASE_8)
class SharedPrefProcessor : AbstractProcessor() {

    override fun getSupportedAnnotationTypes(): MutableSet<String> {
        return mutableSetOf(
            Document::class.java.canonicalName
        )
    }

    override fun getSupportedSourceVersion(): SourceVersion {
        return SourceVersion.latest()
    }

    override fun process(
        annotations: MutableSet<out TypeElement>?,
        roundEnv: RoundEnvironment?
    ): Boolean {
        roundEnv?.getElementsAnnotatedWith(Document::class.java)
            ?.forEachIndexed { i, classElement ->
                if(i == 0) KotlinClassBuilder(classElement, processingEnv).generateSharedPrefManager()
                printWarning( classElement.enclosedElements[0].asType().asTypeName().extractCollectionType())
                KotlinClassBuilder(classElement, processingEnv).generate()
            }
        return false
    }

    private fun printWarning(message: String) {
        processingEnv.messager.printMessage(Diagnostic.Kind.WARNING, "Process Info: $message")
    }
}