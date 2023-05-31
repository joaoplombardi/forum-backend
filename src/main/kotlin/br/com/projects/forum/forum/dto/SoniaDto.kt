package br.com.projects.forum.forum.dto

import br.com.projects.forum.forum.model.TaxAndQuantity
import java.math.BigDecimal

data class SoniaDto(
    val result: MutableList<TaxAndQuantity>
)
