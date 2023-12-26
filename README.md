# Backend do Projeto Pizzaria Bella Napoli

Este é o backend do projeto Pizzaria Bella Napoli, desenvolvido como parte do trabalho de avaliação. Abaixo estão as informações sobre as tecnologias utilizadas, configurações e instruções para executar o backend.

## Tecnologias Utilizadas

- **Java:** Linguagem de programação principal.
- **Spring Boot:** Framework utilizado para desenvolvimento de aplicativos Java.
- **PostgreSQL:** Banco de dados relacional utilizado para armazenar dados.
- **Maven:** Ferramenta de gerenciamento de dependências.
- **JUnit:** Framework de teste para Java.

## Configurações

### Banco de Dados

Certifique-se de ter um banco de dados PostgreSQL em execução e ajuste as configurações do banco de dados no arquivo `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/nome-do-banco
spring.datasource.username=seu-usuario
spring.datasource.password=sua-senha
````
### Executando o Backend

- Certifique-se de ter o Java instalado em seu ambiente.
- Clone este repositório em sua máquina local.
- Abra o projeto em sua IDE preferida.
- Ajuste as configurações do banco de dados no arquivo application.properties.
- Execute a aplicação.

```
./mvnw spring-boot:run
```
**O backend estará acessível em http://localhost:8080.**

### Frontend

O repositório referente ao frontend está disponível em: https://github.com/Calleb21/pizzaria-bellanapoli-frontend-projeto.git

## Testes

O projeto inclui testes unitários. Execute os testes usando o seguinte comando:

```bash
./mvnw test
```
## Contribuição

Sinta-se à vontade para contribuir! Abra problemas ou envie solicitações de pull.

## Contato

Para perguntas ou informações adicionais, entre em contato em [camargocalleb12@gmail.com].

## Agradecimentos

Agradeço por contribuir e fazer parte do projeto! 🍕✨
