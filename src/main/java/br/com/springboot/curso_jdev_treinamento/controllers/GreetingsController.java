package br.com.springboot.curso_jdev_treinamento.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.curso_jdev_treinamento.repository.ClienteRepository;
import br.com.springboot.curso_jdev_treinamento.repository.ProdutoRepository;
import br.com.springboot.curso_jdev_treinamento.repository.UsuarioRepository;
import br.com.springboot.curso_jdev_treinamento_model.Cliente;
import br.com.springboot.curso_jdev_treinamento_model.Produto;
import br.com.springboot.curso_jdev_treinamento_model.Usuario;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
public class GreetingsController {
	
	@Autowired /* IC/CD OU CDI - Injeção de dependencia */
	private UsuarioRepository usuarioRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
   	
	
	/*----------------------------------LISTAR TODOS USUARIOS---------------------------------------------------------*/
	
    
    @GetMapping(value = "listatodos")/*Nosso primeiro método de API*/
    @ResponseStatus /*Retorna os dados para o corpo da resposta*/
    public ResponseEntity<List<Usuario>> listaUsuario(){
    	
    	List<Usuario> usuarios = usuarioRepository.findAll();/*Executa a consulta no banco de dados*/
    	return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);/*Retorna a lista em JSON*/
    }
    
    
    /*----------------------------------SALVAR USUARIO---------------------------------------------------------*/
    
    
    @PostMapping(value = "salvar")/*Mapeia a url*/
    @ResponseBody /*Descrição da resposta*/
    public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario){ /*Recebe os dados para Salvar*/
    	
    	Usuario user = usuarioRepository.save(usuario);
    	
    	return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
    }
    
    
    /*----------------------------------DELETA USUARIO---------------------------------------------------------*/
    
    
    @DeleteMapping(value = "delete")/*Mapeia a url*/
    @ResponseBody /*Descrição da resposta*/
    public ResponseEntity<String> delete(@RequestParam Long iduser){ /*Recebe os dados para Deletar*/
    	
    	usuarioRepository.deleteById(iduser);
    	
    	return new ResponseEntity<String>("User deletado com sucesso!!!", HttpStatus.OK);
    }
    
    
    /*----------------------------------BUSCA POR ID DO USUARIO----------------------------------------------------*/
    
    @GetMapping(value = "buscaruserid")/*Mapeia a url*/
    @ResponseBody /*Descrição da resposta*/
    public ResponseEntity<Usuario> buscaruserid(@RequestParam(name = "iduser") Long iduser){ /*Recebe os dados para Deletar*/
    	
    	Usuario usuario = usuarioRepository.findById(iduser).get();
    	
    	return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
    }
    
    
    /*----------------------------------ATUALIZA USUARIO---------------------------------------------------------*/
    
    
    @PostMapping(value = "atualizar")/*Mapeia a url*/
    @ResponseBody /*Descrição da resposta*/
    public ResponseEntity<?> atualizar(@RequestBody Usuario usuario){ /*Recebe os dados para Salvar*/
    	
    	if (usuario.getId() == null) {
    		return new ResponseEntity<String>("Id não foi informado para atualização.", HttpStatus.OK);
    	}
    	
    	Usuario user = usuarioRepository.saveAndFlush(usuario);
    	
    	return new ResponseEntity<Usuario>(user, HttpStatus.OK);
    }
    
    /*----------------------------------BUSCA POR NOME---------------------------------------------------------*/
    
    @GetMapping(value = "buscarPorNome")/*Mapeia a url*/
    @ResponseBody /*Descrição da resposta*/
    public ResponseEntity<List<Usuario>> buscarPorNome(@RequestParam(name = "name") String name){ /*Recebe os dados para Deletar*/
    	
    	List<Usuario> usuario = usuarioRepository.buscarPorNome(name.trim().toUpperCase());
    	
    	return new ResponseEntity<List<Usuario>>(usuario, HttpStatus.OK);
    }
    
    
    
    /*---------------------------------------------PRODUTOS---------------------------------------------------------------------*/

    /*----------------------------------LISTAR TODOS PRODUTOS---------------------------------------------------------*/
    
    
    @GetMapping(value = "listatodosProduto")/*Nosso primeiro método de API*/
    @ResponseStatus /*Retorna os dados para o corpo da resposta*/
    public ResponseEntity<List<Produto>> listaProduto(){
    	
    	List<Produto> produto = produtoRepository.findAll();/*Executa a consulta no banco de dados*/
    	return new ResponseEntity<List<Produto>>(produto, HttpStatus.OK);/*Retorna a lista em JSON*/
    }
    
    
    /*----------------------------------SALVAR PRODUTO---------------------------------------------------------*/
    
    
    @PostMapping(value = "salvarProduto")/*Mapeia a url*/
    @ResponseBody /*Descrição da resposta*/
    public ResponseEntity<Produto> salvar(@RequestBody Produto produto){ /*Recebe os dados para Salvar*/
    	
    	Produto prod = produtoRepository.save(produto);
    	
    	return new ResponseEntity<Produto>(prod, HttpStatus.CREATED);
    }
    
 /*----------------------------------BUSCA POR NOME PRODUTO----------------------------------------------------*/
    
    @GetMapping(value = "buscarPorNomeProduto")/*Mapeia a url*/
    @ResponseBody /*Descrição da resposta*/
    public ResponseEntity<List<Produto>> buscarPorNomeProduto(@RequestParam(name = "name") String name){ /*Recebe os dados para Deletar*/
    	
    	List<Produto> produto = produtoRepository.buscarPorNome(name.trim().toUpperCase());
    	
    	return new ResponseEntity<List<Produto>>(produto, HttpStatus.OK);
    }
    
    
    /*----------------------------------BUSCA POR ID DO PRODUTO----------------------------------------------------*/
    
    @GetMapping(value = "buscarproudutoid")/*Mapeia a url*/
    @ResponseBody /*Descrição da resposta*/
    public ResponseEntity<Produto> buscarprodutoid(@RequestParam(name = "idproduto") Long idproduto){ /*Recebe os dados para Deletar*/
    	
    	Produto produto = produtoRepository.findById(idproduto).get();
    	
    	return new ResponseEntity<Produto>(produto, HttpStatus.OK);
    }
    
    
    /*----------------------------------DELETA PRODUTO---------------------------------------------------------*/
    
    
    @DeleteMapping(value = "deleteProduto")/*Mapeia a url*/
    @ResponseBody /*Descrição da resposta*/
    public ResponseEntity<String> deleteProduto(@RequestParam Long idproduto){ /*Recebe os dados para Deletar*/
    	
    	produtoRepository.deleteById(idproduto);
    	
    	return new ResponseEntity<String>("Produto deletado com sucesso!!!", HttpStatus.OK);
    }
    
    
    /*----------------------------------ATUALIZA PRODUTO---------------------------------------------------------*/
    
    
    @PostMapping(value = "atualizarProduto")/*Mapeia a url*/
    @ResponseBody /*Descrição da resposta*/
    public ResponseEntity<?> atualizarProduto(@RequestBody Produto produto){ /*Recebe os dados para Salvar*/
    	
    	if (produto.getId() == null) {
    		return new ResponseEntity<String>("Id não foi informado para atualização.", HttpStatus.OK);
    	}
    	
    	Produto prod = produtoRepository.saveAndFlush(produto);
    	
    	return new ResponseEntity<Produto>(prod, HttpStatus.OK);
    }
    
    
    /*----------------------------------CLIENTES---------------------------------------------------------*/
    
