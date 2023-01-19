package com.finance.repositories

import com.finance.models.Role
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.Optional
@Repository
interface RoleRepository : CrudRepository<Role, Long> {

    fun getByName(name : String) : Optional<Role>

}