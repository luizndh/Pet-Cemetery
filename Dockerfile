# =========================================================================
# ESTÁGIO 1: Construir a Aplicação Angular (Frontend)
# Usamos uma imagem oficial do Node.js (versão 20 LTS em base Alpine para ser leve).
# O nome "frontend-builder" é um alias para este estágio.
# =========================================================================
FROM node:20-alpine AS frontend-builder

# Define o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copia os arquivos de definição de dependências do Angular
COPY front-end/package*.json ./

# Instala todas as dependências do Node.js. Este passo aproveita o cache do Docker.
RUN npm install

# Copia todo o código-fonte do frontend para o contêiner
COPY front-end/ ./

# Executa o build de produção do Angular. Os arquivos finais otimizados
# serão gerados na pasta /app/dist/
# O nome 'frontend' em 'dist/frontend' deve corresponder ao nome do seu projeto no angular.json
RUN npm run build

# =========================================================================
# ESTÁGIO 2: Construir a Aplicação Spring Boot (Backend)
# Usamos uma imagem oficial do Maven com o JDK Temurin 20.
# "backend-builder" é o alias para este estágio.
# =========================================================================
FROM maven:3.9-eclipse-temurin-20 AS backend-builder

# Define o diretório de trabalho
WORKDIR /app

# Copia o arquivo de definição do projeto Maven
COPY back-end/pom.xml .

# Baixa as dependências do Maven. Este passo também é cacheado para acelerar builds futuras.
RUN mvn dependency:go-offline

# Copia o código-fonte do backend
COPY back-end/src ./src

# AQUI ESTÁ A MÁGICA:
# Copia os arquivos estáticos do frontend (construídos no ESTÁGIO 1) para a pasta
# de recursos estáticos do Spring Boot. Desta forma, o Maven irá empacotá-los DENTRO do JAR final.
# Altere 'dist/front-end' se o nome do seu projeto Angular no angular.json for diferente.
COPY --from=frontend-builder /app/dist/front-end/browser /app/src/main/resources/static

# Constrói a aplicação Spring Boot, empacotando-a em um JAR.
# -DskipTests pula a execução de testes, comum em ambientes de CI/CD.
RUN mvn clean package -DskipTests

# =========================================================================
# ESTÁGIO 3: Imagem Final de Produção
# Usamos uma imagem Java Runtime Environment (JRE), que é muito menor que o JDK.
# A base Alpine é mínima, resultando em uma imagem final extremamente leve.
# =========================================================================
FROM eclipse-temurin:20-jre-alpine

# Define o diretório de trabalho na imagem final
WORKDIR /app

# Define um argumento para o nome do JAR que pode ser passado durante o build
ARG JAR_FILE=target/*.jar

# Copia APENAS o JAR final (que já contém o frontend) do estágio de build do backend
COPY --from=backend-builder /app/${JAR_FILE} pet-cemetery.jar

# Expõe a porta padrão do Spring Boot (8080) para o mundo exterior
EXPOSE 8080

# Comando final para executar a aplicação quando o contêiner for iniciado
ENTRYPOINT ["java", "-jar", "pet-cemetery.jar"]