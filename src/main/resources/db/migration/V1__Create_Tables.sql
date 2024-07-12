CREATE DOMAIN VALOR_POSITIVO AS FLOAT CHECK (VALUE >= 0);
CREATE DOMAIN NUMERO_POSITIVO AS INT CHECK (VALUE >= 0);
CREATE DOMAIN SALARIO_POSITIVO AS DECIMAL(10, 2) CHECK (VALUE >= 0);

CREATE FUNCTION non_negative_numeric(value varchar) RETURNS boolean AS $$
BEGIN
RETURN value ~ '^\d+$';
END;
$$ LANGUAGE plpgsql;

CREATE DOMAIN NUMERO_CASA AS varchar(10)
    CHECK (non_negative_numeric(VALUE)) NOT NULL;

CREATE TABLE Usuario (
    ID_Usuario SERIAL PRIMARY KEY,
    Nome VARCHAR(30) NOT NULL,
    Sobrenome VARCHAR(30) NOT NULL,
    CPF VARCHAR(14) UNIQUE NOT NULL,
    Email VARCHAR(50) NOT NULL,
    Senha VARCHAR(100) NOT NULL,
    Data_Nascimento DATE NOT NULL,
    Sexo VARCHAR(10) NOT NULL,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    Tipo CHAR(1) NOT NULL
);

CREATE TABLE Admin (
    ID_Admin SERIAL PRIMARY KEY,
    ID_Usuario_FK INT UNIQUE,
    Permissao VARCHAR(20),
    FOREIGN KEY (ID_Usuario_FK) REFERENCES Usuario(ID_Usuario)
);

