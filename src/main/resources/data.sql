INSERT INTO pessoas (nome, email, telefone, criado_em) VALUES
('Maria Silva', 'maria@email.com', '11999990000', CURRENT_TIMESTAMP),
('João Souza', 'joao@email.com', '21988887777', CURRENT_TIMESTAMP);

INSERT INTO cartoes (apelido, bandeira, ultimos_digitos, limite, dia_fechamento, dia_vencimento, pessoa_id, criado_em) VALUES
('Nubank Roxinho', 'Mastercard', '1234', 5000.00, 5, 12, 1, CURRENT_TIMESTAMP),
('Banco Azul Visa', 'Visa', '9876', 3500.00, 10, 17, 1, CURRENT_TIMESTAMP),
('Cartão Mercado', 'Elo', '5555', 2000.00, 8, 15, 2, CURRENT_TIMESTAMP);

INSERT INTO despesas (descricao, categoria, valor, data_compra, parcelas, cartao_id, criado_em) VALUES
('Supermercado', 'Alimentação', 350.75, '2026-05-05', 1, 1, CURRENT_TIMESTAMP),
('Combustível', 'Transporte', 220.00, '2026-05-08', 1, 1, CURRENT_TIMESTAMP),
('Tênis esportivo', 'Vestuário', 499.90, '2026-05-12', 5, 2, CURRENT_TIMESTAMP),
('Streaming mensal', 'Lazer', 39.90, '2026-05-15', 1, 3, CURRENT_TIMESTAMP);
