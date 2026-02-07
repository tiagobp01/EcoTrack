# EcoTrack: Gestor de Descarte de Resíduos Eletrônicos

EcoTrack é uma solução inovadora para o gerenciamento de descarte de resíduos eletrônicos e pontos de coleta. O sistema conecta usuários que desejam descartar materiais de forma sustentável com administradores que gerenciam os fluxos de coleta e operação.

## 🚀 Funcionalidades Principal

### Área Administrativa (`/adm`)
- **Dashboard Estratégico**: Visualização de estatísticas do sistema (total de usuários, descartes e pontos).
- **Gestão de Usuários**: CRUD completo de perfis com filtros por cargo (Admin/Usuário).
- **Gestão de Pontos de Coleta**: Cadastro e edição de locais de coleta com endereços geocofificados.
- **Controle de Solicitações**: Gestão em tempo real do status de solicitações de descarte.
- **Relatórios Profissionais**:
    - Relatório de Pontos por Estado com gráficos.
    - Relatório de Usuários do Sistema.
    - Relatório de Solicitações com Gráfico de Pizza de status.

### Área do Usuário
- **Cadastro e Autenticação**: Acesso seguro com criptografia de senhas.
- **Solicitação de Descarte**: Interface para usuários solicitarem coletas em pontos específicos.
- **Monitoramento**: Acompanhamento do status de suas solicitações.

## 🛠️ Tecnologias Utilizadas

- **Linguagem**: Java 21
- **Framework**: Spring Boot 3.4
- **Segurança**: Spring Security (Role-based access)
- **Engine de Template**: Thymeleaf
- **Persistência**: Spring Data JPA / Hibernate
- **Banco de Dados**: H2 (Memória para desenvolvimento)
- **Geração de PDF**: OpenPDF & JFreeChart
- **Infraestrutura**: Docker (Build multi-stage com JDK/JRE 21)

## 📦 Como Executar

### Pré-requisitos
- Java 21 ou superior
- Docker (opcional)

### Execução Local
1. Clone o repositório.
2. Execute o comando:
   ```bash
   ./gradlew bootRun
   ```
3. Acesse em: `http://localhost:8080`

### Execução via Docker
1. Construa a imagem:
   ```bash
   docker build -t ecotrack .
   ```
2. Execute o container:
   ```bash
   docker run -p 8080:8080 ecotrack
   ```

## 📂 Estrutura do Projeto

- `src/main/java`: Código fonte Java (Controllers, Services, Models, Repositories).
- `src/main/resources/templates`: Templates Thymeleaf (Frontend).
- `src/main/resources/schema.sql`: Definição de tabelas do banco.
- `src/main/resources/data.sql`: Carga inicial de dados e exemplos.
- `Dockerfile`: Configuração de containerização.

## 📄 Licença

Este projeto foi desenvolvido para fins acadêmicos na Disciplina de Projetos EcoTrack.
