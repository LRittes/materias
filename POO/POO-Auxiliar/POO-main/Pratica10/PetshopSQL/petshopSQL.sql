create table tipoAnimal(
	id int,
	nome varchar(50),
	primary key(id)
);

insert into tipoAnimal values (1, 'Cachorro');
insert into tipoAnimal values (2, 'Gato');
insert into tipoAnimal values (3, 'Passaro');
insert into tipoAnimal values (4, 'Roedor');

create table pessoa(
	id int,
	nome varchar(50),
	cpf char(11),
	primary key (id)
);
create sequence id_pessoa;

create table animais(
	id int,
	nome varchar(50),
	idade integer,
	descricao varchar(50),
	id_tipoAnimal integer,
	id_pessoa integer,
	primary key(id),
	foreign key(id_tipoAnimal) references tipoAnimal,
	foreign key(id_pessoa) references pessoa
);
create sequence id_animais;

create table endereco(
	id int,
	rua varchar(50),
	numero integer,
	cep char(8),
	cidade varchar(50),
	estado varchar(50),
	pais varchar(50),
	id_pessoa integer,
	primary key(id),
	foreign key(id_pessoa) references pessoa
);
create sequence id_endereco;

--Verificando tabelas
select * from pessoa;
select * from endereco;
select * from animais;
select * from tipoAnimal;
--Inserindo Usuario
insert into pessoa values (nextval('id_pessoa'),'Lucas',11111111111);
--Inserindo Animal
insert into animais values (nextval('id_animais'), 'Bob', 10, 'Macho', 1, 1);
--Inserindo Endereco
insert into endereco values (nextval('id_endereco'), 'Joao Colin', 1038, 12312334, 'Joinville', 'Santa Catarina', 'Brasil', 1);

