/* package aplic;

import algoritmo.RLogistica;
import java.util.Arrays;
import preprocess.CSVReader;

public class Aplic2 {

    public static void main(String[] args) {
    
        CSVReader a = new CSVReader();
        a.read("C:\\Users\\dioge\\Downloads\\ratos.csv", false);
        
        RLogistica r = new RLogistica(a.getData());
        
        double[][] theta = r.train();
        double[][] teste = {{10,6}}; //deve dar um rato obeso no exemplo
        System.out.println(Arrays.toString(r.predict(teste)));
        
    }
}*/
