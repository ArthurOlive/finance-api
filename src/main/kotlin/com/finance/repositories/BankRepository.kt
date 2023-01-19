package com.finance.repositories

import com.finance.models.Bank
import org.springframework.data.repository.CrudRepository

interface BankRepository : CrudRepository<Bank, Long> {
}