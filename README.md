# Baozi Store API

Atividade prática da disciplina Desenvolvimento Web Back End.

**Aluno:** APOLLO HOINATZ BARDINI  
**RU:** 5586086  
**Curso:** CST ANÁLISE E DESENVOLVIMENTO DE SISTEMAS - DISTÂNCIA (2701)

## Tecnologias
- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- H2 Database
- Maven

## Execução
Abra o projeto em uma IDE com JDK 17 ou superior e execute `BaoziStoreApplication.java`. A API ficará disponível em `http://localhost:8080`.

## Endpoints
### Clientes
- `POST /clientes`
- `GET /clientes`
- `GET /clientes/{id}`
- `PUT /clientes/{id}`
- `DELETE /clientes/{id}`

### Produtos
- `POST /produtos`
- `GET /produtos`
- `GET /produtos/{id}`
- `PUT /produtos/{id}`
- `DELETE /produtos/{id}`

### Pedidos
- `POST /pedidos`
- `GET /pedidos`
- `GET /pedidos/{id}`
- `PUT /pedidos/{id}`
- `DELETE /pedidos/{id}`

A coleção `Baozi_Store.postman_collection.json` contém as requisições utilizadas nos testes.
