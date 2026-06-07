# API de Gastos de Cartão

**Autor:** Manus AI

Esta API REST foi criada em **Java com Spring Boot** para cadastrar e consultar **pessoas**, **cartões de crédito** e **despesas**. O projeto usa o padrão em camadas, separando controllers, serviços, repositórios, entidades e DTOs, o que facilita manutenção e evolução da aplicação. O Spring Boot simplifica a criação de aplicações Java autônomas baseadas em Spring, e o Spring Data JPA reduz a quantidade de código necessário para acesso a dados por meio de repositórios declarativos.[1] [2]

> O objetivo deste projeto é servir como uma base funcional para um backend de controle de gastos pessoais com cartão, usando banco H2 em memória para testes rápidos e podendo ser migrado depois para MySQL, PostgreSQL ou outro banco relacional.

## Tecnologias utilizadas

| Tecnologia | Uso no projeto |
|---|---|
| Java 17 | Linguagem e versão configurada no `pom.xml`. |
| Spring Boot 3.3.5 | Estrutura principal da aplicação backend. |
| Spring Web | Criação dos endpoints REST. |
| Spring Data JPA | Persistência das entidades no banco de dados. |
| Bean Validation | Validação dos dados recebidos nas requisições. |
| H2 Database | Banco em memória para execução local e testes. |
| Maven | Gerenciamento de dependências e build do projeto. |

## Estrutura do projeto

```text
api-gastos-cartao/
├── pom.xml
├── README.md
└── src/
    ├── main/
    │   ├── java/br/com/exemplo/gastoscartao/
    │   │   ├── ApiGastosCartaoApplication.java
    │   │   ├── controller/
    │   │   ├── dto/
    │   │   ├── entity/
    │   │   ├── exception/
    │   │   ├── repository/
    │   │   └── service/
    │   └── resources/
    │       ├── application.properties
    │       └── data.sql
    └── test/
```

## Modelo de dados

A aplicação possui três entidades principais. Uma **Pessoa** pode ter vários **Cartões**, enquanto cada **Cartão** pode ter várias **Despesas**. Essa relação permite consultar gastos por cartão, por pessoa e gerar um resumo financeiro consolidado.

| Entidade | Campos principais | Relacionamento |
|---|---|---|
| Pessoa | `id`, `nome`, `email`, `telefone`, `criadoEm` | Uma pessoa possui muitos cartões. |
| Cartão | `id`, `apelido`, `bandeira`, `ultimosDigitos`, `limite`, `diaFechamento`, `diaVencimento` | Um cartão pertence a uma pessoa e possui muitas despesas. |
| Despesa | `id`, `descricao`, `categoria`, `valor`, `dataCompra`, `parcelas` | Uma despesa pertence a um cartão. |

## Como executar

Antes de executar, confirme que você possui **Java 17** e **Maven** instalados.

```bash
java -version
mvn -version
```

Em seguida, entre na pasta do projeto e execute:

```bash
mvn spring-boot:run
```

A API ficará disponível em:

```text
http://localhost:8080
```

O console do banco H2 estará disponível em:

```text
http://localhost:8080/h2-console
```

Use os seguintes dados para acessar o H2:

| Campo | Valor |
|---|---|
| JDBC URL | `jdbc:h2:mem:gastoscartao` |
| User Name | `sa` |
| Password | vazio |

## Endpoints principais

