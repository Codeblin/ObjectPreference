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
                "class ${className}StoreModel() {\n" +
                "    var value: $className? = null\n" +
                "\n" +
                "    constructor(value: $className) : this() {\n" +
                "        this.value = value\n" +
                "    }\n" +
                "\n" +
                "    fun save() {\n" +
                "        $packageName.SharedPrefManager.saveObject<$className>(\n" +
                "            \"$className\",\n" +
                "            value\n" +
                "        )\n" +
                "    }\n" +
                "\n" +
                "    fun get(): $className =\n" +
                "        $packageName.SharedPrefManager.getObject<$className>(\n" +
                "            \"$className\"\n" +
                "        )\n" +
                "\n" +
                "    fun delete() {\n" +
                "        $packageName.SharedPrefManager.delete(\"$className\")\n" +
                "    }\n" +
                "}"
}