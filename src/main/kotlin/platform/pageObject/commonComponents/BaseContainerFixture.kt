package platform.pageObject.commonComponents

import com.intellij.openapi.actionSystem.impl.ActionMenuItem
import com.intellij.ui.HyperlinkLabel
import com.intellij.ui.components.labels.LinkLabel
import com.jetbrains.test.RemoteRobot
import com.jetbrains.test.data.RemoteComponent
import com.jetbrains.test.fixtures.ComponentFixture
import com.jetbrains.test.fixtures.ContainerFixture
import models.platfrom.fixtures.commonComponents.*
import models.platfrom.fixtures.commonComponents.ComboBoxFixture.Companion.bySelectedItem
import models.platfrom.fixtures.commonComponents.JButtonFixture.Companion.byText
import platform.pageObject.commonComponents.JDialogFixture.Companion.byTitle
import models.platfrom.fixtures.commonComponents.JListFixture.Companion.byType
import platform.step
import java.awt.Container
import java.time.Duration
import java.time.Duration.ofSeconds
import javax.swing.*


open class BaseContainerFixture(
        remoteRobot: RemoteRobot,
        remoteComponent: RemoteComponent) : ContainerFixture(remoteRobot, remoteComponent) {

    inline fun jList(containsItem: String, crossinline function: JListFixture.() -> Unit = {}): JListFixture {
        return step("at 'JList' which contains item '$containsItem'") {
            val lists = findAll<JListFixture>(byType())
            lists.first {
                it.hasItem(containsItem)
            }.apply(function)
        }
    }

    fun jList(func: JListFixture.() -> Unit = {}): JListFixture =
            findWithTimeout<JListFixture>(findBy = byType()).apply(func)

    fun jTable(func: JTableFixture.() -> Unit = {}): JTableFixture = step("search for 'JTable'") {
        findWithTimeout<JTableFixture>(findBy = JTableFixture.byType()).apply(func)
    }

    fun button(text: String) = step("Search 'JButton' with text '$text'") {
        findWithTimeout<JButtonFixture>(findBy = byText(text))
    }

    fun jLabel(text: String): JLabelFixture = step("Search 'JLabel' with text '$text'") {
        val _text = text
        findWithTimeout { it is JLabel && it.text == _text }
    }

    fun jLabelContainingText(text: String): JLabelFixture = step("Search 'JLabel' with text '$text'") {
        val _text = text
        findWithTimeout { it is JLabel && it.text?.contains(_text) ?: false }
    }

    fun linkLabel(text: String): JButtonFixture = step("Search 'LinkLabel' with text '$text'") {
        findWithTimeout { it is LinkLabel<*> && it.text == "Install" }
    }

    fun actionButtonByActionPresentationText(actionPresentationText: String) = step("Search 'ActionButton' with action presentation text '$actionPresentationText") {
        findWithTimeout<ActionButtonFixture>(findBy = ActionButtonFixture.byActionPresentationText(actionPresentationText))
    }

    fun actionButtonByActionClassName(actionClass: Class<*>) = step("Search 'ActionButton' with action class '${actionClass.canonicalName}'") {
        findWithTimeout<ActionButtonFixture>(findBy = ActionButtonFixture.byActionClassName(actionClass.canonicalName))
    }

    fun actionButtonByActionClassName(actionClassName: String) = step("Search 'ActionButton' with action class '${actionClassName}'") {
        findWithTimeout<ActionButtonFixture>(findBy = ActionButtonFixture.byActionClassName(actionClassName))
    }

    fun actionButtonByTooltipText(tooltipText: String) = step("Search 'ActionButton' with tooltip $tooltipText") {
        findWithTimeout<ActionButtonFixture>(findBy = ActionButtonFixture.byTooltipText(tooltipText))
    }

    fun actionButtonByTooltipTextContains(tooltipText: String, timeout: Duration = ofSeconds(5)) = step("Search 'ActionButton' with tooltip $tooltipText") {
        findWithTimeout<ActionButtonFixture>(timeout, findBy = ActionButtonFixture.byTooltipTextContains(tooltipText))
    }

    fun container(containerClass: String, function: BaseContainerFixture.() -> Unit = {}): BaseContainerFixture =
            step("search for '$containerClass' container") {
                val cls = containerClass
                findWithTimeout<BaseContainerFixture> {
                    it is Container && it.isShowing && it.javaClass.canonicalName?.contains(cls) == true
                }.apply(function)
            }

    fun hyperLink(text: String): ComponentFixture = step("Search for 'HyperLinkLabel' with text $text") {
        val text = text
        findWithTimeout(ofSeconds(20)) { it is HyperlinkLabel && it.isShowing && it.text == text }
    }

    fun menuItem(itemText: String): ComponentFixture = findWithTimeout {
        it is ActionMenuItem && it.isShowing && it.text == itemText
    }

    inline fun dialog(
            title: String,
            timeout: Duration = ofSeconds(20),
            crossinline function: JDialogFixture.() -> Unit = {}): JDialogFixture =
            findWithTimeout<JDialogFixture>(duration = timeout, findBy = byTitle(title)).apply(function)

    fun checkBox(text: String) = step("Search 'JCheckbox' with text '$text'") {
        findWithTimeout<JCheckboxFixture>(findBy = JCheckboxFixture.byText(text))
    }

    fun radioButton(text: String) = step("Search for 'RadioButton' with text $text") {
        find<JRadioButtonFixture>(findBy = JRadioButtonFixture.byText(text))
    }

    fun comboBox(text: String): ComboBoxFixture = step("Search combobox with selected text '$text'") {
        return@step findWithTimeout(findBy = bySelectedItem(text))
    }

    fun textField(): JTextFieldFixture = step("Search 'JTextField'") {
        return@step find(JTextFieldFixture.byType())
    }
}