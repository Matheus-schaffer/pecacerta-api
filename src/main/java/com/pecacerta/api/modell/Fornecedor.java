package com.pecacerta.api.modell;

import jakarta.persistence.*;

//[cite: 10]
@Entity
@Table(name = "fornecedores")
public class Fornecedor {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "razao_social", length = 150, nullable = false)
  private String razaoSocial;

  @Column(length = 14, nullable = false, unique = true)
  private String cnpj;

  @Column(length = 120)
  private String email;

  @Column(length = 20)
  private String telefone;
  
  @OneToMany(mappedBy = "fornecedor")
  private java.util.List<Produto> produtos;

  public Fornecedor() {}

  // Getters e Setters
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  
  public String getRazaoSocial() { return razaoSocial; }
  public void setRazaoSocial(String razaoSocial) { this.razaoSocial = razaoSocial; }
  
  public String getCnpj() { return cnpj; }
  public void setCnpj(String cnpj) { this.cnpj = cnpj; }
  
  public String getEmail() { return email; }
  public void setEmail(String email) { this.email = email; }
  
  public String getTelefone() { return telefone; }
  public void setTelefone(String telefone) { this.telefone = telefone; }

  //[cite: 7]
  public boolean possuiProdutos() {
      return produtos != null && !produtos.isEmpty();
  }
}