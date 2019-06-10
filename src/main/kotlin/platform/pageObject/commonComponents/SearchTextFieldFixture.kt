package models.platfrom.fixtures.commonComponents

import com.intellij.ui.SearchTextField
import com.jetbrains.test.RemoteRobot
import com.jetbrains.test.data.RemoteComponent
import com.jetbrains.test.data.RobotContext
import com.jetbrains.test.data.componentAs
import com.jetbrains.test.fixtures.ComponentFixture
import org.assertj.swing.fixture.JTextComponentFixture
import platform.step
import java.awt.Component

@Suppress("UnnecessaryVariable")
class SearchTextFieldFixture(
        remoteRobot: RemoteRobot,
        remoteComponent: RemoteComponent) : ComponentFixture(remoteRobot, remoteComponent) {
    companion object {
        fun byType(): RobotContext.(Component) -> Boolean = {
            it is SearchTextField && it.isShowing
        }
    }

    var text: String
        set(value) = step("Set text '$value'") {
            val localText = value
            execute { JTextComponentFixture(robot, componentAs<SearchTextField>().textEditor).setText(localText) }
        }
        get() = step("Get text") {
            return@step remoteRobot.retrieve(this) { componentAs<SearchTextField>().text }
        }
}