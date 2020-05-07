package mp.cariaso.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.business", "test.business"})
public class TestApplication {

    public static void main(String[] args) {

        SpringApplication.run(TestApplication.class,args);
    }
}
