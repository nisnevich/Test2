package platform.pageObject.commonComponents

import com.jetbrains.test.RemoteRobot
import com.jetbrains.test.data.*
import com.jetbrains.test.fixtures.ComponentFixture
import com.jetbrains.test.fixtures.server.ExtendedJListCellReader
import com.jetbrains.test.waitFor
import platform.step
import org.assertj.swing.fixture.JListFixture as AssertJListFixture
import java.awt.Component
import java.time.Duration.ofSeconds
import javax.swing.JList

@Suppress("NAME_SHADOWING")
class JListFixture(
        remoteRobot: RemoteRobot,
        remoteComponent: RemoteComponent) : ComponentFixture(remoteRobot, remoteComponent) {

    init {
        execute {
            val reader = ExtendedJListCellReader()
            val fixture = AssertJListFixture(robot, componentAs<JList<*>>())
            fixture.replaceCellReader(reader)
            save("fixture", fixture)
            save("reader", reader)
        }
    }

    companion object {
        /**
         * Can be used only if searched list contains shown items without custom cell reader
         * Though custom cell reader is set, really it's not used
         * */
        fun byItem(containsItem: String): RobotContext.(Component) -> Boolean {
            return { c ->
                if (c.isShowing && c.isEnabled && c is JList<*>) {
                    val fixture = AssertJListFixture(robot, c)
                    fixture.replaceCellReader(ExtendedJListCellReader())
                    (0 until fixture.size()).any { fixture.valueAt(it) == containsItem }
                } else {
                    false
                }
            }
        }

        fun byType(): RobotContext.(Component) -> Boolean {
            return { c ->
                c.isShowing && c.isEnabled && c is JList<*>
            }
        }
    }

    fun selectItem(item: String) = step("..select item '$item'") {
        val item = item
        execute {
            val fixture = fixture()
            val index = (0 until fixture.size())
                    .first { fixture.valueAt(it) == item }
            fixture.clickItem(index)
        }
        waitFor(ofSeconds(2)) {
            isSelected(item)
        }
    }

    fun isSelected(item: String): Boolean = step("..is '$item' selected?") {
        val item = item
        return@step remoteRobot.retrieve(this) {
            fixture().selection().any { it == item }
        }
    }

    val items: List<String>
        get() = step("retrieve list of items") {
            return@step remoteRobot.retrieveList(this) {
                fixture().contents().toList()
            }
        }

    val selectedItems: List<String>
        get() = step("retrieve list of selected items") {
            return@step remoteRobot.retrieveList(this) {
                fixture().selection().toList()
            }
        }

    fun valueAt(index: Int): String = step("retrieve value at #$index index") {
        val index = index
        return@step remoteRobot.retrieve(this) {
            fixture().valueAt(index)
        }
    }

    fun hasItem(item: String): Boolean = items.contains(item)
}

@Suppress("NOTHING_TO_INLINE")
private inline fun ComponentContext.fixture(): AssertJListFixture {
    return this.load("fixture")
}

@Suppress("NOTHING_TO_INLINE")
private inline fun AssertJListFixture.size(): Int = contents().size