| Método | Endpoint | Descrição |
|---|---|---|
| POST | `/api/pessoas` | Cadastra uma pessoa. |
| GET | `/api/pessoas` | Lista todas as pessoas. |
| GET | `/api/pessoas/{id}` | Busca uma pessoa por ID. |
| PUT | `/api/pessoas/{id}` | Atualiza uma pessoa. |
| DELETE | `/api/pessoas/{id}` | Exclui uma pessoa. |
| POST | `/api/cartoes` | Cadastra um cartão. |
| GET | `/api/cartoes` | Lista todos os cartões. |
| GET | `/api/cartoes?pessoaId=1` | Lista cartões de uma pessoa. |
| GET | `/api/cartoes/{id}` | Busca um cartão por ID. |
| PUT | `/api/cartoes/{id}` | Atualiza um cartão. |
| DELETE | `/api/cartoes/{id}` | Exclui um cartão. |
| POST | `/api/despesas` | Cadastra uma despesa. |
| GET | `/api/despesas` | Lista todas as despesas. |
| GET | `/api/despesas?cartaoId=1` | Lista despesas de um cartão. |
| GET | `/api/despesas?pessoaId=1` | Lista despesas de uma pessoa. |
| GET | `/api/despesas?pessoaId=1&inicio=2026-05-01&fim=2026-05-31` | Lista despesas por pessoa e período. |
| GET | `/api/despesas/{id}` | Busca uma despesa por ID. |
| PUT | `/api/despesas/{id}` | Atualiza uma despesa. |
| DELETE | `/api/despesas/{id}` | Exclui uma despesa. |
| GET | `/api/resumos/pessoas/{pessoaId}` | Gera resumo de gastos por pessoa. |

## Exemplos de requisições

### Cadastrar pessoa

```bash
curl -X POST http://localhost:8080/api/pessoas \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Ana Lima",
    "email": "ana@email.com",
    "telefone": "31999998888"
  }'
```

### Cadastrar cartão

```bash
curl -X POST http://localhost:8080/api/cartoes \
  -H "Content-Type: application/json" \
  -d '{
    "pessoaId": 1,
    "apelido": "Cartão Principal",
    "bandeira": "Visa",
    "ultimosDigitos": "4321",
    "limite": 4500.00,
    "diaFechamento": 7,
    "diaVencimento": 14
  }'
```

### Cadastrar despesa

```bash
curl -X POST http://localhost:8080/api/despesas \
  -H "Content-Type: application/json" \
  -d '{
    "cartaoId": 1,
    "descricao": "Compra no mercado",
    "categoria": "Alimentação",
    "valor": 189.90,
    "dataCompra": "2026-05-20",
    "parcelas": 1
  }'
```

### Consultar resumo de gastos

```bash
curl http://localhost:8080/api/resumos/pessoas/1
```

A resposta esperada terá um formato semelhante a este:

```json
{
  "pessoaId": 1,
  "nomePessoa": "Maria Silva",
  "totalGasto": 1070.65,
  "limiteTotal": 8500.00,
  "limiteDisponivel": 7429.35,
  "quantidadeDespesas": 3
}
```

## Validações e tratamento de erros

A API possui validações nos DTOs de entrada para evitar cadastro de dados incompletos ou inválidos. Quando ocorre um erro de validação, a resposta retorna o status HTTP `400` com a lista de campos incorretos. Quando um recurso não é encontrado, a API retorna `404`. Esse comportamento é centralizado em `GlobalExceptionHandler`, deixando os controllers mais limpos.

| Situação | Status HTTP | Exemplo |
|---|---:|---|
| Dados inválidos | 400 | E-mail mal formatado ou valor menor que zero. |
| Regra de negócio violada | 400 | E-mail já cadastrado. |
| Recurso inexistente | 404 | Pessoa, cartão ou despesa não encontrada. |
| Erro inesperado | 500 | Falha interna não prevista. |

## Testes

O projeto foi validado com Maven por meio do comando:

```bash
mvn test
```

O teste incluído verifica se o contexto do Spring Boot carrega corretamente, garantindo que controllers, serviços, repositórios e configurações principais estejam consistentes.

## Próximos passos sugeridos

Para transformar este projeto em uma solução mais completa, recomenda-se adicionar autenticação com Spring Security, trocar o H2 por PostgreSQL ou MySQL, implementar paginação nas listagens, criar relatórios por categoria e adicionar testes unitários para as regras de negócio. Essas melhorias são naturais para um backend que futuramente será integrado a um frontend web ou aplicativo móvel.

## References

[1]: https://docs.spring.io/spring-boot/index.html "Spring Boot Documentation"
[2]: https://spring.io/projects/spring-data-jpa "Spring Data JPA"
[3]: https://www.h2database.com/html/main.html "H2 Database Engine"
