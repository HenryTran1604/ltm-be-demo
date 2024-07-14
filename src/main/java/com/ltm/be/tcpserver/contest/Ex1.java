package com.ltm.be.tcpserver.contest;

import com.ltm.be.dto.UserExerciseDto;
import com.ltm.be.service.*;
import com.ltm.be.dto.UserDto;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Random;

public class Ex1 extends GeneralExercise implements IExercise{
    private static final Long QUESTION_CODE = 1L;

    public Ex1(Socket socket,
               IWebSocketService logService,
               IUserService userService,
               ISubmissionService submissionService,
               IExerciseService exerciseService,
               IUserExerciseService scoreBoardService) {
        super(socket, logService, userService, submissionService, exerciseService, scoreBoardService);
    }
    public int[] genRandomArr(int sz, int seed) {
        int[] a = new int[sz];
        Random rand = new Random(seed);

        for (int i = 0; i < sz; i++) {
            a[i] = rand.nextInt(30);
        }
        return a;
    }

    public String genQuestion(int[] arr) {
        String question = "";
        for (int i = 0; i < arr.length - 1; i++) {
            question += arr[i] + "|";
        }
        question += arr[3];
        return question;
    }

    public int getAnswer(int[] arr) {
        int serverAns = 0;
        for (int i = 0; i < arr.length; i++) {
            serverAns += arr[i];
        }
        return serverAns;
    }

    @Override
    public void run() {
        try {
            socket.setSoTimeout(TIMEOUT);
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            // get requestcode = studentcode + questioncode
            String requestCode = dis.readUTF();
            // check requestCode
            if (!isValidateRequestCode(requestCode)) {
                dos.writeUTF("INVALID REQUEST");
            } else {
                String[] code = requestCode.split(";");

                String studentCode = code[0];
                int qCode  = Integer.parseInt(code[1]);
                if(userService.existByStudentCode(studentCode)) {
                    UserDto user = userService.getUserByStudentCode(studentCode);
                    // gen 4 number
                    int[] randArr = genRandomArr(4, qCode);
                    // gen question from 4 number
                    String question = genQuestion(randArr);
                    dos.writeUTF(question);
                    // get client answer
                    int clientAns = dis.readInt();
                    int serverAns = getAnswer(randArr);
                    // Log
                    String resposne = String.format("%s %d: %s: question: %s server answer: %d, client answer: %d, status: %s",
                            this.socket.getInetAddress(), this.socket.getPort(), studentCode, question, clientAns, serverAns, clientAns == serverAns);

//                    webSocketService.sendLog(resposne);
//                    updateUserSubmissions(user.getId(), QUESTION_CODE, clientAns == serverAns);
//                    UserExerciseDto userExerciseDto = getNewStatus(user.getId(), 1L, clientAns == serverAns);
//                    this.webSocketService.sendUpdatedScoreBoard(userExerciseDto);
                }
                else {
                    String response = String.format("%s Mã sinh viên %s không hợp lệ!", this.socket.getInetAddress(), studentCode);
                    webSocketService.sendLog(response);
                }

            }
        } catch (SocketTimeoutException ex) {
            if (socket != null) {
                String responseTimeout = this.socket.getInetAddress() + " " + this.socket.getPort() + ": Timeout";
                webSocketService.sendLog(responseTimeout);
            }
        } catch (IOException ex) {
            // TODO: handle
        } finally {
            shutdown();
        }
    }


    public void shutdown() {
        try {
            if (!this.socket.isClosed()) {
                this.socket.close();
            }
        } catch (IOException ex) {
            // TODO: handle
        }
    }
}