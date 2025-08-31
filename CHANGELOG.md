# Changelog

Todas as mudanças notáveis neste projeto serão documentadas neste arquivo.

O formato é baseado em [Keep a Changelog](https://keepachangelog.com/pt-BR/1.0.0/),
e este projeto adere ao [Semantic Versioning](https://semver.org/lang/pt-BR/).

## [Não Lançado]

### Planejado
- Exception handlers globais
- Testes unitários e de integração
- Implementação de cache
- Paginação nos endpoints
- Autenticação e autorização
- Monitoramento e métricas

## [1.0.0] - 2025-08-30

### Adicionado
- Estrutura inicial do projeto com Quarkus
- Modelo de dados completo:
  - Project (Projeto)
  - Version (Versão)
  - Release (Lançamento)
  - Feature (Funcionalidade)
  - BugFix (Correção de Bug)
- APIs REST para Projects e Versions
- Validação de dados com Bean Validation
- Documentação OpenAPI/Swagger
- Banco de dados H2 para desenvolvimento
- Configuração para PostgreSQL em produção
- Dados de exemplo via import.sql

### Recursos
- CRUD completo para projetos
- CRUD completo para versões
- Validação de versionamento semântico (SemVer)
- Validação de slug único para projetos
- Relacionamentos entre entidades
- Endpoints de busca por nome/título
- Interface Swagger UI para testes

### Tecnologias
- Java 17
- Quarkus 3.3.3
- Hibernate ORM com Panache
- PostgreSQL / H2
- RESTEasy Reactive
- Bean Validation
- OpenAPI/Swagger
