package br.com.projects.forum.forum.mapper

import br.com.projects.forum.forum.dto.TopicDTO
import br.com.projects.forum.forum.model.Topic
import org.springframework.stereotype.Component

@Component
class TopicoDtoMapper: Mapper<Topic, TopicDTO> {
    override fun map(topic: Topic): TopicDTO {
        return TopicDTO(
            id = topic.id,
            title = topic.title,
            message = topic.message,
            creationDate = topic.creationDate,
            status = topic.status
        )
    }
}