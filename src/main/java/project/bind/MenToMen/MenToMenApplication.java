package project.bind.MenToMen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MenToMenApplication {

	public static void main(String[] args) {
		SpringApplication.run(MenToMenApplication.class, args);
	}

}
