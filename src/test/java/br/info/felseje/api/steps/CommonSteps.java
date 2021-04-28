package br.info.felseje.api.steps;

import io.cucumber.java.pt.*;
import io.cucumber.datatable.DataTable;
import br.info.felseje.api.methods.CommonMethods;

public class CommonSteps {

    private CommonMethods commonMethods = new CommonMethods();
    @Dado("que escrevo oi na tela")
    public void queEscrevoOiNaTela() {
        System.out.println("Oi oie");
    }

    @Dado("que estou testando a API")
    public void queEstouTestandoAAPI(DataTable dataTable) {
        commonMethods.atribuirDadosAPI(dataTable);
    }

    @Quando("faço uma requisição {string} ao recurso {string}")
    public void facoUmaRequisicaoAoRecurso(String verbo, String recurso) {
        commonMethods.facoUmaRequisicaoAoRecurso(verbo, recurso);
    }

    @Então("visualizo que os dados retornados são")
    public void visualizoQueOsDadosRetornadosSao(DataTable dataTable) {
        commonMethods.validarDadosDeResposta(dataTable);
    }
}
