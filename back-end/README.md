# Configuração do Back-End

## Configuração Inicial

### 1. Configurar `application.properties`

O arquivo `application.properties` contém informações sensíveis e **não está versionado no Git**.

Para configurar o projeto:

1. Copie o template:
   ```bash
   cp src/main/resources/application.properties.template src/main/resources/application.properties
   ```

2. Edite `application.properties` e substitua os valores de exemplo:
   - `YOUR_POSTGRES_USERNAME` → seu usuário do PostgreSQL
   - `YOUR_POSTGRES_PASSWORD` → sua senha do PostgreSQL
   - `YOUR_MONGO_USERNAME` → seu usuário do MongoDB
   - `YOUR_MONGO_PASSWORD` → sua senha do MongoDB
   - `YOUR_EMAIL@gmail.com` → seu email
   - `YOUR_EMAIL_APP_PASSWORD` → senha de app do Gmail
   - `YOUR_WEATHER_API_KEY` → sua chave da API de clima

### 2. Configurar GCS Key (Google Cloud Storage)

Coloque seu arquivo de chave do Google Cloud Storage em:
```
src/main/resources/gcs-key.json
```

## Como Rodar

### Localmente (Recomendado - Java 21)
```powershell
.\run-with-java21.ps1
```

### Via Docker
```bash
docker-compose up
```

## Portas Utilizadas

- **Backend**: 8080
- **Frontend**: 4200
- **PostgreSQL**: 5433 (local) / 5432 (Docker)
- **MongoDB**: 27017

## Requisitos

- Java 21
- Maven 3.9+
- PostgreSQL 17
- MongoDB (latest)
