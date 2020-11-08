create database 3CM9M

create table Evento(
    idEvento int not null AUTO_INCREMENT,
    nombreEvento varchar(200),
    sede varchar(100),
    fechaInicio date,
    fechaFin date,
    PRIMARY KEY (idEvento)
);