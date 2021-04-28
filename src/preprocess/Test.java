//package preprocess;

public class Test {
    public static void main(String args[]) {
        CSVReader reader = new CSVReader();
        reader.read("src/preprocess/iris.data", true);
        //reader.printData(24);
        
        double[][] data;
        data = reader.getData();

        Split split = new Split();
        split.trainTestSplit(data, 0.2, 42);
        double[][] train = split.getTrainData();
        double[][] test = split.getTestData();


        for (int i=0; i<train.length; i++) {
            for (int j=0; j<train[0].length; j++) {
                System.out.println(train[i][j]);
            }
        }
    }

}