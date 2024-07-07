package com.ltm.be.tcpserver;

import com.ltm.be.service.ILogService;
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
    public ClientHandler(){}
    public ClientHandler(Socket socket, ILogService logService) {
        this.socket = socket;
        this.logService = logService;
    }

    @Override
    public void run() {
        try {
            this.socket.setSoTimeout(TIME_OUT);
            DataInputStream dis = new DataInputStream(this.socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(this.socket.getOutputStream());

            System.out.println("Connect to " + this.socket.getInetAddress().getHostAddress());
            logService.sendLog(this.socket.getInetAddress().getHostAddress() + " connected!");

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
