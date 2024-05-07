package com.ServicioProducto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServicioProductoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicioProductoApplication.class, args);
	}

/*	@Bean
	CommandLineRunner init(IUserJwtRepository userJwtRepository){
		return args -> {
			RoleJwt roleDeveloper = RoleJwt.builder().roleEnum(RoleEnum.DESARROLLADOR).build();

			RoleJwt roleViewer = RoleJwt.builder().roleEnum(RoleEnum.OBSERVADOR).build();

			UserJwt userAngel = UserJwt.builder()
					.username("angel")
					.password("$2a$10$cMY29RPYoIHMJSuwRfoD3eQxU1J5Rww4VnNOUOAEPqCBshkNfrEf6")
					.isEnable(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleDeveloper))
					.build();

			UserJwt userJose = UserJwt.builder()
					.username("jose")
					.password("$2a$10$cMY29RPYoIHMJSuwRfoD3eQxU1J5Rww4VnNOUOAEPqCBshkNfrEf6")
					.isEnable(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleViewer))
					.build();

			userJwtRepository.saveAll(List.of(userAngel,userJose));

		};
	}*/
}
