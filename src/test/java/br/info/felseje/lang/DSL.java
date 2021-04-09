package br.info.felseje.lang;

import static io.restassured.RestAssured.*;


public class DSL {

    public DSL (String baseUri) {
        baseURI = baseUri;
    }

    public DSL (String baseUri, int portNumber) {
        baseURI = baseUri;
        port = portNumber;
    }

    public DSL (String baseUri, int portNumber, String basePATH) {
        baseURI = baseUri;
        port = portNumber;
        basePath = basePATH;
    }

}
