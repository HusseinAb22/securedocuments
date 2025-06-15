package com.sain.securedocuments;

import com.sain.securedocuments.domain.RequestContext;
import com.sain.securedocuments.entity.RolesEntity;
import com.sain.securedocuments.enumeration.Authority;
import com.sain.securedocuments.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableJpaAuditing
@EnableAsync
public class SecuredocumentsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecuredocumentsApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(RoleRepository roleRepository){
		return args -> {
			RequestContext.setUserId(0L);
			var userRole = new RolesEntity();
			userRole.setName(Authority.USER.name());
			userRole.setAuthorities(Authority.USER);
			roleRepository.save(userRole);

			var adminRole = new RolesEntity();
			adminRole.setName(Authority.ADMIN.name());
			adminRole.setAuthorities(Authority.ADMIN);
			roleRepository.save(adminRole);

			RequestContext.start();
		};
	}

}



