-- 1)      Função para inserção de um mecânico.

drop function insere_mec
create or replace function insere_mec(
										pcodm int,
										pcpf char(11),
										pnome varchar(50),
										pidade int,
										pendereco varchar(500),
										pcidade varchar(500),
										pfuncao varchar(50),
										pcods int)
							returns int as 
	$$
	declare vlinhas int;
	begin
		insert into mecanico (codm, cpf, nome, idade, endereco, cidade, funcao, cods)
		values (pcodm, pcpf, pnome, pidade, pendereco, pcidade, pfuncao, pcods);
		get diagnostics vlinhas = ROW_COUNT;
		return vlinhas;
	end;
	$$
	language plpgsql;

select insere_mec(9,'12332112312','jão', 23, 'Rua dos bobos', 'joinville','mamador',2)
select * from mecanico

-- 2)      Função para exclusão de um mecânico.

create or replace function delete_mec (pcodm int) returns void as
	$$
		delete from mecanico where codm = pcodm
	$$
	language sql;

select delete_mec(9)


select mani_cli('I',9,'12332112312','jão', 23, 'Rua dos bobos', 'joinville')

-- 3)      Função única para inserção, atualizar e exclusão de um cliente.

drop function mani_cli
create or replace function mani_cli (
										op char (1),
										pcodc int,
										pcpf char(11),
										pnome varchar(50), 
										pidade int,
										pendereco varchar(500),
										pcidade varchar(500))
	returns int as

	$$
	declare linhas int;
	begin
		if (upper(op) = 'I') then 
			insert into cliente values (pcodc, pcpf, pnome, pidade, pendereco, pcidade);
		elsif (upper(op) = 'D') then 
			delete from cliente where codc = pcodc;
		elsif (upper(op) = 'U') then 
			update cliente set  codc = pcodc, cpf = pcpf, nome = pnome, 
										idade = pidade, endereco = pendereco, cidade = pcidade 
			where codc = pcodc;
		end if;
		get diagnostics linhas = ROW_COUNT;
		return linhas;
	end;
	$$
	language plpgsql

select * from cliente
select mani_cli('U',9,'12332112312','joão Pedro', 23, 'Rua dos mamadores', 'joinville')

-- 4)      Função que limite o cadastro de no máximo 10 setores na oficina mecânica.

DROP FUNCTION limi_setor(pcods int, pnome varchar)
create or replace function limi_setor (pcods int, pnome varchar)
	returns int as
	$$
		declare qtdslinhas int default 0;
		declare linhas int;
		begin
			select count(*) from setor into qtdslinhas;
			if(qtdslinhas < 10) then
				insert into setor values (pcods, pnome);
				raise notice 'Não atingiu o limite';
			else
				raise notice 'Atingiu o limite';
			end if;
			get diagnostics linhas = ROW_COUNT;
			return linhas;
		end;
	$$
	language plpgsql

select * from setor
select limi_setor(5,'Recepção')
select limi_setor(6,'Alinhamento e balanceamento')
select limi_setor(7,'Regulagem de motores')
select limi_setor(8,'Reparo de freios')
select limi_setor(9,'Troca de filtros')
select limi_setor(10,'Revisão e manutenção preventiva')
select limi_setor(11,'Reparo de sistemas de direção')


-- 5) Função que limita o cadastro de um conserto 
-- apenas se o mecânico não tiver mais de 3 consertos agendados para o mesmo dia.

create or replace function cad_mec(
										pcodm int, 
										pcodv int,
										pdata date,
										phora time without time zone)
	returns int as
	$$	
		declare linhas int;
		declare qtdCons int default 0;
		begin
			select count(codm) from conserto
				where codm = pcodm and data = pdata into qtdCons;
			if(qtdCons < 3) then
				insert into conserto values (pcodm, pcodv, pdata,phora);
				get diagnostics linhas = ROW_COUNT;
			else
				raise notice 'Atingiu o limite!';
			end if;
		return linhas;
		end;
	$$
	language plpgsql

select cad_mec()


-- 6)      Função para calcular a média geral de idade dos Mecânicos e Clientes.

create or replace function avg_idade()
	returns float as
	$$
		declare sum_idade int default 0;
		declare	pidade float;
		declare	qtd_func float;
		declare	qtd_cli float;
		begin
			for pidade in select idade from mecanico loop
				sum_idade := sum_idade + pidade;
			end loop;
			for pidade in select idade from cliente loop
				sum_idade := sum_idade + pidade;
			end loop;
			select count(codm) from mecanico into qtd_func;
			select count(codc) from cliente into qtd_cli;
			return (sum_idade)/(qtd_func + qtd_cli);
		end;
	$$
	language plpgsql
    
select avg_idade()


-- 7)      Função única que permita fazer a exclusão de um Setor, Mecânico, Cliente ou Veículo.

