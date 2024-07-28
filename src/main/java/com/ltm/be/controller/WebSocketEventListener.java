//package com.ltm.be.controller;
//import org.springframework.context.annotation.Profile;
//import org.springframework.context.event.EventListener;
//import org.springframework.messaging.simp.broker.BrokerAvailabilityEvent;
//import org.springframework.messaging.simp.broker.SimpleBrokerMessageHandler;
//import org.springframework.stereotype.Component;
//import org.springframework.web.socket.messaging.SessionSubscribeEvent;
//
//@Component
//@Profile("!Prod")
//public class WebSocketEventListener {
//
//    @EventListener
//    public void handleSessionSubscribeEvent(SessionSubscribeEvent event) {
//        String simpDestination = (String) event.getMessage().getHeaders().get("simpDestination");
//        String simpSessionId = (String) event.getMessage().getHeaders().get("simpSessionId");
//        String user = event.getUser() != null ? event.getUser().getName() : "Anonymous";
//
//        System.out.println("User " + user + " subscribed to " + simpDestination + " with session ID " + simpSessionId);
//    }
//}
