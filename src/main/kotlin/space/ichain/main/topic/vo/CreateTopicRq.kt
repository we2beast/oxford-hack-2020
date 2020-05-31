package space.ichain.main.topic.vo

data class CreateTopicRq(
        val title: String? = null,
        val description: String? = null,
        val previewImg: String? = null,
        val html: String? = null,
        val keywords: List<String> = listOf(),
        val approved: Boolean = false
)
