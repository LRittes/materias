create table users (
	email varchar(50),
	username varchar(50),
	userpassword varchar(50),
	primary key (email)
	
);

create table emails (
	id varchar(150),
	author varchar(50),
	receiver varchar(50),
	usermessage TEXT,
	subject varchar(100),
	date varchar(50),
	constraint 
	primary key (id),
	foreign key (author) references users,
	foreign key (receiver) references users

);

select * from emails;

select * from emails where receiver = 'lew@';

select * from users;

select * from users where email='lew@' and userpassword='sdfsdfsdfsd';

insert into users (email, username,userpassword) values ('lew@','le','sdfsdfsdfsd');

insert into users (email, username,userpassword) values ('le@','lse','sdfsdfsdfsd');

insert into emails (id, author,receiver,usermessage,subject,date) values ('sdfsdfsdf','lew@','le@','sdfdsf','sdfsdfsdfsd','345345-435');

insert into emails (id, author,receiver,usermessage,subject,date) values ('sdfssdfsdf','lew@','le@','sdfdsf','sdfsdfsdfsd','345345-435');

insert into emails (id, author,receiver,usermessage,subject,date) values ('fgdhfgh','le@','lew@','hjkuyuk','erterter','789879-8797');

delete from emails where id = 'sdfsdfsdf';