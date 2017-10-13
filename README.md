# TrueSight Intelligence SDK for Java

The SDK helps take the complexity out by providing local methods for creating Metrics, events and Measures. This repository contains java library, samples and documentation for using the SDK.

REST Endpoints which are supported
* /v1/batch/metrics (Only POST)
* /v1/measurements (Only POST)
* /v1/events (Only POST)

## Prerequisites
You need JDK 1.8 for building the project.

## Steps using Maven to install & use the SDK
1.  Clone the repository to your local directory

    `$ git clone <repository URL>`
    
2.  `$ cd tsi-sdk-java`

3.  `$ mvn clean install`

    This will create the jar file in the target folder present in the same directory.
    The folder will also contain the javadoc under apidocs for the library generated.
    You are all set to use the SDK.
    
4. Install the jar to the maven local repository

   a. Create a folder inside the root directory of the client project (local-repo).
   
   b. Run the below maven command to install the jar to the directory
   ```
   $ mvn deploy:deploy-file -DgroupId=com.bmc.truesight.saas.apiclient -DartifactId=truesight-saas-api-client -Dversion=1.0 -Durl=file:./local-repo/ -DrepositoryId=local-repo -DupdateReleaseInfo=true -Dfile=<path-to-the-jar-file>
   
   ```
   
        
5.  Adding the required dependency to client application POM.
    a. Add the below maven repository details to the client pom.
    
    ```
      <repositories>
        <repository>
            <id>local-repo</id>
            <url>file:///${project.basedir}/local-repo</url>
        </repository>
      </repositories>
    ```
    
    b. Add the below dependency to the POM file.
    
    ```
       <dependency>
            <groupId>com.bmc.truesight.saas.apiclient</groupId>
            <artifactId>truesight-saas-api-client</artifactId>
            <version>1.0</version>            
        </dependency>
    ```  
    
### Basic Authentication
The SDK need to be authenticated using HTTP Basic Authentication. We need to provide a TrueSightCredentials object while creating the TruesightAPIClient Object. This will be used for all the API calls which are made using this client. The SDK does not restrict on how many client object can be created.

Use email-ID used for registering with Intelligence and the API-token provided by Intelligence for creating the TrueSightCredentials object.
```
public TrueSightCredentials(String email, String token)
```

### Sample logger configuration
The SDK uses logging facade sfl4j. You can configure any of the concrete logger implementations to get the logs generated from SDK. If none of them is specified, the logs will be ignored. Use the below logging configuration sample while using logback.

```
<?xml version="1.0" encoding="UTF-8"?>
<configuration>
    <appender name="fileAppender" class="ch.qos.logback.core.FileAppender">
        <file>logfile-path</file>
        <append>true</append>
        <encoder>
            <pattern>%d [%thread] %-5level  %logger{35} - %msg%n</pattern>
        </encoder>
    </appender>
     
    <root level="TRACE">
        <appender-ref ref="fileAppender" />
    </root>
</configuration>
```
Please change the 'logfile-path' as required.

Logging dependencies required for logback
````
		<dependency>
            <groupId>ch.qos.logback</groupId>
            <artifactId>logback-classic</artifactId>
            <version>${logback.version}</version>
        </dependency>
        
        <dependency>
            <groupId>ch.qos.logback</groupId>
            <artifactId>logback-core</artifactId>
            <version>${logback.version}</version>
        </dependency>
````

### Retry configuration
The SDK supports retry of the API call during an IOException. Retry count is set to 3. The retry delay can be configured by setting the retryDelay parameter during the creation of the TrueSightAPIClient object. If the value is not provided, the default value of 30 seconds is used. If a value below 30 seconds is provided the default value will be used.

```
public TrueSightAPIClient(TrueSightCredentials creds,  int retryDelay)

public TrueSightAPIClient(TrueSightCredentials creds)
```
 
 ### Sample code
 Sample code snippets are provided in the repository/samples. Samples is a separate project with its own POM file. Follow the readme provided in samples to build the same.
 
 Refer to the javadoc created during build for more reference on how to use the SDK.
 
 [TrueSight REST API reference](https://documentation.truesight.bmc.com/overview)
