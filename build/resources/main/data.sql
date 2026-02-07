-- Inserindo Tipos de Usuário
INSERT INTO tipo_usuario (nm_tipo_usu, ds_tipo_usu) VALUES ('ADMIN', 'Administrador do sistema');
INSERT INTO tipo_usuario (nm_tipo_usu, ds_tipo_usu) VALUES ('USUARIO', 'Usuário comum');

-- Inserindo Categorias de Resíduos
INSERT INTO categorias_residuo (nm_catres_cre, ds_catres_cre) VALUES ('PILHAS', 'Pilhas e baterias portáteis');
INSERT INTO categorias_residuo (nm_catres_cre, ds_catres_cre) VALUES ('COMPUTADORES', 'Notebooks, desktops e periféricos');
INSERT INTO categorias_residuo (nm_catres_cre, ds_catres_cre) VALUES ('CELULARES', 'Smartphones e tablets');
INSERT INTO categorias_residuo (nm_catres_cre, ds_catres_cre) VALUES ('ELETRODOMESTICOS', 'Microondas, cafeteiras, etc.');

-- Inserindo Situações de Descarte
INSERT INTO situacao_descarte (nm_situac_desc, ds_situac_desc) VALUES ('PENDENTE', 'Aguardando coleta');
INSERT INTO situacao_descarte (nm_situac_desc, ds_situac_desc) VALUES ('CONCLUIDO', 'Descarte realizado com sucesso');
INSERT INTO situacao_descarte (nm_situac_desc, ds_situac_desc) VALUES ('CANCELADO', 'Descarte cancelado pelo usuário');

-- Inserindo Tipos de Ponto de Coleta
INSERT INTO tipo_ponto_coleta (nm_tipo_pco, ds_tipo_pco) VALUES ('FIXO', 'Ponto de coleta permanente');
INSERT INTO tipo_ponto_coleta (nm_tipo_pco, ds_tipo_pco) VALUES ('EVENTO', 'Ponto de coleta temporário (campanha)');

-- Inserindo Usuário de Exemplo (Login: admin@ecotrack.com.br / Senha: admin@ecotrack.com.br)
INSERT INTO usuarios (nm_usuari_usu, ds_email_usu, ds_senha_usu, id_tipo_usu) 
VALUES ('Administrador', 'admin@ecotrack.com.br', '$2a$10$4bavax3Au.zdU.eb6iQQzuXe1Vg6zK/xoMqGXA53pEK6E/JfJ96JW', 1);

-- Inserindo Endereços de Exemplo
INSERT INTO enderecos (cd_numcep_end, nm_lograd_end, ds_bairro_end, ds_cidade_end, ds_estado_end, ds_comple_end, ds_mapurl_end)
VALUES ('01310100', 'Avenida Paulista', 'Bela Vista', 'São Paulo', 'SP', '1578', 'https://www.google.com/maps/search/?api=1&query=Avenida+Paulista+1578+Sao+Paulo');
INSERT INTO enderecos (cd_numcep_end, nm_lograd_end, ds_bairro_end, ds_cidade_end, ds_estado_end, ds_comple_end, ds_mapurl_end)
VALUES ('04571010', 'Avenida das Nações Unidas', 'Brooklin', 'São Paulo', 'SP', '12901', 'https://www.google.com/maps/search/?api=1&query=Avenida+das+Nacoes+Unidas+12901+Sao+Paulo');
INSERT INTO enderecos (cd_numcep_end, nm_lograd_end, ds_bairro_end, ds_cidade_end, ds_estado_end, ds_comple_end, ds_mapurl_end)
VALUES ('20040002', 'Avenida Rio Branco', 'Centro', 'Rio de Janeiro', 'RJ', '156', 'https://www.google.com/maps/search/?api=1&query=Avenida+Rio+Branco+156+Rio+de+Janeiro');
INSERT INTO enderecos (cd_numcep_end, nm_lograd_end, ds_bairro_end, ds_cidade_end, ds_estado_end, ds_comple_end, ds_mapurl_end)
VALUES ('30140061', 'Rua da Bahia', 'Lourdes', 'Belo Horizonte', 'MG', '1900', 'https://www.google.com/maps/search/?api=1&query=Rua+da+Bahia+1900+Belo+Horizonte');
INSERT INTO enderecos (cd_numcep_end, nm_lograd_end, ds_bairro_end, ds_cidade_end, ds_estado_end, ds_comple_end, ds_mapurl_end)
VALUES ('80060000', 'Rua XV de Novembro', 'Centro', 'Curitiba', 'PR', '1299', 'https://www.google.com/maps/search/?api=1&query=Rua+XV+de+Novembro+1299+Curitiba');

-- Inserindo Pontos de Coleta de Exemplo
INSERT INTO pontos_coleta (nm_poncol_pco, id_endere_end, id_usuari_usu, id_tipo_pco)
VALUES ('Ecoponto Central Paulista', 1, 1, 1);
INSERT INTO pontos_coleta (nm_poncol_pco, id_endere_end, id_usuari_usu, id_tipo_pco)
VALUES ('TecnoRecicla Brooklin', 2, 1, 1);
INSERT INTO pontos_coleta (nm_poncol_pco, id_endere_end, id_usuari_usu, id_tipo_pco)
VALUES ('EcoRio Centro', 3, 1, 1);
INSERT INTO pontos_coleta (nm_poncol_pco, id_endere_end, id_usuari_usu, id_tipo_pco)
VALUES ('BH Sustentável - Lourdes', 4, 1, 2);
INSERT INTO pontos_coleta (nm_poncol_pco, id_endere_end, id_usuari_usu, id_tipo_pco)
VALUES ('Curitiba GreenTech', 5, 1, 1);