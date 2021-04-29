import preprocess.*;

public class Test {
    public static void main(String args[]) {
        // read the csv file and get its contents
        CSVReader reader = new CSVReader();
        reader.read("src/preprocess/iris.data", true);
        //reader.printData(24);
        
        double[][] data;
        data = reader.getData();

        // split the dataset into training and testing
        Split split = new Split();
        split.trainTestSplit(data, 0.2, 42);
        double[][] train = split.getTrainData();
        double[][] test = split.getTestData();

        // separate between features and labels
        /*
        this is done by the user since this can change depending on the dataset.
        alternatively, we could narrow this down by creating a method that allows the user to
        select which column from `data` contains the labels 
        */
        double[][] X
    }

}