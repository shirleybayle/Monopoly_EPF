/*
* BOCAHUT Manon - FEVRIER Titouan - CHAUVET Ylan - BAYLE Shirley
 */
package monopoly_epf;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author shirl
 */
public class PlateauPanel extends JPanel { //trouver l'info des pions associés et associer des pions aux coordonnees + trouver l'info des maisons
    Plateau plateauAssocie;
    
    @Override 
    public void paintComponent(Graphics g) {
        //super.paintComponent(g);
        try{
             
            Image img = ImageIO.read(new File("plateaujeu.png"));
            g.drawImage(img, 400, 17, this);
        } catch(IOException e){
            e.printStackTrace();
        }
        
        //g.fillOval(0,0,700,700); dessine un oval sur le plateau
             }
    
    }
    

