DROP TABLE IF EXISTS doacao_model;
DROP TABLE IF EXISTS banco_leite_model;
DROP TABLE IF EXISTS usuario_model;
DROP TABLE IF EXISTS cidade_model;

-- Usuário
CREATE TABLE usuario_model (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    telefone VARCHAR(20),
    senha VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    cpf VARCHAR(14) NOT NULL,
    doadora BOOLEAN DEFAULT FALSE,
    receptora BOOLEAN DEFAULT FALSE,
    profissional BOOLEAN DEFAULT FALSE,
    admin BOOLEAN DEFAULT FALSE,
    latitude DOUBLE,
    longitude DOUBLE
);

-- Banco de Leite
CREATE TABLE banco_leite_model (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    endereco VARCHAR(200),
    telefone VARCHAR(20),
    latitude DOUBLE,
    longitude DOUBLE
);

-- Cidade
CREATE TABLE cidade_model (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    estado VARCHAR(2) NOT NULL
);

-- Doação
CREATE TABLE doacao_model (
    id SERIAL PRIMARY KEY,
    data_doacao DATE NOT NULL,
    quantidade_ml INTEGER,
    usuario_id INT NOT NULL,
    banco_de_leite_id INT NOT NULL,
    CONSTRAINT fk_usuario FOREIGN KEY (usuario_id) REFERENCES usuario_model(id) ON DELETE CASCADE,
    CONSTRAINT fk_banco FOREIGN KEY (banco_de_leite_id) REFERENCES banco_leite_model(id) ON DELETE CASCADE
);
