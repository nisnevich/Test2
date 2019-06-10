package models.platfrom.fixtures.commonComponents

import com.jetbrains.test.RemoteRobot
import com.jetbrains.test.data.RemoteComponent
import com.jetbrains.test.data.RobotContext
import platform.step
import java.awt.Component
import javax.swing.JList

class HeavyWeightWindowFixture(
        remoteRobot: RemoteRobot,
        remoteComponent: RemoteComponent) : BaseContainerFixture(remoteRobot, remoteComponent) {

    companion object {
        fun byHeavyWeightWindow(): RobotContext.(Component) -> Boolean = {
            it::class.java.name.endsWith("HeavyWeightWindow") && it.isShowing
        }
    }

    val jList
        get() = step("Find JList") {
            find<JListFixture> { it is JList<*> }
        }
}