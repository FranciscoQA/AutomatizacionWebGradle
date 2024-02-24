#language: es
Característica: Inicio de sesion
  Como analista de negocio
  Quiero iniciar sesión en el sitio web
  Para registar las credenciales de acceso.

  @CASO1
  Esquema del escenario: Iniciar sesion exitosamente
    Dado Un usuario navega a la pagina de inicio de sesion
    Cuando elijo una "<sCategoria>" y "<sSubCategoria>"
    Y selecciono cinco productos aleatorios
      | sProductos                                           | sUnidades |
      | Celular SAMSUNG Galaxy A34 5G 128 GB 6 GB  RAM Negro | 2         |
      | Celular SAMSUNG Galaxy A04 128 GB 4 GB  RAM Negro    | 1         |
      | Celular Samsung Galaxy A54 256Gb 8Gb Ram Blanco      | 3         |
      | Celular SAMSUNG GALAXY A24 128 GB 4 GB RAM Verde     | 2         |
      | Celular SAMSUNG Galaxy A34 5G 128 GB 6 GB  RAM Plata | 3         |
    Y nos dirigimos al carrito de compras
    Entonces Valido que los nombres de los productos agregados sean igual que en el carro
    Y Valido que el total de los precios agregados sean igual que en el carrito
    Y Valido que las cantidades de los productos agregados sean igual que en el carrito
    Y Valido que el numero de productos agregados debe ser igual que en el carrito
    Ejemplos:
      | sCategoria | sSubCategoria |
      | Celulares  | Samsung       |

  @CASO2
  Esquema del escenario: Iniciar sesion exitosamente
    Dado Un usuario navega a la pagina de inicio de sesion
    Cuando elijo una "<sCategoria>" y "<sSubCategoria>"
    Y selecciono cinco productos aleatorios
      | sProductos   | sUnidades   |
      | <sProductos> | <sUnidades> |
    Y nos dirigimos al carrito de compras
    Entonces Valido que los nombres de los productos agregados sean igual que en el carro
      | sProductos   |
      | <sProductos> |
    Y Valido que el total de los precios agregados sean igual que en el carrito
      | sProductos   |
      | <sProductos> |
    Y Valido que las cantidades de los productos agregados sean igual que en el carrito
   # Y Valido que el numero de productos agregados debe ser igual que en el carrito
    Ejemplos:
      | sCategoria | sSubCategoria | sProductos                                           | sUnidades |
      | Celulares  | Samsung       | Celular SAMSUNG Galaxy A34 5G 128 GB 6 GB  RAM Negro | 3         |