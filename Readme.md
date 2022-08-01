# GlobalLogic Auth Server

---
The Global Logic Auth server pretends to be a microservice for creating and querying users.

### Construction instructions - Prerequisites

---

1) [Git]
2) [Java JDK 8]
3) [Postgres SQL 14]
4) [Gradle]
5) [Lombok]
6) Code editor of your preference. In this case [Intellij] was used.

[Git]: https://desktop.github.com/
[Java JDK 8]: https://www.oracle.com/technetwork/java/javase/downloads
[Postgres SQL 14]: https://www.postgresql.org/
[Gradle]: https://gradle.org/install/
[Intellij]: https://www.jetbrains.com/es-es/idea/
[Lombok]: https://projectlombok.org/

### Execution instructions - How to use this code?

---
1. Fork this repository and clone it

```
$ git clone https://github.com/rashkolnikov/GlobalLogicAuth
```

2. Navigate into the folder

```
$ cd GlobalLogicAuth
```

3. Run the project
```
Open the project in Intellij or your favorite editor and run the application.


If you have the JVM encoding correctly set, you can only run:

./gradlew build

./gradlew run
```

4. Navigate to `http://localhost:2001/swagger-ui.html` in your browser to check everything is working correctly. You can change the default port in the `application.yml` file

```yml
server:
  port: 2001
```


