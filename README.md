# exploring-gradle
Exploring Gradle

* [Project](https://docs.gradle.org/current/javadoc/org/gradle/api/Project.html)

## build.gradle
Annotated `build.gradle` file from repository, `exploring-spring`.
```groovy
plugins {
  //PluginManagementSpec.plugins?(Action<? super PluginDependenciesSpec> action)
	//Blocks are methods where the last argument is either of type Closure or Action
  //Delegate for this closure is PluginDependenciesSpec
  //PluginDependenciesSpec.id()
  id('java')
  id('checkstyle')
  id('jacoco')
  //version() is a method on the object returned by id()
  //See https://docs.gradle.org/current/dsl/org.gradle.plugin.use.PluginDependenciesSpec.html  
  id('org.springframework.boot').version('2.7.6')
  id('io.spring.dependency-management').version('1.0.15.RELEASE')
}
//Project.getGroup()
group = 'exploring-spring'
//Project.getVersion()
version = '0.0.0'
repositories {
  //PluginManagementSpec.repositories?(Action<? super RepositoryHandler> repositoriesAction)
  mavenCentral() //RepositoryHandler.mavenCentral()  https://repo.maven.apache.org/maven2/ 
}
dependencies {
  // implementation() and testImplementation() methods added by java plugin
  implementation(group: 'org.springframework.boot', name: 'spring-boot-starter')
  implementation(group: 'org.springframework.boot', name: 'spring-boot-starter-web')
  implementation(group: 'com.couchbase.client', name: 'java-client', version: '3.4.0')
  testImplementation(group: 'org.springframework.boot', name: 'spring-boot-starter-test')
}
//Extensions are added to the project by plugins. 
//Each extension is available as a method which takes a closure or Action as a parameter.
java {
  //JavaPluginExtension.setSourceCompatibility()
  sourceCompatibility = "11"
  //JavaPluginExtension.setTargetCompatibility()
  targetCompatibility = "11"
}
//Extensions are added to the project by plugins. 
//Each extension is available as a method which takes a closure or Action as a parameter.
//See https://docs.gradle.org/current/dsl/org.gradle.api.plugins.quality.CheckstyleExtension.html
checkstyle {
  maxWarnings = 0
  maxErrors = 0
  showViolations = true
}
test {
  //A method is added on project for each task, method name as as task name, and taking a single Closure/Action parameter
  //Task.finalizedBy()
  finalizedBy('jacocoTestReport') // report is always generated after tests run
}
jacocoTestReport {
  //A method is added on project for each task, method name as as task name, and taking a single Closure/Action parameter
  //Task.dependsOn()
  dependsOn('test') // tests are required to run before generating the report
}
tasks.named('test') {
  //Test.useJUnitPlatform()
  useJUnitPlatform() // JUnit Platform should be used to discover and execute the tests
}
```
## Reference Documentation
* [Official Gradle documentation](https://docs.gradle.org)
* [Groovy DSL Primer](https://docs.gradle.org/current/userguide/groovy_build_script_primer.html)
* [Groovy DSL Reference](https://docs.gradle.org/current/dsl/index.html)
* [Using Gradle Plugins](https://docs.gradle.org/current/userguide/plugins.html)
* [Core Plugins](https://docs.gradle.org/current/userguide/plugin_reference.html)
* [Gradle 8.1.1 Javadocs](https://docs.gradle.org/current/javadoc/index.html)
* [Groovy Closure Syntax](https://blog.mrhaki.com/2009/11/groovy-goodness-passing-closures-to.html)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.7.6/gradle-plugin/reference/html/)

## Note(s)
See 
- (Multi-Project Build)[https://www.petrikainulainen.net/programming/gradle/getting-started-with-gradle-creating-a-multi-project-build/]

```bash
# batch.sh will hold a bunch of commands to run in one go.
touch ~/batch.sh && chmod +x ~/batch.sh

# update and upgrade installed packages.
tee ~/batch.sh<<EOF
#!/bin/bash
set -e
set -x
sudo apt update
sudo apt -y upgrade 
wget https://services.gradle.org/distributions/gradle-5.0-bin.zip -P /tmp
sudo unzip -d /opt/gradle /tmp/gradle-*.zip
file /opt/gradle/gradle-5.0
ls -l /opt/gradle/gradle-5.0 
/opt/gradle/gradle-5.0/bin/gradle -v
EOF
~/batch.sh

/opt/gradle/gradle-5.0/bin/gradle init
```
