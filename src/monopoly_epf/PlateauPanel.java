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
    File gauche1 = new File("PionCalculatrice3_gauche.png");
    File haut1 = new File("PionCalculatrice3_haut.png");
    File droite1 = new File("PionCalculatrice3_droite.png");
    File bas1 = new File("PionCalculatrice3_bas.png");
    File orientation1 = new File("PionCalculatrice3_gauche.png");
    File gauche2 = new File("PionDiode3_gauche.png");
    File haut2 = new File("PionDiode3_haut.png");
    File droite2 = new File("PionDiode3_droite.png");
    File bas2 = new File("PionDiode3_bas.png");
    File orientation2 = new File("PionDiode3_gauche.png");
    File gauche3 = new File("PionErlenmeyer3_gauche.png");
    File haut3 = new File("PionErlenmeyer3_haut.png");
    File droite3 = new File("PionErlenmeyer3_droite.png");
    File bas3 = new File("PionErlenmeyer3_bas.png");
    File orientation3 = new File("PionErlenmeyer3_gauche.png");
    File gauche4 = new File("PionOlga3_gauche.png");
    File haut4 = new File("PionOlga3_haut.png");
    File droite4 = new File("PionOlga3_droite.png");
    File bas4 = new File("PionOlga3_bas.png");
    File orientation4 = new File("PionOlga3_gauche.png");
       
    @Override 
    public void paintComponent(Graphics g) {
        //super.paintComponent(g);
        try{
             //g.drawOval(1,1, 800, 600);
            Image img = ImageIO.read(new File("plateaujeu.png"));
            g.drawImage(img, 0,0,800, 798, this);
            if(afficherPions==true) {
                Image Calculette = ImageIO.read(orientation1);
                g.drawImage(Calculette, P1.coordX, P1.coordY, P1.taille, P1.taille, this);
                Image Diode = ImageIO.read(orientation2);
                g.drawImage(Diode, P2.coordX, P2.coordY, P2.taille, P2.taille, this);
                Image Erlenmeyer = ImageIO.read(orientation3);
                g.drawImage(Erlenmeyer, P3.coordX, P3.coordY, P3.taille, P3.taille, this);
                Image Olga = ImageIO.read(orientation4);
                g.drawImage(Olga, P4.coordX, P4.coordY, P4.taille, P4.taille, this);
                Image TickMatiereGauche = ImageIO.read(new File("TicMatière_gauche.png"));
                Image TickMatiereHaut = ImageIO.read(new File("TicMatière_haut.png"));
                Image TickMatiereDroite = ImageIO.read(new File("TicMatière_droite.png"));
                Image TickMatiereBas = ImageIO.read(new File("TicMatière_bas.png"));
                Image TickModuleGauche = ImageIO.read(new File("TicModule_gauche.png"));
                Image TickModuleHaut = ImageIO.read(new File("TicModule_haut.png"));
                Image TickModuleDroite = ImageIO.read(new File("TicModule_droite.png"));
                Image TickModuleBas = ImageIO.read(new File("TicModule_bas.png"));
                if(plateauAssocie.plateaudejeu[1].maison!=0) {
                    if(plateauAssocie.plateaudejeu[1].maison>=1 && plateauAssocie.plateaudejeu[1].maison<5) {
                        g.drawImage(TickMatiereGauche, plateauAssocie.plateaudejeu[1].coordX_premiertick, plateauAssocie.plateaudejeu[1].coordY_premiertick, 22, 16, this);
                    }
                    if(plateauAssocie.plateaudejeu[1].maison>=2 && plateauAssocie.plateaudejeu[1].maison<5) {
                        g.drawImage(TickMatiereGauche, plateauAssocie.plateaudejeu[1].coordX_premiertick, plateauAssocie.plateaudejeu[1].coordY_premiertick+16, 22, 16, this);
                    }
                    if(plateauAssocie.plateaudejeu[1].maison>=3 && plateauAssocie.plateaudejeu[1].maison<5) {
                        g.drawImage(TickMatiereGauche, plateauAssocie.plateaudejeu[1].coordX_premiertick, plateauAssocie.plateaudejeu[1].coordY_premiertick+32, 22, 16, this);
                    }
                    if(plateauAssocie.plateaudejeu[1].maison>=4 && plateauAssocie.plateaudejeu[1].maison<5) {
                        g.drawImage(TickMatiereGauche, plateauAssocie.plateaudejeu[1].coordX_premiertick, plateauAssocie.plateaudejeu[1].coordY_premiertick+48, 22, 16, this);
                    }
                    else if(plateauAssocie.plateaudejeu[1].maison==5) {
                        g.drawImage(TickModuleGauche, plateauAssocie.plateaudejeu[1].coordX_premiertick, plateauAssocie.plateaudejeu[1].coordY_premiertick+24, 22, 16, this);
                    }
                }
            }
          
        } catch(IOException e){
            e.printStackTrace();
        }
        
        //g.fillOval(0,0,700,700); dessine un oval sur le plateau
             }
    
    }
    

