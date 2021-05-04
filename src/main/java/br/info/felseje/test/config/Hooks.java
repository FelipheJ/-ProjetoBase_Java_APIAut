package br.info.felseje.test.config;

import java.util.ArrayList;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.restassured.filter.log.LogDetail;
import br.info.felseje.test.commons.BaseTest;
import io.restassured.builder.ResponseSpecBuilder;
import br.info.felseje.test.commons.exceptions.TagException;

public class Hooks extends BaseTest {

    @Before
    public void beforeScenario(Scenario scenario) {
        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
        responseSpecBuilder.log(LogDetail.ALL);
        responseSpecBuilder.expectStatusCode(getStatusCode(scenario));
        resSpec = responseSpecBuilder.build();
    }

    @After
    public void afterScenario(Scenario scenario) {

    }

    /**
     * This method looks for the status code tag annotated in the scenario.
     * @param scenario the scenario object.
     * @exception TagException when an error with tag occurs.
     * @return the status code from tag.
     */
    private int getStatusCode(Scenario scenario) throws TagException {
        String [] statusCode;
        ArrayList<String> tags = new ArrayList<>();
        scenario.getSourceTagNames().forEach(tag -> {
            if (tag.startsWith("@StatusCode")) {
                tags.add(tag);
            }
        });
        if (tags.isEmpty()) throw new TagException("The tag name has not found.");
        if (tags.size() > 1) throw new TagException("Multiple tag names has found.");
        statusCode = tags.get(0).split("@StatusCode");
        if (statusCode.length == 0) throw new TagException("The tag name needs to specify the status code at the end.");
        return Integer.parseInt(statusCode[1]);
    }
}
