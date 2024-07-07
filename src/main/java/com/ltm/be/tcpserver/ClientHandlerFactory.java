package com.ltm.be.tcpserver;

import com.ltm.be.converter.UserConverter;
import com.ltm.be.dao.UserDAO;
import com.ltm.be.service.ILogService;
import com.ltm.be.service.IScoreBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.Socket;

@Component
public class ClientHandlerFactory {
    @Autowired
    private ILogService logService;
    @Autowired
    private IScoreBoardService scoreBoardService;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private UserConverter userConverter;

    public ClientHandler create(Socket socket) {
        return new ClientHandler(socket, logService, scoreBoardService, userDAO, userConverter);
    }
}
