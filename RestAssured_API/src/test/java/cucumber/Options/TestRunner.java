package cucumber.Options;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"json:target/cucumber.json"},features="src\\test\\java\\qa\\Features", glue="qa.StepDefenitions")
public class TestRunner {

}
