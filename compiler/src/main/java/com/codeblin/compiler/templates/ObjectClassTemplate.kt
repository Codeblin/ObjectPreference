package com.codeblin.compiler.templates

object ObjectClassTemplate : Template {
    override fun getTemplate(
        packageName: String,
        className: String,
        type: String,
        listType: String
    ): String =
        "package $packageName\n" +
                "\n" +
                "class ${className}StoreModel(\n" +
                "    private val value: $type\n" +
                ") {\n" +
                "    fun save() {\n" +
                "        $packageName.SharedPrefManager.saveObject<$type>(\n" +
                "            \"$className\",\n" +
                "            value\n" +
                "        )\n" +
                "    }\n" +
                "\n" +
                "    fun get(): $type =\n" +
                "        $packageName.SharedPrefManager.getObject<$type>(\n" +
                "            \"$className\"\n" +
                "        )\n" +
                "\n" +
                "    fun delete() {\n" +
                "        $packageName.SharedPrefManager.delete(\"$className\")\n" +
                "    }\n" +
                "}"
}