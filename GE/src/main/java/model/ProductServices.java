package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import org.springframework.web.bind.annotation.RequestBody;

// "localhost","matheus","1234","TESTE"

public class ProductServices {
	private ProductDAO productDAO;
	private Reports report;
	
	ProductServices(){
		this.productDAO = new ProductDAO();
		this.report = new Reports();
	}
	
	// GETTERS 
	public ProductDAO getProductdao() { return productDAO; }
	public Reports getReport() { return report; }
	
	// SETTERS
	public void setProductdao(ProductDAO productdao) { this.productDAO = productdao; }
	public void setReport(Reports report) { this.report = report; }
	
	
	public String cadastrarProduct(@RequestBody Product produto) throws SQLException{
		try(ConexaoDB conexao = new ConexaoDB("localhost","matheus", "1234", "TESTE")){
			conexao.getConn().setAutoCommit(false);
			this.productDAO.Cadastrar(conexao, produto);
			
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	
	public boolean criarCodigobarras(ConexaoDB conexao, Product product) throws SQLException{
		
		StringBuilder codigo = new StringBuilder(); //Construtor do código de barras
		Random r = new Random(); // Criando um gerador de números aleatórios
		boolean repeat = false; // Variável responsável por repetir o loop de criação do código barras
		
		
		while(repeat == false) { // estrutura usada para analisar se o código já existe no db, e caso exista é criado um novo valor até que haja um inexistente
			codigo.delete(0, codigo.length());
			for(int i =0; i<5;i++) { //Cria o código de barras com números aleatórios
				codigo.append(r.nextInt(10));
			}
			repeat = this.productDAO.consultarCodigoBarras(conexao, codigo.toString());
		}
		product.setCodigoBarras(codigo.toString());
		return true;
}
	
}
