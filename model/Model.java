
//package model;

/*
/*
O modelo recebe um Algortimo(A) e os dados;.
 */
/*
import algoritmo.*;
import preprocess.CSVReader;

public class Model {
// Rlinear = 1; RLogistica = 2; Arvore = 3

//private int algoritmo;
    private double[][] fullData;
    private double[][] trainData;
    private double[][] testData;
    private Algoritmo alg;

    public Model(Algoritmo alg, double[][] fullData) {
        this.fullData = fullData;
        this.alg = alg;

        modeloPrint(alg.getID());

    }

    public Model(Algoritmo alg, double[][] trainData, double[][] testData) {
        this.trainData = trainData;
        this.testData = testData;
        this.alg = alg;

        modeloPrint(alg.getID());
    }

    private void modeloPrint(int ID) {
        switch (ID) {
            case 1:
                System.out.println("Modelo de Regressão linear criado");
                break;
            case 2:
                System.out.println("Modelo de Regressão Logistica criado");
                break;
            default:
                System.out.println("Modelo de Arvore de decisões criado");
                break;
        }
    }

    
}
*/