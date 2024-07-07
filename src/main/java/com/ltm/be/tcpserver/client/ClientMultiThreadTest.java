package com.ltm.be.tcpserver.client;

public class ClientMultiThreadTest {
    public static void main(String[] args) {
        for (int i = 0; i < 12; i++) {
            String studentCode = "B20DCCN" + String.format("%03d", (int) (Math.random() * 1000));
            int questionCode = (int) (Math.random() * 1000);
            String serverAddress = "localhost";
            int serverPort = 806;
            Thread t = new Thread(new TCPClient(studentCode, questionCode, serverAddress, serverPort));
            t.start();
        }
    }
}

