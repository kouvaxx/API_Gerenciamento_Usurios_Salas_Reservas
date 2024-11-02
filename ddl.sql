create database sala_facil;

use sala_facil;

create table sala (
	id_sala INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(128) NOT NULL,
    departamento VARCHAR(128) NOT NULL,
    descricao VARCHAR(128),
    status TINYINT(1) default 1 NOT NULL
);

create table usuario (
	id_usuario INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(256) NOT NULL,
    email VARCHAR(256) NOT NULL,
    phone VARCHAR(45) NOT NULL,
    cpf VARCHAR(45) NOT NULL
);

create table reserva (
	id_reserva INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    data_reserva datetime NOT NULL,
    data_pedido datetime NOT NULL,
    status TINYINT(1) default 1 NOT NULL,
    sala_id INT NOT NULL,
    usuario_id INT NOT NULL,
    CONSTRAINT fk_sala FOREIGN KEY(sala_id) REFERENCES sala(id_sala),
    CONSTRAINT fk_usuario FOREIGN KEY(usuario_id) REFERENCES usuario(id_usuario)
);
