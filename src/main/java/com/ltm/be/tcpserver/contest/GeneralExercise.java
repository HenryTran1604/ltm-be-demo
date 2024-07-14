package com.ltm.be.tcpserver.contest;

import com.ltm.be.dto.UserExerciseDto;
import com.ltm.be.entity.ExerciseEntity;
import com.ltm.be.entity.UserEntity;
import com.ltm.be.service.*;

import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class GeneralExercise {
    protected Socket socket;
    protected IWebSocketService webSocketService;
    protected IUserService userService;
    protected ISubmissionService submissionService;
    protected IExerciseService exerciseService;
    protected IUserExerciseService scoreBoardService;
    protected UserEntity user;
    protected ExerciseEntity exercise;
    protected static final int TIMEOUT = 500;

    public GeneralExercise(Socket socket,
                           IWebSocketService webSocketService,
                           IUserService userService,
                           ISubmissionService submissionService,
                           IExerciseService exerciseService,
                           IUserExerciseService scoreBoardService) {
        this.socket = socket;
        this.webSocketService = webSocketService;
        this.userService = userService;
        this.submissionService = submissionService;
        this.exerciseService = exerciseService;
        this.scoreBoardService = scoreBoardService;
    }

    public boolean isValidateRequestCode(String requestCode) {
        String regexRequest = "^[Bb]\\d{2}[A-Za-z]{4}\\d{3};\\d+$";
        Pattern pattern = Pattern.compile(regexRequest);
        Matcher matcher = pattern.matcher(requestCode);
        return matcher.matches();
    }
}