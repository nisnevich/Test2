package models.platfrom.fixtures.commonComponents

import com.intellij.openapi.wm.impl.StripeButton
import com.jetbrains.test.RemoteRobot
import com.jetbrains.test.data.RemoteComponent
import com.jetbrains.test.data.RobotContext
import com.jetbrains.test.data.componentAs
import platform.pageObject.commonComponents.JButtonFixture
import java.awt.Component

class StripeButtonFixture(
        remoteRobot: RemoteRobot, remoteComponent: RemoteComponent) : JButtonFixture(remoteRobot, remoteComponent) {
    companion object {
        fun byText(text: String): RobotContext.(Component) -> Boolean = { it.isShowing && it is StripeButton && it.text == text }
    }

    fun isSelected(): Boolean {
        return remoteRobot.retrieve(this) { componentAs<StripeButton>().isSelected }
    }
}