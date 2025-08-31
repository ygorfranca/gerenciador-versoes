# Gerenciador de Versões - API REST

[![Version](https://img.shields.io/badge/version-1.0.0-blue.svg)](https://github.com/usuario/gerenciador-versoes)
[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://openjdk.org/projects/jdk/17/)
[![Quarkus](https://img.shields.io/badge/Quarkus-3.3.3-red.svg)](https://quarkus.io/)
[![License](https://img.shields.io/badge/license-MIT-green.svg)](LICENSE)

Sistema para gerenciar versões de software seguindo o padrão **SemVer** (Semantic Versioning).

## 🚀 Stack Tecnológica

- **Java 17**
- **Quarkus 3.3.3**
- **PostgreSQL**
- **Hibernate ORM com Panache**
- **RESTEasy Reactive**
- **SmallRye OpenAPI**

## Funcionalidades

- ✅ **Gestão de Projetos**: CRUD completo para projetos
- ✅ **Gestão de Versões**: Criação e gerenciamento de versões SemVer (Major.Minor.Patch)
- ✅ **Gestão de Releases**: Associação de features e bug fixes a versões
- ✅ **Controle de Status**: Status de versões (draft, published, deprecated)
- ✅ **API REST**: Endpoints REST para todas as funcionalidades
- ✅ **Documentação OpenAPI**: Swagger UI disponível

## Modelo de Dados

### Entidades e Relacionamentos

```
Project (1:N) → Version (1:1) → Release (1:N) → Feature
                                        └─(1:N) → BugFix
```

- **Project**: `id`, `name`, `description`, `slug`
- **Version**: `id`, `major`, `minor`, `patch`, `status`, `project_id`
- **Release**: `id`, `title`, `description`, `released_at`, `version_id`
- **Feature**: `id`, `title`, `description`, `release_id`
- **BugFix**: `id`, `title`, `description`, `release_id`

## Pré-requisitos

- Java 17+
- Maven 3.8+
- PostgreSQL 12+

## Configuração do Banco de Dados

1. Criar banco PostgreSQL:
```sql
CREATE DATABASE gerenciador_versoes;
```

2. Configurar credenciais no `application.properties`:
```properties
quarkus.datasource.username=postgres
quarkus.datasource.password=postgres
quarkus.datasource.jdbc.url=jdbc:postgresql://localhost:5432/gerenciador_versoes
```

## Executando o Projeto

### Modo de Desenvolvimento
```bash
./mvnw quarkus:dev
```

### Compilar e Executar
```bash
./mvnw package
java -jar target/quarkus-app/quarkus-run.jar
```

## Endpoints da API

### Projetos
- `GET /api/projects` - Listar projetos
- `GET /api/projects/{id}` - Buscar projeto por ID
- `GET /api/projects/slug/{slug}` - Buscar projeto por slug
- `POST /api/projects` - Criar projeto
- `PUT /api/projects/{id}` - Atualizar projeto
- `DELETE /api/projects/{id}` - Excluir projeto

### Versões
- `GET /api/versions` - Listar versões
- `GET /api/versions?projectId={id}` - Versões por projeto
- `GET /api/versions?status={status}` - Versões por status
- `GET /api/versions/{id}` - Buscar versão por ID
- `POST /api/versions` - Criar versão
- `PUT /api/versions/{id}` - Atualizar versão
- `DELETE /api/versions/{id}` - Excluir versão

### Releases
- `GET /api/releases` - Listar releases
- `GET /api/releases?recent={limit}` - Releases recentes
- `GET /api/releases/{id}` - Buscar release por ID
- `GET /api/releases/version/{versionId}` - Release por versão
- `POST /api/releases` - Criar release
- `PUT /api/releases/{id}` - Atualizar release
- `DELETE /api/releases/{id}` - Excluir release

### Features
- `GET /api/features` - Listar features
- `GET /api/features?releaseId={id}` - Features por release
- `GET /api/features?title={title}` - Features por título
- `GET /api/features/{id}` - Buscar feature por ID
- `POST /api/features` - Criar feature
- `PUT /api/features/{id}` - Atualizar feature
- `DELETE /api/features/{id}` - Excluir feature

### Bug Fixes
- `GET /api/bugfixes` - Listar bug fixes
- `GET /api/bugfixes?releaseId={id}` - Bug fixes por release
- `GET /api/bugfixes?title={title}` - Bug fixes por título
- `GET /api/bugfixes/{id}` - Buscar bug fix por ID
- `POST /api/bugfixes` - Criar bug fix
- `PUT /api/bugfixes/{id}` - Atualizar bug fix
- `DELETE /api/bugfixes/{id}` - Excluir bug fix

## Documentação da API

Acesse a documentação interativa em:
- **Swagger UI**: http://localhost:8080/swagger-ui
- **OpenAPI Spec**: http://localhost:8080/openapi

## Dados de Teste

O projeto inclui dados iniciais para teste com:
- 3 projetos de exemplo
- 6 versões distribuídas entre os projetos
- 6 releases com features e bug fixes associados

## Estrutura do Projeto

```
src/main/java/com/exemplo/gerenciadorversoes/
├── dto/           # Data Transfer Objects
├── model/         # Entidades JPA
├── resource/      # Controllers REST
└── service/       # Lógica de negócio
```

## Status dos Endpoints

- ✅ Projects CRUD completo
- ✅ Versions CRUD completo
- ✅ Releases CRUD completo
- ✅ Features CRUD completo
- ✅ BugFixes CRUD completo

## 🎉 Sistema 100% Implementado!
Todas as funcionalidades especificadas estão implementadas e funcionais.
