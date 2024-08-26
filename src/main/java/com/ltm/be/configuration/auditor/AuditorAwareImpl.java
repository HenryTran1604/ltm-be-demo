package com.ltm.be.configuration.auditor;
import lombok.NonNull;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;
import java.util.Optional;

import static com.ltm.be.contants.AppConstants.AuditorConstants.ANONYMOUS;
import static com.ltm.be.contants.AppConstants.AuditorConstants.SYSTEM;

public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public @NonNull Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(Objects.nonNull(authentication) && !isAnonymous()) {
            return Optional.ofNullable(authentication.getPrincipal().toString());
        }
        return Optional.of(SYSTEM);

    }
    private boolean isAnonymous() {
        return SecurityContextHolder.getContext().getAuthentication().getName().equals(ANONYMOUS);
    }
}
