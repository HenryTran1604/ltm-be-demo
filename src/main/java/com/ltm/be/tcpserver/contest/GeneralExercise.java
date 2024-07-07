package com.ltm.be.tcpserver.contest;

import com.ltm.be.converter.UserConverter;
import com.ltm.be.dao.UserDAO;
import com.ltm.be.service.ILogService;
import com.ltm.be.service.IScoreBoardService;
import lombok.AllArgsConstructor;

import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@AllArgsConstructor
public class GeneralExercise {
    protected Socket socket;
    protected ILogService logService;
    protected IScoreBoardService scoreBoardService;
    protected UserDAO userDAO;
    protected UserConverter userConverter;
    protected static final int TIMEOUT = 500;
    public boolean isValidateRequestCode(String requestCode) {
        String regexRequest = "^[Bb]\\d{2}[A-Za-z]{4}\\d{3};\\d+$";
        Pattern pattern = Pattern.compile(regexRequest);
        Matcher matcher = pattern.matcher(requestCode);
        return matcher.matches();
    }
}
