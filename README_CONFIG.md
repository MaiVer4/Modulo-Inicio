# 🏰 Territorio de Niños — Módulo M01: Gestión de Inicio y Acceso

## 📋 Descripción
Implementación del módulo de autenticación, autorización y gestión de usuarios para el sistema **Territorio de Niños**. 

### 🌟 Características Clave:
- 🔑 **Autenticación Stateless JWT**: Firma HS256 con jjwt 0.12.3.
- 🛡️ **Control de Acceso Basado en Roles (RBAC)**: Roles `ADMINISTRADOR`, `TAQUILLERO` y `OPERARIO`.
- 🔄 **CRUD de Usuarios**: Registro, consulta, edición y eliminación lógica (`INACTIVO`).
- 🔐 **Seguridad de Datos**: Encriptación BCrypt, prevención de enumeración de usuarios y CORS configurado.
- 📜 **Documentación OpenAPI / Swagger**: Swagger UI interactivo e importación directa en Postman.
- 🛠️ **Arquitectura Limpia**: Principios SOLID, Lombok, DTOs inmutables, auditoría JPA y controlador global de excepciones.

---

## 💻 Requisitos del Sistema
- **Java**: 21 LTS
- **Build Tool**: Maven 3.8+ (incluye Maven Wrapper `./mvnw`)
- **Base de Datos**: PostgreSQL 12+

---

## ⚙️ Configuración del Entorno

### 1. Base de Datos
Crear la base de datos en PostgreSQL:
```sql
CREATE DATABASE "territorioNinos" WITH ENCODING 'UTF8';
```

### 2. Variables de Entorno (Opcional)
El proyecto utiliza variables de entorno con valores por defecto para entorno local en [application.properties](file:///home/vera/Downloads/Territrio-De-Ninos/src/main/resources/application.properties):

| Variable | Descripción | Valor por defecto (Dev) |
|---|---|---|
| `DB_URL` | URL JDBC de PostgreSQL | `jdbc:postgresql://localhost:5432/territorioNinos` |
| `DB_USERNAME` | Usuario de base de datos | `postgres` |
| `DB_PASSWORD` | Contraseña de base de datos | `postgres` |
| `JWT_SECRET` | Clave secreta para firmar tokens JWT | Base64 secret key |
| `JWT_EXPIRATION_HOURS` | Tiempo de expiración del token | `24` |

---

## 🚀 Ejecución del Proyecto

### Compilar y Ejecutar Pruebas
```bash
./mvnw clean test
```

### Iniciar Servidor en Desarrollo
```bash
./mvnw spring-boot:run
```

La API estará disponible en:
`http://localhost:8080/territorioninos`

---

## 📚 Documentación de la API

### Swagger UI
Interactúa con los endpoints desde el navegador:
`http://localhost:8080/territorioninos/swagger-ui.html`

### Importar en Postman
Para importar automáticamente la colección en Postman:
1. Abre Postman y selecciona **Import**.
2. Ingresa la URL de OpenAPI:
   `http://localhost:8080/territorioninos/v3/api-docs`

---

## 🔑 Flujo de Autenticación (cURL)

### 1. Autenticarse (Login)
```bash
curl -X POST http://localhost:8080/territorioninos/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
        "correo": "admin@example.com",
        "password": "Password123"
      }'
```

**Respuesta HTTP 200 OK**:
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9.eyJyb2wiOiJBRE1JTklTVFJBRE9SIiwic3ViIjo...",
  "tipoToken": "Bearer",
  "correo": "admin@example.com",
  "rol": "ADMINISTRADOR"
}
```

### 2. Consumir Endpoints Protegidos
Enviar el token en el header `Authorization: Bearer <TOKEN>`:

```bash
curl -X GET http://localhost:8080/territorioninos/api/usuarios \
  -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9..."
```

---

## 🛡️ Matriz de Permisos por Rol

| Endpoint | Método | Descripción | ADMINISTRADOR | TAQUILLERO | OPERARIO |
|---|---|---|:---:|:---:|:---:|
| `/api/auth/login` | `POST` | Autenticación y obtención de JWT | 🌐 Público | 🌐 Público | 🌐 Público |
| `/api/usuarios` | `POST` | Registrar nuevo usuario | ✅ | ❌ | ❌ |
| `/api/usuarios` | `GET` | Obtener todos los usuarios activos | ✅ | ✅ | ✅ |
| `/api/usuarios/{id}` | `GET` | Obtener usuario por ID | ✅ | ✅ | ✅ |
| `/api/usuarios/{id}` | `PUT` | Actualizar información de usuario | ✅ | ✅ | ❌ |
| `/api/usuarios/{id}` | `DELETE` | Inactivar usuario (soft-delete) | ✅ | ❌ | ❌ |

---

## 🌿 Convención de Commits (Conventional Commits)

El proyecto utiliza un historial de Git atómico y estructurado:
- `chore(setup):` Inicialización del proyecto y configuración Maven.
- `feat(config):` Modificaciones de configuración y properties.
- `feat(domain):` Entidades JPA y enums con auditoría.
- `feat(dto):` Data Transfer Objects con Lombok y validaciones.
- `feat(repository):` Repositorios Spring Data JPA.
- `feat(security):` Componentes de seguridad Spring Security y JWT.
- `feat(service):` Lógica de negocio.
- `feat(controller):` Endpoints REST y respuestas.
- `docs:` Actualizaciones de documentación.

---

**Versión**: 1.0.0  
**Última actualización**: Julio 2026
