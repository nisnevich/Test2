package models.platfrom.fixtures.commonComponents

import com.jetbrains.test.RemoteRobot
import com.jetbrains.test.data.RemoteComponent
import com.jetbrains.test.data.RobotContext
import com.jetbrains.test.data.componentAs
import com.jetbrains.test.fixtures.ComponentFixture
import platform.step
import java.awt.Component
import javax.swing.JLabel

class JLabelFixture(
        remoteRobot: RemoteRobot,
        remoteComponent: RemoteComponent) : ComponentFixture(remoteRobot, remoteComponent) {
    companion object {
        fun byText(text: String): RobotContext.(Component) -> Boolean = {
            it is JLabel && it.isShowing && it.text == text
        }
    }

    val value: String
        get() = remoteRobot.retrieve(this) { componentAs<JLabel>().text }

    fun isVisible(): Boolean = step("Check whether 'JBLabel' is displayed") {
        return@step remoteRobot.retrieve(this) {
            componentAs<JLabel>().isVisible
        }
    }
}