package app;

import preprocess.*;
import models.*;

public class Test {
    public static void main(String args[]) {
        // LOAD CSV DATA
        CSVReader reader = new CSVReader();

        //reader.read("src/app/iris.data", true); // classification 
        //reader.read("src/app/ratos.csv", false); // classification
        reader.read("src/app/housing.csv", false); // regression
        
        double[][] data;
        data = reader.getData();

        // PREPARE DATA FOR MODEL
        PreProcessing process = new PreProcessing();
        process.trainTestSplit(data, 0.2, 42);

        // separate between features and labels and normalize them
        process.featuresLabelsSplit();
        process.normalize();

        // get the train and test data
        double[][] trainFeatureData = process.getTrainFeatureData();
        double[] trainLabelData = process.getTrainLabelData();

        double[][] testFeatureData = process.getTestFeatureData();
        double[] testLabelData = process.getTestLabelData();

        /*
        for (int i=0; i < trainFeatureData.length; i++){
            String headerStr = "";
            double[] line = trainFeatureData[i];
            double num = trainLabelData[i];
            
            for (int j = 0; j < line.length; j++) 
                headerStr += line[j] + ", ";
            
            System.out.println(headerStr + num);

            
        }
        */

        // CHOOSE CLASSIFIER AND TRAIN IT
        LinearRegression clf = new LinearRegression(0.01, 5000, true, true);
        //LogisticRegression clf = new LogisticRegression(0.001, 5000, 0.5, true);

        clf.fit(trainFeatureData, trainLabelData);
        double[] p = clf.getParameters(); // these can be used to make plots

        //for (int i = 0; i < p.length; i++) {
        //    System.out.println(p[i]);
        //}

        System.out.println("=========================");

        System.out.println(clf.error(testFeatureData, testLabelData));

        // regression test. target = 15.20
        double[] aa = {1.23247,0.00,8.140,0.5380,6.1420,91.70,3.9769,307.0,21.00,396.90,18.72};
        double[] aaNormalized = process.normalize(aa);

        // classification test. target = 1
        //double[] aa = {320,18};
        //double[] aaNormalized = process.normalize(aa);

        System.out.println(clf.predict(aaNormalized));
    }
}