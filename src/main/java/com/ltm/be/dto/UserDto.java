package com.ltm.be.dto;

import com.ltm.be.entity.SubmissionEntity;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String studentCode;
    private String ip;
    private LocalDateTime createdAt;
    private Integer score;
}
