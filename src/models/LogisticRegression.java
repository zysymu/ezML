package models;

import java.util.Random;
import java.lang.Math;

public class LogisticRegression extends Algorithm {
    private double learningRate;
    private double epochs;
    private double threshold;
    private boolean trackError = true;// tracks error on training set
    private double[] parameters; // theta
    private double[][] X;
    private double[] y;
    private double[] historicAccuracy;

    public LogisticRegression(double tr) {
        this.threshold = tr;
    }

    // "constructors"
    @Override
    public void setLearningRate(double learningRate) {
        this.learningRate = learningRate;
    }

    @Override
    public void setEpochs(int epochs) {
        this.epochs = epochs;
        historicAccuracy = new double[epochs];
    }

    @Override
    public void setData(double[][] X, double[] y) {
        this.X = X;
        this.y = y;
    }
    
    @Override
    public void fit() {
        // get dimensions
        int nFeatures = X[0].length;
        
        // initialize variables
        Random random = new Random();

        parameters = new double[nFeatures+1]; // +1 due to theta_0 (bias)

        for (int i = 0; i < parameters.length; i++) {
            parameters[i] = random.nextDouble();
        }

        batchGradientDescent(X,y);
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

            if (trackError)
                historicAccuracy[e] = accuracy(X, y);
        }
    }

    @Override
    public double predict(double[] X) {
        double d = 0;
        
        for (int i = 1; i < X.length; i++) {
            d += X[i-1] * parameters[i];
        }

        double linearModel = d + parameters[0];
        double prob = sigmoid(linearModel);

        return prob > threshold ? 1. : 0.;
    }

    @Override
    public double[] getParameters() {
        return parameters;
    }

    public double accuracy(double[][] X, double[] y) {
        double sum = 0;

        for (int i = 0; i < X.length; i++) {
            sum += predict(X[i]) == y[i] ? 1. : 0.;
        }

        return sum/X.length;
    }

    private static double sigmoid(double z) {
        return 1 / (1 + Math.exp(-z));
    }

    @Override
    public double[] getEfficincyData(){
        return historicAccuracy;
    }
}