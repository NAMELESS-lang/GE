package model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonReciver {
	private String categoria;
	private String input;
	
	@JsonCreator
	public JsonReciver(@JsonProperty("categoria") String categoria, @JsonProperty("input") String input ) {
		this.categoria = categoria;
		this.input = input;
	}
	
	public String getCategoria() { return categoria;}

	public void setCategoria(String categoria) { this.categoria = categoria;}

	public String getInput() { return input;}

	public void setInput(String input) { this.input = input;}
}
