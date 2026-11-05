# Avaliação de Livros

## Título da apresentação
Sistema de Catálogo e Avaliação de Livros

## Descrição do projeto
Este projeto é uma aplicação web simples para listar livros, exibir avaliações e registrar novas notas e comentários de usuários. A ideia central é permitir que o usuário visualize uma biblioteca de títulos, acompanhe a média das avaliações e envie comentários com notas de 1 a 5 estrelas.

A aplicação combina um back-end em Java com Spring Boot e uma interface front-end estática em HTML, CSS e JavaScript. Os dados são simulados em memória e expostos por uma API REST em `/api/livros`.

## Arquitetura do sistema
O sistema segue uma arquitetura simples em camadas:

- Camada de apresentação: página estática em `demo/src/main/resources/static/livros.html`, com estilos em `style.css` e interações em `livros.js`.
- Camada de controle/API: `LivroApiController` expõe endpoints REST para listar livros e registrar avaliações.
- Camada de domínio: `Livro` representa o catálogo e agrega avaliações e comentários; `Avaliacao` representa uma nota e um comentário específicos.
- Camada de roteamento web: `LivroViewController` redireciona a rota inicial `/` para a página principal.

Fluxo principal:
1. O usuário acessa a página inicial em `/livros.html`.
2. O front-end faz requisições para `/api/livros`.
3. O back-end retorna a lista de livros com avaliações e média.
4. O usuário envia uma nova avaliação pelo formulário.
5. A API recebe a nota e o comentário e atualiza os dados em memória.

## Tecnologias utilizadas
- Java 21
- Spring Boot 4.1.2
- Maven
- HTML5
- CSS3
- JavaScript
- REST API
- Spring Web
- Maven Wrapper (`./mvnw`)

## Endpoints principais
- `GET /api/livros` - retorna a lista de livros com avaliações, comentários e média
- `POST /api/livros/{id}/avaliacoes` - registra uma nova avaliação para um livro específico

## Como executar
No diretório do projeto:

```bash
cd demo
./mvnw spring-boot:run
```

Se a porta 8080 estiver ocupada, pode usar outra porta:

```bash
cd demo
PORT=8081 ./mvnw spring-boot:run
```

Depois, acesse:

- `http://localhost:8080/livros.html`
- `http://localhost:8080/api/livros`

## Estrutura do projeto

```text
AVALIACAO-LIVRO-PROJ1/
├── demo/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/demo/
│   │   │   │   ├── Avaliacao.java
│   │   │   │   ├── Bot.java
│   │   │   │   ├── DemoApplication.java
│   │   │   │   ├── Livro.java
│   │   │   │   ├── LivroApiController.java
│   │   │   │   └── LivroViewController.java
│   │   │   └── resources/static/
│   │   │       ├── livros.html
│   │   │       ├── livros.js
│   │   │       └── style.css
│   │   └── test/java/com/example/demo/
│   │       └── DemoApplicationTests.java
│   ├── pom.xml
│   ├── mvnw
│   └── mvnw.cmd
├── README.md
└── AGENTS.md
```

## Objetivo do sistema
O sistema tem como finalidade demonstrar o uso de Java, Spring Boot e consumo de API REST em um cenário realista de catálogo de livros, com foco em avaliação e feedback dos usuários.
