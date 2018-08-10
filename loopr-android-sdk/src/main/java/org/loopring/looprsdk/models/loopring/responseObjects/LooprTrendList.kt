package org.loopring.looprsdk.models.loopring.responseObjects

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import com.google.gson.annotations.SerializedName


import java.lang.reflect.Type

class LooprTrendList(
        override var id: Int? = null, override var jsonrpc: String? = null
) : LooprResponse {

    /**
     * A list of [LooprTrend] objects with trend data
     */
    var trends: ArrayList<LooprTrend>? = null

    /**
     * Custom class deserializer
     */
    class LooprTrendListDeserializer : JsonDeserializer<LooprTrendList> {
        @Throws(JsonParseException::class)
        override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): LooprTrendList? {
            if (json.isJsonNull || json.isJsonPrimitive) {
                return null
            } else {
                val jsonObj = json.asJsonObject
                val trendsListObj = LooprTrendList()

                LooprResponse.checkForError(jsonObj)
                trendsListObj.setIdJsonRPC(jsonObj)

                //TODO - check if this code is enough to handle normally encountered errors
                jsonObj.get("result")?.let {
                    val trendsJsonArray = it.asJsonArray

                    trendsListObj.trends = ArrayList()
                    trendsJsonArray.forEach {
                        trendsListObj.trends?.add(context.deserialize(it, LooprTrend::class.java))
                    }
                }


                return trendsListObj
            }
        }

    }
}