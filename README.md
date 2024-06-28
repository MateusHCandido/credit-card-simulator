# card simulate

## Objetivo

Criar serviço que simula criação de um cartão de crédito utilizando arquitetura de micro serviços, mensageria, controle 
de rotas e supervisão de registro de instâncias

## Tecnologias e padrões de desenvolvimento


### Tecnologias

- Java 11
- Spring Boot
- Sprign Cloud
- Spring Security
- Spring Data
- RabbitMQ
- Eureka
- JUnit
- Mockito
- Keycloak
- Postman
- Docker
- Lombok
- ModelMapper
- H2 Database

###  Padrões de desenvolimento

- Arquitetura limpa
- SOLID
- TDD


## Serviços


### cloud-gateway-service

Controla as requisições dos serviços implementados, garantindo que as requisições realizadas, primeiro passem por ele, 
fornecend segurança e monitoramento das requisições.

### eureka-server

Estabelece a criação de uma instância que permite múltiplos registros dos serviços, garantindo que ocorra a supervisão do
serviço, monitorando seu uso, assim como o balanceamento de carga durante as requisições, ou seja, em caso de múltiplas 
requisições para o mesmo serviço, as instâncias ativas irão dividir suas requisições, garantindo que elas não se
sobrecarreguem.

### card-generating-service

Serviço responsável por:

- criar cartão
- listar todos os cartões
- listar todos os cartões ativos
- listar todos os cartões bloqueados
- listar todos os cartões cancelados
- buscar um cartão específico
- ativar cartão
- bloquear cartão
- cancelar cartão


### credit-assessment-service

Serviço responsável pela verificação do cliente, para chegar possibilidade de aquisição do cartão, avaliar o crédito que 
será disponibilizado para o cartão solicitado pelo cliente, e também responsável pela solicitação do cartão, enviando
uma publicação via serviço de mensageria para o serviço card-generating-service, para que seja efetuado o envio do cartão
para o cliente e vincular o cartão gerado ao mesmo.


### customer-service 

Serviço responsável pela criação do cliente que irá consumir os demais serviços


## Observações

### Documentação das APIs

Utilizei o swagger para documenta-las

```http://localhost:(porta utilizada pelo serviço)/swagger-ui/index.html```




