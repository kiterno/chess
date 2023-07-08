import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.0.5"
	id("io.spring.dependency-management") version "1.1.0"
	kotlin("jvm") version "1.7.22"
	kotlin("plugin.spring") version "1.7.22"



}

group = "com.chess"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17
java.targetCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
	//this dependncecy added by me
//	dependencies {
//		classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10"
//		classpath "org.springframework.boot:spring-boot-gradle-plugin:2.6.4"
//	}
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.projectlombok:lombok:1.18.20")
	testImplementation("org.springframework.boot:spring-boot-starter-test")

	implementation("org.springframework.boot:spring-boot-starter-oauth2-client")

	implementation ("org.springframework.boot:spring-boot-starter-websocket")
	implementation ("org.webjars:webjars-locator-core")
	implementation ("org.webjars:sockjs-client:1.0.2")
	implementation ("org.webjars:stomp-websocket:2.3.3")
	implementation ("org.webjars:bootstrap:3.3.7")
	implementation ("org.webjars:jquery:3.1.1-1")
	testImplementation ("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
