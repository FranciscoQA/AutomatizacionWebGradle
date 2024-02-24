#language: es
Característica: Inicio de sesion
  Yo como analista de negocio necesito iniciar sesión en
  el sitio web con mis credenciales de acceso.

  @InicioSesionExitoso
  Escenario: Iniciar sesion exitosamente
    Dado que el usuario navega a la pagina de inicio de sesion
    Cuando ingresa las credenciales de acceso correctas
    Entonces deberia ver la pagina principal