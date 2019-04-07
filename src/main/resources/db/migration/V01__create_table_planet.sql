CREATE TABLE planet (
  id      BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  name    VARCHAR(255)  NOT NULL,
  climate VARCHAR (255) NOT NULL,
  terrain VARCHAR (255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into planet (name, climate, terrain) values ('Yavin IV', 'temperate, tropical', 'jungle, rainforests')