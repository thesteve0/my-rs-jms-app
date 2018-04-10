The purpose of this project is to create a simple hello world type application that container Jax-RS,
CDI, and JMS that works with Wildfly on OpenShift. I try to keep this "relatively" up to date but 
I certainly make no guarantees. This is aimed at Wildfly 11 which is Java EE 7.

Please remember this example is just to highlight:
1. How to create a connection pool using CDI
2. How to send and receive a message on a topic

In a typical scenario, when using an appserver, you would probably let the appserver handle the connection pool and you would connect using JNDI. You would also not put the sender and receiver in the same JAX-RS endpoint. 

In this example the ActiveMQ Messaging Service is assumed to be running outside the Wildfly server as a 
standalone broker. We also assume you are going to use an environment variables to specify the 
broker URI and username and password for the JMS ActiveMQConnectionFactory 

The original sample is broken out from the Wildfly Quickstarts which are found here:
https://github.com/wildfly/quickstart/. **Original README is below.**


helloworld-rs: Helloworld Using JAX-RS (Java API for RESTful Web Services)
==========================================================================
Author: Gustavo A. Brey, Gaston Coco  
Level: Intermediate  
Technologies: CDI, JAX-RS  
Summary: The `helloworld-rs` quickstart demonstrates a simple Hello World application, bundled and deployed as a WAR, that uses *JAX-RS* to say Hello.    
Target Product: WildFly  
Source: <https://github.com/wildfly/quickstart/>  

What is it?
-----------

The `helloworld-rs` quickstart demonstrates the use of *CDI* and *JAX-RS* in Red Hat JBoss Enterprise Application Platform.


System requirements
-------------------

The application this project produces is designed to be run on Red Hat JBoss Enterprise Application Platform 7 or later. 

All you need to build this project is Java 8.0 (Java SDK 1.8) or later and Maven 3.1.1 or later. See [Configure Maven for WildFly 10](https://github.com/jboss-developer/jboss-developer-shared-resources/blob/master/guides/CONFIGURE_MAVEN_JBOSS_EAP7.md#configure-maven-to-build-and-deploy-the-quickstarts) to make sure you are configured correctly for testing the quickstarts.


Use of WILDFLY_HOME
---------------

In the following instructions, replace `WILDFLY_HOME` with the actual path to your WildFly installation. The installation path is described in detail here: [Use of WILDFLY_HOME and JBOSS_HOME Variables](https://github.com/jboss-developer/jboss-developer-shared-resources/blob/master/guides/USE_OF_EAP7_HOME.md#use-of-eap_home-and-jboss_home-variables).


Start the WildFly Server
-------------------------

1. Open a command prompt and navigate to the root of the WildFly directory.
2. The following shows the command line to start the server:

        For Linux:   WILDFLY_HOME/bin/standalone.sh
        For Windows: WILDFLY_HOME\bin\standalone.bat

 
Build and Deploy the Quickstart
-------------------------

1. Make sure you have started the WildFly server as described above.
2. Open a command prompt and navigate to the root directory of this quickstart.
3. Type this command to build and deploy the archive:

        mvn clean install wildfly:deploy

4. This will deploy `target/wildfly-helloworld-rs.war` to the running instance of the server.


Access the application 
---------------------

The application is deployed to <http://localhost:8080/wildfly-helloworld-rs>.

The *XML* content can be viewed by accessing the following URL: <http://localhost:8080/wildfly-helloworld-rs/rest/xml> 

The *JSON* content can be viewed by accessing this URL: <http://localhost:8080/wildfly-helloworld-rs/rest/json>


Undeploy the Archive
--------------------

1. Make sure you have started the WildFly server as described above.
2. Open a command prompt and navigate to the root directory of this quickstart.
3. When you are finished testing, type this command to undeploy the archive:

        mvn wildfly:undeploy


Run the Quickstart in Red Hat JBoss Developer Studio or Eclipse
-------------------------------------
You can also start the server and deploy the quickstarts or run the Arquillian tests from Eclipse using JBoss tools. For general information about how to import a quickstart, add a WildFly server, and build and deploy a quickstart, see [Use JBoss Developer Studio or Eclipse to Run the Quickstarts](https://github.com/jboss-developer/jboss-developer-shared-resources/blob/master/guides/USE_JBDS.md#use-jboss-developer-studio-or-eclipse-to-run-the-quickstarts) 


Debug the Application
------------------------------------

If you want to debug the source code of any library in the project, run the following command to pull the source into your local repository. The IDE should then detect it.

        mvn dependency:sources

<!-- Build and Deploy the Quickstart to OpenShift - Coming soon! -->

