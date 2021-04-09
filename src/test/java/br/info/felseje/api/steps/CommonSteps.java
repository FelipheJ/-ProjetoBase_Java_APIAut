package br.info.felseje.api.steps;

import io.cucumber.java.pt.Dado;

public class CommonSteps {

    @Dado("que escrevo oi na tela")
    public void queEscrevoOiNaTela() {
        System.out.println("Oi oie");
    }
}
