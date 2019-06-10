@file:Suppress("NOTHING_TO_INLINE", "NAME_SHADOWING")

package models.platfrom.fixtures.commonComponents

import com.jetbrains.test.RemoteRobot
import com.jetbrains.test.data.*
import com.jetbrains.test.fixtures.ComponentFixture
import org.assertj.swing.fixture.JComboBoxFixture
import platform.step
import java.awt.Component
import javax.swing.JComboBox

class ComboBoxFixture(remoteRobot: RemoteRobot, remoteComponent: RemoteComponent) : ComponentFixture(remoteRobot, remoteComponent) {
    companion object {
        fun bySelectedItem(text: String): RobotContext.(Component) -> Boolean =
                { it is JComboBox<*> && it.selectedItem.toString() == text }

        fun byType(): RobotContext.(Component) -> Boolean = { it is JComboBox<*> }

    }

  init {
    execute {
      save("fixture", JComboBoxFixture(robot, componentAs<JComboBox<*>>()))
    }
  }

  fun selectItem(text: String) = step("Select '$text'") {
    val text = text
    execute {
      fixture().selectItem(text)
    }
  }

  fun selectedText(): String? {
    return remoteRobot.retrieveNullable(this) {
      val selectedIndex = fixture().target().selectedIndex
      fixture().valueAt(selectedIndex)
    }
  }

  fun listValues(): List<String> {
    return remoteRobot.retrieve(this) {
      val size = fixture().target().model.size
      (0 until size).mapNotNull { fixture().valueAt(it) }.toTypedArray()
    }.toList()
  }

}

private inline fun ComponentContext.fixture(): JComboBoxFixture = load<JComboBoxFixture>("fixture")