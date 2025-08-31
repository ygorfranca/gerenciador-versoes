# Configuração para ambiente de desenvolvimento com Docker

# Para iniciar o ambiente completo:
docker-compose up -d

# Para iniciar apenas o banco:
docker-compose up -d postgres

# Para parar o ambiente:
docker-compose down

# Para rebuild da aplicação:
docker-compose build app

# URLs de Acesso:
# - API: http://localhost:8080
# - Swagger: http://localhost:8080/swagger-ui
# - PgAdmin: http://localhost:5050 (admin@admin.com / admin)

# Configuração do PgAdmin:
# - Host: postgres
# - Port: 5432
# - Database: gerenciador_versoes
# - Username: postgres
# - Password: postgres
