package br.com.projects.forum.forum.config

import br.com.projects.forum.forum.security.JWTAuthenticationFilter
import br.com.projects.forum.forum.security.JWTLoginFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.filter.OncePerRequestFilter

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
        antMatchers(HttpMethod.POST,"/login")?.permitAll()?.
        anyRequest()?.
        authenticated()

        http?.addFilterBefore(JWTLoginFilter(authManager = authenticationManager(), jwtUtil = jwtUtil), UsernamePasswordAuthenticationFilter().javaClass)
        http?.sessionManagement()?.sessionCreationPolicy(SessionCreationPolicy.STATELESS)?.and()?.csrf()?.disable()
        http?.addFilterBefore(JWTAuthenticationFilter(jwtUtil = jwtUtil), UsernamePasswordAuthenticationFilter::class.java)
    }

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.
        userDetailsService(userDetailsService)?.
        passwordEncoder(bCryptPasswordEncoder())
    }

}