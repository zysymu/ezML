package algoritmo;
//TODO : substituir pelo seu algoritmo com mais de uma dependencia em X
//       Padronizar em relação a classe RLogistica

public class RLinear {
	private double a,b; // para regressão a + bx;
	private double[] valX, valY;
	public RLinear(double valX[], double valY[]){
		
		this.valX = new double[valX.length]; // determina os tamanhos
		this.valY = new double[valX.length];
		
		this.valX = valX.clone(); // faz uma copia do array recebido (referencia diferente)
		this.valY = valY.clone();
	}
	
	public void treino() {
		double somaX=0, somaY=0, somax2=0, somaxy=0; //somas da formula
		 for(int i = 0; i<valX.length; i++) { 
			 somaX = somaX + valX[i];
			 somaY = somaY + valY[i];
			 somax2 = somax2 + valX[i]*valX[i];
			 somaxy = somaxy + valX[i]*valY[i];
		 }
		 this.a = ( somaY*somax2 - somaX*somaxy) / ( valX.length*somax2 - somaX*somaX ); //aplicacao da formula
		 this.b = ( valX.length*somaxy - somaX*somaY ) / ( valX.length*somax2 - somaX*somaX);
		 System.out.println(" Modelo treinado ");
	}
	
	public void printModelo() { 
		String regressao = String.format(" %1$.3f + %2$.3fx ",a,b);
		System.out.println("Modelo de regressão Linear: " + regressao);
	}

}
