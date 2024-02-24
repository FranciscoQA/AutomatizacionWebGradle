package co.com.reliquias.software.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
//import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
//@RunWith(SerenityRunner.class)
//@RunWith(JUnit4.class)
@CucumberOptions(
        features = "src/test/resources/features/login",
        glue = "co.com.reliquias.software.definitions",//POM
        //   glue ="co.com.reliquias.software.stepdefinitions",//SCREENPLAY
        snippets = CucumberOptions.SnippetType.CAMELCASE,//navegadorPaginaWeb
        plugin = "pretty",
        tags = "@CASO1"
)
public class LoginRunner {
}
