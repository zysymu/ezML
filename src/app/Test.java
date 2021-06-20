package app;

import preprocess.*;
import models.*;
    
public class Test {
    public static void main(String args[]) {
        /* 
        LOAD CSV DATA
        */
        CSVReader reader = new CSVReader();

        //reader.read("src/app/iris.data", true); // classification 
        //reader.read("src/app/ratos.csv", false); // classification
        reader.read("src/app/heart.csv", true); // classification
        //reader.read("src/app/housing.csv", false); // regression
        
        double[][] data;
        data = reader.getData();

        /*
        PREPARE DATA FOR MODEL
        */
        PreProcessing process = new PreProcessing();
        process.trainTestSplit(data, 0.8, 42);

        // separate between features and labels and normalize them
        process.featuresLabelsSplit();
        process.normalize();

        // get the train and test data
        double[][] trainFeatureData = process.getTrainFeatureData();
        double[] trainLabelData = process.getTrainLabelData();

        double[][] testFeatureData = process.getTestFeatureData();
        double[] testLabelData = process.getTestLabelData();

        /* 
        CHOOSE CLASSIFIER AND TRAIN IT
        */
        //LinearRegression clf = new LinearRegression(1000, 0.0001, true, true);
        
        //LogisticRegression clf = new LogisticRegression(0.5, 500, 0.001, true);

        NaiveBayes clf = new NaiveBayes();
        
        //SVM clf = new SVM(0.1, 500, 0.001, true);

        clf.fit(trainFeatureData, trainLabelData);

        /*
        OPTIONALLY PRINT THE PARAMETERS
        */
        //double[] p = clf.getParameters();
        
        //System.out.println("parameters: theta_0 (bias), theta_1, theta_2, ...");
        //for (int i = 0; i < p.length; i++) {
        //    System.out.println(p[i]);
        //}

        /*
        OPTIONALLY PRINT THE TRAINING METRIC
        */
        //double[] h = clf.getMetrics();
        
        //for (int i = 0; i < h.length; i++) {
        //    System.out.println(h[i]);
        //}

        /*
        TEST ERROR/ACCURACY ON TEST SET
        */
        System.out.println("test error/accuracy");
        System.out.println(clf.accuracy(testFeatureData, testLabelData));

        /*
        PROCESS AND EVALUATE A NEW DATA POINT (these are actually taken from the CSV data)
        */
        // regression test. target = 34.90, housing.csv
        //double[] evalData = {0.03359,75.00,2.950,0.4280,7.0240,15.80,5.4011,252.0,18.30,395.62,1.98};
        //double[] evalDataNormalized = process.normalize(evalData);

        // classification test. target = 1, heart.csv
        double[] evalData = {74,0,1,120,269,0,0,121,1,0.2,2,1,2};
        double[] evalDataNormalized = process.normalize(evalData);

        System.out.println("prediction");
        System.out.println(clf.predict(evalDataNormalized));
    }
}