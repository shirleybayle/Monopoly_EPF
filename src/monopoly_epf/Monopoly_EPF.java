/*
 * BOCAHUT Manon - FEVRIER Titouan - CHAUVET Ylan - BAYLE Shirley
 */
package monopoly_epf;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author shirl
 */
public class Monopoly_EPF extends JFrame {

    /**
     * @param args the command line arguments
     */
    
    Plateau plateau;
    Joueur joueurCourant;
    Paquet paquetChance;
    Paquet paquetCommunaute;
    Joueur [] tabJoueurs = new Joueur[4];
    Des de1;
    Des de2;
    int compteurDouble;
    int argentParcGratuit;
    
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
        
        titre = new JLabel("Monopoly EPF");
        Dimension sizet = titre.getPreferredSize();
        titre.setFont(new Font("Monopoly", Font.BOLD, 50));
        add(titre, new org.netbeans.lib.awtextra.AbsoluteConstraints(30,10));
        this.repaint();
        
        infos_joueurs1 = new JPanel();
        Dimension sizej1 = infos_joueurs1.setSize(300,500);
        
        add(infos_joueurs1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10,50));
    }
    
    PlateauPanel plateauJeu;
    private JLabel titre;
    private JPanel infos_joueurs1;
    
    public static void main(String[] args) {
        JFrame frame = new Monopoly_EPF();
    }

    
    //-----------------------------------
     public void tourJoueur() {
        if (paquetChance.paquetVide() == true) { //au début du tour on vérifie qu'il reste des carte dans les paquets et si non on les réinitialise
            paquetChance.MAJtab();
        }
        if (paquetCommunaute.paquetVide() == true) {
            paquetCommunaute.MAJtab();
        }
        //prévoir le fait qu'on puisse acheter des maisons au début du tour
        if (joueurCourant.prison == true) {
            //LE JOUEUR EST EN PRISON --> double ?
            lancerDes();
            boolean testDouble = lireDouble(); //gérer la carte sortie de prison
            if (testDouble == true) {
                joueurCourant.prison = false;
                joueurCourant.compteurTourPrison=0; //réinitialisation du nombre de tours en prison pour la prochaine fois
                lancerDes();
                deplacerPion(joueurCourant.pion, de1.valeur+de2.valeur, joueurCourant.pion.caseassociee);
                faireActionCase();  
            }
            else {
                joueurCourant.compteurTourPrison++;
                if(joueurCourant.compteurTourPrison == 3) {
                    joueurCourant.credits = joueurCourant.credits-50;
                    joueurCourant.prison = false;
                    joueurCourant.compteurTourPrison = 0;
                }
            }
        }
        else {
            for(int i=0;i<3;i++) {
                lancerDes();
                boolean testDouble = lireDouble();
                if(testDouble == true) {
                    compteurDouble++;
                    if(compteurDouble==3) {
                        joueurCourant.prison = true;
                        i=3;
                    }
                    else {
                        deplacerPion(joueurCourant.pion, de1.valeur+de2.valeur, joueurCourant.pion.caseassociee);
                        faireActionCase();
                    }
                }
                else {
                    deplacerPion(joueurCourant.pion, de1.valeur+de2.valeur, joueurCourant.pion.caseassociee);
                    faireActionCase();
                    i=3;
                }
            }
        }
        if(joueurCourant.credits<0) { // à la fin du tour on regarde si le joueur a toujours des credits, si non il est éliminé
            eliminationJoueur(); 
        }
        changerJoueur();
    }
    
   
}
