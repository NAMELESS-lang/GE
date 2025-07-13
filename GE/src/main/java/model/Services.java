package model;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

// "localhost","matheus","1234","TESTE"

public class Services {
	private ProductDAO productDAO;
	
	Services(){
		this.productDAO = new ProductDAO();
	}
	
	// GETTERS 
	public ProductDAO getProductdao() { return productDAO; }
	
	// SETTERS
	public void setProductdao(ProductDAO productdao) { this.productDAO = productdao; }
	
	public void criarCodigobarras(Product product){
			
		StringBuilder codigo = new StringBuilder();
		Random r = new Random();
		for(int i = 0; i < 5; i++) {
			codigo.append(r.nextInt());
		}
		product.setCodigoBarras(codigo.toString());
	}
	
	public boolean cadastrarProduct(Product product) throws SQLException {
		try(ConexaoDB conexao = new ConexaoDB("localhost","matheus", "1234", "TESTE")){
			conexao.getConn().setAutoCommit(false);
			this.criarCodigobarras(product);
			while(this.productDAO.consultarCodigoBarras(conexao, product.getcodigoBarras())) {
				this.criarCodigobarras(product);
			};
			
			if (this.productDAO.cadastrar(conexao, product)) {
				conexao.getConn().commit();
				return true;
			}
			
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	public boolean deletarProduct(Product product) throws SQLException{
		try(ConexaoDB conexao = new ConexaoDB("localhost","matheus", "1234", "TESTE")){
			
			conexao.getConn().setAutoCommit(false);
			if(this.productDAO.deletar(conexao, product)) {
				conexao.getConn().commit();
				return true;
			}
			
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	public boolean atualizarProduct(Product product) throws SQLException{
		try(ConexaoDB conexao = new ConexaoDB("localhost","matheus", "1234", "TESTE")){
				
			conexao.getConn().setAutoCommit(false);
			
			 if(this.productDAO.atualizar(conexao, product)) {
				 conexao.getConn().commit();
				 return true;
			 }
				
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	public ArrayList<Product> consultarProducts(Category category) throws SQLException {
		try(ConexaoDB conexao = new ConexaoDB("localhost","matheus", "1234", "TESTE")){
			
			conexao.getConn().setAutoCommit(false);
			ArrayList<Product> listaProdutos = this.productDAO.pesquisar(conexao,category); 
			if( listaProdutos != null) {
				conexao.getConn().commit();
				return listaProdutos;
			}
			return null;
		
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
}
