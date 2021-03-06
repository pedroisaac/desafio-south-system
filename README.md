# Informações

Autor: Pedro Isaac

> [Desafio South System (Sistema Bancario)](https://github.com/rh-southsystem/desafio-java-banco)

Pra resolver o desafio criei 2 projetos

1) Server `(porta 8080)` | tempo de desenvolvimento 6 horas

Para a regra de negócio (Back-End), utilizei as seguintes tecnologias:

- Microserviços com Spring Boot 2
- Linguagem Java 11
- Mensageria ActiveMQ + JMS "embedded server/in-memory"
- Banco de Dados H2 "embedded server/in-memory"
- Spring Data JPA
- Design Patterns
- Arquitetura REST para os End Points
- Fasterxml Jackson (parametrização de dados do desafio ex: agência padrão da conta)
  
A Classe "AppConfig" popula o Banco com as Regras de Limite de Crédito iniciais que podem ser parametrizadas de acordo com a necessidade. criando novas ou excluindo.

Fluxo: ao criar uma Pessoa, ele identifica se é PF ou PJ, gera o Score 0-9 Random, salva no banco H2 e publica a pessoa no Topic "gerar-nova-conta"
O consumidor "CriadorConta" que fica ouvindo o Topic assim que identifica que tem algo, executa o Processo de criação da Conta, Limite de Cheque Especial e Cartão de Credito.

## _Executar Server (Back-End)_
```
mvn spring-boot:run
```

2) Cliente-Web `(porta 8082)` | tempo de desenvolvimento 4 horas

## _Executar Cliente Web (Front-End)_
```
npm install
ng serve --port 8081
```

Para o View (Front-End) foi usado Angular 11


Exportei a lista de End-Points direto do Postman "arquivo esta na raiz do projeto"
```
> endpoints-pessoa-postman.json
> endpoints-conta-postman.json
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

