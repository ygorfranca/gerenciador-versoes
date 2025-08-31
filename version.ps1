# Script PowerShell para versionamento automático
# Uso: .\version.ps1 [major|minor|patch]

param(
    [Parameter(Mandatory=$true)]
    [ValidateSet("major", "minor", "patch")]
    [string]$VersionType
)

$ErrorActionPreference = "Stop"

$VersionFile = "VERSION"
$PomFile = "pom.xml"

# Criar arquivo VERSION se não existir
if (-not (Test-Path $VersionFile)) {
    "1.0.0" | Out-File -FilePath $VersionFile -Encoding UTF8
}

$CurrentVersion = Get-Content $VersionFile -Raw | Trim
Write-Host "Versão atual: $CurrentVersion" -ForegroundColor Yellow

# Parse da versão atual
$VersionParts = $CurrentVersion.Split('.')
$Major = [int]$VersionParts[0]
$Minor = [int]$VersionParts[1] 
$Patch = [int]$VersionParts[2]

# Incrementar versão baseado no tipo
switch ($VersionType) {
    "major" {
        $Major++
        $Minor = 0
        $Patch = 0
    }
    "minor" {
        $Minor++
        $Patch = 0
    }
    "patch" {
        $Patch++
    }
}

$NewVersion = "$Major.$Minor.$Patch"
Write-Host "Nova versão: $NewVersion" -ForegroundColor Green

# Atualizar arquivo VERSION
$NewVersion | Out-File -FilePath $VersionFile -Encoding UTF8

# Atualizar pom.xml
$PomContent = Get-Content $PomFile -Raw
$PomContent = $PomContent -replace '<version>.*-SNAPSHOT</version>', "<version>$NewVersion-SNAPSHOT</version>"
$PomContent | Out-File -FilePath $PomFile -Encoding UTF8

# Operações Git
Write-Host "Criando commit e tag..." -ForegroundColor Cyan
git add .
git commit -m "chore: bump version to $NewVersion"
git tag -a "v$NewVersion" -m "Version $NewVersion"

Write-Host "✅ Versão $NewVersion criada com sucesso!" -ForegroundColor Green
Write-Host "📝 Não esqueça de atualizar o CHANGELOG.md" -ForegroundColor Yellow
Write-Host "🚀 Para publicar: git push && git push --tags" -ForegroundColor Magenta
