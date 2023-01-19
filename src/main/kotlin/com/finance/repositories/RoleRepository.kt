package com.finance.repositories

import com.finance.models.Role
import org.springframework.data.repository.CrudRepository
import java.util.Optional

interface RoleRepository : CrudRepository<Role, Long> {

    fun getByName(name : String) : Optional<Role>

}