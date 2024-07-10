package com.ltm.be.tcpserver;

import com.ltm.be.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.Socket;

@Component
public class ClientHandlerFactory {
    @Autowired
    private IWebSocketService webSocketService;
    @Autowired
    private IUserService userService;
    @Autowired
    private ISubmissionService submissionService;
    @Autowired
    private IExerciseService exerciseService;
    @Autowired
    private IUserExerciseService userExerciseService;

    public ClientHandler create(Socket socket) {
        return new ClientHandler(socket, webSocketService, userService, submissionService, exerciseService, userExerciseService);
    }
}