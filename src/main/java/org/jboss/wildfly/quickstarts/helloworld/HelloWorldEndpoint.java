/*
 * JBoss, Home of Professional Open Source
 * Copyright 2015, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.wildfly.quickstarts.helloworld;

import org.jboss.wildfly.quickstarts.helloworld.messaging.MsgConnection;

import javax.inject.Inject;
import javax.jms.*;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * A simple REST service which is able to say hello to someone using HelloService Please take a look at the web.xml where JAX-RS
 * is enabled
 *
 * @author gbrey@redhat.com
 *
 */

@Path("/")
public class HelloWorldEndpoint {

    
    @Inject
    HelloService helloService;

    @Inject
    MsgConnection msgConnection;

    @GET
    @Path("/json")
    @Produces({ "application/json" })
    public String getHelloWorldJSON() {
        String msgResult = "";
        try {
            Session session  = msgConnection.getSession();
            // Create the destination
            Destination destination = session.createTopic("NOTES");

            // Create a MessageProducer from the Session to the Queue
            MessageProducer producer = session.createProducer(destination);
            TextMessage message = session.createTextMessage("my messasge");
            producer.send(message);


        } catch (JMSException e) {
            System.out.println("In producer");
            e.printStackTrace();
        }

        try {
            Session session = msgConnection.getSession();
            Destination destination = session.createTopic("NOTES");
            MessageConsumer consumer = session.createConsumer(destination);
            TextMessage message = (TextMessage) consumer.receive();
            msgResult = message.getText();
            //TODO need to understand session open and closing dynamics
            session.close();
        } catch (JMSException e){
            System.out.println("In consumer");
            e.printStackTrace();
        }
        return "{\"result\":\" " + msgResult + " to " + helloService.createHelloMessage("World") + "\"}";
    }

    @GET
    @Path("/xml")
    @Produces({ "application/xml" })
    public String getHelloWorldXML() {
        return "<xml><result>" + helloService.createHelloMessage("World") + "</result></xml>";
    }

}
