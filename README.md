# exploring-gradle
Exploring Gradle

## Note(s)
See 
- (Gradle Tutorial)[https://tomgregory.com/gradle-tutorial-for-complete-beginners/]
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
```groovy
plugins {
    id 'java'
}
jar {
    manifest {
        attributes 'Main-Class': 'sample.App'
    }
}
repositories {
    mavenCentral()
}
dependencies {
    testImplementation group: 'junit', name: 'junit', version: '4.13.2'
}
task javaVersion(type: Exec) {
  executable "java"
  args "--version"
}
```