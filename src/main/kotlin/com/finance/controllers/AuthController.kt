package com.finance.controllers

import com.finance.config.jwt.JwtUtils
import com.finance.dtos.LoginRequestDTO
import com.finance.dtos.RegisterRequestDTO
import com.finance.models.Auth
import com.finance.models.Role
import com.finance.models.UserCustomDetails
import com.finance.models.UserSys
import com.finance.repositories.RoleRepository
import com.finance.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
class AuthController (
        val authenticationManager: AuthenticationManager,
        val userRepository: UserRepository,
        val roleRepository: RoleRepository,
        val encoder: PasswordEncoder,
        val jwtUtils: JwtUtils
){

    @PostMapping("/login")
    fun login(@RequestBody loginRequest : @Valid LoginRequestDTO) : ResponseEntity<*> {

        val authentication = authenticationManager!!.authenticate(
                UsernamePasswordAuthenticationToken
                (loginRequest!!.username, loginRequest.password)
        )
        SecurityContextHolder.getContext().authentication = authentication
        val jwt = jwtUtils!!.generateJwtToken(authentication)

        val userDetails = authentication.principal as UserCustomDetails

        return ResponseEntity.ok("Bearer " + jwt)
    }

    @PostMapping("/register")
    fun register(@RequestBody registerRequest : @Valid RegisterRequestDTO) : ResponseEntity<*> {

        if (userRepository!!.getByUsername(registerRequest.username!!) != null) {
            return ResponseEntity.ok("Error: Username is already taken!")
        }

        val user = userRepository!!.save(
                UserSys(
                        auth = Auth(
                            username = registerRequest.username!! ,
                            password = encoder!!.encode(registerRequest.password),
                        ),
                        role = roleRepository.getByName("USER").orElseThrow()
                )
        )

        return ResponseEntity.ok("Registrado com sucesso")
    }
}