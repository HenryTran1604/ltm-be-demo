package com.ltm.be.tcpserver.contest;

import com.ltm.be.converter.UserConverter;
import com.ltm.be.dao.UserDAO;
import com.ltm.be.entity.UserEntity;
import com.ltm.be.service.ILogService;
import com.ltm.be.service.IScoreBoardService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ex1 extends GeneralExercise implements IExercise{
    public Ex1(Socket socket,
                ILogService logService,
                IScoreBoardService scoreBoardService,
                UserDAO userDAO,
                UserConverter userConverter) {
        super(socket, logService, scoreBoardService, userDAO, userConverter);
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

    public boolean isValidateRequestCode(String requestCode) {
        String regexRequest = "^[Bb]\\d{2}[A-Za-z]{4}\\d{3};\\d+$";
        Pattern pattern = Pattern.compile(regexRequest);
        Matcher matcher = pattern.matcher(requestCode);
        return matcher.matches();
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
                UserEntity userEntity = userDAO.getUserById(studentCode);
                if(userEntity != null) {
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

                    logService.sendLog(resposne);

                   updateUserExercises(userEntity, 0, clientAns == serverAns);

                    scoreBoardService.sendUpdatedScoreBoard(userConverter.toDto(userEntity));
                }
                else {
                    String response = String.format("%s Mã sinh viên %s không hợp lệ!", this.socket.getInetAddress(), studentCode);
                    logService.sendLog(response);
                }

            }
        } catch (SocketTimeoutException ex) {
            if (socket != null) {
                String responseTimeout = this.socket.getInetAddress() + " " + this.socket.getPort() + ": Timeout";
                logService.sendLog(responseTimeout);
            }
        } catch (IOException ex) {
            // TODO: handle
        } finally {
            shutdown();
        }
    }

    @Override
    public void updateUserExercises(UserEntity user, int exerciseIndex, boolean status) {
        if(status) {
            user.getExercises().get(exerciseIndex).setAC(status);
        }
        int tryCount = user.getExercises().get(0).getTryCount();
        user.getExercises().get(0).setTryCount(tryCount + 1);
        userDAO.updateUser(user);
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
