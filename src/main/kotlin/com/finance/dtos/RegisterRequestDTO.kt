package com.finance.dtos

import javax.validation.constraints.NotBlank

class RegisterRequestDTO (
    var username: @NotBlank String? = null,
    var password: @NotBlank String? = null
)