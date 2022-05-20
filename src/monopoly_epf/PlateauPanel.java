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
    boolean afficherCartes=false;
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
    
    Carte com;
    Carte chance;
    File com1 = new File("comu1.png");
    File com2 = new File("comu1.png");
    File com3 = new File("comu3.png");
    File com4 = new File("comu4.png");
    File orientationcom = new File("comu1.png");
    File chance1 = new File("chance1.png");
    File chance2 = new File("chance2.png");
    File chance3 = new File("chance3.png");
    File chance4 = new File("chance4.png");
    File orientationchance = new File("chance1.png");
       
    @Override 
    public void paintComponent(Graphics g) {
        //super.paintComponent(g);
        try{
             //g.drawOval(1,1, 800, 600);
            Image img = ImageIO.read(new File("plateaujeu.png"));
            g.drawImage(img, 0,0,800, 798, this);
            if(afficherCartes==true) {
                Image Com = ImageIO.read(orientationcom);
                g.drawImage(Com, 497, 130, 170, 173, this);
                Image Chance = ImageIO.read(orientationchance);
                g.drawImage(Chance, 133, 495, 174, 174, this);
            }
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
                        g.drawImage(TickMatiereGauche, plateauAssocie.plateaudejeu[1].coordX_premiertick, plateauAssocie.plateaudejeu[1].coordY_premiertick, 16, 16, this);
                    }
                    if(plateauAssocie.plateaudejeu[1].maison>=2 && plateauAssocie.plateaudejeu[1].maison<5) {
                        g.drawImage(TickMatiereGauche, plateauAssocie.plateaudejeu[1].coordX_premiertick, plateauAssocie.plateaudejeu[1].coordY_premiertick+16, 16, 16, this);
                    }
                    if(plateauAssocie.plateaudejeu[1].maison>=3 && plateauAssocie.plateaudejeu[1].maison<5) {
                        g.drawImage(TickMatiereGauche, plateauAssocie.plateaudejeu[1].coordX_premiertick, plateauAssocie.plateaudejeu[1].coordY_premiertick+32, 16, 16, this);
                    }
                    if(plateauAssocie.plateaudejeu[1].maison>=4 && plateauAssocie.plateaudejeu[1].maison<5) {
                        g.drawImage(TickMatiereGauche, plateauAssocie.plateaudejeu[1].coordX_premiertick, plateauAssocie.plateaudejeu[1].coordY_premiertick+48, 16, 16, this);
                    }
                    else if(plateauAssocie.plateaudejeu[1].maison==5) {
                        g.drawImage(TickModuleGauche, plateauAssocie.plateaudejeu[1].coordX_premiertick, plateauAssocie.plateaudejeu[1].coordY_premiertick+24, 16, 16, this);
                    }
                }
                if(plateauAssocie.plateaudejeu[3].maison!=0) {
                    if(plateauAssocie.plateaudejeu[3].maison>=1 && plateauAssocie.plateaudejeu[3].maison<5) {
                        g.drawImage(TickMatiereGauche, plateauAssocie.plateaudejeu[3].coordX_premiertick, plateauAssocie.plateaudejeu[3].coordY_premiertick, 16, 16, this);
                    }
                    if(plateauAssocie.plateaudejeu[3].maison>=2 && plateauAssocie.plateaudejeu[3].maison<5) {
                        g.drawImage(TickMatiereGauche, plateauAssocie.plateaudejeu[3].coordX_premiertick, plateauAssocie.plateaudejeu[3].coordY_premiertick+16, 16, 16, this);
                    }
                    if(plateauAssocie.plateaudejeu[3].maison>=3 && plateauAssocie.plateaudejeu[3].maison<5) {
                        g.drawImage(TickMatiereGauche, plateauAssocie.plateaudejeu[3].coordX_premiertick, plateauAssocie.plateaudejeu[3].coordY_premiertick+32, 16, 16, this);
                    }
                    if(plateauAssocie.plateaudejeu[3].maison>=4 && plateauAssocie.plateaudejeu[3].maison<5) {
                        g.drawImage(TickMatiereGauche, plateauAssocie.plateaudejeu[3].coordX_premiertick, plateauAssocie.plateaudejeu[3].coordY_premiertick+48, 16, 16, this);
                    }
                    else if(plateauAssocie.plateaudejeu[3].maison==5) {
                        g.drawImage(TickModuleGauche, plateauAssocie.plateaudejeu[3].coordX_premiertick, plateauAssocie.plateaudejeu[3].coordY_premiertick+24, 16, 16, this);
                    }
                }
                if(plateauAssocie.plateaudejeu[6].maison!=0) {
                    if(plateauAssocie.plateaudejeu[6].maison>=1 && plateauAssocie.plateaudejeu[6].maison<5) {
                        g.drawImage(TickMatiereGauche, plateauAssocie.plateaudejeu[6].coordX_premiertick, plateauAssocie.plateaudejeu[6].coordY_premiertick, 16, 16, this);
                    }
                    if(plateauAssocie.plateaudejeu[6].maison>=2 && plateauAssocie.plateaudejeu[6].maison<5) {
                        g.drawImage(TickMatiereGauche, plateauAssocie.plateaudejeu[6].coordX_premiertick, plateauAssocie.plateaudejeu[6].coordY_premiertick+16, 16, 16, this);
                    }
                    if(plateauAssocie.plateaudejeu[6].maison>=3 && plateauAssocie.plateaudejeu[6].maison<5) {
                        g.drawImage(TickMatiereGauche, plateauAssocie.plateaudejeu[6].coordX_premiertick, plateauAssocie.plateaudejeu[6].coordY_premiertick+32, 16, 16, this);
                    }
                    if(plateauAssocie.plateaudejeu[6].maison>=4 && plateauAssocie.plateaudejeu[6].maison<5) {
                        g.drawImage(TickMatiereGauche, plateauAssocie.plateaudejeu[6].coordX_premiertick, plateauAssocie.plateaudejeu[6].coordY_premiertick+48, 16, 16, this);
                    }
                    else if(plateauAssocie.plateaudejeu[6].maison==5) {
                        g.drawImage(TickModuleGauche, plateauAssocie.plateaudejeu[6].coordX_premiertick, plateauAssocie.plateaudejeu[6].coordY_premiertick+24, 16, 16, this);
                    }
                }
                if(plateauAssocie.plateaudejeu[8].maison!=0) {
                    if(plateauAssocie.plateaudejeu[8].maison>=1 && plateauAssocie.plateaudejeu[8].maison<5) {
                        g.drawImage(TickMatiereGauche, plateauAssocie.plateaudejeu[8].coordX_premiertick, plateauAssocie.plateaudejeu[8].coordY_premiertick, 16, 16, this);
                    }
                    if(plateauAssocie.plateaudejeu[8].maison>=2 && plateauAssocie.plateaudejeu[8].maison<5) {
                        g.drawImage(TickMatiereGauche, plateauAssocie.plateaudejeu[8].coordX_premiertick, plateauAssocie.plateaudejeu[8].coordY_premiertick+16, 16, 16, this);
                    }
                    if(plateauAssocie.plateaudejeu[8].maison>=3 && plateauAssocie.plateaudejeu[8].maison<5) {
                        g.drawImage(TickMatiereGauche, plateauAssocie.plateaudejeu[8].coordX_premiertick, plateauAssocie.plateaudejeu[8].coordY_premiertick+32, 16, 16, this);
                    }
                    if(plateauAssocie.plateaudejeu[8].maison>=4 && plateauAssocie.plateaudejeu[8].maison<5) {
                        g.drawImage(TickMatiereGauche, plateauAssocie.plateaudejeu[8].coordX_premiertick, plateauAssocie.plateaudejeu[8].coordY_premiertick+48, 16, 16, this);
                    }
                    else if(plateauAssocie.plateaudejeu[8].maison==5) {
                        g.drawImage(TickModuleGauche, plateauAssocie.plateaudejeu[8].coordX_premiertick, plateauAssocie.plateaudejeu[8].coordY_premiertick+24, 16, 16, this);
                    }
                }
                if(plateauAssocie.plateaudejeu[9].maison!=0) {
                    if(plateauAssocie.plateaudejeu[9].maison>=1 && plateauAssocie.plateaudejeu[9].maison<5) {
                        g.drawImage(TickMatiereGauche, plateauAssocie.plateaudejeu[9].coordX_premiertick, plateauAssocie.plateaudejeu[9].coordY_premiertick, 16, 16, this);
                    }
                    if(plateauAssocie.plateaudejeu[9].maison>=2 && plateauAssocie.plateaudejeu[9].maison<5) {
                        g.drawImage(TickMatiereGauche, plateauAssocie.plateaudejeu[9].coordX_premiertick, plateauAssocie.plateaudejeu[9].coordY_premiertick+16, 16, 16, this);
                    }
                    if(plateauAssocie.plateaudejeu[9].maison>=3 && plateauAssocie.plateaudejeu[9].maison<5) {
                        g.drawImage(TickMatiereGauche, plateauAssocie.plateaudejeu[9].coordX_premiertick, plateauAssocie.plateaudejeu[9].coordY_premiertick+32, 16, 16, this);
                    }
                    if(plateauAssocie.plateaudejeu[9].maison>=4 && plateauAssocie.plateaudejeu[9].maison<5) {
                        g.drawImage(TickMatiereGauche, plateauAssocie.plateaudejeu[9].coordX_premiertick, plateauAssocie.plateaudejeu[9].coordY_premiertick+48, 16, 16, this);
                    }
                    else if(plateauAssocie.plateaudejeu[9].maison==5) {
                        g.drawImage(TickModuleGauche, plateauAssocie.plateaudejeu[9].coordX_premiertick, plateauAssocie.plateaudejeu[9].coordY_premiertick+24, 16, 16, this);
                    }
                }
                
                
                if(plateauAssocie.plateaudejeu[11].maison!=0) {
                    if(plateauAssocie.plateaudejeu[11].maison>=1 && plateauAssocie.plateaudejeu[11].maison<5) {
                        g.drawImage(TickMatiereHaut, plateauAssocie.plateaudejeu[11].coordX_premiertick, plateauAssocie.plateaudejeu[11].coordY_premiertick, 16, 16, this);
                    }
                    if(plateauAssocie.plateaudejeu[11].maison>=2 && plateauAssocie.plateaudejeu[11].maison<5) {
                        g.drawImage(TickMatiereHaut, plateauAssocie.plateaudejeu[11].coordX_premiertick-16, plateauAssocie.plateaudejeu[11].coordY_premiertick, 16, 16, this);
                    }
                    if(plateauAssocie.plateaudejeu[11].maison>=3 && plateauAssocie.plateaudejeu[11].maison<5) {
                        g.drawImage(TickMatiereHaut, plateauAssocie.plateaudejeu[11].coordX_premiertick-32, plateauAssocie.plateaudejeu[11].coordY_premiertick, 16, 16, this);
                    }
                    if(plateauAssocie.plateaudejeu[11].maison>=4 && plateauAssocie.plateaudejeu[11].maison<5) {
                        g.drawImage(TickMatiereHaut, plateauAssocie.plateaudejeu[11].coordX_premiertick-48, plateauAssocie.plateaudejeu[11].coordY_premiertick, 16, 16, this);
                    }
                    else if(plateauAssocie.plateaudejeu[11].maison==5) {
                        g.drawImage(TickModuleHaut, plateauAssocie.plateaudejeu[11].coordX_premiertick-24, plateauAssocie.plateaudejeu[11].coordY_premiertick, 16, 16, this);
                    }
                }
                if(plateauAssocie.plateaudejeu[13].maison!=0) {
                    if(plateauAssocie.plateaudejeu[13].maison>=1 && plateauAssocie.plateaudejeu[13].maison<5) {
                        g.drawImage(TickMatiereHaut, plateauAssocie.plateaudejeu[13].coordX_premiertick, plateauAssocie.plateaudejeu[13].coordY_premiertick, 16, 16, this);
                    }
                    if(plateauAssocie.plateaudejeu[13].maison>=2 && plateauAssocie.plateaudejeu[13].maison<5) {
                        g.drawImage(TickMatiereHaut, plateauAssocie.plateaudejeu[13].coordX_premiertick-16, plateauAssocie.plateaudejeu[13].coordY_premiertick, 16, 16, this);
                    }
                    if(plateauAssocie.plateaudejeu[13].maison>=3 && plateauAssocie.plateaudejeu[13].maison<5) {
                        g.drawImage(TickMatiereHaut, plateauAssocie.plateaudejeu[13].coordX_premiertick-32, plateauAssocie.plateaudejeu[13].coordY_premiertick, 16, 16, this);
                    }
                    if(plateauAssocie.plateaudejeu[13].maison>=4 && plateauAssocie.plateaudejeu[13].maison<5) {
                        g.drawImage(TickMatiereHaut, plateauAssocie.plateaudejeu[13].coordX_premiertick-48, plateauAssocie.plateaudejeu[13].coordY_premiertick, 16, 16, this);
                    }
                    else if(plateauAssocie.plateaudejeu[13].maison==5) {
                        g.drawImage(TickModuleHaut, plateauAssocie.plateaudejeu[13].coordX_premiertick-24, plateauAssocie.plateaudejeu[13].coordY_premiertick, 16, 16, this);
                    }
                }
                if(plateauAssocie.plateaudejeu[14].maison!=0) {
                    if(plateauAssocie.plateaudejeu[14].maison>=1 && plateauAssocie.plateaudejeu[14].maison<5) {
                        g.drawImage(TickMatiereHaut, plateauAssocie.plateaudejeu[14].coordX_premiertick, plateauAssocie.plateaudejeu[14].coordY_premiertick, 16, 16, this);
                    }
                    if(plateauAssocie.plateaudejeu[14].maison>=2 && plateauAssocie.plateaudejeu[14].maison<5) {
                        g.drawImage(TickMatiereHaut, plateauAssocie.plateaudejeu[14].coordX_premiertick-16, plateauAssocie.plateaudejeu[14].coordY_premiertick, 16, 16, this);
                    }
                    if(plateauAssocie.plateaudejeu[14].maison>=3 && plateauAssocie.plateaudejeu[14].maison<5) {
                        g.drawImage(TickMatiereHaut, plateauAssocie.plateaudejeu[14].coordX_premiertick-32, plateauAssocie.plateaudejeu[14].coordY_premiertick, 16, 16, this);
                    }
                    if(plateauAssocie.plateaudejeu[14].maison>=4 && plateauAssocie.plateaudejeu[14].maison<5) {
                        g.drawImage(TickMatiereHaut, plateauAssocie.plateaudejeu[14].coordX_premiertick-48, plateauAssocie.plateaudejeu[14].coordY_premiertick, 16, 16, this);
                    }
                    else if(plateauAssocie.plateaudejeu[14].maison==5) {
                        g.drawImage(TickModuleHaut, plateauAssocie.plateaudejeu[14].coordX_premiertick-24, plateauAssocie.plateaudejeu[14].coordY_premiertick, 16, 16, this);
                    }
                }
                if(plateauAssocie.plateaudejeu[16].maison!=0) {
                    if(plateauAssocie.plateaudejeu[16].maison>=1 && plateauAssocie.plateaudejeu[16].maison<5) {
                        g.drawImage(TickMatiereHaut, plateauAssocie.plateaudejeu[16].coordX_premiertick, plateauAssocie.plateaudejeu[16].coordY_premiertick, 16, 16, this);
                    }
                    if(plateauAssocie.plateaudejeu[16].maison>=2 && plateauAssocie.plateaudejeu[16].maison<5) {
                        g.drawImage(TickMatiereHaut, plateauAssocie.plateaudejeu[16].coordX_premiertick-16, plateauAssocie.plateaudejeu[16].coordY_premiertick, 16, 16, this);
                    }
                    if(plateauAssocie.plateaudejeu[16].maison>=3 && plateauAssocie.plateaudejeu[16].maison<5) {
                        g.drawImage(TickMatiereHaut, plateauAssocie.plateaudejeu[16].coordX_premiertick-32, plateauAssocie.plateaudejeu[16].coordY_premiertick, 16, 16, this);
                    }
                    if(plateauAssocie.plateaudejeu[16].maison>=4 && plateauAssocie.plateaudejeu[16].maison<5) {
                        g.drawImage(TickMatiereHaut, plateauAssocie.plateaudejeu[16].coordX_premiertick-48, plateauAssocie.plateaudejeu[16].coordY_premiertick, 16, 16, this);
                    }
                    else if(plateauAssocie.plateaudejeu[16].maison==5) {
                        g.drawImage(TickModuleHaut, plateauAssocie.plateaudejeu[16].coordX_premiertick-24, plateauAssocie.plateaudejeu[16].coordY_premiertick, 16, 16, this);
                    }
                }
                if(plateauAssocie.plateaudejeu[18].maison!=0) {
                    if(plateauAssocie.plateaudejeu[18].maison>=1 && plateauAssocie.plateaudejeu[18].maison<5) {
                        g.drawImage(TickMatiereHaut, plateauAssocie.plateaudejeu[18].coordX_premiertick, plateauAssocie.plateaudejeu[18].coordY_premiertick, 16, 16, this);
                    }
                    if(plateauAssocie.plateaudejeu[18].maison>=2 && plateauAssocie.plateaudejeu[18].maison<5) {
                        g.drawImage(TickMatiereHaut, plateauAssocie.plateaudejeu[18].coordX_premiertick-16, plateauAssocie.plateaudejeu[18].coordY_premiertick, 16, 16, this);
                    }
                    if(plateauAssocie.plateaudejeu[18].maison>=3 && plateauAssocie.plateaudejeu[18].maison<5) {
                        g.drawImage(TickMatiereHaut, plateauAssocie.plateaudejeu[18].coordX_premiertick-32, plateauAssocie.plateaudejeu[18].coordY_premiertick, 16, 16, this);
                    }
                    if(plateauAssocie.plateaudejeu[18].maison>=4 && plateauAssocie.plateaudejeu[18].maison<5) {
                        g.drawImage(TickMatiereHaut, plateauAssocie.plateaudejeu[18].coordX_premiertick-48, plateauAssocie.plateaudejeu[18].coordY_premiertick, 16, 16, this);
                    }
                    else if(plateauAssocie.plateaudejeu[18].maison==5) {
                        g.drawImage(TickModuleHaut, plateauAssocie.plateaudejeu[18].coordX_premiertick-24, plateauAssocie.plateaudejeu[18].coordY_premiertick, 16, 16, this);
                    }
                }
                if(plateauAssocie.plateaudejeu[19].maison!=0) {
                    if(plateauAssocie.plateaudejeu[19].maison>=1 && plateauAssocie.plateaudejeu[19].maison<5) {
                        g.drawImage(TickMatiereHaut, plateauAssocie.plateaudejeu[19].coordX_premiertick, plateauAssocie.plateaudejeu[19].coordY_premiertick, 16, 16, this);
                    }
                    if(plateauAssocie.plateaudejeu[19].maison>=2 && plateauAssocie.plateaudejeu[19].maison<5) {
                        g.drawImage(TickMatiereHaut, plateauAssocie.plateaudejeu[19].coordX_premiertick-16, plateauAssocie.plateaudejeu[19].coordY_premiertick, 16, 16, this);
                    }
                    if(plateauAssocie.plateaudejeu[19].maison>=3 && plateauAssocie.plateaudejeu[19].maison<5) {
                        g.drawImage(TickMatiereHaut, plateauAssocie.plateaudejeu[19].coordX_premiertick-32, plateauAssocie.plateaudejeu[19].coordY_premiertick, 16, 16, this);
                    }
                    if(plateauAssocie.plateaudejeu[19].maison>=4 && plateauAssocie.plateaudejeu[19].maison<5) {
                        g.drawImage(TickMatiereHaut, plateauAssocie.plateaudejeu[19].coordX_premiertick-48, plateauAssocie.plateaudejeu[19].coordY_premiertick, 16, 16, this);
                    }
                    else if(plateauAssocie.plateaudejeu[19].maison==5) {
                        g.drawImage(TickModuleHaut, plateauAssocie.plateaudejeu[19].coordX_premiertick-24, plateauAssocie.plateaudejeu[19].coordY_premiertick, 16, 16, this);
                    }
                }
                
                
                if(plateauAssocie.plateaudejeu[21].maison!=0) {
                    if(plateauAssocie.plateaudejeu[21].maison>=1 && plateauAssocie.plateaudejeu[21].maison<5) {
                        g.drawImage(TickMatiereDroite, plateauAssocie.plateaudejeu[21].coordX_premiertick, plateauAssocie.plateaudejeu[21].coordY_premiertick, 16, 16, this);
                    }
                    if(plateauAssocie.plateaudejeu[21].maison>=2 && plateauAssocie.plateaudejeu[21].maison<5) {
                        g.drawImage(TickMatiereDroite, plateauAssocie.plateaudejeu[21].coordX_premiertick, plateauAssocie.plateaudejeu[21].coordY_premiertick-16, 16, 16, this);
                    }
                    if(plateauAssocie.plateaudejeu[21].maison>=3 && plateauAssocie.plateaudejeu[21].maison<5) {
                        g.drawImage(TickMatiereDroite, plateauAssocie.plateaudejeu[21].coordX_premiertick, plateauAssocie.plateaudejeu[21].coordY_premiertick-32, 16, 16, this);
                    }
                    if(plateauAssocie.plateaudejeu[21].maison>=4 && plateauAssocie.plateaudejeu[21].maison<5) {
                        g.drawImage(TickMatiereDroite, plateauAssocie.plateaudejeu[21].coordX_premiertick, plateauAssocie.plateaudejeu[21].coordY_premiertick-48, 16, 16, this);
                    }
                    else if(plateauAssocie.plateaudejeu[21].maison==5) {
                        g.drawImage(TickModuleDroite, plateauAssocie.plateaudejeu[21].coordX_premiertick, plateauAssocie.plateaudejeu[21].coordY_premiertick-24, 16, 16, this);
                    }
                }
                if(plateauAssocie.plateaudejeu[23].maison!=0) {
                    if(plateauAssocie.plateaudejeu[23].maison>=1 && plateauAssocie.plateaudejeu[23].maison<5) {
                        g.drawImage(TickMatiereDroite, plateauAssocie.plateaudejeu[23].coordX_premiertick, plateauAssocie.plateaudejeu[23].coordY_premiertick, 16, 16, this);
                    }
                    if(plateauAssocie.plateaudejeu[23].maison>=2 && plateauAssocie.plateaudejeu[23].maison<5) {
                        g.drawImage(TickMatiereDroite, plateauAssocie.plateaudejeu[23].coordX_premiertick, plateauAssocie.plateaudejeu[23].coordY_premiertick-16, 16, 16, this);
                    }
                    if(plateauAssocie.plateaudejeu[23].maison>=3 && plateauAssocie.plateaudejeu[23].maison<5) {
                        g.drawImage(TickMatiereDroite, plateauAssocie.plateaudejeu[23].coordX_premiertick, plateauAssocie.plateaudejeu[23].coordY_premiertick-32, 16, 16, this);
                    }
                    if(plateauAssocie.plateaudejeu[23].maison>=4 && plateauAssocie.plateaudejeu[23].maison<5) {
                        g.drawImage(TickMatiereDroite, plateauAssocie.plateaudejeu[23].coordX_premiertick, plateauAssocie.plateaudejeu[23].coordY_premiertick-48, 16, 16, this);
                    }
                    else if(plateauAssocie.plateaudejeu[23].maison==5) {
                        g.drawImage(TickModuleDroite, plateauAssocie.plateaudejeu[23].coordX_premiertick, plateauAssocie.plateaudejeu[23].coordY_premiertick-24, 16, 16, this);
                    }
                }
                if(plateauAssocie.plateaudejeu[24].maison!=0) {
                    if(plateauAssocie.plateaudejeu[24].maison>=1 && plateauAssocie.plateaudejeu[24].maison<5) {
                        g.drawImage(TickMatiereDroite, plateauAssocie.plateaudejeu[24].coordX_premiertick, plateauAssocie.plateaudejeu[24].coordY_premiertick, 16, 16, this);
                    }
                    if(plateauAssocie.plateaudejeu[24].maison>=2 && plateauAssocie.plateaudejeu[24].maison<5) {
                        g.drawImage(TickMatiereDroite, plateauAssocie.plateaudejeu[24].coordX_premiertick, plateauAssocie.plateaudejeu[24].coordY_premiertick-16, 16, 16, this);
                    }
                    if(plateauAssocie.plateaudejeu[24].maison>=3 && plateauAssocie.plateaudejeu[24].maison<5) {
                        g.drawImage(TickMatiereDroite, plateauAssocie.plateaudejeu[24].coordX_premiertick, plateauAssocie.plateaudejeu[24].coordY_premiertick-32, 16, 16, this);
                    }
                    if(plateauAssocie.plateaudejeu[24].maison>=4 && plateauAssocie.plateaudejeu[24].maison<5) {
                        g.drawImage(TickMatiereDroite, plateauAssocie.plateaudejeu[24].coordX_premiertick, plateauAssocie.plateaudejeu[24].coordY_premiertick-48, 16, 16, this);
                    }
                    else if(plateauAssocie.plateaudejeu[24].maison==5) {
                        g.drawImage(TickModuleDroite, plateauAssocie.plateaudejeu[24].coordX_premiertick, plateauAssocie.plateaudejeu[24].coordY_premiertick-24, 16, 16, this);
                    }
                }
                if(plateauAssocie.plateaudejeu[26].maison!=0) {
                    if(plateauAssocie.plateaudejeu[26].maison>=1 && plateauAssocie.plateaudejeu[26].maison<5) {
                        g.drawImage(TickMatiereDroite, plateauAssocie.plateaudejeu[26].coordX_premiertick, plateauAssocie.plateaudejeu[26].coordY_premiertick, 16, 16, this);
                    }
                    if(plateauAssocie.plateaudejeu[26].maison>=2 && plateauAssocie.plateaudejeu[26].maison<5) {
                        g.drawImage(TickMatiereDroite, plateauAssocie.plateaudejeu[26].coordX_premiertick, plateauAssocie.plateaudejeu[26].coordY_premiertick-16, 16, 16, this);
                    }
                    if(plateauAssocie.plateaudejeu[26].maison>=3 && plateauAssocie.plateaudejeu[26].maison<5) {
                        g.drawImage(TickMatiereDroite, plateauAssocie.plateaudejeu[26].coordX_premiertick, plateauAssocie.plateaudejeu[26].coordY_premiertick-32, 16, 16, this);
                    }
                    if(plateauAssocie.plateaudejeu[26].maison>=4 && plateauAssocie.plateaudejeu[26].maison<5) {
                        g.drawImage(TickMatiereDroite, plateauAssocie.plateaudejeu[26].coordX_premiertick, plateauAssocie.plateaudejeu[26].coordY_premiertick-48, 16, 16, this);
                    }
                    else if(plateauAssocie.plateaudejeu[26].maison==5) {
                        g.drawImage(TickModuleDroite, plateauAssocie.plateaudejeu[26].coordX_premiertick, plateauAssocie.plateaudejeu[26].coordY_premiertick-24, 16, 16, this);
                    }
                }
                if(plateauAssocie.plateaudejeu[27].maison!=0) {
                    if(plateauAssocie.plateaudejeu[27].maison>=1 && plateauAssocie.plateaudejeu[27].maison<5) {
                        g.drawImage(TickMatiereDroite, plateauAssocie.plateaudejeu[27].coordX_premiertick, plateauAssocie.plateaudejeu[27].coordY_premiertick, 16, 16, this);
                    }
                    if(plateauAssocie.plateaudejeu[27].maison>=2 && plateauAssocie.plateaudejeu[27].maison<5) {
                        g.drawImage(TickMatiereDroite, plateauAssocie.plateaudejeu[27].coordX_premiertick, plateauAssocie.plateaudejeu[27].coordY_premiertick-16, 16, 16, this);
                    }
                    if(plateauAssocie.plateaudejeu[27].maison>=3 && plateauAssocie.plateaudejeu[27].maison<5) {
                        g.drawImage(TickMatiereDroite, plateauAssocie.plateaudejeu[27].coordX_premiertick, plateauAssocie.plateaudejeu[27].coordY_premiertick-32, 16, 16, this);
                    }
                    if(plateauAssocie.plateaudejeu[27].maison>=4 && plateauAssocie.plateaudejeu[27].maison<5) {
                        g.drawImage(TickMatiereDroite, plateauAssocie.plateaudejeu[27].coordX_premiertick, plateauAssocie.plateaudejeu[27].coordY_premiertick-48, 16, 16, this);
                    }
                    else if(plateauAssocie.plateaudejeu[27].maison==5) {
                        g.drawImage(TickModuleDroite, plateauAssocie.plateaudejeu[27].coordX_premiertick, plateauAssocie.plateaudejeu[27].coordY_premiertick-24, 16, 16, this);
                    }
                }
                if(plateauAssocie.plateaudejeu[29].maison!=0) {
                    if(plateauAssocie.plateaudejeu[29].maison>=1 && plateauAssocie.plateaudejeu[29].maison<5) {
                        g.drawImage(TickMatiereDroite, plateauAssocie.plateaudejeu[29].coordX_premiertick, plateauAssocie.plateaudejeu[29].coordY_premiertick, 16, 16, this);
                    }
                    if(plateauAssocie.plateaudejeu[29].maison>=2 && plateauAssocie.plateaudejeu[29].maison<5) {
                        g.drawImage(TickMatiereDroite, plateauAssocie.plateaudejeu[29].coordX_premiertick, plateauAssocie.plateaudejeu[29].coordY_premiertick-16, 16, 16, this);
                    }
                    if(plateauAssocie.plateaudejeu[29].maison>=3 && plateauAssocie.plateaudejeu[29].maison<5) {
                        g.drawImage(TickMatiereDroite, plateauAssocie.plateaudejeu[29].coordX_premiertick, plateauAssocie.plateaudejeu[29].coordY_premiertick-32, 16, 16, this);
                    }
                    if(plateauAssocie.plateaudejeu[29].maison>=4 && plateauAssocie.plateaudejeu[29].maison<5) {
                        g.drawImage(TickMatiereDroite, plateauAssocie.plateaudejeu[29].coordX_premiertick, plateauAssocie.plateaudejeu[29].coordY_premiertick-48, 16, 16, this);
                    }
                    else if(plateauAssocie.plateaudejeu[29].maison==5) {
                        g.drawImage(TickModuleDroite, plateauAssocie.plateaudejeu[29].coordX_premiertick, plateauAssocie.plateaudejeu[29].coordY_premiertick-24, 16, 16, this);
                    }
                }
                
                
                if(plateauAssocie.plateaudejeu[31].maison!=0) {
                    if(plateauAssocie.plateaudejeu[31].maison>=1 && plateauAssocie.plateaudejeu[31].maison<5) {
                        g.drawImage(TickMatiereBas, plateauAssocie.plateaudejeu[31].coordX_premiertick, plateauAssocie.plateaudejeu[31].coordY_premiertick, 16, 16, this);
                    }
                    if(plateauAssocie.plateaudejeu[31].maison>=2 && plateauAssocie.plateaudejeu[31].maison<5) {
                        g.drawImage(TickMatiereBas, plateauAssocie.plateaudejeu[31].coordX_premiertick+16, plateauAssocie.plateaudejeu[31].coordY_premiertick, 16, 16, this);
                    }
                    if(plateauAssocie.plateaudejeu[31].maison>=3 && plateauAssocie.plateaudejeu[31].maison<5) {
                        g.drawImage(TickMatiereBas, plateauAssocie.plateaudejeu[31].coordX_premiertick+32, plateauAssocie.plateaudejeu[31].coordY_premiertick, 16, 16, this);
                    }
                    if(plateauAssocie.plateaudejeu[31].maison>=4 && plateauAssocie.plateaudejeu[31].maison<5) {
                        g.drawImage(TickMatiereBas, plateauAssocie.plateaudejeu[31].coordX_premiertick+48, plateauAssocie.plateaudejeu[31].coordY_premiertick, 16, 16, this);
                    }
                    else if(plateauAssocie.plateaudejeu[31].maison==5) {
                        g.drawImage(TickModuleBas, plateauAssocie.plateaudejeu[31].coordX_premiertick+24, plateauAssocie.plateaudejeu[31].coordY_premiertick, 16, 16, this);
                    }
                }
                if(plateauAssocie.plateaudejeu[32].maison!=0) {
                    if(plateauAssocie.plateaudejeu[32].maison>=1 && plateauAssocie.plateaudejeu[32].maison<5) {
                        g.drawImage(TickMatiereBas, plateauAssocie.plateaudejeu[32].coordX_premiertick, plateauAssocie.plateaudejeu[32].coordY_premiertick, 16, 16, this);
                    }
                    if(plateauAssocie.plateaudejeu[32].maison>=2 && plateauAssocie.plateaudejeu[32].maison<5) {
                        g.drawImage(TickMatiereBas, plateauAssocie.plateaudejeu[32].coordX_premiertick+16, plateauAssocie.plateaudejeu[32].coordY_premiertick, 16, 16, this);
                    }
                    if(plateauAssocie.plateaudejeu[32].maison>=3 && plateauAssocie.plateaudejeu[32].maison<5) {
                        g.drawImage(TickMatiereBas, plateauAssocie.plateaudejeu[32].coordX_premiertick+32, plateauAssocie.plateaudejeu[32].coordY_premiertick, 16, 16, this);
                    }
                    if(plateauAssocie.plateaudejeu[32].maison>=4 && plateauAssocie.plateaudejeu[32].maison<5) {
                        g.drawImage(TickMatiereBas, plateauAssocie.plateaudejeu[32].coordX_premiertick+48, plateauAssocie.plateaudejeu[32].coordY_premiertick, 16, 16, this);
                    }
                    else if(plateauAssocie.plateaudejeu[32].maison==5) {
                        g.drawImage(TickModuleBas, plateauAssocie.plateaudejeu[32].coordX_premiertick+24, plateauAssocie.plateaudejeu[32].coordY_premiertick, 16, 16, this);
                    }
                }
                if(plateauAssocie.plateaudejeu[34].maison!=0) {
                    if(plateauAssocie.plateaudejeu[34].maison>=1 && plateauAssocie.plateaudejeu[34].maison<5) {
                        g.drawImage(TickMatiereBas, plateauAssocie.plateaudejeu[34].coordX_premiertick, plateauAssocie.plateaudejeu[34].coordY_premiertick, 16, 16, this);
                    }
                    if(plateauAssocie.plateaudejeu[34].maison>=2 && plateauAssocie.plateaudejeu[34].maison<5) {
                        g.drawImage(TickMatiereBas, plateauAssocie.plateaudejeu[34].coordX_premiertick+16, plateauAssocie.plateaudejeu[34].coordY_premiertick, 16, 16, this);
                    }
                    if(plateauAssocie.plateaudejeu[34].maison>=3 && plateauAssocie.plateaudejeu[34].maison<5) {
                        g.drawImage(TickMatiereBas, plateauAssocie.plateaudejeu[34].coordX_premiertick+32, plateauAssocie.plateaudejeu[34].coordY_premiertick, 16, 16, this);
                    }
                    if(plateauAssocie.plateaudejeu[34].maison>=4 && plateauAssocie.plateaudejeu[34].maison<5) {
                        g.drawImage(TickMatiereBas, plateauAssocie.plateaudejeu[34].coordX_premiertick+48, plateauAssocie.plateaudejeu[34].coordY_premiertick, 16, 16, this);
                    }
                    else if(plateauAssocie.plateaudejeu[34].maison==5) {
                        g.drawImage(TickModuleBas, plateauAssocie.plateaudejeu[34].coordX_premiertick+24, plateauAssocie.plateaudejeu[34].coordY_premiertick, 16, 16, this);
                    }
                }
                if(plateauAssocie.plateaudejeu[37].maison!=0) {
                    if(plateauAssocie.plateaudejeu[37].maison>=1 && plateauAssocie.plateaudejeu[37].maison<5) {
                        g.drawImage(TickMatiereBas, plateauAssocie.plateaudejeu[37].coordX_premiertick, plateauAssocie.plateaudejeu[37].coordY_premiertick, 16, 16, this);
                    }
                    if(plateauAssocie.plateaudejeu[37].maison>=2 && plateauAssocie.plateaudejeu[37].maison<5) {
                        g.drawImage(TickMatiereBas, plateauAssocie.plateaudejeu[37].coordX_premiertick+16, plateauAssocie.plateaudejeu[37].coordY_premiertick, 16, 16, this);
                    }
                    if(plateauAssocie.plateaudejeu[37].maison>=3 && plateauAssocie.plateaudejeu[37].maison<5) {
                        g.drawImage(TickMatiereBas, plateauAssocie.plateaudejeu[37].coordX_premiertick+32, plateauAssocie.plateaudejeu[37].coordY_premiertick, 16, 16, this);
                    }
                    if(plateauAssocie.plateaudejeu[37].maison>=4 && plateauAssocie.plateaudejeu[37].maison<5) {
                        g.drawImage(TickMatiereBas, plateauAssocie.plateaudejeu[37].coordX_premiertick+48, plateauAssocie.plateaudejeu[37].coordY_premiertick, 16, 16, this);
                    }
                    else if(plateauAssocie.plateaudejeu[37].maison==5) {
                        g.drawImage(TickModuleBas, plateauAssocie.plateaudejeu[37].coordX_premiertick+24, plateauAssocie.plateaudejeu[37].coordY_premiertick, 16, 16, this);
                    }
                }
                if(plateauAssocie.plateaudejeu[39].maison!=0) {
                    if(plateauAssocie.plateaudejeu[39].maison>=1 && plateauAssocie.plateaudejeu[39].maison<5) {
                        g.drawImage(TickMatiereBas, plateauAssocie.plateaudejeu[39].coordX_premiertick, plateauAssocie.plateaudejeu[39].coordY_premiertick, 16, 16, this);
                    }
                    if(plateauAssocie.plateaudejeu[39].maison>=2 && plateauAssocie.plateaudejeu[39].maison<5) {
                        g.drawImage(TickMatiereBas, plateauAssocie.plateaudejeu[39].coordX_premiertick+16, plateauAssocie.plateaudejeu[39].coordY_premiertick, 16, 16, this);
                    }
                    if(plateauAssocie.plateaudejeu[39].maison>=3 && plateauAssocie.plateaudejeu[39].maison<5) {
                        g.drawImage(TickMatiereBas, plateauAssocie.plateaudejeu[39].coordX_premiertick+32, plateauAssocie.plateaudejeu[39].coordY_premiertick, 16, 16, this);
                    }
                    if(plateauAssocie.plateaudejeu[39].maison>=4 && plateauAssocie.plateaudejeu[39].maison<5) {
                        g.drawImage(TickMatiereBas, plateauAssocie.plateaudejeu[39].coordX_premiertick+48, plateauAssocie.plateaudejeu[39].coordY_premiertick, 16, 16, this);
                    }
                    else if(plateauAssocie.plateaudejeu[39].maison==5) {
                        g.drawImage(TickModuleBas, plateauAssocie.plateaudejeu[39].coordX_premiertick+24, plateauAssocie.plateaudejeu[39].coordY_premiertick, 16, 16, this);
                    }
                }
            }
          
        } catch(IOException e){
            e.printStackTrace();
        }
        
        //g.fillOval(0,0,700,700); dessine un oval sur le plateau
             }
    
    }
    

