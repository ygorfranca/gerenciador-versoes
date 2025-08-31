-- ========================================
-- DADOS MOCK PARA GERENCIADOR DE VERSÕES
-- ========================================

-- Inserir projetos
INSERT INTO projects (id, name, description, slug) VALUES 
(1, 'Sistema de E-commerce', 'Plataforma completa de vendas online com gestão de produtos, pedidos e pagamentos', 'sistema-ecommerce'),
(2, 'Portal do Cliente', 'Portal web para clientes acessarem histórico de pedidos, faturas e suporte', 'portal-cliente'),
(3, 'API Gateway Corporativo', 'Gateway centralizado para todas as APIs da empresa com autenticação e rate limiting', 'api-gateway'),
(4, 'Sistema de CRM', 'Customer Relationship Management para gestão de clientes e leads', 'sistema-crm'),
(5, 'Plataforma de Analytics', 'Sistema de análise de dados e relatórios em tempo real', 'analytics-platform'),
(6, 'App Mobile Delivery', 'Aplicativo móvel para delivery de alimentos e produtos', 'app-delivery'),
(7, 'Sistema de Inventário', 'Controle de estoque e gestão de inventário automatizado', 'sistema-inventario'),
(8, 'Portal de Recursos Humanos', 'Sistema interno para gestão de funcionários e folha de pagamento', 'portal-rh');

-- Inserir versões
INSERT INTO versions (id, major, minor, patch, status, project_id) VALUES 
-- Sistema E-commerce
(1, 1, 0, 0, 'published', 1),
(2, 1, 1, 0, 'published', 1),
(3, 1, 2, 0, 'published', 1),
(4, 1, 2, 1, 'published', 1),
(5, 2, 0, 0, 'draft', 1),
-- Portal Cliente
(6, 1, 0, 0, 'published', 2),
(7, 1, 1, 0, 'published', 2),
(8, 2, 0, 0, 'published', 2),
(9, 2, 1, 0, 'draft', 2),
-- API Gateway
(10, 1, 0, 0, 'published', 3),
(11, 1, 0, 1, 'published', 3),
(12, 1, 1, 0, 'published', 3),
(13, 1, 2, 0, 'deprecated', 3),
-- CRM
(14, 1, 0, 0, 'published', 4),
(15, 1, 1, 0, 'published', 4),
(16, 1, 2, 0, 'draft', 4),
-- Analytics
(17, 1, 0, 0, 'published', 5),
(18, 1, 1, 0, 'draft', 5),
-- App Delivery
(19, 1, 0, 0, 'published', 6),
(20, 1, 0, 1, 'published', 6),
(21, 1, 1, 0, 'published', 6),
(22, 2, 0, 0, 'draft', 6),
-- Inventário
(23, 1, 0, 0, 'published', 7),
(24, 1, 1, 0, 'draft', 7),
-- RH
(25, 1, 0, 0, 'published', 8);

-- Inserir releases
INSERT INTO releases (id, title, description, released_at, version_id) VALUES 
(1, 'E-commerce v1.0.0 - Lançamento Inicial', 'Primeira versão do sistema com funcionalidades básicas de catálogo e carrinho de compras', '2024-01-15 10:00:00', 1),
(2, 'E-commerce v1.1.0 - Pagamentos Online', 'Integração com gateway de pagamento e checkout aprimorado', '2024-03-20 14:30:00', 2),
(3, 'E-commerce v1.2.0 - Sistema de Cupons', 'Adicionado sistema de cupons de desconto e promoções', '2024-05-10 16:00:00', 3),
(4, 'E-commerce v1.2.1 - Correções Críticas', 'Correções de bugs críticos no sistema de pagamento', '2024-05-25 09:15:00', 4),
(5, 'E-commerce v2.0.0 - Nova Arquitetura', 'Refatoração completa com microserviços e nova interface', '2024-08-30 10:00:00', 5),

