object Configuration {
    val clionConfigurationId: String
        get() = System.getProperty("clion.buildConfigurationId") ?: DEFAULT_CLION_CONFIGURATION_ID

    val clionBuildNumber: String
        get() = System.getProperty("clion.buildNumber") ?: DEFAULT_CLION_BUILD_NUMBER

    val clionArrtifactPattern: String?
        get() = System.getProperty("clion.artifactPattern")

    val kotlinConfigurationId: String
        get() = System.getProperty("kotlin.buildConfigurationId") ?: DEFAULT_KOTLIN_CONFIGURATION_ID

    val kotlinArtifactPattern: String
        get() = System.getProperty("kotlin.artifactPattern") ?: DEFAULT_KOTLIN_ARTIFACT_PATTERN

    private const val DEFAULT_CLION_CONFIGURATION_ID = "ijplatform_IjPlatform191_Cidr_CLion_PublicInstallers"
    private const val DEFAULT_CLION_BUILD_NUMBER = "191.7479.33"

    private const val DEFAULT_KOTLIN_CONFIGURATION_ID = "Kotlin_CidrPlugins_CidrPluginsCLionAppCode1340"
    private const val DEFAULT_KOTLIN_ARTIFACT_PATTERN = "kotlin-plugin-1.3.40-release-CLion-191.7479.33-dev-7.zip" // TODO: need to refactor?
}