/*----------------------------------LISTAR TODOS CLIENTES---------------------------------------------------------*/
	
    
    @GetMapping(value = "listatodosCliente")/*Nosso primeiro método de API*/
    @ResponseStatus /*Retorna os dados para o corpo da resposta*/
    public ResponseEntity<List<Cliente>> listaCliente(){
    	
    	List<Cliente> cliente = clienteRepository.findAll();/*Executa a consulta no banco de dados*/
    	return new ResponseEntity<List<Cliente>>(cliente, HttpStatus.OK);/*Retorna a lista em JSON*/
    }
    
    
    /*----------------------------------SALVAR CLIENTE---------------------------------------------------------*/
    
    
    @PostMapping(value = "salvarCliente")/*Mapeia a url*/
    @ResponseBody /*Descrição da resposta*/
    public ResponseEntity<Cliente> salvar(@RequestBody Cliente cliente){ /*Recebe os dados para Salvar*/
    	
    	Cliente cli = clienteRepository.save(cliente);
    	
    	return new ResponseEntity<Cliente>(cli, HttpStatus.CREATED);
    }
    
    
    /*----------------------------------DELETA CLIENTE---------------------------------------------------------*/
    
    
    @DeleteMapping(value = "deleteCliente")/*Mapeia a url*/
    @ResponseBody /*Descrição da resposta*/
    public ResponseEntity<String> deleteCliente(@RequestParam Long idcliente){ /*Recebe os dados para Deletar*/
    	
    	clienteRepository.deleteById(idcliente);
    	
    	return new ResponseEntity<String>("User deletado com sucesso!!!", HttpStatus.OK);
    }
    
    
    /*----------------------------------BUSCA POR ID DO CLIENTE----------------------------------------------------*/
    
    @GetMapping(value = "buscarclienteid")/*Mapeia a url*/
    @ResponseBody /*Descrição da resposta*/
    public ResponseEntity<Cliente> buscarclienteid(@RequestParam(name = "idcli") Long idcli){ /*Recebe os dados para Deletar*/
    	
    	Cliente cliente = clienteRepository.findById(idcli).get();
    	
    	return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
    }
    
    
    /*----------------------------------ATUALIZA CLIENTE---------------------------------------------------------*/
    
    
    @PostMapping(value = "atualizarCliente")/*Mapeia a url*/
    @ResponseBody /*Descrição da resposta*/
    public ResponseEntity<?> atualizarCliente(@RequestBody Cliente cliente){ /*Recebe os dados para Salvar*/
    	
    	if (cliente.getId() == null) {
    		return new ResponseEntity<String>("Id não foi informado para atualização.", HttpStatus.OK);
    	}
    	
    	Cliente cli = clienteRepository.saveAndFlush(cliente);
    	
    	return new ResponseEntity<Cliente>(cli, HttpStatus.OK);
    }
    
    /*----------------------------------BUSCA POR CLIENTE---------------------------------------------------------*/
    
    @GetMapping(value = "buscarPorNomeCliente")/*Mapeia a url*/
    @ResponseBody /*Descrição da resposta*/
    public ResponseEntity<List<Cliente>> buscarPorNomeCliente(@RequestParam(name = "name") String name){ /*Recebe os dados para Deletar*/
    	
    	List<Cliente> cliente = clienteRepository.buscarPorNome(name.trim().toUpperCase());
    	
    	return new ResponseEntity<List<Cliente>>(cliente, HttpStatus.OK);
    }
}



















