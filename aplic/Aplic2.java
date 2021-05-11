package aplic;

import algoritmo.RLogistica;
import java.util.Arrays;
import leitor.CSVReader;

public class Aplic2 {

    public static void main(String[] args) {
    
        CSVReader a = new CSVReader();
        a.read("C:\\Users\\dioge\\Downloads\\ratos.csv", false);
        
        RLogistica r = new RLogistica(a.getData());
        
        double[][] theta = r.train();
        double[][] teste = {{100,5}}; //deve dar um rato obeso no exemplo
        System.out.println(Arrays.toString(r.predict(theta, teste)));
        
    }
}
