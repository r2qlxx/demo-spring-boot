plugins {
    java
    id("org.springframework.boot") version "3.3.1"
    id("io.spring.dependency-management") version "1.1.5"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

dependencies {
//	Use stable package versions on 07/14/2024.
    implementation("org.springframework.boot:spring-boot-starter-web:3.3.1")
    implementation("org.springframework.boot:spring-boot-starter-aop:3.3.1")
    implementation("org.springframework.boot:spring-boot-starter-validation:3.3.1")
    implementation("org.springframework.boot:spring-boot-starter-jdbc:3.3.1")
    implementation("org.springframework.boot:spring-boot-starter-cache:3.3.1")
    implementation("com.github.ben-manes.caffeine:caffeine:3.1.8")
    implementation("org.springframework.retry:spring-retry:2.0.7")
    implementation("org.apache.httpcomponents.client5:httpclient5:5.3.1")
    compileOnly("org.projectlombok:lombok:1.18.34")
    annotationProcessor("org.projectlombok:lombok:1.18.34")
    implementation("com.h2database:h2:2.3.230")
    testImplementation("org.wiremock:wiremock:3.8.0")
    testImplementation("org.springframework.boot:spring-boot-starter-test:3.3.1")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher:1.10.3")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
