package space.ichain.main.topic.services

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import space.ichain.main.core.exceptions.EntityNotFoundException
import space.ichain.main.topic.entities.Topic
import space.ichain.main.topic.repositories.TopicRepository
import space.ichain.main.topic.vo.CreateTopicRq
import space.ichain.main.topic.vo.TopicVO
import space.ichain.main.topic.vo.TopicWithoutVerbVO

@Service
class TopicServiceImpl {

    private val log = LoggerFactory.getLogger(TopicServiceImpl::class.java)

    @Autowired
    lateinit var topicRepository: TopicRepository

    @Transactional(readOnly = true)
    fun getAll(): List<TopicVO> {
        return topicRepository.findAllByApprovedIsTrue().map(::toTopicVO)
    }

    fun getFixedTopics(): List<TopicWithoutVerbVO> {
        return topicRepository.findAllByApprovedIsTrueAndFixedIsTrue().map(::toTopicWithoutVerbVO)
    }

    @Throws(EntityNotFoundException::class)
    @Transactional(readOnly = true)
    fun getById(id: Long): TopicVO {
        return topicRepository.findById(id).map(::toTopicVO).orElseThrow { throw EntityNotFoundException("Topic with $id not found.") }
    }

    fun createTopic(createTopicRq: CreateTopicRq): TopicVO {
        val id = topicRepository.save(Topic().apply {
            title = createTopicRq.title
            description = createTopicRq.description
            previewImg = createTopicRq.previewImg
            html = createTopicRq.html
            keywords = createTopicRq.keywords
            approved = createTopicRq.approved
        }).id ?: throw IllegalArgumentException("Bad id returned.")

        log.debug("Created entity $id")
        return getById(id)
    }

    fun updateTopic(id: Long, updateTopicRq: CreateTopicRq): TopicVO {
        val id = topicRepository.save(topicRepository.findById(id).get().apply {
            title = updateTopicRq.title
            description = updateTopicRq.description
            previewImg = updateTopicRq.previewImg
            html = updateTopicRq.html
            keywords = updateTopicRq.keywords
            approved = updateTopicRq.approved
        }).id ?: throw IllegalArgumentException("Bad id returned.")

        log.debug("Created entity $id")
        return getById(id)
    }

    fun delete(id: Long) {
        topicRepository.deleteById(id)
        log.debug("Deleted topic entity $id")
    }

    @Throws(EntityNotFoundException::class)
    fun fixedTopic(id: Long, fixed: Boolean): TopicVO {
        val topic = topicRepository.findByIdOrNull(id) ?: throw EntityNotFoundException("Topic with $id not found.")

        val id = topicRepository.save(topic.apply {
            this.fixed = fixed
        }).id ?: throw IllegalArgumentException("Bad id returned.")

        log.debug("Fixed entity $id")
        return getById(id)
    }

    @Throws(EntityNotFoundException::class)
    fun addAddons(id: Long, addonId: Long): TopicVO {
        val topic = topicRepository.findByIdOrNull(id) ?: throw EntityNotFoundException("Topic with $id not found.")
        val addonTopic = topicRepository.findByIdOrNull(addonId)
                ?: throw EntityNotFoundException("Topic with $addonId not found.")

        val id = topicRepository.save(topic.apply {
            addons = this.addons + addonTopic
        }).id ?: throw IllegalArgumentException("Bad id returned.")

        log.debug("Add addons entity $addonId to $id")
        return getById(id)
    }

    @Throws(EntityNotFoundException::class)
    fun addExtensions(id: Long, extensionId: Long): TopicVO {
        val topic = topicRepository.findByIdOrNull(id) ?: throw EntityNotFoundException("Topic with $id not found.")
        val extensionTopic = topicRepository.findByIdOrNull(extensionId)
                ?: throw EntityNotFoundException("Topic with $extensionId not found.")

        val id = topicRepository.save(topic.apply {
            extensions = this.extensions + extensionTopic
        }).id ?: throw IllegalArgumentException("Bad id returned.")

        log.debug("Add extension entity $extensionTopic to $id")
        return getById(id)
    }

    @Throws(EntityNotFoundException::class)
    fun addReplacements(id: Long, replacementId: Long): TopicVO {
        val topic = topicRepository.findByIdOrNull(id) ?: throw EntityNotFoundException("Topic with $id not found.")
        val replacementTopic = topicRepository.findByIdOrNull(replacementId)
                ?: throw EntityNotFoundException("Topic with $replacementId not found.")

        val id = topicRepository.save(topic.apply {
            replacements = this.replacements + replacementTopic
        }).id ?: throw IllegalArgumentException("Bad id returned.")

        log.debug("Add replacements entity $replacementTopic to $id")
        return getById(id)
    }

    @Throws(EntityNotFoundException::class)
    fun addSimplifications(id: Long, simplificationId: Long): TopicVO {
        val topic = topicRepository.findByIdOrNull(id) ?: throw EntityNotFoundException("Topic with $id not found.")
        val simplificationTopic = topicRepository.findByIdOrNull(simplificationId)
                ?: throw EntityNotFoundException("Topic with $simplificationId not found.")

        val id = topicRepository.save(topic.apply {
            simplifications = this.simplifications + simplificationTopic
        }).id ?: throw IllegalArgumentException("Bad id returned.")

        log.debug("Add simplifications entity $simplificationTopic to $id")
        return getById(id)
    }

    private fun toTopicVO(topic: Topic): TopicVO {
        return TopicVO.fromData(topic)
    }

    private fun toTopicWithoutVerbVO(topic: Topic): TopicWithoutVerbVO {
        return TopicWithoutVerbVO.fromData(topic)
    }

}
