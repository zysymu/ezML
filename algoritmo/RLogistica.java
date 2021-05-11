/* */
package algoritmo;

import static java.lang.Math.exp;

public class RLogistica {
    //Função cost usada é a 
    //Sigmoid(h) = 1 / (1 + exp(- theta_0 + theta_1*x_1 + theta_2*x_2 + ... )

    private double[][] x;
    private double[][] theta;
    private double[] y;

    public RLogistica(double [][] data) {
        this.setData(data);
    } //só para poder criar RLogistica sem passar nada
    public RLogistica(){};
    
    
    public void setData(double[][] data) {
        int numCol, numLin;
        numLin = data.length;       //M
        numCol = data[0].length;    //N

        //uma das colunas é de y
        this.x = new double[numLin][numCol - 1]; //MATRIZ (M x N-1)
        this.y = new double[numLin];         //ARRAY  (M x 1)
        //ja seta o theta (parametros de função custo Sigmoid
        this.theta = new double[numCol][1];   //MATRIZ (N x 1)

        //copia data para x e y;
        for (int i = 0; i < data.length; i++) {
            System.arraycopy(data[i], 0, x[i], 0, numCol - 1);
            y[i] = data[i][numCol - 1];
        }
    }

    //                              (n x 1)           (m x n-1)       (m x 1)
    private double[] cost(/*double[][] theta, double[][] x, double[] y*/) {
        //x,y,theta são os da propria classe 
        int m; //para uso nas contas
        m = this.x.length;
        int i, j; //iteradores
        //Matriz com uma coluna de 1 a esquerda de x com dados
        //A coluna de 1's é precisa para o parametro independende das entradas em Sigmoid(cost)
        double[][] X_bias = new double[x.length][x[0].length + 1];

        for (i = 0; i < x.length; i++) { //Add coluna de 1 a esquerda de x formando X_bias 
            X_bias[i][0] = 1;
            for (j = 0; j < x[0].length; j++) {
                X_bias[i][j + 1] = x[i][j];
            }
        }
        //h = matriz(M x n) x (N x 1) = (M x 1)
        double[] h = sigmoid(matrizMulti(X_bias, theta));
        /*
        h tem os thetas(pesos das entradas)
        agora é preciso achar a derivada de Sigmoid e minimiza-la
        (Minimize the cost)
         */

        // Só para manipulação dentro do loop do calulo da derivada de cost 
        // em relação aos thetas
        double[][] temp1 = new double[1][h.length];
        double[][] temp2 = new double[h.length][1];
        double[][] temp3 = new double[1][1];

        // O que irá alterando os pesos theta conforme a derivada 
        double[] gradient = new double[theta.length];

        for (i = 0; i < gradient.length; i++) {
            // h - y termo a termo
            for (j = 0; j < h.length; j++) {
                temp1[0][j] = (h[j] - y[j]);
            }
            // pego uma das colunas de X_bias (relativa a derivada
            // de cost em relação ao theta[i]

            for (j = 0; j < X_bias.length; j++) {
                temp2[j][0] = X_bias[j][i];
            }

            // (1 x m) x (m x 1) = (1 x 1) 
            temp3 = matrizMulti(temp1, temp2);

            //gradiente de cada theta
            gradient[i] = (1.0 / m) * temp3[0][0];
        }

        return gradient;

        /*
        caso queira ver o valor do cost a cada interação
        mas o que importa é a sua derivada para a regressão 
        
        double J = 0, sum = 0;
        //COST FUNCTION 
        for (i = 0; i < h.length; i++) {
            sum = sum + (y[i] * Math.log(h[i]) + (1 - y[i]) * (Math.log(1 - h[i])));
        }
        J = (-1.0 / m) * sum;
        
         */
    }

    private double[] sigmoid(double[][] z) {
        // se z for uma matriz [1][1] pode se testar apenas um x 
        // retorno seria um vetor[1]

        double[] h = new double[z.length];
        for (int i = 0; i < z.length; i++) {
            h[i] = 1 / (1 + exp(-z[i][0]));
        }
        return h;
    }

    //função para treino de modelo
    public double[][] train(/*double[][] theta, double[][] x, double[] y*/) {
        double alpha = 0.005; //(LEARNING RATE) - posso add um optimização aqui
        for (int i = 0; i < 50000; i++) {

            //double[] grad = cost(theta, x, y);
            double[] grad = cost();
            for (int j = 0; j < grad.length; j++) {
                theta[j][0] = theta[j][0] - alpha * grad[j];
            }
        }
        System.out.println("Modelo Treinado");
        return theta;
    }

    public double [] predict(double[][] theta, double[][] x /*,treshold */) {

        double[][] X_bias = new double[x.length][x[0].length + 1];
        for (int i = 0; i < x.length; i++) { //Add coluna de 1 a esquerda de x formando X_bias
            X_bias[i][0] = 1;
            for (int j = 0; j < x[0].length; j++) {
                X_bias[i][j + 1] = x[i][j];
            }
        }
        //              (m x n)        (n x 1)
        double[][] m = (matrizMulti(X_bias, theta));//sigmoid recebe matriz

        double[] res = new double[m.length];
        double[] probs = sigmoid(m);
        
        double treshold = 0.5; //pode ser um parametro que o usario escolhe 
        // geralmento o padrão é 0.5;
        for (int i = 0; i < probs.length; i++) {
            if (probs[i] > treshold) {
                res[i] = 1;
            } else {
                res[i] = 0;
            }
        }

        return res;
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
