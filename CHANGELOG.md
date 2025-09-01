# Changelog

Todas as mudanças notáveis neste projeto serão documentadas neste arquivo.

O formato é baseado em [Keep a Changelog](https://keepachangelog.com/pt-BR/1.0.0/),
e este projeto adere ao [Semantic Versioning](https://semver.org/lang/pt-BR/).

## [2.0.0] - 2025-08-31

### 🚀 Mudanças Importantes
- **BREAKING**: Arquitetura de tratamento de exceções melhorada
- **BREAKING**: Respostas de erro da API aprimoradas com JSON estruturado
- **BREAKING**: Códigos de status HTTP agora seguem corretamente os padrões REST

### ✨ Adicionado
- **Exception Handlers Globais**: Sistema centralizado de tratamento de erros
  - `ResourceNotFoundExceptionMapper`: Retorna HTTP 404 para recursos não encontrados
  - `BusinessRuleExceptionMapper`: Retorna HTTP 400 para violações de regras de negócio
  - `ConstraintViolationExceptionMapper`: Retorna HTTP 400 para erros de validação
  - `GeneralExceptionMapper`: Retorna HTTP 500 para erros inesperados
- **Respostas de Erro Aprimoradas**: Formato JSON estruturado com timestamps
- **Integração Lombok**: Código boilerplate reduzido em todos os DTOs e entidades
- **Infraestrutura DevOps**:
  - Pipeline CI/CD GitHub Actions com testes e builds automatizados
  - Suporte Docker com Dockerfiles para JVM e imagem nativa
  - Docker Compose para ambiente de desenvolvimento local
- **Melhorias de Qualidade de Código**:
  - Tratamento de exceções consistente em todos os services
  - Semântica adequada de códigos de status HTTP
  - Validação e mensagens de erro aprimoradas

### 🔧 Alterado
- **Tratamento de Exceções**: 
  - Substituído `NotFoundException` por `ResourceNotFoundException` em todos os services
  - Substituído `IllegalArgumentException` por `BusinessRuleException` onde apropriado
- **Classes Resource**: Simplificadas removendo blocos try-catch (tratados globalmente)
- **Classes Service**: Atualizadas para usar tipos de exceção apropriados
- **Classes Model**: Aprimoradas com anotações Lombok (`@Data`, `@EqualsAndHashCode`)

### 🐛 Corrigido
- **Bug Crítico**: Recursos não encontrados agora retornam HTTP 404 em vez de HTTP 500
- **Consistência da API**: Todos os endpoints seguem convenções adequadas de status HTTP REST
- **Mensagens de Erro**: Clareza e consistência das mensagens de erro melhoradas

### 🛠️ Débito Técnico
- Removidos blocos try-catch redundantes das classes Resource
- Padronizados padrões de tratamento de exceções
- Melhorada manutenibilidade do código com Lombok

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
