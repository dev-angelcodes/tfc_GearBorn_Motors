# 🚗 Gearborn Motors - Sistema de Gestión de Clientes y Vehículos

**Gearborn Motors** es un sistema de gestión para una concesionaria de vehículos. Este proyecto forma parte de un **Trabajo de Fin de Ciclo** (TFC) y permite el registro, visualización y control de clientes y vehículos desde una interfaz gráfica.

> ⚠️ **Estado del proyecto:** En desarrollo.  
> Se siguen incorporando funcionalidades, validaciones y mejoras visuales.

---

## 🧱 Tecnologías utilizadas

### 🔙 Backend
- Java 21
- Spring Boot
- Spring Data JPA (Hibernate)
- MySQL
- Maven

### 💻 Frontend
- JavaFX (Interfaz de escritorio)
- FXML
- Gson (JSON)

### 🧪 Complementarias
- Lombok
- Control de versiones: Git
- Arquitectura: MVC + REST

---

## ✨ Funcionalidades implementadas

- Registro de nuevos clientes con validación de campos
- Inicio de sesión (login) de cliente con contraseña hasheada (MD5)
- Consulta y listado de vehículos disponibles
- Navegación entre vistas en el frontend (pantallas: login, registro, dashboard)
- Comunicación entre frontend y backend mediante solicitudes HTTP (REST)
- Separación de lógica por capas: DTO ↔ Mapper ↔ Entity ↔ Repositorio
