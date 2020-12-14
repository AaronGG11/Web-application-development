-- Teacher's Queries 
SELECT categoria.nombrecategoria, count(*)
FROM producto
	INNER JOIN categoria ON 
producto.idcategoria = categoria.idcategoria group by categoria.idcategoria

-- #######################################################
-- Estructura de la tabla Categoria
-- #######################################################
create table Categoria(
	idCategoria serial primary key,
	nombreCategoria varchar(50) not null,
	descripcionCategoria varchar(500) not null
);
‌
-- #######################################################
-- Estructura de la tabla Producto
-- #######################################################
CREATE TABLE Producto (
  idProducto serial primary key,
  nombreProducto varchar(50) NOT NULL,
  descripcionProducto varchar(500) NOT NULL,
  precio float NOT NULL,
  existencia int NOT null,
  idCategoria int not null,
  foreign key (idcategoria) references Categoria(idcategoria) on update cascade on delete cascade
 );

‌
-- #######################################################
-- Estructura de la tabla Usuario
-- #######################################################
CREATE TABLE Usuario (
  idUsuario serial primary key,
  nombre varchar(50) NOT NULL,
  paterno varchar(50) NOT NULL,
  materno varchar(50) NOT NULL,
  email varchar(50) NOT NULL,
  nombreUsuario varchar(20) NOT NULL,
  claveUsuario varchar(20) NOT null,
  tipoUsuario varchar(20));



-- #######################################################
-- Datos de la tabla Categoria
-- #######################################################
insert into Categoria values (1,'Ropa de hombre', 'Comprende todas aquellas prendas que cumplen con tres características básicas: son sencillas, sus colores son lisos y neutros (azul marino, beiges y toda la escala de grises que va del blanco al negro) y por mucho que pase el tiempo siempre están de moda.');
insert into Categoria values (2, 'Telefonia', 'Libres, Por operadora, accesorios, telefonia fija');
insert into Categoria values (3, 'Computo', 'Equipos de cómputo de última generación');

insert into Categoria values (4,'Video Juegos', 'Juega con lo más nuevo de Xbox One, PS4 y Nintendo Switch al mejor precio, contamos con los mejores títulos de videojuegos del año.');
insert into Categoria values (5,'Electrónica', 'Gran cantidad de prodcutos electrónicos, además responde dudas en electrónica cuando se requeire reparar o comprar un electrónico, cambiar una pieza o comprar el producto adecuado. Comprende televisores, dispositivos de audio y video, blue-ray, gatges, etc.');
insert into Categoria values (6,'Linea Blanca','La línea blanca se refiere a los principales electrodomésticos vinculados a la cocina, limpieza del hogar y ventilación, como son refrigeradores, estufas, etc.');


-- #######################################################
-- Datos de la tabla Producto
-- #######################################################

insert into producto (nombreProducto, descripcionProducto, precio, existencia, idCategoria) values (
	'Jogger marca Boy London para Hombre',
	'Este modelo casual es cómodo y versátil a partes iguales. Dispone de una confección en algodón y poliéster que hacen de la prenda una suave y perfecta para aportar mayor libertad de movimiento.',
	479.00,11,1);

insert into producto (nombreProducto, descripcionProducto, precio, existencia, idCategoria) values (
'Camisa Manga Larga color Tinto marca Wallstreet',
'El calor no es un impedimento para conformar tu outfit con una camisa Wallstreet de manga larga, al contrario puedes crear un look con estilo, por ejemplo al subir las mangas y dejarlas a la altura de los codos.',
229.00, 56, 1
);

insert into producto (nombreProducto, descripcionProducto, precio, existencia, idCategoria) values (
'Pijama Champion para Hombre',
'Se trata de un patalón confeccionado en algodón y elastano, fibras textiles de gran confort. Su diseño en azul marino, con la palabra de la marca estampada, hacen que combinarlo sea mucho más fácil.',
769.00, 24, 1
);

insert into producto (nombreProducto, descripcionProducto, precio, existencia, idCategoria) values (
'Telcel Apple iPhone 12 64 GB Black',
'¡El iPhone 12 lo tiene todo! Eficiencia máxima gracias al chip A14 Bionic, el más rápido en un smartphone, realiza 11 billones de operaciones por segundo, además es supereficiente para ofrecer una gran duración de la batería.',
23949, 100, 2
);

insert into producto (nombreProducto, descripcionProducto, precio, existencia, idCategoria) values (
'Virgin Motorola Moto G8 Power Lite 64 GB Azul',
'Es un dispositivo inteligente, con una genial pantalla Max Vision HD+ de 6.5", que te brinda los colores más nítidos y cabe perfectamente en tu mano. Su sistema operativo es Android 9, con tecnología 4G y conectividad Wi-Fi y Bluetooth. ',
3999,34,2
);

insert into producto (nombreProducto, descripcionProducto, precio, existencia, idCategoria) values (
'AT&T/Unefon Samsung Galaxy A21S 64 GB Rojo',
'Sumérgete en su pantalla Infinity-O de 6.5 pulgadas. Su almacenamiento es de 64 GB, que fácilmente puedes expandir hasta 512 GB. ',
5999,15,2
);

