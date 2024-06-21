CREATE TYPE SEXOENUM AS ENUM ('Masculino', 'Feminino', 'Outro');
CREATE TYPE TIPOTELENUM AS ENUM ('RES', 'COM', 'CEL');

CREATE TABLE Usuario (
        id_usuario SERIAL PRIMARY KEY,
        nome VARCHAR(30) NOT NULL,
        sobrenome VARCHAR(30) NOT NULL,
        email VARCHAR(50) NOT NULL,
        senha VARCHAR(15) NOT NULL,
        data_nascimento DATE NOT NULL,
        sexo SEXOENUM NOT NULL,
        data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE EnderecoUsuario (
    id_endereco_usuario SERIAL PRIMARY KEY,
    cep CHAR(9) NOT NULL,
    rua VARCHAR(30) NOT NULL,
    bairro VARCHAR(30) NOT NULL,
    cidade VARCHAR(30) NOT NULL,
    estado CHAR(2) NOT NULL,
    pais VARCHAR(30) NOT NULL,
    numero_casa VARCHAR(1000) NOT NULL,
    id_usuario_FK INT UNIQUE,
    FOREIGN KEY (id_usuario_FK) REFERENCES Usuario(id_usuario)
);

CREATE TABLE Telefone (
    id_telefone SERIAL PRIMARY KEY,
    tipo TIPOTELENUM NOT NULL,
    contato VARCHAR(10) NOT NULL,
    id_usuario_fk INT,
    FOREIGN KEY (id_usuario_fk) REFERENCES Usuario(id_usuario)
);


