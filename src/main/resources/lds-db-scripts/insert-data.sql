INSERT INTO usuario_model (
    email, nome, senha, telefone, cpf, doadora, receptora, profissional, admin, latitude, longitude
) VALUES
('a@a', 'tiburssin tiburssius', 'a', NULL, '000.000.000-00', TRUE, FALSE, FALSE, FALSE, NULL, NULL),
('s@s', 'aroldo aroldus', 'a', NULL, '111.111.111-11', FALSE, TRUE, FALSE, FALSE, NULL, NULL),
('d@d', 'cabral cabralzius', 'a', NULL, '222.222.222-22', FALSE, FALSE, TRUE, FALSE, NULL, NULL),
('f@f', 'tonin toninhus', 'a', NULL, '333.333.333-33', FALSE, FALSE, FALSE, TRUE, NULL, NULL),
('g@g', 'g g', 'a', NULL, '444.444.444-44', TRUE, FALSE, FALSE, FALSE, NULL, NULL);



INSERT INTO cidade_model (nome, estado) VALUES
('Belo Horizonte', 'MG'),
('Uberlândia', 'MG'),
('Contagem', 'MG'),
('Juiz de Fora', 'MG'),
('Betim', 'MG'),
('Montes Claros', 'MG'),
('Ribeirão das Neves', 'MG'),
('Divinópolis', 'MG'),
('Ipatinga', 'MG'),
('Sete Lagoas', 'MG');


INSERT INTO banco_leite_model (nome, endereco, telefone, latitude, longitude) VALUES
('Banco de Leite BH', 'Rua A, 100, Belo Horizonte', '31-1234-5678', -19.920830, -43.937778),
('Banco de Leite Uberlândia', 'Av. B, 200, Uberlândia', '34-2345-6789', -18.918611, -48.277222),
('Banco de Leite Contagem', 'Rua C, 300, Contagem', '31-3456-7890', -19.931944, -44.053611),
('Banco de Leite Juiz de Fora', 'Av. D, 400, Juiz de Fora', '32-4567-8901', -21.764444, -43.349444),
('Banco de Leite Betim', 'Rua E, 500, Betim', '31-5678-9012', -19.967222, -44.198611);


INSERT INTO doacao_model (data_doacao, quantidade_ml, usuario_id, banco_de_leite_id) VALUES
('2025-01-10', 150, 1, 1),
('2025-01-12', 200, 2, 2),
('2025-01-15', 100, 3, 3);
