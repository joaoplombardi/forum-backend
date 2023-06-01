package br.com.projects.forum.forum.config

import br.com.projects.forum.forum.security.JWTLoginFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfiguration(
    private val userDetailsService: UserDetailsService,
    private val jwtUtil: JWTUtil
) : WebSecurityConfigurerAdapter() {

    @Bean
    fun bCryptPasswordEncoder() = BCryptPasswordEncoder()

    override fun configure(http: HttpSecurity?) {
        http?.
        authorizeRequests()?.
//        antMatchers("/topics")?.hasAuthority("READ_WRITE")?.
        antMatchers("/login")?.permitAll()?.
        anyRequest()?.
        authenticated()?.
        and()

        http?.addFilterBefore(JWTLoginFilter(authManager = authenticationManager(), jwtUtil = jwtUtil), UsernamePasswordAuthenticationFilter().javaClass)

        http?.sessionManagement()?.
        sessionCreationPolicy(SessionCreationPolicy.ALWAYS)?.
        and()?.
        formLogin()?.
        disable()?.
        csrf()?.
        disable()?.
        httpBasic()
    }

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.
        userDetailsService(userDetailsService)?.
        passwordEncoder(bCryptPasswordEncoder())
    }

}