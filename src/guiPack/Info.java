package guiPack; 
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class Info {
    //cria Um jFrame que vai informar coisas sobre a aplicação
    JFrame infoFrame;
    JTextArea infoText;
    
    public Info() {
        infoFrame = new JFrame();
        infoFrame.setLayout(new FlowLayout(FlowLayout.CENTER));
        setText();
        infoFrame.add(infoText);
        infoFrame.setTitle("Informações");
        infoFrame.pack();
        infoFrame.setVisible(true);

    }

    private void setText() {
        String str1;
        String str2 = "";
        try {
            BufferedReader lerInfos = null;
            
            try {
                String path;
                path = "informes.txt";
                lerInfos = new BufferedReader(new FileReader(path));
            } catch (FileNotFoundException e) {
                System.out.println("Não achei o arq");
            }

            while ((str1 = lerInfos.readLine()) != null) {
               str2 = str2.concat(str1);
               str2 = str2.concat("\n");
            }
        } catch (IOException ex) {
            Logger.getLogger(Info.class.getName()).log(Level.SEVERE, null, ex);
        }

        infoText = new JTextArea(str2);
        infoText.setEditable(false);
        infoText.setEnabled(false);
        infoFrame.setForeground(Color.blue);
        infoFrame.setPreferredSize(new Dimension(500,200));
    }

}
