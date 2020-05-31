package space.ichain.main.topic.entities

import org.neo4j.ogm.annotation.GeneratedValue
import org.neo4j.ogm.annotation.Id
import org.neo4j.ogm.annotation.NodeEntity
import org.neo4j.ogm.annotation.Relationship

@NodeEntity
data class Topic(

        @Id @GeneratedValue
        val id: Long? = null,

        var title: String? = null,
        var description: String? = null,
        var previewImg: String? = null,
        var html: String? = null,
        var keywords: List<String> = listOf(),
        var approved: Boolean = false,
        var fixed: Boolean = false,

        @Relationship(type = "ADDONS", direction = Relationship.OUTGOING)
        var addons: List<Topic> = listOf(),

        @Relationship(type = "EXTENSIONS", direction = Relationship.OUTGOING)
        var extensions: List<Topic> = listOf(),

        @Relationship(type = "REPLACEMENTS", direction = Relationship.OUTGOING)
        var replacements: List<Topic> = listOf(),

        @Relationship(type = "SIMPLIFICATIONS", direction = Relationship.OUTGOING)
        var simplifications: List<Topic> = listOf()

)
