package platform.pageObject.commonComponents

import com.jetbrains.test.RemoteRobot
import com.jetbrains.test.data.RemoteComponent
import com.jetbrains.test.data.RobotContext
import com.jetbrains.test.data.componentAs
import com.jetbrains.test.fixtures.ComponentFixture
import platform.step
import java.awt.Component
import javax.swing.JButton

open class JButtonFixture(
        remoteRobot: RemoteRobot,
        remoteComponent: RemoteComponent) : ComponentFixture(remoteRobot, remoteComponent) {

    companion object {
        fun byText(text: String): RobotContext.(Component) -> Boolean = { it.isShowing && it is JButton && it.text == text }
    }

    val text: String
        get() = remoteRobot.retrieve(this, true) {
            componentAs<JButton>().text
        }

    override fun click() {
        step("..click") {
            super.click()
        }
    }

    fun isEnabled(): Boolean {
        return remoteRobot.retrieve(this) { componentAs<JButton>().isEnabled }
    }
}