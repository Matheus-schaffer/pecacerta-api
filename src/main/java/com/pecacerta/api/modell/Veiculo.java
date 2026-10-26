package com.pecacerta.api.modell;

import jakarta.persistence.*;

//[cite: 11]
@Entity
@Table(name = "veiculos")
public class Veiculo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 60, nullable = false)
  private String marca;

  @Column(length = 80, nullable = false)
  private String modelo;

  @Column(name = "ano_inicial", nullable = false)
  private Integer anoInicial;

  @Column(name = "ano_final", nullable = false)
  private Integer anoFinal;

  public Veiculo() {}

  // Getters e Setters
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  
  public String getMarca() { return marca; }
  public void setMarca(String marca) { this.marca = marca; }
  
  public String getModelo() { return modelo; }
  public void setModelo(String modelo) { this.modelo = modelo; }
  
  public Integer getAnoInicial() { return anoInicial; }
  public void setAnoInicial(Integer anoInicial) { this.anoInicial = anoInicial; }
  
  public Integer getAnoFinal() { return anoFinal; }
  public void setAnoFinal(Integer anoFinal) { this.anoFinal = anoFinal; }

  //[cite: 6]
  public boolean aceitaAno(Integer ano) {
      if (ano == null) return false;
      return ano >= this.anoInicial && ano <= this.anoFinal;
  }
}