package br.info.felseje.test.api.steps;

import io.cucumber.java.pt.*;
import io.cucumber.datatable.DataTable;
import br.info.felseje.test.api.methods.CommonMethods;

public class CommonSteps {

    private CommonMethods commonMethods = new CommonMethods();

    @Dado("que estou testando a API")
    public void queEstouTestandoAAPI(DataTable dataTable) {
        if (dataTable == null || dataTable.isEmpty())
            throw new IllegalArgumentException("The DataTable object is null or empty.");
        commonMethods.atribuirDadosAPI(dataTable);
    }

    @Quando("faço a requisição")
    public void facoARequisicao(DataTable dataTable) {
        if (dataTable == null || dataTable.isEmpty())
            throw new IllegalArgumentException("The DataTable object is null or empty.");
        commonMethods.fazerRequisicao(dataTable);
    }

    @Então("visualizo que os dados retornados são")
    public void visualizoQueOsDadosRetornadosSao(DataTable dataTable) {
        if (dataTable == null || dataTable.isEmpty())
            throw new IllegalArgumentException("The DataTable object is null or empty.");
        commonMethods.validarDadosDeResposta(dataTable);
    }

    @Então("visualizo que a chave {string} é nula")
    public void visualizoQueAChaveENula(String chave) {
        commonMethods.validarQueAChaveENula(chave);
    }

    @Então("visualizo que a chave {string} não é nula")
    public void visualizoQueAChaveNaoENula(String chave) {
        commonMethods.validarQueAChaveNaoENula(chave);
    }

    @Então("visualizo que a chave {string} contém o valor {string}")
    public void visualizoQueAChaveContemOValor(String chave, String valor) {
        commonMethods.validarQueAChaveContem(chave, valor);
    }

    @Então("visualizo que a chave {string} não contém o valor {string}")
    public void visualizoQueAChaveNaoContemOValor(String chave, String valor) {
        commonMethods.validarQueAChaveNaoContem(chave, valor);
    }
}
