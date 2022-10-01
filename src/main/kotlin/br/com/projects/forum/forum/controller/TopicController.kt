package br.com.projects.forum.forum.controller

import br.com.projects.forum.forum.dto.NewTopicForm
import br.com.projects.forum.forum.dto.TopicDTO
import br.com.projects.forum.forum.dto.UpdateTopicForm
import br.com.projects.forum.forum.service.TopicService
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping("/topics")
class TopicController(private val service: TopicService) {

    @GetMapping
    @Cacheable("topics")
    fun list(
        @RequestParam(required = false) courseName: String?,
        @PageableDefault(size = 5, sort = ["creationDate"], direction = Sort.Direction.DESC) pagination: Pageable
    ): Page<TopicDTO> {
        return service.list(courseName, pagination)
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): TopicDTO =
        service.findById(id)

    @PostMapping
    @Transactional
    @CacheEvict(value = ["topics"], allEntries = true)
    fun register(
        @RequestBody @Valid dto: NewTopicForm,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<TopicDTO>{
        val topicDto = service.register(dto)
        val uri = uriBuilder.path("/topic/${topicDto.id}").build().toUri()
        return ResponseEntity.created(uri).body(topicDto)
    }

    @PutMapping
    @Transactional
    @CacheEvict(value = ["topics"], allEntries = true)
    fun update(@RequestBody @Valid form: UpdateTopicForm): ResponseEntity<TopicDTO>{
        val topicDto = service.update(form)
        return ResponseEntity.ok(topicDto)
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    @CacheEvict(value = ["topics"], allEntries = true)
    fun delete(@PathVariable id: Long){
        service.delete(id)
    }


}