package com.codeblin.compiler.templates

object SimpleClassTemplate : Template {
    override fun getTemplate(
        packageName: String,
        className: String,
        type: String,
        listType: String
    ) =
        "package $packageName\n" +
                "\n" +
                "class ${className}StoreModel(\n" +
                "    private val value: $type\n" +
                ") {\n" +
                "    fun save() = $packageName.SharedPrefManager.save(\n" +
                "        \"$className\",\n" +
                "        value\n" +
                "    )\n" +
                "\n" +
                "    fun get(): $type =\n" +
                "        $packageName.SharedPrefManager.get<$type>(\"$className\")\n" +
                "\n" +
                "    fun delete() = $packageName.SharedPrefManager.delete(\"$className\")\n" +
                "}"
}