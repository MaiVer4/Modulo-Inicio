# 🏰 Territorio de Niños — API Backend

API RESTful desarrollada con **Spring Boot 4.0.6** y **Java 21 LTS** para el sistema de gestión **Territorio de Niños**.

---

## 🚀 Inicio Rápido

### Requisitos
- **Java 21**
- **PostgreSQL 12+**

### Ejecución Local
```bash
# 1. Crear base de datos
psql -U postgres -c "CREATE DATABASE \"territorioNinos\";"

# 2. Compilar y ejecutar pruebas
./mvnw clean test

# 3. Iniciar la aplicación
./mvnw spring-boot:run
```

- **API Base**: `http://localhost:8080/territorioninos`
- **Swagger UI**: `http://localhost:8080/territorioninos/swagger-ui.html`
- **OpenAPI Specs (para Postman)**: `http://localhost:8080/territorioninos/v3/api-docs`

---

## 📖 Documentación Completa

Para la guía detallada de instalación, variables de entorno, matriz de permisos y flujo de autenticación cURL / Postman, consulta la [Guía de Configuración](README_CONFIG.md).
