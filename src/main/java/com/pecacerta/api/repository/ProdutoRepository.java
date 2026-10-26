package com.pecacerta.api.repository;

import com.pecacerta.api.modell.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

//[cite: 12]
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    Optional<Produto> findByCodigo(String codigo);
    Page<Produto> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
    Page<Produto> findByCategoriaId(Long categoriaId, Pageable pageable);
    Page<Produto> findByFornecedorId(Long fornecedorId, Pageable pageable);
    Page<Produto> findByVeiculosId(Long veiculoId, Pageable pageable);

    // Consulta personalizada exigida na documentação para estoque baixo[cite: 12]
    @Query("SELECT p FROM Produto p WHERE p.quantidadeEstoque < p.estoqueMinimo")
    Page<Produto> findProdutosAbaixoDoMinimo(Pageable pageable);
}