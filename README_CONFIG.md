# Módulo M01: Gestión de Inicio y Acceso al Sistema

## Descripción
Implementación completa del módulo de autenticación y gestión de usuarios para el sistema Territorio de Niños, incluyendo:
- Autenticación con JWT
- CRUD de usuarios con eliminación lógica
- Control de acceso basado en roles
- Documentación automática con Swagger/OpenAPI

## Requisitos Previos
- **Java**: 21 o superior
- **Maven**: 3.6+
- **PostgreSQL**: 12 o superior

## Pasos de Configuración

### 1. Crear la Base de Datos
```bash
# Con el usuario postgres
psql -U postgres -c "CREATE DATABASE territorioNinos WITH ENCODING 'UTF8';"
```

### 2. Configurar la Conexión en `application.properties`
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/territorioNinos
spring.datasource.username=postgres
spring.datasource.password=TU_CONTRASEÑA
```

### 3. Generar una Clave JWT Segura
Ejecutar en Java REPL o en la aplicación:
```java
import java.util.Base64;
import java.security.SecureRandom;

SecureRandom random = new SecureRandom();
byte[] bytes = new byte[32];
random.nextBytes(bytes);
String secretKey = Base64.getEncoder().encodeToString(bytes);
System.out.println("jwt.secret=" + secretKey);
```

Actualizar en `application.properties`:
```properties
jwt.secret=TU_CLAVE_GENERADA_AQUI
```

### 4. Compilar el Proyecto
```bash
./mvnw clean compile
```

### 5. Ejecutar Migraciones
Ejecutar el script de inicialización con psql:
```bash
psql -U postgres -d territorioNinos -f src/main/resources/init-db.sql
```

O ejecutar manualmente en PostgreSQL:
```sql
INSERT INTO roles (nombre, descripcion, fecha_creacion) VALUES
('ADMINISTRADOR', 'Administrador del sistema', NOW()),
('TAQUILLERO', 'Encargado de taquilla', NOW()),
('OPERARIO', 'Operario del sistema', NOW());
```

### 6. Iniciar la Aplicación
```bash
./mvnw spring-boot:run
```

La aplicación estará disponible en: `http://localhost:8080/territorioninos`

## Acceso a Swagger
**URL**: http://localhost:8080/territorioninos/swagger-ui.html

## Credenciales por Defecto
**Email**: admin@territoriodeniños.com  
**Contraseña**: Admin123456

## Flujo de Autenticación

### 1. Login
```bash
curl -X POST http://localhost:8080/territorioninos/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"correo":"admin@territoriodeniños.com","password":"Admin123456"}'
```

**Respuesta**:
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "tipoToken": "Bearer",
  "correo": "admin@territoriodeniños.com",
  "rol": "ADMINISTRADOR"
}
```

### 2. Usar Token para Requests Protegidos
```bash
curl -X GET http://localhost:8080/territorioninos/api/usuarios \
  -H "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
```

## Estructura de Respuestas

### Usuario (UsuarioResponseDTO)
```json
{
  "id": 1,
  "nombre": "Juan Pérez",
  "correo": "juan@example.com",
  "estado": "ACTIVO",
  "rol": "TAQUILLERO",
  "fechaCreacion": "2024-06-30T10:15:30",
  "fechaActualizacion": null
}
```

### Error (ErrorResponse)
```json
{
  "codigo": 400,
  "mensaje": "Error de validación",
  "detalle": "nombre: El nombre debe tener entre 3 y 100 caracteres",
  "timestamp": "2024-06-30T10:15:30"
}
```

## Permisos por Rol

| Endpoint | Método | ADMINISTRADOR | TAQUILLERO | OPERARIO |
|----------|--------|---------------|-----------|----------|
| /api/usuarios | POST | ✅ | ❌ | ❌ |
| /api/usuarios | GET | ✅ | ✅ | ✅ |
| /api/usuarios/{id} | GET | ✅ | ✅ | ✅ |
| /api/usuarios/{id} | PUT | ✅ | ✅ | ❌ |
| /api/usuarios/{id} | DELETE | ✅ | ❌ | ❌ |

## Buenas Prácticas Implementadas

✅ **Separación de responsabilidades**: Cada capa tiene su responsabilidad clara  
✅ **Bajo acoplamiento**: Interfaces y inyección de dependencias  
✅ **DTOs**: Transferencia segura de datos sin exponer entidades  
✅ **Validación**: Bean Validation + validación de negocio  
✅ **Seguridad**: BCrypt, JWT, CORS, control de acceso  
✅ **Documentación**: Swagger con descripciones detalladas  
✅ **Manejo de errores**: GlobalExceptionHandler centralizado  
✅ **Eliminación lógica**: Preservación de datos históricos  

## Solución de Problemas

### "FATAL: database 'territorioNinos' does not exist"
```bash
# Crear la BD manualmente
psql -U postgres -c "CREATE DATABASE territorioNinos WITH ENCODING 'UTF8';"
```

### "FATAL: Ident authentication failed for user 'postgres'"
- Asegúrate de que PostgreSQL está corriendo
- Actualiza credenciales en `application.properties`
- Verifica el archivo `pg_hba.conf` en la configuración de PostgreSQL

### "Invalid JWT token"
- Verificar que el token no ha expirado (24 horas)
- Verificar que la clave JWT coincide

## Próximos Pasos
- Crear módulos adicionales según requerimientos
- Implementar tests unitarios e integración
- Configurar CI/CD
- Documentar APIs adicionales

---

**Versión**: 1.0.0  
**Última actualización**: 30 de junio de 2024
