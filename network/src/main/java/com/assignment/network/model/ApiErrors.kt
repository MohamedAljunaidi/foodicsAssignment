package com.assignment.network.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ApiErrors(
    @SerializedName("error") val message: String? = null,
)
