# Chess Game

Description about this project

## Setup

### Recommended Way

```bash

# To compile and package the .jar file
$ ./compile.sh

# To run the game
$ ./run.sh

```

### Build Manually

```bash

# Clear the /target directory if exists
$ rm -r target

# Make target directories
$ mkdir -p target/bin

# Compile the classes
$ javac -d ./target/bin -cp ./src ./src/me/samoa/chess/Main.java

# Copy resource pack into built path
$ cp -r src/resources target/bin/

# Package the whole thing
$ jar cfm target/Game.jar manifest.txt -C target/bin .

# To run the game
$ java -jar target/Game.jar

```