(6, 'Portal Cliente v1.0.0 - Versão Inicial', 'Portal básico com login e visualização de pedidos', '2024-02-01 11:00:00', 6),
(7, 'Portal Cliente v1.1.0 - Chat e Suporte', 'Adicionado chat em tempo real e sistema de tickets', '2024-04-15 13:45:00', 7),
(8, 'Portal Cliente v2.0.0 - Dashboard Redesign', 'Nova interface moderna e dashboard personalizado', '2024-07-20 15:30:00', 8),
(9, 'Portal Cliente v2.1.0 - Gamificação', 'Sistema de pontos e recompensas para clientes', '2024-08-30 11:00:00', 9),

(10, 'API Gateway v1.0.0 - Core Functions', 'Funcionalidades básicas de roteamento e autenticação', '2024-01-05 12:00:00', 10),
(11, 'API Gateway v1.0.1 - Security Patch', 'Correções de segurança críticas', '2024-01-20 08:00:00', 11),
(12, 'API Gateway v1.1.0 - Rate Limiting', 'Implementação de rate limiting avançado', '2024-03-10 17:00:00', 12),
(13, 'API Gateway v1.2.0 - Monitoring (DEPRECATED)', 'Sistema de monitoramento - versão descontinuada', '2024-05-05 14:00:00', 13),

(14, 'CRM v1.0.0 - Base System', 'Sistema básico de gestão de clientes e leads', '2024-02-20 09:30:00', 14),
(15, 'CRM v1.1.0 - Email Marketing', 'Integração com ferramentas de email marketing', '2024-06-10 11:15:00', 15),
(16, 'CRM v1.2.0 - AI Insights', 'Análises com inteligência artificial sobre clientes', '2024-08-30 12:00:00', 16),

(17, 'Analytics v1.0.0 - Data Visualization', 'Dashboards básicos e relatórios em tempo real', '2024-03-01 16:45:00', 17),
(18, 'Analytics v1.1.0 - Machine Learning', 'Modelos de ML para previsões e insights', '2024-08-30 13:00:00', 18),

(19, 'Delivery App v1.0.0 - MVP', 'Aplicativo básico para pedidos de delivery', '2024-04-01 12:30:00', 19),
(20, 'Delivery App v1.0.1 - Performance Fix', 'Melhorias de performance e correções de bugs', '2024-04-10 10:20:00', 20),
(21, 'Delivery App v1.1.0 - GPS Tracking', 'Rastreamento em tempo real dos entregadores', '2024-06-20 14:40:00', 21),
(22, 'Delivery App v2.0.0 - Multi-store', 'Suporte para múltiplas lojas e categorias', '2024-08-30 14:00:00', 22),

(23, 'Inventário v1.0.0 - Sistema Base', 'Controle básico de estoque e produtos', '2024-05-15 10:50:00', 23),
(24, 'Inventário v1.1.0 - Automação', 'Automação de reposição e alertas de estoque baixo', '2024-08-30 15:00:00', 24),

(25, 'Portal RH v1.0.0 - Gestão Básica', 'Sistema básico de gestão de funcionários', '2024-06-01 08:30:00', 25);

-- Inserir features
INSERT INTO features (id, title, description, release_id) VALUES 
-- E-commerce v1.0.0
(1, 'Catálogo de Produtos', 'Sistema completo de exibição de produtos com categorias e filtros', 1),
(2, 'Carrinho de Compras', 'Funcionalidade de adicionar/remover produtos do carrinho', 1),
(3, 'Sistema de Usuários', 'Cadastro, login e perfil de usuários', 1),
(4, 'Busca Avançada', 'Busca por nome, categoria e faixa de preço', 1),

-- E-commerce v1.1.0
(5, 'Gateway de Pagamento', 'Integração com PagSeguro e PayPal', 2),
(6, 'Checkout Seguro', 'Processo de finalização de compra otimizado', 2),
(7, 'Histórico de Pedidos', 'Visualização de pedidos anteriores', 2),

-- E-commerce v1.2.0
(8, 'Sistema de Cupons', 'Criação e aplicação de cupons de desconto', 3),
(9, 'Programa de Fidelidade', 'Pontos por compra e resgates', 3),
(10, 'Avaliações de Produtos', 'Sistema de reviews e ratings', 3),

-- Portal Cliente v1.0.0
(11, 'Dashboard do Cliente', 'Painel principal com resumo de atividades', 6),
(12, 'Gestão de Perfil', 'Edição de dados pessoais e preferências', 6),
(13, 'Histórico de Transações', 'Visualização completa de transações', 6),

