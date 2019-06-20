package platform.pageObject.commonComponents

import assertk.Assert
import assertk.assertions.support.expected
import com.jetbrains.test.RemoteRobot
import com.jetbrains.test.data.RemoteComponent
import com.jetbrains.test.data.RobotContext
import com.jetbrains.test.data.componentAs
import com.jetbrains.test.fixtures.ComponentFixture
import platform.step
import java.awt.Component
import javax.swing.JCheckBox

class JCheckboxFixture(
    remoteRobot: RemoteRobot,
    remoteComponent: RemoteComponent) : ComponentFixture(remoteRobot, remoteComponent) {
  companion object {
    fun byText(text: String): RobotContext.(Component) -> Boolean = {
      it is JCheckBox && it.isShowing && it.text == text
    }
  }

  val text
    get() = remoteRobot.retrieve(this) { componentAs<JCheckBox>().text }

  fun isSelected(): Boolean {
    return remoteRobot.retrieve(this) { componentAs<JCheckBox>().isSelected }
  }

  fun select() {
    if (!isSelected()) this.click()
  }

  fun unselect() {
    if (isSelected()) this.click()
  }

    fun setValue(value: Boolean) =
            when (value) {
                true -> select()
                false -> unselect()
            }
}

fun Assert<JCheckboxFixture>.isSelected() = step("Check that '${actual.text}' is selected") {
  if (actual.isSelected()) return@step
  expected("checkbox to be selected", "selected", "it's not ))")
}

fun Assert<JCheckboxFixture>.isNotSelected() = step("Check that '${actual.text}' is NOT selected") {
  if (actual.isSelected().not()) return@step
  expected("checkbox is selected", "not selected", "selected")
}

fun Assert<JCheckboxFixture>.checkValue(value: Boolean) =
        when (value) {
            true -> this.isSelected()
            false -> this.isNotSelected()
        }
