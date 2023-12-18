# Backend do Projeto Pizzaria Bella Napoli

Este é o backend do projeto Pizzaria Bella Napoli, desenvolvido como parte do trabalho de avaliação. Abaixo estão as informações sobre as tecnologias utilizadas, configurações e instruções para executar o backend.

## Tecnologias Utilizadas

- **Java 17:** Linguagem de programação principal.
- **Spring Boot 2.6.1:** Framework utilizado para desenvolvimento de aplicativos Java.
- **PostgreSQL:** Banco de dados relacional utilizado para armazenar dados.
- **Maven:** Ferramenta de gerenciamento de dependências.
- **JUnit 5:** Framework de teste para Java.

## Configurações

### Banco de Dados

Certifique-se de ter um banco de dados PostgreSQL em execução e ajuste as configurações do banco de dados no arquivo `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/nome-do-banco
spring.datasource.username=seu-usuario
spring.datasource.password=sua-senha
````
### Executando o Backend

- Certifique-se de ter o Java 17 instalado em seu ambiente.
- Clone este repositório em sua máquina local.
- Abra o projeto em sua IDE preferida.
- Ajuste as configurações do banco de dados no arquivo application.properties.
- Execute a aplicação.

```
./mvnw spring-boot:run
```
**O backend estará acessível em http://localhost:8080.**

### Endpoints

- **Listar todos os pedidos:**

- **Método: GET**
URL: /api/pedidos/listar
Buscar pedido por ID:

- **Método: GET**
URL: /api/pedidos/buscar/{id}
Salvar novo pedido:

- **Método: POST**
URL: /api/pedidos/salvar
Corpo da solicitação: JSON representando o pedido
Listar todas as pizzas:

- **Método: GET**
URL: /api/pizzas/listar
Buscar pizza por ID:

- **Método: GET**
URL: /api/pizzas/buscar/{id}
Salvar nova pizza:

- **Método: POST**
URL: /api/pizzas/cadastrar
Corpo da solicitação: JSON representando a pizza
Excluir pizza por ID:

- **Método: DELETE**
URL: /api/pizzas/excluir/{id}

## Testes

O projeto inclui testes unitários. Execute os testes usando o seguinte comando:

```bash
./mvnw test

