package com.ltm.be.tcpserver.contest;

import com.ltm.be.entity.UserEntity;

public interface IExercise {
    public void run();
    public void updateUserExercises(UserEntity user, int exerciseIndex, boolean status);
}
