@file:Suppress("NOTHING_TO_INLINE")

package models.platfrom.fixtures.commonComponents

import com.jetbrains.test.RemoteRobot
import com.jetbrains.test.data.*
import com.jetbrains.test.fixtures.ComponentFixture
import com.jetbrains.test.fixtures.server.ExtendedJTableCellReader
import org.assertj.swing.data.TableCell
import org.assertj.swing.fixture.JTableFixture as AssertJTableFixture

import java.awt.Component
import java.util.regex.Pattern
import javax.swing.JTable

class JTableFixture(remoteRobot: RemoteRobot,
                    remoteComponent: RemoteComponent) : ComponentFixture(remoteRobot, remoteComponent) {
    companion object {
        fun byType(): RobotContext.(Component) -> Boolean {
            return { c ->
                c.isShowing && c.isEnabled && c is JTable
            }
        }
    }

    init {
        execute {
            val reader = ExtendedJTableCellReader()
            val jTableFixture = AssertJTableFixture(robot, componentAs<JTable>())
            jTableFixture.replaceCellReader(reader)
            save("fixture", jTableFixture)
            save("reader", reader)
        }
    }

    val rowCount
        get() = remoteRobot.retrieve(this) {
            componentAs<JTable>().model.rowCount
        }

    val columnCount
        get() = remoteRobot.retrieve(this) {
            componentAs<JTable>().model.columnCount
        }

    fun valueAt(row: Int, column: Int): String {
        return remoteRobot.retrieve(this) { fixture().valueAt(TableCell.row(row).column(column)) }
    }

    fun valueAtRow(row: Int): List<String> {
        return remoteRobot.retrieve(this) {
            val cc = componentAs<JTable>().model.columnCount
            (0 until cc).map { fixture().valueAt(TableCell.row(row).column(it)) }.toTypedArray()
        }.toList()
    }

    fun clickCell(row: Int, column: Int) {
        remoteRobot.execute(this) { fixture().cell(TableCell.row(row).column(column)).click() }
    }

    fun doubleClickCell(row: Int, column: Int) {
        remoteRobot.execute(this) { fixture().cell(TableCell.row(row).column(column)).doubleClick() }
    }

    fun doubleClickCell(cellText: String) {
        remoteRobot.execute(this) { fixture().cell(cellText).doubleClick() }
    }

    fun clickCell(cellText: String) {
        remoteRobot.execute(this) { fixture().cell(cellText).click() }
    }

    fun clickCell(pattern: Pattern) {
        remoteRobot.execute(this) { fixture().cell(pattern).click() }
    }

}

private inline fun ComponentContext.fixture(): AssertJTableFixture {
    return this.load("fixture")
}
