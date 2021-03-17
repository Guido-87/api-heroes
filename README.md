Content:
1.- Requirements and run app
    A.- Install JDK
    B.- Run app with mvnw
    C.- Maven
2.- Execute tests
3.- Postman Collection
4.- Debug

1.- Requirements and run app:

A.- Install JDK (Java Development Kit)

Before you run app, you need to install a JDK or SDK if you dont have one.

For this project i use OpenJDK 8, downloaded from here https://adoptopenjdk.net/,
and recommended from https://spring.io/quickstart

Installation instructions can be found here: https://adoptopenjdk.net/installation.html

B.- Run app with mvnw (Maven Wrapper)

After this, open a command line or terminal on project folder and use:

For Linux / Mac:
./mvnw spring-boot:run

For Windows:
mvnw spring-boot:run

If you have trouble running this, and have Maven configured in your system you could try:

mvn spring-boot:run

Or to install Maven Wrapper plugin:
mvn -N io.takari:maven:wrapper

Then re-run mvnw corresponding command.

If you dont have Maven installed check next section.

C.- Maven

Download can be found here: https://maven.apache.org/download.cgi
And installation instructions here: https://maven.apache.org/install.html

2.- Execute tests

To execute tests, you can use mvnw:

For Linux / Mac:
./mvnw test

For Windows:
mvnw test

Or mvn for any OS:
mvn test

3.- Postman Collection

Postman Collection containing some tests and endpoints can be found in folder:
src/main/resources

4.- Debug

To debug application in any OS you can launch this mvn command:

mvn spring-boot:run -Drun.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=$PORT_NUMBER"

Where PORT_NUMBER is an unused port you choice.

Until today (February 10, 2021) you cant use mvnw for debugging.