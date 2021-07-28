package com.invoice.entities;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public class SpringSecurityAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {

         return Optional.ofNullable("isra").filter(s -> s.isEmpty());
    }
public void main(String args[]){
//    getCurrentAuditor();
        System.out.println(getCurrentAuditor());
}

}
