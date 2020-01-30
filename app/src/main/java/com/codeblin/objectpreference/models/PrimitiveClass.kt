package com.codeblin.objectpreference.models

import com.codeblin.annotations.Document

@Document
data class IntegerClass(val value: Int)
@Document
data class StringClass(val value: String)
@Document
data class DoubleClass(val value: Double)
@Document
data class LongClass(val value: Long)
@Document
data class BooleanClass(val value: Boolean)