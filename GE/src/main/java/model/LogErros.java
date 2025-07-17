package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class LogErros {
	public void registrarError(SQLException sqle) {
		try(FileWriter fr = new FileWriter("/home/matheus/GE/GE/Logs/Erros.log", true)){
			try(BufferedWriter bf = new BufferedWriter(fr)){
				bf.newLine();
				bf.write("Data do erro: " + LocalDateTime.now().toString());
				bf.newLine();
				bf.write(sqle.getMessage());
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());	
		}
	}
}
