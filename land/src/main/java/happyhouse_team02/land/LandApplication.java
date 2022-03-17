package happyhouse_team02.land;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class LandApplication {
    public static void main(String[] args) {
        SpringApplication.run(LandApplication.class, args);
    }
}
