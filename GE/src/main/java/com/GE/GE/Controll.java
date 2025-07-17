package com.GE.GE;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.PostMapping;
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
		if(sv.cadastrarProduct(product)) {
			return "Produto cadastrado com sucesso!";
		}
		return "Erro ao cadastrar o produto, tente novamente!";
	}
	
	@PostMapping("/atualizar")
	public String Atualizar(@RequestBody Product product) throws SQLException{
		Services sv = new Services();
		if(sv.atualizarProduct(product)) {
			return "Produto cadastrado com sucesso!";
		}
		return "Houve um erro ao atualizar o produto, tente novamente!";
	}
	
	@PostMapping("/pesquisar")
	public ArrayList<Product> buscar(@RequestBody Category category) throws SQLException{
		Services sv = new Services();
		ArrayList<Product> lp = sv.consultarProducts(category);
		if(lp != null) {
			return lp;
		}
		return null;
	}
	
	@PostMapping("/deletar")
	public String deletar(@RequestBody Product product) throws SQLException{
		Services sv = new Services();
		if(sv.deletarProduct(product)) {
			return "Produto deletado com sucesso!";
		}
		return "Houve um erro ao deletar o produto, tente novamente mais tarde!";
	}
	}
