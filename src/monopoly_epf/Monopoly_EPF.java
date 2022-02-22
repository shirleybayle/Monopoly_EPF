/*
 * BOCAHUT Manon - FEVRIER Titouan - CHAUVET Ylan - BAYLE Shirley
 */
package monopoly_epf;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;

/**
 *
 * @author shirl
 */
public class Monopoly_EPF extends JFrame {

    /**
     * @param args the command line arguments
     */
    
    public Monopoly_EPF(){
        super("Monopoly EPF");
        WindowListener test = new WindowAdapter() { //création de la fenetre
         public void windowClosing(WindowEvent e){
            System.exit(0);
         }
        };
        addWindowListener(test);
        setSize(1920,1080);
        initComponent();
        this.setVisible(true);
    }
    
    public void initComponent() {
        
    }
    
    public static void main(String[] args) {
        JFrame frame = new Monopoly_EPF();
    }
    
}
