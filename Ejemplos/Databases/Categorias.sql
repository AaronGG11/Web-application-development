create table Categoria(
    idCategoria int not null auto_increment primary key,
    nombreCategoria varchar(50) not null,
    descripcionCategoria varchar(200) not null
);

delimiter //    

create procedure spInsertarCategoria(
    nombre varchar(50),
    descripcion varchar(200)) 
begin 
    insert into Categoria(nombreCategoria,descripcionCategoria) 
    values(nombre,descripcion);

end //

call spInsertarCategoria("Computación", "Equipos de cómputo de última generación") //

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