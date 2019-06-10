package models.platfrom.fixtures.commonComponents

import com.intellij.ui.EngravedLabel
import com.intellij.ui.components.JBList
import com.intellij.ui.popup.AbstractPopup
import com.intellij.util.ui.UIUtil
import com.jetbrains.test.RemoteRobot
import com.jetbrains.test.data.RemoteComponent
import com.jetbrains.test.data.RobotContext
import com.jetbrains.test.fixtures.ContainerFixture
import java.awt.Component
import javax.swing.JComponent


class NamedMenuFixture(
        remoteRobot: RemoteRobot,
        remoteComponent: RemoteComponent) : ContainerFixture(remoteRobot, remoteComponent) {

    companion object {
        fun byTitle(title: String): RobotContext.(Component) -> Boolean = { it: Component ->
            if (it is AbstractPopup.MyContentPanel && it.isShowing) {
                val engravedLabel = UIUtil.findComponentOfType((it as? JComponent), EngravedLabel::class.java)
                engravedLabel?.text == title
            } else false
        }
    }

    val menuItems: JListFixture
        get() = find { it is JBList<*> && it.isShowing }

}