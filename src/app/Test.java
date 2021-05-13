package app;

import preprocess.*;
import models.*;

public class Test {
    public static void main(String args[]) {
        // load csv data into variable
        CSVReader reader = new CSVReader();
        //reader.read("src/app/iris.data", true);
        reader.read("src/app/winequality-red.csv", true);
        
        double[][] data;
        data = reader.getData();

        // split data into training and testing sets
        Split split = new Split();
        split.trainTestSplit(data, 0.2, 42);

        // separate between features and labels
        split.separateFeaturesLabels(4, true);
        double[][] trainFeatureData = split.getTrainFeatureData();
        double[] trainLabelData = split.getTrainLabelData();

        double[][] testFeatureData = split.getTestFeatureData();
        double[] testLabelData = split.getTestLabelData();

        /*
        for (int i=0; i < trainFeatureData.length; i++){
            String headerStr = "";
            double[] line = trainFeatureData[i];
            double num = trainLabelData[i];
            
            for (int j = 0; j < line.length; j++) 
                headerStr += line[j] + ", ";
            
            System.out.println(headerStr + num);

            break;
        }
        */      

        System.out.println("=========================");

        LinearRegression clf = new LinearRegression(0.01, 10, false);
        clf.fit(trainFeatureData, trainLabelData);
        double[] p = clf.getParameters();

        //for (int i = 0; i < p.length; i++) {
        //    System.out.println(p[i]);
        //}

        System.out.println("=========================");

        System.out.println(clf.error(testFeatureData, testLabelData));

        // normalized data taken as an example. target = 0.09
        double[] aa = {6.945349609935239E-4, -0.004486753313236001, 0.004402155685535225, 2.2498816130148914E-4, -7.89204435143285E-5, -1.8599881038632E-5, 0.3990021084128771, -0.004719770706901076, -0.0033649541840405674, 5.565698711273619E-5, -7.563012530836733E-4};
        //double[] aa = {5.9,3.0,5.1,1.8};
        System.out.println(clf.predict(aa));
    }
}