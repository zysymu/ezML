package leitor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Leitor {
	//Classe para ler os arquivos de dados
	private String pathString;
	public Leitor(String path) {
		this.pathString = path;
	}
	public void setArquivo(String path){
		this.pathString = path;
		//Caso se não se queira ficar instanciando novos leitores
	}
	
	public ArrayList<String> lerArq() { //retorna um arrayList de string com o lido no arq
		File arq = new File(pathString); //cria um obj file para o scanner usar
		ArrayList<String> conteudo = new ArrayList<String>();
		try {
			Scanner leitorArq = new Scanner(arq); //Instancia o Scanner
			while(leitorArq.hasNext()) { // Enquanto ainda pode ler
				conteudo.add(leitorArq.nextLine()); // vai add no arrayList
			}
			leitorArq.close();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage() );
		}
		return conteudo;
	}
}
