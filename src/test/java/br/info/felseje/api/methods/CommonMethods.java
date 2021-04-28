package br.info.felseje.api.methods;

import java.util.Map;
import java.util.List;
import io.cucumber.datatable.DataTable;
import br.info.felseje.commons.BaseTest;
import io.restassured.filter.log.LogDetail;
import io.restassured.builder.RequestSpecBuilder;

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
        reqSpec = requestSpecBuilder.build();
    }

    public void facoUmaRequisicaoAoRecurso(String verbo, String recurso) {
        switch (verbo) {
            case "GET":
                validatableResponse = given().spec(reqSpec).when().get(recurso).then().spec(resSpec);
                break;
            case "POST":
                validatableResponse = given().spec(reqSpec).when().post(recurso).then().spec(resSpec);
                break;
            case "PUT":
                validatableResponse = given().spec(reqSpec).when().put(recurso).then().spec(resSpec);
                break;
            case "DELETE":
                validatableResponse = given().spec(reqSpec).when().delete(recurso).then().spec(resSpec);
                break;
            default:
                throw new IllegalArgumentException("Unrecognized httpMethod: " + verbo);
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
}
