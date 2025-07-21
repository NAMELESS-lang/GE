package com.GE.GE;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import model.Category;
import model.Product;
import model.Services;

@RestController // define que esta classe trabalha com requisições de API REST
public class Controll {
	
	@PostMapping("/cadastrar") // Caso o método seja POST este método é chamado
	public String Cadastrar(@RequestBody Product product) throws SQLException { // Recebe como argumento o JSON da requisição http e usa automaticamento no construtor do produto
		Services sv = new Services();	
		return sv.cadastrarProduct(product);
	}
	
	@PutMapping("/atualizar")
	public String Atualizar(@RequestBody Product product) throws SQLException{
		Services sv = new Services();
		return sv.atualizarProduct(product);
	}
	
	@GetMapping("/pesquisar")
	public ArrayList<Product> buscar(@RequestBody Category category) throws SQLException{
		Services sv = new Services();
		ArrayList<Product> lp = sv.consultarProducts(category);
		return lp;
	}
	
	@DeleteMapping("/deletar")
	public String deletar(@RequestBody String codigo_barras) throws SQLException{
		Services sv = new Services();
		if(sv.deletarProduct(codigo_barras)) {
			return "Produto deletado com sucesso!";
		}
		return "Houve um erro ao deletar o produto!";
	}
	
	@GetMapping("/pesquisartodos")
	public Map<String, String> pesquisarTodos() throws SQLException{
		Services sv = new Services();
		return sv.pesquisarProducts();
	}
	}
