package br.com.projects.forum.forum.service

import br.com.projects.forum.forum.model.User
import br.com.projects.forum.forum.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserService(private val repository: UserRepository): UserDetailsService {

    fun findById(id: Long): User =
        repository.getById(id)

    override fun loadUserByUsername(email: String?): UserDetails {
        val user = repository.findByEmail(email) ?: throw RuntimeException()
        return UserDetail(user)
    }

}
