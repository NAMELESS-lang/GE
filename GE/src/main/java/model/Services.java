package model;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

// "localhost","matheus","1234","TESTE"

public class Services {
	private ProductDAO productDAO;
	
	public Services(){
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
			codigo.append(r.nextInt(10));
		}
		product.setCodigoBarras(codigo.toString());
	}
	
	public String cadastrarProduct(Product product) throws SQLException {
		try(ConexaoDB conexao = new ConexaoDB("localhost","matheus", "1234", "TESTE")){
			
			conexao.iniciarConexao();
			conexao.getConn().setAutoCommit(false);
			
			this.criarCodigobarras(product);
			while(this.productDAO.consultarCodigoBarras(conexao, product.getcodigoBarras())) {
				this.criarCodigobarras(product);
			};
			
			int result = this.productDAO.cadastrar(conexao, product);
			
			if (result == 1) {
				conexao.getConn().commit();
				return "Produto cadastrado com sucesso!";
			}
			return "Produto já cadastrado na base de dados";
			
		}catch(SQLException e) {
			LogErros le = new LogErros();
			le.registrarError(e);
			return "Erro ao cadastrar o produto!";
		}
	}
	
	public boolean deletarProduct(Product product) throws SQLException{
		try(ConexaoDB conexao = new ConexaoDB("localhost","matheus", "1234", "TESTE")){
			
			conexao.iniciarConexao();
			conexao.getConn().setAutoCommit(false);
			if(this.productDAO.deletar(conexao, product)) {
				conexao.getConn().commit();
				return true;
			}
			
		}catch(SQLException e) {
			LogErros le = new LogErros();
			le.registrarError(e);
		}
		return false;
	}
	
	public String atualizarProduct(Product product) throws SQLException{
		try(ConexaoDB conexao = new ConexaoDB("localhost","matheus", "1234", "TESTE")){
				
			conexao.iniciarConexao();
			conexao.getConn().setAutoCommit(false);
			
			 if(this.productDAO.atualizar(conexao, product)) {
				 conexao.getConn().commit();
				 return "Produto atualizado com sucesso!";
			 }else {
				 return "Item não encontrado em estoque!";
			 }
				
		}catch(SQLException e) {
			LogErros le = new LogErros();
			le.registrarError(e);
			return "Erro ao atualizar o produto";
		}
	}
	
	public ArrayList<Product> consultarProducts(Category category) throws SQLException {
		try(ConexaoDB conexao = new ConexaoDB("localhost","matheus", "1234", "TESTE")){
			
			conexao.iniciarConexao();
			conexao.getConn().setAutoCommit(false);
			ArrayList<Product> listaProdutos = this.productDAO.pesquisar(conexao,category); 
			if( listaProdutos != null) {
				conexao.getConn().commit();
				System.out.println(listaProdutos);
				return listaProdutos;
			}
			return null;
		
		}catch(SQLException e) {
			LogErros le = new LogErros();
			le.registrarError(e);
			return null;
		}
	}
	
	public Map<String, String> pesquisarProducts() throws SQLException{
		try(ConexaoDB conexao = new ConexaoDB("localhost","matheus", "1234", "TESTE")){
			conexao.iniciarConexao();
			conexao.getConn().setAutoCommit(false);
			
			Map<String, String> dados = this.productDAO.pesquisarProdutos(conexao);
			
			return dados;
			
		}catch(SQLException e) {
			LogErros le = new LogErros();
			le.registrarError(e);
			return null;
		}
	}
}
