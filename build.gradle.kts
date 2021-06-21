

buildscript {
    extra.apply {
        set("kotlin_version", "1.5.10")
    }
    //TODO also can export to extra kts gradle file
    //    apply(from = "extra_define.gradle.kts")
    repositories {
        gradlePluginPortal()
        mavenCentral()
        google()
    }

    dependencies {
        //TODO must get from rootProject.extra
        classpath(kotlin("gradle-plugin", rootProject.extra["kotlin_version"].toString()))
    }
}

subprojects {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        google()
    }

    tasks.withType<JavaCompile> {
        sourceCompatibility = JavaVersion.VERSION_11.toString()
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "11"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}