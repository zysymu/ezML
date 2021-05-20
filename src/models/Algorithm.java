package models;

public abstract class Algorithm { 
    public abstract void        fit();         //treino de cada alg
    public abstract double      predict(double[] x);       // previsao de um ou mais teste
    public abstract void        setData(double[][] x, double[] y);
    public abstract void        setEpochs(int epochs);
    public abstract void        setLearningRate(double learningRate);
    public abstract double []   getParameters();
    public abstract double []   getEfficincyData();
    /* Talvez
    public abstract double[]    cost();
    public abstract void        setData(double[][] data);
    ...
    */
}