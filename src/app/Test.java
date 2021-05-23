package app;

import preprocess.*;
import models.*;
    
public class Test {
    public static void main(String args[]) {
        // LOAD CSV DATA
        CSVReader reader = new CSVReader();

        //reader.read("src/app/iris.data", true); // classification 
        //reader.read("src/app/ratos.csv", false); // classification
        reader.read("src/app/heart.csv", true); // classification
        //reader.read("src/app/housing.csv", false); // regression
        
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
        //LinearRegression clf = new LinearRegression(true);
        //clf.setLearningRate(0.0001);
        //clf.setEpochs(1000);
        //clf.setStochastic();
        
        //LogisticRegression clf = new LogisticRegression(0.5);
        //clf.setLearningRate(0.001);
        //clf.setEpochs(5000);
        //clf.TrackError();

        NaiveBayes clf = new NaiveBayes();

        clf.setData(trainFeatureData, trainLabelData);
        clf.fit();
        System.out.println("=========================");

        double[] p = clf.getParameters(); // these can be used to make plots

        /*
        System.out.println("parameters: theta_0 (bias), theta_1, theta_2, ...");
        for (int i = 0; i < p.length; i++) {
            System.out.println(p[i]);
        }
        System.out.println("=========================");
        */

        System.out.println("mean");
        p = process.getMean();
        for (int i = 0; i < p.length; i++) {
            System.out.println(p[i]);
        }
        System.out.println("=========================");

        System.out.println("std");
        p = process.getStd();
        for (int i = 0; i < p.length; i++) {
            System.out.println(p[i]);
        }
        System.out.println("=========================");

        System.out.println("test error/accuracy");
        System.out.println(clf.accuracy(testFeatureData, testLabelData));

        // regression test. target = 34.90
        //double[] aa = {0.03359,75.00,2.950,0.4280,7.0240,15.80,5.4011,252.0,18.30,395.62,1.98};
        //double[] aaNormalized = process.normalize(aa);

        // classification test. target = 1
        //double[] aa = {320,18};
        double[] aa = {74,0,1,120,269,0,0,121,1,0.2,2,1,2};
        double[] aaNormalized = process.normalize(aa);

        System.out.println("prediction");
        System.out.println(clf.predict(aaNormalized));
    }
}