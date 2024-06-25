CREATE TABLE tb_cidade (
    id_cidade bigint not null primary key,
    nome varchar(50) not null,
    qtd_habitantes bigint not null
);

INSERT INTO tb_cidade (id_cidade, nome, qtd_habitantes) 
VALUES
(1, 'Brasília', 2817068),
(2, 'São Paulo', 11451245),
(3, 'Rio de Janeiro', 6221423),
(4, 'Goiânia', 2890418),
(5, 'Palmas', 302692),
(6, 'Fortaleza', 2428678),
(7, 'Natal', 751300),
(8, 'São Luis', 1037775),
(9, 'Porto Alegre', 1332570),
(10, 'Florianópolis', 537213),
(11, 'Belo Horizonte', 6300409);