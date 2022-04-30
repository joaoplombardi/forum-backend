package br.com.projects.forum.forum.exception

import java.lang.RuntimeException


class NotFoundException(message: String?): RuntimeException(message) {
    
}