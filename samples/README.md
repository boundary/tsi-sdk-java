# Sample Programs

This project contains SDK samples which can be built separately.

### Steps to run samples

	1. Build and install the parent SDK from the root directory.
		$mvn clean install
        
    2. Once build successful, traverse to the samples folder and build the same.
    	$mvn clean install
        
 You need to provide your `<email-ID>` and `<api-token>` before building the samples for successfuly authenticating the API when invoking the SDK methods available.
 
 Once we build the main SDK project the SDK jar is created and placed in the maven local repository. Hence when we refer the same in the sample pom it picks up the jar from the local repository. Please make changes to the pom.xml in samples folder for any changes in the master jar.
 
 There are samples for the following operations which are supported by the SDK
 
 	* Adding Measurements(bulk)
 	* Creating Metrics (bulk)
 	* Creating Events (bulk)

The values used are dummy values, Please change if required.  While creating Metrics, the call can fail if the metric already exist.
