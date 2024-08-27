package com.ltm.be.service.impl;

import com.ltm.be.converter.AliasConverter;
import com.ltm.be.dto.AliasDto;
import com.ltm.be.entity.AliasEntity;
import com.ltm.be.repository.AliasRepository;
import com.ltm.be.service.IAliasService;
import com.ltm.be.service.base.BaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

import static com.ltm.be.contants.AppConstants.GeneratorConstants.ALL_CHARACTERS;

@Service
@RequiredArgsConstructor
public class AliasServiceImpl implements IAliasService{
    private final AliasRepository aliasRepository;

    public String generate(int size) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < size; i++) {
            sb.append(ALL_CHARACTERS.charAt(random.nextInt(ALL_CHARACTERS.length())));
        }
        return sb.toString();
    }


    @Override
    public String generateAliasCode(int size) {
        String aliasCode;
        do {
            aliasCode = generate(size);
        } while(aliasRepository.existsByCodeAndActive(aliasCode, true));
        return aliasCode;
    }
}
