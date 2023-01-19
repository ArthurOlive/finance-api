package com.finance.repositories

import com.finance.models.Card
import org.springframework.data.repository.CrudRepository

interface CardRepository : CrudRepository<Card, Long> {
}