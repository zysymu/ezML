//package preprocess;

public class Test {
    public static void main(String args[]) {
        CSVReader reader = new CSVReader();
        reader.read("src/preprocess/iris.data", true);
        reader.printData(24);
        
        double[][] data;
        data = reader.getData();
        System.out.println(data[0][0]);
    }

}
