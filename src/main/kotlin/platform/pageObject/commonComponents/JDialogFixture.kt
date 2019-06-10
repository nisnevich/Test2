package models.platfrom.fixtures.commonComponents

import com.jetbrains.test.RemoteRobot
import com.jetbrains.test.data.RemoteComponent
import com.jetbrains.test.data.RobotContext
import java.awt.Component
import javax.swing.JDialog

open class JDialogFixture(
        remoteRobot: RemoteRobot,
        remoteComponent: RemoteComponent) : BaseContainerFixture(remoteRobot, remoteComponent) {

    companion object {
        fun byTitle(title: String): RobotContext.(Component) -> Boolean = {
            it is JDialog && it.isShowing && it.title == title
        }

        fun byTitleContains(title: String): RobotContext.(Component) -> Boolean = {
            it is JDialog && it.isShowing && it.title.contains(title, true)
        }
    }
}