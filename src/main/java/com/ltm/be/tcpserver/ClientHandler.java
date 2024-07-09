package com.ltm.be.tcpserver;

import com.ltm.be.service.*;
import com.ltm.be.tcpserver.contest.Ex1;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

@Component
@AllArgsConstructor
public class ClientHandler implements Runnable {
    private static final int TIME_OUT = 5000;
    private Socket socket;
    private IWebSocketService webSocketService;
    private IUserService userService;
    private ISubmissionService submissionService;
    private IExerciseService exerciseService;
    private IUserExerciseService scoreBoardService;
    public ClientHandler(){}
    @Override
    public void run() {
        try {
            this.socket.setSoTimeout(TIME_OUT);
            DataInputStream dis = new DataInputStream(this.socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(this.socket.getOutputStream());

            System.out.println("Connect to " + this.socket.getInetAddress().getHostAddress());
            webSocketService.sendLog(this.socket.getInetAddress().getHostAddress() + " connected!");
            Ex1 ex1 = new Ex1(this.socket, this.webSocketService, this.userService,
                    this.submissionService, this.exerciseService, this.scoreBoardService);
            ex1.run();

            dos.close();
            dis.close();
        } catch (SocketTimeoutException ex) {
            if(this.socket != null)
                webSocketService.sendLog(this.socket.getInetAddress().getHostAddress() + " Time out!");
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