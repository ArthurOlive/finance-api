package com.finance.controllers

import com.finance.dtos.AccountDTO
import com.finance.models.Account
import com.finance.repositories.AccountRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AccountController (
        @Autowired
        val accountRepository: AccountRepository
) {

    @GetMapping("accounts")
    fun getAccounts(authentication : Authentication) : ResponseEntity<String> {
        return ResponseEntity.ok("Deu certo")
    }

    @PostMapping("accounts")
    fun postAccount(@RequestBody account : Account) : ResponseEntity<String> {
        accountRepository.save(account)

        return ResponseEntity.ok("Ok")
    }

}