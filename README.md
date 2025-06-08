# 🚗 Gearborn Motors - Sistema de Gestión de Clientes y Vehículos

**Gearborn Motors** es un sistema de gestión para una concesionaria de vehículos. Este proyecto forma parte de un **Trabajo de Fin de Ciclo (TFC)** y permite el registro, visualización, compra y venta de vehículos desde una interfaz gráfica de escritorio.

> ✅ **Estado del proyecto:** Funcional y en ejecución.  
> 🔄 **Actualizaciones previstas:** Se añadirán nuevas funcionalidades descritas en la memoria del proyecto.

---

## 📂 Estructura del proyecto

⚙️ **El sistema está dividido en dos módulos independientes:**

- **🔙 Backend**: API REST desarrollada con Spring Boot y MySQL  
- **💻 Frontend**: Aplicación de escritorio desarrollada con JavaFX

---

## 🧱 Tecnologías utilizadas

### Backend
- Java 21
- Spring Boot
- Spring Data JPA (Hibernate)
- MySQL
- Maven

### Frontend
- JavaFX (Interfaz de escritorio)
- FXML
- Gson (JSON)

### Complementarias
- Lombok
- Control de versiones: Git
- Arquitectura: MVC + REST

---

## ✨ Funcionalidades actuales

### 👤 Gestión de usuarios
- Registro de clientes con validación de campos obligatorios
- Inicio de sesión con contraseña cifrada (MD5)
- Roles diferenciados: cliente y empleado con interfaces personalizadas

### 🚗 Gestión de vehículos
- Visualización del catálogo de vehículos en tarjetas con imágenes
- Detalles ampliados por vehículo
- Validación para evitar la compra de vehículos ya vendidos

### 💳 Procesos de compra y venta
- Compras automáticas desde el panel del cliente
- Ventas gestionadas por empleados
- Visualización de todas las ventas y compras desde el panel de empleados

### 💼 Administración
- Registro de gastos empresariales por vehículo (reparaciones, mantenimiento, etc.)
- Consulta del historial de gastos de la empresa

### 🧭 Navegación y estructura
- Interfaz fluida con navegación entre vistas: login, registro, catálogo, detalle, administración
- Arquitectura por capas: DTO ↔ Mapper ↔ Entity ↔ Repository
- Comunicación entre cliente y servidor vía HTTP REST

---

## 🚀 Funcionalidades futuras (descritas en la memoria)

Las siguientes mejoras se implementarán en futuras versiones del sistema:

1. **🛡 Verificación de compras por empleados**  
   Las solicitudes de compra pasarán por una validación manual en el panel del empleado antes de ser confirmadas. Se notificará por correo electrónico al cliente una vez validada.

2. **📧 Integración de servicios de correo electrónico**  
   El sistema enviará correos automáticos para:
   - Confirmación de registro
   - Cambios de contraseña
   - Promociones o novedades
   - Validación de compras y ventas  
   También incluirá verificación de email durante el registro.

3. **👤 Panel de usuario para gestión personal**  
   Clientes y empleados podrán:
   - Editar sus datos personales
   - Cambiar su contraseña
   - Subir foto de perfil
   - Consultar historial de compras o acciones

4. **📁 Módulo de gestión documental**  
   Permite subir y asociar documentos como:
   - Contratos de venta
   - Documentos de identidad
   - Fichas técnicas de vehículos
   - Facturas y comprobantes

5. **📊 Panel de estadísticas e informes**  
   Vista para administración con gráficas e indicadores:
   - Ventas por períodos
   - Gasto total acumulado
   - Vehículos más vendidos
   - Rendimiento por empleado

6. **🌐 Versión web y despliegue en la nube**  
   Futuro desarrollo de una versión web (React/Angular) y despliegue del sistema como servicio en la nube (AWS, Azure, etc.), permitiendo acceso desde navegador y escalabilidad completa.

---

## 📌 Notas finales

Este sistema está en funcionamiento y cumple con los objetivos del TFC.  
Las funcionalidades futuras detalladas en la memoria serán añadidas progresivamente para ampliar las capacidades del sistema y profesionalizar su despliegue.
