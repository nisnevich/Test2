package plugins

import com.jetbrains.test.ide.IdePlugin
import org.jetbrains.teamcity.rest.BuildConfigurationId
import org.jetbrains.teamcity.rest.BuildStatus
import org.jetbrains.teamcity.rest.TeamCityInstanceFactory

private const val BUILD_SERVER_URL = "https://teamcity.jetbrains.com/"
private val BUILD_SERVER = TeamCityInstanceFactory.guestAuth(BUILD_SERVER_URL)

val PLUGIN_KOTLIN_MASTER: IdePlugin =
        pluginFromBuildServer("kotlin",
                "Kotlin_1340_UltimatePlugins",
                "kotlin-plugin-1.3.40-eap-121-CLion-191.7479.33-1.zip")

fun pluginFromBuildServer(pluginName: String,
                          buildConfigurationId: String,
                          artifactNamePattern: String,
                          parentPath: String = ""): IdePlugin {
    val pluginPath = BUILD_SERVER.builds().fromConfiguration(BuildConfigurationId(buildConfigurationId))
            .withStatus(BuildStatus.SUCCESS)
            .latest()!!
            .findArtifact(artifactNamePattern, parentPath)
            .fullName
    return IdePlugin(pluginName,
            "$BUILD_SERVER_URL/guestAuth/repository/download/$buildConfigurationId/.lastSuccessful/$pluginPath")
}
