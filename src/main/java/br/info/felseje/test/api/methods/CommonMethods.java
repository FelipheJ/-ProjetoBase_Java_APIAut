package br.info.felseje.test.api.methods;

import java.util.Map;
import java.util.List;
import io.cucumber.datatable.DataTable;
import br.info.felseje.test.commons.BaseTest;
import io.restassured.filter.log.LogDetail;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class CommonMethods extends BaseTest {

    public void atribuirDadosAPI(DataTable dataTable) throws IllegalArgumentException {
        String aux;
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        Map<Object, Object> map = dataTable.asMaps(String.class, String.class).get(0);
        aux = (String) map.get("BaseURL");
        if (aux == null || aux.isEmpty())
            throw new IllegalArgumentException("The base url cannot be null or empty.");
        requestSpecBuilder.setBaseUri(aux);
        aux = (String) map.get("BasePath");
        if (aux != null && !aux.isEmpty()) requestSpecBuilder.setBasePath(aux);
        aux = (String) map.get("PortNumber");
        if (aux != null) requestSpecBuilder.setPort(Integer.parseInt(aux));
        requestSpecBuilder.log(LogDetail.ALL);
        requestSpecBuilder.setContentType(ContentType.JSON);
        reqSpec = requestSpecBuilder.build();
    }

    public void fazerRequisicao(DataTable dataTable) {
        String httpMethod, resource, body;
        Map<String, String> map = dataTable.asMaps().size() > 0 ?  dataTable.asMaps().get(0) : null;
        if (map == null) throw new IllegalArgumentException("DataTable.asMaps() does not have any elements.");
        body = map.get("body");
        resource = map.get("resource");
        httpMethod = map.get("httpMethod");
        switch (httpMethod) {
            case "GET":
                validatableResponse = given().spec(reqSpec).when().get(resource).then().spec(resSpec);
                break;
            case "POST":
                if (body == null || body.isEmpty()) throw new IllegalArgumentException("The body is null or empty.");
                validatableResponse = given().spec(reqSpec).body(body).when().post(resource).then().spec(resSpec);
                break;
            case "PUT":
                if (body == null || body.isEmpty()) throw new IllegalArgumentException("The body is null or empty.");
                validatableResponse = given().spec(reqSpec).body(body).when().put(resource).then().spec(resSpec);
                break;
            case "DELETE":
                validatableResponse = given().spec(reqSpec).when().delete(resource).then().spec(resSpec);
                break;
            default:
                throw new IllegalArgumentException("Unrecognized httpMethod: " + httpMethod);
        }
    }

    public void validarDadosDeResposta(DataTable dataTable) {
        String[] aux;
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> map : maps) {
            for (Object key : map.keySet()) {
                aux = ((String) key).split(":");
                switch (aux[1]) {
                    case "int":
                        validatableResponse.body(aux[0], is(Integer.parseInt(map.get(key))));
                        break;
                    case "float":
                        validatableResponse.body(aux[0], is(Float.parseFloat(map.get(key))));
                        break;
                    case "double":
                        validatableResponse.body(aux[0], is(Double.parseDouble(map.get(key))));
                        break;
                    case "string":
                        validatableResponse.body(aux[0], is(map.get(key)));
                        break;
                    default:
                        throw new IllegalArgumentException("Type not recognized.");
                }
            }
        }
    }

    public void validarQueAChaveENula(String chave) {
        validatableResponse.body(chave, nullValue());
    }

    public void validarQueAChaveNaoENula(String chave) {
        validatableResponse.body(chave, notNullValue());
    }

    public void validarQueAChaveContem(String chave, String valor) {
        String[] aux = chave.split(":");
        switch (aux[1]) {
            case "int":
                validatableResponse.body(aux[0], contains(Integer.parseInt(valor)));
                break;
            case "float":
                validatableResponse.body(aux[0], contains(Float.parseFloat(valor)));
                break;
            case "double":
                validatableResponse.body(aux[0], contains(Double.parseDouble(valor)));
                break;
            case "string":
                validatableResponse.body(aux[0], contains(valor));
                break;
            default:
                throw new IllegalArgumentException("Type not recognized.");
        }
    }

    public void validarQueAChaveNaoContem(String chave, String valor) {
        String[] aux = chave.split(":");
        switch (aux[1]) {
            case "int":
                validatableResponse.body(aux[0], not(contains(Integer.parseInt(valor))));
                break;
            case "float":
                validatableResponse.body(aux[0], not(contains(Float.parseFloat(valor))));
                break;
            case "double":
                validatableResponse.body(aux[0], not(contains(Double.parseDouble(valor))));
                break;
            case "string":
                validatableResponse.body(aux[0], not(contains(valor)));
                break;
            default:
                throw new IllegalArgumentException("Type not recognized.");
        }
    }
}
