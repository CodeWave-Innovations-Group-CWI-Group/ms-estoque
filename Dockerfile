# Imagem base com JDK 17 (pode trocar para 21 se usar Java 21)
FROM eclipse-temurin:21-jdk

# Define diretório de trabalho
WORKDIR /app

# Copia o projeto
COPY . .

# Dá permissão de execução ao mvnw
RUN chmod +x mvnw

# Builda o projeto (sem rodar testes)
RUN ./mvnw clean package -DskipTests

# Expõe a porta que o Render usa
EXPOSE 8080

# Comando de inicialização
CMD ["sh", "-c", "java -jar target/*.jar"]
