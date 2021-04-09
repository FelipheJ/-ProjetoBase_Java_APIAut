package br.info.felseje;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = {
                "br.info.felseje.config",
                "br.info.felseje.commons",
                "br.info.felseje.api.steps"
        },
        plugin = {
                "pretty",
                "json:target/cucumber.json",
                "br.info.felseje.config.listeners.TestListener"
        },
        features = "src/test/resources/features",
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        tags = ""
)
public class Runner {

}
