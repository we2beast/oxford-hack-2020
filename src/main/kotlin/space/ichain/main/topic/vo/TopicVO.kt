package space.ichain.main.topic.vo

import space.ichain.main.topic.entities.Topic

data class TopicVO(
        val id: Long? = null,
        val title: String? = null,
        val description: String? = null,
        val previewImg: String? = null,
        val html: String? = null,
        val keywords: List<String> = listOf(),
        val approved: Boolean = false,
        val fixed: Boolean = false,
        val addons: List<TopicWithoutVerbVO> = listOf(),
        val extensions: List<TopicWithoutVerbVO> = listOf(),
        val replacements: List<TopicWithoutVerbVO> = listOf(),
        val simplifications: List<TopicWithoutVerbVO> = listOf()
) {

    companion object {
        fun fromData(topic: Topic): TopicVO =
                TopicVO(
                        topic.id,
                        topic.title,
                        topic.description,
                        topic.previewImg,
                        topic.html,
                        topic.keywords,
                        topic.approved,
                        topic.fixed,
                        topic.addons.map { TopicWithoutVerbVO.fromData(it) }.apply {
                            if (topic.addons.size > 1)
                                this.slice(0..0)
                        },
                        topic.extensions.map { TopicWithoutVerbVO.fromData(it) }.apply {
                            if (topic.extensions.size > 1)
                                this.slice(0..0)
                        },
                        topic.replacements.map { TopicWithoutVerbVO.fromData(it) }.apply {
                            if (topic.replacements.size > 1)
                                this.slice(0..0)
                        },
                        topic.simplifications.map { TopicWithoutVerbVO.fromData(it) }.apply {
                            if (topic.simplifications.size > 1)
                                this.slice(0..0)
                        }
                )
    }

}
