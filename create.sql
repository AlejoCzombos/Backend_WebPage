create table user (id integer not null auto_increment, country varchar(255), dni varchar(255), firstname varchar(255), lastname varchar(255), password varchar(255), role enum ('Admin','Parent','Student'), username varchar(255) not null, primary key (id)) engine=InnoDB;
alter table user add constraint UKsb8bbouer5wak8vyiiy4pf2bx unique (username);
