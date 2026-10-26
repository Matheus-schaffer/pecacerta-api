package com.pecacerta.api.modell;
import jakarta.persistence.*;

//[cite: 10]
@Entity
@Table(name = "categorias")
public class Categoria {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 80, nullable = false, unique = true)
  private String nome;

  @Column(length = 255)
  private String descricao;
  
  @OneToMany(mappedBy = "categoria")
  private java.util.List<Produto> produtos;

  // Construtor vazio exigido pelo JPA
  public Categoria() {}

  // Getters e Setters
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  
  public String getNome() { return nome; }
  public void setNome(String nome) { this.nome = nome; }
  
  public String getDescricao() { return descricao; }
  public void setDescricao(String descricao) { this.descricao = descricao; }

  //[cite: 7]
  public boolean possuiProdutos() {
      return produtos != null && !produtos.isEmpty();
  }
}