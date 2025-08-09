# 📚 Sistema de Gestión de Libros

Proyecto desarrollado en **Java** con **Spring Boot** y **PostgreSQL**, que consume la API pública [Gutendex](https://gutendex.com) para buscar y gestionar información de libros.

## 🚀 Características

- 🔍 **Búsqueda de libros** en la API de Gutendex.
- 💾 **Almacenamiento** de libros en base de datos PostgreSQL.
- 📜 **Listado** de libros guardados.
- 📈 **Consulta** de autores y estadísticas.
- 🛠 **Arquitectura limpia** con Java + Spring Boot.

## 🛠 Tecnologías utilizadas

- ☕ **Java 17**
- 🌱 **Spring Boot**
- 🐘 **PostgreSQL**
- 🌐 **Gutendex API**
- 🛢 **JPA / Hibernate**
- 🐙 **Git & GitHub**

## 📂 Estructura del proyecto


## ⚙️ Configuración

1. Clonar el repositorio:
   ```bash
   git clone https://github.com/tuusuario/nombre-del-proyecto.git
   
2. Configurar application.properties:
spring.datasource.url=jdbc:postgresql://localhost:5432/nombre_db
spring.datasource.username=usuario
spring.datasource.password=contraseña
spring.jpa.hibernate.ddl-auto=update
3. Iniciar el servidor:
   mvn spring-boot:run

## Edpoints principales:
   | Método | Endpoint   | Descripción                      |
   | ------ | ---------- | -------------------------------- |
   | GET    | `/libros`  | Lista todos los libros guardados |
   | POST   | `/libros`  | Guarda un libro de Gutendex      |
   | GET    | `/autores` | Lista todos los autores          |

