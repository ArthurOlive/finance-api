package com.finance

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@SpringBootApplication
class FinanceApplication

fun main(args: Array<String>) {
	runApplication<FinanceApplication>(*args)
}
