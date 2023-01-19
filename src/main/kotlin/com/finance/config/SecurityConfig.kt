package com.finance.config

import com.finance.config.jwt.AuthEntryPointJwt
import com.finance.config.jwt.AuthTokenFilter
import com.finance.services.UserCustomDetailService
import com.finance.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.servlet.config.annotation.EnableWebMvc


@Configuration
@EnableWebMvc
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfig (
        private val unauthorizedHandler: AuthEntryPointJwt,
        val userDetailsCustomService: UserCustomDetailService
) : WebSecurityConfigurerAdapter () {

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.cors().and().csrf().disable().exceptionHandling().
        authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().
                sessionCreationPolicy(SessionCreationPolicy.STATELESS).
                and().authorizeRequests()
                .antMatchers(HttpMethod.POST,"/api/auth/login").permitAll()
                .antMatchers(HttpMethod.POST,"/api/auth/register").permitAll()
                .antMatchers(HttpMethod.GET,"/api/alive").permitAll()
                .anyRequest().authenticated()
        http.addFilterBefore(
                authenticationJwtTokenFilter(),
                UsernamePasswordAuthenticationFilter::class.java
        )
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun authenticationJwtTokenFilter(): AuthTokenFilter {
        return AuthTokenFilter()
    }

    @Throws(Exception::class)
    public override fun configure
            (authenticationManagerBuilder: AuthenticationManagerBuilder) {
        authenticationManagerBuilder.userDetailsService(userDetailsCustomService).
        passwordEncoder(passwordEncoder())
    }

    @Bean
    @Throws(Exception::class)
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }
}