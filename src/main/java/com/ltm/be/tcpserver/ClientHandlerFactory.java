package com.ltm.be.tcpserver;

import com.ltm.be.service.ILogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.Socket;

@Component
public class ClientHandlerFactory {

    @Autowired
    private ILogService webSocketService;

    public ClientHandler create(Socket socket) {
        return new ClientHandler(socket, webSocketService);
    }
}
