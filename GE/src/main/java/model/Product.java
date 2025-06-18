package model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.sql.Date;
import java.util.Random;
public class Product{
		private String codigo_barra = "";
		private String nomeProduto;
		private Date dataValidade;
		private String marca;
		private Integer quantidade;
		private Peso pesoProduto = null;
		private Double valor;
	
		@JsonCreator
		public Product( @JsonProperty("nome_produto") String nomeProduto, 
						@JsonProperty("data_validade") Long dataValidade, 
						@JsonProperty("marca") String marca, 
						@JsonProperty("quantidade") Integer quantidade, 
						@JsonProperty("peso_produto") Float peso, 
						@JsonProperty("unidade") String unidade,
						@JsonProperty("valor") Double valor
					  )
					{
						this.nomeProduto = nomeProduto;
						this.dataValidade = new Date(dataValidade);
						this.marca = marca;
						this.quantidade = quantidade;
						this.pesoProduto = new Peso(valor, unidade);
						this.valor = valor;
					}
		
		public void setNomeProduto(String nome_item) { this.nomeProduto = nome_item; }
		public void setDataValidade(Date data_validade) { this.dataValidade = data_validade; }
		public void setMarca(String marca) { this.marca = marca; }
		public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }
		public void setValor(Double valor) { this.valor = valor; }
		
		public String getcodigoBarras() {return this.codigo_barra;}
		public String getNomeProduto() { return this.nomeProduto;}
		public Date getDataValidade() { return this.dataValidade;}
		public String getMarca() { return this.marca;}
		public Integer getQuantidade() {return this.quantidade;}
		public Peso getPeso() { return this.pesoProduto;}
		public Double getValor() { return this.valor;}
		
		public String getValorformatado() {
			String formatado = this.valor + "R$";
			return formatado;
		}
		
		public String criarCodigobarra() {
			String codigo = new String();
			Random r = new Random();
			for(int i = 0; i <5;i++) {
				codigo += r.nextInt(10);
				}
			return this.codigo_barra = codigo;
			}
		
		@Override
		public String toString() {
			return "formatar o to string";
		}
		
		class Peso{
			@JsonProperty("peso_produto")
			private Double valorPeso;
			@JsonProperty("unidade")
			private String medida;
			
			public Peso(Double valorPeso, String medida) {
				this.valorPeso = valorPeso;
				this.medida = medida;
			}
			
			
		public void setValorPeso(Double valorPeso) {this.valorPeso = valorPeso;}
		public void setValorMedida(String medida) { this.medida = medida;}
		
		public Double getValorPeso() { return this.valorPeso;}
		public String getMedida() { return this.medida;}
		
		public String getProdutoformatado(){
			return this.valorPeso + this.medida;
		}
		}
}
