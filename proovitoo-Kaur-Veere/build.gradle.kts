plugins {
	java
	id("org.springframework.boot") version "3.2.3"
	id("io.spring.dependency-management") version "1.1.4"
}

group = "spring.course"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_21
}

repositories {
	mavenCentral()
}

dependencies {
	// https://mvnrepository.com/artifact/org.jsoup/jsoup for scraping the website
	implementation("org.jsoup:jsoup:1.7.2")

	// https://mvnrepository.com/artifact/org.quartz-scheduler/quartz for cromjob
	implementation("org.quartz-scheduler:quartz:2.3.2")

	implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
	implementation("org.springframework.boot:spring-boot-starter-web")
	runtimeOnly("com.h2database:h2")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
