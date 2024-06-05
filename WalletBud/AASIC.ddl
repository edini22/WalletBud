CREATE TABLE `User` (Id_user int(10) NOT NULL AUTO_INCREMENT, Name varchar(255), Password varchar(255), Email varchar(255), Saldo float NOT NULL, Idioma varchar(255), PRIMARY KEY (Id_user));
CREATE TABLE Categoria (Id_categoria int(10) NOT NULL AUTO_INCREMENT, Name varchar(255), Tipo varchar(255), PRIMARY KEY (Id_categoria));
CREATE TABLE Comentario (Id_comentario int(10) NOT NULL AUTO_INCREMENT, Descrição varchar(255), PRIMARY KEY (Id_comentario));
CREATE TABLE Transacao (Id_transacao int(10) NOT NULL AUTO_INCREMENT, Name varchar(255), Value float NOT NULL, `Date` date, Descrição varchar(255), Local varchar(255), Tipo varchar(255), Repetição int(10), Pago tinyint(1), Data_pagamento date, Id_fixa int(10), Discriminator varchar(255) NOT NULL, PRIMARY KEY (Id_transacao));
CREATE TABLE Objetivo (Id_objetivo int(10) NOT NULL AUTO_INCREMENT, Value float NOT NULL, Name varchar(255), Descrição varchar(255), PRIMARY KEY (Id_objetivo));
CREATE TABLE Notificacao (Id_notificacao int(10) NOT NULL AUTO_INCREMENT, `Date` date, Descrição varchar(255), PRIMARY KEY (Id_notificacao));
CREATE TABLE UserTransacao (ID int(10) NOT NULL AUTO_INCREMENT, UserId_user int(10) NOT NULL, TransacaoId_transacao int(10) NOT NULL, PRIMARY KEY (ID));
ALTER TABLE UserTransacao ADD CONSTRAINT FKUserTransa546855 FOREIGN KEY (UserId_user) REFERENCES `User` (Id_user);
ALTER TABLE UserTransacao ADD CONSTRAINT FKUserTransa315225 FOREIGN KEY (TransacaoId_transacao) REFERENCES Transacao (Id_transacao);
