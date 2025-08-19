CREATE TABLE Hotel (
    cod_hotel SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    endereco TEXT NOT NULL,
    telefone VARCHAR(20)
);

CREATE TABLE Cliente (
    cod_cliente SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    endereco TEXT NOT NULL,
    telefone VARCHAR(20)
);

CREATE TABLE Tipo_Quarto (
    tipo_quarto VARCHAR(20),
    cod_hotel INT,
    preco_noite NUMERIC(10, 2) NOT NULL,
    PRIMARY KEY (tipo_quarto, cod_hotel),
    FOREIGN KEY (cod_hotel) REFERENCES Hotel(cod_hotel)
);

CREATE TABLE Quarto (
    num_quarto INT,
    cod_hotel INT,
    andar INT,
    tipo_quarto VARCHAR(20),
    PRIMARY KEY (num_quarto, cod_hotel),
    FOREIGN KEY (cod_hotel) REFERENCES Hotel(cod_hotel),
    FOREIGN KEY (tipo_quarto, cod_hotel) REFERENCES Tipo_Quarto(tipo_quarto, cod_hotel)
);

CREATE TABLE Reserva (
    cod_reserva SERIAL PRIMARY KEY,
    data_reserva DATE NOT NULL,
    data_checkin_prevista DATE NOT NULL,
    data_checkout_previsto DATE NOT NULL,
    tipo_quarto VARCHAR(20) NOT NULL,
    cama_extra BOOLEAN DEFAULT FALSE,
    cod_cliente INT NOT NULL,
    cod_hotel INT NOT NULL,
    status VARCHAR(20) CHECK (status IN ('confirmada', 'cancelada')),
    FOREIGN KEY (cod_cliente) REFERENCES Cliente(cod_cliente),
    FOREIGN KEY (cod_hotel) REFERENCES Hotel(cod_hotel),
    FOREIGN KEY (tipo_quarto, cod_hotel) REFERENCES Tipo_Quarto(tipo_quarto, cod_hotel)
);

CREATE TABLE Estadia (
    cod_estadia SERIAL PRIMARY KEY,
    data_checkin DATE NOT NULL,
    data_checkout DATE NOT NULL,
    cod_cliente INT NOT NULL,
    num_quarto INT NOT NULL,
    cod_hotel INT NOT NULL,
    FOREIGN KEY (cod_cliente) REFERENCES Cliente(cod_cliente),
    FOREIGN KEY (num_quarto, cod_hotel) REFERENCES Quarto(num_quarto, cod_hotel)
);

CREATE TABLE Servico_Extra (
    cod_servico SERIAL PRIMARY KEY,
    descricao TEXT NOT NULL,
    preco NUMERIC(10, 2) NOT NULL
);

CREATE TABLE Servico_Estadia (
    cod_estadia INT,
    cod_servico INT,
    data_hora TIMESTAMP NOT NULL,
    PRIMARY KEY (cod_estadia, cod_servico, data_hora),
    FOREIGN KEY (cod_estadia) REFERENCES Estadia(cod_estadia),
    FOREIGN KEY (cod_servico) REFERENCES Servico_Extra(cod_servico)
);

CREATE TABLE Empregado (
    cod_empregado SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL
);

CREATE TABLE Limpeza (
    cod_empregado INT,
    cod_hotel INT,
    num_quarto INT,
    data DATE NOT NULL,
    PRIMARY KEY (cod_empregado, cod_hotel, num_quarto, data),
    FOREIGN KEY (cod_empregado) REFERENCES Empregado(cod_empregado),
    FOREIGN KEY (num_quarto, cod_hotel) REFERENCES Quarto(num_quarto, cod_hotel)
);

