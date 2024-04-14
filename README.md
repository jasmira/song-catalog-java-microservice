# song-catalog-java-microservice
Java/ Spring-Boot implementation for a backend-end microservice and its APIs for a songs catalog

## Getting Started
**NOTE: This microservice doesn't have any security, Authentication, Authorization or any sort of Encryption in place right now.**

### Steps to run the App locally
* There are two ways this app can be run. 
* If you want to run the app directly in any IDE for ex. IntelliJ IDEA, just clone and import the code in the IDE and open the file `SongDirectoryApplication.java`, right click on it and select `Run ....` option. This should run the application in your IDE terminal itself.
* If you manually want to run the project inside a separate terminal (Terminal for MacOS or Command Prompt for Windows), you can run below commands to do the same:
```agsl
1. mvn package
2. java -jar target/<your_jar_name>.jar

NOTE: Make sure you have below plugin inside your `pom.xml` file and it is using the correct/latest version:

<!-- this goes within <build> -->
<plugins>
	<plugin>
		<!-- Build an executable JAR -->
		<groupId>org.apache.maven.plugins</groupId>
		<artifactId>maven-jar-plugin</artifactId>
		<version>3.1.0</version>
		<configuration>
			<archive>
				<manifest>
					<addClasspath>true</addClasspath>
					<!-- here we specify that we want to use the main method within the App class -->
					<mainClass>your_main_class</mainClass>
				</manifest>
			</archive>
		</configuration>
	</plugin>
</plugins>
<!-- other properties -->

You can read more about it here: https://www.sohamkamani.com/java/cli-app-with-maven/
```
* To check if the application is running properly, check if your console shows similar lines as below. This ensures that app is running and has pre-populated the records in the Database.
```agsl
2024-04-14T12:33:00.372+01:00  INFO 24152 --- [song.directory] [           main] j.LocalContainerEntityManagerFactoryBean : Initialized JPA EntityManagerFactory for persistence unit 'default'
Songs saved to database!
2024-04-14T12:33:01.220+01:00  WARN 24152 --- [song.directory] [           main] JpaBaseConfiguration$JpaWebConfiguration : spring.jpa.open-in-view is enabled by default. Therefore, database queries may be performed during view rendering. Explicitly configure spring.jpa.open-in-view to disable this warning
2024-04-14T12:33:01.643+01:00  INFO 24152 --- [song.directory] [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port 8080 (http) with context path ''
2024-04-14T12:33:01.658+01:00  INFO 24152 --- [song.directory] [           main] c.i.s.d.SongDirectoryApplication         : Started SongDirectoryApplication in 5.696 seconds (process running for 6.109)
```
* This application uses an internal H2 Database. You can check the UI for H2 DB at location `http://localhost:8080/h2-console/`. You need to use the exact config values in there as you have specified in your `application.yml` file and then click the `Connect` button. This will open the H2 console and connect you to your Database. Try running command `SELECT * from SONG` and it should show you the `1000` records the DB currently has which was pre-populated for us by this microservice.
* For ease of testing and to have that initial data, this application reads dummy data from a CSV file called `songs.csv` located in `src/main/resources` folder. This is the data that is used by the microservice to pre-populate the DB at start.
* This `songs.csv` file is generated using a Python script, which you can find at the root location. The file name is `generate_songs.py`. You can modify this script to add song data of your choice and regenerate the `songs.csv` CSV file from it. To do this, in the terminal window, go to the location where the Python script file resides and run the command `python generate_songs.py` and it will create the CSV file at the same location as this Python script file. You can then place the CSV file at the right location in the project i.e. `src/main/resources`.
* These steps should ensure that you have your Java microservice running locally.

### Steps to test the App locally
* The App can be tested manually using POSTMAN or INSOMNIA client or any other API testing tools, or you can test the APIs directly in the React app that is using this microservice.
* To test the APIs in POSTMAN client, there is a POSTMAN client collection file already created. It is located at the root location and is called `Song Catalog.postman_collection.json`. You can simply open your POSTMAN client and import this file and use it directly.
* To test the microservice through the React app, make sure the React app makes the calls to this microservice and renders the data returned by it on the UI.
* There is also a test suite in place for this microservice which includes both unit and integration tests. You can simply right-click on the folder `src/test/java` and select `Run All Tests` in the IntelliJ IDE itself, or you can do it manually inside any terminal using command `mvn test`. This should run all the unit and integration test cases present for the microservice.
* These steps should ensure that your microservice is tested properly and runs as expected.

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.2.4/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.2.4/maven-plugin/reference/html/#build-image)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/3.2.4/reference/htmlsingle/index.html#data.sql.jpa-and-spring-data)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.2.4/reference/htmlsingle/index.html#web)

### Guides
The following guides illustrate how to use some features concretely:

* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
