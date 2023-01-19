package com.finance.dtos

import javax.validation.constraints.NotBlank

data class LoginRequestDTO (
    var username: @NotBlank String? = null,
    var password: @NotBlank String? = null
)