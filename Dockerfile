# Use a imagem oficial do OpenJDK para criar a imagem base.
FROM openjdk:11-jdk-slim

# Variáveis de ambiente para a aplicação Java
ENV JAVA_OPTS=""

# Porta em que a aplicação será exposta
EXPOSE 8080

# O diretório de trabalho dentro do container Docker
WORKDIR /app

# Copia o arquivo jar para dentro do container Docker
COPY target/*.jar app.jar

# Comando para executar a aplicação Java
ENTRYPOINT exec java $JAVA_OPTS -jar /app/app.jar