package com.invoice.entities;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
//        return "Naresh";
        // Can use Spring Security to return currently logged in user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return   Optional.ofNullable(currentPrincipalName).filter(s -> !s.isEmpty());
    }

}