insert into producto (nombreProducto, descripcionProducto, precio, existencia, idCategoria) values (
'Apple Watch Series 3 42 mm Negro',
'Saludable, activo y conectado. Haz todo lo que quieras sin tener que estar cuidando tu smartphone de salpicaduras de agua o polvo. Lleva el poder de comunicarte y de saber qué pasa a tu alrededor con el genial Apple Watch Series 3 de 42 mm. ',
6899,45,2
);

insert into producto (nombreProducto, descripcionProducto, precio, existencia, idCategoria) values (
'Disco Duro Externo Toshiba 1 TB',
'Su capacidad de almacenamiento de 1 TB te permite guardar una gran cantidad de fotos, videos, series y películas. Además, su interfaz USB 3.0 te brinda opciones flexibles de conectividad y transferencias veloces. ',
1099,21,3
);

insert into producto (nombreProducto, descripcionProducto, precio, existencia, idCategoria) values (
'Monitor HP 21.5" Full HD 22FW',
'Trabaja, haz tareas o disfruta del contenido de la web con el monitor HP de 21.5" Full HD, modelo  22FW.',
3999,2,3
);

insert into producto (nombreProducto, descripcionProducto, precio, existencia, idCategoria) values (
'Laptop HP14" Intel Core i3 12 GB RAM 1TB Negro',
'Cuenta con un procesador Intel Core i3, sistema operativo Windows 10 Home con una cámara web vga integrada en una batería en uso de hasta 9 horas.',
15599,7,3
);


insert into Producto (nombreProducto, descripcionProducto, precio, existencia, idCategoria) 
	values ('FIFA 21 - Edición e',
		'Incluye la UEFA Champions League, la UEFA Europa League',
		 1349.00,100,
		4);

insert into Producto (nombreProducto, descripcionProducto, precio, existencia, idCategoria) values ('Super Mario Nintendo Switch 3D All Stars',
'Incluye los titulos Super Mario Sunshine, Super Mario Galaxy y Super Mario 64.',
1399.00, 100, 4);
insert into Producto (nombreProducto, descripcionProducto, precio, existencia, idCategoria) values ('Luigis Mansion 3 Nintendo Switch',
'Incluye los titulos Super Mario Sunshine, Super Mario Galaxy y Super Mario 64.',
1399.00, 100, 4);
insert into Producto (nombreProducto, descripcionProducto, precio, existencia, idCategoria) values ('Nintendo Switch',
'Incluye los titulos Super Mario Sunshine, Super Mario Galaxy y Super Mario 64.',
1399.00, 100, 4);
insert into Producto (nombreProducto, descripcionProducto, precio, existencia, idCategoria) values ('FIFA 21 ',
'Incluye la UEFA Champions League, la UEFA Europa League, CONMEBOL Libertadores',
1349.00,100, 4);
‌
‌
insert into Producto (nombreProducto, descripcionProducto, precio, existencia, idCategoria) values ('Televisor LG',
'UHD 4K Resolución 3840x2160 LG 8806091995643',
9925.00,100, 5);
insert into Producto (nombreProducto, descripcionProducto, precio, existencia, idCategoria) values ('Control Remoto',
'Vinabty Akb73756542 Para Televisor Smart Lg Vinabty AKB73756542',
628.00, 100, 5);
insert into Producto (nombreProducto, descripcionProducto, precio, existencia, idCategoria) values ('Soporte StarTech',
'Pared para Televisor o Monitor de 32 a 55 StarTech.com FPWARTB1M',
2099.00, 100, 5);
‌
insert into Producto (nombreProducto, descripcionProducto, precio, existencia, idCategoria) values ('Lavadora Daewoo',
'Sistema de Lavado por Aeroburbujas AIR Bubble 4D',
9999, 100, 6);
insert into Producto (nombreProducto, descripcionProducto, precio, existencia, idCategoria) values ('Estufa Acros AF-5001D',
'3 parrillas de alambrón con diseño tipo dedos',
4999.00, 100, 6);
‌

-- #######################################################
-- Datos de la tabla Usuario
-- #######################################################

insert into Usuario (nombre,paterno,materno,email,nombreUsuario,claveUsuario,tipoUsuario) values
    ('Aaron', 'Garcia', 'Gonzalez', 'aaron@gmail.com', 'AaronGG11', 'AaronGG11', 'admin');

insert into Usuario (nombre,paterno,materno,email,nombreUsuario,claveUsuario,tipoUsuario) values
    ('Antonio', 'Garcia', 'Gonzalez', 'aaron.eng99@gmail.com', 'AntonioGG11', 'AntonioGG11', 'empleado');

insert into Usuario (nombre,paterno,materno,email,nombreUsuario,claveUsuario,tipoUsuario) values
    ('Alejandro', 'Perez', 'Perez', 'cetisofimatica4.10@gmail.com', 'AlexPP10', 'AlexPP10', 'empleado');

insert into Usuario (nombre,paterno,materno,email,nombreUsuario,claveUsuario,tipoUsuario) values
    ('Arnold', 'Gomez', 'Preciado', 'aarongarcia.ipn.escom@gmail.com', 'ArnoldGP', 'ArnoldGP', 'empleado');