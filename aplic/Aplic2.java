package aplic;

import algoritmo.*;
import java.util.Arrays;
import preprocess.*;

public class Aplic2 {

    public static void main(String[] args) {
    
       CSVReader a = new CSVReader();
        Split spliter = new Split();
        
        
        a.read("C:\\Users\\dioge\\Downloads\\heart.csv", true);
        
        spliter.trainTestSplit(a.getData(), 0.7, 8);
        double[][] d = a.getData();
        
        spliter.separateFeaturesLabels(d[0].length-1);
        
        RLogistica r = new RLogistica(spliter.getTrainData());
        
        double[][] theta = r.train();
        
        double[][] teste = {{67,1,0,160,286,0,0,108,1,1.5,1,3,2}};
        double[] previsoes = r.predict(teste);
        System.out.println(previsoes[0]);
        
        
        /*double[] real = spliter.getTestLabelData();
        
        double contador = 0;
        for(int i = 0; i<previsoes.length;i++){
            if(previsoes[i] == real[i]){
                contador ++;
            }
        }*/
        //double acerto = contador/previsoes.length;
        //System.out.println("Taxa de acerto: " + acerto*100 + "%" );
        
        for(int i = 0;i<theta.length;i++)
              System.out.println(Arrays.toString(theta[i]));
       
        
    }
}
