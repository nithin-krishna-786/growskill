package com.alippo.growskill;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.alippo.growskill.service.AdminService;
import com.alippo.growskill.service.IStudentService;
import com.alippo.growskill.service.StudentService;


@SpringBootApplication
@ComponentScan(basePackages = "com.alippo.growskill")
public class GrowskillApplication {
	
	@Bean
	public ModelMapper modelMapper()
	{
		return new ModelMapper();
	}
	
	@Bean
	public AdminService adminService()
	{
		return new AdminService();
	}
	
	@Bean
	public IStudentService iStudentService()
	{
		return new StudentService();
	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(GrowskillApplication.class, args);
	}

}
