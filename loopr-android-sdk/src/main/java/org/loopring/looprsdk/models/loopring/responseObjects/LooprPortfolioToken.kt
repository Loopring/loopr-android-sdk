package org.loopring.looprsdk.models.loopring.responseObjects

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import com.google.gson.annotations.SerializedName

import java.lang.reflect.Type
import java.math.BigInteger

class LooprPortfolioToken {

    /**
     * The symbol of the token
     * Example output - "LRC"
     */
    @SerializedName("token")
    var token: String? = null

    /**
     * Amount of [token] owned
     * Example output - 9782200000000000000000
     */
    var amount: BigInteger?
        get() {
            return mAmount?.let { BigInteger(mAmount) }
        }
        set(value) {
            mAmount = value?.toString()
        }

    private var mAmount: String? = null

    /**
     * Percentage of portfolio //TODO - confirm this statement
     * Example output - "2.35"
     */
    @SerializedName("percentage")
    var percentage: String? = null

    /**
     * Custom class deserializer
     */
    class LooprPortfolioTokenDeserializer : JsonDeserializer<LooprPortfolioToken> {
        @Throws(JsonParseException::class)
        override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): LooprPortfolioToken? {
            if (json.isJsonNull || json.isJsonPrimitive) {
                return null
            } else {
                val priceQuote = LooprPortfolioToken()
                val priceQuoteJsonObject = json.asJsonObject

                //TODO - check if this code is enough to handle normally encountered errors
                priceQuoteJsonObject.get("token")?.let {
                    priceQuote.token = it.asString
                }

                priceQuoteJsonObject.get("amount")?.let {
                    priceQuote.mAmount = it.asString
                }

                priceQuoteJsonObject.get("percentage")?.let {
                    priceQuote.percentage = it.asString
                }


                return priceQuote
            }
        }

    }
}