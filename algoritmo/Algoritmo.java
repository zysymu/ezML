package algoritmo;

public abstract class Algoritmo {
	
    public abstract double [][] train();
    public abstract double []   predict();
    public abstract void        printModel();
    public abstract void        getDescription();
    /* Talvez
    public abstract double[]    cost();
    public abstract void        setData(double[][] data);
    ...
    */
    
}
