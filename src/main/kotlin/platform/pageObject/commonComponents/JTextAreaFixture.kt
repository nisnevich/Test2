package platform.pageObject.commonComponents

import com.jetbrains.test.RemoteRobot
import com.jetbrains.test.data.RemoteComponent
import com.jetbrains.test.data.RobotContext
import com.jetbrains.test.data.componentAs
import com.jetbrains.test.fixtures.ComponentFixture
import platform.step
import java.awt.Component
import javax.swing.JTextArea

class JTextAreaFixture(remoteRobot: RemoteRobot, remoteComponent: RemoteComponent) : ComponentFixture(remoteRobot, remoteComponent) {

  companion object {
    fun byType(): RobotContext.(Component) -> Boolean = {
      it is JTextArea && it.isShowing
    }
  }

  val text: String
    get() = step("Get text") {
      return@step remoteRobot.retrieve(this) { componentAs<JTextArea>().text }
    }

}