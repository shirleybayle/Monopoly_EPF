/*
* BOCAHUT Manon - FEVRIER Titouan - CHAUVET Ylan - BAYLE Shirley
 */
package monopoly_epf;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import java.net.URL;
import javax.swing.JPanel;

/**
 *
 * @author shirl
 */
public class PlateauPanel extends JPanel { //trouver l'info des pions associés et associer des pions aux coordonnees
    Plateau plateauAssocie;
    private Image plateau;
    
    @Override 
    public void paintComponent(Graphics g) {
        plateau = Toolkit.getDefaultToolkit().getImage("/image/plateaujeu.png");
        this.setPreferredSize(new Dimension(plateau.getWidth(this), plateau.getHeight(this)));
        System.out.println(plateau);
        g.drawImage(plateau, 0,0,null);
        //g.fillOval(0,0,1000,1000);
    }
    
}
