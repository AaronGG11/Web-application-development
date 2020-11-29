use WAD;

-- Crear tabla Categoria

create table Categoria(
    idCategoria int not null auto_increment primary key,
    nombreCategoria varchar(50) not null,
    descripcionCategoria varchar(200) not null
);

-- Crear procedimientos para tabla categoria

delimiter //    

create procedure spInsertarCategoria(
    nombre varchar(50),
    descripcion varchar(200)) 
begin 
    insert into Categoria(nombreCategoria,descripcionCategoria) 
    values(nombre,descripcion);
end //


create procedure spActualizarCategoria(
    id int,
    nombre varchar(50),
    descripcion varchar(200)) 
begin   
    update Categoria set 
    nombreCategoria = nombre, descripcionCategoria = descripcion
    where idCategoria = id;
end //


create procedure spBorrarCategoria(
    id int)
begin
    delete from Categoria where idCategoria = id;
end //


create procedure spMostrarCategorias()
    begin
        select * from Categoria;
end //


create procedure spVerCategoria(
    id int)
    begin 
        select * from Categoria where
        idCategoria = id;
    end //

call spVerCategoria(1) //

delimiter ;


-- Inserción de datos inciales

call spInsertarCategoria("Ropa de hombre", "Pantalon, camisa, sacos, ropa ejecutiva, ropa interior, ropa deportiva");
call spInsertarCategoria("Ropa de mujer", "Pantalon, blusa, faldas, ropa ejecutiva, ropa interior, ropa deportiva");
call spInsertarCategoria("Celular", "Libres, Por operadora, accesorios, telefonia fija");
call spInsertarCategoria("Computación", "Equipos de cómputo de última generación") //


-- Comandos para verificar estado de funcion y procedimientos
show procedure status;
show function status;


-- Crear tablas usuario, entidad federativa, producto y municipio

use WAD;

create table Usuario(
    idUsuario int not null auto_increment primary key,
    nombre varchar(50) not null,
    paterno varchar(50) not null,
    materno varchar(50) not null,
    email varchar(80) not null,
    nombreUsuario varchar(20) not null,
    claveUsuario varchar(20) not null,
    tipoUsuario varchar(20) not null
);

create table EntidadFederativa(
    idEntidadFederativa int not null primary key,
    nombre varchar(50) not null,
    abreviatura char(5) not null
);

create table Producto(
    idProducto int not null auto_increment primary key,
    nombreProducto varchar(50) not null,
    descripcionProdcuto varchar(256) not null,
    precioProducto int not null,
    existenciaProducto int not null
);

create table Municipio(
    idMunicipio int not null,
    idEntidadFederativa int,
    nombre varchar(128),
    foreign key(idEntidadFederativa) 
    references EntidadFederativa(idEntidadFederativa)
    on delete cascade on update cascade
);


-- Crear procedimimientos para relacion Usuario

delimiter // 


create procedure spInsertarUsuario(
    nombre varchar(50),
    paterno varchar(50),
    materno varchar(50),
    email varchar(80),
    nombreUsuario varchar(20),
    claveUsuario varchar(20),
    tipoUsuario varchar(20)
    )begin
        insert into Usuario(nombre, paterno, materno, email, nombreUsuario, claveUsuario, tipoUsuario)
        values(nombre, paterno, materno, email, nombreUsuario, claveUsuario, tipoUsuario);
    end //


create procedure spActualizarUsuario(
    nombre varchar(50),
    paterno varchar(50),
    materno varchar(50),
    email varchar(80),
    nombreUsuario varchar(20),
    claveUsuario varchar(20),
    tipoUsuario varchar(20),
    idUsuario int
    )begin
        update Usuario set 
        nombre = nombre, paterno = paterno, materno = materno, email = email,
        nombreUsuario = nombreUsuario, claveUsuario = claveUsuario, tipoUsuario = tipoUsuario
        where idUsuario = idUsuario;
    end //


create procedure spBorrarUsuario(
    idUsuario int
    )begin
        delete from Usuario where idUsuario = idUsuario;
    end //


create procedure spVerUsuario(
    idUsuario int
    )begin
        select * from Usuario 
        where idUsuario = idUsuario;
    end //
    

create procedure spMostrarUsuarios()
    begin
        select * from Usuario;
    end //


create procedure spLogin(
    nombre varchar(20),
    clave varchar(20)
    )begin
        select * from Usuario
        where nombreUsuario like nombre and claveUsuario like clave; -- en la misma linea
    end //


drop procedure if exists spLogin;

-- Probando procedimientos 
delimiter ;

call spInsertarUsuario("Aaron", "Garcia", "Gonzalez", "aaron.eng99@gmail.com", "AaronGarcia", "rootroot", "administrador");
call spLogin("AaronGarcia", "rootroot");
call spLogin("AaronGarcia", "algoalgo");


-- Crear procedimientos para EntidadFederativa
delimiter //

create procedure spInsertarEntidadFederativa(
    idEntidadFederativa int,
    nombre varchar(50),
    abreviatura char(5)
    )begin
        insert into EntidadFederativa
        values (idEntidadFederativa, nombre, abreviatura);
    end //


create procedure spBorrarEntidadFederativa(
    idEntidadFederativa int
    )begin
        delete from EntidadFederativa where idEntidadFederativa = idEntidadFederativa;
    end //


create procedure spVerEntidadFederativa(
    idEntidadFederativa int
    )begin
        select * from EntidadFederativa where idEntidadFederativa = EntidadFederativa;
    end //


create procedure spVerEntidadesFederativas()
    begin
    select * from EntidadFederativa;
    end //
 

 -- Crear procedimientos para Municipio

 create procedure spInsertarMunicipio(
    idMunicipio int,
    idEntidadFederativa int,
    nombre varchar(128)
    )begin
        insert into Municipio 
        values (idMunicipio, idEntidadFederativa, nombre);
    end //


create procedure spBorrarMunicipio(
    idMunicipio int
    )begin
        delete from Municipio where idMunicipio = idMunicipio;
    end //


create procedure spVerMunicipio(
    idMunicipio int
    )begin
        select * from Municipio
        where idMunicipio = idMunicipio;
    end //


create procedure spVerMunicipios()
    begin
        select * from Municipio;
    end //


-- Crear vistas
-- Vista con todos los atributos de estado y el nuemro de municipios asoicados

create view vista_municipios_por_estado as 
    select e.idEntidadFederativa as Id_estado, e.nombre as Nombre_de_estado, count(*) as Total_de_municipios
    from EntidadFederativa e, Municipio m 
    where e.idEntidadFederativa=m.idEntidadFederativa
    group by e.idEntidadFederativa
    order by Total_de_municipios desc;

select * from vista_municipios_por_estado;
drop view vista_municipios_por_estado;
