package dad.javafx.micv.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;

import dad.javafx.micv.model.Nacionalidad;
import javafx.beans.property.ListProperty;

/**
 * @author Ayoze Amaro
 *
 */
public class ReadCSVFiles {

	public static void readNacionalidades (ListProperty<Nacionalidad> nacionalidadesCSV) {
		try {
			File file = new File ("src/main/resources/csv/nacionalidades.csv");
			FileReader readFile = new FileReader(file, Charset.forName("UTF-8"));
			BufferedReader usersRead = new BufferedReader(readFile);
			String lines;
				    
			while ((lines = usersRead.readLine()) != null) {
				Nacionalidad nacionalidad = new Nacionalidad(lines);
				nacionalidadesCSV.add(nacionalidad);
			}
			usersRead.close();
		} catch (IOException e) { 
			e.printStackTrace();
		}
	}
	
	public static void readPaises (ListProperty<String> paisesCSV) {
		try {
			File file = new File ("src/main/resources/csv/paises.csv");
			FileReader readFile = new FileReader(file, Charset.forName("UTF-8"));
			BufferedReader usersRead = new BufferedReader(readFile);
			String lines;
						    
			while ((lines = usersRead.readLine()) != null) {
				paisesCSV.add(lines);
			}
			usersRead.close();
		} catch (IOException e) { 
			e.printStackTrace();
		}
	}
}
