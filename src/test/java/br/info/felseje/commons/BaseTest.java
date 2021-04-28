package br.info.felseje.commons;

import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class BaseTest {

    protected static RequestSpecification reqSpec;
    protected static ResponseSpecification resSpec;
    protected static ValidatableResponse validatableResponse;
}
