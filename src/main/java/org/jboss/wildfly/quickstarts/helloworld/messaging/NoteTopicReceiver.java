package org.jboss.wildfly.quickstarts.helloworld.messaging;


import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.jms.*;

@ApplicationScoped
public class NoteTopicReceiver {

    @Inject MsgConnectionPool msgConnectionPool;

    private MessageConsumer consumer;

    @PostConstruct
    public void afterConstruct(){
        try {
            Connection connection = msgConnectionPool.getConnection();
            //This is required by the durable connection
            //http://activemq.apache.org/how-do-durable-queues-and-topics-work.html
            //We want a durable connection so we can receive messages even when we are not around
            connection.setClientID("NoteTopicReciver");

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Topic topic = session.createTopic("NAME");

            //If this was JMS 2.0 looks like we could have used SharedDurableConsumer
            //https://docs.oracle.com/javaee/7/api/javax/jms/Session.html#createSharedDurableConsumer-javax.jms.Topic-java.lang.String-
            consumer = session.createDurableSubscriber(topic, "notShared");
            connection.start();
        } catch (JMSException e){
            System.out.println("Threw this in @PostConstruct " + e.getCause() + " :: " + e.getMessage());
        }
    }


    public String receiveTextMessage(){
        String message = "failed";
        try {
            TextMessage txtMessage = (TextMessage) consumer.receive();
            message = txtMessage.getText();

        } catch (JMSException e) {
            System.out.println("Threw this in ReceiveTextMessage " + e.getCause() + " :: " + e.getMessage());
        }
        return message;
    }

}
