package com.ltm.be.tcpserver;

import com.ltm.be.converter.UserConverter;
import com.ltm.be.dao.UserDAO;
import com.ltm.be.service.ILogService;
import com.ltm.be.service.IScoreBoardService;
import com.ltm.be.tcpserver.contest.Ex1;
import com.ltm.be.tcpserver.contest.GeneralExercise;
import org.springframework.stereotype.Component;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

@Component
public class ClientHandler implements Runnable {
    private static final int TIME_OUT = 5000;
    private Socket socket;
    private ILogService logService;
    private IScoreBoardService scoreBoardService;
    private UserDAO userDAO;
    private UserConverter userConverter;
    public ClientHandler(){}
    public ClientHandler(Socket socket, ILogService logService, IScoreBoardService scoreBoardService, UserDAO userDAO,
                         UserConverter userConverter) {
        this.socket = socket;
        this.logService = logService;
        this.scoreBoardService = scoreBoardService;
        this.userDAO = userDAO;
        this.userConverter = userConverter;
    }

    @Override
    public void run() {
        try {
            this.socket.setSoTimeout(TIME_OUT);
            DataInputStream dis = new DataInputStream(this.socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(this.socket.getOutputStream());

            System.out.println("Connect to " + this.socket.getInetAddress().getHostAddress());
            logService.sendLog(this.socket.getInetAddress().getHostAddress() + " connected!");
            Ex1 ex1 = new Ex1(this.socket, this.logService, this.scoreBoardService, this.userDAO, this.userConverter);
            ex1.run();

            dos.close();
            dis.close();
        } catch (SocketTimeoutException ex) {
            if(this.socket != null)
                logService.sendLog(this.socket.getInetAddress().getHostAddress() + " Time out!");
            shutdown();
        } catch (SocketException ex) {
            // TODO: handle
        } catch (IOException ex) {
//            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void shutdown() {
        if (!this.socket.isClosed()) {
            try {
                this.socket.close();
            } catch (IOException ex) {
                // TODO: handle
            }
        }
    }
}
