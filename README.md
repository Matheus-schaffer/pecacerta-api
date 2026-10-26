# 🚗 PeçaCerta — API de Gestão de Auto Peças

Projeto Final da disciplina Programação Web (foco em Backend).[cite: 20] API REST em Java + Spring Boot para controlar catálogo, estoque, compatibilidade com veículos e pedidos de uma loja de peças automotivas.[cite: 20, 21]

**Status:** Etapa 1 (requisitos, modelagem e camada de persistência) — entrega em 26/10/2026, 23h59.[cite: 20]

## 👥 Equipe
| Integrante | Papel |
| :--- | :--- |
| **Matheus Reck Schaffer** | Líder (Responsável pelo envio)[cite: 20] |
| **Felipe dos Santos** | Desenvolvimento[cite: 20] |
| **Luan Ferreira do Amaral** | Desenvolvimento[cite: 20] |

---

## 📑 Sumário
1. [Visão geral](#1-visão-geral)
2. [Tecnologias](#2-tecnologias)
3. [Requisitos funcionais](#3-requisitos-funcionais)
4. [Requisitos não funcionais](#4-requisitos-não-funcionais)
5. [Modelo de dados e diagrama de classes](#5-modelo-de-dados-e-diagrama-de-classes)
6. [Dicionário de dados](#6-dicionário-de-dados)
7. [Regras de negócio](#7-regras-de-negócio)

---

## 1. Visão geral

### 1.1 Problema e justificativa
Uma loja de auto peças vende itens que servem a veículos específicos, e a mesma peça pode servir a vários modelos e anos.[cite: 21] Quando o balcão controla isso de memória ou em planilhas, a peça errada é vendida, o estoque diverge do real e o pedido vira devolução.[cite: 21] O **PeçaCerta** centraliza catálogo, compatibilidade com veículos, estoque e pedidos em uma API REST padronizada, evitando retrabalho no backend.[cite: 21]

### 1.2 Público-alvo
* **Atendentes e vendedores** de balcão (consultam peças e registram pedidos).[cite: 21]
* **Gerentes e administradores** da loja (mantêm cadastros e estoque).[cite: 21]
* **Clientes**, que consultam peças compatíveis com seu veículo e acompanham seus pedidos.[cite: 21]

### 1.3 Objetivos
* Manter o catálogo de peças organizado por categoria e fornecedor.[cite: 21]
* Registrar a compatibilidade de cada peça com marca, modelo e ano de veículos.[cite: 21]
* Registrar pedidos com seus itens e baixar o estoque a cada venda.[cite: 21]
* Expor funções via endpoints REST protegidos por autenticação.[cite: 21]
* Persistir os dados em banco relacional via JPA e Spring Data.[cite: 21]

---

## 2. Tecnologias
| Camada | Tecnologia |
| :--- | :--- |
| **Linguagem** | Java 17 / 21 |
| **Framework** | Spring Boot (Web, Data JPA, Validation, Security)[cite: 20, 24] |
| **Persistência** | JPA / Hibernate, Spring Data JPA (JpaRepository)[cite: 20, 21, 31] |
| **Banco de dados** | PostgreSQL/MySQL (produção) · H2 em memória (testes)[cite: 24, 25] |
| **Autenticação** | JWT, senhas com BCrypt |
| **Documentação** | OpenAPI / Swagger UI[cite: 25] |
| **Build** | Maven |

---

## 3. Requisitos funcionais
| Código | Requisito | Descrição |
| :--- | :--- | :--- |
| **RF01** | Autenticação de usuários | Token JWT com validade. Endpoints exigem token.[cite: 21, 22] |
| **RF02** | Gerenc. de usuários | CRUD de usuários. Definição de perfil. Vínculo de cliente. |
| **RF03** | Gerenc. de categorias | CRUD de categorias. Impede remoção se houver produtos.[cite: 22] |
| **RF04** | Gerenc. de fornecedores | CRUD de fornecedores (CNPJ único). Impede remoção se houver produtos.[cite: 22] |
| **RF05** | Gerenc. de produtos | CRUD de peças (código único, preço > zero, estoque).[cite: 22] |
| **RF06** | Gerenc. de veículos | CRUD de veículos (marca, modelo, intervalo de anos).[cite: 22] |
| **RF07** | Compatibilidade peça-veículo | Associação de veículos a uma peça. Consulta de compatibilidade. |
| **RF08** | Consulta de peças | Listagem paginada com filtros por nome, categoria, fornecedor e veículo.[cite: 23] |
| **RF09** | Gerenc. de clientes | CRUD de clientes (CPF único, e-mail, telefone, endereço).[cite: 23] |
| **RF10** | Criação de pedidos | Cria pedido calculando valor total baseado no preço unitário. Recusa se não houver estoque.[cite: 23] |
| **RF11** | Consulta de pedidos | ADMIN/ATENDENTE consultam tudo. CLIENTE consulta apenas os seus.[cite: 23, 24] |
| **RF12** | Atualização e cancelamento | Atualiza status (CRIADO, PAGO, ENVIADO, ENTREGUE, CANCELADO). Devolve estoque ao cancelar. |
| **RF13** | Controle de estoque | Baixa na criação do pedido. Lista peças abaixo do mínimo.[cite: 24] |

---

## 4. Requisitos não funcionais
| Código | Categoria | Requisito |
| :--- | :--- | :--- |
| **RNF01** | Desempenho | Paginação (max 100 itens). Consultas de 10k produtos em até 2s.[cite: 24] |
| **RNF02** | Segurança | Token JWT via Spring Security. Senhas em hash BCrypt. Controle por perfil.[cite: 24] |
| **RNF03** | Segurança | Bean Validation. Sem exposição de stack trace. Uso de HTTPS em produção.[cite: 24] |
| **RNF04** | Banco de dados | Restrições de unicidade (código, CNPJ, CPF, etc). H2 para testes.[cite: 24, 25] |
| **RNF05** | Banco de dados | `BigDecimal` para valores monetários. Transação única (`@Transactional`) no pedido.[cite: 25] |
| **RNF06** | Padrões REST | Substantivos no plural (`/api/produtos`). Verbos semânticos (GET, POST, PUT, PATCH, DELETE).[cite: 25] |
| **RNF07** | Padrões REST | Tratamento global de erros (`@RestControllerAdvice`) com payload JSON padronizado.[cite: 25] |
| **RNF08** | Arquitetura | Camadas Controller, Service, Repository, Entity, DTO. Documentação Swagger UI.[cite: 25] |

---

## 5. Modelo de dados e diagrama de classes
O diagrama abaixo é renderizado automaticamente pelo GitHub via [Mermaid](https://mermaid.js.org/).

```mermaid
classDiagram
    class Usuario {
        -Long id
        -String email
        -String senha
        -Perfil perfil
        -Boolean ativo
        +autenticar(senha): boolean
    }
    class Cliente {
        -Long id
        -String nome
        -String cpf
        -String email
        -String telefone
        -String endereco
        +possuiConta(): boolean
    }
    class Categoria {
        -Long id
        -String nome
        -String descricao
        +possuiProdutos(): boolean
    }
    class Fornecedor {
        -Long id
        -String razaoSocial
        -String cnpj
        -String email
        -String telefone
        +possuiProdutos(): boolean
    }
    class Produto {
        -Long id
        -String codigo
        -String nome
        -String descricao
        -BigDecimal preco
        -Integer quantidadeEstoque
        -Integer estoqueMinimo
        -Boolean ativo
        +baixarEstoque(qtd): void
        +reporEstoque(qtd): void
        +abaixoDoMinimo(): boolean
    }
    class Veiculo {
        -Long id
        -String marca
        -String modelo
        -Integer anoInicial
        -Integer anoFinal
        +aceitaAno(ano): boolean
    }
    class Pedido {
        -Long id
        -LocalDateTime dataPedido
        -StatusPedido status
        -BigDecimal valorTotal
        +adicionarItem(item): void
        +alterarStatus(novo): void
        +somarItens(): BigDecimal
        +cancelar(): void
    }
    class ItemPedido {
        -Long id
        -Integer quantidade
        -BigDecimal precoUnitario
        +subtotal(): BigDecimal
    }

    Usuario "0..1" -- "0..1" Cliente
    Cliente "1" -- "0..*" Pedido
    Pedido "1" *-- "1..*" ItemPedido
    Produto "1" -- "0..*" ItemPedido
    Categoria "1" -- "0..*" Produto
    Fornecedor "1" -- "0..*" Produto
    Produto "0..*" -- "0..*" Veiculo : compatível com