-- Portal Cliente v1.1.0
(14, 'Chat em Tempo Real', 'Comunicação direta com suporte', 7),
(15, 'Sistema de Tickets', 'Abertura e acompanhamento de chamados', 7),
(16, 'Base de Conhecimento', 'FAQ e artigos de ajuda', 7),

-- Portal Cliente v2.0.0
(17, 'Interface Moderna', 'Nova UI/UX responsiva e intuitiva', 8),
(18, 'Dashboard Personalizado', 'Widgets customizáveis pelo usuário', 8),
(19, 'Notificações Push', 'Alertas em tempo real', 8),

-- API Gateway v1.0.0
(20, 'Roteamento de APIs', 'Direcionamento inteligente de requisições', 10),
(21, 'Autenticação JWT', 'Sistema de tokens para APIs', 10),
(22, 'Logs Centralizados', 'Registro unificado de todas as chamadas', 10),

-- API Gateway v1.1.0
(23, 'Rate Limiting Avançado', 'Controle de taxa por usuário e endpoint', 12),
(24, 'Cache Distribuído', 'Cache Redis para melhor performance', 12),
(25, 'Health Checks', 'Monitoramento de saúde dos serviços', 12),

-- CRM v1.0.0
(26, 'Gestão de Contatos', 'CRUD completo de clientes e leads', 14),
(27, 'Pipeline de Vendas', 'Funil de vendas customizável', 14),
(28, 'Relatórios Básicos', 'Relatórios de vendas e conversão', 14),

-- CRM v1.1.0
(29, 'Email Marketing', 'Campanhas automatizadas por email', 15),
(30, 'Segmentação de Clientes', 'Grupos baseados em comportamento', 15),
(31, 'Landing Pages', 'Criador de páginas de captura', 15),

-- Analytics v1.0.0
(32, 'Dashboards Interativos', 'Gráficos dinâmicos e filtráveis', 17),
(33, 'Relatórios Automáticos', 'Geração agendada de relatórios', 17),
(34, 'Métricas em Tempo Real', 'KPIs atualizados constantemente', 17),

-- Delivery App v1.0.0
(35, 'Catálogo Mobile', 'Listagem de produtos otimizada para mobile', 19),
(36, 'Pedidos Online', 'Fluxo completo de pedido no app', 19),
(37, 'Pagamento Integrado', 'Pagamento direto no aplicativo', 19),

-- Delivery App v1.1.0
(38, 'GPS Tracking', 'Rastreamento em tempo real do entregador', 21),
(39, 'Notificações de Status', 'Atualizações sobre o pedido', 21),
(40, 'Avaliação do Entregador', 'Sistema de feedback para entregadores', 21),

-- Inventário v1.0.0
(41, 'Controle de Estoque', 'Gestão de entrada e saída de produtos', 23),
(42, 'Códigos de Barras', 'Leitura e geração de códigos', 23),
(43, 'Relatórios de Movimentação', 'Histórico detalhado de movimentações', 23),

-- Portal RH v1.0.0
(44, 'Cadastro de Funcionários', 'Gestão completa de dados pessoais', 25),
(45, 'Controle de Ponto', 'Registro de entrada e saída', 25),
(46, 'Folha de Pagamento', 'Cálculo automático de salários', 25);

-- Inserir bug fixes
INSERT INTO bug_fixes (id, title, description, release_id) VALUES 
-- E-commerce v1.0.0
(1, 'Correção no cálculo de frete', 'Corrigido erro no cálculo automático de frete por CEP', 1),
(2, 'Fix na validação de CPF/CNPJ', 'Corrigida validação que estava rejeitando documentos válidos', 1),
(3, 'Correção em produtos sem estoque', 'Removida possibilidade de comprar produtos esgotados', 1),

-- E-commerce v1.1.0
(4, 'Fix no processo de pagamento', 'Corrigido travamento na finalização do pagamento', 2),
(5, 'Correção na sincronização de estoque', 'Resolvido problema de estoque inconsistente', 2),

-- E-commerce v1.2.0
(6, 'Fix na aplicação de cupons', 'Corrigido bug que permitia usar cupom expirado', 3),
(7, 'Correção no cálculo de pontos', 'Ajustado cálculo incorreto de pontos de fidelidade', 3),

