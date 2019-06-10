package clion.pageObject

import com.intellij.openapi.wm.impl.welcomeScreen.FlatWelcomeFrame
import com.jetbrains.test.RemoteRobot
import com.jetbrains.test.data.RemoteComponent
import com.jetbrains.test.fixtures.dataExtractor.text
import models.platfrom.fixtures.commonComponents.BaseContainerFixture
import platform.step


fun RemoteRobot.clionWelcomeFrame(steps: ClionWelcomeFrameFixture.() -> Unit) = step("at Clion Welcome Frame") {
    findWithTimeout<ClionWelcomeFrameFixture> {
        it is FlatWelcomeFrame && it.isShowing
    }.apply(steps)
}

class ClionWelcomeFrameFixture(remoteRobot: RemoteRobot, remoteComponent: RemoteComponent) : BaseContainerFixture(remoteRobot, remoteComponent) {
    val createNewProject by text("New Project")
}