# TrueSight Intelligence SDK for Java

Java client SDK for integrating to TrueSight Intelligence. Provides methods which can be invoked locally for creating Metrics, events and Measures. As of now only POST methods are supported for Measurements, Metrics & Events.

## Prerequisites
Java version - Java 1.8

## Steps to install & use the SDK (Maven).
1.  Clone the repository to your local directory

    `$git clone <repository URL>`
    
2.  `$cd tsi-sdk-java`

3.  mvn clean install
    This will create the jar file in the target folder in the same directory.
    The folder will also contain the javadoc for the library generated.
    You are all set to use the SDK.
    
4. Install the local jar to the maven repository
   a. create a folder inside the root directory of the client project (local-repo)
   b. Run the below maven command to install the jar to the directory.
   
      `$ mvn deploy:deploy-file -DgroupId=com.bmc.truesight.saas.apiclient -DartifactId=truesight-saas-api-client -Dversion=1.0
        -Durl=file:./local-repo/ -DrepositoryId=local-repo -DupdateReleaseInfo=true -Dfile=<path-to-the-jar-file>`
        
5.  Adding the required depedency to client application POM.
    a. Add the below maven repository details to the client pom.
    
    `
      <repositories>
        <repository>
            <id>local-repo</id>
            <url>file:///${project.basedir}/local-repo</url>
        </repository>
      </repositories>
    `
    
    b. Add the below dependency to the POM file.
    
    `
       <dependency>
            <groupId>com.bmc.truesight.saas.apiclient</groupId>
            <artifactId>truesight-saas-api-client</artifactId>
            <version>1.0</version>            
        </dependency>
    `    
        
 ### Sample code
 Sample code snippets are provided in the repository/samples.
 Please provide the <email-id> & <api-token> while creating the credentials object.
 Refer to the javadoc created during build for more reference.
 
 [TrueSight REST API reference](https://documentation.truesight.bmc.com/overview)

