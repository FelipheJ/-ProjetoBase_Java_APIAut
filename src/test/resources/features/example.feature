# encoding: utf-8
# language: pt

Funcionalidade: Exemplo de automação de testes em uma API

  Contexto: Definir dados de acesso da API
    Dado que estou testando a API
      | BaseURL                     | BasePath | PortNumber |
      | https://restapi.wcaquino.me |          | 443        |

  @StatusCode200
  Cenário: Validar que a requisição à um recurso é feita com sucesso
    Quando faço uma requisição "GET" ao recurso "/users/1"
    Então visualizo que os dados retornados são
      | id:int | name:string   | age:int | salary:float |
      | 1      | João da Silva | 30      | 1234.5678    |



