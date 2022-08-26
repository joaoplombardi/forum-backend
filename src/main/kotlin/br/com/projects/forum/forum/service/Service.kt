package br.com.projects.forum.forum.service

import br.com.projects.forum.forum.dto.*
import org.springframework.data.jpa.repository.JpaRepository

abstract class Service<T, U>(
    private val repository: JpaRepository<T, U>
) {
    abstract fun list(): List<DTO>

    abstract fun findById(id: U): DTO

    abstract fun register(form: Form): DTO

    abstract fun update(form: Form): DTO

    fun delete(id: U) {
        repository.deleteById(id)
    }
}