#!/bin/bash

# Script para versionamento automático
# Uso: ./version.sh [major|minor|patch]

set -e

VERSION_FILE="VERSION"
POM_FILE="pom.xml"

if [ ! -f "$VERSION_FILE" ]; then
    echo "1.0.0" > "$VERSION_FILE"
fi

CURRENT_VERSION=$(cat $VERSION_FILE)
echo "Versão atual: $CURRENT_VERSION"

# Parse da versão atual
IFS='.' read -r -a VERSION_PARTS <<< "$CURRENT_VERSION"
MAJOR=${VERSION_PARTS[0]}
MINOR=${VERSION_PARTS[1]}
PATCH=${VERSION_PARTS[2]}

case $1 in
    major)
        MAJOR=$((MAJOR + 1))
        MINOR=0
        PATCH=0
        ;;
    minor)
        MINOR=$((MINOR + 1))
        PATCH=0
        ;;
    patch)
        PATCH=$((PATCH + 1))
        ;;
    *)
        echo "Uso: $0 [major|minor|patch]"
        echo "  major: 1.0.0 -> 2.0.0"
        echo "  minor: 1.0.0 -> 1.1.0"
        echo "  patch: 1.0.0 -> 1.0.1"
        exit 1
        ;;
esac

NEW_VERSION="$MAJOR.$MINOR.$PATCH"
echo "Nova versão: $NEW_VERSION"

# Atualizar VERSION file
echo "$NEW_VERSION" > "$VERSION_FILE"

# Atualizar pom.xml
sed -i "s/<version>.*-SNAPSHOT<\/version>/<version>$NEW_VERSION-SNAPSHOT<\/version>/" "$POM_FILE"

# Git operations
git add .
git commit -m "chore: bump version to $NEW_VERSION"
git tag -a "v$NEW_VERSION" -m "Version $NEW_VERSION"

echo "✅ Versão $NEW_VERSION criada com sucesso!"
echo "📝 Não esqueça de atualizar o CHANGELOG.md"
echo "🚀 Para publicar: git push && git push --tags"
