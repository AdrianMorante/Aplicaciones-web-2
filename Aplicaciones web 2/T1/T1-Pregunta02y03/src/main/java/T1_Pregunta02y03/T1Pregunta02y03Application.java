package T1_Pregunta02y03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class T1Pregunta02y03Application {

	public static void main(String[] args) {
		SpringApplication.run(T1Pregunta02y03Application.class, args);
	}

}
