package com.ltm.be.service.impl;

import com.ltm.be.service.IRandomGeneratorService;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class RandomGeneratorServiceImpl implements IRandomGeneratorService {
    private static final String allCharacters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    @Override
    public String generate(int size) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < size; i++) {
            sb.append(allCharacters.charAt(random.nextInt(allCharacters.length())));
        }
        return sb.toString();
    }
}
