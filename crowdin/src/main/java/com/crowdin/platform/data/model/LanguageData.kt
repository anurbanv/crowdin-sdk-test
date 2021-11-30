package com.crowdin.platform.data.model

import com.crowdin.platform.util.convertToJson

internal class LanguageData(var language: String) {

    constructor() : this("")

    var resources: MutableList<StringData> = mutableListOf()
    var arrays: MutableList<ArrayData> = mutableListOf()
    var plurals: MutableList<PluralData> = mutableListOf()

    fun updateResources(languageData: LanguageData) {
        when {
            languageData.resources.isNotEmpty() -> resources = languageData.resources
            languageData.arrays.isNotEmpty() -> arrays = languageData.arrays
            languageData.plurals.isNotEmpty() -> plurals = languageData.plurals
        }
    }

    fun addNewResources(languageData: LanguageData) {
        resources.addAll(languageData.resources)
        arrays.addAll(languageData.arrays)
        plurals.addAll(languageData.plurals)
    }

    override fun toString(): String {
        return convertToJson(this)
    }
}
