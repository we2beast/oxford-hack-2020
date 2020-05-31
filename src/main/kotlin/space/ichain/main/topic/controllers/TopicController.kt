package space.ichain.main.topic.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import space.ichain.main.core.controllers.BaseController
import space.ichain.main.topic.services.TopicServiceImpl
import space.ichain.main.topic.vo.CreateTopicRq
import space.ichain.main.topic.vo.TopicVO
import space.ichain.main.topic.vo.TopicWithoutVerbVO

@RestController
@RequestMapping
class TopicController : BaseController() {

    @Autowired
    lateinit var topicService: TopicServiceImpl

    @GetMapping("/topics")
    fun getTopics(): ResponseEntity<List<TopicVO>> = processServiceExceptions {
        ResponseEntity.ok(topicService.getAll())
    }

    @GetMapping("/topics/fixed")
    fun getFixedTopics(): ResponseEntity<List<TopicWithoutVerbVO>> = processServiceExceptions {
        ResponseEntity.ok(topicService.getFixedTopics())
    }

    @GetMapping("/topic/{id}")
    fun getTopicById(@PathVariable id: String): ResponseEntity<TopicVO> = processServiceExceptions {
        ResponseEntity.ok(topicService.getById(id.toLong()))
    }

    @PostMapping("/topic")
    fun createTopic(@RequestBody topicCreateRq: CreateTopicRq): ResponseEntity<TopicVO> = processServiceExceptions {
        ResponseEntity.ok(topicService.createTopic(topicCreateRq))
    }

    @PutMapping("/topic/{id}")
    fun updateTopic(@PathVariable id: String, @RequestBody topicUpdateRq: CreateTopicRq): ResponseEntity<TopicVO> = processServiceExceptions {
        ResponseEntity.ok(topicService.updateTopic(id.toLong(), topicUpdateRq))
    }

    @PostMapping("/topic/{id}/fixed/{fixed}")
    fun fixedTopic(@PathVariable id: String, @PathVariable fixed: Boolean): ResponseEntity<TopicVO> = processServiceExceptions {
        ResponseEntity.ok(topicService.fixedTopic(id.toLong(), fixed))
    }

    @PutMapping("/topic/{id}/addon/{addonId}")
    fun updateTopicAddons(@PathVariable id: String, @PathVariable addonId: String): ResponseEntity<TopicVO> = processServiceExceptions {
        ResponseEntity.ok(topicService.addAddons(id.toLong(), addonId.toLong()))
    }

    @PutMapping("/topic/{id}/extension/{extensionId}")
    fun updateTopicExtensions(@PathVariable id: String, @PathVariable extensionId: String): ResponseEntity<TopicVO> = processServiceExceptions {
        ResponseEntity.ok(topicService.addExtensions(id.toLong(), extensionId.toLong()))
    }

    @PutMapping("/topic/{id}/replacement/{replacementId}")
    fun updateTopicReplacements(@PathVariable id: String, @PathVariable replacementId: String): ResponseEntity<TopicVO> = processServiceExceptions {
        ResponseEntity.ok(topicService.addReplacements(id.toLong(), replacementId.toLong()))
    }

    @PutMapping("/topic/{id}/simplification/{simplificationId}")
    fun updateTopicSimplifications(@PathVariable id: String, @PathVariable simplificationId: String): ResponseEntity<TopicVO> = processServiceExceptions {
        ResponseEntity.ok(topicService.addSimplifications(id.toLong(), simplificationId.toLong()))
    }

    @DeleteMapping("/topic/{id}")
    fun deleteTopic(@PathVariable id: String): ResponseEntity<*> = processServiceExceptions {
        ResponseEntity.ok(topicService.delete(id.toLong()))
    }

}
