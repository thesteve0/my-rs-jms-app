package org.jboss.wildfly.quickstarts.helloworld.messaging;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.jms.Connection;
import javax.jms.JMSException;

/**
 * BY making this class application scoped the "container" holds one of these objects in scope for the life of
 * the application. This allows one creation of the factory which we can keep reusing to give us different connections
 * as needed.
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

