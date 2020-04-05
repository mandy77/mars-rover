# Mars Rover Kata
Develop an API that translates the commands sent from earth to instructions that are understood by the rover.
For more specifications see: [Mars Rover Kata](https://kata-log.rocks/mars-rover-kata)

##### Building the Project
```bash
mvn clean install
```
##### To run just Tests
```bash
mvn test
```
##### To run the App:
```bash
java -jar target/mars-rover.jar
```
- Enter Rover starting position in this format: X Y Direction
- Then enter commands to control the Rover: F, B, L or R
- Type "exit" to stop the application