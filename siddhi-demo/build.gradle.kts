import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath("se.transmode.gradle:gradle-docker:1.2")
    }
}

plugins {
    java
    id("org.springframework.boot") version "2.2.4.RELEASE"
    id("io.spring.dependency-management") version "1.0.9.RELEASE"
    kotlin("jvm") version "1.3.61"
    kotlin("plugin.spring") version "1.3.61"
    application
    groovy
}
apply(plugin = "docker")

group = "pl.codeaddict"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-amqp")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
    testImplementation("io.projectreactor:reactor-test")
    testImplementation("org.springframework.amqp:spring-rabbit-test")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}

application {
    mainClassName = "pl.codeaddict.siddhidemoclient.SiddhidemoclientApplicationKt"
}

configure<se.transmode.gradle.plugins.docker.DockerPluginExtension> {
    maintainer = "Michal Kostewicz <m.kostewicz84@gmail.com>"
    baseImage = "adoptopenjdk/openjdk8:alpine-slim"
}

tasks.register("copyJar",Copy::class){
    dependsOn("build")
    from(file("$buildDir/libs/siddhidemoclient-0.0.1-SNAPSHOT.jar"))
    into(file("$buildDir/docker"))
}

tasks.register("appDocker" ,se.transmode.gradle.plugins.docker.DockerTask::class) {
    dependsOn("copyJar")
    addFile("siddhidemoclient-0.0.1-SNAPSHOT.jar", "/")
    entryPoint(listOf("java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/siddhidemoclient-0.0.1-SNAPSHOT.jar"))
}

