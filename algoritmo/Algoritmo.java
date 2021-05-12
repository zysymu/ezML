package algoritmo;

public abstract class Algoritmo {
	
    public abstract double [][] train();         //treino de cada alg
    public abstract double []   predict(double[][] x);       // previsao de um ou mais teste
    public abstract void        printModel();    // printa o modelo matemtico
    public abstract void        getDescription();// breve descrição do q faz
    public abstract int         getID();         // ID do alg (linear = 1, logistica = 2, arvore = 3) 
    /* Talvez
    public abstract double[]    cost();
    public abstract void        setData(double[][] data);
    ...
    */
    
}
