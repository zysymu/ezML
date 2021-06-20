package models;

import java.util.Random;
import java.lang.Math;

public class SVM extends Algorithm {
    private double learningRate;
    private double epochs;
    private double lambda;
    private boolean trackMetrics = true; // tracks accuracy on training set
    private double[] parameters; // theta
    private double [] history;

    public SVM(double lambda, int epochs, double learningRate, boolean trackMetrics) {
        this.lambda = lambda; // 0.1 is a good default value, but there are no limits
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

        // turns labels into -1/+1
        double[] yNew = new double[y.length];
        
        for (int i = 0; i < y.length; i++) {
            yNew[i] = y[i] <= 0 ? -1 : 1;
        }

        // train the model
        stochasticGradientDescent(X, yNew);
    }

    private void stochasticGradientDescent(double[][] X, double[] y) {
        // get dimensions
        int nSamples = X.length;
        double d;

        // perform update
        for (int e = 0; e < epochs; e++) {
            for (int m = 0; m < nSamples; m++) {
                // dot product
                d = 0;
        
                for (int i = 1; i < X[m].length; i++) {
                    d += X[m][i-1] * parameters[i];
                }
                
                d =  d + parameters[0];

                if (y[m] * d >= 1) {
                    for (int j = 1; j < parameters.length; j++) {
                        parameters[j] -= learningRate * 2 * lambda * parameters[j];
                    }
                }

                else {
                    parameters[0] -= learningRate * y[m];

                    for (int j = 1; j < parameters.length; j++) {
                        // dot product
                        d = 0;
                        for (int i = 0; i < X[m].length; i++) {
                            d += X[m][i] * y[i];
                        }

                        parameters[j] -= learningRate * (2 * lambda * parameters[j] - d);
                    }
                }
            }

            if (trackMetrics)
                history[e] = accuracy(X, y);
        }
    }

    @Override
    public double predict(double[] X) {
        double d = 0;
        
        for (int i = 1; i < X.length; i++) {
            d += X[i-1] * parameters[i];
        }
        
        d =  d + parameters[0];

        return d <= 0 ? -1 : 1;
    }

    @Override
    public double[] getParameters() {
        return parameters; // theta_0 + theta_1 x_1 + theta_2 + x_2 + ...
    }

    public double accuracy(double[][] X, double[] y) {
        double sum = 0;

        // turns labels into -1/+1
        double[] yy = new double[y.length];
        
        for (int i = 0; i < y.length; i++) {
            yy[i] = y[i] <= 0 ? -1 : 1;
        }

        for (int i = 0; i < X.length; i++) {
            sum += predict(X[i]) == yy[i] ? 1. : 0.;
            //System.out.println(predict(X[i]) + " " + yy[i]);
        }

        return sum/X.length;
    }
    
    @Override
    public double[] getMetrics() {
        return history;
    }

}