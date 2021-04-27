package aplic;
import java.util.ArrayList;
import algoritmo.*;
import leitor.*;
public class Aplic {

	public static void main(String[] args) {
		
		Leitor l = new Leitor("C:\\Users\\dioge\\Downloads\\t.csv"); //instancia leitor do arquivo
		ArrayList<String> conteudos = new ArrayList<String>(); // cria um array para armazenar
		conteudos = l.lerArq(); //pega os conteudos
		
		double[] valX = new double[conteudos.size()]; //array para guardar os X
		double[] valY = new double[conteudos.size()]; // e Y
		String[] pares; //String[] para dar split nas strings de em conteudo 
		
		// essa parte provavelmente sera feita em uma classe Dados que vai pegar as que lerArq() devolve e converter
		// não sei se tem como já ler um arquivo interpretando direto como double
		int i = 0;
		for(String cont: conteudos) { //para cada cont em conteudo
			pares = cont.split(","); //da split baseado na virgula
			valX[i] = Double.parseDouble(pares[0]); //primeiro do split vai para X
			valY[i] = Double.parseDouble(pares[1]); //segundo para Y
			i++;
		}
		
		//Agora já tenho um Vetor de X e de Y 
		//Só fazer a regressão linear 
		
		Linear rLinear = new Linear(valX, valY);
		rLinear.treino();
		rLinear.printModelo();
		
		
	}
}
