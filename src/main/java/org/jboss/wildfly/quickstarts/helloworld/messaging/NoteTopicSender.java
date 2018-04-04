package org.jboss.wildfly.quickstarts.helloworld.messaging;


import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.jms.*;

@ApplicationScoped
public class NoteTopicSender {

    @Inject MsgConnectionPool msgConnectionPool;

    private MessageProducer producer;
    private Session session;


    @PostConstruct
    public void afterConstruct(){
        try {
            Connection connection = msgConnectionPool.getConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createTopic("NAME");
            producer = session.createProducer(destination);
            connection.start();
        } catch (JMSException e){
            System.out.println("Threw this in @PostConstruct " + e.getCause() + " :: " + e.getMessage());
        }
    }


    public boolean sendTextMessage(String message){
        boolean isSuccessful = false;

        try {
            Message textMessage =  session.createTextMessage(message);
            producer.send(textMessage);
            isSuccessful = true;
        } catch (JMSException e){
            System.out.println("Threw this in sendTextMessage " + e.getCause() + " :: " + e.getMessage());
        }

        return isSuccessful;

    }

}
