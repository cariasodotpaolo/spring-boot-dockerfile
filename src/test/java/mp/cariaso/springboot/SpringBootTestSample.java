package mp.cariaso.springboot;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestApplication.class,
                webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext //This will destroy Spring context built for previous tests
public class SpringBootTestSample {
}
