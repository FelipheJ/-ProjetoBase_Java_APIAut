package br.info.felseje;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = {
                "br.info.felseje.test.config",
                "br.info.felseje.test.commons",
                "br.info.felseje.test.api.steps"
        },
        plugin = {
                "pretty",
                "json:target/cucumber.json",
//                "br.info.felseje.test.config.listeners.TestListener"
        },
        features = "src/main/resources/features",
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        tags = "@"
)
public class Runner {

}
