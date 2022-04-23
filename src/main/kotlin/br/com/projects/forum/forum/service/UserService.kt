package br.com.projects.forum.forum.service

import br.com.projects.forum.forum.model.User
import org.springframework.stereotype.Service

@Service
class UserService(var users: List<User>) {
    init {
        val user = User(
            id = 1,
            name = "João Pedro",
            email = "joaozin_zikadabal4d4@gmail.com"
        )
        users = listOf(user)
    }

    fun findById(id: Long): User =
        users.stream().filter{ it.id == id }.findFirst().get()
}