create or REPLACE FUNCTION del_smcv( tab varchar(1),
                                   	 fcodc int,
                                     fcodm int,
                                     fcods int,
                                     fcodv int
                                   )
	returns void as
    $$
    	BEGIN
          if ( UPPER(tab) = 'S') then
              DELETE from setor WHERE cods = fcods;
          ELSIF ( UPPER(tab) = 'C') then
              DELETE from cliente WHERE codc = fcodc;
          ELSIF ( UPPER(tab) = 'M') then
              DELETE from mecanico WHERE codm = fcodm;
          ELSIF ( UPPER(tab) = 'V') then
              DELETE from cliente WHERE codv = fcodv;
          END IF;
        end;
    $$
    LANGUAGE plpgsql

SELECT del_smcv('S',0,0,5,0)

-- 8)      Considerando que na tabela Cliente apenas codc é a chave primária,
-- faça uma função que remova clientes com CPF repetido, deixando apenas um cadastro para cada CPF.
-- Escolha o critério que preferir para definir qual cadastro será mantido: aquele com a menor idade, que possuir mais consertos agendados, etc.
-- Para testar a função, não se esqueça de inserir na tabela alguns clientes com este problema.

SELECT c1.codc, c1.cpf, c1.idade from cliente c1
	where c1.cpf in ( SELECT cpf from cliente
						GROUP by cpf
    					HAVING COUNT(cpf) > 1)
                        
CREATE OR REPLACE FUNCTION remover_cpfs_duplicados_maior_idade()
	RETURNS void AS
	$$
      BEGIN
        DELETE FROM cliente
        WHERE codc NOT IN (
            SELECT sub.codc
            FROM (
                SELECT DISTINCT ON (cpf) codc
                FROM cliente
                WHERE cpf IN (
                    SELECT cpf
                    FROM cliente
                    GROUP BY cpf
                    HAVING COUNT(*) > 1
                )
                ORDER BY cpf, idade DESC  -- Maior idade primeiro
            ) AS sub
        )
        AND cpf IN (
            SELECT cpf
            FROM cliente
            GROUP BY cpf
            HAVING COUNT(*) > 1
        );
      END;
	$$
    LANGUAGE plpgsql;

SELECT remover_cpfs_duplicados_maior_idade();

-- 9)   Função para calcular se o CPF é válido*.

CREATE OR REPLACE FUNCTION cpf_valido(cpf_texto VARCHAR(11))
  RETURNS BOOLEAN AS 
  $$
    DECLARE
        cpf VARCHAR(11);
        i int;
        soma1 int := 0;
        soma2 int := 0;
        digito1 int;
        digito2 int;
    BEGIN

        IF length(cpf) != 11 THEN
            RETURN FALSE;
        END IF;

        FOR i IN 1..9 LOOP
            soma1 := soma1 + CAST(substr(cpf, i, 1) AS INTEGER) * (10 - i);
        END LOOP;

        digito1 := soma1 % 11;
        IF digito1 < 2 THEN
            digito1 := 0;
        ELSE
            digito1 := 11 - digito1;
        END IF;

        FOR i IN 1..9 LOOP
            soma2 := soma2 + CAST(substr(cpf, i, 1) AS INTEGER) * (11 - i);
        END LOOP;
        soma2 := soma2 + digito1 * 2;

        digito2 := soma2 % 11;
        IF digito2 < 2 THEN
            digito2 := 0;
        ELSE
            digito2 := 11 - digito2;
        END IF;

        RETURN digito1 = CAST(substr(cpf, 10, 1) AS INTEGER)
            AND digito2 = CAST(substr(cpf, 11, 1) AS INTEGER);
    END;
  $$ 
  LANGUAGE plpgsql;


-- 10)   Função que calcula a quantidade de horas extras de um mecânico em um mês de trabalho. 
-- O número de horas extras é calculado a partir das horas que excedam as 160 horas de trabalho mensais. 
-- O número de horas mensais trabalhadas é calculada a partir dos consertos realizados. Cada conserto tem a duração de 1 hora.

CREATE OR REPLACE FUNCTION calcular_horas_extras(
                                                  p_cod_mecanico INTEGER,
                                                  p_mes INTEGER,
                                                  p_ano INTEGER
                                              )	
	RETURNS INTEGER AS 
	$$
      DECLARE
          total_horas INTEGER;
          horas_extras INTEGER;
      BEGIN
          -- Conta quantos consertos foram feitos pelo mecânico no mês e ano especificado
          SELECT COUNT(*)
          INTO total_horas
          FROM conserto
          WHERE cod_mecanico = p_cod_mecanico
            AND EXTRACT(MONTH FROM data) = p_mes
            AND EXTRACT(YEAR FROM data) = p_ano;

          -- Calcula as horas extras
          horas_extras := GREATEST(total_horas - 160, 0);

          RETURN horas_extras;
      END;
	$$ LANGUAGE plpgsql;
