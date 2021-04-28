//package preprocess;

import java.util.Random;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;

public class Split {
    private double[][] trainData;
    private double[][] testData;

    public void trainTestSplit(double[][] data, double testSize, int randomState) {
        /*
        Input:
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
        }
    }

    public double[][] getTrainData () {
        return trainData;
    }

    public double[][] getTestData () {
        return testData;
    }
}
