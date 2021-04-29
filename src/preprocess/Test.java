//package preprocess;

public class Test {
    public static void main(String args[]) {
        // load csv data into variable
        CSVReader reader = new CSVReader();
        reader.read("src/preprocess/iris.data", true);
        //reader.printData(24);
        
        double[][] data;
        data = reader.getData();

        // split data into training and testing sets
        Split split = new Split();
        split.trainTestSplit(data, 0.2, 42);
        //double[][] train = split.getTrainData();
        //double[][] test = split.getTestData();

        // separate between features and labels
        split.separateFeaturesLabels(4);
        double[][] trainFeatureData = split.getTrainFeatureData();
        double[] trainLabelData = split.getTrainLabelData();
        
        //System.out.println(trainFeatureData[0][]);

        for (int i=0; i < trainFeatureData.length; i++){
            String headerStr = "";
            double[] line = trainFeatureData[i];
            double num = trainLabelData[i];
            
            for (int j = 0; j < line.length; j++) 
                headerStr += line[j] + ", ";
            
            System.out.println(headerStr + num);
        }
    }

}