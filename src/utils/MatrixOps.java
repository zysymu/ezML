package utils;

public class MatrixOps {
    public static double[][] dotProduct(double[][] a, double[][] b) {
        int m = a.length;
        int n = b[0].length;
        
        double[][] dotProduct = new double[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dotProduct[i][j] = a[i][j] * b[j][i];
            }
        }

        return dotProduct;
    }

    public static double[] dotProduct(double[][] a, double[] b) {
        int m = a.length;
        int n = a[0].length;
        
        double[] dotProduct = new double[m];

        for (int i = 0; i < m; i++) {
            double sum = 0;

            for (int j = 0; j < n; j++) {
                sum += a[i][j] * b[j];
            }
            dotProduct[i] = sum;
        }

        return dotProduct;
    }

    public static double dotProduct(double[] a, double[] b) {
        double dotProduct = 0;
    
        for (int i = 0; i < a.length; i++) {
            dotProduct += a[i] * b[i];
        }
    
        return dotProduct;
    }

    public static double[][] transpose(double[][] x) {
        int m = x.length;
        int n = x[0].length;
        
        double[][] xTranspose = new double[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                xTranspose[i][j] = x[j][i];
            }
        }

        return xTranspose;
    }

    public static double[][] inverse(double[][] x) {
        int m = x.length;
        int n = x[0].length;
        
        double[][]
    }
}
