package models;

public abstract class Algorithm {
    public abstract void fit(double[][] x, double[] y);
    public abstract double predict(double[] X);
    
    public abstract double[] getParameters();
    public abstract double[] getMetrics();
}