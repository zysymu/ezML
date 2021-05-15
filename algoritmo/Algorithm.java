package algoritmo;

public abstract class Algorithm { 
    public abstract double [][] fit();         //treino de cada alg
    public abstract double []   predict(double[][] x);       // previsao de um ou mais teste
    public abstract void        printModel();    // printa o modelo matemtico
    public abstract void        getDescription();// breve descrição do q faz
    public abstract int         getID();         // ID do alg (linear = 1, logistica = 2, arvore = 3) 
    public abstract void        setData(double[][] data);
    public abstract void        setData(double[][] x, double[] y);
    public abstract void        setEpochs(int epochs);
    public abstract void        setLearingRate(double lr);
    
    /* Talvez
    public abstract double[]    cost();
    public abstract void        setData(double[][] data);
    ...
    */
}
