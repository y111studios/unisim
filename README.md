# Unisim

## Table of Contents

- [Build](#build)
- [Run](#run)

## Build

### Prerequisites

This project uses [Maven](https://maven.apache.org), a build automation and project management tool.

- [Download Maven](https://maven.apache.org/download.html)
- [Maven Installation Instructions](https://maven.apache.org/install.html)

### Build process

Once Maven has been installed, the build command can be run from the root directory of the project.

**Compile and Package the project**:

```sh
mvn clean package
```

## Run

Once the project has been compiled. An executable shaded JAR file is produced, which can then be run.

**Run from project root**:

```sh
java -jar ./target/unisim-0.1.0.jar
```

**Run from the same directory as the jar**:

```sh
java -jar unisim-0.1.0.jar
```
