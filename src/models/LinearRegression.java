package models;

import java.util.Random;
import java.lang.Math;

public class LinearRegression extends Algorithm {
    private double learningRate;
    private double epochs;
    private boolean stochastic = false;
    private boolean trackMetrics = true; // tracks error on training set
    private double[] parameters; // theta
    private double [] history;

    public LinearRegression(int epochs, double learningRate, boolean stochastic, boolean trackMetrics) {
        this.epochs = epochs;
        this.stochastic = stochastic;
        this.learningRate = learningRate;
        this.trackMetrics = trackMetrics;

        if (trackMetrics)
            history = new double[epochs];
    }

    @Override
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
        if (stochastic == true) {
            stochasticGradientDescent(X, y);
        }
        
        else {
            batchGradientDescent(X,y);
        }
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

            if (trackMetrics)
                history[e] = error(X, y);
        }
    }

    private void batchGradientDescent(double[][] X, double[] y) {
        // get dimensions
        int nSamples = X.length;

        // perform update
        for (int e = 0; e < epochs; e++) {
            for (int j = 0; j < parameters.length; j++) {
                double sum = 0;

                if (j == 0) { // bias
                    for (int m = 0; m < nSamples; m++) {
                        sum += (predict(X[m]) - y[m]);  
                    }
                }

                else { // other params
                    for (int m = 0; m < nSamples; m++) {
                        sum += (predict(X[m]) - y[m]) * X[m][j-1];
                    }
                }

                parameters[j] -= learningRate * sum;
            }

            if (trackMetrics)
                history[e] = error(X, y);
        }
    }

    @Override
    public double predict(double[] X) {
        double d = 0;
        
        for (int i = 1; i < X.length; i++) {
            d += X[i-1] * parameters[i];
        }
        return d + parameters[0];
    }

    public double error(double[][] X, double[] y) { // mean absolute error
        double sum = 0;

        for (int i = 0; i < X.length; i++) {
                //sum += Math.pow(predict(X[i]) - y[i], 2);
                sum += Math.abs(predict(X[i]) - y[i]);
        }

        return sum/X.length;
    }
    
    @Override
    public double[] getParameters() {
        return parameters; // theta_0 + theta_1 x_1 + theta_2 + x_2 + ...
    }

    @Override
    public double[] getMetrics() {
        return history;
    }
}