package guiPack;

import java.awt.BorderLayout;
import java.awt.Dimension;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


public class chartWindowMaker extends javax.swing.JFrame {
    private javax.swing.JPanel jPanelChart;
    //TODO : MUDAR OS NOMES DE Y DEPENDENDO DE REGRESSÃO
    //Faz o plot de cada valor de X na posição do array
    
    public chartWindowMaker(double [] x) {
        initComponents();
        this.jPanelChart.setLayout(new BorderLayout());
        this.jPanelChart.add(this.createGraf(x));
        //this.requestFocus();
        this.setVisible(true);
        setDefaultCloseOperation(Jframe_Form.DISPOSE_ON_CLOSE);
    }
    
    private XYSeriesCollection createDataset(double [] X){
        XYSeries line = new XYSeries("Eficiencia x Epochs");
        XYSeriesCollection dataset = new XYSeriesCollection();
        for(int i=0;i<X.length;i++){
            line.add(X[i],i);
        }
        dataset.addSeries(line);
        return dataset;
    }
    
    //faz o chart
    private JFreeChart createLineChart(XYSeriesCollection dataset){
        JFreeChart lineChart = ChartFactory.createXYLineChart("Eficiencia por epochs",
                                              "Taxa de acerto", "Epochs", dataset,
                                       PlotOrientation.HORIZONTAL, true,true,false);
        return lineChart;
    }
    //coloca o chart em um panel
    private ChartPanel createGraf(double [] X){
        
        XYSeriesCollection dataset = this.createDataset(X);
        
        JFreeChart chart = this.createLineChart(dataset);
        
        
        ChartPanel forPlotChartPanel = new ChartPanel(chart);
        
        forPlotChartPanel.setPreferredSize(new Dimension(700,400));
        
        return forPlotChartPanel;

    }

    @SuppressWarnings("unchecked")                         
    private void initComponents() {

        jPanelChart = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jPanelChartLayout = new javax.swing.GroupLayout(jPanelChart);
        jPanelChart.setLayout(jPanelChartLayout);
        jPanelChartLayout.setHorizontalGroup(
            jPanelChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jPanelChartLayout.setVerticalGroup(
            jPanelChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelChart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelChart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }                        
                                       
}
