# Casos de prueba - Login

Proyecto: SauceDemoQA
Módulo: Inicio de sesión

## TC001 - Login exitoso

- Objetivo: Verificar que un usuario válido pueda iniciar sesión.
- Datos: standard_user / secret_sauce
- Resultado esperado: El sistema permite el acceso y muestra Products.
- Resultado obtenido: Products.
- Estado: PASS.

## TC002 - Usuario bloqueado

- Objetivo: Verificar que un usuario bloqueado no pueda ingresar.
- Datos: locked_out_user / secret_sauce
- Resultado esperado: Mostrar mensaje indicando que el usuario está bloqueado.
- Resultado obtenido: Mensaje de usuario bloqueado.
- Estado: PASS.

## TC003 - Login sin usuario

- Objetivo: Verificar la validación cuando el campo usuario está vacío.
- Datos: Usuario vacío / secret_sauce
- Resultado esperado: Mostrar Username is required.
- Resultado obtenido: Username is required.
- Estado: PASS.

## TC004 - Login sin contraseña

- Objetivo: Verificar la validación cuando el campo contraseña está vacío.
- Datos: standard_user / Contraseña vacía
- Resultado esperado: Mostrar Password is required.
- Resultado obtenido: Password is required.
- Estado: PASS.

## TC005 - Credenciales incorrectas

- Objetivo: Verificar el rechazo de credenciales inválidas.
- Datos: usuario_incorrecto / clave_incorrecta
- Resultado esperado: Mostrar mensaje de usuario y contraseña incorrectos.
- Resultado obtenido: Mensaje de credenciales incorrectas.
- Estado: PASS.
