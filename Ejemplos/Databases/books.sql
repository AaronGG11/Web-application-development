create database books;
use books;

create table Authors
(
    AuthorID int not null AUTO_INCREMENT,
    FirstName varchar(50),
    LastName varchar(50),
    PRIMARY KEY (AuthorID)
);

create table Titles
(
    ISBN varchar(50) not null,
    Title varchar(100),
    EditionNumber int,
    Copyright varchar(50),
    PRIMARY KEY (ISBN)
);


create table AuthorISBN
(
    AuthorID int not null,
    ISBN varchar(50) not null,
    primary key(AuthorID,ISBN),
    foreign key(AuthorID) references Authors(AuthorID) 
    on delete cascade on update cascade,
    foreign key(ISBN) references Titles(ISBN)
    on delete cascade on update cascade
);

insert into Authors (FirstName, LastName)
values
("Paul", "Deitel"),
("Harvey", "Deitel"),
("Abbey", "Deitel"),
("Michael", "Morgano"),
("Eric", "Kern");


insert into Titles
values
("0132152134", "Visual Basic 2010 How to Program", 5, "2011"),
("0132151421", "Visual C# 2010 How to Program", 4, "2011"),
("0132575663", "Java How to Program", 9, "2012"),
("0132662361", "C++ How to Program", 8, "2012"),
("0132404168", "C How to Program", 6, "2010"),
("013705842X", "iPhone for Programmers: An AppDriven Approach", 1, "2010"),
("0132121360", "Android for Programmers: An AppDriven Approach", 1, "2012");




insert into AuthorISBN
values
(1, "0132152134"),
(2, "0132152134"),
(1, "0132151421"),
(2, "0132151421"),
(1, "0132575663"),
(2, "0132575663"),
(1, "0132662361"),
(2, "0132662361"),
(1, "0132404168"),
(2, "0132404168");