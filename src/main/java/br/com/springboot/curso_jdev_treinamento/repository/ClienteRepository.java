package br.com.springboot.curso_jdev_treinamento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.springboot.curso_jdev_treinamento_model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
	@Query(value = "select u from Cliente u where upper(trim(u.nome)) like %?1%")
	List<Cliente> buscarPorNome (String name);
}
