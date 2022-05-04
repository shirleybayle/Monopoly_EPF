/*
 * BOCAHUT Manon - FEVRIER Titouan - CHAUVET Ylan - BAYLE Shirley
 */
package monopoly_epf;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

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
        
        InitialiserPartie();
    }
    
    public void initComponent() {
        this.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        plateauJeu = new PlateauPanel();
        plateauJeu.setBackground(Color.red);
        add(plateauJeu, new org.netbeans.lib.awtextra.AbsoluteConstraints(367,0,-1,-1));
        plateauJeu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        plateauJeu.setSize(800,798);
        //plateauJeu.setBackground(Color.red);
        plateauJeu.setPreferredSize(new Dimension(800,798));
        setVisible(true);
        
        titre = new JLabel("Monopoly EPF");
        Dimension sizet = titre.getPreferredSize();
        titre.setFont(new Font("Monopoly", Font.BOLD, 50));
        add(titre, new org.netbeans.lib.awtextra.AbsoluteConstraints(30,8));
        this.repaint();
        
        infos_joueurs1 = new JPanel();
        infos_joueurs1.setSize(355,200);
        infos_joueurs1.setBackground(Color.red);
        infos_joueurs1.setBorder(BorderFactory.createMatteBorder(2,2,2,2, Color.BLACK));
        add(infos_joueurs1, new org.netbeans.lib.awtextra.AbsoluteConstraints(5,200,355,200));
        infos_joueurs1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        this.repaint();
        
        infos_joueurs2 = new JPanel();
        infos_joueurs2.setSize(355,200);
        infos_joueurs2.setBackground(Color.red);
        infos_joueurs2.setBorder(BorderFactory.createMatteBorder(2,2,2,2, Color.BLACK));
        add(infos_joueurs2, new org.netbeans.lib.awtextra.AbsoluteConstraints(5,460,355,200));
        infos_joueurs2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        this.repaint();
        
        infos_joueurs3 = new JPanel();
        infos_joueurs3.setSize(355,200);
        infos_joueurs3.setBackground(Color.red);
        infos_joueurs3.setBorder(BorderFactory.createMatteBorder(2,2,2,2, Color.BLACK));
        add(infos_joueurs3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1174,200,355,200));
        infos_joueurs3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        this.repaint();
        
        infos_joueurs4 = new JPanel();
        infos_joueurs4.setSize(355,200);
        infos_joueurs4.setBackground(Color.red);
        infos_joueurs4.setBorder(BorderFactory.createMatteBorder(2,2,2,2, Color.BLACK));
        add(infos_joueurs4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1174,460,355,200));
        infos_joueurs4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        this.repaint();
        
        panel_boutons = new JPanel();
        panel_boutons.setSize(355,140);
        panel_boutons.setBackground(Color.LIGHT_GRAY);
        panel_boutons.setBorder(BorderFactory.createMatteBorder(2,2,2,2, Color.BLACK));
        add(panel_boutons, new org.netbeans.lib.awtextra.AbsoluteConstraints(5,58,355,135));
        panel_boutons.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        this.repaint();
        
        infos_partie = new JPanel();
        infos_partie.setSize(355,185);
        infos_partie.setBackground(Color.WHITE);
        infos_partie.setBorder(BorderFactory.createMatteBorder(2,2,2,2, Color.BLACK));
        add(infos_partie, new org.netbeans.lib.awtextra.AbsoluteConstraints(1174,8,355,185));
        infos_partie.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        this.repaint();
        
        Regles = new JButton();
        Regles.setBounds(5, 5, 170, 60);
        Regles.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
        panel_boutons.add(Regles, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 5, 170, 60));
        Regles.setBackground(Color.BLUE);
        Regles.setText("Règles");
        Regles.setFont(new Font("Règles", Font.BOLD, 16));
        this.repaint();
        Regles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JFrame FrameRegles = new JFrame();
                FrameRegles.setBounds(325, 0, 900, 900);
                FrameRegles.setVisible(true);
                FrameRegles.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
                FrameRegles.add(Regles_pane,new org.netbeans.lib.awtextra.AbsoluteConstraints(0,30,-1,-1));
                FrameRegles.add(Regles_LabelTitre,new org.netbeans.lib.awtextra.AbsoluteConstraints(0,0,-1,-1));
                
            }
        });
        
        
        Regles_LabelTitre = new JLabel("Regles du jeu");
        Regles_LabelTitre.setFont(new Font("Monopoly", Font.PLAIN, 20));
        
        
        Regles_Text = new JTextArea();
        Regles_Text.setColumns(72);
        Regles_Text.setRows(53);
        Regles_Text.setText(
"Nombre de joueurs : 4 Duree : 1h à 2h30\n" +
"- Ce jeu consiste a ACHETER, a LOUER ou a VENDRE diverses salles de façon si profitable que l’on puisse devenir le plus riche des joueurs et éventuellement, le GAGNANT. Des qu’un joueur n’a plus de credit il est elimine du jeu et ses propriétés sont de nouveau disponible. Le gagnant est le dernier encore debout.\n" +
"\n" +
"- « Début d’année » est le point de départ, et l’on fait avancer les pions sur le plateau de jeu d’après le chiffre indiqué en lançant les dés.\n" +
"\n" +
"- Si le pion d’un joueur se place sur une case qui n’appartient encore à personne, le joueur pourra l’acheter.\n" +
"\n" +
"- Devenir propriétaire a pour OBJECTIF la perception de loyers des adversaires logés sur la case qui représente la propriété. Ces loyers sont fortement augmentés par l’obtention de matières et de modules (voir emplacements pour les montants)\n" +
"\n" +
"Début de partie :\n" +
"\n" +
"- Chaque joueur reçoit 1500 crédits\n" +
"\n" +
"- Les joueurs jouent un par un dans le sens des aiguilles d’une montre en commençant par lancer le dé\n" +
"\n" +
"- Le pion du joueur avance, dans le sens horaire, d’autant de cases que l’aura indiqué le nombre total des dés.\n" +
"\n" +
"- Les PIONS restent sur les cases qu’ils occupent et repartent au tour suivant.\n" +
"\n" +
"- Il peut y avoir, en même temps, plus d’un pion sur la même case.\n" +
"\n" +
"- Si une case est atteinte par son pion, un joueur peut avoir le droit d’acheter la salle ou être forcé de payer un loyer (si la propriété appartient à un autre), de payer des taxes, de prendre une carte des paquets « Chance » ou « Caisse Commune, » d’aller aux rattrapages, etc.\n" +
"\n" +
"- Si un joueur obtient un doublet, il avance son pion, comme d’habitude, du total représenté par les deux dés, et bénéficie de tous les privilèges ou doit subir toutes les pénalités relatives à la case ainsi atteinte. Il bénéficie d’un second lancé, avance son pion comme précédemment et la case ainsi atteinte le soumet de nouveau à tous ses effets.\n" +
"\n" +
"- Si, cependant, il obtient un doublet trois fois de suite, il devra immédiatement ALLER AUX RATTRAPAGES.\n" +
"\n" +
"- Les joueurs, durant la partie, feront plusieurs fois le tour du plateau de jeu. Chaque fois que le pion s’arrête sur la case Début D’année il reçoit 400 crédits s’il la dépasse il recevra 200 crédits.\n" +
"\n" +
"- Il est avantageux de détenir des titres de propriété sur la TOTALITÉ d’un groupe complet de même couleur car le loyer s’en voit augmenté\n" +
"\n" +
"- Si le joueur tombe sur les cartes chances ou communautés, il applique ce qui est écrit sur la carte. Si c’est une carte libérée de Rattrapages, il garde la carte et pourra l’utiliser ou non s’il est envoyé en prison.\n" +
"\n" +
"- QUAND LE PION ATTEINT UNE CASE Taxe, il faut payer le montant indiqué\n" +
"\n" +
"- RATTRAPAGES : Un Joueur y Est Envoyé :\n" +
"\n" +
"1) Si son pion atteint la case « ALLEZ AUX RATTRAPAGES » (Il ne touche pas la bourse de la case début d’année)\n" +
"\n" +
"2) S’il tire une carte marquée « ALLEZ AUX RATTRAPAGES »\n" +
"\n" +
"3) S’il obtient trois doublets de suite.\n" +
"\n" +
"Aux rattrapages, il y reste bloqué 3 tours. Il a 1 chance par tour de faire un double et de sortir et au 3ème tour il est obligé de payer 50 crédits et de jouer\n" +
"\n" +
"- S’il tombe sur la carte Majoration il récupère les crédits des taxes accumulés\n" +
"\n" +
"- Les Matières ne peuvent être obtenues et validées que sur des terrains d’un GROUPE COMPLET DE MÊME COULEUR appartenant au joueur.\n" +
"\n" +
"- Les Modules ne peuvent être validés que si TOUTES LES MATIERES d’un emplacement ont été validées\n" +
"\n" +
"- Pour plus de détails sur les cartes, allez voir la rubrique emplacements ou découvrez-les en partie.\n" +
"\n" +
"- Si une règle du jeu vous semble floue ou si vous ne comprenez pas certains éléments, n’hésitez pas à demander de l’aide à des joueurs plus expérimentés.");
        
        Regles_Text.setEditable(false);
        Regles_Text.setFont(new Font("Monopoly", Font.PLAIN, 15));
        
        Regles_pane = new JScrollPane();
        Regles_pane.setViewportView(Regles_Text);
        
        pseudo1 = new JLabel("Pseudo:");
        Dimension pseudo1_size = pseudo1.getPreferredSize();
        pseudo1.setFont(new Font("Pseudo:", Font.BOLD, 15));
        infos_joueurs1.add(pseudo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(5,30));
        this.repaint();
        
        pseudo2 = new JLabel("Pseudo:");
        Dimension pseudo2_size = pseudo2.getPreferredSize();
        pseudo2.setFont(new Font("Pseudo:", Font.BOLD, 15));
        infos_joueurs2.add(pseudo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(5,30));
        this.repaint();
        
        pseudo3 = new JLabel("Pseudo:");
        Dimension pseudo3_size = pseudo3.getPreferredSize();
        pseudo3.setFont(new Font("Pseudo:", Font.BOLD, 15));
        infos_joueurs3.add(pseudo3, new org.netbeans.lib.awtextra.AbsoluteConstraints(5,30));
        this.repaint();
        
        pseudo4 = new JLabel("Pseudo:");
        Dimension pseudo4_size = pseudo4.getPreferredSize();
        pseudo4.setFont(new Font("Pseudo:", Font.BOLD, 15));
        infos_joueurs4.add(pseudo4, new org.netbeans.lib.awtextra.AbsoluteConstraints(5,30));
        this.repaint();
        
        JTextArea zone_texte_infos = new JTextArea();
        zone_texte_infos.setBounds(10, 25, 335, 155);
        infos_partie.add(zone_texte_infos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10,25,335,155));
        zone_texte_infos.setEditable(false);
        zone_texte_infos.setText("Veuillez rentrer les quatre pseudos puis cliquer sur le bouton\ndémarrer la partie !\nAttention, si vous ne rentrez rien, les joueurs\nn'auront pas de pseudo.");
        this.repaint();
        
        JTextField nomJ1 = new JTextField();
        nomJ1.setBounds(70, 30, 110, 20);
        infos_joueurs1.add(nomJ1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70,30,110,20));
        this.repaint();
        
        JTextField nomJ2 = new JTextField();
        nomJ2.setBounds(70, 30, 110, 20);
        infos_joueurs2.add(nomJ2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70,30,110,20));
        this.repaint();
        
        JTextField nomJ3 = new JTextField();
        nomJ3.setBounds(70, 30, 110, 20);
        infos_joueurs3.add(nomJ3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70,30,110,20));
        this.repaint();
        
        JTextField nomJ4 = new JTextField();
        nomJ4.setBounds(70, 30, 110, 20);
        infos_joueurs4.add(nomJ4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70,30,110,20));
        this.repaint();
        
        Démarrer = new JButton();
        Démarrer.setBounds(5, 70, 170, 60);
        Démarrer.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
        panel_boutons.add(Démarrer, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 70, 170, 60));
        Démarrer.setBackground(Color.BLUE);
        Démarrer.setText("Démarrer la partie");
        Démarrer.setFont(new Font("Démarrer la partie", Font.BOLD, 16));
        Démarrer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Démarrer.setEnabled(false);
                tabJoueurs[0].nom = nomJ1.getText();
                nomJ1.setVisible(false);
                Joueur1.setText(tabJoueurs[0].nom);
                pseudo1.setVisible(false);
                tabJoueurs[1].nom = nomJ2.getText();
                nomJ2.setVisible(false);
                Joueur2.setText(tabJoueurs[1].nom);
                pseudo2.setVisible(false);
                tabJoueurs[2].nom = nomJ3.getText();
                nomJ3.setVisible(false);
                Joueur3.setText(tabJoueurs[2].nom);
                pseudo3.setVisible(false);
                tabJoueurs[3].nom = nomJ4.getText();
                nomJ4.setVisible(false);
                Joueur4.setText(tabJoueurs[3].nom);
                pseudo4.setVisible(false);
                zone_texte_infos.setText("\nJoueur 1, votre pseudo est " + tabJoueurs[0].nom + "!" + "\nJoueur 2, votre pseudo est " + tabJoueurs[1].nom + "!" + "\nJoueur 3, votre pseudo est " + tabJoueurs[2].nom + "!" + "\nJoueur 4, votre pseudo est " + tabJoueurs[3].nom + "!");
                credits1.setVisible(true);
                credits2.setVisible(true);
                credits3.setVisible(true);
                credits4.setVisible(true);
                marron1.setVisible(true);
                bleuciel1.setVisible(true);
                violet1.setVisible(true);
                orange1.setVisible(true);
                rouge1.setVisible(true);
                jaune1.setVisible(true);
                vert1.setVisible(true);
                bleufoncé1.setVisible(true);
                marron2.setVisible(true);
                bleuciel2.setVisible(true);
                violet2.setVisible(true);
                orange2.setVisible(true);
                rouge2.setVisible(true);
                jaune2.setVisible(true);
                vert2.setVisible(true);
                bleufoncé2.setVisible(true);
                marron3.setVisible(true);
                bleuciel3.setVisible(true);
                violet3.setVisible(true);
                orange3.setVisible(true);
                rouge3.setVisible(true);
                jaune3.setVisible(true);
                vert3.setVisible(true);
                bleufoncé3.setVisible(true);
                marron4.setVisible(true);
                bleuciel4.setVisible(true);
                violet4.setVisible(true);
                orange4.setVisible(true);
                rouge4.setVisible(true);
                jaune4.setVisible(true);
                vert4.setVisible(true);
                bleufoncé4.setVisible(true);
                SallesPossedees1.setVisible(true);
                SallesPossedees2.setVisible(true);
                SallesPossedees3.setVisible(true);
                SallesPossedees4.setVisible(true);
                Autres1.setVisible(true);
                Autres2.setVisible(true);
                Autres3.setVisible(true);
                Autres4.setVisible(true);
                Lancer_des.setEnabled(true);
            }
        });
        this.repaint();
        
        JLabel dé1 = new JLabel(new ImageIcon("dé1.png"));
        plateauJeu.add(dé1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 120, 55, 55));
        this.repaint();
        dé1.setVisible(false);
        JLabel dé1V2 = new JLabel(new ImageIcon("dé1.png"));
        plateauJeu.add(dé1V2, new org.netbeans.lib.awtextra.AbsoluteConstraints(195, 120, 55, 55));
        this.repaint();
        dé1V2.setVisible(false);
        JLabel dé2 = new JLabel(new ImageIcon("dé2.png"));
        plateauJeu.add(dé2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 120, 55, 55));
        this.repaint();
        dé2.setVisible(false);
        JLabel dé2V2 = new JLabel(new ImageIcon("dé2.png"));
        plateauJeu.add(dé2V2, new org.netbeans.lib.awtextra.AbsoluteConstraints(195, 120, 55, 55));
        this.repaint();
        dé2V2.setVisible(false);
        JLabel dé3 = new JLabel(new ImageIcon("dé3.png"));
        plateauJeu.add(dé3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 120, 55, 55));
        this.repaint();
        dé3.setVisible(false);
        JLabel dé3V2 = new JLabel(new ImageIcon("dé3.png"));
        plateauJeu.add(dé3V2, new org.netbeans.lib.awtextra.AbsoluteConstraints(195, 120, 55, 55));
        this.repaint();
        dé3V2.setVisible(false);
        JLabel dé4 = new JLabel(new ImageIcon("dé4.png"));
        plateauJeu.add(dé4, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 120, 55, 55));
        this.repaint();
        dé4.setVisible(false);
        JLabel dé4V2 = new JLabel(new ImageIcon("dé4.png"));
        plateauJeu.add(dé4V2, new org.netbeans.lib.awtextra.AbsoluteConstraints(195, 120, 55, 55));
        this.repaint();
        dé4V2.setVisible(false);
        JLabel dé5 = new JLabel(new ImageIcon("dé5.png"));
        plateauJeu.add(dé5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 120, 55, 55));
        this.repaint();
        dé5.setVisible(false);
        JLabel dé5V2 = new JLabel(new ImageIcon("dé5.png"));
        plateauJeu.add(dé5V2, new org.netbeans.lib.awtextra.AbsoluteConstraints(195, 120, 55, 55));
        this.repaint();
        dé5V2.setVisible(false);
        JLabel dé6 = new JLabel(new ImageIcon("dé6.png"));
        plateauJeu.add(dé6, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 120, 55, 55));
        this.repaint();
        dé6.setVisible(false);
        JLabel dé6V2 = new JLabel(new ImageIcon("dé6.png"));
        plateauJeu.add(dé6V2, new org.netbeans.lib.awtextra.AbsoluteConstraints(195, 120, 55, 55));
        this.repaint();
        dé6V2.setVisible(false);
       
        Lancer_des = new JButton();
        Lancer_des.setBounds(180, 5, 170, 60);
        Lancer_des.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
        panel_boutons.add(Lancer_des, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 5, 170, 60));
        Lancer_des.setBackground(Color.BLUE);
        Lancer_des.setText("Lancer les dés");
        Lancer_des.setFont(new Font("Lancer les dés", Font.BOLD, 16));
        Lancer_des.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Lancer_des.setEnabled(false);

                Thread thread = new Thread(){
                    public void run(){
                        lancerDes();
                        try {
                            for (int i = 0; i < 6; i++) {
                                dé1.setVisible(true);
                                dé6V2.setVisible(true);
                                Thread.sleep(50);
                                dé1.setVisible(false);
                                dé6V2.setVisible(false);
                                dé2.setVisible(true);
                                dé5V2.setVisible(true);
                                Thread.sleep(50);
                                dé2.setVisible(false);
                                dé5V2.setVisible(false);
                                dé3.setVisible(true);
                                dé4V2.setVisible(true);
                                Thread.sleep(50);
                                dé3.setVisible(false);
                                dé4V2.setVisible(false);
                                dé4.setVisible(true);
                                dé3V2.setVisible(true);
                                Thread.sleep(50);
                                dé4.setVisible(false);
                                dé3V2.setVisible(false);
                                dé5.setVisible(true);
                                dé2V2.setVisible(true);
                                Thread.sleep(50);
                                dé5.setVisible(false);
                                dé2V2.setVisible(false);
                                dé6.setVisible(true);
                                dé1V2.setVisible(true);
                                Thread.sleep(50);
                                dé6.setVisible(false);
                                dé1V2.setVisible(false);
                            }
                        dé1.setVisible(false);
                        dé2.setVisible(false);
                        dé3.setVisible(false);
                        dé4.setVisible(false);
                        dé5.setVisible(false);
                        dé6.setVisible(false);
                        if(de1.valeur==1) {
                            dé1.setVisible(true);
                        }
                        else if(de1.valeur==2) {
                            dé2.setVisible(true);
                        }
                        else if(de1.valeur==3) {
                            dé3.setVisible(true);
                        }
                        if(de1.valeur==4) {
                            dé4.setVisible(true);
                        }
                        if(de1.valeur==5) {
                            dé5.setVisible(true);
                        }
                        else {
                            dé6.setVisible(true);
                        }
                        if(de2.valeur==1) {
                            dé1V2.setVisible(true);
                        }
                        else if(de2.valeur==2) {
                            dé2V2.setVisible(true);
                        }
                        else if(de2.valeur==3) {
                            dé3V2.setVisible(true);
                        }
                        if(de2.valeur==4) {
                            dé4V2.setVisible(true);
                        }
                        if(de2.valeur==5) {
                            dé5V2.setVisible(true);
                        }
                        else {
                            dé6V2.setVisible(true);
                        }
                        Lancer_des.setEnabled(true);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                };

                thread.start();
                changerJoueur();
                Lancer_des.setEnabled(true);
            } 
        });
        this.repaint();
        
        Salles = new JButton();
        Salles.setBounds(180, 70, 170, 60);
        Salles.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
        panel_boutons.add(Salles, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 70, 170, 60));
        Salles.setBackground(Color.BLUE);
        Salles.setText("Salles");
        Salles.setFont(new Font("Salles", Font.BOLD, 16));
        this.repaint();
        
        Payer_Jousset = new JButton();
        Payer_Jousset.setBounds(57,680,250,100);
        Payer_Jousset.setBorder(BorderFactory.createMatteBorder(2,2,2,2, Color.BLACK));
        add(Payer_Jousset, new org.netbeans.lib.awtextra.AbsoluteConstraints(57, 680, 250, 100));
        Payer_Jousset.setBackground(Color.BLUE);
        Payer_Jousset.setText("Payer Jousset");
        Payer_Jousset.setFont(new Font("Payer Jousset", Font.BOLD, 20));
        this.repaint();
        
        Matieres_possedees = new JButton();
        Matieres_possedees.setBounds(1226,680,250,100);
        Matieres_possedees.setBorder(BorderFactory.createMatteBorder(2,2,2,2, Color.BLACK));
        add(Matieres_possedees, new org.netbeans.lib.awtextra.AbsoluteConstraints(1226, 680, 250, 100));
        Matieres_possedees.setBackground(Color.BLUE);
        Matieres_possedees.setText("Matières possédées");
        Matieres_possedees.setFont(new Font("Matières possédées", Font.BOLD, 20));
        this.repaint();
        
        Joueur1 = new JLabel("Joueur 1");
        Dimension text_joueur1 = Joueur1.getPreferredSize();
        Joueur1.setFont(new Font("Joueur 1", Font.BOLD, 25));
        infos_joueurs1.add(Joueur1, new org.netbeans.lib.awtextra.AbsoluteConstraints(125,1));
        this.repaint();
        
        Joueur2 = new JLabel("Joueur 2");
        Dimension text_joueur2 = Joueur2.getPreferredSize();
        Joueur2.setFont(new Font("Joueur 2", Font.BOLD, 25));
        infos_joueurs2.add(Joueur2, new org.netbeans.lib.awtextra.AbsoluteConstraints(125,1));
        this.repaint();
        
        Joueur3 = new JLabel("Joueur 3");
        Dimension text_joueur3 = Joueur3.getPreferredSize();
        Joueur3.setFont(new Font("Joueur 3", Font.BOLD, 25));
        infos_joueurs3.add(Joueur3, new org.netbeans.lib.awtextra.AbsoluteConstraints(125,1));
        this.repaint();
        
        Joueur4 = new JLabel("Joueur 4");
        Dimension text_joueur4 = Joueur4.getPreferredSize();
        Joueur4.setFont(new Font("Joueur 4", Font.BOLD, 25));
        infos_joueurs4.add(Joueur4, new org.netbeans.lib.awtextra.AbsoluteConstraints(125,1));
        this.repaint();
        
        Infos_partie = new JLabel("Infos partie:");
        Dimension text_infos_partie = Infos_partie.getPreferredSize();
        Infos_partie.setFont(new Font("Infos partie;", Font.BOLD, 15));
        infos_partie.add(Infos_partie, new org.netbeans.lib.awtextra.AbsoluteConstraints(5,5));
        this.repaint();
        
        credits1 = new JLabel("Crédits:" + "ECTS");
        Dimension text_credits1 = credits1.getPreferredSize();
        credits1.setFont(new Font("Crédits:" + "ECTS", Font.BOLD, 19));
        infos_joueurs1.add(credits1, new org.netbeans.lib.awtextra.AbsoluteConstraints(5,35));
        this.repaint();
        
        credits2 = new JLabel("Crédits:" + "ECTS");
        Dimension text_credits2 = credits2.getPreferredSize();
        credits2.setFont(new Font("Crédits:" + "ECTS", Font.BOLD, 19));
        infos_joueurs2.add(credits2, new org.netbeans.lib.awtextra.AbsoluteConstraints(5,35));
        this.repaint();
        
        credits3 = new JLabel("Crédits:" + "ECTS");
        Dimension text_credits3 = credits3.getPreferredSize();
        credits3.setFont(new Font("Crédits:" + "ECTS", Font.BOLD, 19));
        infos_joueurs3.add(credits3, new org.netbeans.lib.awtextra.AbsoluteConstraints(5,35));
        this.repaint();
        
        credits4 = new JLabel("Crédits:" + "ECTS");
        Dimension text_credits4 = credits4.getPreferredSize();
        credits4.setFont(new Font("Crédits:" + "ECTS", Font.BOLD, 19));
        infos_joueurs4.add(credits4, new org.netbeans.lib.awtextra.AbsoluteConstraints(5,35));
        this.repaint();
        
        nbCartes1 = new JLabel("Cartes libération de rattrapages : ");
        Dimension text_nbCartes1 = nbCartes1.getPreferredSize();
        nbCartes1.setFont(new Font("Cartes libération de rattrapages : ", Font.BOLD, 9));
        infos_joueurs1.add(nbCartes1, new org.netbeans.lib.awtextra.AbsoluteConstraints(187,122));
        this.repaint();
        
        nbCartes2 = new JLabel("Cartes libération de rattrapages : ");
        Dimension text_nbCartes2 = nbCartes2.getPreferredSize();
        nbCartes2.setFont(new Font("Cartes libération de rattrapages : ", Font.BOLD, 9));
        infos_joueurs2.add(nbCartes2, new org.netbeans.lib.awtextra.AbsoluteConstraints(187,120));
        this.repaint();
        
        nbCartes3 = new JLabel("Cartes libération de rattrapages : ");
        Dimension text_nbCartes3 = nbCartes3.getPreferredSize();
        nbCartes3.setFont(new Font("Cartes libération de rattrapages : ", Font.BOLD, 9));
        infos_joueurs3.add(nbCartes3, new org.netbeans.lib.awtextra.AbsoluteConstraints(187,120));
        this.repaint();
        
        nbCartes4 = new JLabel("Cartes libération de rattrapages : ");
        Dimension text_nbCartes14 = nbCartes4.getPreferredSize();
        nbCartes4.setFont(new Font("Cartes libération de rattrapages : ", Font.BOLD, 9));
        infos_joueurs4.add(nbCartes4, new org.netbeans.lib.awtextra.AbsoluteConstraints(187,120));
        this.repaint();
        
        Libération1 = new JButton();
        Libération1.setBounds(180, 115, 170, 25);
        Libération1.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
        infos_joueurs1.add(Libération1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 115, 170, 25));
        Libération1.setBackground(Color.LIGHT_GRAY);
        Libération1.setText("Carte libération de rattrapages");
        Libération1.setFont(new Font("Carte libération de rattrapages", Font.BOLD, 10));
        Libération1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //l'idée ça serait d'afficher la carte à jouer avec un bouton "utiliser" sauf que pour l'instant la fonction utiliser carte (joueur) elle tire n'importe quelle carte (chance ou caisse) donc faut voir
            }
        });
        this.repaint();
        
        Libération2 = new JButton();
        Libération2.setBounds(180, 115, 170, 25);
        Libération2.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
        infos_joueurs2.add(Libération2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 115, 170, 25));
        Libération2.setBackground(Color.LIGHT_GRAY);
        Libération2.setText("Carte libération de rattrapages");
        Libération2.setFont(new Font("Carte libération de rattrapages", Font.BOLD, 10));
        Libération2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                
            }
        });
        this.repaint();
        
        Libération3 = new JButton();
        Libération3.setBounds(180, 115, 170, 25);
        Libération3.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
        infos_joueurs3.add(Libération3, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 115, 170, 25));
        Libération3.setBackground(Color.LIGHT_GRAY);
        Libération3.setText("Carte libération de rattrapages");
        Libération3.setFont(new Font("Carte libération de rattrapages", Font.BOLD, 10));
        Libération3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                
            }
        });
        this.repaint();
        
        Libération4 = new JButton();
        Libération4.setBounds(180, 115, 170, 25);
        Libération4.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
        infos_joueurs4.add(Libération4, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 115, 170, 25));
        Libération4.setBackground(Color.LIGHT_GRAY);
        Libération4.setText("Carte libération de rattrapages");
        Libération4.setFont(new Font("Carte libération de rattrapages", Font.BOLD, 10));
        Libération4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                
            }
        });
        this.repaint();
        
        PayerPrison1 = new JButton();
        PayerPrison1.setBounds(5, 115, 170, 25);
        PayerPrison1.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
        infos_joueurs1.add(PayerPrison1, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 115, 170, 25));
        PayerPrison1.setBackground(Color.LIGHT_GRAY);
        PayerPrison1.setText("Payer 50 ECTS");
        PayerPrison1.setFont(new Font("Payer 50 ECTS", Font.BOLD, 10));
        PayerPrison1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                
            }
        });
        this.repaint();
        
        PayerPrison2 = new JButton();
        PayerPrison2.setBounds(5, 115, 170, 25);
        PayerPrison2.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
        infos_joueurs2.add(PayerPrison2, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 115, 170, 25));
        PayerPrison2.setBackground(Color.LIGHT_GRAY);
        PayerPrison2.setText("Payer 50 ECTS");
        PayerPrison2.setFont(new Font("Payer 50 ECTS", Font.BOLD, 10));
        PayerPrison2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                
            }
        });
        this.repaint();
        
        PayerPrison3 = new JButton();
        PayerPrison3.setBounds(5, 115, 170, 25);
        PayerPrison3.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
        infos_joueurs3.add(PayerPrison3, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 115, 170, 25));
        PayerPrison3.setBackground(Color.LIGHT_GRAY);
        PayerPrison3.setText("Payer 50 ECTS");
        PayerPrison3.setFont(new Font("Payer 50 ECTS", Font.BOLD, 10));
        PayerPrison3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                
            }
        });
        this.repaint();
        
        PayerPrison4 = new JButton();
        PayerPrison4.setBounds(5, 115, 170, 25);
        PayerPrison4.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
        infos_joueurs4.add(PayerPrison4, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 115, 170, 25));
        PayerPrison4.setBackground(Color.LIGHT_GRAY);
        PayerPrison4.setText("Payer 50 ECTS");
        PayerPrison4.setFont(new Font("Payer 50 ECTS", Font.BOLD, 10));
        PayerPrison4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                
            }
        });
        this.repaint();
        
        Acheter1 = new JButton();
        Acheter1.setBounds(5, 60, 170, 50);
        Acheter1.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
        infos_joueurs1.add(Acheter1, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 60, 170, 50));
        Acheter1.setBackground(Color.LIGHT_GRAY);
        Acheter1.setText("Acheter");
        Acheter1.setFont(new Font("Acheter", Font.BOLD, 20));
        Acheter1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acheter(joueurCourant.pion.caseassociee);
            }
        });
        this.repaint();
        
        Acheter2 = new JButton();
        Acheter2.setBounds(5, 60, 170, 50);
        Acheter2.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
        infos_joueurs2.add(Acheter2, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 60, 170, 50));
        Acheter2.setBackground(Color.LIGHT_GRAY);
        Acheter2.setText("Acheter");
        Acheter2.setFont(new Font("Acheter", Font.BOLD, 20));
        Acheter2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acheter(joueurCourant.pion.caseassociee);
            }
        });
        this.repaint();
        
        Acheter3 = new JButton();
        Acheter3.setBounds(5, 60, 170, 50);
        Acheter3.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
        infos_joueurs3.add(Acheter3, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 60, 170, 50));
        Acheter3.setBackground(Color.LIGHT_GRAY);
        Acheter3.setText("Acheter");
        Acheter3.setFont(new Font("Acheter", Font.BOLD, 20));
        Acheter3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acheter(joueurCourant.pion.caseassociee);
            }
        });
        this.repaint();
        
        Acheter4 = new JButton();
        Acheter4.setBounds(5, 60, 170, 50);
        Acheter4.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
        infos_joueurs4.add(Acheter4, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 60, 170, 50));
        Acheter4.setBackground(Color.LIGHT_GRAY);
        Acheter4.setText("Acheter");
        Acheter4.setFont(new Font("Acheter", Font.BOLD, 20));
        Acheter4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acheter(joueurCourant.pion.caseassociee);
            }
        });
        this.repaint();
        
        ValiderMatiere1 = new JButton();
        ValiderMatiere1.setBounds(180, 60, 170, 50);
        ValiderMatiere1.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
        infos_joueurs1.add(ValiderMatiere1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 60, 170, 50));
        ValiderMatiere1.setBackground(Color.LIGHT_GRAY);
        ValiderMatiere1.setText("Valider");
        ValiderMatiere1.setFont(new Font("Valider", Font.BOLD, 20));
        ValiderMatiere1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                
            }
        });
        this.repaint();
        
        ValiderMatiere2 = new JButton();
        ValiderMatiere2.setBounds(180, 60, 170, 50);
        ValiderMatiere2.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
        infos_joueurs2.add(ValiderMatiere2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 60, 170, 50));
        ValiderMatiere2.setBackground(Color.LIGHT_GRAY);
        ValiderMatiere2.setText("Valider");
        ValiderMatiere2.setFont(new Font("Valider", Font.BOLD, 20));
        ValiderMatiere2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                
            }
        });
        this.repaint();
        
        ValiderMatiere3 = new JButton();
        ValiderMatiere3.setBounds(180, 60, 170, 50);
        ValiderMatiere3.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
        infos_joueurs3.add(ValiderMatiere3, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 60, 170, 50));
        ValiderMatiere3.setBackground(Color.LIGHT_GRAY);
        ValiderMatiere3.setText("Valider");
        ValiderMatiere3.setFont(new Font("Valider", Font.BOLD, 20));
        ValiderMatiere3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                
            }
        });
        this.repaint();
        
        ValiderMatiere4 = new JButton();
        ValiderMatiere4.setBounds(180, 60, 170, 50);
        ValiderMatiere4.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
        infos_joueurs4.add(ValiderMatiere4, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 60, 170, 50));
        ValiderMatiere4.setBackground(Color.LIGHT_GRAY);
        ValiderMatiere4.setText("Valider");
        ValiderMatiere4.setFont(new Font("Valider", Font.BOLD, 20));
        ValiderMatiere4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                
            }
        });
        this.repaint();
        
        marron1 = new JButton();
        marron1.setBounds(8, 157, 38, 38);
        marron1.setBorder(BorderFactory.createMatteBorder(3,3,3,3, Color.BLACK));
        infos_joueurs1.add(marron1, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 157, 38, 38));
        marron1.setBackground(new Color(148, 74, 37));
        marron1.setText("0");
        marron1.setFont(new Font("0", Font.BOLD, 10));
        this.repaint();
        
        bleuciel1 = new JButton();
        bleuciel1.setBounds(51, 157, 38, 38);
        bleuciel1.setBorder(BorderFactory.createMatteBorder(3,3,3,3, Color.BLACK));
        infos_joueurs1.add(bleuciel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(51, 157, 38, 38));
        bleuciel1.setBackground(new Color(189, 227, 246));
        bleuciel1.setText("0");
        bleuciel1.setFont(new Font("0", Font.BOLD, 10));
        this.repaint();
        
        violet1 = new JButton();
        violet1.setBounds(94, 157, 38, 38);
        violet1.setBorder(BorderFactory.createMatteBorder(3,3,3,3, Color.BLACK));
        infos_joueurs1.add(violet1, new org.netbeans.lib.awtextra.AbsoluteConstraints(94, 157, 38, 38));
        violet1.setBackground(new Color(120, 39, 134));
        violet1.setText("0");
        violet1.setFont(new Font("0", Font.BOLD, 10));
        this.repaint();
        
        orange1 = new JButton();
        orange1.setBounds(137, 157, 38, 38);
        orange1.setBorder(BorderFactory.createMatteBorder(3,3,3,3, Color.BLACK));
        infos_joueurs1.add(orange1, new org.netbeans.lib.awtextra.AbsoluteConstraints(137, 157, 38, 38));
        orange1.setBackground(new Color(242, 148, 0));
        orange1.setText("0");
        orange1.setFont(new Font("0", Font.BOLD, 10));
        this.repaint();
        
        rouge1 = new JButton();
        rouge1.setBounds(180, 157, 38, 38);
        rouge1.setBorder(BorderFactory.createMatteBorder(3,3,3,3, Color.BLACK));
        infos_joueurs1.add(rouge1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 157, 38, 38));
        rouge1.setBackground(new Color(227, 0, 27));
        rouge1.setText("0");
        rouge1.setFont(new Font("0", Font.BOLD, 10));
        this.repaint();
        
        jaune1 = new JButton();
        jaune1.setBounds(223, 157, 38, 38);
        jaune1.setBorder(BorderFactory.createMatteBorder(3,3,3,3, Color.BLACK));
        infos_joueurs1.add(jaune1, new org.netbeans.lib.awtextra.AbsoluteConstraints(223, 157, 38, 38));
        jaune1.setBackground(new Color(255, 238, 2));
        jaune1.setText("0");
        jaune1.setFont(new Font("0", Font.BOLD, 10));
        this.repaint();
        
        vert1 = new JButton();
        vert1.setBounds(266, 157, 38, 38);
        vert1.setBorder(BorderFactory.createMatteBorder(3,3,3,3, Color.BLACK));
        infos_joueurs1.add(vert1, new org.netbeans.lib.awtextra.AbsoluteConstraints(266, 157, 38, 38));
        vert1.setBackground(new Color(38, 163, 69));
        vert1.setText("0");
        vert1.setFont(new Font("0", Font.BOLD, 10));
        this.repaint();
        
        bleufoncé1 = new JButton();
        bleufoncé1.setBounds(309, 157, 38, 38);
        bleufoncé1.setBorder(BorderFactory.createMatteBorder(3,3,3,3, Color.BLACK));
        infos_joueurs1.add(bleufoncé1, new org.netbeans.lib.awtextra.AbsoluteConstraints(309, 157, 38, 38));
        bleufoncé1.setBackground(new Color(28, 55, 126));
        bleufoncé1.setText("0");
        bleufoncé1.setFont(new Font("0", Font.BOLD, 10));
        this.repaint();
        
        marron2 = new JButton();
        marron2.setBounds(8, 157, 38, 38);
        marron2.setBorder(BorderFactory.createMatteBorder(3,3,3,3, Color.BLACK));
        infos_joueurs2.add(marron2, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 157, 38, 38));
        marron2.setBackground(new Color(148, 74, 37));
        marron2.setText("0");
        marron2.setFont(new Font("0", Font.BOLD, 10));
        this.repaint();
        
        bleuciel2 = new JButton();
        bleuciel2.setBounds(51, 157, 38, 38);
        bleuciel2.setBorder(BorderFactory.createMatteBorder(3,3,3,3, Color.BLACK));
        infos_joueurs2.add(bleuciel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(51, 157, 38, 38));
        bleuciel2.setBackground(new Color(189, 227, 246));
        bleuciel2.setText("0");
        bleuciel2.setFont(new Font("0", Font.BOLD, 10));
        this.repaint();
        
        violet2 = new JButton();
        violet2.setBounds(94, 157, 38, 38);
        violet2.setBorder(BorderFactory.createMatteBorder(3,3,3,3, Color.BLACK));
        infos_joueurs2.add(violet2, new org.netbeans.lib.awtextra.AbsoluteConstraints(94, 157, 38, 38));
        violet2.setBackground(new Color(120, 39, 134));
        violet2.setText("0");
        violet2.setFont(new Font("0", Font.BOLD, 10));
        this.repaint();
        
        orange2 = new JButton();
        orange2.setBounds(137, 157, 38, 38);
        orange2.setBorder(BorderFactory.createMatteBorder(3,3,3,3, Color.BLACK));
        infos_joueurs2.add(orange2, new org.netbeans.lib.awtextra.AbsoluteConstraints(137, 157, 38, 38));
        orange2.setBackground(new Color(242, 148, 0));
        orange2.setText("0");
        orange2.setFont(new Font("0", Font.BOLD, 10));
        this.repaint();
        
        rouge2 = new JButton();
        rouge2.setBounds(180, 157, 38, 38);
        rouge2.setBorder(BorderFactory.createMatteBorder(3,3,3,3, Color.BLACK));
        infos_joueurs2.add(rouge2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 157, 38, 38));
        rouge2.setBackground(new Color(227, 0, 27));
        rouge2.setText("0");
        rouge2.setFont(new Font("0", Font.BOLD, 10));
        this.repaint();
        
        jaune2 = new JButton();
        jaune2.setBounds(223, 157, 38, 38);
        jaune2.setBorder(BorderFactory.createMatteBorder(3,3,3,3, Color.BLACK));
        infos_joueurs2.add(jaune2, new org.netbeans.lib.awtextra.AbsoluteConstraints(223, 157, 38, 38));
        jaune2.setBackground(new Color(255, 238, 2));
        jaune2.setText("0");
        jaune2.setFont(new Font("0", Font.BOLD, 10));
        this.repaint();
        
        vert2 = new JButton();
        vert2.setBounds(266, 157, 38, 38);
        vert2.setBorder(BorderFactory.createMatteBorder(3,3,3,3, Color.BLACK));
        infos_joueurs2.add(vert2, new org.netbeans.lib.awtextra.AbsoluteConstraints(266, 157, 38, 38));
        vert2.setBackground(new Color(38, 163, 69));
        vert2.setText("0");
        vert2.setFont(new Font("0", Font.BOLD, 10));
        this.repaint();
        
        bleufoncé2 = new JButton();
        bleufoncé2.setBounds(309, 157, 38, 38);
        bleufoncé2.setBorder(BorderFactory.createMatteBorder(3,3,3,3, Color.BLACK));
        infos_joueurs2.add(bleufoncé2, new org.netbeans.lib.awtextra.AbsoluteConstraints(309, 157, 38, 38));
        bleufoncé2.setBackground(new Color(28, 55, 126));
        bleufoncé2.setText("0");
        bleufoncé2.setFont(new Font("0", Font.BOLD, 10));
        this.repaint();
        
        marron3 = new JButton();
        marron3.setBounds(8, 157, 38, 38);
        marron3.setBorder(BorderFactory.createMatteBorder(3,3,3,3, Color.BLACK));
        infos_joueurs3.add(marron3, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 157, 38, 38));
        marron3.setBackground(new Color(148, 74, 37));
        marron3.setText("0");
        marron3.setFont(new Font("0", Font.BOLD, 10));
        this.repaint();
        
        bleuciel3 = new JButton();
        bleuciel3.setBounds(51, 157, 38, 38);
        bleuciel3.setBorder(BorderFactory.createMatteBorder(3,3,3,3, Color.BLACK));
        infos_joueurs3.add(bleuciel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(51, 157, 38, 38));
        bleuciel3.setBackground(new Color(189, 227, 246));
        bleuciel3.setText("0");
        bleuciel3.setFont(new Font("0", Font.BOLD, 10));
        this.repaint();
        
        violet3 = new JButton();
        violet3.setBounds(94, 157, 38, 38);
        violet3.setBorder(BorderFactory.createMatteBorder(3,3,3,3, Color.BLACK));
        infos_joueurs3.add(violet3, new org.netbeans.lib.awtextra.AbsoluteConstraints(94, 157, 38, 38));
        violet3.setBackground(new Color(120, 39, 134));
        violet3.setText("0");
        violet3.setFont(new Font("0", Font.BOLD, 10));
        this.repaint();
        
        orange3 = new JButton();
        orange3.setBounds(137, 157, 38, 38);
        orange3.setBorder(BorderFactory.createMatteBorder(3,3,3,3, Color.BLACK));
        infos_joueurs3.add(orange3, new org.netbeans.lib.awtextra.AbsoluteConstraints(137, 157, 38, 38));
        orange3.setBackground(new Color(242, 148, 0));
        orange3.setText("0");
        orange3.setFont(new Font("0", Font.BOLD, 10));
        this.repaint();
        
        rouge3 = new JButton();
        rouge3.setBounds(180, 157, 38, 38);
        rouge3.setBorder(BorderFactory.createMatteBorder(3,3,3,3, Color.BLACK));
        infos_joueurs3.add(rouge3, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 157, 38, 38));
        rouge3.setBackground(new Color(227, 0, 27));
        rouge3.setText("0");
        rouge3.setFont(new Font("0", Font.BOLD, 10));
        this.repaint();
        
        jaune3 = new JButton();
        jaune3.setBounds(223, 157, 38, 38);
        jaune3.setBorder(BorderFactory.createMatteBorder(3,3,3,3, Color.BLACK));
        infos_joueurs3.add(jaune3, new org.netbeans.lib.awtextra.AbsoluteConstraints(223, 157, 38, 38));
        jaune3.setBackground(new Color(255, 238, 2));
        jaune3.setText("0");
        jaune3.setFont(new Font("0", Font.BOLD, 10));
        this.repaint();
        
        vert3 = new JButton();
        vert3.setBounds(266, 157, 38, 38);
        vert3.setBorder(BorderFactory.createMatteBorder(3,3,3,3, Color.BLACK));
        infos_joueurs3.add(vert3, new org.netbeans.lib.awtextra.AbsoluteConstraints(266, 157, 38, 38));
        vert3.setBackground(new Color(38, 163, 69));
        vert3.setText("0");
        vert3.setFont(new Font("0", Font.BOLD, 10));
        this.repaint();
        
        bleufoncé3 = new JButton();
        bleufoncé3.setBounds(309, 157, 38, 38);
        bleufoncé3.setBorder(BorderFactory.createMatteBorder(3,3,3,3, Color.BLACK));
        infos_joueurs3.add(bleufoncé3, new org.netbeans.lib.awtextra.AbsoluteConstraints(309, 157, 38, 38));
        bleufoncé3.setBackground(new Color(28, 55, 126));
        bleufoncé3.setText("0");
        bleufoncé3.setFont(new Font("0", Font.BOLD, 10));
        this.repaint();
        
        marron4 = new JButton();
        marron4.setBounds(8, 157, 38, 38);
        marron4.setBorder(BorderFactory.createMatteBorder(3,3,3,3, Color.BLACK));
        infos_joueurs4.add(marron4, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 157, 38, 38));
        marron4.setBackground(new Color(148, 74, 37));
        marron4.setText("0");
        marron4.setFont(new Font("0", Font.BOLD, 10));
        this.repaint();
        
        bleuciel4 = new JButton();
        bleuciel4.setBounds(51, 157, 38, 38);
        bleuciel4.setBorder(BorderFactory.createMatteBorder(3,3,3,3, Color.BLACK));
        infos_joueurs4.add(bleuciel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(51, 157, 38, 38));
        bleuciel4.setBackground(new Color(189, 227, 246));
        bleuciel4.setText("0");
        bleuciel4.setFont(new Font("0", Font.BOLD, 10));
        this.repaint();
        
        violet4 = new JButton();
        violet4.setBounds(94, 157, 38, 38);
        violet4.setBorder(BorderFactory.createMatteBorder(3,3,3,3, Color.BLACK));
        infos_joueurs4.add(violet4, new org.netbeans.lib.awtextra.AbsoluteConstraints(94, 157, 38, 38));
        violet4.setBackground(new Color(120, 39, 134));
        violet4.setText("0");
        violet4.setFont(new Font("0", Font.BOLD, 10));
        this.repaint();
        
        orange4 = new JButton();
        orange4.setBounds(137, 157, 38, 38);
        orange4.setBorder(BorderFactory.createMatteBorder(3,3,3,3, Color.BLACK));
        infos_joueurs4.add(orange4, new org.netbeans.lib.awtextra.AbsoluteConstraints(137, 157, 38, 38));
        orange4.setBackground(new Color(242, 148, 0));
        orange4.setText("0");
        orange4.setFont(new Font("0", Font.BOLD, 10));
        this.repaint();
        
        rouge4 = new JButton();
        rouge4.setBounds(180, 157, 38, 38);
        rouge4.setBorder(BorderFactory.createMatteBorder(3,3,3,3, Color.BLACK));
        infos_joueurs4.add(rouge4, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 157, 38, 38));
        rouge4.setBackground(new Color(227, 0, 27));
        rouge4.setText("0");
        rouge4.setFont(new Font("0", Font.BOLD, 10));
        this.repaint();
        
        jaune4 = new JButton();
        jaune4.setBounds(223, 157, 38, 38);
        jaune4.setBorder(BorderFactory.createMatteBorder(3,3,3,3, Color.BLACK));
        infos_joueurs4.add(jaune4, new org.netbeans.lib.awtextra.AbsoluteConstraints(223, 157, 38, 38));
        jaune4.setBackground(new Color(255, 238, 2));
        jaune4.setText("0");
        jaune4.setFont(new Font("0", Font.BOLD, 10));
        this.repaint();
        
        vert4 = new JButton();
        vert4.setBounds(266, 157, 38, 38);
        vert4.setBorder(BorderFactory.createMatteBorder(3,3,3,3, Color.BLACK));
        infos_joueurs4.add(vert4, new org.netbeans.lib.awtextra.AbsoluteConstraints(266, 157, 38, 38));
        vert4.setBackground(new Color(38, 163, 69));
        vert4.setText("0");
        vert4.setFont(new Font("0", Font.BOLD, 10));
        this.repaint();
        
        bleufoncé4 = new JButton();
        bleufoncé4.setBounds(309, 157, 38, 38);
        bleufoncé4.setBorder(BorderFactory.createMatteBorder(3,3,3,3, Color.BLACK));
        infos_joueurs4.add(bleufoncé4, new org.netbeans.lib.awtextra.AbsoluteConstraints(309, 157, 38, 38));
        bleufoncé4.setBackground(new Color(28, 55, 126));
        bleufoncé4.setText("0");
        bleufoncé4.setFont(new Font("0", Font.BOLD, 10));
        this.repaint();
        
        SallesPossedees1 = new JLabel("Salles Possédées :");
        Dimension salles_possed1_size = SallesPossedees1.getPreferredSize();
        SallesPossedees1.setFont(new Font("Salles Possédées :", Font.BOLD, 12));
        infos_joueurs1.add(SallesPossedees1, new org.netbeans.lib.awtextra.AbsoluteConstraints(8,140));
        this.repaint();
        
        SallesPossedees2 = new JLabel("Salles Possédées :");
        Dimension salles_possed2_size = SallesPossedees2.getPreferredSize();
        SallesPossedees2.setFont(new Font("Salles Possédées :", Font.BOLD, 12));
        infos_joueurs2.add(SallesPossedees2, new org.netbeans.lib.awtextra.AbsoluteConstraints(8,140));
        this.repaint();
        
        SallesPossedees3 = new JLabel("Salles Possédées :");
        Dimension salles_possed3_size = SallesPossedees3.getPreferredSize();
        SallesPossedees3.setFont(new Font("Salles Possédées :", Font.BOLD, 12));
        infos_joueurs3.add(SallesPossedees3, new org.netbeans.lib.awtextra.AbsoluteConstraints(8,140));
        this.repaint();
        
        SallesPossedees4 = new JLabel("Salles Possédées :");
        Dimension salles_possed4_size = SallesPossedees4.getPreferredSize();
        SallesPossedees4.setFont(new Font("Salles Possédées :", Font.BOLD, 12));
        infos_joueurs4.add(SallesPossedees4, new org.netbeans.lib.awtextra.AbsoluteConstraints(8,140));
        this.repaint();
                
        Autres1 = new JLabel("Autre(s):0");
        Dimension autres1_size = Autres1.getPreferredSize();
        Autres1.setFont(new Font("Autre(s):0", Font.BOLD, 10));
        infos_joueurs1.add(Autres1, new org.netbeans.lib.awtextra.AbsoluteConstraints(298,143));
        this.repaint();
        
        Autres2 = new JLabel("Autre(s):0");
        Dimension autres2_size = Autres2.getPreferredSize();
        Autres2.setFont(new Font("Autre(s):0", Font.BOLD, 10));
        infos_joueurs2.add(Autres2, new org.netbeans.lib.awtextra.AbsoluteConstraints(298,143));
        this.repaint();
        
        Autres3 = new JLabel("Autre(s):0");
        Dimension autres3_size = Autres3.getPreferredSize();
        Autres3.setFont(new Font("Autre(s):0", Font.BOLD, 10));
        infos_joueurs3.add(Autres3, new org.netbeans.lib.awtextra.AbsoluteConstraints(298,143));
        this.repaint();
        
        Autres4 = new JLabel("Autre(s):0");
        Dimension autres4_size = Autres4.getPreferredSize();
        Autres4.setFont(new Font("Autre(s):0", Font.BOLD, 10));
        infos_joueurs4.add(Autres4, new org.netbeans.lib.awtextra.AbsoluteConstraints(298,143));
        this.repaint();
        
    }
    
    PlateauPanel plateauJeu;
    private JLabel titre;
    private JLabel Joueur1;
    private JLabel Joueur2;
    private JLabel Joueur3;
    private JLabel Joueur4;
    private JLabel Infos_partie;
    private JLabel credits1;
    private JLabel credits2;
    private JLabel credits3;
    private JLabel credits4;
    private JLabel pseudo1;
    private JLabel pseudo2;
    private JLabel pseudo3;
    private JLabel pseudo4;
    private JLabel nbCartes1;
    private JLabel nbCartes2;
    private JLabel nbCartes3;
    private JLabel nbCartes4;
    private JLabel SallesPossedees1;
    private JLabel SallesPossedees2;
    private JLabel SallesPossedees3;
    private JLabel SallesPossedees4;
    private JLabel Autres1;
    private JLabel Autres2;
    private JLabel Autres3;
    private JLabel Autres4;
    private JPanel infos_joueurs1;
    private JPanel infos_joueurs2;
    private JPanel infos_joueurs3;
    private JPanel infos_joueurs4;
    private JPanel panel_boutons;
    private JPanel infos_partie;
    private JButton Regles;
    private JButton Démarrer;
    private JButton Lancer_des;
    private JButton Salles;
    private JButton Payer_Jousset;
    private JButton Matieres_possedees;
    private JButton Libération1;
    private JButton Libération2;
    private JButton Libération3;
    private JButton Libération4;
    private JButton PayerPrison1;
    private JButton PayerPrison2;
    private JButton PayerPrison3;
    private JButton PayerPrison4;
    private JButton Acheter1;
    private JButton Acheter2;
    private JButton Acheter3;
    private JButton Acheter4;
    private JButton ValiderMatiere1;
    private JButton ValiderMatiere2;
    private JButton ValiderMatiere3;
    private JButton ValiderMatiere4;
    private JButton marron1;
    private JButton bleuciel1;
    private JButton violet1;
    private JButton orange1;
    private JButton rouge1;
    private JButton jaune1;
    private JButton vert1;
    private JButton bleufoncé1;
    private JButton marron2;
    private JButton bleuciel2;
    private JButton violet2;
    private JButton orange2;
    private JButton rouge2;
    private JButton jaune2;
    private JButton vert2;
    private JButton bleufoncé2;
    private JButton marron3;
    private JButton bleuciel3;
    private JButton violet3;
    private JButton orange3;
    private JButton rouge3;
    private JButton jaune3;
    private JButton vert3;
    private JButton bleufoncé3;
    private JButton marron4;
    private JButton bleuciel4;
    private JButton violet4;
    private JButton orange4;
    private JButton rouge4;
    private JButton jaune4;
    private JButton vert4;
    private JButton bleufoncé4;
    private Thread thread;
    private JTextArea zone_texte_infos;
    private JTextArea Regles_Text;
    private JScrollPane Regles_pane;
    private JLabel Regles_LabelTitre;
    
    
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
            if(joueurCourant.tabCartes[0]!=null || joueurCourant.tabCartes[1]!=null) {
                if(joueurCourant==tabJoueurs[0]) {
                    nbCartes1.setVisible(false);
                    Libération1.setVisible(true);
                }
                else if(joueurCourant==tabJoueurs[1]) {
                    nbCartes2.setVisible(false);
                    Libération2.setVisible(true);
                }
                else if(joueurCourant==tabJoueurs[2]) {
                    nbCartes3.setVisible(false);
                    Libération3.setVisible(true);
                }
                else {
                    nbCartes4.setVisible(false);
                    Libération4.setVisible(true);
                }
            }
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
            if(joueurCourant==tabJoueurs[0]) {
                Libération1.setVisible(false);
                nbCartes1.setVisible(true);
            }
            else if(joueurCourant==tabJoueurs[1]) {
                Libération2.setVisible(false);
                nbCartes2.setVisible(true);
            }
            if(joueurCourant==tabJoueurs[2]) {
                Libération3.setVisible(false);
                nbCartes3.setVisible(true);
            }
            else {
                Libération4.setVisible(false);
                nbCartes4.setVisible(true);
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
     
     
     public int lancerDes() { //lire double et initialiser compteur
        Random lancerdes = new Random();
        de1.valeur = lancerdes.nextInt(6)+1; 
        de2.valeur = lancerdes.nextInt(6)+1;
        if(lireDouble()==true) {
            compteurDouble++;
        }
        return(de1.valeur+de2.valeur);
    }
    
     
     public boolean lireDouble() {
        if (de1.valeur == de2.valeur) {
            return true;
        }
        else {
            return false;
        }
    }
     
     
     public void deplacerPion(Pion pionAssocie, int nbcases, Case caseInitiale) { //gérer si i+nbcases dépasse l'indice max du plateau
        for(int i=0; i<plateau.plateaudejeu.length; i++) {
            if(caseInitiale==plateau.plateaudejeu[i]) {
                if (i+nbcases <= 39) {
                    pionAssocie.caseassociee=plateau.plateaudejeu[i+nbcases];
                }
                else {
                    pionAssocie.caseassociee=plateau.plateaudejeu[i+nbcases-39]; //tour de plateau
                    if (i+nbcases-39!=0) {
                        joueurCourant.credits = joueurCourant.credits + 200; //200crédits quand on a fait un tour de plateau
                    }
                    else {
                        joueurCourant.credits = joueurCourant.credits + 400; //400crédits quand on tombe sur la case départ
                    }
                }
            }
        }
    }
     
     
     public boolean faireActionCase(){ // A VERIFIER 
        Case caseDuJoueur = joueurCourant.pion.caseassociee;
        if (caseDuJoueur == plateau.plateaudejeu[2] || caseDuJoueur == plateau.plateaudejeu[17] || caseDuJoueur == plateau.plateaudejeu[33]) { //s'il est sur une case caisse de communauté
            Carte carteTiree = tirerCarte(paquetCommunaute);
            if (carteTiree.idCarte != 4) {
                faireActionCarte(carteTiree);
            }
            else {
                //faire action carte choix simple --> récupérer le choix
            }
            return true;
        }
        else if (caseDuJoueur == plateau.plateaudejeu[7] || caseDuJoueur == plateau.plateaudejeu[22] || caseDuJoueur == plateau.plateaudejeu[36]) { //s'il est sur une case chance
            Carte carteTiree = tirerCarte(paquetChance);
            if (carteTiree.idCarte != 15) {
                faireActionCarte(carteTiree);
            }
            else {
                //faire action carte choix --> récupérer le choix
            }
            return true;
        }
        else if (caseDuJoueur == plateau.plateaudejeu[4]) { //s'il est sur la premiere taxe
            joueurCourant.credits = joueurCourant.credits-200;
            argentParcGratuit = argentParcGratuit + 200;
            zone_texte_infos.setText(joueurCourant.nom + ", vous avez triché!!! \nVous payez 200 ECTS et repassez le CC en tête à tête avec François Stephan!");
            return true;
        }
        else if (caseDuJoueur == plateau.plateaudejeu[38]) { //deuxième taxe
            joueurCourant.credits = joueurCourant.credits-100;
            argentParcGratuit = argentParcGratuit + 100;
            zone_texte_infos.setText(joueurCourant.nom + ", vous cumulez trop d'absences!\nPayez 100 ECTS et vennez en cours sans quoi une année de plus à suivre les CM's de Barandon vous attends!!!");
            return true;
        }
        else if (caseDuJoueur == plateau.plateaudejeu[20]) { //parc gratuit
            joueurCourant.credits= joueurCourant.credits + argentParcGratuit;
            argentParcGratuit = 0;
            zone_texte_infos.setText(joueurCourant.nom + ", vous majorez le partiel de maths abs à 55%!!!\nVous recevez " + argentParcGratuit + " ECTS et les félicitations de la direction!");
            return true;
        }
        else if (caseDuJoueur == plateau.plateaudejeu[30]) { //prison
            joueurCourant.prison = true;
            zone_texte_infos.setText(joueurCourant.nom + ", vos notes sont trop faibles!\nAllez aux rattrapages et validez-les pour espérer passer l'année");
            return true;
        }
        else if (caseDuJoueur == plateau.plateaudejeu[12] && caseDuJoueur.proprietaire != null && caseDuJoueur.proprietaire!=joueurCourant) { //premiere compagnie déjà achetée
            if (caseDuJoueur.proprietaire == plateau.plateaudejeu[28].proprietaire) {
                joueurCourant.credits = joueurCourant.credits - (de1.valeur + de2.valeur)*10;
                caseDuJoueur.proprietaire.credits = caseDuJoueur.proprietaire.credits + (de1.valeur + de2.valeur)*10;
                zone_texte_infos.setText(joueurCourant.nom + ", vous devez payer la maintenance des micro-ondes à " + caseDuJoueur.proprietaire + " qui en est le respo!\nVous lui versez " + (de1.valeur + de2.valeur)*10 + " ECTS!");
                return true;
            }
            else {
                joueurCourant.credits = joueurCourant.credits - (de1.valeur + de2.valeur)*4;
                caseDuJoueur.proprietaire.credits = caseDuJoueur.proprietaire.credits + (de1.valeur + de2.valeur)*4;
                zone_texte_infos.setText(joueurCourant.nom + ", vous devez payer la maintenance des micro-ondes à " + caseDuJoueur.proprietaire + " qui en est le respo!\nVous lui versez " + (de1.valeur + de2.valeur)*4 + " ECTS!");
                return true;
            }
        }
        else if (caseDuJoueur == plateau.plateaudejeu[28] && caseDuJoueur.proprietaire != null) { //deuxième compagnie déjà achetée
            if (caseDuJoueur.proprietaire == plateau.plateaudejeu[12].proprietaire) {
                joueurCourant.credits = joueurCourant.credits - (de1.valeur + de2.valeur)*10;
                caseDuJoueur.proprietaire.credits = caseDuJoueur.proprietaire.credits + (de1.valeur + de2.valeur)*10;
                zone_texte_infos.setText(joueurCourant.nom + ", vous devez payer la maintenace du chauffage à " + caseDuJoueur.proprietaire + " qui en est le respo!\nVous lui versez " + (de1.valeur + de2.valeur)*10 + " ECTS!");
                return true;
            }
            else {
                joueurCourant.credits = joueurCourant.credits - (de1.valeur + de2.valeur)*4;
                caseDuJoueur.proprietaire.credits = caseDuJoueur.proprietaire.credits + (de1.valeur + de2.valeur)*4;
                zone_texte_infos.setText(joueurCourant.nom + ", vous devez payer la maintenance du chauffage à " + caseDuJoueur.proprietaire + " qui en est le respo!\nVous lui versez " + (de1.valeur + de2.valeur)*10 + " ECTS!");
                return true;
            }
        }
        else if (caseDuJoueur == plateau.plateaudejeu[5] && caseDuJoueur.proprietaire != null) {
            payerloyer(caseDuJoueur);
            zone_texte_infos.setText(joueurCourant.nom + ", vous tombez dans un des lieux iconiques préférés de " + caseDuJoueur.proprietaire + "!\nVous lui versez " + caseDuJoueur.loyer + " ECTS pour vous excuser!");
        }
        else if (caseDuJoueur == plateau.plateaudejeu[15] && caseDuJoueur.proprietaire != null) {
            payerloyer(caseDuJoueur);
            zone_texte_infos.setText(joueurCourant.nom + ", vous tombez dans un des lieux iconiques préférés de " + caseDuJoueur.proprietaire + "!\nVous lui versez " + caseDuJoueur.loyer + " ECTS pour vous excuser!");
        }
        else if (caseDuJoueur == plateau.plateaudejeu[25] && caseDuJoueur.proprietaire != null) {
            payerloyer(caseDuJoueur);
            zone_texte_infos.setText(joueurCourant.nom + ", vous tombez dans un des lieux iconiques préférés de " + caseDuJoueur.proprietaire + "!\nVous lui versez " + caseDuJoueur.loyer + " ECTS pour vous excuser!");
        }
        else if (caseDuJoueur == plateau.plateaudejeu[35] && caseDuJoueur.proprietaire != null) {
            payerloyer(caseDuJoueur);
            zone_texte_infos.setText(joueurCourant.nom + ", vous tombez dans un des lieux iconiques préférés de " + caseDuJoueur.proprietaire + "!\nVous lui versez " + caseDuJoueur.loyer + " ECTS pour vous excuser!");
        }
        
        else if (caseDuJoueur.proprietaire != null) { //autre cases déjà achetées
            payerloyer(caseDuJoueur);
            zone_texte_infos.setText(joueurCourant.nom + ", vous entrez dans la salle où " + caseDuJoueur.proprietaire + " est en train de réviser!\nVous lui versez " + caseDuJoueur.loyer + " ECTS pour vous excuser!");
            return true;
        }
        return false;
    }
     
     
     public void eliminationJoueur() { //réinitialiser toutes ses cases
        for (int i=0; i<joueurCourant.sallesPossedees.size(); i++) {
            Case c = joueurCourant.sallesPossedees.get(i);
            c.maison = 0;
            c.proprietaire = null;
        }
        joueurCourant.sallesPossedees=null;
        joueurCourant.tabCartes=null; 
        for (int i=0; i<4; i++) {
            if (joueurCourant == tabJoueurs[i]) { //enlever le joueur du tableau
                tabJoueurs[i] = null;
            }
        }
    }
     
     
     public void changerJoueur() { // j'ai gardé ta fonction en commentaire et j'en ai écrite une autre parce que j'ai modifié elimination joueur en supprimant les joueurs du tableau
        int temp = 0;
        boolean changementi = false;
        for (int i=0; i<4; i++) { //i représente l'indice du joueur courant dans le tableau tabjoueurs
            for (int j=1; j<4; j++) { //j représente le décalage du nouveau joueur courant dans le tableau tabjoueurs
                
                if (joueurCourant == tabJoueurs[i]) {
                    if (i==3 || i+j>3) { //si on sort de l'intervalle des indices du tableau
                        temp = i; //on garde en mémoire la position du joueur courant initial
                        i = i-3;
                        changementi = true; //on indique qu'on a changé la valeur de i
                    }
                    if (tabJoueurs[i+j] != null) { //si le joueur qu'on veut passer en joueur courant n'est pas éliminé
                        joueurCourant = tabJoueurs[i+j]; //il devient joueur courant
                    }
                    else if (changementi == true) { // si i a été changé 
                        i = temp; //sinon i reprend sa valeur initale
                    }
                }
               
            }
        }
        compteurDouble = 0; //réinitialisation du compteur double à 0
    }
     
     public Carte tirerCarte(Paquet paquet) {
        for (int i=0; i<16; i++) {
            if(paquet.tabstatuts[i] == true) {
                paquet.tabstatuts[i] = false;
                return paquet.paquet[i];
            }
        }
        return null;
    }
     
     public void faireActionCarte(Carte carteTiree) {
        boolean com = carteTiree.communaute;
        int id = carteTiree.idCarte;
        if (com == true){
            if (id==0) {
                joueurCourant.credits = joueurCourant.credits-20;
                argentParcGratuit = argentParcGratuit + 20;
                zone_texte_infos.setText(joueurCourant.nom + " payez 20 ECTS!");
            }
            else if (id==1) {
                joueurCourant.credits = joueurCourant.credits-50;
                argentParcGratuit = argentParcGratuit + 50;
                zone_texte_infos.setText(joueurCourant.nom + " payez 50 ECTS!");
            }
            else if (id==2) {
                joueurCourant.pion.caseassociee = plateau.plateaudejeu[10];
                joueurCourant.prison = true;
                zone_texte_infos.setText(joueurCourant.nom + " allez aux rattrapages");
            }
            else if (id==3) {
                joueurCourant.recuperercarte(carteTiree);
                if(joueurCourant==tabJoueurs[0]) {
                    nbCartes1.setText("Cartes libération de rattrapages : " + tabJoueurs[0].nbCartes);
                }
                if(joueurCourant==tabJoueurs[1]) {
                    nbCartes2.setText("Cartes libération de rattrapages : " + tabJoueurs[1].nbCartes);
                }
                if(joueurCourant==tabJoueurs[2]) {
                    nbCartes3.setText("Cartes libération de rattrapages : " + tabJoueurs[2].nbCartes);
                }
                if(joueurCourant==tabJoueurs[3]) {
                    nbCartes4.setText("Cartes libération de rattrapages : " + tabJoueurs[4].nbCartes);
                }
                zone_texte_infos.setText(joueurCourant.nom + " vous récupérez une carte Caisse de communauté vous libérant des rattrapages!");
            }
            else if (id==5) {
                joueurCourant.credits = joueurCourant.credits+25;
                zone_texte_infos.setText(joueurCourant.nom + " recevez 25 ECTS!");
            }
            else if (id==6) {
                joueurCourant.credits = joueurCourant.credits-25*joueurCourant.nbMaisonJoueur-100*joueurCourant.nbHotelJoueur;
                zone_texte_infos.setText(joueurCourant.nom + " payez " + 25*joueurCourant.nbMaisonJoueur+100*joueurCourant.nbHotelJoueur + " ECTS!");
            }
            else if (id==7) { // a verifier
                int nbJoueursRestants=0;
                for (int i=0; i<4; i++){ //calcul du nombre de joueurs restants
                    if (tabJoueurs[i] != null) nbJoueursRestants = nbJoueursRestants+1;
                }
                joueurCourant.credits = joueurCourant.credits-15*nbJoueursRestants; //cagnotte du joueur courant qui s'actualise
                zone_texte_infos.setText(joueurCourant.nom + " payez " + 15*nbJoueursRestants + " ECTS!");
                for (int i=0; i<4; i++){
                    if (tabJoueurs[i] != null && tabJoueurs[i] != joueurCourant) {
                        tabJoueurs[i].credits = tabJoueurs[i].credits+15; //credits des autres joueurs qui d'actualisent
                        zone_texte_infos.setText(zone_texte_infos.getText() + "\n" + tabJoueurs[i].nom + " recevez 15 ECTS de la part de " + joueurCourant.nom + "!");
                    }
                }
            }
            else if (id==8) {
                joueurCourant.pion.avancer(8);
                zone_texte_infos.setText(joueurCourant.nom + " avancez de 8 cases (ça souffle)!");
            }
            else if (id==9) {
                joueurCourant.droitdejouer = false;
                zone_texte_infos.setText(joueurCourant.nom + ", vous restez-là (fallait écouter Winston)!");
            }
            else if (id==10) {
                joueurCourant.credits = joueurCourant.credits + 150;
                zone_texte_infos.setText(joueurCourant.nom + " recevez 150 ECTS!");
            }
            else if (id==11) {
                joueurCourant.credits = joueurCourant.credits + 100;
                zone_texte_infos.setText(joueurCourant.nom + " recevez 100 ECTS!");
            }
            else if (id==12) {
                joueurCourant.pion.téléportation(32);
                zone_texte_infos.setText(joueurCourant.nom + " allez en K04 (vive la chimie)!");
            }  //indice 32 du plateau
            else if (id==13) { 
                zone_texte_infos.setText(joueurCourant.nom + " allez au bureau des Ours");
                int indicecase = 0;
                int indicebdo = 35;
                for (int i=0; i<40; i++) {
                    if (plateau.plateaudejeu[i] == joueurCourant.pion.caseassociee) indicecase = i;
                }
                if (indicecase-indicebdo>=0) {
                    joueurCourant.pion.avancer(indicecase-indicebdo);
                }
                else { //indice case est entre 35 et 39
                    joueurCourant.pion.avancer(36+39-indicecase); //36 = départ jusqu'à bdo ; 39-indicecase = nb cases jusqu'à case départ
                }
            }
            else if (id==14) {
                joueurCourant.credits = joueurCourant.credits + 10;
                zone_texte_infos.setText(joueurCourant.nom + " recevez 10 ECTS (et les applaudissements de Jousset)!");
                
            }
            else if (id==15) joueurCourant.credits = joueurCourant.credits + 100;
            zone_texte_infos.setText(joueurCourant.nom + " recevez 100 ECTS!");
        }
        else {
            if (id==0) {
                int nbJoueursRestants=0;
                for (int i=0; i<4; i++){ //calcul du nombre de joueurs restants
                    if (tabJoueurs[i] != null) nbJoueursRestants = nbJoueursRestants+1;
                }
                joueurCourant.credits = joueurCourant.credits+10*nbJoueursRestants; //cagnotte du joueur courant qui s'actualise
                zone_texte_infos.setText(joueurCourant.nom + " recevez " + 10*nbJoueursRestants+ " ECTS (bon anniv)!");
                for (int i=0; i<4; i++){
                    if (tabJoueurs[i] != null && tabJoueurs[i] != joueurCourant) {
                        tabJoueurs[i].credits = tabJoueurs[i].credits-10; //credits des autres joueurs qui d'actualisent
                        zone_texte_infos.setText(zone_texte_infos.getText() + "\n" + tabJoueurs[i].nom + ", vous payez 10 ECTS pour le cadeau de " + joueurCourant.nom + "!");
                    }
                }
            }
            else if (id==1) {
                joueurCourant.pion.reculer(3);
                zone_texte_infos.setText(joueurCourant.nom + " reculez de 3 cases!");
            }
            else if (id==2) {
                joueurCourant.pion.caseassociee = plateau.plateaudejeu[10];
                joueurCourant.prison = true;
                zone_texte_infos.setText(joueurCourant.nom + " allez aux rattrapages!");
            }
            else if (id==3) {
                joueurCourant.pion.téléportation(0);
                zone_texte_infos.setText(joueurCourant.nom + " allez au début d'année!");
                // la bourse n'est pas doublée
            }
            else if (id==4) {
                zone_texte_infos.setText(joueurCourant.nom + " allez en K03 (bonne chance pour le partiel)!");
                int indicecase = 0;
                int indicek3 = 31;
                for (int i=0; i<40; i++) {
                      if (plateau.plateaudejeu[i] == joueurCourant.pion.caseassociee) indicecase = i;
                }
                if (indicecase-indicek3>=0) {
                      joueurCourant.pion.avancer(indicecase-indicek3);
                }
                else { //indice case est entre 31 et 39
                  joueurCourant.pion.avancer(32+39-indicecase); //36 = départ jusqu'à k3 ; 39-indicecase = nb cases jusqu'à case départ
                }
            }
            else if (id==5) {
                joueurCourant.credits = joueurCourant.credits+100;
                zone_texte_infos.setText(joueurCourant.nom + " recevez 100 ECTS!");
            }
            else if (id==6) {
                joueurCourant.credits = joueurCourant.credits+50;
                zone_texte_infos.setText(joueurCourant.nom + " recevez 50 ECTS!");
            }
            else if (id==7) {
                joueurCourant.recuperercarte(carteTiree);
                if(joueurCourant==tabJoueurs[0]) {
                    nbCartes1.setText("Cartes libération de rattrapages : " + tabJoueurs[0].nbCartes);
                }
                if(joueurCourant==tabJoueurs[1]) {
                    nbCartes2.setText("Cartes libération de rattrapages : " + tabJoueurs[1].nbCartes);
                }
                if(joueurCourant==tabJoueurs[2]) {
                    nbCartes3.setText("Cartes libération de rattrapages : " + tabJoueurs[2].nbCartes);
                }
                if(joueurCourant==tabJoueurs[3]) {
                    nbCartes4.setText("Cartes libération de rattrapages : " + tabJoueurs[4].nbCartes);
                }
                zone_texte_infos.setText(joueurCourant.nom + " vous récupérez une carte Chance vous libérant des rattrapages!");
            }
            else if (id==8) {
                joueurCourant.credits = joueurCourant.credits-20;
                argentParcGratuit = argentParcGratuit+20;
                zone_texte_infos.setText(joueurCourant.nom + " payez 20 ECTS (santé)!");
            }
            else if (id==9) {
                joueurCourant.credits = joueurCourant.credits+25;
                zone_texte_infos.setText(joueurCourant.nom + " recevez 25 ECTS!");
            }
            else if (id==10) {
                zone_texte_infos.setText(joueurCourant.nom + " avancez en amphi (vite c'est Mahou qui fait le CM, pas Jousset)!");
                int indicecase = 0;
                int indicek2 = 15;
                for (int i=0; i<40; i++) {
                      if (plateau.plateaudejeu[i] == joueurCourant.pion.caseassociee) indicecase = i;
                }
                if (indicecase-indicek2>=0) {
                      joueurCourant.pion.avancer(indicecase-indicek2);
                }
                else { //indice case est entre 15 et 39
                  joueurCourant.pion.avancer(15+39-indicecase); //15 = départ jusqu'à k2 ; 39-indicecase = nb cases jusqu'à case départ
                  zone_texte_infos.setText(zone_texte_infos.getText() + "\nVous touchez 200 ECTS!");
                }
            }
            else if (id==11) {
                joueurCourant.credits = joueurCourant.credits-25*joueurCourant.nbMaisonJoueur-100*joueurCourant.nbHotelJoueur;
                zone_texte_infos.setText(joueurCourant.nom + " payez " + 25*joueurCourant.nbMaisonJoueur+100*joueurCourant.nbHotelJoueur + " ECTS!");
            }
            else if (id==12) {
                joueurCourant.credits = joueurCourant.credits-40;
                argentParcGratuit = argentParcGratuit+40;
                zone_texte_infos.setText(joueurCourant.nom + " payez 40 ECTS!");
            }
            else if (id==13) { //téléporter tous les joueurs à la case départ
                for (int i=0; i<4; i++){ 
                    if (tabJoueurs[i] != null) {
                        tabJoueurs[i].pion.téléportation(0);
                        zone_texte_infos.setText(tabJoueurs[i].nom + " allez au début d'année!");
                    }
                }
            }
            else if (id==14) {
                joueurCourant.credits = joueurCourant.credits-75;
                argentParcGratuit = argentParcGratuit+75;
                zone_texte_infos.setText(joueurCourant.nom + " payez 75 ECTS (un brelan fait pas le poids fâce au full du Jouset national)!");
            }
        }
    }
     
    public void payerloyer(Case caseassociee) {
        joueurCourant.credits = joueurCourant.credits-caseassociee.loyer;
        caseassociee.proprietaire.credits = caseassociee.proprietaire.credits+caseassociee.loyer;
        credits1.setText("Crédits:" + tabJoueurs[0].credits + " ECTS"); //pour pas s'embêter on met tt à jour au moins c pas compliqué!
        credits2.setText("Crédits:" + tabJoueurs[1].credits + " ECTS");
        credits3.setText("Crédits:" + tabJoueurs[2].credits + " ECTS");
        credits4.setText("Crédits:" + tabJoueurs[3].credits + " ECTS");
        zone_texte_infos.setText(joueurCourant.nom + ", vous payez " + caseassociee.loyer + "ECTS  à " + caseassociee.proprietaire.nom + "!");
    }
    
    public void téléporterPion(Pion pionAssocie, Case caseOuAller) {
        pionAssocie.caseassociee=caseOuAller;
    }
    
    public Carte faireActionCarteSimple(int choix) { // A VERIFIER
        if (choix == 0) { //choix=0 --> perdre des crédits
            joueurCourant.credits = joueurCourant.credits - 10;
            zone_texte_infos.setText(joueurCourant.nom + " payez 10 ECTS!");
            return null;
        }
        else { //choix=1 tirer une carte chance
            Carte laCarte = tirerCarte(paquetChance);
            zone_texte_infos.setText(joueurCourant.nom + " tirez une carte chance!");
            return laCarte;
        }
    }
    
    public boolean faireActionChoix(Case caseChoisie) { // A VERIFIER
        if (caseChoisie == null) {
            return false;
        }
        else {
            caseChoisie.maison = caseChoisie.maison+1; //loyer à changer
            return true;
        }
        
    }
    
    public boolean acheter(Case caseassociee){
        if (caseassociee.proprietaire == null) {
            caseassociee.proprietaire = joueurCourant;
            joueurCourant.credits = joueurCourant.credits-caseassociee.prixTerrain;
            joueurCourant.sallesPossedees.add(caseassociee);
            zone_texte_infos.setText(joueurCourant.nom + ", vous achetez " + caseassociee.nom + " pour " + caseassociee.prixTerrain + "ECTS!");
            if(caseassociee.couleur=="marron") {
                joueurCourant.compterMemesSallesJoueur("marron");
                if(caseassociee.nbSallesMemeCouleur==1) {
                    if(joueurCourant==tabJoueurs[0]) {
                        marron1.setText("1");
                    }
                    if(joueurCourant==tabJoueurs[1]) {
                        marron2.setText("1");
                    }
                    if(joueurCourant==tabJoueurs[2]) {
                        marron3.setText("1");
                    }
                    else {
                        marron4.setText("1");
                    }
                }
                else {
                    caseassociee.changerLoyer();
                    if(caseassociee.idCase==0) {
                        for(int i=0;i<joueurCourant.sallesPossedees.size();i++) {
                            if(joueurCourant.sallesPossedees.get(i).idCase==1) {
                                joueurCourant.sallesPossedees.get(i).changerLoyer();
                            }
                        }
                    }
                    else {
                        for(int i=0;i<joueurCourant.sallesPossedees.size();i++) {
                            if(joueurCourant.sallesPossedees.get(i).idCase==0) {
                                joueurCourant.sallesPossedees.get(i).changerLoyer();
                            }
                        }
                    }
                    if(joueurCourant==tabJoueurs[0]) {
                        marron1.setText("2");
                    }
                    if(joueurCourant==tabJoueurs[1]) {
                        marron2.setText("2");
                    }
                    if(joueurCourant==tabJoueurs[2]) {
                        marron3.setText("2");
                    }
                    else {
                        marron4.setText("2");
                    }
                }
            }
            else if(caseassociee.couleur=="bleu ciel") {
                joueurCourant.compterMemesSallesJoueur("bleu ciel");
                if(caseassociee.nbSallesMemeCouleur==1) {
                    if(joueurCourant==tabJoueurs[0]) {
                        bleuciel1.setText("1");
                    }
                    if(joueurCourant==tabJoueurs[1]) {
                        bleuciel2.setText("1");
                    }
                    if(joueurCourant==tabJoueurs[2]) {
                        bleuciel3.setText("1");
                    }
                    else {
                        bleuciel4.setText("1");
                    }
                }
                else if(caseassociee.nbSallesMemeCouleur==2 ){
                    if(joueurCourant==tabJoueurs[0]) {
                        bleuciel1.setText("2");
                    }
                    if(joueurCourant==tabJoueurs[1]) {
                        bleuciel2.setText("2");
                    }
                    if(joueurCourant==tabJoueurs[2]) {
                        bleuciel3.setText("2");
                    }
                    else {
                        bleuciel4.setText("2");
                    }
                }
                else {
                    caseassociee.changerLoyer();
                    if(caseassociee.idCase==2) {
                        for(int i=0;i<joueurCourant.sallesPossedees.size();i++) {
                            if(joueurCourant.sallesPossedees.get(i).idCase==3) {
                                joueurCourant.sallesPossedees.get(i).changerLoyer();
                            }
                            else if(joueurCourant.sallesPossedees.get(i).idCase==4) {
                                joueurCourant.sallesPossedees.get(i).changerLoyer();
                            }
                        }
                    }
                    else if(caseassociee.idCase==3) {
                        for(int i=0;i<joueurCourant.sallesPossedees.size();i++) {
                            if(joueurCourant.sallesPossedees.get(i).idCase==2) {
                                joueurCourant.sallesPossedees.get(i).changerLoyer();
                            }
                            else if(joueurCourant.sallesPossedees.get(i).idCase==4) {
                                joueurCourant.sallesPossedees.get(i).changerLoyer();
                            }
                        }
                    }
                    else {
                        for(int i=0;i<joueurCourant.sallesPossedees.size();i++) {
                            if(joueurCourant.sallesPossedees.get(i).idCase==2) {
                                joueurCourant.sallesPossedees.get(i).changerLoyer();
                            }
                            else if(joueurCourant.sallesPossedees.get(i).idCase==3) {
                                joueurCourant.sallesPossedees.get(i).changerLoyer();
                            }
                        }
                    }
                    if(joueurCourant==tabJoueurs[0]) {
                        bleuciel1.setText("3");
                    }
                    if(joueurCourant==tabJoueurs[1]) {
                        bleuciel2.setText("3");
                    }
                    if(joueurCourant==tabJoueurs[2]) {
                        bleuciel3.setText("3");
                    }
                    else {
                        bleuciel4.setText("3");
                    }
                }
            }
            else if(caseassociee.couleur=="violet") {
                joueurCourant.compterMemesSallesJoueur("violet");
                if(caseassociee.nbSallesMemeCouleur==1) {
                    if(joueurCourant==tabJoueurs[0]) {
                        violet1.setText("1");
                    }
                    if(joueurCourant==tabJoueurs[1]) {
                        violet2.setText("1");
                    }
                    if(joueurCourant==tabJoueurs[2]) {
                        violet3.setText("1");
                    }
                    else {
                        violet4.setText("1");
                    }
                }
                else if(caseassociee.nbSallesMemeCouleur==2 ){
                    if(joueurCourant==tabJoueurs[0]) {
                        violet1.setText("2");
                    }
                    if(joueurCourant==tabJoueurs[1]) {
                        violet2.setText("2");
                    }
                    if(joueurCourant==tabJoueurs[2]) {
                        violet3.setText("2");
                    }
                    else {
                        violet4.setText("2");
                    }
                }
                else {
                    caseassociee.changerLoyer();
                    if(caseassociee.idCase==5) {
                        for(int i=0;i<joueurCourant.sallesPossedees.size();i++) {
                            if(joueurCourant.sallesPossedees.get(i).idCase==6) {
                                joueurCourant.sallesPossedees.get(i).changerLoyer();
                            }
                            else if(joueurCourant.sallesPossedees.get(i).idCase==7) {
                                joueurCourant.sallesPossedees.get(i).changerLoyer();
                            }
                        }
                    }
                    else if(caseassociee.idCase==6) {
                        for(int i=0;i<joueurCourant.sallesPossedees.size();i++) {
                            if(joueurCourant.sallesPossedees.get(i).idCase==5) {
                                joueurCourant.sallesPossedees.get(i).changerLoyer();
                            }
                            else if(joueurCourant.sallesPossedees.get(i).idCase==7) {
                                joueurCourant.sallesPossedees.get(i).changerLoyer();
                            }
                        }
                    }
                    else {
                        for(int i=0;i<joueurCourant.sallesPossedees.size();i++) {
                            if(joueurCourant.sallesPossedees.get(i).idCase==5) {
                                joueurCourant.sallesPossedees.get(i).changerLoyer();
                            }
                            else if(joueurCourant.sallesPossedees.get(i).idCase==6) {
                                joueurCourant.sallesPossedees.get(i).changerLoyer();
                            }
                        }
                    }
                    if(joueurCourant==tabJoueurs[0]) {
                        violet1.setText("3");
                    }
                    if(joueurCourant==tabJoueurs[1]) {
                        violet2.setText("3");
                    }
                    if(joueurCourant==tabJoueurs[2]) {
                        violet3.setText("3");
                    }
                    else {
                        violet4.setText("3");
                    }
                }
            }
            else if(caseassociee.couleur=="orange") {
                joueurCourant.compterMemesSallesJoueur("orange");
                if(caseassociee.nbSallesMemeCouleur==1) {
                    if(joueurCourant==tabJoueurs[0]) {
                        orange1.setText("1");
                    }
                    if(joueurCourant==tabJoueurs[1]) {
                        orange2.setText("1");
                    }
                    if(joueurCourant==tabJoueurs[2]) {
                        orange3.setText("1");
                    }
                    else {
                        orange4.setText("1");
                    }
                }
                else if(caseassociee.nbSallesMemeCouleur==2 ){
                    if(joueurCourant==tabJoueurs[0]) {
                        orange1.setText("2");
                    }
                    if(joueurCourant==tabJoueurs[1]) {
                        orange2.setText("2");
                    }
                    if(joueurCourant==tabJoueurs[2]) {
                        orange3.setText("2");
                    }
                    else {
                        orange4.setText("2");
                    }
                }
                else {
                    caseassociee.changerLoyer();
                    if(caseassociee.idCase==8) {
                        for(int i=0;i<joueurCourant.sallesPossedees.size();i++) {
                            if(joueurCourant.sallesPossedees.get(i).idCase==9) {
                                joueurCourant.sallesPossedees.get(i).changerLoyer();
                            }
                            else if(joueurCourant.sallesPossedees.get(i).idCase==10) {
                                joueurCourant.sallesPossedees.get(i).changerLoyer();
                            }
                        }
                    }
                    else if(caseassociee.idCase==9) {
                        for(int i=0;i<joueurCourant.sallesPossedees.size();i++) {
                            if(joueurCourant.sallesPossedees.get(i).idCase==8) {
                                joueurCourant.sallesPossedees.get(i).changerLoyer();
                            }
                            else if(joueurCourant.sallesPossedees.get(i).idCase==10) {
                                joueurCourant.sallesPossedees.get(i).changerLoyer();
                            }
                        }
                    }
                    else {
                        for(int i=0;i<joueurCourant.sallesPossedees.size();i++) {
                            if(joueurCourant.sallesPossedees.get(i).idCase==8) {
                                joueurCourant.sallesPossedees.get(i).changerLoyer();
                            }
                            else if(joueurCourant.sallesPossedees.get(i).idCase==9) {
                                joueurCourant.sallesPossedees.get(i).changerLoyer();
                            }
                        }
                    }
                    if(joueurCourant==tabJoueurs[0]) {
                        orange1.setText("3");
                    }
                    if(joueurCourant==tabJoueurs[1]) {
                        orange2.setText("3");
                    }
                    if(joueurCourant==tabJoueurs[2]) {
                        orange3.setText("3");
                    }
                    else {
                        orange4.setText("3");
                    }
                }
            }
            else if(caseassociee.couleur=="rouge") {
                joueurCourant.compterMemesSallesJoueur("rouge");
                if(caseassociee.nbSallesMemeCouleur==1) {
                    if(joueurCourant==tabJoueurs[0]) {
                        rouge1.setText("1");
                    }
                    if(joueurCourant==tabJoueurs[1]) {
                        rouge2.setText("1");
                    }
                    if(joueurCourant==tabJoueurs[2]) {
                        rouge3.setText("1");
                    }
                    else {
                        rouge4.setText("1");
                    }
                }
                else if(caseassociee.nbSallesMemeCouleur==2 ){
                    if(joueurCourant==tabJoueurs[0]) {
                        rouge1.setText("2");
                    }
                    if(joueurCourant==tabJoueurs[1]) {
                        rouge2.setText("2");
                    }
                    if(joueurCourant==tabJoueurs[2]) {
                        rouge3.setText("2");
                    }
                    else {
                        rouge4.setText("2");
                    }
                }
                else {
                    caseassociee.changerLoyer();
                    if(caseassociee.idCase==11) {
                        for(int i=0;i<joueurCourant.sallesPossedees.size();i++) {
                            if(joueurCourant.sallesPossedees.get(i).idCase==12) {
                                joueurCourant.sallesPossedees.get(i).changerLoyer();
                            }
                            else if(joueurCourant.sallesPossedees.get(i).idCase==12) {
                                joueurCourant.sallesPossedees.get(i).changerLoyer();
                            }
                        }
                    }
                    else if(caseassociee.idCase==12) {
                        for(int i=0;i<joueurCourant.sallesPossedees.size();i++) {
                            if(joueurCourant.sallesPossedees.get(i).idCase==11) {
                                joueurCourant.sallesPossedees.get(i).changerLoyer();
                            }
                            else if(joueurCourant.sallesPossedees.get(i).idCase==13) {
                                joueurCourant.sallesPossedees.get(i).changerLoyer();
                            }
                        }
                    }
                    else {
                        for(int i=0;i<joueurCourant.sallesPossedees.size();i++) {
                            if(joueurCourant.sallesPossedees.get(i).idCase==11) {
                                joueurCourant.sallesPossedees.get(i).changerLoyer();
                            }
                            else if(joueurCourant.sallesPossedees.get(i).idCase==12) {
                                joueurCourant.sallesPossedees.get(i).changerLoyer();
                            }
                        }
                    }
                    if(joueurCourant==tabJoueurs[0]) {
                        rouge1.setText("3");
                    }
                    if(joueurCourant==tabJoueurs[1]) {
                        rouge2.setText("3");
                    }
                    if(joueurCourant==tabJoueurs[2]) {
                        rouge3.setText("3");
                    }
                    else {
                        rouge4.setText("3");
                    }
                }
            }
            else if(caseassociee.couleur=="jaune") {
                joueurCourant.compterMemesSallesJoueur("jaune");
                if(caseassociee.nbSallesMemeCouleur==1) {
                    if(joueurCourant==tabJoueurs[0]) {
                        jaune1.setText("1");
                    }
                    if(joueurCourant==tabJoueurs[1]) {
                        jaune2.setText("1");
                    }
                    if(joueurCourant==tabJoueurs[2]) {
                        jaune3.setText("1");
                    }
                    else {
                        jaune4.setText("1");
                    }
                }
                else if(caseassociee.nbSallesMemeCouleur==2 ){
                    if(joueurCourant==tabJoueurs[0]) {
                        jaune1.setText("2");
                    }
                    if(joueurCourant==tabJoueurs[1]) {
                        jaune2.setText("2");
                    }
                    if(joueurCourant==tabJoueurs[2]) {
                        jaune3.setText("2");
                    }
                    else {
                        jaune4.setText("2");
                    }
                }
                else {
                    caseassociee.changerLoyer();
                    if(caseassociee.idCase==14) {
                        for(int i=0;i<joueurCourant.sallesPossedees.size();i++) {
                            if(joueurCourant.sallesPossedees.get(i).idCase==15) {
                                joueurCourant.sallesPossedees.get(i).changerLoyer();
                            }
                            else if(joueurCourant.sallesPossedees.get(i).idCase==16) {
                                joueurCourant.sallesPossedees.get(i).changerLoyer();
                            }
                        }
                    }
                    else if(caseassociee.idCase==15) {
                        for(int i=0;i<joueurCourant.sallesPossedees.size();i++) {
                            if(joueurCourant.sallesPossedees.get(i).idCase==14) {
                                joueurCourant.sallesPossedees.get(i).changerLoyer();
                            }
                            else if(joueurCourant.sallesPossedees.get(i).idCase==16) {
                                joueurCourant.sallesPossedees.get(i).changerLoyer();
                            }
                        }
                    }
                    else {
                        for(int i=0;i<joueurCourant.sallesPossedees.size();i++) {
                            if(joueurCourant.sallesPossedees.get(i).idCase==14) {
                                joueurCourant.sallesPossedees.get(i).changerLoyer();
                            }
                            else if(joueurCourant.sallesPossedees.get(i).idCase==15) {
                                joueurCourant.sallesPossedees.get(i).changerLoyer();
                            }
                        }
                    }
                    if(joueurCourant==tabJoueurs[0]) {
                        jaune1.setText("3");
                    }
                    if(joueurCourant==tabJoueurs[1]) {
                        jaune2.setText("3");
                    }
                    if(joueurCourant==tabJoueurs[2]) {
                        jaune3.setText("3");
                    }
                    else {
                        jaune4.setText("3");
                    }
                }
            }
            else if(caseassociee.couleur=="vert") {
                joueurCourant.compterMemesSallesJoueur("vert");
                if(caseassociee.nbSallesMemeCouleur==1) {
                    if(joueurCourant==tabJoueurs[0]) {
                        vert1.setText("1");
                    }
                    if(joueurCourant==tabJoueurs[1]) {
                        vert2.setText("1");
                    }
                    if(joueurCourant==tabJoueurs[2]) {
                        vert3.setText("1");
                    }
                    else {
                        vert4.setText("1");
                    }
                }
                else if(caseassociee.nbSallesMemeCouleur==2 ){
                    if(joueurCourant==tabJoueurs[0]) {
                        vert1.setText("2");
                    }
                    if(joueurCourant==tabJoueurs[1]) {
                        vert2.setText("2");
                    }
                    if(joueurCourant==tabJoueurs[2]) {
                        vert3.setText("2");
                    }
                    else {
                        vert4.setText("2");
                    }
                }
                else {
                    caseassociee.changerLoyer();
                    if(caseassociee.idCase==17) {
                        for(int i=0;i<joueurCourant.sallesPossedees.size();i++) {
                            if(joueurCourant.sallesPossedees.get(i).idCase==18) {
                                joueurCourant.sallesPossedees.get(i).changerLoyer();
                            }
                            else if(joueurCourant.sallesPossedees.get(i).idCase==19) {
                                joueurCourant.sallesPossedees.get(i).changerLoyer();
                            }
                        }
                    }
                    else if(caseassociee.idCase==18) {
                        for(int i=0;i<joueurCourant.sallesPossedees.size();i++) {
                            if(joueurCourant.sallesPossedees.get(i).idCase==17) {
                                joueurCourant.sallesPossedees.get(i).changerLoyer();
                            }
                            else if(joueurCourant.sallesPossedees.get(i).idCase==19) {
                                joueurCourant.sallesPossedees.get(i).changerLoyer();
                            }
                        }
                    }
                    else {
                        for(int i=0;i<joueurCourant.sallesPossedees.size();i++) {
                            if(joueurCourant.sallesPossedees.get(i).idCase==17) {
                                joueurCourant.sallesPossedees.get(i).changerLoyer();
                            }
                            else if(joueurCourant.sallesPossedees.get(i).idCase==18) {
                                joueurCourant.sallesPossedees.get(i).changerLoyer();
                            }
                        }
                    }
                    if(joueurCourant==tabJoueurs[0]) {
                        vert1.setText("3");
                    }
                    if(joueurCourant==tabJoueurs[1]) {
                        vert2.setText("3");
                    }
                    if(joueurCourant==tabJoueurs[2]) {
                        vert3.setText("3");
                    }
                    else {
                        vert4.setText("3");
                    }
                }
            }
            else if(caseassociee.couleur=="bleu foncé") {
                joueurCourant.compterMemesSallesJoueur("bleu foncé");
                if(caseassociee.nbSallesMemeCouleur==1) {
                    if(joueurCourant==tabJoueurs[0]) {
                        bleufoncé1.setText("1");
                    }
                    if(joueurCourant==tabJoueurs[1]) {
                        bleufoncé2.setText("1");
                    }
                    if(joueurCourant==tabJoueurs[2]) {
                        bleufoncé3.setText("1");
                    }
                    else {
                        bleufoncé4.setText("1");
                    }
                }
                else {
                    caseassociee.changerLoyer();
                    if(caseassociee.idCase==20) {
                        for(int i=0;i<joueurCourant.sallesPossedees.size();i++) {
                            if(joueurCourant.sallesPossedees.get(i).idCase==21) {
                                joueurCourant.sallesPossedees.get(i).changerLoyer();
                            }
                        }
                    }
                    else {
                        for(int i=0;i<joueurCourant.sallesPossedees.size();i++) {
                            if(joueurCourant.sallesPossedees.get(i).idCase==20) {
                                joueurCourant.sallesPossedees.get(i).changerLoyer();
                            }
                        }
                    }
                    if(joueurCourant==tabJoueurs[0]) {
                        bleufoncé1.setText("2");
                    }
                    if(joueurCourant==tabJoueurs[1]) {
                        bleufoncé2.setText("2");
                    }
                    if(joueurCourant==tabJoueurs[2]) {
                        bleufoncé3.setText("2");
                    }
                    else {
                        bleufoncé4.setText("2");
                    }
                }
            }
            else if(caseassociee.couleur=="gare") {
                joueurCourant.compterMemesSallesJoueur("gare");
                joueurCourant.nbAutres++;
                if(joueurCourant==tabJoueurs[0]) {
                    Autres1.setText("Autre(s):" + tabJoueurs[0].nbAutres);
                }
                else if(joueurCourant==tabJoueurs[1]) {
                    Autres2.setText("Autre(s):" + tabJoueurs[1].nbAutres);
                }
                else if(joueurCourant==tabJoueurs[2]) {
                    Autres3.setText("Autre(s):" + tabJoueurs[2].nbAutres);
                }
                else {
                    Autres4.setText("Autre(s):" + tabJoueurs[3].nbAutres);
                }
                if(caseassociee.nbSallesMemeCouleur==2) {
                    caseassociee.loyer=50;
                    if(caseassociee.idCase==22) {
                        for(int i=0;i<joueurCourant.sallesPossedees.size();i++) {
                            if(joueurCourant.sallesPossedees.get(i).idCase==23) {
                                joueurCourant.sallesPossedees.get(i).loyer=50;
                            }
                            else if(joueurCourant.sallesPossedees.get(i).idCase==24) {
                                joueurCourant.sallesPossedees.get(i).loyer=50;
                            }
                            else if(joueurCourant.sallesPossedees.get(i).idCase==25) {
                                joueurCourant.sallesPossedees.get(i).loyer=50;
                            }
                        }
                    }
                    else if(caseassociee.idCase==23) {
                        for(int i=0;i<joueurCourant.sallesPossedees.size();i++) {
                            if(joueurCourant.sallesPossedees.get(i).idCase==22) {
                                joueurCourant.sallesPossedees.get(i).loyer=50;
                            }
                            else if(joueurCourant.sallesPossedees.get(i).idCase==24) {
                                joueurCourant.sallesPossedees.get(i).loyer=50;
                            }
                            else if(joueurCourant.sallesPossedees.get(i).idCase==25) {
                                joueurCourant.sallesPossedees.get(i).loyer=50;
                            }
                        }
                    }
                    else if(caseassociee.idCase==24) {
                        for(int i=0;i<joueurCourant.sallesPossedees.size();i++) {
                            if(joueurCourant.sallesPossedees.get(i).idCase==22) {
                                joueurCourant.sallesPossedees.get(i).loyer=50;
                            }
                            else if(joueurCourant.sallesPossedees.get(i).idCase==23) {
                                joueurCourant.sallesPossedees.get(i).loyer=50;
                            }
                            else if(joueurCourant.sallesPossedees.get(i).idCase==25) {
                                joueurCourant.sallesPossedees.get(i).loyer=50;
                            }
                        }
                    }
                    else {
                        for(int i=0;i<joueurCourant.sallesPossedees.size();i++) {
                            if(joueurCourant.sallesPossedees.get(i).idCase==22) {
                                joueurCourant.sallesPossedees.get(i).loyer=50;
                            }
                            else if(joueurCourant.sallesPossedees.get(i).idCase==23) {
                                joueurCourant.sallesPossedees.get(i).loyer=50;
                            }
                            else if(joueurCourant.sallesPossedees.get(i).idCase==24) {
                                joueurCourant.sallesPossedees.get(i).loyer=50;
                            }
                        }
                    }
                }
                else if(caseassociee.nbSallesMemeCouleur==3) {
                    caseassociee.loyer=100;
                    if(caseassociee.idCase==22) {
                        for(int i=0;i<joueurCourant.sallesPossedees.size();i++) {
                            if(joueurCourant.sallesPossedees.get(i).idCase==23) {
                                joueurCourant.sallesPossedees.get(i).loyer=100;
                            }
                            else if(joueurCourant.sallesPossedees.get(i).idCase==24) {
                                joueurCourant.sallesPossedees.get(i).loyer=100;
                            }
                            else if(joueurCourant.sallesPossedees.get(i).idCase==25) {
                                joueurCourant.sallesPossedees.get(i).loyer=100;
                            }
                        }
                    }
                    else if(caseassociee.idCase==23) {
                        for(int i=0;i<joueurCourant.sallesPossedees.size();i++) {
                            if(joueurCourant.sallesPossedees.get(i).idCase==22) {
                                joueurCourant.sallesPossedees.get(i).loyer=100;
                            }
                            else if(joueurCourant.sallesPossedees.get(i).idCase==24) {
                                joueurCourant.sallesPossedees.get(i).loyer=100;
                            }
                            else if(joueurCourant.sallesPossedees.get(i).idCase==25) {
                                joueurCourant.sallesPossedees.get(i).loyer=100;
                            }
                        }
                    }
                    else if(caseassociee.idCase==24) {
                        for(int i=0;i<joueurCourant.sallesPossedees.size();i++) {
                            if(joueurCourant.sallesPossedees.get(i).idCase==22) {
                                joueurCourant.sallesPossedees.get(i).loyer=100;
                            }
                            else if(joueurCourant.sallesPossedees.get(i).idCase==23) {
                                joueurCourant.sallesPossedees.get(i).loyer=100;
                            }
                            else if(joueurCourant.sallesPossedees.get(i).idCase==25) {
                                joueurCourant.sallesPossedees.get(i).loyer=100;
                            }
                        }
                    }
                    else {
                        for(int i=0;i<joueurCourant.sallesPossedees.size();i++) {
                            if(joueurCourant.sallesPossedees.get(i).idCase==22) {
                                joueurCourant.sallesPossedees.get(i).loyer=100;
                            }
                            else if(joueurCourant.sallesPossedees.get(i).idCase==23) {
                                joueurCourant.sallesPossedees.get(i).loyer=100;
                            }
                            else if(joueurCourant.sallesPossedees.get(i).idCase==24) {
                                joueurCourant.sallesPossedees.get(i).loyer=100;
                            }
                        }
                    }
                }
                else if(caseassociee.nbSallesMemeCouleur==4) {
                    caseassociee.loyer=200;
                    if(caseassociee.idCase==22) {
                        for(int i=0;i<joueurCourant.sallesPossedees.size();i++) {
                            if(joueurCourant.sallesPossedees.get(i).idCase==23) {
                                joueurCourant.sallesPossedees.get(i).loyer=200;
                            }
                            else if(joueurCourant.sallesPossedees.get(i).idCase==24) {
                                joueurCourant.sallesPossedees.get(i).loyer=200;
                            }
                            else if(joueurCourant.sallesPossedees.get(i).idCase==25) {
                                joueurCourant.sallesPossedees.get(i).loyer=200;
                            }
                        }
                    }
                    else if(caseassociee.idCase==23) {
                        for(int i=0;i<joueurCourant.sallesPossedees.size();i++) {
                            if(joueurCourant.sallesPossedees.get(i).idCase==22) {
                                joueurCourant.sallesPossedees.get(i).loyer=200;
                            }
                            else if(joueurCourant.sallesPossedees.get(i).idCase==24) {
                                joueurCourant.sallesPossedees.get(i).loyer=200;
                            }
                            else if(joueurCourant.sallesPossedees.get(i).idCase==25) {
                                joueurCourant.sallesPossedees.get(i).loyer=200;
                            }
                        }
                    }
                    else if(caseassociee.idCase==24) {
                        for(int i=0;i<joueurCourant.sallesPossedees.size();i++) {
                            if(joueurCourant.sallesPossedees.get(i).idCase==22) {
                                joueurCourant.sallesPossedees.get(i).loyer=200;
                            }
                            else if(joueurCourant.sallesPossedees.get(i).idCase==23) {
                                joueurCourant.sallesPossedees.get(i).loyer=200;
                            }
                            else if(joueurCourant.sallesPossedees.get(i).idCase==25) {
                                joueurCourant.sallesPossedees.get(i).loyer=200;
                            }
                        }
                    }
                    else {
                        for(int i=0;i<joueurCourant.sallesPossedees.size();i++) {
                            if(joueurCourant.sallesPossedees.get(i).idCase==22) {
                                joueurCourant.sallesPossedees.get(i).loyer=200;
                            }
                            else if(joueurCourant.sallesPossedees.get(i).idCase==23) {
                                joueurCourant.sallesPossedees.get(i).loyer=200;
                            }
                            else if(joueurCourant.sallesPossedees.get(i).idCase==24) {
                                joueurCourant.sallesPossedees.get(i).loyer=200;
                            }
                        }
                    }
                }
            }
            else if(caseassociee.couleur=="Compagnie") {
                joueurCourant.compterMemesSallesJoueur("Compagnie");
                joueurCourant.nbAutres++;
                if(joueurCourant==tabJoueurs[0]) {
                    Autres1.setText("Autre(s):" + tabJoueurs[0].nbAutres);
                }
                else if(joueurCourant==tabJoueurs[1]) {
                    Autres2.setText("Autre(s):" + tabJoueurs[1].nbAutres);
                }
                else if(joueurCourant==tabJoueurs[2]) {
                    Autres3.setText("Autre(s):" + tabJoueurs[2].nbAutres);
                }
                else {
                    Autres4.setText("Autre(s):" + tabJoueurs[3].nbAutres);
                }
                if(caseassociee.nbSallesMemeCouleur==2) {
                    caseassociee.loyer=20;
                    if(caseassociee.idCase==26){
                        for(int i=0;i<joueurCourant.sallesPossedees.size();i++) {
                            if(joueurCourant.sallesPossedees.get(i).idCase==27) {
                                joueurCourant.sallesPossedees.get(i).loyer=20;
                            }
                        }
                    }
                    else if(caseassociee.idCase==27){
                        for(int i=0;i<joueurCourant.sallesPossedees.size();i++) {
                            if(joueurCourant.sallesPossedees.get(i).idCase==26) {
                                joueurCourant.sallesPossedees.get(i).loyer=20;
                            }
                        }
                    }
                }
            }
            return true;
        }
        else {
            return false;
        }
    }
    
    
    //-----------------------------------
    
    public void InitialiserPartie() {
        tabJoueurs[0] = new Joueur();
        tabJoueurs[1] = new Joueur();
        tabJoueurs[2] = new Joueur();
        tabJoueurs[3] = new Joueur();
        de1 = new Des();
        de2 = new Des();
        Payer_Jousset.setVisible(false);
        Matieres_possedees.setVisible(false);
        Lancer_des.setEnabled(false);
        marron1.setVisible(false);
        bleuciel1.setVisible(false);
        violet1.setVisible(false);
        orange1.setVisible(false);
        rouge1.setVisible(false);
        jaune1.setVisible(false);
        vert1.setVisible(false);
        bleufoncé1.setVisible(false);
        marron2.setVisible(false);
        bleuciel2.setVisible(false);
        violet2.setVisible(false);
        orange2.setVisible(false);
        rouge2.setVisible(false);
        jaune2.setVisible(false);
        vert2.setVisible(false);
        bleufoncé2.setVisible(false);
        marron3.setVisible(false);
        bleuciel3.setVisible(false);
        violet3.setVisible(false);
        orange3.setVisible(false);
        rouge3.setVisible(false);
        jaune3.setVisible(false);
        vert3.setVisible(false);
        bleufoncé3.setVisible(false);
        marron4.setVisible(false);
        bleuciel4.setVisible(false);
        violet4.setVisible(false);
        orange4.setVisible(false);
        rouge4.setVisible(false);
        jaune4.setVisible(false);
        vert4.setVisible(false);
        bleufoncé4.setEnabled(false);
        bleufoncé4.setVisible(false);
        SallesPossedees1.setVisible(false);
        SallesPossedees2.setVisible(false);
        SallesPossedees3.setVisible(false);
        SallesPossedees4.setVisible(false);
        Salles.setEnabled(false);
        credits1.setVisible(false);
        credits2.setVisible(false);
        credits3.setVisible(false);
        credits4.setVisible(false);
        nbCartes1.setVisible(false);
        nbCartes2.setVisible(false);
        nbCartes3.setVisible(false);
        nbCartes4.setVisible(false);
        tabJoueurs[0].credits = 1500;
        tabJoueurs[1].credits = 1500;
        tabJoueurs[2].credits = 1500;
        tabJoueurs[3].credits = 1500;
        credits1.setText("Crédits : " + tabJoueurs[0].credits + " ECTS");
        credits2.setText("Crédits : " + tabJoueurs[1].credits + " ECTS");
        credits3.setText("Crédits : " + tabJoueurs[2].credits + " ECTS");
        credits4.setText("Crédits : " + tabJoueurs[3].credits + " ECTS");
        nbCartes1.setText("Cartes libération de rattrapages : " + tabJoueurs[0].nbCartes);
        nbCartes2.setText("Cartes libération de rattrapages : " + tabJoueurs[1].nbCartes);
        nbCartes3.setText("Cartes libération de rattrapages : " + tabJoueurs[2].nbCartes);
        nbCartes4.setText("Cartes libération de rattrapages : " + tabJoueurs[3].nbCartes);
        Libération1.setVisible(false);
        Libération2.setVisible(false);
        Libération3.setVisible(false);
        Libération4.setVisible(false);
        PayerPrison1.setVisible(false);
        PayerPrison2.setVisible(false);
        PayerPrison3.setVisible(false);
        PayerPrison4.setVisible(false);
        Acheter1.setVisible(false);
        Acheter2.setVisible(false);
        Acheter3.setVisible(false);
        Acheter4.setVisible(false);
        ValiderMatiere1.setVisible(false);
        ValiderMatiere2.setVisible(false);
        ValiderMatiere3.setVisible(false);
        ValiderMatiere4.setVisible(false);
        Autres1.setVisible(false);
        Autres2.setVisible(false);
        Autres3.setVisible(false);
        Autres4.setVisible(false);
    }
}
