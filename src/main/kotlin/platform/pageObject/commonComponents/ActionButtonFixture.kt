package platform.pageObject.commonComponents

import com.intellij.openapi.actionSystem.ActionManager
import com.intellij.openapi.actionSystem.impl.ActionButton
import com.jetbrains.test.RemoteRobot
import com.jetbrains.test.data.RemoteComponent
import com.jetbrains.test.data.RobotContext
import com.jetbrains.test.data.componentAs
import com.jetbrains.test.fixtures.ComponentFixture
import platform.step
import java.awt.Component

class ActionButtonFixture(
        remoteRobot: RemoteRobot,
        remoteComponent: RemoteComponent) : ComponentFixture(remoteRobot, remoteComponent) {

    companion object {
        fun byActionButtonId(text: String): RobotContext.(Component) -> Boolean = {
            it is ActionButton && it.isShowing && ActionManager.getInstance().getId(it.action) == text
        }
        fun byActionClassName(className: String): RobotContext.(Component) -> Boolean = {
            it is ActionButton && it.isShowing && it.action::class.java.name.endsWith(className)
        }

        fun byActionPresentationText(text: String): RobotContext.(Component) -> Boolean = {
            it is ActionButton && it.isShowing && it.action.templatePresentation.text == text
        }

        fun byTooltipText(tooltipText: String): RobotContext.(Component) -> Boolean = {
            it is ActionButton && it.toolTipText.equals(tooltipText, true)
        }

        fun byTooltipTextContains(tooltipText: String): RobotContext.(Component) -> Boolean = {
            it is ActionButton && it.toolTipText?.contains(tooltipText, true) == true
        }
    }

    override fun click() {
        step("..click") {
            super.click()
        }
    }

    fun isEnabled(): Boolean = step("..is 'Action button' enabled") {
        return@step remoteRobot.retrieve(this) { componentAs<ActionButton>().isEnabled }
    }
}