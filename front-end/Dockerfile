# Arquivo: frontend/Dockerfile

# Estágio 1: Build do Angular
FROM node:20 AS builder
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .

# O nome 'front-end' aqui deve ser o nome do seu projeto no angular.json
RUN npm run build --configuration front-end

# Estágio 2: Servidor de Produção Nginx
FROM nginx:stable-alpine
# Copia os arquivos compilados do Angular para a pasta padrão do Nginx
COPY --from=builder /app/dist/front-end/browser /usr/share/nginx/html
# Copia a configuração customizada do Nginx
COPY nginx.conf /etc/nginx/conf.d/default.conf
EXPOSE 80