package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class LogErros {
	public void registrarError(SQLException sqle) {
		try(FileWriter fr = new FileWriter("/home/matheus/GE/GE/Logs/Erros.log", true)){
			try(BufferedWriter bf = new BufferedWriter(fr)){
				sqle.printStackTrace();
				System.out.println(sqle.getMessage());
				bf.newLine();
				bf.write("Data do erro: " + LocalDateTime.now().toString());
				bf.newLine();
				bf.write(sqle.getMessage());
				bf.newLine();
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());	
		}
	}
	
	
	public void limparLogs() {
		try(FileWriter fr = new FileWriter("/home/matheus/GE/GE/Logs/Erros.log")){
			try(BufferedWriter bf = new BufferedWriter(fr)){
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());	
		}
	}
}
