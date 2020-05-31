package space.ichain.main

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories
import springfox.documentation.swagger2.annotations.EnableSwagger2


@SpringBootApplication
@ConfigurationPropertiesScan(basePackages = ["space.ichain.*"])
@EnableNeo4jRepositories(basePackages = ["space.ichain.*"])
@EnableSwagger2
class MainApplication {

}

fun main(args: Array<String>) {
	runApplication<MainApplication>(*args)
}
