package br.info.felseje.config;

import java.util.ArrayList;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.AfterStep;
import io.cucumber.java.BeforeStep;
import br.info.felseje.commons.BaseTest;
import io.restassured.filter.log.LogDetail;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;

public class Hooks extends BaseTest {

    @Before
    public void beforeScenario(Scenario scenario) {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
        requestSpecBuilder.log(LogDetail.ALL);
        responseSpecBuilder.log(LogDetail.ALL);
        System.out.println(getStatusCode(scenario));
        responseSpecBuilder.expectStatusCode(getStatusCode(scenario));
        requestSpecification = requestSpecBuilder.build();
        responseSpecification = responseSpecBuilder.build();
    }

    @BeforeStep
    public void beforeStep() {

    }

    @AfterStep
    public void afterStep() {

    }

    @After
    public void afterScenario(Scenario scenario) {

    }

    /**
     * This method looks for the status code tag annotated in the scenario.
     * @param scenario the scenario object.
     * @return the status code from tag.
     */
    private int getStatusCode(Scenario scenario) {
        String [] statusCode;
        ArrayList<String> tags = new ArrayList<>();
        scenario.getSourceTagNames().forEach(tag -> {
            if (tag.startsWith("@StatusCode")) {
                tags.add(tag);
            }
        });
        if (tags.isEmpty()) throw new IllegalStateException("The tag name has not found.");
        if (tags.size() > 1) throw new IllegalStateException("Multiple tag names has found.");
        statusCode = tags.get(0).split("@StatusCode");
        if (statusCode.length == 0) throw new IllegalStateException("The tag name needs to specify the status code at the end.");
        return Integer.parseInt(statusCode[1]);
    }
}
