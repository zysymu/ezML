package algoritmo;

import java.util.Random;
import java.lang.Math;

public class LinearRegression extends Algorithm{
    double learningRate;
    double epochs;
    boolean stochastic;
    double[] parameters; // theta
    double[][] x;
    double[] y;

    public LinearRegression(double learningRate, int epochs, boolean stochastic) {
        this.learningRate = learningRate;
        this.epochs = epochs;
        this.stochastic = stochastic;
    }
    public LinearRegression(){
        learningRate = 0.01;
        epochs = 500;
        stochastic = false;
    }

    @Override
    public void setData(double [][] x, double [] y){
        this.y = new double[y.length];
        this.x = new double[x.length][x[0].length];
        
        for(int i = 0;i < x.length;i++){
            this.y[i] = y[i];
            for(int j=0;j < x[0].length;j++){
                this.x[i] = x[i];
            }
        }
        
    }
    public double[][] fit() {
        // get dimensions
        int nFeatures = x[0].length;
        
        // initialize variables
        Random random = new Random();

        parameters = new double[nFeatures+1]; // +1 due to theta_0 (bias)

        for (int i = 0; i < parameters.length; i++) {
            parameters[i] = random.nextDouble();
        }

        // train the model
        if (stochastic = true)
            stochasticGradientDescent(x, y);

        batchGradientDescent(x,y);
        return null; //VER ISSO DPS, SE VALE A PENA TENATR PRINTAR UM MODELO
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

    public double predict(double[] X) { //MUDAR DPS PARA DEVOLVER DOUBLE[]
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
    
    /*public double[][] fit() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/

    @Override
    public double[] predict(double[][] x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void printModel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void getDescription() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getID() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}