package com.jboss.eagleeye;

import com.jboss.eagleeye.service.IncidentService;
import com.jboss.eagleeye.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.annotation.Resource;

@SpringBootApplication
@EnableJpaAuditing
public class EagleeyeApplication {

    @Resource
    IncidentService storageService;
    public static void main(String[] args) {
        SpringApplication.run(EagleeyeApplication.class, args);
    }
    public void run(final String... arg) throws Exception {
        storageService.init();
    }


}
