package com.invoice;

import com.invoice.entities.Auditable;
//import com.invoice.entities.SpringSecurityAuditorAware;
import com.invoice.entities.AuditorAwareImpl;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


//@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@SpringBootApplication

public class DemoApplication {

//	@Bean
//	public AuditorAware<String> auditorAware() {
//		return new SpringSecurityAuditorAware();
//	}

//	@Bean
//	public AuditorAware<String> auditorAware() {
//		return new AuditorAwareImpl();
//	}
	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
//	@Bean
//	public AuditorAware<AuditableUser> auditorProvider() {
//		return new AuditorAwareImpl();
//	}
//SpringSecurityAuditorAware u=new SpringSecurityAuditorAware();
//	u.setLastMo

}
