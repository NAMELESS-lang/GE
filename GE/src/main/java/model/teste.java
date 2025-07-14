package model;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class teste {
	public static void main(String []args) {
		try(FileWriter fr = new FileWriter("/home/matheus/GE/GE/Logs/Erros.log", true)){
			try(BufferedWriter bf = new BufferedWriter(fr)){
				bf.newLine();
				bf.write("Oia que coisa de doido");
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());	
		}
	}
}
