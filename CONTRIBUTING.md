# Configuração Git Flow para Gerenciador de Versões

## Branches

- **main/master**: Código de produção
- **develop**: Branch de desenvolvimento
- **feature/nome-da-funcionalidade**: Novas funcionalidades
- **release/x.y.z**: Preparação para release
- **hotfix/x.y.z**: Correções urgentes em produção

## Workflow

### 1. Nova Funcionalidade
```bash
git checkout develop
git checkout -b feature/nova-funcionalidade
# ... desenvolver ...
git checkout develop
git merge feature/nova-funcionalidade
git branch -d feature/nova-funcionalidade
```

### 2. Preparar Release
```bash
git checkout develop
git checkout -b release/1.1.0
# ... ajustes finais ...
git checkout master
git merge release/1.1.0
git tag -a v1.1.0 -m "Release 1.1.0"
git checkout develop
git merge release/1.1.0
git branch -d release/1.1.0
```

### 3. Hotfix
```bash
git checkout master
git checkout -b hotfix/1.0.1
# ... corrigir bug ...
git checkout master
git merge hotfix/1.0.1
git tag -a v1.0.1 -m "Hotfix 1.0.1"
git checkout develop
git merge hotfix/1.0.1
git branch -d hotfix/1.0.1
```

## Versionamento Automático

Use os scripts fornecidos:
```bash
# PowerShell (Windows)
.\version.ps1 patch   # 1.0.0 -> 1.0.1
.\version.ps1 minor   # 1.0.0 -> 1.1.0
.\version.ps1 major   # 1.0.0 -> 2.0.0

# Bash (Linux/Mac)
./version.sh patch
./version.sh minor
./version.sh major
```
