package br.com.projects.forum.forum.mapper

import br.com.projects.forum.forum.dto.TopicDTO
import br.com.projects.forum.forum.model.Topic
import org.springframework.stereotype.Component

@Component
class TopicoDtoMapper: Mapper<Topic, TopicDTO> {
    override fun map(t: Topic): TopicDTO {
        return TopicDTO(
            id = t.id,
            title = t.title,
            message = t.message,
            creationDate = t.creationDate,
            status = t.status
        )
    }
}