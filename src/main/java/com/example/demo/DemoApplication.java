package com.example.demo;

import org.elasticsearch.core.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.bean.Role;
import com.example.demo.commons.AppConstants;
import com.example.demo.repo.IRoleRepo;

@SpringBootApplication
@EnableAsync
public class DemoApplication implements CommandLineRunner {

	@Autowired
	private IRoleRepo roleRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(this.passwordEncoder.encode("xyz"));

		try {
			Role role = new Role();
			role.setId(AppConstants.ADMIN_USER);
			role.setName("ADMIN_USER");

			Role role1 = new Role();
			role1.setId(AppConstants.NORMAL_USER);
			role1.setName("NORMAL_USER");

			this.roleRepo.saveAll(List.of(role, role1));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
