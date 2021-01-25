# Proyecto de la optativa Web Applicaction Development [Proyecto en postgresql]

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
|5|/admin/reportList|Página de acceso a reportes disponibles|
|6|/admin/SellerReport|Generar reporte de usuarios "seller"|
|7|/admin/ConsumerReport|Generar reporte de usuarios "consumer"|
|8|/admin/graph|Gráficar usuarios totales y por rol|
|9|/seller/home|Página de inicio de vendedor|
|10|/seller/productlist|Lista de productos|
|11|/seller/formProduct|Formulario de registro de producto|
|12|/seller/saveProduct|Guardar producto nuevo o actualización|
|13|/selller/view/{id}|Página de visualización de producto con base a Id|
|14|/seller/delete/{id}|Eliminar producto con base a Id|
|15|/seller/update/{id}|Página de formulario de producto con campos precargados para actualización|
|16|/seller/getTowns/|Obtener municipios con base a id de estado|
|17|/consumer/home|Página de inicio de cliente|
|18|/consumer/productlist|Obtener todos los productos disponibles|
|19|/consumer/buy/{id}|Comprar producto, disminuye en 1 unidad el stock del vendedor|
|20|-|-|
|21|-|-|
|22|-|-|


### Modelo de datos
![Imagen](https://github.com/AaronGG11/Web-application-development/blob/master/Proyecto/Imagenes/bd.png?raw=true) 


