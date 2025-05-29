# 🚗 Gearborn Motors - Sistema de Gestión de Clientes y Vehículos

**Gearborn Motors** es un sistema de gestión para una concesionaria de vehículos. Este proyecto forma parte de un **Trabajo de Fin de Ciclo (TFC)** y permite el registro, visualización, compra y venta de vehículos desde una interfaz gráfica de escritorio.

> ⚠️ **Estado del proyecto:** En desarrollo activo.  
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

### 👤 Gestión de usuarios
- Registro de nuevos clientes con validación de campos
- Inicio de sesión con contraseña hasheada (MD5)
- Gestión de roles diferenciados: clientes y empleados acceden a paneles distintos

### 🚗 Gestión de vehículos
- Consulta y listado de vehículos disponibles
- Visualización en formato de tarjetas con imágenes y descripciones
- Acceso a detalles ampliados del vehículo
- Validación: vehículos ya vendidos no pueden ser comprados nuevamente

### 💳 Ventas y compras
- Realización de ventas desde el panel de empleados
- Realización de compras desde el panel de clientes (flujo automatizado)
- Panel de empleados con acceso a:
  - Todas las ventas realizadas
  - Todas las compras efectuadas por la empresa

### 💼 Administración
- Registro de gastos asociados a vehículos (por ejemplo: reparaciones, pintura, etc.)
- Consulta del historial de gastos de la empresa

### 🧭 Navegación y estructura
- Navegación fluida entre vistas: login, registro, catálogo, detalles, administración
- Separación clara por capas: DTO ↔ Mapper ↔ Entity ↔ Repositorio
- Comunicación entre frontend y backend mediante solicitudes HTTP (REST)

---

## 🔮 Funcionalidades futuras

### Para empleados
- Aprobación de solicitudes de **venta de vehículos enviadas por clientes**
- Aprobación de solicitudes de **compra de vehículos realizadas por clientes**
- Registro manual de nuevos **gastos empresariales** asociados a vehículos
- Consulta del historial de:
  - Ventas de la empresa
  - Gastos generales
  - Gastos específicos por vehículo
- Suspensión permanente de un cliente o empleado desde el panel administrativo

### Para clientes
- Consulta de historial de compras personales (por implementar mediante consulta por ID de cliente)
- Solicitud de venta de su propio vehículo (requiere aprobación del empleado)
