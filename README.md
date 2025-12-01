# Proyecto M6 Evaluación Portafolio

## Cómo ejecutar el proyecto

1. Ejecuta la clase principal del proyecto.
2. La aplicación se inicia en el puerto 9093 (configurar el puerto de uso).
3. Accede al sistema ingresando a la ruta:

   ```
   http://localhost:9093/login
   ```

## Credenciales preconfiguradas

En la clase principal se encuentran definidos los beans con las credenciales de acceso:

**Administrador**

* Usuario: [admin@cursos.com]
* Contraseña: admin123

**Empleado**

* Usuario: [empleado@cursos.com]
* Contraseña: empleado123

## Funcionamiento según el rol

### Rol Administrador

Al iniciar sesión como administrador se despliega una ventana donde se listan todos los cursos disponibles.
Cada curso muestra botones para **editar** y **eliminar**.
Los instructores asociados a los cursos fueron definidos mediante beans en la clase principal.

### Rol Empleado

Al iniciar sesión como empleado se visualizan todos los cursos disponibles, cada uno con un único botón: **inscribirse**.
Al hacer clic en “inscribirse”, se registra la inscripción y aparece un mensaje confirmando la operación.

## Endpoints expuestos

### GET /api/cursos

Devuelve el listado completo de cursos disponibles.

### POST /api/inscripciones

Registra una inscripción recibiendo los parámetros:

* empleadoId
* cursoId

Estos endpoints están asegurados mediante autenticación Basic Auth y permiten solicitudes externas gracias a la anotación `@CrossOrigin`.
