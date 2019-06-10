package models.platfrom.fixtures.commonComponents

import com.intellij.openapi.actionSystem.impl.ActionButtonWithText
import com.jetbrains.test.RemoteRobot
import com.jetbrains.test.data.RemoteComponent
import com.jetbrains.test.data.RobotContext
import java.awt.Component

class ActionButtonWithTextFixture(remoteRobot: RemoteRobot, remoteComponent: RemoteComponent): JButtonFixture(remoteRobot, remoteComponent) {

  companion object {
    fun byText(text: String): RobotContext.(Component) -> Boolean = { it.isShowing && it is ActionButtonWithText && it.action.templatePresentation.text == text }
  }

}