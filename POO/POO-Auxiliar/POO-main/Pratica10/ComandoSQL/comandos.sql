create table pessoa(
	id int,
	nome varchar(50),
	cpf char(11),
	telefone int,
	primary key (id)
);
create table endereco(
	id int,
	rua varchar(50),
	numero integer,
	cidade varchar(50),
	id_pessoa integer,
	primary key(id),
	foreign key(id_pessoa) references pessoa
);
--criando sequencia
create sequence id_pessoa;
create sequence id_endereco;
--inserindo dados
insert into pessoa values (nextval('id_pessoa'),'Lucas',1111,12345);
insert into endereco values (nextval('id_endereco'),'Ministro Calogeras',11,'Joinville', 1);
--buscando dados
select * from pessoa;
select * from endereco;
--atualizando dados
update pessoa set telefone = '67890' where nome = 'Lucas';
--removendo dados
delete from pessoa where id = 1;
--remover tabela
drop table pessoa;
--alterando tabela:
--adicionando coluna
alter table pessoa add column rg integer;
--remover uma coluna
alter table pessoa drop column rg integer;
