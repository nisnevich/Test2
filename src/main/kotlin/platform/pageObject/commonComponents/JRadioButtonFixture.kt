package platform.pageObject.commonComponents

import com.jetbrains.test.RemoteRobot
import com.jetbrains.test.data.RemoteComponent
import com.jetbrains.test.data.RobotContext
import com.jetbrains.test.data.componentAs
import com.jetbrains.test.fixtures.ComponentFixture
import java.awt.Component
import javax.swing.JCheckBox
import javax.swing.JRadioButton

class JRadioButtonFixture(
        remoteRobot: RemoteRobot,
        remoteComponent: RemoteComponent) : ComponentFixture(remoteRobot, remoteComponent) {
    companion object {
        fun byText(text: String): RobotContext.(Component) -> Boolean = {
            it is JRadioButton && it.isShowing && it.text == text
        }
    }

    val text
        get() = remoteRobot.retrieve(this) { componentAs<JRadioButton>().text }

    fun isSelected(): Boolean {
        return remoteRobot.retrieve(this) { componentAs<JRadioButton>().isSelected }
    }

    fun select() {
        setValue(true)
    }

    fun unselect() {
        setValue(false)
    }

    fun setValue(value: Boolean){
        if(isSelected() != value) click()
    }

}