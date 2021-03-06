package preprocess;

import java.util.Random;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;

public class PreProcessing {
    private double[][] trainData;
    private double[][] testData;
    private double[][] trainFeatureData;
    private double[][] testFeatureData;
    private double[] trainLabelData;
    private double[] testLabelData;
    private double[] mean;
    private double[] std;

    public void trainTestSplit(double[][] data, double testSize, int randomState) {
        /*
        Splits the data into a training set and a testing set.

        Inputs:
            data (double[][]): 2d array cotaining the data points.

            testSize (double): value between 0. and 1. that represents the proportion of the dataset to include in the test split.

            randomState (int): seed for the random shuffle of `data`.
        */
        Random random = new Random(randomState);
        double[][] dataShuffled = data.clone();

        List<double[]> asList = Arrays.asList(dataShuffled);
        Collections.shuffle(asList, random);
        dataShuffled = asList.toArray(new double[0][0]);
        //System.out.println("toArray = " + Arrays.deepToString(dataShuffled));

        // now split it according to `testSize`
        int sep = (int) (dataShuffled.length * (1.- testSize)); // .length = number of rows / column train 
        int numCols = dataShuffled[0].length;

        trainData = new double[sep][numCols];
        for (int i=0; i<sep; i++) {
            trainData[i] = dataShuffled[i];
        }

        testData = new double[dataShuffled.length - sep][numCols];
        int iActual = 0;
        for (int i=sep; i<dataShuffled.length; i++) {
            testData[iActual] = dataShuffled[i];
            iActual++;
        }
    }

    public void featuresLabelsSplit(int labelCol) {
        /*
        Splits the training set and the testing set into an array of features (2d) and an array of labels (1d).

        Inputs:
            labelCol (int): the position of the column that contains the labels, starts at 0 (default = rightmost column).
        */

        // features of training and testing data
        trainFeatureData = extractFeatures(trainData, labelCol);
        testFeatureData = extractFeatures(testData, labelCol);

        // labels of training and testing data
        trainLabelData = extractLabel(trainData, labelCol); 
        testLabelData =  extractLabel(testData, labelCol);
    }

    public void featuresLabelsSplit() {
        featuresLabelsSplit(trainData[0].length-1);
    }

    public void normalize() {
        /*
        Normalizes feature data for each colun of the training and testing set according to newX = (X - meanColumn) / stdColumn
        */

        int numCols = trainFeatureData[0].length;
        int numSamples = trainFeatureData.length;

        mean = new double[numCols]; // columns
        std = new double[numCols]; // columns

        for (int i = 0; i < numCols; i++) { // column
            // mean
            double meanCol = 0;

            for (int m = 0; m < numSamples; m++) {
                meanCol += trainFeatureData[m][i];
            }

            mean[i] = meanCol/numSamples;

            // std
            double stdCol = 0;

            for (int m = 0; m < numSamples; m++) {
                stdCol += Math.pow(trainFeatureData[m][i] - mean[i], 2);
            }

            std[i] = Math.sqrt(stdCol/(numSamples-1));

            // normalize the training data
            for (int m = 0; m < numSamples; m++) {
                trainFeatureData[m][i] = (trainFeatureData[m][i] - mean[i])/std[i];
            }

            // normalize the testing data (using training's mean and std to avoid leakage)
            for (int m = 0; m < testFeatureData.length; m++) {
                testFeatureData[m][i] = (testFeatureData[m][i] - mean[i])/std[i];
            }
        }
    }

    public double[] normalize (double[] X) {
        /*
        Normalizes an input vector X according to the dataset's normalization.
        To be used when predicting input X on a classifier that was trained on normalized data.

        Inputs:
            X (double[]): array of the features to be normalized (and then used with the classifiers '.predict' method).
        */

        assert(X.length == trainFeatureData[0].length):
        "X de entrada deve ter o mesmo numero de features (colunas) que os dados de treino";

        for (int i = 0; i < X.length; i++) {
            X[i] = (X[i] - mean[i]) / std[i];
        }

        return X;
    }

    // ONE HOT ENCODER FOR LABELS IN CLASSIFICATION PROBLEMS

    public double[] getMean() {
        return mean;
    }

    public double[] getStd() {
        return std;
    }

    public double[][] getTrainFeatureData() {
        return trainFeatureData;
    }

    public double[][] getTestFeatureData() {
        return testFeatureData;
    }

    public double[] getTrainLabelData() {
        return trainLabelData;
    }

    public double[] getTestLabelData() {
        return testLabelData;
    }

    private static double[][] extractFeatures(double[][] data, int colRemove) {
        int row = data.length;
        int col = data[0].length;

        double[][] featureData = new double[row][col-1];

        for (int i = 0; i < row; i++) {
            for (int j = 0, currCol = 0; j < col; j++) {
                if (j != colRemove)
                    featureData[i][currCol++] = data[i][j];
            }    
        }

        return featureData;
    }

    private static double[] extractLabel(double[][] data, int colKeep) {
        int row = data.length;

        double[] labelData = new double[row];

        for (int i = 0; i < row; i++) {
            labelData[i] = data[i][colKeep];
            }

        return labelData;
    }
    
}
