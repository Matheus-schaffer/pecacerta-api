#  PeçaCerta API

**API REST para gestão e venda de auto peças**, desenvolvida em Java com Spring Boot e Spring Data JPA.

##  Objetivo do Sistema
O PeçaCerta centraliza catálogo, compatibilidade com veículos, estoque e pedidos em uma API REST padronizada. O objetivo geral é oferecer um backend confiável para cadastrar peças, verificar sua compatibilidade com veículos e registrar pedidos com controle de estoque.[cite: 21]

##  Equipe
* **Matheus Reck Schaffer** (Líder)
* **Felipe dos Santos**
* **Luan Ferreira do Amaral**[cite: 20]

##  Tecnologias Utilizadas
* **Linguagem:** Java 17 / 21
* **Framework Web:** Spring Boot
* **Persistência:** Spring Data JPA / Hibernate[cite: 20]
* **Banco de Dados de Teste:** H2 Database (em memória)[cite: 24]
* **Gerenciador de Dependências:** Maven

##  Entidades do Domínio (Modelagem)
A camada de persistência foi estruturada com 8 entidades com mapeamentos de relacionamento (1:1, 1:N, N:M):[cite: 25]
* `Usuario` | `Cliente` | `Categoria` | `Fornecedor` | `Produto` | `Veiculo` | `Pedido` | `ItemPedido`[cite: 25, 26]

##  Principais Funcionalidades
* **Catálogo:** Gerenciamento de categorias, fornecedores e produtos.[cite: 21, 22]
* **Compatibilidade:** Associação de peças a marcas, modelos e anos de veículos.[cite: 21, 22]
* **Vendas:** Criação de pedidos, cálculo de subtotais/totais e baixa automática de estoque.[cite: 21, 22, 23]
* **Segurança:** Estrutura base para acesso controlado por perfis (ADMIN, ATENDENTE, CLIENTE).[cite: 21]

---
*Projeto desenvolvido para a Etapa 1 da disciplina de Programação Web.*[cite: 20]
