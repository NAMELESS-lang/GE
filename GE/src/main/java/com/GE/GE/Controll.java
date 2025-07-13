package com.GE.GE;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonProperty;

import model.ConexaoDB;
import model.Product;
import model.ProductDAO;
import model.JsonReciver;

@RestController // define que esta classe trabalha com requisições de API REST
public class Controll {
	
	@PostMapping("/cadastrar") // Caso o método seja POST este método é chamado
	public String Cadastrar() { // Recebe como argumento o JSON da requisição http e usa automaticamento no construtor do produto
		return "";
	}
	
	@PostMapping("/atualizar")
	public String Atualizar() throws SQLException{
		return "";
	}
	
	@PostMapping("/pesquisar")
	public ArrayList<Product> buscar(@RequestBody JsonReciver jsonreciver) throws SQLException{
		return null;
	}
	
	@PostMapping("/deletar")
	public String deletar() throws SQLException{
		return "";
	}
	}
