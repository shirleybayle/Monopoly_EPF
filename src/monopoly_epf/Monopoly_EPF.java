/*
 * BOCAHUT Manon - FEVRIER Titouan - CHAUVET Ylan - BAYLE Shirley
 */
package monopoly_epf;

import java.awt.Color;
import java.awt.Dimension;
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
        this.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        
        plateauJeu = new PlateauPanel();
        add(plateauJeu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0,0,-1,-1));
        plateauJeu.setSize(1500,1498);
        //plateauJeu.setBackground(Color.red);
        plateauJeu.setPreferredSize(new Dimension(1500,1498));
        setVisible(true);
    }
    
    PlateauPanel plateauJeu;
    
    public static void main(String[] args) {
        JFrame frame = new Monopoly_EPF();
    }
    
    
   
}
