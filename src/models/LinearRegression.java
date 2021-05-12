package models;

import utils.MatrixOps;

public class LinearRegression {
    double coefficients;
    double intercept;

    public LinearRegression() {

    }
    
    public void fit(double[][] X, double[] y) {
        int m = X.length;
        int n = X[0].length;

        double[][] xT = new double[n][m];
        xT = MatrixOps.transpose(X);
        
        n = xT[0].length;
        double[][] product = new double[m][n];
        product = MatrixOps.dotProduct(xT, X);

        
    }

    public void predict(double[][] X) {

    }
}