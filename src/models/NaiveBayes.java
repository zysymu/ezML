package models;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.lang.Math;

public class NaiveBayes extends Algorithm {
    // a generative learning algorithm doesn't have epochs or a learning rate
    
    Set<Integer> uniqueClasses;
    int numClasses;
    double[][] meanClasses;
    double[][] varClasses;
    double[] priorClasses;
    private double[][] X;
    private double[] y;

    public NaiveBayes() {
    }

    // "constructors"
    @Override
    public void setLearningRate(double learningRate) {
    }

    @Override
    public void setEpochs(int epochs) {
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
        int nSamples = X.length;

        // get unique classes
        uniqueClasses = new LinkedHashSet<Integer>();
        for (double label : y) {
            uniqueClasses.add((int) label); // NOT GOOD... but necessary i guess..
        }

        // number of classes
        numClasses = uniqueClasses.size();

        // define parameters for each class (and column)
        meanClasses = new double[numClasses][nFeatures];
        varClasses = new double[numClasses][nFeatures];
        priorClasses = new double[numClasses];

        // calculate these parameters for each class
        for (int c : uniqueClasses) {
            double priorClass = 0;

            // calculate parameters for each column
            for (int j = 0; j < nFeatures; j++) {
                double meanCol = 0;
                double varCol = 0;

                // iterate over samples
                for (int i = 0; i < nSamples; i++) {
                    // get only rows where the label is equal to the class
                    if (y[i] == c) {
                        // mean
                        meanCol += X[i][j];
                    }

                }
                
                meanClasses[c][j] = meanCol/nSamples;

                // calculate variance
                for (int i = 0; i < nSamples; i++) {
                    if (y[i] == c) {
                        // variance
                        varCol += Math.pow(X[i][j] - meanClasses[c][j], 2);
                    }

                }

                varClasses[c][j] = varCol/nSamples;
            }

            // get prior of each class
            for (int i = 0; i < nSamples; i++) {
                if (y[i] == c) {
                    priorClass += 1;
                }

            }

            priorClasses[c] = priorClass/nSamples;
        }
    }

    @Override
    public double predict(double[] X) {
        // probability of X being from each class
        double[] probabilities = new double[numClasses];
        
        for (int c : uniqueClasses) {
            double prior = Math.log(priorClasses[c]);
            double posterior = 0;

            // calculate posterior probability
            for (int j = 0; j < X.length; j++) {          
                posterior += Math.log(probabilityDensityFunction(c, j, X[j]));
            }

            probabilities[c] = prior + posterior;
        }

        // find index of largest probability (argmax)
        double max = probabilities[0];
        int idx = 0;

        for (int i = 0; i < probabilities.length; i++) {
            if (max < probabilities[i]) {
                max = probabilities[i];
                idx = i;
            }
            
        }

        return (double) idx;
    }

    private double probabilityDensityFunction(int c, int col, double colVal) {
        double mean = meanClasses[c][col];
        double var = varClasses[c][col];

        double num = Math.exp(- Math.pow(colVal-mean, 2) / (2*var));
        double den = Math.sqrt(2*Math.PI*var);

        return num/den;
    }

    public double accuracy(double[][] X, double[] y) {
        double sum = 0;

        for (int i = 0; i < X.length; i++) {
            sum += predict(X[i]) == y[i] ? 1. : 0.;
        }

        return sum/X.length;
    }
    
    @Override
    public double[] getParameters() {
        return null; 
    }

    @Override
    public double[] getEfficincyData () {
        return null;
    }
}