package models;

public abstract class Algorithm { 
    public abstract void setData(double[][] x, double[] y);
    public abstract void setEpochs(int epochs);
    public abstract void setLearningRate(double learningRate);

    public abstract void fit();
    public abstract double predict(double[] X);
    
    public abstract double[] getParameters();
    public abstract double[] getEfficincyData();
}