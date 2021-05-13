package models;

import java.util.Random;
import java.lang.Math;

public class LinearRegression {
    double learningRate;
    double epochs;
    boolean stochastic;
    double[] parameters; // theta

    public LinearRegression(double learningRate, int epochs, boolean stochastic) {
        this.learningRate = learningRate;
        this.epochs = epochs;
        this.stochastic = stochastic;
    }
    
    public void fit(double[][] X, double[] y) {
        // get dimensions
        int nFeatures = X[0].length;
        
        // initialize variables
        Random random = new Random();

        parameters = new double[nFeatures+1]; // +1 due to theta_0 (bias)

        for (int i = 0; i < parameters.length; i++) {
            parameters[i] = random.nextDouble();
        }

        // train the model
        if (stochastic = true)
            stochasticGradientDescent(X, y);

        batchGradientDescent(X,y);
    }

    private void stochasticGradientDescent(double[][] X, double[] y) {
        // get dimensions
        int nSamples = X.length;

        // perform update
        for (int e = 0; e < epochs; e++) {
            for (int m = 0; m < nSamples; m++) {
                parameters[0] -= learningRate * (predict(X[m]) - y[m]); // * x_j^m = 1

                for (int j = 1; j < parameters.length; j++) {
                    parameters[j] -= learningRate * (predict(X[m]) - y[m]) * X[m][j-1];
                }
            }
        }
    }

    private void batchGradientDescent(double[][] X, double[] y) {
        // get dimensions
        int nSamples = X.length;

        // perform update
        for (int e = 0; e < epochs; e++) {

            for (int j = 1; j < parameters.length; j++) {
                double sum = 0;

                switch (j-1) {
                    case 0:
                    for (int m = 0; m < nSamples; m++) {
                        sum += (predict(X[m]) - y[m]);  
                    }

                    default:
                    for (int m = 0; m < nSamples; m++) {
                        sum += (predict(X[m]) - y[m]) * X[m][j-1];
                    }
                }

                parameters[j] -= learningRate * sum;
            }
        }
    }

    public double predict(double[] X) {
        double d = 0;
        
        for (int i = 1; i < X.length; i++) {
            d += X[i-1] * parameters[i];
        }
        return d + parameters[0];
    }

    public double[] getParameters() {
        return parameters;
    }

    public double error(double[][] X, double[] y) { // mean squared error, 2 b used when testing
        int m = X.length;
        int n = X[0].length;

        double sum = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sum += Math.pow(predict(X[i]) - y[i], 2);
            }
        }

        return sum/m;
    }
}