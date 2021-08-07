package com.invoice;

//import com.invoice.entities.Auditable;
//import com.invoice.entities.SpringSecurityAuditorAware;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

//@Configuration
//@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
//@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@SpringBootApplication

public class DemoApplication {


    public static void main(String[] args) {

        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @PostConstruct
    public void init(){
        // Setting Spring Boot SetTimeZone
        TimeZone.setDefault(TimeZone.getTimeZone("IST"));
    }

}