CREATE TABLE Prontuario (
    ID_Prontuario SERIAL PRIMARY KEY,
    Idade NUMERO_POSITIVO NOT NULL,
    tipo_sanguineo VARCHAR(3) NOT NULL,
    Altura VALOR_POSITIVO NOT NULL,
    Peso VALOR_POSITIVO NOT NULL,
    Temperatura VALOR_POSITIVO NOT NULL,
    presao VARCHAR(10) NOT NULL,
    Descricao VARCHAR(255),
    Data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Paciente (
    ID_Paciente SERIAL PRIMARY KEY,
    ID_Usuario_FK INT UNIQUE NOT NULL,
    ID_numero_Prontuario_FK INT NOT NULL UNIQUE,
    FOREIGN KEY (ID_Usuario_FK) REFERENCES Usuario(ID_Usuario),
    FOREIGN KEY (ID_numero_Prontuario_FK) REFERENCES Prontuario(ID_Prontuario)
);

CREATE TABLE Medico (
    ID_Medico SERIAL PRIMARY KEY,
    ID_Usuario_FK INT UNIQUE NOT NULL,
    Salario SALARIO_POSITIVO NOT NULL,
    Especialidade VARCHAR(50) NOT NULL,
    CRM VARCHAR(30) NOT NULL,
    Data_Contratacao TIMESTAMP NOT NULL,
    FOREIGN KEY (ID_Usuario_FK) REFERENCES Usuario(ID_Usuario)
);

CREATE TABLE Endereco_Usuario (
    ID_Endereco_Usuario SERIAL PRIMARY KEY,
    ID_Usuario_FK INT UNIQUE NOT NULL,
    CEP VARCHAR(9) NOT NULL,
    Rua VARCHAR(30) NOT NULL,
    Bairro VARCHAR(30) NOT NULL,
    Cidade VARCHAR(30) NOT NULL,
    Estado VARCHAR(2) NOT NULL,
    Pais VARCHAR(13) NOT NULL,
    Numero_casa NUMERO_CASA,
    FOREIGN KEY (ID_Usuario_FK) REFERENCES Usuario(ID_Usuario)
);

CREATE TABLE Telefone_Usuario (
    ID_Telefone SERIAL PRIMARY KEY,
    ID_Usuario_FK INT NOT NULL,
    Contato VARCHAR(14) NOT NULL,
    Tipo VARCHAR(20) NOT NULL,
    FOREIGN KEY (ID_Usuario_FK) REFERENCES Usuario(ID_Usuario)
);

CREATE TABLE Exame (
    ID_Exame SERIAL PRIMARY KEY,
    Tipo_Exame VARCHAR(255) NOT NULL,
    Resultado VARCHAR(500) NOT NULL,
    Data_Exame DATE NOT NULL,
    ID_Paciente_FK INT NOT NULL,
    FOREIGN KEY (ID_Paciente_FK) REFERENCES Paciente(ID_Paciente)
);

CREATE TABLE Consulta (
    ID_Consulta SERIAL PRIMARY KEY,
    Medico_Id_FK INT NOT NULL,
    Prontuario_Id_FK INT NOT NULL,
    Data TIMESTAMP NOT NULL,
    Valor DECIMAL(10,2) NOT NULL,
    Hora TIME NOT NULL,
    Status VARCHAR(20) NOT NULL,
    Observacoes TEXT,
    FOREIGN KEY (Medico_Id_FK) REFERENCES Medico(ID_Medico),
    FOREIGN KEY (Prontuario_Id_FK) REFERENCES Prontuario(ID_Prontuario)
);

CREATE TABLE Prescricao (
    ID_Prescricao SERIAL PRIMARY KEY,
    ID_Consulta_FK INT NOT NULL,
    Instrucoes TEXT NOT NULL,
    FOREIGN KEY (ID_Consulta_FK) REFERENCES Consulta(ID_Consulta)
);

CREATE TABLE Fornecedor (
    ID_Fornecedor SERIAL PRIMARY KEY,
    Nome VARCHAR(50) NOT NULL,
    CNPJ VARCHAR(14) NOT NULL
);

CREATE TABLE Estoque (
    ID_Estoque SERIAL PRIMARY KEY,
    Nome VARCHAR(255) NOT NULL,
    Data_Atualizacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    Quantidade INT NOT NULL
);

CREATE TABLE Endereco_Fornecedor (
    ID_Endereco_Fornecedor SERIAL PRIMARY KEY,
    ID_Fornecedor_FK INT UNIQUE NOT NULL,
    CEP VARCHAR(10) NOT NULL,
    Rua VARCHAR(255) NOT NULL,
    Bairro VARCHAR(255) NOT NULL,
    Cidade VARCHAR(255) NOT NULL,
    Pais VARCHAR(50) NOT NULL,
    Estado VARCHAR(50) NOT NULL,
    Numero NUMERO_CASA,
    FOREIGN KEY (ID_Fornecedor_FK) REFERENCES Fornecedor(ID_Fornecedor)
);

CREATE TABLE Telefone_Fornecedor (
    ID_Telefone_Fornecedor SERIAL PRIMARY KEY,
    ID_Fornecedor_FK INT NOT NULL,
    Telefone VARCHAR(20) NOT NULL,
    FOREIGN KEY (ID_Fornecedor_FK) REFERENCES Fornecedor(ID_Fornecedor)
);

CREATE TABLE Itens_Hospitalares (
    ID_Itens_Hospitalares SERIAL PRIMARY KEY,
    Nome VARCHAR(255) NOT NULL,
    Preco VALOR_POSITIVO NOT NULL,
    Descricao VARCHAR(255) NOT NULL,
    Data_Validade DATE NOT NULL,
    Quantidade NUMERO_POSITIVO NOT NULL,
    ID_Fornecedor_FK INT NOT NULL,
    ID_Precricao_FK INT NOT NULL,
    ID_Estoque INT NOT NULL,
    FOREIGN KEY (ID_Fornecedor_FK) REFERENCES Fornecedor(ID_Fornecedor),
    FOREIGN KEY (ID_Estoque) REFERENCES Estoque(ID_Estoque)
);