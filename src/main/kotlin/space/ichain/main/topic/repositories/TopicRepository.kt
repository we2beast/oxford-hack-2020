package space.ichain.main.topic.repositories

import org.springframework.data.neo4j.repository.Neo4jRepository
import space.ichain.main.topic.entities.Topic

interface TopicRepository : Neo4jRepository<Topic, Long> {

    fun findAllByApprovedIsTrue(): List<Topic>
    fun findAllByApprovedIsTrueAndFixedIsTrue(): List<Topic>

}
