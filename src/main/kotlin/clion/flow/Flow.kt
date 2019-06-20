package clion.flow

import com.jetbrains.test.*
import kotlinx.coroutines.runBlocking
import plugins.PLUGIN_KOTLIN_MASTER

inline fun usingLocalContainer(crossinline test: RemoteRobot.() -> Unit) {
    val urls = Urls(
            "http://127.0.0.1:8181",
            "http://127.0.0.1:8081",
            "http://127.0.0.1:5901",
            "http://127.0.0.1:6901"
    )
    val ideaNode = RemoteIdeaNode("some id", urls)
    val remoteRobot = RemoteRobot(urls.robotUrl)

    runBlocking {
        try {
            log.info("You can watch the test at ${ideaNode.urls.noVncUrl}/?password=1")
            ideaNode.runIde(
                    "clion",
                    "tc",
                    buildConfigurationId = "ijplatform_IjPlatform191_Cidr_CLion_Installers",
                    artifactName = "CLion-191.7974",
                    requiredPlugins = listOf(PLUGIN_KOTLIN_MASTER))
            remoteRobot.test()
        } finally {
            ideaNode.killIde()
        }
    }
}

