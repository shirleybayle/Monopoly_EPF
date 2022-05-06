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
    boolean afficherPions=false;
    Pion P1;
    Pion P2;
    Pion P3;
    Pion P4;
    
    @Override 
    public void paintComponent(Graphics g) {
        //super.paintComponent(g);
        try{
             //g.drawOval(1,1, 800, 600);
            Image img = ImageIO.read(new File("plateaujeu.png"));
            g.drawImage(img, 0,0,800, 798, this);
            if(afficherPions==true) {
                Image Calculette = ImageIO.read(new File("PionCalculatrice3.png"));
                g.drawImage(Calculette, P1.coordX, P1.coordY, 45, 45, this);
                Image Diode = ImageIO.read(new File("PionDiode3.png"));
                g.drawImage(Diode, P2.coordX, P2.coordY, 45, 45, this);
                Image Erlenmeyer = ImageIO.read(new File("PionErlenmeyer3.png"));
                g.drawImage(Erlenmeyer, P3.coordX, P3.coordY, 45, 45, this);
                Image Olga = ImageIO.read(new File("PionOlga3.png"));
                g.drawImage(Olga, P4.coordX, P4.coordY, 45, 45, this);
            }
          
        } catch(IOException e){
            e.printStackTrace();
        }
        
        //g.fillOval(0,0,700,700); dessine un oval sur le plateau
             }
    
    }
    

