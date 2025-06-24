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
public class Controll_product {
	
	public ConexaoDB conexao = new ConexaoDB("localhost","matheus","1234","TESTE");
	public ProductDAO productdao = new ProductDAO();
	
	@PostMapping("/cadastrar") // Caso o método seja POST este método é chamado
	public String Cadastrar(@RequestBody Product produto) throws SQLException { // Recebe como argumento o JSON da requisição http e usa automaticamento no construtor do produto
		String message = "Houve um erro no cadastro, tente novamente!";
		try {
			// Crio o objeto conexão e o inicio, assim como o DAO
			
			this.conexao.iniciarConexao();
			
			// Cadastra o produto no banco de dados
			boolean status = this.productdao.Cadastrar(this.conexao, produto);
			if(status == true) {
				conexao.closeConn();
				return "Produto cadastrado com sucesso";
			}
			throw new SQLException("Houve um erro no processo!");
		}catch(SQLException e) {
			conexao.closeConn();
			System.out.println(e.getMessage());
			return message;
		}
	}
	
	@PostMapping("/atualizar")
	public String Atualizar(@RequestBody Product produto) throws SQLException{
		String message = "Houve um erro no cadastro, tente novamente!";
		try {	
			this.conexao.iniciarConexao();
			
			boolean status = this.productdao.Atualizar(this.conexao, produto);
			if(status = true) {
				conexao.closeConn();
				return "produto atualizado com sucesso!";
			}
			
			conexao.closeConn();
			return "";
		}catch(SQLException e) {
			conexao.closeConn();
			System.out.println(e.getMessage());
			return "Erro ao cadastrar o produto, tente novamente!";
		}
	}
	
	@PostMapping("/pesquisar")
	public ArrayList<Product> buscar(@RequestBody JsonReciver jsonreciver) throws SQLException{
		ArrayList<Product> lista = null;
		try {
			this.conexao.iniciarConexao();
			lista = this.productdao.Pesquisar(this.conexao,jsonreciver);
			return lista;
			
			
		}catch(SQLException e) {
			conexao.closeConn();
			System.out.println(e.getMessage());
			return lista;
		}
	}
	
	@PostMapping("/deletar")
	public String deletar(@RequestBody Product produto) throws SQLException{
		try {
			this.conexao.iniciarConexao();
			boolean status = this.productdao.Deletar(this.conexao, produto);
			if(status) {
				return "Produto deletado com sucesso!";
			}
			throw new SQLException("Deu algum erro!");
		}catch(SQLException e) {
			conexao.closeConn();
			System.out.println(e.getMessage());
			return "Erro ao deletar o produto!";
		}
	}
	}
