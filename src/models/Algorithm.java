package models;

public abstract class Algorithm {
    public abstract void setData(double[][] X, double[] y);
    
    public abstract void fit();

    public abstract double predict(double[] X);
}