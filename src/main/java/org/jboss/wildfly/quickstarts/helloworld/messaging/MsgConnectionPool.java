package org.jboss.wildfly.quickstarts.helloworld.messaging;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.jms.Connection;
import javax.jms.JMSException;



/*
Topics are for pub/sub, queues are for point to point

TODO make sure we get maven to import the clients we need
TODO make sure we get this as the right class to inject
TODO then create a message and send to the topic - make sure we do pub sub
TODO then make the class that receives it and adds "from messaging" to the message
TODO then hook it up to the JAX-RS server
 */

@ApplicationScoped
public class MsgConnectionPool {
    private ActiveMQConnectionFactory connectionFactory = null;

    private Connection connection = null;


    /* seems like singleton's don't inject this way

    public MsgConnectionPool getInstance() {
        return this;
    }


    THe new pattern is this just gives a connection
    Then we inject it into a class for each topic and that has the send message
    the jax-rs just injects the topicCLass

    */

    public MsgConnectionPool(){
        super();
    }

    @PreDestroy
    public void timeToShutdown(){

        try {
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }

    @PostConstruct
    public void afterCreate() {
        String uri = System.getenv("BROKER_AMQ_TCP_PORT");
        String username = System.getenv("AMQ_USER");
        String password = System.getenv("AMQ_PASSWORD");
        try {
            // Create a ConnectionFactory
            connectionFactory = new ActiveMQConnectionFactory(username, password, uri);
            // Create a Connection
            connection = connectionFactory.createConnection();

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
   
    public Connection getConnection() {
        return connection;
    }
}