-- E-commerce v1.2.1
(8, 'Hotfix crítico no gateway', 'Corrigida falha crítica no processamento de pagamentos', 4),
(9, 'Fix na autenticação', 'Resolvido problema de logout automático', 4),
(10, 'Correção na busca de produtos', 'Melhorada performance da busca com muitos resultados', 4),

-- Portal Cliente v1.0.0
(11, 'Fix no carregamento de pedidos', 'Corrigida lentidão ao carregar histórico extenso', 6),
(12, 'Correção na edição de perfil', 'Resolvido erro ao salvar dados pessoais', 6),

-- Portal Cliente v1.1.0
(13, 'Fix na conexão do chat', 'Corrigidas desconexões frequentes do chat', 7),
(14, 'Correção em anexos de tickets', 'Resolvido problema no upload de arquivos', 7),

-- Portal Cliente v2.0.0
(15, 'Fix na responsividade mobile', 'Corrigidos problemas de layout em telas pequenas', 8),
(16, 'Correção nas notificações', 'Ajustado sistema que não enviava algumas notificações', 8),

-- API Gateway v1.0.0
(17, 'Fix em timeout de APIs', 'Ajustado timeout que causava erros desnecessários', 10),
(18, 'Correção na autenticação', 'Resolvido problema com tokens JWT malformados', 10),

-- API Gateway v1.0.1
(19, 'Security patch crítico', 'Corrigida vulnerabilidade de injeção de código', 11),
(20, 'Fix na validação de headers', 'Melhorada validação de headers HTTP', 11),

-- API Gateway v1.1.0
(21, 'Correção no rate limiting', 'Ajustado algoritmo que bloqueava usuários válidos', 12),
(22, 'Fix na invalidação de cache', 'Corrigido problema de cache não atualizado', 12),

-- CRM v1.0.0
(23, 'Fix na importação de contatos', 'Corrigido erro ao importar CSV com caracteres especiais', 14),
(24, 'Correção na busca de leads', 'Melhorada performance da busca em grandes volumes', 14),

-- CRM v1.1.0
(25, 'Fix no envio de emails', 'Corrigido problema com templates de email', 15),
(26, 'Correção na segmentação', 'Ajustados critérios de segmentação que falhavam', 15),

-- Analytics v1.0.0
(27, 'Fix na geração de gráficos', 'Corrigido erro com datasets muito grandes', 17),
(28, 'Correção na exportação de dados', 'Resolvido problema no export para Excel', 17),

-- Delivery App v1.0.0
(29, 'Fix no carregamento de produtos', 'Otimizado carregamento que estava muito lento', 19),
(30, 'Correção na geolocalização', 'Melhorada precisão do GPS', 19),

-- Delivery App v1.0.1
(31, 'Performance crítica mobile', 'Otimizações para dispositivos com pouca memória', 20),
(32, 'Fix na sincronização offline', 'Corrigido problema quando app ficava offline', 20),

-- Delivery App v1.1.0
(33, 'Correção no tracking GPS', 'Ajustado rastreamento que parava de funcionar', 21),
(34, 'Fix nas notificações push', 'Corrigidas notificações que não chegavam', 21),

-- Inventário v1.0.0
(35, 'Fix na leitura de códigos', 'Melhorada precisão do leitor de código de barras', 23),
(36, 'Correção nos relatórios', 'Ajustados relatórios com dados incorretos', 23),

-- Portal RH v1.0.0
(37, 'Fix no cálculo de horas', 'Corrigido cálculo incorreto de horas extras', 25),
(38, 'Correção na folha de pagamento', 'Ajustados descontos que estavam duplicados', 25);

-- Atualizar sequences para PostgreSQL (caso seja usado em produção)
-- SELECT setval('projects_id_seq', (SELECT MAX(id) FROM projects));
-- SELECT setval('versions_id_seq', (SELECT MAX(id) FROM versions));
-- SELECT setval('releases_id_seq', (SELECT MAX(id) FROM releases));
-- SELECT setval('features_id_seq', (SELECT MAX(id) FROM features));
-- SELECT setval('bug_fixes_id_seq', (SELECT MAX(id) FROM bug_fixes));
