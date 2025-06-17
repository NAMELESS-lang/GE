package model;

import java.sql.SQLException;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Product{
		private String codigo_barra = "";
		private String nomeProduto;
		private String dataValidade;
		private String marca;
		private Integer quantidade;
		private Peso pesoProduto = null;
		private Double valor;
	
		@JsonCreator
		public Product( @JsonProperty("nome_produto") String nomeProduto, 
						@JsonProperty("data_validade") String dataValidade, 
						@JsonProperty("marca") String marca, 
						@JsonProperty("quantidade") Integer quantidade, 
						@JsonProperty("peso_produto") Float peso, 
						@JsonProperty("unidade") String unidade,
						@JsonProperty("valor") Double valor
					  )
					{
						this.nomeProduto = nomeProduto;
						this.dataValidade = dataValidade;
						this.marca = marca;
						this.quantidade = quantidade;
						this.pesoProduto = new Peso(valor, unidade);
						this.valor = valor;
					}
		
		public void setNomeProduto(String nome_item) { this.nomeProduto = nome_item; }
		public void setDataValidade(String data_validade) { this.dataValidade = data_validade; }
		public void setMarca(String marca) { this.marca = marca; }
		public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }
		public void setValor(Double valor) { this.valor = valor; }
		
		public String getcodigoBarras() {return this.codigo_barra;}
		public String getNomeProduto() { return this.nomeProduto;}
		public String getDataValidade() { return this.dataValidade;}
		public String getMarca() { return this.marca;}
		public Integer getQuantidade() {return this.quantidade;}
		public Peso getPeso() { return this.pesoProduto;}
		public Double getValor() { return this.valor;}
		
		public String getProdutoformatado(){
			String formatado = this.pesoProduto.getvalorPeso().toString() + this.pesoProduto.getMedida();
			return formatado;
		}
		
		public String getValorformatado() {
			String formatado = this.valor + "R$";
			return formatado;
		}
		
		public String criarCodigobarra(Conexao_db conexao, ProductDAO productDAO) throws SQLException {
			String codigo = new String();
			for(int i = 0; i <5;i++) {
				codigo += Math.random();
				if(i == 4) {
					boolean retorno = productDAO.consultarCodigobarras(conexao, codigo);
					if(retorno) {
						return codigo;
					}else {
						codigo = "";
						i = 0;
					}
				}
			}
			return codigo;
		}
		
		class Peso{
			@JsonProperty("peso_produto")
			private Double valor_peso;
			@JsonProperty("unidade")
			private String medida;
			
			public Peso(Double valor_peso, String medida) {
				this.valor_peso = valor_peso;
				this.medida = medida;
			}
			
		public void setvalorPeso(Double valor_peso) {this.valor_peso = valor_peso;}
		public void setvalorMedida(String medida) { this.medida = medida;}
		
		public Double getvalorPeso() { return this.valor_peso;}
		public String getMedida() { return this.medida;}
		}
}
