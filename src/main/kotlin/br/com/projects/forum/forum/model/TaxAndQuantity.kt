package br.com.projects.forum.forum.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

data class TaxAndQuantity(
    @JsonProperty("taxa")
    val tax: BigDecimal,
    @JsonProperty("quantidade")
    val quantity: Int
)
