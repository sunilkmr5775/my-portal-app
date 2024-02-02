package com.sunil.myportal;

import com.sunil.myportal.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@EnableSwagger2
@SpringBootApplication
@Component
//@EnableAutoConfiguration
public class MyPortalApplication {

	public static void main(String[] args) {

		SpringApplication.run(MyPortalApplication.class, args);
	}


}
