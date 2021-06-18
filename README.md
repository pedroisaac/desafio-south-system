# Informações

Autor: Pedro Isaac

> [Desafio South System (Sistema Bancario)](https://github.com/rh-southsystem/desafio-java-banco)

Pra resolver o desafio criei 2 projetos

1) server-spring-boot `(porta 8081)` | tempo de desenvolvimento 6 horas

Para a regra de negócio (Back-End), utilizei as seguintes tecnologias:

- Microserviços com Spring Boot 2
- Linguagem Java 11
- Mensageria ActiveMQ + JMS "embedded server/in-memory"
- Banco de Dados H2 "embedded server/in-memory"
- Spring Data JPA
- Design Patterns
- Arquitetura REST para os End Points
- Fasterxml Jackson (parametrização de dados do desafio ex: agência padrão da conta)
  
A Classe "AppConfig" popula o Banco de Inicial com as Regras de Limite de Crédito iniciais que podem ser parametrizadas de acordo com a necessidade. criando novas ou excluindo.

2) cliente-web `(porta 8082)` | tempo de desenvolvimento 4 horas

Para o View (Front-End) foi usado Angular 11


Exportei a lista de End-Points direto do Postman "arquivo esta na raiz do projeto"
```
> endpoints-pessoa-postman.json
> endpoints-conta-postman.json
```

## _Executar Spring Boot Application (Back-End)_
```
mvn spring-boot:run
```
> [localhost:8080/api](http://localhost:8080/api)

> Add Pessoa Fisica
> 
>`POST` http://localhost:8080/api/pessoas
>```json
>{"nome": "Mateus Bernardo Yuri Assis", "documento": "769.462.990-86"}
>```

> Add Pessoa Jurídica
>
>`POST` http://localhost:8080/api/pessoas
>```json
>{"nome": "Clara e Geraldo Contábil Ltda", "documento": "68.911.356/0001-10"}
>```

> Lista Pessoas by Name
> 
>`GET` http://localhost:8080/api/pessoas?nome=Pedro

>Lista Pessoa by ID
>
>`GET` http://localhost:8080/api/pessoas?id=1

>Lista Todas Pessoas
>
>`GET` http://localhost:8080/api/pessoas

>Add Conta Corrente
>
>`POST` http://localhost:8080/api/contas
>```json
>{"idPessoa": 1}
>```

>Add Conta Empresarial
>
>`POST` http://localhost:8080/api/contas
>```json
>{"idPessoa": 2}
>```

>Lista Todas Contas
>
>`GET` http://localhost:8080/api/contas

>Lista Contas by Pessoa
>
>`GET` http://localhost:8080/api/contas?idPessoa=1

>Lista Contas by ID
>
>`GET` http://localhost:8080/api/contas?id=1

