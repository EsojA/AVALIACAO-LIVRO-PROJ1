# AGENTS.md

## Project overview
This repository contains a small Spring Boot application for managing a book catalog and book reviews.

- Application root: `demo/`
- Main package: `com.example.demo`
- API entry point: `/api`
- Front-end page: `demo/src/main/resources/static/livros.html`

## Build and run
Use Maven from the `demo` directory:

```bash
cd demo
./mvnw test
./mvnw spring-boot:run
```

If port 8080 is already in use in the environment, start on a free port instead:

```bash
cd demo
PORT=8081 ./mvnw spring-boot:run
```

The app exposes the HTML page at `/livros.html` and the JSON API at `/api/livros`.

## Architecture notes
- Business logic lives in the model layer under `src/main/java/com/example/demo`.
- `Livro` manages the catalog and review aggregation.
- `Avaliacao` represents a single book rating/comment.
- `LivroApiController` exposes the REST API used by the static page.
- `LivroViewController` redirects `/` to `/livros.html`.

## Conventions for AI coding agents
- Prefer minimal, targeted changes that match the current Spring Boot style.
- Keep model/data access logic in the existing Java classes rather than introducing extra frameworks.
- When editing the static UI, keep the browser fetches compatible with the API routes and common local ports.
- Validate changes with the smallest relevant Maven command, typically `./mvnw test`.
- Do not add unrelated dependencies or rewrite the project structure unless a task specifically requires it.

## File references to check first
- `README.md` for project-level notes
- `demo/src/main/java/com/example/demo/Livro.java`
- `demo/src/main/java/com/example/demo/Avaliacao.java`
- `demo/src/main/java/com/example/demo/LivroApiController.java`
- `demo/src/main/resources/static/livros.html`
