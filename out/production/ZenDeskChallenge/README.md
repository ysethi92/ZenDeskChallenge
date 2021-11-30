# ZenDeskChallenge

The Project is developed using Java(version - 11.0.1).

# Enviornment Setup for Java

* Download Java from [here](https://www.oracle.com/java/technologies/downloads/). To download Java 11.0.1
  click [here](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html).
* Set up the enviornment varialbes to point to correct installation directories.

---

# Running the Project.

* Clone the project and save it locally.
* Locate the config.properties file in : ``` src/main/resources ``` folder and add correct login credentials for an account.
* Rebuild the artifacts, to update the jar file contents with the correct credentials. 
  * If using Intellij, go to ```Build->Build Artifacts..->Rebuild```
* In the project folder, navigate to the below mentioned path and open the terminal there.
* The above path contains a jar file of the project that can be run on the command line.
* After opening the terminal in the above path, run the below mentioned command.

```bash
Path inside Project folder: out/artifacts/ZenDeskChallenge_jar
Command to run the project: java -jar ZenDeskChallenge.jar
```

---

# Usages:

* Java version - 11.0.1
* org.json from maven repository - [download](https://repo1.maven.org/maven2/org/json/json/20210307/json-20210307.jar)
* Junit 5.7.0 for Unit testing