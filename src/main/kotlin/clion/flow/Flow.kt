package clion.flow

import com.jetbrains.test.*
import kotlinx.coroutines.runBlocking

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
            log.info("You can watch the test at ${ideaNode.urls.noVncUrl}")
            ideaNode.runIde("clion", "nightly")
            remoteRobot.test()
        } finally {
            ideaNode.killIde()
        }
    }
}

