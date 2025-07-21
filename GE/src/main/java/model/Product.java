package model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;		

public class Product{
		private String codigoBarra;
		private String nomeProduto;
		private Date dataValidade;
		private String marca;
		private String categoria;
		private Integer quantidade;
		private Peso pesoProduto;
		private Dimensoes dimensoes;
		private Double valor;
		
		@JsonCreator
		public Product( @JsonProperty("codigo_barras") String codigoBarras,
						@JsonProperty("nome_produto") String nomeProduto,
						@JsonProperty("data_validade") Long dataValidade, 
						@JsonProperty("marca") String marca,
						@JsonProperty("categoria") String categoria,
						@JsonProperty("quantidade") Integer quantidade, 
						@JsonProperty("peso_produto") Double peso,
						@JsonProperty("unidade") String unidade,
						@JsonProperty("altura") Double altura,
						@JsonProperty("largura") Double largura,
						@JsonProperty("comprimento") Double comprimento,
						@JsonProperty("valor") Double valor
					  )
					{
						this.codigoBarra = codigoBarras;
						this.nomeProduto = nomeProduto;
						this.dataValidade = new Date(dataValidade);
						this.marca = marca;
						this.categoria = categoria;
						this.quantidade = quantidade;
						this.pesoProduto = new Peso(peso, unidade);
						this.dimensoes = new Dimensoes(altura, largura, comprimento);
						this.valor = valor;
					}
		
		public Product(String codigoBarras, String nomeProduto, Date dataValidade,String categoria, String marca, Integer quantidade, 
				Double peso, String unidade, Double altura, Double largura, Double comprimento, Double valor)
			{
				this.codigoBarra = codigoBarras;
				this.nomeProduto = nomeProduto;
				this.dataValidade = dataValidade;
				this.categoria = categoria;
				this.marca = marca;
				this.quantidade = quantidade;
				this.pesoProduto = new Peso(peso, unidade);
				this.dimensoes = new Dimensoes(altura, largura, comprimento);
				this.valor = valor;
			}
		
		public Product(ResultSet rs) throws SQLException {
			this.codigoBarra = rs.getString("codigo_barras");
			this.nomeProduto = rs.getString("nome_produto");
			this.dataValidade = rs.getDate("data_validade");
			this.categoria = rs.getString("categoria");
			this.marca = rs.getString("marca");
			this.quantidade = rs.getInt("quantidade");
			this.pesoProduto = new Peso(rs.getDouble("peso"),rs.getString("unidade_peso"));
			this.dimensoes = new Dimensoes(rs.getDouble("altura"), rs.getDouble("largura"), rs.getDouble("comprimento"));
			this.valor = rs.getDouble("valor");
		}
		
		// GETTERS
		
		public void setCodigoBarras(String codigoBarras) {this.codigoBarra = codigoBarras;}
		public void setNomeProduto(String nome_item) { this.nomeProduto = nome_item; }
		public void setDataValidade(Date data_validade) { this.dataValidade = data_validade; }
		public void setMarca(String marca) { this.marca = marca; }
		public void setCategoria(String categoria) {this.categoria = categoria; }
		public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }
		public void setValor(Double valor) { this.valor = valor; }
		
		// SETTERS
		
		public String getcodigoBarras() {return this.codigoBarra; }
		public String getNomeProduto() { return this.nomeProduto; }
		public Date getDataValidade() { return this.dataValidade; }
		public String getMarca() { return this.marca; }
		public String getCategoria() {return this.categoria; }
		public Integer getQuantidade() {return this.quantidade; }
		public Peso getPeso() { return this.pesoProduto; }
		public Double getValor() { return this.valor; }
		public Dimensoes getDimensoes() {return this.dimensoes; }
		
		@Override
		public String toString() {
			return "Código barras: "+this.codigoBarra + " Nome do produto: "+this.nomeProduto+" Data de validade: "+this.dataValidade+" Marca: "+this.marca+
					" Quantidade: "+this.quantidade+this.pesoProduto.toString();
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
				
			// SETTERS
				
			public void setValorPeso(Double valorPeso) {this.valorPeso = valorPeso;}
			public void setValorMedida(String medida) { this.medida = medida;}
			
			// GETTERS
			
			public Double getValorPeso() { return this.valorPeso;}
			public String getMedida() { return this.medida;}
			
			@Override
			public String toString() {
				return "Peso: "+this.valorPeso+" "+this.medida;
			}
			}
			
			
			class Dimensoes{
				private Double altura;
				private Double largura;
				private Double comprimento;
				
				
				public Dimensoes(Double altura, Double largura, Double comprimento) {
					this.altura = altura;
					this.largura = largura;
					this.comprimento = comprimento;
				}
				
				// GETTERS
				
				public Double getAltura() { return altura; }
				public Double getLargura() { return largura; }
				public Double getComprimento() { return comprimento; }
				
				// SETTERS
				
				public void setAltura(Double altura) { this.altura = altura; }
				public void setLargura(Double largura) { this.largura = largura; }
				public void setComprimento(Double comprimento) { this.comprimento = comprimento; }
				
				@Override
				public String toString() {
					return "Altura: "+this.altura+" Largura: "+this.largura+" Comprimento: "+this.comprimento;
				}
			
			}
}
