# Fiap-tech-challenge-03
Api para gerenciar reservas de restaurantes.

# Stacks

- Java 17
- Spring 3.4.3
- Docker
- DDD (Domain Driven Design)
- TDD (Test Driven Design)
- BDD (Behavor Drive Design)
- The testing pyramid approach
- Clean architecture


# Swagger

- local: http://localhost:8080/swagger-ui/index.html

# Docker

-start

- > docker-compose up -d

-stop

- > docker-compose down

# Application Test

- Testes unitários
- >mvn test
  
- Testes integrados
- >mvn test -P integration-test
  
- Testes de sistema
- >mvn spring-boot:run
- >mvn test -P system-test