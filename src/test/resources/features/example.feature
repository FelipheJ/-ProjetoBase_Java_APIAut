# encoding: utf-8
# language: pt

Funcionalidade: Exemplo de automação de testes em uma API

  Contexto: Definir dados de acesso da API
    Dado que estou testando a API
      | BaseURL                     | BasePath | PortNumber |
      | https://restapi.wcaquino.me |          | 443        |

  @CT001 @StatusCode200
  Cenário: Validar o retorno de um usuário específico após uma requisição GET
    Quando faço uma requisição "GET" ao recurso "/users/1"
    Então visualizo que os dados retornados são
      | id:int | name:string   | age:int | salary:float |
      | 1      | João da Silva | 30      | 1234.5678    |


  @CT002 @StatusCode201
  Cenário: Validar que consigo inserir um novo usuário utilizando uma requisição POST
    Quando faço a requisição
      | httpMethod | resource | body                             |
      | POST       | /users   | { "name": "Feliphe", "age": 22 } |
    Então visualizo que a chave "id" não é nula
    E visualizo que os dados retornados são
      | name:string | age:int |
      | Feliphe     | 22      |


