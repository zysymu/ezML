package models;

public abstract class Algorithm {
    public abstract void fit(double[][] X, double[] y);

    public abstract double predict(double[] X);
}