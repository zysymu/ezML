package models;

public abstract class Algorithm {
    public abstract void fit(double[][] X, double[] y);

    public abstract void predict(double[][] X);
}