plugins {
    id("java")
    id("io.spring.dependency-management") version "1.1.3"
    id("org.springframework.boot") version "3.1.4"
}

group = "org.example"
version = "1.0"

repositories {
    mavenCentral()
    gradlePluginPortal()
}

/**
 * Spring
 */
dependencies {
    implementation(group = "org.springframework.boot", name = "spring-boot-starter-web")
    implementation(group = "org.springframework.boot", name = "spring-boot-starter-data-jpa")
}

/**
 * Test
 */
dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

/**
 * Lombok
 */
dependencies {
    compileOnly("org.projectlombok:lombok:1.18.30")
    testCompileOnly("org..projectlombok::1.18.30")
    annotationProcessor("org.projectlombok:lombok:1.18.30")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.30")
}

/**
 * Other
 */
dependencies {
    implementation(group = "org.postgresql", name = "postgresql", version = "42.7.2")
    implementation(group = "com.ibm.icu", name = "icu4j", version = "73.2")
}




tasks.test {
    useJUnitPlatform()
}