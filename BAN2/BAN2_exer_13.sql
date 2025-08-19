-- 1)      Recupere o CPF e o nome dos mecânicos que trabalham nos setores maiores que 100 e menores que 200.

explain analyze select m.cpf, m.nome from mecanico m where cods between 100 and 200

create index idx_mecs_cods on mecanico using btree(cods);

-- 2)      Recupere o CPF e nome dos mecânicos que atenderam no dia 13/06/2018.

explain analyze select m.cpf, m.nome from mecanico m, conserto c where m.codm = c.codm and data = '13/06/2018'

create index idx_con_data on conserto using btree(data)
drop index idx_con_data

create index idx_con_daya on conserto using hash(data)

-- 3)      Recupere o nome do mecânico, o nome do cliente e a hora do conserto para os consertos realizados de 12/06/2018 à 25/09/2018.

explain analyze select m.nome, cli.nome, c.data from mecanico m join conserto c using (codm) 
	join veiculo v using (codv)
	join cliente cli using (codc) 
	where c.data between '12/06/2018' and '25/09/2018' 

create index idx_con_data on conserto using btree(data)

-- 4)      Recupere o nome e a função de todos os mecânicos, e o número e o nome dos setores para os mecânicos que tenham essa informação.

CREATE INDEX idx_mecanico_cods ON mecanico(cods) USING btree(cods);
CREATE INDEX idx_setor_cods ON setor(cods);

EXPLAIN ANALYZE
SELECT 
    m.nome,
    m.funcao,
    s.cods,
    s.nome
FROM mecanico m
INNER JOIN setor s ON m.cods = s.cods;

-- 5)      Recupere o nome de todos os mecânicos, e as datas dos consertos para os mecânicos que têm consertos feitos (deve aparecer apenas um registro de nome de mecânico para cada data de conserto).
CREATE INDEX idx_mecanico_codm ON mecanico USING btree(codm);
CREATE INDEX idx_conserto_codm ON conserto USING btree(codm);
CREATE INDEX idx_conserto_data ON conserto USING hash(data);

EXPLAIN ANALYZE
SELECT DISTINCT 
    m.nome,
    c.data
FROM mecanico m
INNER JOIN conserto c ON m.codm = c.codm
ORDER BY m.nome, c.data;

-- 6)      Recupere a média da quilometragem de todos os veículos de cada cliente, ordenando da maior KM para o menor.
-- Criação de índices para otimização
CREATE INDEX idx_veiculo_codc ON veiculo USING btree(codc);
CREATE INDEX idx_veiculo_quilometragem ON veiculo USING btree(quilometragem);
CREATE INDEX idx_cliente_codc ON cliente USING btree(codc);

EXPLAIN ANALYZE
SELECT 
    c.nome,
    AVG(v.quilometragem) as media_km
FROM cliente c
INNER JOIN veiculo v ON c.codc = v.codc
GROUP BY c.codc, c.nome
ORDER BY media_km DESC;

-- 7)      Recupere a soma da quilometragem dos veículos de cada cidade onde residem seus proprietários.
-- Criação de índices para otimização
CREATE INDEX idx_cliente_codc ON cliente USING btree(codc);
CREATE INDEX idx_cliente_cidade ON cliente USING hash(cidade);
CREATE INDEX idx_veiculo_codc ON veiculo USING btree(codc);
CREATE INDEX idx_veiculo_quilometragem ON veiculo USING btree(quilometragem);

EXPLAIN ANALYZE
SELECT 
    c.cidade,
    SUM(v.quilometragem) as total_km
FROM cliente c
INNER JOIN veiculo v ON c.codc = v.codc
GROUP BY c.cidade
ORDER BY total_km DESC;

-- 8)      Recupere a quantidade de consertos feitos por cada mecânico durante o período de 12/06/2018 até 19/010/2018
-- Criação de índices para otimização
CREATE INDEX idx_mecanico_codm ON mecanico USING btree(codm);
CREATE INDEX idx_conserto_codm ON conserto USING btree(codm);
CREATE INDEX idx_conserto_data ON conserto USING btree(data);

EXPLAIN ANALYZE
SELECT 
    m.nome,
    COUNT(*) as quantidade_consertos
FROM mecanico m
INNER JOIN conserto c ON m.codm = c.codm
WHERE c.data BETWEEN '2018-06-12' AND '2018-10-19'
GROUP BY m.codm, m.nome
ORDER BY quantidade_consertos DESC;

-- 9)   Recupere a quantidade de consertos feitos agrupada pela marca do veículo.
CREATE INDEX idx_conserto_codv ON conserto USING btree(codv);
CREATE INDEX idx_veiculo_codv ON veiculo USING btree(codv);
CREATE INDEX idx_veiculo_marca ON veiculo USING hash(marca);

EXPLAIN ANALYZE
SELECT 
    v.marca,
    COUNT(*) as quantidade_consertos
FROM veiculo v
INNER JOIN conserto c ON v.codv = c.codv
GROUP BY v.marca
ORDER BY quantidade_consertos DESC;

-- 10)   Recupere o modelo, a marca e o ano dos veículos que têm quilometragem maior que a média de quilometragem de todos os veículos.
CREATE INDEX idx_veiculo_quilometragem ON veiculo USING btree(quilometragem);
CREATE INDEX idx_veiculo_modelo ON veiculo USING hash(modelo);
CREATE INDEX idx_veiculo_marca ON veiculo USING hash(marca);

EXPLAIN ANALYZE
SELECT 
    modelo,
    marca,
    ano
FROM veiculo
WHERE quilometragem > (SELECT AVG(quilometragem) FROM veiculo)
ORDER BY quilometragem DESC;

-- 11)   Recupere o nome dos mecânicos que têm mais de um conserto marcado para o mesmo dia.
CREATE INDEX idx_conserto_codm_data ON conserto USING btree(codm, data);
CREATE INDEX idx_mecanico_codm ON mecanico USING btree(codm);

EXPLAIN ANALYZE
SELECT DISTINCT m.nome
FROM mecanico m
INNER JOIN conserto c ON m.codm = c.codm
WHERE c.codm IN (
    SELECT codm
    FROM conserto
    GROUP BY codm, data
    HAVING COUNT(*) > 1
);
