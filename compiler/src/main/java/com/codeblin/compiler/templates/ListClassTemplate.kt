package com.codeblin.compiler.templates

object ListClassTemplate : Template {
    override fun getTemplate(
        packageName: String,
        className: String,
        type: String,
        listType: String
    ) =
        "package $packageName\n" +
                "\n" +
                "class ${className}StoreModel() {\n" +
                "    var value: $type? = null\n" +
                "\n" +
                "    constructor(value: $type) : this() {\n" +
                "        this.value = value\n" +
                "    }\n" +
                "\n" +
                "    fun save() {\n" +
                "        $packageName.SharedPrefManager.saveObject<$type>(\n" +
                "            \"$className\",\n" +
                "            value\n" +
                "        )\n" +
                "    }\n" +
                "\n" +
                "    fun get(): $type =\n" +
                "        $packageName.SharedPrefManager.getObjectList<$listType>(\n" +
                "            \"$className\"\n" +
                "        )\n" +
                "\n" +
                "    fun delete() {\n" +
                "        $packageName.SharedPrefManager.delete(\"$className\")\n" +
                "    }\n" +
                "}"
}