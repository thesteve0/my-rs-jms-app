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
            //TODO find out if it is legal to create a connection per Object
            //connection = connectionFactory.createConnection();

        } catch (Exception e) {
            e.printStackTrace();
        }
    
    }
   
    public Connection getConnection() {
        Connection newConnection = null;
        try {
            newConnection = connectionFactory.createConnection();
        } catch (JMSException e) {
            e.printStackTrace();
        }

        return newConnection;
    }
}

