import org.jetbrains.compose.compose
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.10"
    kotlin("kapt") version "1.7.10"
    id("org.jetbrains.compose")
    id("dev.hydraulic.conveyor") version "1.4"
}

group = "com.linux"
version = "1.0"

repositories {
    mavenCentral()
    google()
    mavenCentral()
    maven { url = uri("https://jitpack.io") }
    maven { url = uri("https://maven.pkg.jetbrains.space/public/p/compose/dev") }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(11))
    }
}
tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "11"
}


dependencies {
    // Use the configurations created by the Conveyor plugin to tell Gradle/Conveyor where to find the artifacts for each platform.
    linuxAmd64(compose.desktop.linux_x64)
    macAmd64(compose.desktop.macos_x64)
    macAarch64(compose.desktop.macos_arm64)
    windowsAmd64(compose.desktop.windows_x64)

    //iconos
    implementation("org.jetbrains.compose.material:material-icons-extended-desktop:1.1.1")
    implementation("org.apache.tomcat.embed:tomcat-embed-core:9.0.56")
    //sping framework
    implementation("org.springframework:spring-context:5.3.14")
    implementation("org.springframework:spring-webmvc:5.3.14")
    implementation("javax.annotation:javax.annotation-api:1.3.2")

    //hash
    implementation("org.mindrot:jbcrypt:0.4")
    // serializar objects
    implementation("com.google.code.gson:gson:2.9.0")


}

compose.desktop {
    application {
        mainClass = "com.linux.createcompilador.MainKt"
    }
}

// region Work around temporary Compose bugs.
configurations.all {
    attributes {
        // https://github.com/JetBrains/compose-jb/issues/1404#issuecomment-1146894731
        attribute(Attribute.of("ui", String::class.java), "awt")
    }
}

dependencies {
    // Force override the Kotlin stdlib version used by Compose to 1.7 in the machine specific configurations, as otherwise we can end up
    // with a mix of 1.6 and 1.7 on our classpath. This is the same logic as is applied to the regular Compose configurations normally.
    val v = "1.7.10"
    for (m in setOf("linuxAmd64", "macAmd64", "macAarch64", "windowsAmd64")) {
        m("org.jetbrains.kotlin:kotlin-stdlib:$v")
        m("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$v")
        m("org.jetbrains.kotlin:kotlin-stdlib-jdk7:$v")
    }
}
// endregion
