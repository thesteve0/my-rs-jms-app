package org.jboss.wildfly.quickstarts.helloworld.messaging;


import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class HelloMsgSender {
   /*
    @Inject
    // Create a Connection
    Connection connection = connectionFactory.createConnection();

    // Create a Session
    Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

    // Create the destination
    Destination destination = session.createQueue("EXAMPLE.FOO");

    // Create a MessageProducer from the Session to the Queue
    MessageProducer producer = session.createProducer(destination);

    // Start the connection
    connection.start();
    */
}
