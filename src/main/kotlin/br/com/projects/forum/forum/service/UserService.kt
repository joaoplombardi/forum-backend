package br.com.projects.forum.forum.service

import br.com.projects.forum.forum.model.User
import br.com.projects.forum.forum.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val repository: UserRepository) {

    fun findById(id: Long): User =
        repository.getById(id)
}
