
PROJETO INTEGRADO - MVC

Estrutura:
- Backend: Spring Boot
- Frontend: HTML/CSS/JavaScript
- Banco: PostgreSQL

MVC utilizado:
- Model -> entities e repositories
- View -> páginas HTML em src/main/resources/static
- Controller -> controllers REST do Spring Boot

COMO RODAR:

1. Instale Java JDK 21
2. Instale Maven
3. Configure PostgreSQL

Banco:
- database: secure_card
- user: postgres
- password: postgres

4. Execute:
mvn spring-boot:run

5. Acesse:
http://localhost:8080/index.html
