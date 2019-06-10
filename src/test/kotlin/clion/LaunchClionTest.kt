package clion

import clion.flow.usingLocalContainer
import clion.pageObject.clionFrame
import clion.pageObject.clionWelcomeFrame
import org.junit.Test

class LaunchClionTest {

    @Test
    fun clickCreateNewProject() = usingLocalContainer {
        clionWelcomeFrame {
            createNewProject.click()
            button("Create").click()
        }
        clionFrame {
            dialog("Tip of the Day") {
                button("Close").click()
            }
            Thread.sleep(5000)
        }
    }
}

