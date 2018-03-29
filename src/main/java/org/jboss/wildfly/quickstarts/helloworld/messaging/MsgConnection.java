package org.jboss.wildfly.quickstarts.helloworld.messaging;


import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Session;


/*
Username and password
    "name": "AMQ_USER""
    "name": "AMQ_PASSWORD"
    BROKER_AMQ_TCP_PORT

 */

/*
Topics are for pub/sub, queues are for point to point

TODO I really think this class should be a singleton so make a method for getInstanceOf and then also make the constructor private
TODO make sure we get maven to import the clients we need
TODO make sure we get this as the right class to inject
TODO then create a message and send to the topic - make sure we do pub sub
TODO then make the class that receives it and adds "from messaging" to the message
TODO then hook it up to the JAX-RS server
 */

@ApplicationScoped
public class MsgConnection {
    private ActiveMQConnectionFactory connectionFactory = null;
    private String uri = System.getenv("BROKER_AMQ_TCP_PORT");
    private String username = System.getenv("AMQ_USER");
    private String password = System.getenv("AMQ_PASSWORD");


    public MsgConnection() {

        try {
            // Create a ConnectionFactory
            connectionFactory = new ActiveMQConnectionFactory(uri);
            // Create a Connection
            Connection connection = connectionFactory.createConnection();
            // Create a Session
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        } catch (JMSException e) {
            e.printStackTrace();
        }

        /*
        TODO according to this page - http://activemq.apache.org/how-do-i-use-jms-efficiently.html I should resuse message
        producers and consumers. How do I do that effectively here. Do I make a Map and store them in there and then call them
        out of the map when needed.

        usage would be like:
        1. Inject this class
        2  Then call a method on this class that creates a consumer or producer into a specific topic or queue which puts it into a map -
        be sure to check first if it already exists. If it does return it if not make it and return it
         */

    }


}

