package mp.cariaso.springboot.cucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import mp.cariaso.springboot.TestApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest(classes = TestApplication.class,
                webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext
public class CucumberGlueUnitTests {

    //can create multiple @Given
    @Given("My name is \"([^\"]*)\"$")
    public void givenName(String name) {

        //do something
    }

    @When("Given this or that scenario or case")
    public void givenSomeScenario() {

    }

    @Then("Do something and expect the following")
    public void someExpectedResulstHere() {

    }


}
