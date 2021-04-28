//package preprocess;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.NumberFormatException;

public class CSVReader {
    private boolean header;
    private String[] contentHeader;
    private ArrayList<String[]> contentStr;
    private double[][] data;

    public void read(String file, String delimiter, boolean header) {
        this.header = header;
        contentStr = new ArrayList<>();

        // read the csv into an ArrayList of String[]
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = null;
        
            while ((line = br.readLine()) != null) {
                // if theres a header, save it to contentHeader
                if (header == false) {
                    contentStr.add(line.split(delimiter));
                }

                else {
                    String[] lineSplit = line.split(delimiter);
                    contentHeader = new String[lineSplit.length];
                    contentHeader = lineSplit;
                    header = false;
                }
            }
            br.close();

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();

        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        // converts csv to a double[]
        /* 
        very sensitive, each csv file must have only numbers and must not have extra lines
        */
        data = new double[contentStr.size()][(contentStr.get(0)).length];

        try {
            for (int i=0; i<contentStr.size(); i++) {
                String[] lineStr = contentStr.get(i);

                for (int j=0; j<lineStr.length; j++) {
                    data[i][j] = Double.parseDouble(lineStr[j]);
                    //System.out.println(Double.parseDouble(lineStr[j]));
                }
            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void read(String file, boolean header) {
        read(file, ",", header);
    }

    public double[][] getData() {
        return data;
    }

    public void printData(int n) {
        if (n >= contentStr.size())
            n = contentStr.size();

        if (header) {
            String headerStr = "";
            for (String s : contentHeader)
                headerStr += s + ", ";
            System.out.println(headerStr);
        }

        for (int i=0; i<n; i++) {
            String[] line = contentStr.get(i);
            System.out.println(Arrays.toString(line));

        }
        
    }
}
