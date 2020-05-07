package mp.cariaso.springboot.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/cucumber",
                 glue = "test.cacib.dda.balance.cucumber",
                 plugin = {"pretty", "html:target/cucumber", "json:target/cucumber.json"},
                 monochrome = true,
                 tags = "@MY_TAG")
public class CucumberRunnerTest {
}
