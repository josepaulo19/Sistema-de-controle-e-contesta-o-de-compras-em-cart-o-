
CREATE DATABASE secure_card;

\c secure_card;

CREATE TABLE usuarios (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(120) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    senha_hash VARCHAR(255) NOT NULL,
    telefone VARCHAR(20),
    cpf VARCHAR(14) UNIQUE,
    status VARCHAR(20) DEFAULT 'ATIVO',
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    atualizado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE cartoes (
    id BIGSERIAL PRIMARY KEY,
    usuario_id BIGINT NOT NULL,
    apelido VARCHAR(80) NOT NULL,
    bandeira VARCHAR(50) NOT NULL,
    banco VARCHAR(80),
    ultimos_digitos VARCHAR(4) NOT NULL,
    limite NUMERIC(12,2) NOT NULL,
    dia_fechamento INT NOT NULL,
    dia_vencimento INT NOT NULL,
    status VARCHAR(20) DEFAULT 'ATIVO',
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_cartao_usuario
    FOREIGN KEY(usuario_id)
    REFERENCES usuarios(id)
    ON DELETE CASCADE
);

CREATE TABLE compras (
    id BIGSERIAL PRIMARY KEY,
    cartao_id BIGINT NOT NULL,
    estabelecimento VARCHAR(140) NOT NULL,
    descricao TEXT,
    categoria VARCHAR(80),
    valor NUMERIC(12,2) NOT NULL,
    data_compra DATE NOT NULL,
    parcelas INT DEFAULT 1,
    compra_online BOOLEAN DEFAULT FALSE,
    status VARCHAR(30) DEFAULT 'PROCESSADA',
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_compra_cartao
    FOREIGN KEY(cartao_id)
    REFERENCES cartoes(id)
    ON DELETE CASCADE
);

CREATE TABLE contestacoes (
    id BIGSERIAL PRIMARY KEY,
    compra_id BIGINT NOT NULL,
    usuario_id BIGINT NOT NULL,
    motivo VARCHAR(100) NOT NULL,
    descricao TEXT NOT NULL,
    status VARCHAR(30) DEFAULT 'EM_ANALISE',
    protocolo VARCHAR(40) UNIQUE NOT NULL,
    valor_contestado NUMERIC(12,2) NOT NULL,
    data_contestacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    resolucao TEXT,

    CONSTRAINT fk_contestacao_compra
    FOREIGN KEY(compra_id)
    REFERENCES compras(id)
    ON DELETE CASCADE,

    CONSTRAINT fk_contestacao_usuario
    FOREIGN KEY(usuario_id)
    REFERENCES usuarios(id)
    ON DELETE CASCADE
);

CREATE TABLE historico_contestacao (
    id BIGSERIAL PRIMARY KEY,
    contestacao_id BIGINT NOT NULL,
    status_anterior VARCHAR(30),
    novo_status VARCHAR(30) NOT NULL,
    observacao TEXT,
    alterado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_historico_contestacao
    FOREIGN KEY(contestacao_id)
    REFERENCES contestacoes(id)
    ON DELETE CASCADE
);

CREATE TABLE anexos_contestacao (
    id BIGSERIAL PRIMARY KEY,
    contestacao_id BIGINT NOT NULL,
    nome_arquivo VARCHAR(255) NOT NULL,
    url_arquivo TEXT NOT NULL,
    enviado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_anexo_contestacao
    FOREIGN KEY(contestacao_id)
    REFERENCES contestacoes(id)
    ON DELETE CASCADE
);

CREATE TABLE notificacoes (
    id BIGSERIAL PRIMARY KEY,
    usuario_id BIGINT NOT NULL,
    titulo VARCHAR(120) NOT NULL,
    mensagem TEXT NOT NULL,
    lida BOOLEAN DEFAULT FALSE,
    criada_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_notificacao_usuario
    FOREIGN KEY(usuario_id)
    REFERENCES usuarios(id)
    ON DELETE CASCADE
);

CREATE TABLE logs_sistema (
    id BIGSERIAL PRIMARY KEY,
    usuario_id BIGINT,
    acao VARCHAR(100) NOT NULL,
    endpoint VARCHAR(255),
    ip_usuario VARCHAR(50),
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_log_usuario
    FOREIGN KEY(usuario_id)
    REFERENCES usuarios(id)
    ON DELETE SET NULL
);

CREATE INDEX idx_usuario_email ON usuarios(email);
CREATE INDEX idx_compra_cartao ON compras(cartao_id);
CREATE INDEX idx_contestacao_status ON contestacoes(status);
