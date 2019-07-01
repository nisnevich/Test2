package clion.flow

import com.jetbrains.test.*
import com.jetbrains.test.ide.Ide
import com.jetbrains.test.ide.IdeFromTeamcity
import com.jetbrains.test.ide.IdeName
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
                    IDE_CLION, listOf(PLUGIN_KOTLIN_MASTER))
            remoteRobot.test()
        } finally {
            ideaNode.killIde()
        }
    }
}

val IDE_CLION: Ide
    get() = IdeFromTeamcity.create(
            IdeName.CLION,
            Configuration.clionConfigurationId,
            Configuration.clionArrtifactPattern,
            Configuration.clionBuildNumber)
