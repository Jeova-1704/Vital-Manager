CREATE TABLE Usuario (
    ID_Usuario SERIAL PRIMARY KEY,
    Nome VARCHAR(30) NOT NULL,
    Sobrenome VARCHAR(30) NOT NULL,
    CPF VARCHAR(14) UNIQUE NOT NULL,
    Email VARCHAR(50) NOT NULL,
    Senha VARCHAR(15) NOT NULL,
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
    Idade INT,
    tipo_sanguineo VARCHAR(3),
    Altura FLOAT,
    Peso FLOAT,
    Temperatura FLOAT,
    presao VARCHAR(10),
    Descricao VARCHAR(255),
    Data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
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
    Salario DECIMAL(10,2),
    Especialidade VARCHAR(50),
    CRM VARCHAR(30),
    Data_Contratacao TIMESTAMP,
    FOREIGN KEY (ID_Usuario_FK) REFERENCES Usuario(ID_Usuario)
);

CREATE TABLE Endereco_Usuario (
    ID_Endereco_Usuario SERIAL PRIMARY KEY,
    ID_Usuario_FK INT UNIQUE NOT NULL,
    CEP VARCHAR(9),
    Rua VARCHAR(30),
    Bairro VARCHAR(30),
    Cidade VARCHAR(30),
    Estado VARCHAR(2),
    Pais VARCHAR(13),
    Numero_casa VARCHAR(10),
    FOREIGN KEY (ID_Usuario_FK) REFERENCES Usuario(ID_Usuario)
);

CREATE TABLE Telefone_Usuario (
    ID_Telefone SERIAL PRIMARY KEY,
    ID_Usuario_FK INT,
    Contato VARCHAR(14),
    Tipo VARCHAR(20),
    FOREIGN KEY (ID_Usuario_FK) REFERENCES Usuario(ID_Usuario)
);

CREATE TABLE Exame (
    ID_Exame SERIAL PRIMARY KEY,
    Tipo_Exame VARCHAR(255),
    Resultado VARCHAR(500),
    Data_Exame DATE,
    ID_Paciente_FK INT,
    FOREIGN KEY (ID_Paciente_FK) REFERENCES Paciente(ID_Paciente)
);

CREATE TABLE Consulta (
    ID_Consulta SERIAL PRIMARY KEY,
    Medico_Id_FK INT,
    Prontuario_Id_FK INT,
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
    ID_Consulta_FK INT,
    Instrucoes VARCHAR(50),
    FOREIGN KEY (ID_Consulta_FK) REFERENCES Consulta(ID_Consulta)
);

CREATE TABLE Fornecedor (
    ID_Fornecedor SERIAL PRIMARY KEY,
    Nome VARCHAR(50),
    CNPJ VARCHAR(14)
);

CREATE TABLE Estoque (
    ID_Estoque SERIAL PRIMARY KEY,
    Nome VARCHAR(255),
    Data_Atualizacao DATE,
    Quantidade INT
);

CREATE TABLE Endereco_Fornecedor (
    ID_Endereco_Fornecedor SERIAL PRIMARY KEY,
    ID_Fornecedor_FK INT UNIQUE,
    CEP VARCHAR(10),
    Rua VARCHAR(255),
    Bairro VARCHAR(255),
    Cidade VARCHAR(255),
    Pais VARCHAR(50),
    Estado VARCHAR(50),
    Numero VARCHAR(10),
    FOREIGN KEY (ID_Fornecedor_FK) REFERENCES Fornecedor(ID_Fornecedor)
);

CREATE TABLE Telefone_Fornecedor (
    ID_Telefone_Fornecedor SERIAL PRIMARY KEY,
    ID_Fornecedor_FK INT,
    Telefone VARCHAR(20),
    FOREIGN KEY (ID_Fornecedor_FK) REFERENCES Fornecedor(ID_Fornecedor)
);

CREATE TABLE Itens_Hospitalares (
    ID_Itens_Hospitalares SERIAL PRIMARY KEY,
    Nome VARCHAR(255),
    Preco DECIMAL(10,2),
    Descricao VARCHAR(255),
    Data_Validade DATE,
    Quantidade INT,
    ID_Fornecedor_FK INT,
    ID_Precricao_FK INT,
    ID_Estoque INT,
    FOREIGN KEY (ID_Fornecedor_FK) REFERENCES Fornecedor(ID_Fornecedor),
    FOREIGN KEY (ID_Estoque) REFERENCES Estoque(ID_Estoque)
);