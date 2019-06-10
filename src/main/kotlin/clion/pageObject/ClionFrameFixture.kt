package clion.pageObject

import com.intellij.openapi.wm.impl.IdeFrameImpl
import com.jetbrains.test.RemoteRobot
import com.jetbrains.test.data.RemoteComponent
import models.platfrom.fixtures.commonComponents.BaseContainerFixture
import platform.step


fun RemoteRobot.clionFrame(steps: ClionFrameFixture.() -> Unit) = step("at Clion Welcome Frame") {
    findWithTimeout<ClionFrameFixture> {
        it is IdeFrameImpl
                && it.isShowing
                && it.title.contains("Welcome to").not()
    }.apply(steps)
}

class ClionFrameFixture(remoteRobot: RemoteRobot, remoteComponent: RemoteComponent) : BaseContainerFixture(remoteRobot, remoteComponent) {

}