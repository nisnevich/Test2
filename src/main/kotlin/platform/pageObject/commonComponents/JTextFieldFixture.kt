package platform.pageObject.commonComponents

import com.jetbrains.test.RemoteRobot
import com.jetbrains.test.data.RemoteComponent
import com.jetbrains.test.data.RobotContext
import com.jetbrains.test.data.componentAs
import com.jetbrains.test.fixtures.ComponentFixture
import org.assertj.swing.fixture.JTextComponentFixture
import platform.step
import java.awt.Component
import javax.swing.JTextField

@Suppress("NAME_SHADOWING")
class JTextFieldFixture(
        remoteRobot: RemoteRobot,
        remoteComponent: RemoteComponent) : ComponentFixture(remoteRobot, remoteComponent) {
    companion object {
        fun byType(): RobotContext.(Component) -> Boolean = {
            it is JTextField && it.isShowing
        }

        fun byLabel(remoteRobot: RemoteRobot, name: String): JTextFieldFixture {
            return step("Search text field for label '$name'") {
                val list = remoteRobot.findAll<JTextFieldFixture>(findBy = byType())
                val labelY = remoteRobot.find<JLabelFixture>(findBy = JLabelFixture.byText(name)).let { fixture ->
                    remoteRobot.retrieve(fixture) {
                        this.component.locationOnScreen.y
                    }
                }
                val index = list.indexOfFirst { fixture ->
                    val y = fixture.remoteRobot.retrieve(fixture) {
                        this.component.locationOnScreen.y
                    }
                    val h = fixture.remoteRobot.retrieve(fixture) { component.height }
                    labelY in y..y + h
                }
                return@step list[index]
            }
        }
    }

    var text: String
        set(value) = step("Set text '$value'") {
            val localText = value
            execute { JTextComponentFixture(robot, componentAs<JTextField>()).setText(localText) }
        }
        get() = step("Get text") {
            return@step remoteRobot.retrieve(this) { componentAs<JTextField>().text }
        }
}