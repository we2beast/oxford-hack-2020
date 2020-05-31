package space.ichain.main.topic.vo

import space.ichain.main.topic.entities.Topic

data class TopicWithoutVerbVO(
        val id: Long? = null,
        val title: String? = null,
        val description: String? = null,
        val previewImg: String? = null,
        val html: String? = null,
        val keywords: List<String> = listOf(),
        val approved: Boolean = false
) {

    companion object {
        fun fromData(topic: Topic): TopicWithoutVerbVO =
                TopicWithoutVerbVO(
                        topic.id,
                        topic.title,
                        topic.description,
                        topic.previewImg,
                        topic.html,
                        topic.keywords,
                        topic.approved
                )
    }

}
