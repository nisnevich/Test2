package models.platfrom.fixtures.commonComponents

import com.intellij.ui.components.labels.ActionLink
import com.jetbrains.test.RemoteRobot
import com.jetbrains.test.data.RemoteComponent
import com.jetbrains.test.data.RobotContext
import com.jetbrains.test.fixtures.ComponentFixture
import org.assertj.swing.core.MouseButton
import platform.step
import java.awt.Component
import java.awt.Point

class ActionLinkFixture(
        remoteRobot: RemoteRobot,
        remoteComponent: RemoteComponent) : ComponentFixture(remoteRobot, remoteComponent) {

    companion object {
        fun byText(text: String): RobotContext.(Component) -> Boolean = {
            it is ActionLink && it.text == text
        }
    }

    override fun click() {
        step("..click") {
            execute {
                val offset = component.height / 2
                robot.click(component, Point(offset, offset), MouseButton.LEFT_BUTTON, 1)
            }
        }
    }
}