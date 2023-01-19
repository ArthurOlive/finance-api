package com.finance.repositories

import com.finance.models.Color
import org.springframework.data.repository.CrudRepository

interface ColorRepository : CrudRepository<Color, Long> {
}