# Proyecto de la optativa Web Applicaction Development

Sistema web realizado con Spring Boot, el cual simula una tienda online en el que interactuan el cliente y en vendedor, el vendedor puede publicar sus productos, cada producto tiene que registrar el estado y municipio en donde lo comercializará, posteriormente el cliente podra comprar los prodcutos que desee. 

### Requerimientos
- Acceso a datos mediante un ORM, JPA Hibernate
- Patrón de  diseño MVC
- Desarrollo bajo Spring Boot
- Envio de correos electrónicos
- Generación de reportes
- Manejo de sesiones
- Gestión de usuarios

### API
|#|Nombre|Descripción|
|----|----|----|
|1|/|Inicio de sesión|
|2|/login|Inicio de sesión|
|3|/registration|Registro de usuario|
|4|/admin/home|Página de inicio de administrador|
|5|/seller/home|Página de inicio de vendedor|
|6|/seller/productlist|Lista de productos|
|7|/seller/formProduct|Formulario de registro de producto|
|8|/seller/getTowns/|Obtener municipios con base a id de estado|
|9|/consumer/home|Página de inicio de cliente|
|10|/consumer/productlist|Obtener todos los productos disponibles|
|11|/consumer/buy/{id}|Comprar producto, disminuye en 1 unidad el stock del vendedor|
|12|-|-|
|13|-|-|
|14|-|-|


### Modelo de datos
![Imagen](https://github.com/AaronGG11/Web-application-development/blob/master/Proyecto/Imagenes/bd.png?raw=true) 


