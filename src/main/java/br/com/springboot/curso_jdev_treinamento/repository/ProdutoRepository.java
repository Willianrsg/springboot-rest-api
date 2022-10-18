package br.com.springboot.curso_jdev_treinamento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.springboot.curso_jdev_treinamento_model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	
	@Query(value = "select u from Produto u where upper(trim(u.nome)) like %?1%")
	List<Produto> buscarPorNome (String name);
	

}
