### API e-commerce

Aplicação de integração usando Spring Boot

## Escopo:

1. Criar um endpoint REST para criação de pedidos
2. Utilizar o spring validation para validar as informações enviadas
3. Salvar o pedido no banco de dados
4. Criar um Scheduler para pedidos abandonados
5. Buscar no banco de dados os pedidos com status INCOMPLETE sem atualização por mais de 48h
6. Atualizar status para ABANDONED
7. Salvar pedido e enviar para fila
8. Criar um consumidor pedidos através da fila
9. Receber o pedido na fila e enviar para um sistema externo com o novo status (ex: https://webhook.site/)

## Requisitos Técnicos:

- [ ] Utilizar spring security para fazer autenticação das APIS Rest
- [ ] Utilizar o spring admin para monitorar a aplicação
- [ ] Utilizar o rabbitmq como fila de mensageria
- [X] Utilizar o banco mysql/postgres (via HikariCP ou JPA/HIBERNATE)
- [ ] Utilizar o spring scheduled
- [X] Utilizar o spring envs (local, hml, prd)
- [ ] Utilizar o junit para realizar testes unitários
- [ ] Utilizar o slf4j + slf4j.MDC para loggs da aplicação (infos, debugs e errors)
- [ ] Utilizar o redis cache para cache de informações
- [X] Utilizar um client HTTP REST + JSON
- [X] Utilizar maven para build da aplicação
- [X] Utilizar o lombok + builder

## Recursos Utilizados:

* Spring Boot
* Spring boot Validation
* JPA/HIBERNATE
* Lombok
* ModelMapper
* Maven
* Custon Messages Errors
* Cucumber
* JUnit
* OpenAPI (Swagger)
* Spring Actuator

## Deploy

[Host Production](https://commerce-api-production.up.railway.app)

[Host Staging](https://commerce-api-staging.up.railway.app)

- /swagger-ui/index.html
- /api-docs
- /actuator

### Referencias

- Hospedado em: (https://railway.app?referralCode=tPS5tO)
