package com.ltm.be.service.impl;

import com.ltm.be.converter.PracticeUserExerciseConverter;
import com.ltm.be.entity.AliasEntity;
import com.ltm.be.entity.ExerciseEntity;
import com.ltm.be.entity.PracticeUserExerciseEntity;
import com.ltm.be.entity.UserEntity;
import com.ltm.be.payload.response.PageResponse;
import com.ltm.be.repository.AliasRepository;
import com.ltm.be.repository.ExerciseRepository;
import com.ltm.be.repository.PracticeUserExerciseRepository;
import com.ltm.be.repository.UserRepository;
import com.ltm.be.service.IAliasService;
import com.ltm.be.service.IPracticeUserExerciseService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PracticeUserExerciseServiceImpl implements IPracticeUserExerciseService {
    private final ExerciseRepository exerciseRepository;
    private final PracticeUserExerciseRepository practiceUserExerciseRepository;
    private final IAliasService aliasService;
    private final AliasRepository aliasRepository;
    private final UserRepository userRepository;
    private final PracticeUserExerciseConverter practiceUserExerciseConverter;
    @Override
    @Transactional
    public void addUserToPractice(Long userId) {
        UserEntity user = userRepository.getReferenceById(userId); // for fast, don't need to load whole user
        List<ExerciseEntity> exercises = exerciseRepository.findAll();
        List<PracticeUserExerciseEntity> practiceUserExercises = new ArrayList<>();
        for(ExerciseEntity exercise : exercises) {
            String aliasCode = aliasService.generateAliasCode(8);
            AliasEntity alias = AliasEntity.builder()
                    .code(aliasCode)
                    .active(true)
                    .exercise(exercise)
                    .build();
            alias = aliasRepository.save(alias);
            PracticeUserExerciseEntity practiceUserExercise = PracticeUserExerciseEntity.builder()
                    .user(user)
                    .exercise(exercise)
                    .ac(false)
                    .alias(alias)
                    .build();
            practiceUserExercises.add(practiceUserExercise);
        }
        practiceUserExerciseRepository.saveAll(practiceUserExercises);
    }

    @Override
    public PageResponse<?> getAllPracticeUserExerciseByUser(Long userId, int pageNo, int pageSize) {
        int page = 0;
        if (pageNo > 0) {
            page = pageNo - 1;
        }
        Pageable pageable = PageRequest.of(page, pageSize);

        Page<PracticeUserExerciseEntity> exercises = practiceUserExerciseRepository.findAllByUserId(userId, pageable);
        return PageResponse.builder()
                .page(pageNo)
                .size(pageSize)
                .totalPages(exercises.getTotalPages())
                .totalElements(exercises.getTotalElements())
                .items(exercises.stream().map(practiceUserExerciseConverter::toDto).toList())
                .build();
    }
}
