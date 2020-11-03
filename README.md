## BinaryIO application

### Running the application

Clone the github repo.
From inside the main folder, run:
```
./gradlew jar
```

That should generate a jar file in build/libs.
To run it, execute: 
```
java -jar build/libs/BinaryIO-1.0-SNAPSHOT.jar {binaryData}
```

To send the end of stream, press CTRL+D.