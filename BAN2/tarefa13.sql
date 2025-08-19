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

create index idx_con_data on conserto using btre(data)

-- 4)      Recupere o nome e a função de todos os mecânicos, e o número e o nome dos setores para os mecânicos que tenham essa informação.

-- 5)      Recupere o nome de todos os mecânicos, e as datas dos consertos para os mecânicos que têm consertos feitos (deve aparecer apenas um registro de nome de mecânico para cada data de conserto).

-- 6)      Recupere a média da quilometragem de todos os veículos de cada cliente, ordenando da maior KM para o menor.

-- 7)      Recupere a soma da quilometragem dos veículos de cada cidade onde residem seus proprietários.

-- 8)      Recupere a quantidade de consertos feitos por cada mecânico durante o período de 12/06/2018 até 19/010/2018

-- 9)   Recupere a quantidade de consertos feitos agrupada pela marca do veículo.

-- 10)   Recupere o modelo, a marca e o ano dos veículos que têm quilometragem maior que a média de quilometragem de todos os veículos.

-- 11)   Recupere o nome dos mecânicos que têm mais de um conserto marcado para o mesmo dia.