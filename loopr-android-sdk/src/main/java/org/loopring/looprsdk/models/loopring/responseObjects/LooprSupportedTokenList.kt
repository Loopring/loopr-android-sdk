package org.loopring.looprsdk.models.loopring.responseObjects

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import com.google.gson.annotations.SerializedName


import java.lang.reflect.Type

class LooprSupportedTokenList(
        override var id: Int? = null, override var jsonrpc: String? = null
) : LooprResponse {

    /**
     * A list of [LooprSupportedToken] objects with token support data
     */
    @SerializedName("result")
    var tokens: ArrayList<LooprSupportedToken>? = null

    /**
     * Custom class deserializer
     */
    class LooprSupportedTokenListDeserializer : JsonDeserializer<LooprSupportedTokenList> {
        @Throws(JsonParseException::class)
        override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): LooprSupportedTokenList? {
            if (json.isJsonNull || json.isJsonPrimitive) {
                return null
            } else {
                //throw(Throwable("11"))
                val jsonObj = json.asJsonObject
                val supportedTokenObj = LooprSupportedTokenList()

                LooprResponse.checkForError(jsonObj)
                supportedTokenObj.setIdJsonRPC(jsonObj)

                //TODO - check if this code is enough to handle normally encountered errors
                jsonObj.get("result")?.let {
                    supportedTokenObj.tokens = ArrayList()
                    it.asJsonArray.forEach {
                        supportedTokenObj.tokens?.add(context.deserialize(it, LooprSupportedToken::class.java))
                    }
                }

                return supportedTokenObj
            }
        }

    }

}