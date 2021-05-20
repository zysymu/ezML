
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
        
        //double[][];
        return null;
    }
    public static double[][] matrizMulti(double[][] a, double[][] b) {
        //Função de multiplicação de matrizes basica 
        double[][] c = new double[a.length][b[0].length];

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                c[i][j] = 0;
                for (int k = 0; k < b.length; k++) {
                    c[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return c;
    }
}
