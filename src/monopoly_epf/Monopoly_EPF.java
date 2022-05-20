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
import java.io.File;
import java.util.Random;
import java.util.TimerTask;
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
import javax.swing.SwingConstants;
import javax.swing.Timer;

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
    int currentFrame = 0;
    Timer monChrono = null;
    int indiceouAller = 0;
    int indiceouOnEst = 0;
    int inc = 0;
    int nbFrames =0;
    int déplacement=0;
    Case choixchance;
    int choixcom=2;
    String carte = "";
    String cartespéciale="";
    boolean negatif=false;
    int compteur=0;
    int compteurlocal=0;
    boolean mini=false;
    boolean tripledouble=false;
    boolean partiefinie=false;
    boolean elim=false;
    boolean darties = false;
    
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
        infos_joueurs1.setBackground(new Color(218,91,93));
        infos_joueurs1.setBorder(BorderFactory.createMatteBorder(2,2,2,2, Color.BLACK));
        add(infos_joueurs1, new org.netbeans.lib.awtextra.AbsoluteConstraints(5,200,355,200));
        infos_joueurs1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        this.repaint();
        
        infos_joueurs2 = new JPanel();
        infos_joueurs2.setSize(355,200);
        infos_joueurs2.setBackground(new Color(218 ,91,93));
        infos_joueurs2.setBorder(BorderFactory.createMatteBorder(2,2,2,2, Color.BLACK));
        add(infos_joueurs2, new org.netbeans.lib.awtextra.AbsoluteConstraints(5,460,355,200));
        infos_joueurs2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        this.repaint();
        
        infos_joueurs3 = new JPanel();
        infos_joueurs3.setSize(355,200);
        infos_joueurs3.setBackground(new Color(218,91,93));
        infos_joueurs3.setBorder(BorderFactory.createMatteBorder(2,2,2,2, Color.BLACK));
        add(infos_joueurs3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1174,200,355,200));
        infos_joueurs3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        this.repaint();
        
        infos_joueurs4 = new JPanel();
        infos_joueurs4.setSize(355,200);
        infos_joueurs4.setBackground(new Color(218,91,93));
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
        Regles.setBackground(new Color(226,252,251));
        Regles.setText("Règles");
        Regles.setFont(new Font("Règles", Font.BOLD, 16));
        this.repaint();
        Regles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JFrame FrameRegles = new JFrame("Règles du jeu");
                FrameRegles.setBounds(325, 0, 900, 900);
                FrameRegles.setVisible(true);
                FrameRegles.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
                FrameRegles.add(Regles_pane,new org.netbeans.lib.awtextra.AbsoluteConstraints(0,30,-1,-1));
                FrameRegles.add(Regles_LabelTitre,new org.netbeans.lib.awtextra.AbsoluteConstraints(350,0,-1,-1));
                
            }
        });
        
        
        Regles_LabelTitre = new JLabel("Regles du jeu");
        Regles_LabelTitre.setFont(new Font("Monopoly", Font.BOLD, 30));
        Regles_LabelTitre.setForeground(Color.red);
        
        
        Regles_Text = new JTextArea();
        Regles_Text.setColumns(67);
        Regles_Text.setRows(53);
        Regles_Text.setWrapStyleWord(true);
        Regles_Text.setLineWrap(true);
        Regles_Text.setText(
"Nombre de joueurs : 4\n\n"+

"Duree : 1h a 2h30\n\n"+

"- Ce jeu consiste a ACHETER et a DISPENSER des cours dans diverses salles de facon si profitable que l’on puisse devenir le joueur possedant le plus de credits et eventuellement, le GAGNANT. Des que le nombre de credit d’un joueur est negatif il est elimine du jeu et ses proprietes sont de nouveau disponibles. Le gagnant est le dernier encore debout.\n"+

"- Debut d’annee est le point de depart, et l’on fait avancer les pions sur le plateau de jeu d’apres le chiffre indique en lancant les des.\n"+

"- Si le pion d’un joueur se place sur une case qui n’appartient encore a personne, le joueur pourra l’acheter.\n"+

"- Devenir proprietaire a pour OBJECTIF la perception de credits des adversaires que le proprietaire aide en dispensant des cours sur la case qui represente la propriete. Les prix des cours sont fortement augmentes par l’obtention de matieres et de modules (voir emplacements pour les montants)\n\n"+

"Debut de partie :\n\n"+

"- Chaque joueur recoit 1500 credits.\n"+

"- Les joueurs jouent un par un dans le sens des aiguilles d’une montre en commencant par lancer le de.\n"+

"- Le pion du joueur avance, dans le sens horaire, d’autant de cases que l’aura indique le nombre total des des.\n"+

"- Les PIONS restent sur les cases qu’ils occupent et repartent au tour suivant.\n"+

"- Il peut y avoir, en meme temps, plus d’un pion sur la meme case.\n"+

"- Si une case est atteinte par son pion, un joueur peut avoir le droit d’acheter la salle ou etre force de payer un cours (si la propriéte appartient a un autre), de payer des taxes, de prendre une carte des paquets Chance ou Caisse de Communauté d’aller aux rattrapages, etc.\n"+

"- Si un joueur obtient un doublet, il avance son pion, comme d’habitude, du total represente par les deux des, et beneficie de tous les privileges ou doit subir toutes les penalites relatives a la case ainsi atteinte. Il beneficie d’un second lance, avance son pion comme precedemment et la case ainsi atteinte le soumet de nouveau a tous ses effets.\n"+

"- Si, cependant, il obtient un doublet trois fois de suite, il devra immediatement ALLER AUX RATTRAPAGES.\n"+

"- Les joueurs, durant la partie, feront plusieurs fois le tour du plateau de jeu. Chaque fois que le pion s’arrete sur la case Debut D’annee il recoit, pour sa bourse, 400 credits s’il la depasse il recevra 200 credits.\n"+

"- Il est avantageux de detenir des titres de propriete sur la TOTALITE d’un groupe complet de meme couleur car le prix du cours dispense s’en voit augmente.\n"+

"- Si le joueur tombe sur les cartes chances ou communautes, il applique ce qui est ecrit sur la carte. Si c’est une carte Libere de Rattrapages, il garde la carte et pourra l’utiliser ou non s’il est envoye aux rattrapages.\n"+

"- QUAND LE PION ATTEINT UNE CASE Taxe, il faut payer le montant indique.\n\n"+

"- RATTRAPAGES : Un Joueur y est envoye :\n\n"+

"1) Si son pion atteint la case ALLEZ AUX RATTRAPAGES (il ne touche pas la bourse de la case debut d’annee)\n"+

"2) S’il tire une carte ALLEZ AUX RATTRAPAGES\n"+

"3) S’il obtient trois doublets de suite\n"+

"Il reste bloque 3 tours aux rattrapages. Il a 1 chance par tour de faire un double et de sortir et au 3eme tour il est oblige de payer 50 credits et de jouer.");
        
        Regles_Text.setEditable(false);
        Regles_Text.setFont(new Font("Monopoly", Font.PLAIN, 17));
        
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
        
        zone_texte_infos = new JTextArea();
        zone_texte_infos.setBounds(10, 25, 335, 155);
        infos_partie.add(zone_texte_infos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10,25,335,155));
        zone_texte_infos.setEditable(false);
        zone_texte_infos.setWrapStyleWord(true);
        zone_texte_infos.setLineWrap(true);
        zone_texte_infos.setText("Veuillez rentrer les quatre pseudos puis cliquer sur le bouton démarrer la partie ! Attention, si vous ne rentrez rien, les joueurs n'auront pas de pseudo.");
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
        Démarrer.setBackground(new Color(226,252,251));
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
                plateauJeu.afficherPions=true;
                plateauJeu.repaint();
                Lancer_des.setEnabled(true);
                //joueurCourant = tabJoueurs[0];
                Random generateur = new Random(); //génère un nombre
                int nbaleat = generateur.nextInt(4); //entre 0 et 3 compris
                joueurCourant = tabJoueurs[nbaleat];
                zone_texte_infos.setText("Joueur 1, votre pseudo est " + tabJoueurs[0].nom + " et votre pion est la Calculatrice!" + "\nJoueur 2, votre pseudo est " + tabJoueurs[1].nom + " et votre pion est la Diode!" + "\nJoueur 3, votre pseudo est " + tabJoueurs[2].nom + " et votre pion est l'Erlenmeyer!" + "\nJoueur 4, votre pseudo est " + tabJoueurs[3].nom + " et votre pion est Olga!\n" + joueurCourant.nom + " vous commencez!");
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
        
        Libération1 = new JButton();
        Libération1.setBounds(180, 115, 170, 25);
        Libération1.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
        infos_joueurs1.add(Libération1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 115, 170, 25));
        Libération1.setBackground(Color.LIGHT_GRAY);
        Libération1.setText("Carte libération de rattrapages");
        Libération1.setFont(new Font("Carte libération de rattrapages", Font.BOLD, 10));
        Libération1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tabJoueurs[0].prison=false;
                tabJoueurs[0].compteurTourPrison=0;
                for(int i=0;i<tabJoueurs[0].tabCartes.length;i++) {
                    if(tabJoueurs[0].tabCartes[i]!=null) {
                        tabJoueurs[0].tabCartes[i]=null;
                        break;
                    }
                }
                tabJoueurs[0].nbCartes--;
                nbCartes1.setText("Cartes libération de rattrapages : " + tabJoueurs[0].nbCartes);
                tabJoueurs[0].pion.coordX=61;
                tabJoueurs[0].pion.coordY=2;
                tabJoueurs[0].pion.taille=24;
                plateauJeu.orientation1=plateauJeu.haut1;
                Libération1.setVisible(false);
                PayerPrison1.setVisible(false);
                plateauJeu.repaint();
                zone_texte_infos.setText(tabJoueurs[0].nom + " vous utilisez une carte vous libérant des rattrapages, lancez les dés et faites en sorte de valider vos matières la prochaine fois!");
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
                tabJoueurs[1].prison=false;
                tabJoueurs[1].compteurTourPrison=0;
                for(int i=0;i<tabJoueurs[1].tabCartes.length;i++) {
                    if(tabJoueurs[1].tabCartes[i]!=null) {
                        tabJoueurs[1].tabCartes[i]=null;
                        break;
                    }
                }
                tabJoueurs[1].nbCartes--;
                nbCartes2.setText("Cartes libération de rattrapages : " + tabJoueurs[1].nbCartes);
                tabJoueurs[1].pion.coordX=18;
                tabJoueurs[1].pion.coordY=2;
                tabJoueurs[1].pion.taille=24;
                plateauJeu.orientation2=plateauJeu.haut2;
                Libération2.setVisible(false);
                PayerPrison2.setVisible(false);
                plateauJeu.repaint();
                zone_texte_infos.setText(tabJoueurs[1].nom + " vous utilisez une carte vous libérant des rattrapages, lancez les dés et faites en sorte de valider vos matières la prochaine fois!");
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
                tabJoueurs[2].prison=false;
                tabJoueurs[2].compteurTourPrison=0;
                for(int i=0;i<tabJoueurs[2].tabCartes.length;i++) {
                    if(tabJoueurs[2].tabCartes[i]!=null) {
                        tabJoueurs[2].tabCartes[i]=null;
                        break;
                    }
                }
                tabJoueurs[2].nbCartes--;
                nbCartes3.setText("Cartes libération de rattrapages : " + tabJoueurs[2].nbCartes);
                tabJoueurs[2].pion.coordX=2;
                tabJoueurs[2].pion.coordY=20;
                tabJoueurs[2].pion.taille=24;
                plateauJeu.orientation3=plateauJeu.gauche3;
                Libération3.setVisible(false);
                PayerPrison3.setVisible(false);
                plateauJeu.repaint();
                zone_texte_infos.setText(tabJoueurs[2].nom + " vous utilisez une carte vous libérant des rattrapages, lancez les dés et faites en sorte de valider vos matières la prochaine fois!");
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
                tabJoueurs[3].prison=false;
                tabJoueurs[3].compteurTourPrison=0;
                for(int i=0;i<tabJoueurs[3].tabCartes.length;i++) {
                    if(tabJoueurs[3].tabCartes[i]!=null) {
                        tabJoueurs[3].tabCartes[i]=null;
                        break;
                    }
                }
                tabJoueurs[3].nbCartes--;
                nbCartes4.setText("Cartes libération de rattrapages : " + tabJoueurs[3].nbCartes);
                tabJoueurs[3].pion.coordX=2;
                tabJoueurs[3].pion.coordY=63;
                tabJoueurs[3].pion.taille=24;
                plateauJeu.orientation4=plateauJeu.gauche4;
                Libération4.setVisible(false);
                PayerPrison4.setVisible(false);
                plateauJeu.repaint();
                zone_texte_infos.setText(tabJoueurs[3].nom + " vous utilisez une carte vous libérant des rattrapages, lancez les dés et faites en sorte de valider vos matières la prochaine fois!");
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
                tabJoueurs[0].prison=false;
                tabJoueurs[0].compteurTourPrison=0;
                tabJoueurs[0].credits-=50;
                credits1.setText("Crédits : " + tabJoueurs[0].credits + " ECTS");
                tabJoueurs[0].pion.coordX=61;
                tabJoueurs[0].pion.coordY=2;
                tabJoueurs[0].pion.taille=24;
                plateauJeu.orientation1=plateauJeu.haut1;
                PayerPrison1.setVisible(false);
                Libération1.setVisible(false);
                plateauJeu.repaint();
                zone_texte_infos.setText(tabJoueurs[0].nom + " vous payez pour éviter les rattrapages, lancez les dés et faites en sorte de valider vos matières la prochaine fois!");
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
                tabJoueurs[1].prison=false;
                tabJoueurs[1].compteurTourPrison=0;
                tabJoueurs[1].credits-=50;
                credits2.setText("Crédits : " + tabJoueurs[1].credits + " ECTS");
                tabJoueurs[1].pion.coordX=18;
                tabJoueurs[1].pion.coordY=2;
                tabJoueurs[1].pion.taille=24;
                plateauJeu.orientation2=plateauJeu.haut2;
                PayerPrison2.setVisible(false);
                Libération2.setVisible(false);
                plateauJeu.repaint();
                zone_texte_infos.setText(tabJoueurs[1].nom + " vous payez pour éviter les rattrapages, lancez les dés et faites en sorte de valider vos matières la prochaine fois!");
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
                tabJoueurs[2].prison=false;
                tabJoueurs[2].compteurTourPrison=0;
                tabJoueurs[2].credits-=50;
                credits3.setText("Crédits : " + tabJoueurs[2].credits + " ECTS");
                tabJoueurs[2].pion.coordX=2;
                tabJoueurs[2].pion.coordY=20;
                tabJoueurs[2].pion.taille=24;
                plateauJeu.orientation3=plateauJeu.gauche3;
                PayerPrison3.setVisible(false);
                Libération3.setVisible(false);
                plateauJeu.repaint();
                zone_texte_infos.setText(tabJoueurs[2].nom + " vous payez pour éviter les rattrapages, lancez les dés et faites en sorte de valider vos matières la prochaine fois!");
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
                tabJoueurs[3].prison=false;
                tabJoueurs[3].compteurTourPrison=0;
                tabJoueurs[3].credits-=50;
                credits4.setText("Crédits : " + tabJoueurs[3].credits + " ECTS");
                tabJoueurs[3].pion.coordX=2;
                tabJoueurs[3].pion.coordY=63;
                tabJoueurs[3].pion.taille=24;
                plateauJeu.orientation4=plateauJeu.gauche4;
                PayerPrison4.setVisible(false);
                Libération4.setVisible(false);
                plateauJeu.repaint();
                zone_texte_infos.setText(tabJoueurs[3].nom + " vous payez pour éviter les rattrapages, lancez les dés et faites en sorte de valider vos matières la prochaine fois!");
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
                Acheter1.setVisible(false);
                credits1.setText("Crédits : " + tabJoueurs[0].credits + " ECTS");
                zone_texte_infos.setText(joueurCourant.nom + " vous achetez " + joueurCourant.pion.caseassociee.nom + " pour " + joueurCourant.pion.caseassociee.prixTerrain + " ECTS!");
                if(de1.valeur==de2.valeur && joueurCourant.droitdejouer==true) {
                    if(joueurCourant.credits<0) {
                        eliminationJoueur();
                    }
                    compteurDouble++;
                    if(compteurDouble==3) {
                        joueurCourant.prison=true;
                        if(joueurCourant.credits<0) {
                            eliminationJoueur();
                        }
                        else {
                            changerJoueur();
                            compteurDouble=0;
                        }
                    }
                }
                else {
                    if(joueurCourant.credits<0) {
                        eliminationJoueur();
                    }
                    else {
                        changerJoueur();
                        compteurDouble=0;
                    }
                }
                while(joueurCourant.droitdejouer==false) {
                    joueurCourant.droitdejouer=true;
                    changerJoueur();
                    compteurDouble=0;
                }
                if(joueurCourant.compteurTourPrison==3) {
                    joueurCourant.compteurTourPrison=0;
                    joueurCourant.prison=false;
                    joueurCourant.credits=joueurCourant.credits-50;
                    if(joueurCourant==tabJoueurs[0]) {
                        tabJoueurs[0].pion.coordX=61;
                        tabJoueurs[0].pion.coordY=2;
                        tabJoueurs[0].pion.taille=24;
                        plateauJeu.orientation1=plateauJeu.haut1;
                        plateauJeu.repaint();
                        credits1.setText("Crédits : " + joueurCourant.credits + " ECTS");
                    }
                    else if(joueurCourant==tabJoueurs[1]) {
                        tabJoueurs[1].pion.coordX=18;
                        tabJoueurs[1].pion.coordY=2;
                        tabJoueurs[1].pion.taille=24;
                        plateauJeu.orientation2=plateauJeu.haut2;
                        plateauJeu.repaint();
                        credits2.setText("Crédits : " + joueurCourant.credits + " ECTS");
                    }
                    else if(joueurCourant==tabJoueurs[2]) {
                        tabJoueurs[2].pion.coordX=2;
                        tabJoueurs[2].pion.coordY=20;
                        tabJoueurs[2].pion.taille=24;
                        plateauJeu.orientation3=plateauJeu.gauche3;
                        plateauJeu.repaint();
                        credits3.setText("Crédits : " + joueurCourant.credits + " ECTS");
                    }
                    else if(joueurCourant==tabJoueurs[3]) {
                        tabJoueurs[3].pion.coordX=2;
                        tabJoueurs[3].pion.coordY=63;
                        tabJoueurs[3].pion.taille=24;
                        plateauJeu.orientation4=plateauJeu.gauche4;
                        plateauJeu.repaint();
                        credits4.setText("Crédits : " + joueurCourant.credits + " ECTS");
                    }
                    zone_texte_infos.setText(zone_texte_infos.getText() + "\n" + joueurCourant.nom + " vous venez de rater vos 3 essais aux rattrapages, vous payez donc 50 crédits et réintégrez le parcours scolaire!");
                    }
                if(joueurCourant==tabJoueurs[0] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                    ValiderMatiere1.setVisible(true);
                    }
                else if(joueurCourant==tabJoueurs[1] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                    ValiderMatiere2.setVisible(true);
                }
                else if(joueurCourant==tabJoueurs[2] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                    ValiderMatiere3.setVisible(true);
                }
                else if(joueurCourant==tabJoueurs[3] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                    ValiderMatiere4.setVisible(true);
                }
                if(joueurCourant==tabJoueurs[0] && joueurCourant.prison==true) {
                    joueurCourant.compteurTourPrison++;
                    if(joueurCourant.nbCartes!=0) {
                        Libération1.setVisible(true);
                    }
                    PayerPrison1.setVisible(true);
                }
                else if(joueurCourant==tabJoueurs[1] && joueurCourant.prison==true) {
                    joueurCourant.compteurTourPrison++;
                    if(joueurCourant.nbCartes!=0) {
                        Libération2.setVisible(true);
                    }
                    PayerPrison2.setVisible(true);
                }
                else if(joueurCourant==tabJoueurs[2] && joueurCourant.prison==true) {
                    joueurCourant.compteurTourPrison++;
                    if(joueurCourant.nbCartes!=0) {
                        Libération3.setVisible(true);
                    }
                    PayerPrison3.setVisible(true);
                }
                else if(joueurCourant==tabJoueurs[3] && joueurCourant.prison==true) {
                    joueurCourant.compteurTourPrison++;
                    if(joueurCourant.nbCartes!=0) {
                        Libération4.setVisible(true);
                    }
                    PayerPrison4.setVisible(true);
                }
                fin_de_tour1.setVisible(false);
                if(elim!=true) {
                    Lancer_des.setEnabled(true);
                }
                if(joueurCourant.prison!=true && joueurCourant.nom!=null && compteurDouble==0) {
                    zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon tirez les dés!");
                }
                else if(joueurCourant.prison!=true && joueurCourant.nom!=null) {
                    zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, vous pouvez tirez les dés!");
                }
                else {
                    if(joueurCourant.nom!=null) {
                        zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon essayer de quitter les rattrapages (vous pouvez payer 50 crédits, jouer une carte libération de rattrapages si vous en possédez une ou sinon tenter de faire un double)!");
                    }
                }
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
                Acheter2.setVisible(false);
                credits2.setText("Crédits : " + tabJoueurs[1].credits + " ECTS");
                zone_texte_infos.setText(joueurCourant.nom + " vous achetez " + joueurCourant.pion.caseassociee.nom + " pour " + joueurCourant.pion.caseassociee.prixTerrain + " ECTS!");
                if(de1.valeur==de2.valeur && joueurCourant.droitdejouer==true) {
                    if(joueurCourant.credits<0) {
                        eliminationJoueur();
                    }
                    compteurDouble++;
                    if(compteurDouble==3) {
                        joueurCourant.prison=true;
                        if(joueurCourant.credits<0) {
                            eliminationJoueur();
                        }
                        else {
                            changerJoueur();
                            compteurDouble=0;
                        }
                    }
                }
                else {
                    if(joueurCourant.credits<0) {
                        eliminationJoueur();
                    }
                    else {
                        changerJoueur();
                        compteurDouble=0;
                    }
                }
                while(joueurCourant.droitdejouer==false) {
                    joueurCourant.droitdejouer=true;
                    changerJoueur();
                    compteurDouble=0;
                }
                if(joueurCourant.compteurTourPrison==3) {
                    joueurCourant.compteurTourPrison=0;
                    joueurCourant.prison=false;
                    joueurCourant.credits=joueurCourant.credits-50;
                    if(joueurCourant==tabJoueurs[0]) {
                        tabJoueurs[0].pion.coordX=61;
                        tabJoueurs[0].pion.coordY=2;
                        tabJoueurs[0].pion.taille=24;
                        plateauJeu.orientation1=plateauJeu.haut1;
                        plateauJeu.repaint();
                        credits1.setText("Crédits : " + joueurCourant.credits + " ECTS");
                    }
                    else if(joueurCourant==tabJoueurs[1]) {
                        tabJoueurs[1].pion.coordX=18;
                        tabJoueurs[1].pion.coordY=2;
                        tabJoueurs[1].pion.taille=24;
                        plateauJeu.orientation2=plateauJeu.haut2;
                        plateauJeu.repaint();
                        credits2.setText("Crédits : " + joueurCourant.credits + " ECTS");
                    }
                    else if(joueurCourant==tabJoueurs[2]) {
                        tabJoueurs[2].pion.coordX=2;
                        tabJoueurs[2].pion.coordY=20;
                        tabJoueurs[2].pion.taille=24;
                        plateauJeu.orientation3=plateauJeu.gauche3;
                        plateauJeu.repaint();
                        credits3.setText("Crédits : " + joueurCourant.credits + " ECTS");
                    }
                    else if(joueurCourant==tabJoueurs[3]) {
                        tabJoueurs[3].pion.coordX=2;
                        tabJoueurs[3].pion.coordY=63;
                        tabJoueurs[3].pion.taille=24;
                        plateauJeu.orientation4=plateauJeu.gauche4;
                        plateauJeu.repaint();
                        credits4.setText("Crédits : " + joueurCourant.credits + " ECTS");
                    }
                    zone_texte_infos.setText(zone_texte_infos.getText() + "\n" + joueurCourant.nom + " vous venez de rater vos 3 essais aux rattrapages, vous payez donc 50 crédits et réintégrez le parcours scolaire!");
                    }
                if(joueurCourant==tabJoueurs[0] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                    ValiderMatiere1.setVisible(true);
                    }
                else if(joueurCourant==tabJoueurs[1] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                    ValiderMatiere2.setVisible(true);
                }
                else if(joueurCourant==tabJoueurs[2] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                    ValiderMatiere3.setVisible(true);
                }
                else if(joueurCourant==tabJoueurs[3] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                    ValiderMatiere4.setVisible(true);
                }
                if(joueurCourant==tabJoueurs[0] && joueurCourant.prison==true) {
                    joueurCourant.compteurTourPrison++;
                    if(joueurCourant.nbCartes!=0) {
                        Libération1.setVisible(true);
                    }
                    PayerPrison1.setVisible(true);
                }
                else if(joueurCourant==tabJoueurs[1] && joueurCourant.prison==true) {
                    joueurCourant.compteurTourPrison++;
                    if(joueurCourant.nbCartes!=0) {
                        Libération2.setVisible(true);
                    }
                    PayerPrison2.setVisible(true);
                }
                else if(joueurCourant==tabJoueurs[2] && joueurCourant.prison==true) {
                    joueurCourant.compteurTourPrison++;
                    if(joueurCourant.nbCartes!=0) {
                        Libération3.setVisible(true);
                    }
                    PayerPrison3.setVisible(true);
                }
                else if(joueurCourant==tabJoueurs[3] && joueurCourant.prison==true) {
                    joueurCourant.compteurTourPrison++;
                    if(joueurCourant.nbCartes!=0) {
                        Libération1.setVisible(true);
                    }
                    PayerPrison4.setVisible(true);
                }
                fin_de_tour2.setVisible(false);
                if(elim!=true) {
                    Lancer_des.setEnabled(true);
                }
                if(joueurCourant.prison!=true && joueurCourant.nom!=null && compteurDouble==0) {
                    zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon tirez les dés!");
                }
                else if(joueurCourant.prison!=true && joueurCourant.nom!=null) {
                    zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, vous pouvez tirez les dés!");
                }
                else {
                    if(joueurCourant.nom!=null) {
                        zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon essayer de quitter les rattrapages (vous pouvez payer 50 crédits, jouer une carte libération de rattrapages si vous en possédez une ou sinon tenter de faire un double)!");
                    }
                }
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
                Acheter3.setVisible(false);
                credits3.setText("Crédits : " + tabJoueurs[2].credits + " ECTS");
                zone_texte_infos.setText(joueurCourant.nom + " vous achetez " + joueurCourant.pion.caseassociee.nom + " pour " + joueurCourant.pion.caseassociee.prixTerrain + " ECTS!");
                if(de1.valeur==de2.valeur && joueurCourant.droitdejouer==true) {
                    if(joueurCourant.credits<0) {
                        eliminationJoueur();
                    }
                    compteurDouble++;
                    if(compteurDouble==3) {
                        joueurCourant.prison=true;
                        if(joueurCourant.credits<0) {
                            eliminationJoueur();
                        }
                        else {
                            changerJoueur();
                            compteurDouble=0;
                        }
                    }
                }
                else {
                    if(joueurCourant.credits<0) {
                        eliminationJoueur();
                    }
                    else {
                        changerJoueur();
                        compteurDouble=0;
                    }
                }
                while(joueurCourant.droitdejouer==false) {
                    joueurCourant.droitdejouer=true;
                    changerJoueur();
                    compteurDouble=0;
                }
                if(joueurCourant.compteurTourPrison==3) {
                    joueurCourant.compteurTourPrison=0;
                    joueurCourant.prison=false;
                    joueurCourant.credits=joueurCourant.credits-50;
                    if(joueurCourant==tabJoueurs[0]) {
                        tabJoueurs[0].pion.coordX=61;
                        tabJoueurs[0].pion.coordY=2;
                        tabJoueurs[0].pion.taille=24;
                        plateauJeu.orientation1=plateauJeu.haut1;
                        plateauJeu.repaint();
                        credits1.setText("Crédits : " + joueurCourant.credits + " ECTS");
                    }
                    else if(joueurCourant==tabJoueurs[1]) {
                        tabJoueurs[1].pion.coordX=18;
                        tabJoueurs[1].pion.coordY=2;
                        tabJoueurs[1].pion.taille=24;
                        plateauJeu.orientation2=plateauJeu.haut2;
                        plateauJeu.repaint();
                        credits2.setText("Crédits : " + joueurCourant.credits + " ECTS");
                    }
                    else if(joueurCourant==tabJoueurs[2]) {
                        tabJoueurs[2].pion.coordX=2;
                        tabJoueurs[2].pion.coordY=20;
                        tabJoueurs[2].pion.taille=24;
                        plateauJeu.orientation3=plateauJeu.gauche3;
                        plateauJeu.repaint();
                        credits3.setText("Crédits : " + joueurCourant.credits + " ECTS");
                    }
                    else if(joueurCourant==tabJoueurs[3]) {
                        tabJoueurs[3].pion.coordX=2;
                        tabJoueurs[3].pion.coordY=63;
                        tabJoueurs[3].pion.taille=24;
                        plateauJeu.orientation4=plateauJeu.gauche4;
                        plateauJeu.repaint();
                        credits4.setText("Crédits : " + joueurCourant.credits + " ECTS");
                    }
                    zone_texte_infos.setText(zone_texte_infos.getText() + "\n" + joueurCourant.nom + " vous venez de rater vos 3 essais aux rattrapages, vous payez donc 50 crédits et réintégrez le parcours scolaire!");
                    }
                if(joueurCourant==tabJoueurs[0] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                    ValiderMatiere1.setVisible(true);
                    }
                else if(joueurCourant==tabJoueurs[1] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                    ValiderMatiere2.setVisible(true);
                }
                else if(joueurCourant==tabJoueurs[2] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                    ValiderMatiere3.setVisible(true);
                }
                else if(joueurCourant==tabJoueurs[3] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                    ValiderMatiere4.setVisible(true);
                }
                if(joueurCourant==tabJoueurs[0] && joueurCourant.prison==true) {
                    joueurCourant.compteurTourPrison++;
                    if(joueurCourant.nbCartes!=0) {
                        Libération1.setVisible(true);
                    }
                    PayerPrison1.setVisible(true);
                }
                else if(joueurCourant==tabJoueurs[1] && joueurCourant.prison==true) {
                    joueurCourant.compteurTourPrison++;
                    if(joueurCourant.nbCartes!=0) {
                        Libération2.setVisible(true);
                    }
                    PayerPrison2.setVisible(true);
                }
                else if(joueurCourant==tabJoueurs[2] && joueurCourant.prison==true) {
                    joueurCourant.compteurTourPrison++;
                    if(joueurCourant.nbCartes!=0) {
                        Libération3.setVisible(true);
                    }
                    PayerPrison3.setVisible(true);
                }
                else if(joueurCourant==tabJoueurs[3] && joueurCourant.prison==true) {
                    joueurCourant.compteurTourPrison++;
                    if(joueurCourant.nbCartes!=0) {
                        Libération4.setVisible(true);
                    }
                    PayerPrison4.setVisible(true);
                }
                fin_de_tour3.setVisible(false);
                if(elim!=true) {
                    Lancer_des.setEnabled(true);
                }
                if(joueurCourant.prison!=true && joueurCourant.nom!=null && compteurDouble==0) {
                    zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon tirez les dés!");
                }
                else if(joueurCourant.prison!=true && joueurCourant.nom!=null) {
                    zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, vous pouvez tirez les dés!");
                }
                else {
                    if(joueurCourant.nom!=null) {
                        zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon essayer de quitter les rattrapages (vous pouvez payer 50 crédits, jouer une carte libération de rattrapages si vous en possédez une ou sinon tenter de faire un double)!");
                    }
                }
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
                Acheter4.setVisible(false);
                credits4.setText("Crédits : " + tabJoueurs[3].credits + " ECTS");
                zone_texte_infos.setText(joueurCourant.nom + " vous achetez " + joueurCourant.pion.caseassociee.nom + " pour " + joueurCourant.pion.caseassociee.prixTerrain + " ECTS!");
                if(de1.valeur==de2.valeur && joueurCourant.droitdejouer==true) {
                    if(joueurCourant.credits<0) {
                        eliminationJoueur();
                    }
                    compteurDouble++;
                    if(compteurDouble==3) {
                        joueurCourant.prison=true;
                        if(joueurCourant.credits<0) {
                            eliminationJoueur();
                        }
                        else {
                            changerJoueur();
                            compteurDouble=0;
                        }
                    }
                }
                else {
                    if(joueurCourant.credits<0) {
                        eliminationJoueur();
                    }
                    else {
                        changerJoueur();
                        compteurDouble=0;
                    }
                }
                while(joueurCourant.droitdejouer==false) {
                    joueurCourant.droitdejouer=true;
                    changerJoueur();
                    compteurDouble=0;
                }
                if(joueurCourant.compteurTourPrison==3) {
                    joueurCourant.compteurTourPrison=0;
                    joueurCourant.prison=false;
                    joueurCourant.credits=joueurCourant.credits-50;
                    if(joueurCourant==tabJoueurs[0]) {
                        tabJoueurs[0].pion.coordX=61;
                        tabJoueurs[0].pion.coordY=2;
                        tabJoueurs[0].pion.taille=24;
                        plateauJeu.orientation1=plateauJeu.haut1;
                        plateauJeu.repaint();
                        credits1.setText("Crédits : " + joueurCourant.credits + " ECTS");
                    }
                    else if(joueurCourant==tabJoueurs[1]) {
                        tabJoueurs[1].pion.coordX=18;
                        tabJoueurs[1].pion.coordY=2;
                        tabJoueurs[1].pion.taille=24;
                        plateauJeu.orientation2=plateauJeu.haut2;
                        plateauJeu.repaint();
                        credits2.setText("Crédits : " + joueurCourant.credits + " ECTS");
                    }
                    else if(joueurCourant==tabJoueurs[2]) {
                        tabJoueurs[2].pion.coordX=2;
                        tabJoueurs[2].pion.coordY=20;
                        tabJoueurs[2].pion.taille=24;
                        plateauJeu.orientation3=plateauJeu.gauche3;
                        plateauJeu.repaint();
                        credits3.setText("Crédits : " + joueurCourant.credits + " ECTS");
                    }
                    else if(joueurCourant==tabJoueurs[3]) {
                        tabJoueurs[3].pion.coordX=2;
                        tabJoueurs[3].pion.coordY=63;
                        tabJoueurs[3].pion.taille=24;
                        plateauJeu.orientation4=plateauJeu.gauche4;
                        plateauJeu.repaint();
                        credits4.setText("Crédits : " + joueurCourant.credits + " ECTS");
                    }
                    zone_texte_infos.setText(zone_texte_infos.getText() + "\n" + joueurCourant.nom + " vous venez de rater vos 3 essais aux rattrapages, vous payez donc 50 crédits et réintégrez le parcours scolaire!");
                    }
                if(joueurCourant==tabJoueurs[0] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                    ValiderMatiere1.setVisible(true);
                    }
                else if(joueurCourant==tabJoueurs[1] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                    ValiderMatiere2.setVisible(true);
                }
                else if(joueurCourant==tabJoueurs[2] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                    ValiderMatiere3.setVisible(true);
                }
                else if(joueurCourant==tabJoueurs[3] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                    ValiderMatiere4.setVisible(true);
                }
                if(joueurCourant==tabJoueurs[0] && joueurCourant.prison==true) {
                    joueurCourant.compteurTourPrison++;
                    if(joueurCourant.nbCartes!=0) {
                        Libération1.setVisible(true);
                    }
                    PayerPrison1.setVisible(true);
                }
                else if(joueurCourant==tabJoueurs[1] && joueurCourant.prison==true) {
                    joueurCourant.compteurTourPrison++;
                    if(joueurCourant.nbCartes!=0) {
                        Libération2.setVisible(true);
                    }
                    PayerPrison2.setVisible(true);
                }
                else if(joueurCourant==tabJoueurs[2] && joueurCourant.prison==true) {
                    joueurCourant.compteurTourPrison++;
                    if(joueurCourant.nbCartes!=0) {
                        Libération3.setVisible(true);
                    }
                    PayerPrison3.setVisible(true);
                }
                else if(joueurCourant==tabJoueurs[3] && joueurCourant.prison==true) {
                    joueurCourant.compteurTourPrison++;
                    if(joueurCourant.nbCartes!=0) {
                        Libération4.setVisible(true);
                    }
                    PayerPrison4.setVisible(true);
                }
                fin_de_tour4.setVisible(false);
                if(elim!=true) {
                    Lancer_des.setEnabled(true);
                }
                if(joueurCourant.prison!=true && joueurCourant.nom!=null && compteurDouble==0) {
                    zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon tirez les dés!");
                }
                else if(joueurCourant.prison!=true && joueurCourant.nom!=null) {
                    zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, vous pouvez tirez les dés!");
                }
                else {
                    if(joueurCourant.nom!=null) {
                        zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon essayer de quitter les rattrapages (vous pouvez payer 50 crédits, jouer une carte libération de rattrapages si vous en possédez une ou sinon tenter de faire un double)!");
                    }
                }
            }
        });
        this.repaint();
        
        marronV = new JButton();
        marronV.setBounds(0,0,40,40);
        marronV.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
        marronV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if (darties) {
                    marronV.setEnabled(false);
                    bleucielV.setEnabled(false);
                    violetV.setEnabled(false);
                    orangeV.setEnabled(false);
                    rougeV.setEnabled(false);
                    jauneV.setEnabled(false);
                    vertV.setEnabled(false);
                    bleufoncéV.setEnabled(false);
                }
                JFrame MarronFrame = new JFrame("Validation dans une salle marron");
                MarronFrame.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
                MarronFrame.setVisible(true);
                MarronFrame.setBounds(420,100,465,400);
                JPanel PanelMarron = new JPanel();
                PanelMarron.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
                PanelMarron.setBounds(0,0,465,400);
                MarronFrame.add(PanelMarron, new org.netbeans.lib.awtextra.AbsoluteConstraints(0,0,-1,-1));
                JLabel L102 = new JLabel(new ImageIcon("src/salles/L102.png"));
                JLabel L104 = new JLabel(new ImageIcon("src/salles/L104.png"));
                PanelMarron.add(L102, new org.netbeans.lib.awtextra.AbsoluteConstraints(50,30,-1,-1));
                PanelMarron.add(L104, new org.netbeans.lib.awtextra.AbsoluteConstraints(250,30, -1,-1));
                
                JButton Construire1 = new JButton("Valider");
                Construire1.setBounds(50,300,154,30);
                Construire1.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
                Construire1.setBackground(new Color(148,74,37));
                Construire1.setForeground(Color.white);
                if (plateau.plateaudejeu[1].proprietaire != joueurCourant) {
                    Construire1.setEnabled(false);
                }
                else {
                    Construire1.setEnabled(true);
                }
                PanelMarron.add(Construire1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50,300,154,30));
                
                JButton Construire2 = new JButton("Valider");
                Construire2.setBounds(250,300,154,30);
                Construire2.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
                Construire2.setBackground(new Color(148,74,37));
                Construire2.setForeground(Color.white);
                if (plateau.plateaudejeu[3].proprietaire != joueurCourant) {
                    Construire2.setEnabled(false);
                }
                else {
                    Construire2.setEnabled(true);
                }
                Construire2.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        if (darties) {
                            Construire1.setEnabled(false);
                            Construire2.setEnabled(false);
                            if(joueurCourant.credits<0) {
                                eliminationJoueur();
                            }
                            else {
                                changerJoueur();
                                compteurDouble=0;
                            }
                            while(joueurCourant.droitdejouer==false) {
                                joueurCourant.droitdejouer=true;
                                changerJoueur();
                                compteurDouble=0;
                            }
                            if(joueurCourant.compteurTourPrison==3) {
                                joueurCourant.compteurTourPrison=0;
                                joueurCourant.prison=false;
                                joueurCourant.credits=joueurCourant.credits-50;
                                if(joueurCourant==tabJoueurs[0]) {
                                    tabJoueurs[0].pion.coordX=61;
                                    tabJoueurs[0].pion.coordY=2;
                                    tabJoueurs[0].pion.taille=24;
                                    plateauJeu.orientation1=plateauJeu.haut1;
                                    plateauJeu.repaint();
                                    credits1.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                else if(joueurCourant==tabJoueurs[1]) {
                                    tabJoueurs[1].pion.coordX=18;
                                    tabJoueurs[1].pion.coordY=2;
                                    tabJoueurs[1].pion.taille=24;
                                    plateauJeu.orientation2=plateauJeu.haut2;
                                    plateauJeu.repaint();
                                    credits2.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                else if(joueurCourant==tabJoueurs[2]) {
                                    tabJoueurs[2].pion.coordX=2;
                                    tabJoueurs[2].pion.coordY=20;
                                    tabJoueurs[2].pion.taille=24;
                                    plateauJeu.orientation3=plateauJeu.gauche3;
                                    plateauJeu.repaint();
                                    credits3.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                else if(joueurCourant==tabJoueurs[3]) {
                                    tabJoueurs[3].pion.coordX=2;
                                    tabJoueurs[3].pion.coordY=63;
                                    tabJoueurs[3].pion.taille=24;
                                    plateauJeu.orientation4=plateauJeu.gauche4;
                                    plateauJeu.repaint();
                                    credits4.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n" + joueurCourant.nom + " vous venez de rater vos 3 essais aux rattrapages, vous payez donc 50 crédits et réintégrez le parcours scolaire!");
                            }
                            if(joueurCourant==tabJoueurs[0] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere1.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[1] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere2.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[2] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere3.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[3] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere4.setVisible(true);
                            }
                            if(joueurCourant==tabJoueurs[0] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération1.setVisible(true);
                                }
                                PayerPrison1.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[1] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération2.setVisible(true);
                                }
                                PayerPrison2.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[2] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération3.setVisible(true);
                                }
                                PayerPrison3.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[3] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération4.setVisible(true);
                                }
                                PayerPrison4.setVisible(true);
                            }
                            if(elim!=true) {
                                Lancer_des.setEnabled(true);
                            }
                            if(joueurCourant.prison!=true && joueurCourant.nom!=null && compteurDouble==0) {
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon tirez les dés!");
                            }
                            else if(joueurCourant.prison!=true && joueurCourant.nom!=null) {
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, vous pouvez tirez les dés!");
                            }
                            else {
                                if(joueurCourant.nom!=null) {
                                    zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon essayer de quitter les rattrapages (vous pouvez payer 50 crédits, jouer une carte libération de rattrapages si vous en possédez une ou sinon tenter de faire un double)!");
                                }
                            }
                            plateauJeu.repaint();
                        }
                        plateau.plateaudejeu[3].construireMaison(darties);
                        plateau.plateaudejeu[3].changerLoyer();
                        credits1.setText("Crédits : " + tabJoueurs[0].credits + " ECTS");
                        credits2.setText("Crédits : " + tabJoueurs[1].credits + " ECTS");
                        credits3.setText("Crédits : " + tabJoueurs[2].credits + " ECTS");
                        credits4.setText("Crédits : " + tabJoueurs[3].credits + " ECTS");
                        if(plateau.plateaudejeu[3].maison==5) {
                            Construire2.setEnabled(false);
                        }
                        if(joueurCourant.credits<0) {
                            eliminationJoueur();
                        }
                        darties = false;
                        plateauJeu.repaint();
                    }
                });
                
                 Construire1.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        if (darties) {
                            Construire1.setEnabled(false);
                            Construire2.setEnabled(false);
                            if(joueurCourant.credits<0) {
                                eliminationJoueur();
                            }
                            else {
                                changerJoueur();
                                compteurDouble=0;
                            }
                            while(joueurCourant.droitdejouer==false) {
                                joueurCourant.droitdejouer=true;
                                changerJoueur();
                                compteurDouble=0;
                            }
                            if(joueurCourant.compteurTourPrison==3) {
                                joueurCourant.compteurTourPrison=0;
                                joueurCourant.prison=false;
                                joueurCourant.credits=joueurCourant.credits-50;
                                if(joueurCourant==tabJoueurs[0]) {
                                    tabJoueurs[0].pion.coordX=61;
                                    tabJoueurs[0].pion.coordY=2;
                                    tabJoueurs[0].pion.taille=24;
                                    plateauJeu.orientation1=plateauJeu.haut1;
                                    plateauJeu.repaint();
                                    credits1.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                else if(joueurCourant==tabJoueurs[1]) {
                                    tabJoueurs[1].pion.coordX=18;
                                    tabJoueurs[1].pion.coordY=2;
                                    tabJoueurs[1].pion.taille=24;
                                    plateauJeu.orientation2=plateauJeu.haut2;
                                    plateauJeu.repaint();
                                    credits2.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                else if(joueurCourant==tabJoueurs[2]) {
                                    tabJoueurs[2].pion.coordX=2;
                                    tabJoueurs[2].pion.coordY=20;
                                    tabJoueurs[2].pion.taille=24;
                                    plateauJeu.orientation3=plateauJeu.gauche3;
                                    plateauJeu.repaint();
                                    credits3.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                else if(joueurCourant==tabJoueurs[3]) {
                                    tabJoueurs[3].pion.coordX=2;
                                    tabJoueurs[3].pion.coordY=63;
                                    tabJoueurs[3].pion.taille=24;
                                    plateauJeu.orientation4=plateauJeu.gauche4;
                                    plateauJeu.repaint();
                                    credits4.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n" + joueurCourant.nom + " vous venez de rater vos 3 essais aux rattrapages, vous payez donc 50 crédits et réintégrez le parcours scolaire!");
                            }
                            if(joueurCourant==tabJoueurs[0] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere1.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[1] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere2.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[2] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere3.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[3] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere4.setVisible(true);
                            }
                            if(joueurCourant==tabJoueurs[0] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération1.setVisible(true);
                                }
                                PayerPrison1.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[1] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération2.setVisible(true);
                                }
                                PayerPrison2.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[2] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération3.setVisible(true);
                                }
                                PayerPrison3.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[3] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération4.setVisible(true);
                                }
                                PayerPrison4.setVisible(true);
                            }
                            if(elim!=true) {
                                Lancer_des.setEnabled(true);
                            }
                            if(joueurCourant.prison!=true && joueurCourant.nom!=null && compteurDouble==0) {
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon tirez les dés!");
                            }
                            else if(joueurCourant.prison!=true && joueurCourant.nom!=null) {
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, vous pouvez tirez les dés!");
                            }
                            else {
                                if(joueurCourant.nom!=null) {
                                    zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon essayer de quitter les rattrapages (vous pouvez payer 50 crédits, jouer une carte libération de rattrapages si vous en possédez une ou sinon tenter de faire un double)!");
                                }
                            }
                            plateauJeu.repaint();
                        }
                        plateau.plateaudejeu[1].construireMaison(darties);
                        plateau.plateaudejeu[1].changerLoyer();
                        credits1.setText("Crédits : " + tabJoueurs[0].credits + " ECTS");
                        credits2.setText("Crédits : " + tabJoueurs[1].credits + " ECTS");
                        credits3.setText("Crédits : " + tabJoueurs[2].credits + " ECTS");
                        credits4.setText("Crédits : " + tabJoueurs[3].credits + " ECTS");
                        if(plateau.plateaudejeu[1].maison==5) {
                            Construire1.setEnabled(false);
                        }
                        if(joueurCourant.credits<0) {
                            eliminationJoueur();
                        }
                        darties = false;
                        plateauJeu.repaint();
                    }
                });
                PanelMarron.add(Construire2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250,300,154,30));
                
                PanelMarron.repaint();
                MarronFrame.repaint();  
            }
        });
        
        bleucielV = new JButton();
        bleucielV.setBounds(0,0,40,40);
        bleucielV.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
        bleucielV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if (darties) {
                    marronV.setEnabled(false);
                    bleucielV.setEnabled(false);
                    violetV.setEnabled(false);
                    orangeV.setEnabled(false);
                    rougeV.setEnabled(false);
                    jauneV.setEnabled(false);
                    vertV.setEnabled(false);
                    bleufoncéV.setEnabled(false);
                }
                JFrame BleuCielFrame = new JFrame("Validation dans une salle bleu ciel");
                BleuCielFrame.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
                BleuCielFrame.setVisible(true);
                BleuCielFrame.setBounds(420,100,665,400);
                JPanel PanelBleuCiel = new JPanel();
                PanelBleuCiel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
                PanelBleuCiel.setBounds(0,0,665,400);
                BleuCielFrame.add(PanelBleuCiel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0,0,-1,-1));
                JLabel M01 = new JLabel(new ImageIcon("src/salles/M01.png"));
                JLabel M02 = new JLabel(new ImageIcon("src/salles/M02.png"));
                JLabel M03 = new JLabel(new ImageIcon("src/salles/M03.png"));
                PanelBleuCiel.add(M01, new org.netbeans.lib.awtextra.AbsoluteConstraints(50,30,-1,-1));
                PanelBleuCiel.add(M02, new org.netbeans.lib.awtextra.AbsoluteConstraints(250,30, -1,-1));
                PanelBleuCiel.add(M03, new org.netbeans.lib.awtextra.AbsoluteConstraints(450,30,-1,-1));
                
                JButton Construire1 = new JButton("Valider");
                Construire1.setBounds(50,300,154,30);
                Construire1.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
                Construire1.setBackground(new Color(189,227,246));
                Construire1.setForeground(Color.white);
                
                PanelBleuCiel.add(Construire1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50,300,154,30));
                
                JButton Construire2 = new JButton("Valider");
                Construire2.setBounds(250,300,154,30);
                Construire2.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
                Construire2.setBackground(new Color(189,227,246));
                Construire2.setForeground(Color.white);
                
                PanelBleuCiel.add(Construire2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250,300,154,30));
                
                JButton Construire3 = new JButton("Valider");
                Construire3.setBounds(250,300,154,30);
                Construire3.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
                Construire3.setBackground(new Color(189,227,246));
                Construire3.setForeground(Color.white);
                
                if (plateau.plateaudejeu[6].proprietaire != joueurCourant) {
                    Construire1.setEnabled(false);
                }
                else {
                    Construire1.setEnabled(true);
                }
                if (plateau.plateaudejeu[8].proprietaire != joueurCourant) {
                    Construire2.setEnabled(false);
                }
                else {
                    Construire2.setEnabled(true);
                }
                if(plateau.plateaudejeu[9].proprietaire != joueurCourant) {
                    Construire3.setEnabled(false);
                }
                else {
                    Construire3.setEnabled(true);
                }
                
                Construire1.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        if (darties) {
                            Construire1.setEnabled(false);
                            Construire2.setEnabled(false);
                            Construire3.setEnabled(false);
                            if(joueurCourant.credits<0) {
                                eliminationJoueur();
                            }
                            else {
                                changerJoueur();
                                compteurDouble=0;
                            }
                            while(joueurCourant.droitdejouer==false) {
                                joueurCourant.droitdejouer=true;
                                changerJoueur();
                                compteurDouble=0;
                            }
                            if(joueurCourant.compteurTourPrison==3) {
                                joueurCourant.compteurTourPrison=0;
                                joueurCourant.prison=false;
                                joueurCourant.credits=joueurCourant.credits-50;
                                if(joueurCourant==tabJoueurs[0]) {
                                    tabJoueurs[0].pion.coordX=61;
                                    tabJoueurs[0].pion.coordY=2;
                                    tabJoueurs[0].pion.taille=24;
                                    plateauJeu.orientation1=plateauJeu.haut1;
                                    plateauJeu.repaint();
                                    credits1.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                else if(joueurCourant==tabJoueurs[1]) {
                                    tabJoueurs[1].pion.coordX=18;
                                    tabJoueurs[1].pion.coordY=2;
                                    tabJoueurs[1].pion.taille=24;
                                    plateauJeu.orientation2=plateauJeu.haut2;
                                    plateauJeu.repaint();
                                    credits2.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                else if(joueurCourant==tabJoueurs[2]) {
                                    tabJoueurs[2].pion.coordX=2;
                                    tabJoueurs[2].pion.coordY=20;
                                    tabJoueurs[2].pion.taille=24;
                                    plateauJeu.orientation3=plateauJeu.gauche3;
                                    plateauJeu.repaint();
                                    credits3.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                else if(joueurCourant==tabJoueurs[3]) {
                                    tabJoueurs[3].pion.coordX=2;
                                    tabJoueurs[3].pion.coordY=63;
                                    tabJoueurs[3].pion.taille=24;
                                    plateauJeu.orientation4=plateauJeu.gauche4;
                                    plateauJeu.repaint();
                                    credits4.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n" + joueurCourant.nom + " vous venez de rater vos 3 essais aux rattrapages, vous payez donc 50 crédits et réintégrez le parcours scolaire!");
                            }
                            if(joueurCourant==tabJoueurs[0] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere1.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[1] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere2.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[2] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere3.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[3] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere4.setVisible(true);
                            }
                            if(joueurCourant==tabJoueurs[0] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération1.setVisible(true);
                                }
                                PayerPrison1.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[1] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération2.setVisible(true);
                                }
                                PayerPrison2.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[2] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération3.setVisible(true);
                                }
                                PayerPrison3.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[3] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération4.setVisible(true);
                                }
                                PayerPrison4.setVisible(true);
                            }
                            if(elim!=true) {
                                Lancer_des.setEnabled(true);
                            }
                            if(joueurCourant.prison!=true && joueurCourant.nom!=null && compteurDouble==0) {
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon tirez les dés!");
                            }
                            else if(joueurCourant.prison!=true && joueurCourant.nom!=null) {
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, vous pouvez tirez les dés!");
                            }
                            else {
                                if(joueurCourant.nom!=null) {
                                    zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon essayer de quitter les rattrapages (vous pouvez payer 50 crédits, jouer une carte libération de rattrapages si vous en possédez une ou sinon tenter de faire un double)!");
                                }
                            }
                            plateauJeu.repaint();
                        }
                        plateau.plateaudejeu[6].construireMaison(darties);
                        plateau.plateaudejeu[6].changerLoyer();
                        credits1.setText("Crédits : " + tabJoueurs[0].credits + " ECTS");
                        credits2.setText("Crédits : " + tabJoueurs[1].credits + " ECTS");
                        credits3.setText("Crédits : " + tabJoueurs[2].credits + " ECTS");
                        credits4.setText("Crédits : " + tabJoueurs[3].credits + " ECTS");
                        if(plateau.plateaudejeu[6].maison==5) {
                            Construire1.setEnabled(false);
                        }
                        if(joueurCourant.credits<0) {
                            eliminationJoueur();
                        }
                        darties = false;
                        plateauJeu.repaint();
                    }
                });
                
                Construire2.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        if (darties) {
                            Construire1.setEnabled(false);
                            Construire2.setEnabled(false);
                            Construire3.setEnabled(false);
                            if(joueurCourant.credits<0) {
                                eliminationJoueur();
                            }
                            else {
                                changerJoueur();
                                compteurDouble=0;
                            }
                            while(joueurCourant.droitdejouer==false) {
                                joueurCourant.droitdejouer=true;
                                changerJoueur();
                                compteurDouble=0;
                            }
                            if(joueurCourant.compteurTourPrison==3) {
                                joueurCourant.compteurTourPrison=0;
                                joueurCourant.prison=false;
                                joueurCourant.credits=joueurCourant.credits-50;
                                if(joueurCourant==tabJoueurs[0]) {
                                    tabJoueurs[0].pion.coordX=61;
                                    tabJoueurs[0].pion.coordY=2;
                                    tabJoueurs[0].pion.taille=24;
                                    plateauJeu.orientation1=plateauJeu.haut1;
                                    plateauJeu.repaint();
                                    credits1.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                else if(joueurCourant==tabJoueurs[1]) {
                                    tabJoueurs[1].pion.coordX=18;
                                    tabJoueurs[1].pion.coordY=2;
                                    tabJoueurs[1].pion.taille=24;
                                    plateauJeu.orientation2=plateauJeu.haut2;
                                    plateauJeu.repaint();
                                    credits2.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                else if(joueurCourant==tabJoueurs[2]) {
                                    tabJoueurs[2].pion.coordX=2;
                                    tabJoueurs[2].pion.coordY=20;
                                    tabJoueurs[2].pion.taille=24;
                                    plateauJeu.orientation3=plateauJeu.gauche3;
                                    plateauJeu.repaint();
                                    credits3.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                else if(joueurCourant==tabJoueurs[3]) {
                                    tabJoueurs[3].pion.coordX=2;
                                    tabJoueurs[3].pion.coordY=63;
                                    tabJoueurs[3].pion.taille=24;
                                    plateauJeu.orientation4=plateauJeu.gauche4;
                                    plateauJeu.repaint();
                                    credits4.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n" + joueurCourant.nom + " vous venez de rater vos 3 essais aux rattrapages, vous payez donc 50 crédits et réintégrez le parcours scolaire!");
                            }
                            if(joueurCourant==tabJoueurs[0] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere1.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[1] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere2.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[2] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere3.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[3] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere4.setVisible(true);
                            }
                            if(joueurCourant==tabJoueurs[0] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération1.setVisible(true);
                                }
                                PayerPrison1.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[1] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération2.setVisible(true);
                                }
                                PayerPrison2.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[2] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération3.setVisible(true);
                                }
                                PayerPrison3.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[3] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération4.setVisible(true);
                                }
                                PayerPrison4.setVisible(true);
                            }
                            if(elim!=true) {
                                Lancer_des.setEnabled(true);
                            }
                            if(joueurCourant.prison!=true && joueurCourant.nom!=null && compteurDouble==0) {
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon tirez les dés!");
                            }
                            else if(joueurCourant.prison!=true && joueurCourant.nom!=null) {
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, vous pouvez tirez les dés!");
                            }
                            else {
                                if(joueurCourant.nom!=null) {
                                    zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon essayer de quitter les rattrapages (vous pouvez payer 50 crédits, jouer une carte libération de rattrapages si vous en possédez une ou sinon tenter de faire un double)!");
                                }
                            }
                            plateauJeu.repaint();
                        }
                        plateau.plateaudejeu[8].construireMaison(darties);
                        plateau.plateaudejeu[8].changerLoyer();
                        credits1.setText("Crédits : " + tabJoueurs[0].credits + " ECTS");
                        credits2.setText("Crédits : " + tabJoueurs[1].credits + " ECTS");
                        credits3.setText("Crédits : " + tabJoueurs[2].credits + " ECTS");
                        credits4.setText("Crédits : " + tabJoueurs[3].credits + " ECTS");
                        if(plateau.plateaudejeu[8].maison==5) {
                            Construire2.setEnabled(false);
                        }
                        if(joueurCourant.credits<0) {
                            eliminationJoueur();
                        }
                        darties = false;
                        plateauJeu.repaint();
                    }
                });
                
                Construire3.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        if (darties) {
                            Construire1.setEnabled(false);
                            Construire2.setEnabled(false);
                            Construire3.setEnabled(false);
                            if(joueurCourant.credits<0) {
                                eliminationJoueur();
                            }
                            else {
                                changerJoueur();
                                compteurDouble=0;
                            }
                            while(joueurCourant.droitdejouer==false) {
                                joueurCourant.droitdejouer=true;
                                changerJoueur();
                                compteurDouble=0;
                            }
                            if(joueurCourant.compteurTourPrison==3) {
                                joueurCourant.compteurTourPrison=0;
                                joueurCourant.prison=false;
                                joueurCourant.credits=joueurCourant.credits-50;
                                if(joueurCourant==tabJoueurs[0]) {
                                    tabJoueurs[0].pion.coordX=61;
                                    tabJoueurs[0].pion.coordY=2;
                                    tabJoueurs[0].pion.taille=24;
                                    plateauJeu.orientation1=plateauJeu.haut1;
                                    plateauJeu.repaint();
                                    credits1.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                else if(joueurCourant==tabJoueurs[1]) {
                                    tabJoueurs[1].pion.coordX=18;
                                    tabJoueurs[1].pion.coordY=2;
                                    tabJoueurs[1].pion.taille=24;
                                    plateauJeu.orientation2=plateauJeu.haut2;
                                    plateauJeu.repaint();
                                    credits2.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                else if(joueurCourant==tabJoueurs[2]) {
                                    tabJoueurs[2].pion.coordX=2;
                                    tabJoueurs[2].pion.coordY=20;
                                    tabJoueurs[2].pion.taille=24;
                                    plateauJeu.orientation3=plateauJeu.gauche3;
                                    plateauJeu.repaint();
                                    credits3.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                else if(joueurCourant==tabJoueurs[3]) {
                                    tabJoueurs[3].pion.coordX=2;
                                    tabJoueurs[3].pion.coordY=63;
                                    tabJoueurs[3].pion.taille=24;
                                    plateauJeu.orientation4=plateauJeu.gauche4;
                                    plateauJeu.repaint();
                                    credits4.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n" + joueurCourant.nom + " vous venez de rater vos 3 essais aux rattrapages, vous payez donc 50 crédits et réintégrez le parcours scolaire!");
                            }
                            if(joueurCourant==tabJoueurs[0] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere1.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[1] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere2.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[2] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere3.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[3] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere4.setVisible(true);
                            }
                            if(joueurCourant==tabJoueurs[0] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération1.setVisible(true);
                                }
                                PayerPrison1.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[1] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération2.setVisible(true);
                                }
                                PayerPrison2.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[2] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération3.setVisible(true);
                                }
                                PayerPrison3.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[3] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération4.setVisible(true);
                                }
                                PayerPrison4.setVisible(true);
                            }
                            if(elim!=true) {
                                Lancer_des.setEnabled(true);
                            }
                            if(joueurCourant.prison!=true && joueurCourant.nom!=null && compteurDouble==0) {
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon tirez les dés!");
                            }
                            else if(joueurCourant.prison!=true && joueurCourant.nom!=null) {
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, vous pouvez tirez les dés!");
                            }
                            else {
                                if(joueurCourant.nom!=null) {
                                    zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon essayer de quitter les rattrapages (vous pouvez payer 50 crédits, jouer une carte libération de rattrapages si vous en possédez une ou sinon tenter de faire un double)!");
                                }
                            }
                            plateauJeu.repaint();
                        }
                        plateau.plateaudejeu[9].construireMaison(darties);
                        plateau.plateaudejeu[9].changerLoyer();
                        credits1.setText("Crédits : " + tabJoueurs[0].credits + " ECTS");
                        credits2.setText("Crédits : " + tabJoueurs[1].credits + " ECTS");
                        credits3.setText("Crédits : " + tabJoueurs[2].credits + " ECTS");
                        credits4.setText("Crédits : " + tabJoueurs[3].credits + " ECTS");
                        if(plateau.plateaudejeu[9].maison==5) {
                            Construire3.setEnabled(false);
                        }
                        if(joueurCourant.credits<0) {
                            eliminationJoueur();
                        }
                        darties = false;
                        plateauJeu.repaint();
                    }
                });
                PanelBleuCiel.add(Construire3, new org.netbeans.lib.awtextra.AbsoluteConstraints(450,300,154,30));
                
                
                PanelBleuCiel.repaint();
                BleuCielFrame.repaint();
            }
        });
        
        bleufoncéV = new JButton();
        bleufoncéV.setBounds(0,0,40,40);
        bleufoncéV.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
        bleufoncéV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if (darties) {
                    marronV.setEnabled(false);
                    bleucielV.setEnabled(false);
                    violetV.setEnabled(false);
                    orangeV.setEnabled(false);
                    rougeV.setEnabled(false);
                    jauneV.setEnabled(false);
                    vertV.setEnabled(false);
                    bleufoncéV.setEnabled(false);
                }
                JFrame BleuFonceFrame = new JFrame("Validation dans une salle bleu foncé");
                BleuFonceFrame.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
                BleuFonceFrame.setVisible(true);
                BleuFonceFrame.setBounds(420,100,465,400);
                JPanel PanelBleuFonce = new JPanel();
                PanelBleuFonce.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
                PanelBleuFonce.setBounds(0,0,465,400);
                BleuFonceFrame.add(PanelBleuFonce, new org.netbeans.lib.awtextra.AbsoluteConstraints(0,0,-1,-1));
                JLabel EnergyLab = new JLabel(new ImageIcon("src/salles/Energy Lab.png"));
                JLabel I01 = new JLabel(new ImageIcon("src/salles/I01.png"));
                PanelBleuFonce.add(EnergyLab, new org.netbeans.lib.awtextra.AbsoluteConstraints(50,30,-1,-1));
                PanelBleuFonce.add(I01, new org.netbeans.lib.awtextra.AbsoluteConstraints(250,30, -1,-1));
                
                JButton Construire1 = new JButton("Valider");
                Construire1.setBounds(50,300,154,30);
                Construire1.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
                Construire1.setBackground(new Color(28,55,126));
                Construire1.setForeground(Color.white);
                
                PanelBleuFonce.add(Construire1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50,300,154,30));
                
                if (plateau.plateaudejeu[37].proprietaire != joueurCourant) {
                    Construire1.setEnabled(false);
                }
                else {
                    Construire1.setEnabled(true);
                }
                
                
                JButton Construire2 = new JButton("Valider");
                Construire2.setBounds(250,300,154,30);
                Construire2.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
                Construire2.setBackground(new Color(28,55,126));
                Construire2.setForeground(Color.white);
                Construire2.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        if (darties) {
                            Construire1.setEnabled(false);
                            Construire2.setEnabled(false);
                            if(joueurCourant.credits<0) {
                                eliminationJoueur();
                            }
                            else {
                                changerJoueur();
                                compteurDouble=0;
                            }
                            while(joueurCourant.droitdejouer==false) {
                                joueurCourant.droitdejouer=true;
                                changerJoueur();
                                compteurDouble=0;
                            }
                            if(joueurCourant.compteurTourPrison==3) {
                                joueurCourant.compteurTourPrison=0;
                                joueurCourant.prison=false;
                                joueurCourant.credits=joueurCourant.credits-50;
                                if(joueurCourant==tabJoueurs[0]) {
                                    tabJoueurs[0].pion.coordX=61;
                                    tabJoueurs[0].pion.coordY=2;
                                    tabJoueurs[0].pion.taille=24;
                                    plateauJeu.orientation1=plateauJeu.haut1;
                                    plateauJeu.repaint();
                                    credits1.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                else if(joueurCourant==tabJoueurs[1]) {
                                    tabJoueurs[1].pion.coordX=18;
                                    tabJoueurs[1].pion.coordY=2;
                                    tabJoueurs[1].pion.taille=24;
                                    plateauJeu.orientation2=plateauJeu.haut2;
                                    plateauJeu.repaint();
                                    credits2.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                else if(joueurCourant==tabJoueurs[2]) {
                                    tabJoueurs[2].pion.coordX=2;
                                    tabJoueurs[2].pion.coordY=20;
                                    tabJoueurs[2].pion.taille=24;
                                    plateauJeu.orientation3=plateauJeu.gauche3;
                                    plateauJeu.repaint();
                                    credits3.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                else if(joueurCourant==tabJoueurs[3]) {
                                    tabJoueurs[3].pion.coordX=2;
                                    tabJoueurs[3].pion.coordY=63;
                                    tabJoueurs[3].pion.taille=24;
                                    plateauJeu.orientation4=plateauJeu.gauche4;
                                    plateauJeu.repaint();
                                    credits4.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n" + joueurCourant.nom + " vous venez de rater vos 3 essais aux rattrapages, vous payez donc 50 crédits et réintégrez le parcours scolaire!");
                            }
                            if(joueurCourant==tabJoueurs[0] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere1.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[1] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere2.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[2] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere3.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[3] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere4.setVisible(true);
                            }
                            if(joueurCourant==tabJoueurs[0] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération1.setVisible(true);
                                }
                                PayerPrison1.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[1] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération2.setVisible(true);
                                }
                                PayerPrison2.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[2] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération3.setVisible(true);
                                }
                                PayerPrison3.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[3] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération4.setVisible(true);
                                }
                                PayerPrison4.setVisible(true);
                            }
                            if(elim!=true) {
                                Lancer_des.setEnabled(true);
                            }
                            if(joueurCourant.prison!=true && joueurCourant.nom!=null && compteurDouble==0) {
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon tirez les dés!");
                            }
                            else if(joueurCourant.prison!=true && joueurCourant.nom!=null) {
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, vous pouvez tirez les dés!");
                            }
                            else {
                                if(joueurCourant.nom!=null) {
                                    zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon essayer de quitter les rattrapages (vous pouvez payer 50 crédits, jouer une carte libération de rattrapages si vous en possédez une ou sinon tenter de faire un double)!");
                                }
                            }
                            plateauJeu.repaint();
                        }
                        plateau.plateaudejeu[37].construireMaison(darties);
                        plateau.plateaudejeu[37].changerLoyer();
                        credits1.setText("Crédits : " + tabJoueurs[0].credits + " ECTS");
                        credits2.setText("Crédits : " + tabJoueurs[1].credits + " ECTS");
                        credits3.setText("Crédits : " + tabJoueurs[2].credits + " ECTS");
                        credits4.setText("Crédits : " + tabJoueurs[3].credits + " ECTS");
                        if(plateau.plateaudejeu[37].maison==5) {
                            Construire1.setEnabled(false);
                        }
                        if(joueurCourant.credits<0) {
                            eliminationJoueur();
                        }
                        darties = false;
                        plateauJeu.repaint();
                    }
                });
                
                if (plateau.plateaudejeu[39].proprietaire != joueurCourant) {
                    Construire2.setEnabled(false);
                }
                else {
                    Construire2.setEnabled(true);
                }
                
                Construire1.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        if (darties) {
                            Construire1.setEnabled(false);
                            Construire2.setEnabled(false);
                            if(joueurCourant.credits<0) {
                                eliminationJoueur();
                            }
                            else {
                                changerJoueur();
                                compteurDouble=0;
                            }
                            while(joueurCourant.droitdejouer==false) {
                                joueurCourant.droitdejouer=true;
                                changerJoueur();
                                compteurDouble=0;
                            }
                            if(joueurCourant.compteurTourPrison==3) {
                                joueurCourant.compteurTourPrison=0;
                                joueurCourant.prison=false;
                                joueurCourant.credits=joueurCourant.credits-50;
                                if(joueurCourant==tabJoueurs[0]) {
                                    tabJoueurs[0].pion.coordX=61;
                                    tabJoueurs[0].pion.coordY=2;
                                    tabJoueurs[0].pion.taille=24;
                                    plateauJeu.orientation1=plateauJeu.haut1;
                                    plateauJeu.repaint();
                                    credits1.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                else if(joueurCourant==tabJoueurs[1]) {
                                    tabJoueurs[1].pion.coordX=18;
                                    tabJoueurs[1].pion.coordY=2;
                                    tabJoueurs[1].pion.taille=24;
                                    plateauJeu.orientation2=plateauJeu.haut2;
                                    plateauJeu.repaint();
                                    credits2.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                else if(joueurCourant==tabJoueurs[2]) {
                                    tabJoueurs[2].pion.coordX=2;
                                    tabJoueurs[2].pion.coordY=20;
                                    tabJoueurs[2].pion.taille=24;
                                    plateauJeu.orientation3=plateauJeu.gauche3;
                                    plateauJeu.repaint();
                                    credits3.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                else if(joueurCourant==tabJoueurs[3]) {
                                    tabJoueurs[3].pion.coordX=2;
                                    tabJoueurs[3].pion.coordY=63;
                                    tabJoueurs[3].pion.taille=24;
                                    plateauJeu.orientation4=plateauJeu.gauche4;
                                    plateauJeu.repaint();
                                    credits4.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n" + joueurCourant.nom + " vous venez de rater vos 3 essais aux rattrapages, vous payez donc 50 crédits et réintégrez le parcours scolaire!");
                            }
                            if(joueurCourant==tabJoueurs[0] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere1.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[1] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere2.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[2] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere3.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[3] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere4.setVisible(true);
                            }
                            if(joueurCourant==tabJoueurs[0] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération1.setVisible(true);
                                }
                                PayerPrison1.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[1] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération2.setVisible(true);
                                }
                                PayerPrison2.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[2] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération3.setVisible(true);
                                }
                                PayerPrison3.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[3] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération4.setVisible(true);
                                }
                                PayerPrison4.setVisible(true);
                            }
                            if(elim!=true) {
                                Lancer_des.setEnabled(true);
                            }
                            if(joueurCourant.prison!=true && joueurCourant.nom!=null && compteurDouble==0) {
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon tirez les dés!");
                            }
                            else if(joueurCourant.prison!=true && joueurCourant.nom!=null) {
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, vous pouvez tirez les dés!");
                            }
                            else {
                                if(joueurCourant.nom!=null) {
                                    zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon essayer de quitter les rattrapages (vous pouvez payer 50 crédits, jouer une carte libération de rattrapages si vous en possédez une ou sinon tenter de faire un double)!");
                                }
                            }
                            plateauJeu.repaint();
                        }
                        plateau.plateaudejeu[39].construireMaison(darties);
                        plateau.plateaudejeu[39].changerLoyer();
                        credits1.setText("Crédits : " + tabJoueurs[0].credits + " ECTS");
                        credits2.setText("Crédits : " + tabJoueurs[1].credits + " ECTS");
                        credits3.setText("Crédits : " + tabJoueurs[2].credits + " ECTS");
                        credits4.setText("Crédits : " + tabJoueurs[3].credits + " ECTS");
                        if(plateau.plateaudejeu[39].maison==5) {
                            Construire2.setEnabled(false);
                        }
                        if(joueurCourant.credits<0) {
                            eliminationJoueur();
                        }
                        darties = false;
                        plateauJeu.repaint();
                    }
                });
                
                PanelBleuFonce.add(Construire2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250,300,154,30));
                
                
                PanelBleuFonce.repaint();
                BleuFonceFrame.repaint();
            }
        });
        
        jauneV = new JButton();
        jauneV.setBounds(0,0,40,40);
        jauneV.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
        jauneV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if (darties) {
                    marronV.setEnabled(false);
                    bleucielV.setEnabled(false);
                    violetV.setEnabled(false);
                    orangeV.setEnabled(false);
                    rougeV.setEnabled(false);
                    jauneV.setEnabled(false);
                    vertV.setEnabled(false);
                    bleufoncéV.setEnabled(false);
                }
                JFrame JauneFrame = new JFrame("Validation dans une salle jaune");
                JauneFrame.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
                JauneFrame.setVisible(true);
                JauneFrame.setBounds(420,100,665,400);
                JPanel PanelJaune = new JPanel();
                PanelJaune.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
                PanelJaune.setBounds(0,0,665,400);
                JauneFrame.add(PanelJaune, new org.netbeans.lib.awtextra.AbsoluteConstraints(0,0,-1,-1));
                JLabel H03 = new JLabel(new ImageIcon("src/salles/H03.png"));
                JLabel H05 = new JLabel(new ImageIcon("src/salles/H05.png"));
                JLabel H08 = new JLabel(new ImageIcon("src/salles/H08.png"));
                PanelJaune.add(H03, new org.netbeans.lib.awtextra.AbsoluteConstraints(50,30,-1,-1));
                PanelJaune.add(H05, new org.netbeans.lib.awtextra.AbsoluteConstraints(250,30, -1,-1));
                PanelJaune.add(H08, new org.netbeans.lib.awtextra.AbsoluteConstraints(450,30,-1,-1));
                
                JButton Construire1 = new JButton("Valider");
                Construire1.setBounds(50,300,154,30);
                Construire1.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
                Construire1.setBackground(new Color(255,238,2));
                Construire1.setForeground(Color.white);
               
                PanelJaune.add(Construire1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50,300,154,30));
                
                JButton Construire2 = new JButton("Valider");
                Construire2.setBounds(250,300,154,30);
                Construire2.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
                Construire2.setBackground(new Color(255,238,2));
                Construire2.setForeground(Color.white);
                
                PanelJaune.add(Construire2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250,300,154,30));
                
                JButton Construire3 = new JButton("Valider");
                Construire3.setBounds(250,300,154,30);
                Construire3.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
                Construire3.setBackground(new Color(255,238,2));
                Construire3.setForeground(Color.white);
                Construire3.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        if (darties) {
                            Construire1.setEnabled(false);
                            Construire2.setEnabled(false);
                            Construire3.setEnabled(false);
                            if(joueurCourant.credits<0) {
                                eliminationJoueur();
                            }
                            else {
                                changerJoueur();
                                compteurDouble=0;
                            }
                            while(joueurCourant.droitdejouer==false) {
                                joueurCourant.droitdejouer=true;
                                changerJoueur();
                                compteurDouble=0;
                            }
                            if(joueurCourant.compteurTourPrison==3) {
                                joueurCourant.compteurTourPrison=0;
                                joueurCourant.prison=false;
                                joueurCourant.credits=joueurCourant.credits-50;
                                if(joueurCourant==tabJoueurs[0]) {
                                    tabJoueurs[0].pion.coordX=61;
                                    tabJoueurs[0].pion.coordY=2;
                                    tabJoueurs[0].pion.taille=24;
                                    plateauJeu.orientation1=plateauJeu.haut1;
                                    plateauJeu.repaint();
                                    credits1.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                else if(joueurCourant==tabJoueurs[1]) {
                                    tabJoueurs[1].pion.coordX=18;
                                    tabJoueurs[1].pion.coordY=2;
                                    tabJoueurs[1].pion.taille=24;
                                    plateauJeu.orientation2=plateauJeu.haut2;
                                    plateauJeu.repaint();
                                    credits2.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                else if(joueurCourant==tabJoueurs[2]) {
                                    tabJoueurs[2].pion.coordX=2;
                                    tabJoueurs[2].pion.coordY=20;
                                    tabJoueurs[2].pion.taille=24;
                                    plateauJeu.orientation3=plateauJeu.gauche3;
                                    plateauJeu.repaint();
                                    credits3.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                else if(joueurCourant==tabJoueurs[3]) {
                                    tabJoueurs[3].pion.coordX=2;
                                    tabJoueurs[3].pion.coordY=63;
                                    tabJoueurs[3].pion.taille=24;
                                    plateauJeu.orientation4=plateauJeu.gauche4;
                                    plateauJeu.repaint();
                                    credits4.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n" + joueurCourant.nom + " vous venez de rater vos 3 essais aux rattrapages, vous payez donc 50 crédits et réintégrez le parcours scolaire!");
                            }
                            if(joueurCourant==tabJoueurs[0] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere1.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[1] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere2.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[2] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere3.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[3] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere4.setVisible(true);
                            }
                            if(joueurCourant==tabJoueurs[0] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération1.setVisible(true);
                                }
                                PayerPrison1.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[1] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération2.setVisible(true);
                                }
                                PayerPrison2.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[2] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération3.setVisible(true);
                                }
                                PayerPrison3.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[3] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération4.setVisible(true);
                                }
                                PayerPrison4.setVisible(true);
                            }
                            if(elim!=true) {
                                Lancer_des.setEnabled(true);
                            }
                            if(joueurCourant.prison!=true && joueurCourant.nom!=null && compteurDouble==0) {
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon tirez les dés!");
                            }
                            else if(joueurCourant.prison!=true && joueurCourant.nom!=null) {
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, vous pouvez tirez les dés!");
                            }
                            else {
                                if(joueurCourant.nom!=null) {
                                    zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon essayer de quitter les rattrapages (vous pouvez payer 50 crédits, jouer une carte libération de rattrapages si vous en possédez une ou sinon tenter de faire un double)!");
                                }
                            }
                            plateauJeu.repaint();
                        }
                        plateau.plateaudejeu[29].construireMaison(darties);
                        plateau.plateaudejeu[29].changerLoyer();
                        credits1.setText("Crédits : " + tabJoueurs[0].credits + " ECTS");
                        credits2.setText("Crédits : " + tabJoueurs[1].credits + " ECTS");
                        credits3.setText("Crédits : " + tabJoueurs[2].credits + " ECTS");
                        credits4.setText("Crédits : " + tabJoueurs[3].credits + " ECTS");
                        if(plateau.plateaudejeu[29].maison==5) {
                            Construire3.setEnabled(false);
                        }
                        if(joueurCourant.credits<0) {
                            eliminationJoueur();
                        }
                        darties = false;
                        plateauJeu.repaint();
                    }
                });
                
                Construire1.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        if (darties) {
                            Construire1.setEnabled(false);
                            Construire2.setEnabled(false);
                            Construire3.setEnabled(false);
                            if(joueurCourant.credits<0) {
                                eliminationJoueur();
                            }
                            else {
                                changerJoueur();
                                compteurDouble=0;
                            }
                            while(joueurCourant.droitdejouer==false) {
                                joueurCourant.droitdejouer=true;
                                changerJoueur();
                                compteurDouble=0;
                            }
                            if(joueurCourant.compteurTourPrison==3) {
                                joueurCourant.compteurTourPrison=0;
                                joueurCourant.prison=false;
                                joueurCourant.credits=joueurCourant.credits-50;
                                if(joueurCourant==tabJoueurs[0]) {
                                    tabJoueurs[0].pion.coordX=61;
                                    tabJoueurs[0].pion.coordY=2;
                                    tabJoueurs[0].pion.taille=24;
                                    plateauJeu.orientation1=plateauJeu.haut1;
                                    plateauJeu.repaint();
                                    credits1.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                else if(joueurCourant==tabJoueurs[1]) {
                                    tabJoueurs[1].pion.coordX=18;
                                    tabJoueurs[1].pion.coordY=2;
                                    tabJoueurs[1].pion.taille=24;
                                    plateauJeu.orientation2=plateauJeu.haut2;
                                    plateauJeu.repaint();
                                    credits2.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                else if(joueurCourant==tabJoueurs[2]) {
                                    tabJoueurs[2].pion.coordX=2;
                                    tabJoueurs[2].pion.coordY=20;
                                    tabJoueurs[2].pion.taille=24;
                                    plateauJeu.orientation3=plateauJeu.gauche3;
                                    plateauJeu.repaint();
                                    credits3.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                else if(joueurCourant==tabJoueurs[3]) {
                                    tabJoueurs[3].pion.coordX=2;
                                    tabJoueurs[3].pion.coordY=63;
                                    tabJoueurs[3].pion.taille=24;
                                    plateauJeu.orientation4=plateauJeu.gauche4;
                                    plateauJeu.repaint();
                                    credits4.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n" + joueurCourant.nom + " vous venez de rater vos 3 essais aux rattrapages, vous payez donc 50 crédits et réintégrez le parcours scolaire!");
                            }
                            if(joueurCourant==tabJoueurs[0] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere1.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[1] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere2.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[2] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere3.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[3] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere4.setVisible(true);
                            }
                            if(joueurCourant==tabJoueurs[0] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération1.setVisible(true);
                                }
                                PayerPrison1.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[1] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération2.setVisible(true);
                                }
                                PayerPrison2.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[2] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération3.setVisible(true);
                                }
                                PayerPrison3.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[3] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération4.setVisible(true);
                                }
                                PayerPrison4.setVisible(true);
                            }
                            if(elim!=true) {
                                Lancer_des.setEnabled(true);
                            }
                            if(joueurCourant.prison!=true && joueurCourant.nom!=null && compteurDouble==0) {
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon tirez les dés!");
                            }
                            else if(joueurCourant.prison!=true && joueurCourant.nom!=null) {
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, vous pouvez tirez les dés!");
                            }
                            else {
                                if(joueurCourant.nom!=null) {
                                    zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon essayer de quitter les rattrapages (vous pouvez payer 50 crédits, jouer une carte libération de rattrapages si vous en possédez une ou sinon tenter de faire un double)!");
                                }
                            }
                            plateauJeu.repaint();
                        }
                        plateau.plateaudejeu[26].construireMaison(darties);
                        plateau.plateaudejeu[26].changerLoyer();
                        credits1.setText("Crédits : " + tabJoueurs[0].credits + " ECTS");
                        credits2.setText("Crédits : " + tabJoueurs[1].credits + " ECTS");
                        credits3.setText("Crédits : " + tabJoueurs[2].credits + " ECTS");
                        credits4.setText("Crédits : " + tabJoueurs[3].credits + " ECTS");
                        if(plateau.plateaudejeu[26].maison==5) {
                            Construire1.setEnabled(false);
                        }
                        if(joueurCourant.credits<0) {
                            eliminationJoueur();
                        }
                        darties = false;
                        plateauJeu.repaint();
                    }
                });
                
                Construire2.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        if (darties) {
                            Construire1.setEnabled(false);
                            Construire2.setEnabled(false);
                            Construire3.setEnabled(false);
                            if(joueurCourant.credits<0) {
                                eliminationJoueur();
                            }
                            else {
                                changerJoueur();
                                compteurDouble=0;
                            }
                            while(joueurCourant.droitdejouer==false) {
                                joueurCourant.droitdejouer=true;
                                changerJoueur();
                                compteurDouble=0;
                            }
                            if(joueurCourant.compteurTourPrison==3) {
                                joueurCourant.compteurTourPrison=0;
                                joueurCourant.prison=false;
                                joueurCourant.credits=joueurCourant.credits-50;
                                if(joueurCourant==tabJoueurs[0]) {
                                    tabJoueurs[0].pion.coordX=61;
                                    tabJoueurs[0].pion.coordY=2;
                                    tabJoueurs[0].pion.taille=24;
                                    plateauJeu.orientation1=plateauJeu.haut1;
                                    plateauJeu.repaint();
                                    credits1.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                else if(joueurCourant==tabJoueurs[1]) {
                                    tabJoueurs[1].pion.coordX=18;
                                    tabJoueurs[1].pion.coordY=2;
                                    tabJoueurs[1].pion.taille=24;
                                    plateauJeu.orientation2=plateauJeu.haut2;
                                    plateauJeu.repaint();
                                    credits2.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                else if(joueurCourant==tabJoueurs[2]) {
                                    tabJoueurs[2].pion.coordX=2;
                                    tabJoueurs[2].pion.coordY=20;
                                    tabJoueurs[2].pion.taille=24;
                                    plateauJeu.orientation3=plateauJeu.gauche3;
                                    plateauJeu.repaint();
                                    credits3.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                else if(joueurCourant==tabJoueurs[3]) {
                                    tabJoueurs[3].pion.coordX=2;
                                    tabJoueurs[3].pion.coordY=63;
                                    tabJoueurs[3].pion.taille=24;
                                    plateauJeu.orientation4=plateauJeu.gauche4;
                                    plateauJeu.repaint();
                                    credits4.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n" + joueurCourant.nom + " vous venez de rater vos 3 essais aux rattrapages, vous payez donc 50 crédits et réintégrez le parcours scolaire!");
                            }
                            if(joueurCourant==tabJoueurs[0] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere1.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[1] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere2.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[2] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere3.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[3] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere4.setVisible(true);
                            }
                            if(joueurCourant==tabJoueurs[0] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération1.setVisible(true);
                                }
                                PayerPrison1.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[1] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération2.setVisible(true);
                                }
                                PayerPrison2.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[2] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération3.setVisible(true);
                                }
                                PayerPrison3.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[3] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération4.setVisible(true);
                                }
                                PayerPrison4.setVisible(true);
                            }
                            if(elim!=true) {
                                Lancer_des.setEnabled(true);
                            }
                            if(joueurCourant.prison!=true && joueurCourant.nom!=null && compteurDouble==0) {
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon tirez les dés!");
                            }
                            else if(joueurCourant.prison!=true && joueurCourant.nom!=null) {
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, vous pouvez tirez les dés!");
                            }
                            else {
                                if(joueurCourant.nom!=null) {
                                    zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon essayer de quitter les rattrapages (vous pouvez payer 50 crédits, jouer une carte libération de rattrapages si vous en possédez une ou sinon tenter de faire un double)!");
                                }
                            }
                            plateauJeu.repaint();
                        }
                        plateau.plateaudejeu[27].construireMaison(darties);
                        plateau.plateaudejeu[27].changerLoyer();
                        credits1.setText("Crédits : " + tabJoueurs[0].credits + " ECTS");
                        credits2.setText("Crédits : " + tabJoueurs[1].credits + " ECTS");
                        credits3.setText("Crédits : " + tabJoueurs[2].credits + " ECTS");
                        credits4.setText("Crédits : " + tabJoueurs[3].credits + " ECTS");
                        if(plateau.plateaudejeu[27].maison==5) {
                            Construire2.setEnabled(false);
                        }
                        if(joueurCourant.credits<0) {
                            eliminationJoueur();
                        }
                        darties = false;
                        plateauJeu.repaint();
                    }
                });
                
                if (plateau.plateaudejeu[29].proprietaire != joueurCourant) {
                    Construire3.setEnabled(false);
                }
                else {
                    Construire3.setEnabled(true);
                }
                if (plateau.plateaudejeu[27].proprietaire != joueurCourant) {
                    Construire2.setEnabled(false);
                }
                else {
                    Construire2.setEnabled(true);
                }
                if (plateau.plateaudejeu[26].proprietaire != joueurCourant) {
                    Construire1.setEnabled(false);
                }
                else {
                    Construire1.setEnabled(true);
                }
                PanelJaune.add(Construire3, new org.netbeans.lib.awtextra.AbsoluteConstraints(450,300,154,30));
                
                
                PanelJaune.repaint();
                JauneFrame.repaint();
            }
        });
        
        orangeV = new JButton();
        orangeV.setBounds(0,0,40,40);
        orangeV.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
        orangeV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if (darties) {
                    marronV.setEnabled(false);
                    bleucielV.setEnabled(false);
                    violetV.setEnabled(false);
                    orangeV.setEnabled(false);
                    rougeV.setEnabled(false);
                    jauneV.setEnabled(false);
                    vertV.setEnabled(false);
                    bleufoncéV.setEnabled(false);
                }
                JFrame OrangeFrame = new JFrame("Validation dans une salle orange");
                OrangeFrame.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
                OrangeFrame.setVisible(true);
                OrangeFrame.setBounds(420,100,665,400);
                JPanel PanelOrange = new JPanel();
                PanelOrange.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
                PanelOrange.setBounds(0,0,665,400);
                OrangeFrame.add(PanelOrange, new org.netbeans.lib.awtextra.AbsoluteConstraints(0,0,-1,-1));
                JLabel M101 = new JLabel(new ImageIcon("src/salles/M101.png"));
                JLabel M102 = new JLabel(new ImageIcon("src/salles/M102.png"));
                JLabel M103 = new JLabel(new ImageIcon("src/salles/M103.png"));
                PanelOrange.add(M101, new org.netbeans.lib.awtextra.AbsoluteConstraints(50,30,-1,-1));
                PanelOrange.add(M102, new org.netbeans.lib.awtextra.AbsoluteConstraints(250,30, -1,-1));
                PanelOrange.add(M103, new org.netbeans.lib.awtextra.AbsoluteConstraints(450,30,-1,-1));
                
                JButton Construire1 = new JButton("Valider");
                Construire1.setBounds(50,300,154,30);
                Construire1.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
                Construire1.setBackground(new Color(242,148,0));
                Construire1.setForeground(Color.white);
                if (plateau.plateaudejeu[16].proprietaire != joueurCourant) {
                    Construire1.setEnabled(false);
                }
                else {
                    Construire1.setEnabled(true);
                }
                PanelOrange.add(Construire1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50,300,154,30));
                
                JButton Construire2 = new JButton("Valider");
                Construire2.setBounds(250,300,154,30);
                Construire2.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
                Construire2.setBackground(new Color(242,148,0));
                Construire2.setForeground(Color.white);
                if (plateau.plateaudejeu[18].proprietaire != joueurCourant) {
                    Construire2.setEnabled(false);
                }
                else {
                    Construire2.setEnabled(true);
                }
                PanelOrange.add(Construire2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250,300,154,30));
                
                JButton Construire3 = new JButton("Valider");
                Construire3.setBounds(250,300,154,30);
                Construire3.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
                Construire3.setBackground(new Color(242,148,0));
                Construire3.setForeground(Color.white);
                if (plateau.plateaudejeu[19].proprietaire != joueurCourant) {
                    Construire3.setEnabled(false);
                }
                else {
                    Construire3.setEnabled(true);
                }
                Construire3.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        if (darties) {
                            Construire1.setEnabled(false);
                            Construire2.setEnabled(false);
                            Construire3.setEnabled(false);
                            if(joueurCourant.credits<0) {
                                eliminationJoueur();
                            }
                            else {
                                changerJoueur();
                                compteurDouble=0;
                            }
                            while(joueurCourant.droitdejouer==false) {
                                joueurCourant.droitdejouer=true;
                                changerJoueur();
                                compteurDouble=0;
                            }
                            if(joueurCourant.compteurTourPrison==3) {
                                joueurCourant.compteurTourPrison=0;
                                joueurCourant.prison=false;
                                joueurCourant.credits=joueurCourant.credits-50;
                                if(joueurCourant==tabJoueurs[0]) {
                                    tabJoueurs[0].pion.coordX=61;
                                    tabJoueurs[0].pion.coordY=2;
                                    tabJoueurs[0].pion.taille=24;
                                    plateauJeu.orientation1=plateauJeu.haut1;
                                    plateauJeu.repaint();
                                    credits1.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                else if(joueurCourant==tabJoueurs[1]) {
                                    tabJoueurs[1].pion.coordX=18;
                                    tabJoueurs[1].pion.coordY=2;
                                    tabJoueurs[1].pion.taille=24;
                                    plateauJeu.orientation2=plateauJeu.haut2;
                                    plateauJeu.repaint();
                                    credits2.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                else if(joueurCourant==tabJoueurs[2]) {
                                    tabJoueurs[2].pion.coordX=2;
                                    tabJoueurs[2].pion.coordY=20;
                                    tabJoueurs[2].pion.taille=24;
                                    plateauJeu.orientation3=plateauJeu.gauche3;
                                    plateauJeu.repaint();
                                    credits3.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                else if(joueurCourant==tabJoueurs[3]) {
                                    tabJoueurs[3].pion.coordX=2;
                                    tabJoueurs[3].pion.coordY=63;
                                    tabJoueurs[3].pion.taille=24;
                                    plateauJeu.orientation4=plateauJeu.gauche4;
                                    plateauJeu.repaint();
                                    credits4.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n" + joueurCourant.nom + " vous venez de rater vos 3 essais aux rattrapages, vous payez donc 50 crédits et réintégrez le parcours scolaire!");
                            }
                            if(joueurCourant==tabJoueurs[0] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere1.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[1] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere2.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[2] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere3.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[3] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere4.setVisible(true);
                            }
                            if(joueurCourant==tabJoueurs[0] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération1.setVisible(true);
                                }
                                PayerPrison1.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[1] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération2.setVisible(true);
                                }
                                PayerPrison2.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[2] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération3.setVisible(true);
                                }
                                PayerPrison3.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[3] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération4.setVisible(true);
                                }
                                PayerPrison4.setVisible(true);
                            }
                            if(elim!=true) {
                                Lancer_des.setEnabled(true);
                            }
                            if(joueurCourant.prison!=true && joueurCourant.nom!=null && compteurDouble==0) {
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon tirez les dés!");
                            }
                            else if(joueurCourant.prison!=true && joueurCourant.nom!=null) {
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, vous pouvez tirez les dés!");
                            }
                            else {
                                if(joueurCourant.nom!=null) {
                                    zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon essayer de quitter les rattrapages (vous pouvez payer 50 crédits, jouer une carte libération de rattrapages si vous en possédez une ou sinon tenter de faire un double)!");
                                }
                            }
                            plateauJeu.repaint();
                        }
                        plateau.plateaudejeu[19].construireMaison(darties);
                        plateau.plateaudejeu[19].changerLoyer();
                        credits1.setText("Crédits : " + tabJoueurs[0].credits + " ECTS");
                        credits2.setText("Crédits : " + tabJoueurs[1].credits + " ECTS");
                        credits3.setText("Crédits : " + tabJoueurs[2].credits + " ECTS");
                        credits4.setText("Crédits : " + tabJoueurs[3].credits + " ECTS");
                        if(plateau.plateaudejeu[19].maison==5) {
                            Construire3.setEnabled(false);
                        }
                        if(joueurCourant.credits<0) {
                            eliminationJoueur();
                        }
                        darties = false;
                        plateauJeu.repaint();
                    }
                });
                
                Construire1.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        if (darties) {
                            Construire1.setEnabled(false);
                            Construire2.setEnabled(false);
                            Construire3.setEnabled(false);
                            if(joueurCourant.credits<0) {
                                eliminationJoueur();
                            }
                            else {
                                changerJoueur();
                                compteurDouble=0;
                            }
                            while(joueurCourant.droitdejouer==false) {
                                joueurCourant.droitdejouer=true;
                                changerJoueur();
                                compteurDouble=0;
                            }
                            if(joueurCourant.compteurTourPrison==3) {
                                joueurCourant.compteurTourPrison=0;
                                joueurCourant.prison=false;
                                joueurCourant.credits=joueurCourant.credits-50;
                                if(joueurCourant==tabJoueurs[0]) {
                                    tabJoueurs[0].pion.coordX=61;
                                    tabJoueurs[0].pion.coordY=2;
                                    tabJoueurs[0].pion.taille=24;
                                    plateauJeu.orientation1=plateauJeu.haut1;
                                    plateauJeu.repaint();
                                    credits1.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                else if(joueurCourant==tabJoueurs[1]) {
                                    tabJoueurs[1].pion.coordX=18;
                                    tabJoueurs[1].pion.coordY=2;
                                    tabJoueurs[1].pion.taille=24;
                                    plateauJeu.orientation2=plateauJeu.haut2;
                                    plateauJeu.repaint();
                                    credits2.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                else if(joueurCourant==tabJoueurs[2]) {
                                    tabJoueurs[2].pion.coordX=2;
                                    tabJoueurs[2].pion.coordY=20;
                                    tabJoueurs[2].pion.taille=24;
                                    plateauJeu.orientation3=plateauJeu.gauche3;
                                    plateauJeu.repaint();
                                    credits3.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                else if(joueurCourant==tabJoueurs[3]) {
                                    tabJoueurs[3].pion.coordX=2;
                                    tabJoueurs[3].pion.coordY=63;
                                    tabJoueurs[3].pion.taille=24;
                                    plateauJeu.orientation4=plateauJeu.gauche4;
                                    plateauJeu.repaint();
                                    credits4.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n" + joueurCourant.nom + " vous venez de rater vos 3 essais aux rattrapages, vous payez donc 50 crédits et réintégrez le parcours scolaire!");
                            }
                            if(joueurCourant==tabJoueurs[0] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere1.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[1] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere2.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[2] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere3.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[3] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere4.setVisible(true);
                            }
                            if(joueurCourant==tabJoueurs[0] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération1.setVisible(true);
                                }
                                PayerPrison1.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[1] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération2.setVisible(true);
                                }
                                PayerPrison2.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[2] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération3.setVisible(true);
                                }
                                PayerPrison3.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[3] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération4.setVisible(true);
                                }
                                PayerPrison4.setVisible(true);
                            }
                            if(elim!=true) {
                                Lancer_des.setEnabled(true);
                            }
                            if(joueurCourant.prison!=true && joueurCourant.nom!=null && compteurDouble==0) {
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon tirez les dés!");
                            }
                            else if(joueurCourant.prison!=true && joueurCourant.nom!=null) {
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, vous pouvez tirez les dés!");
                            }
                            else {
                                if(joueurCourant.nom!=null) {
                                    zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon essayer de quitter les rattrapages (vous pouvez payer 50 crédits, jouer une carte libération de rattrapages si vous en possédez une ou sinon tenter de faire un double)!");
                                }
                            }
                            plateauJeu.repaint();
                        }
                        plateau.plateaudejeu[16].construireMaison(darties);
                        plateau.plateaudejeu[16].changerLoyer();
                        credits1.setText("Crédits : " + tabJoueurs[0].credits + " ECTS");
                        credits2.setText("Crédits : " + tabJoueurs[1].credits + " ECTS");
                        credits3.setText("Crédits : " + tabJoueurs[2].credits + " ECTS");
                        credits4.setText("Crédits : " + tabJoueurs[3].credits + " ECTS");
                        if(plateau.plateaudejeu[16].maison==5) {
                            Construire1.setEnabled(false);
                        }
                        if(joueurCourant.credits<0) {
                            eliminationJoueur();
                        }
                        darties = false;
                        plateauJeu.repaint();
                    
                    }
                });
                
                Construire2.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        if (darties) {
                            Construire1.setEnabled(false);
                            Construire2.setEnabled(false);
                            Construire3.setEnabled(false);
                            if(joueurCourant.credits<0) {
                                eliminationJoueur();
                            }
                            else {
                                changerJoueur();
                                compteurDouble=0;
                            }
                            while(joueurCourant.droitdejouer==false) {
                                joueurCourant.droitdejouer=true;
                                changerJoueur();
                                compteurDouble=0;
                            }
                            if(joueurCourant.compteurTourPrison==3) {
                                joueurCourant.compteurTourPrison=0;
                                joueurCourant.prison=false;
                                joueurCourant.credits=joueurCourant.credits-50;
                                if(joueurCourant==tabJoueurs[0]) {
                                    tabJoueurs[0].pion.coordX=61;
                                    tabJoueurs[0].pion.coordY=2;
                                    tabJoueurs[0].pion.taille=24;
                                    plateauJeu.orientation1=plateauJeu.haut1;
                                    plateauJeu.repaint();
                                    credits1.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                else if(joueurCourant==tabJoueurs[1]) {
                                    tabJoueurs[1].pion.coordX=18;
                                    tabJoueurs[1].pion.coordY=2;
                                    tabJoueurs[1].pion.taille=24;
                                    plateauJeu.orientation2=plateauJeu.haut2;
                                    plateauJeu.repaint();
                                    credits2.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                else if(joueurCourant==tabJoueurs[2]) {
                                    tabJoueurs[2].pion.coordX=2;
                                    tabJoueurs[2].pion.coordY=20;
                                    tabJoueurs[2].pion.taille=24;
                                    plateauJeu.orientation3=plateauJeu.gauche3;
                                    plateauJeu.repaint();
                                    credits3.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                else if(joueurCourant==tabJoueurs[3]) {
                                    tabJoueurs[3].pion.coordX=2;
                                    tabJoueurs[3].pion.coordY=63;
                                    tabJoueurs[3].pion.taille=24;
                                    plateauJeu.orientation4=plateauJeu.gauche4;
                                    plateauJeu.repaint();
                                    credits4.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n" + joueurCourant.nom + " vous venez de rater vos 3 essais aux rattrapages, vous payez donc 50 crédits et réintégrez le parcours scolaire!");
                            }
                            if(joueurCourant==tabJoueurs[0] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere1.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[1] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere2.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[2] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere3.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[3] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere4.setVisible(true);
                            }
                            if(joueurCourant==tabJoueurs[0] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération1.setVisible(true);
                                }
                                PayerPrison1.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[1] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération2.setVisible(true);
                                }
                                PayerPrison2.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[2] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération3.setVisible(true);
                                }
                                PayerPrison3.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[3] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération4.setVisible(true);
                                }
                                PayerPrison4.setVisible(true);
                            }
                            if(elim!=true) {
                                Lancer_des.setEnabled(true);
                            }
                            if(joueurCourant.prison!=true && joueurCourant.nom!=null && compteurDouble==0) {
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon tirez les dés!");
                            }
                            else if(joueurCourant.prison!=true && joueurCourant.nom!=null) {
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, vous pouvez tirez les dés!");
                            }
                            else {
                                if(joueurCourant.nom!=null) {
                                    zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon essayer de quitter les rattrapages (vous pouvez payer 50 crédits, jouer une carte libération de rattrapages si vous en possédez une ou sinon tenter de faire un double)!");
                                }
                            }
                            plateauJeu.repaint();
                        }
                        plateau.plateaudejeu[18].construireMaison(darties);
                        plateau.plateaudejeu[18].changerLoyer();
                        credits1.setText("Crédits : " + tabJoueurs[0].credits + " ECTS");
                        credits2.setText("Crédits : " + tabJoueurs[1].credits + " ECTS");
                        credits3.setText("Crédits : " + tabJoueurs[2].credits + " ECTS");
                        credits4.setText("Crédits : " + tabJoueurs[3].credits + " ECTS");
                        if(plateau.plateaudejeu[18].maison==5) {
                            Construire2.setEnabled(false);
                        }
                        if(joueurCourant.credits<0) {
                            eliminationJoueur();
                        }
                        darties = false;
                        plateauJeu.repaint();
                    }
                });
                
                PanelOrange.add(Construire3, new org.netbeans.lib.awtextra.AbsoluteConstraints(450,300,154,30));
                
                
                PanelOrange.repaint();
                OrangeFrame.repaint();
            }
        });
        
        rougeV = new JButton();
        rougeV.setBounds(0,0,40,40);
        rougeV.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
        rougeV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if (darties) {
                    marronV.setEnabled(false);
                    bleucielV.setEnabled(false);
                    violetV.setEnabled(false);
                    orangeV.setEnabled(false);
                    rougeV.setEnabled(false);
                    jauneV.setEnabled(false);
                    vertV.setEnabled(false);
                    bleufoncéV.setEnabled(false);
                }
                JFrame RougeFrame = new JFrame("Validation dans une salle rouge");
                RougeFrame.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
                RougeFrame.setVisible(true);
                RougeFrame.setBounds(420,100,665,400);
                JPanel PanelRouge = new JPanel();
                PanelRouge.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
                PanelRouge.setBounds(0,0,665,400);
                RougeFrame.add(PanelRouge, new org.netbeans.lib.awtextra.AbsoluteConstraints(0,0,-1,-1));
                JLabel Bulle1 = new JLabel(new ImageIcon("src/salles/Bulle1.png"));
                JLabel Bulle2 = new JLabel(new ImageIcon("src/salles/Bulle2.png"));
                JLabel Bulle3 = new JLabel(new ImageIcon("src/salles/Bulle3.png"));
                PanelRouge.add(Bulle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50,30,-1,-1));
                PanelRouge.add(Bulle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250,30, -1,-1));
                PanelRouge.add(Bulle3, new org.netbeans.lib.awtextra.AbsoluteConstraints(450,30,-1,-1));
                
                JButton Construire1 = new JButton("Valider");
                Construire1.setBounds(50,300,154,30);
                Construire1.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
                Construire1.setBackground(new Color(227,27,0));
                Construire1.setForeground(Color.white);
                if (plateau.plateaudejeu[21].proprietaire != joueurCourant) {
                    Construire1.setEnabled(false);
                }
                else {
                    Construire1.setEnabled(true);
                }
                PanelRouge.add(Construire1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50,300,154,30));
                
                JButton Construire2 = new JButton("Valider");
                Construire2.setBounds(250,300,154,30);
                Construire2.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
                Construire2.setBackground(new Color(227,27,0));
                Construire2.setForeground(Color.white);
                if (plateau.plateaudejeu[23].proprietaire != joueurCourant) {
                    Construire2.setEnabled(false);
                }
                else {
                    Construire2.setEnabled(true);
                }
                PanelRouge.add(Construire2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250,300,154,30));
                
                JButton Construire3 = new JButton("Valider");
                Construire3.setBounds(250,300,154,30);
                Construire3.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
                Construire3.setBackground(new Color(227,27,0));
                Construire3.setForeground(Color.white);
                if (plateau.plateaudejeu[24].proprietaire != joueurCourant) {
                    Construire3.setEnabled(false);
                }
                else {
                    Construire3.setEnabled(true);
                }
                Construire3.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        if (darties) {
                            Construire1.setEnabled(false);
                            Construire2.setEnabled(false);
                            Construire3.setEnabled(false);
                            if(joueurCourant.credits<0) {
                                eliminationJoueur();
                            }
                            else {
                                changerJoueur();
                                compteurDouble=0;
                            }
                            while(joueurCourant.droitdejouer==false) {
                                joueurCourant.droitdejouer=true;
                                changerJoueur();
                                compteurDouble=0;
                            }
                            if(joueurCourant.compteurTourPrison==3) {
                                joueurCourant.compteurTourPrison=0;
                                joueurCourant.prison=false;
                                joueurCourant.credits=joueurCourant.credits-50;
                                if(joueurCourant==tabJoueurs[0]) {
                                    tabJoueurs[0].pion.coordX=61;
                                    tabJoueurs[0].pion.coordY=2;
                                    tabJoueurs[0].pion.taille=24;
                                    plateauJeu.orientation1=plateauJeu.haut1;
                                    plateauJeu.repaint();
                                    credits1.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                else if(joueurCourant==tabJoueurs[1]) {
                                    tabJoueurs[1].pion.coordX=18;
                                    tabJoueurs[1].pion.coordY=2;
                                    tabJoueurs[1].pion.taille=24;
                                    plateauJeu.orientation2=plateauJeu.haut2;
                                    plateauJeu.repaint();
                                    credits2.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                else if(joueurCourant==tabJoueurs[2]) {
                                    tabJoueurs[2].pion.coordX=2;
                                    tabJoueurs[2].pion.coordY=20;
                                    tabJoueurs[2].pion.taille=24;
                                    plateauJeu.orientation3=plateauJeu.gauche3;
                                    plateauJeu.repaint();
                                    credits3.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                else if(joueurCourant==tabJoueurs[3]) {
                                    tabJoueurs[3].pion.coordX=2;
                                    tabJoueurs[3].pion.coordY=63;
                                    tabJoueurs[3].pion.taille=24;
                                    plateauJeu.orientation4=plateauJeu.gauche4;
                                    plateauJeu.repaint();
                                    credits4.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n" + joueurCourant.nom + " vous venez de rater vos 3 essais aux rattrapages, vous payez donc 50 crédits et réintégrez le parcours scolaire!");
                            }
                            if(joueurCourant==tabJoueurs[0] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere1.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[1] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere2.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[2] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere3.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[3] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere4.setVisible(true);
                            }
                            if(joueurCourant==tabJoueurs[0] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération1.setVisible(true);
                                }
                                PayerPrison1.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[1] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération2.setVisible(true);
                                }
                                PayerPrison2.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[2] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération3.setVisible(true);
                                }
                                PayerPrison3.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[3] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération4.setVisible(true);
                                }
                                PayerPrison4.setVisible(true);
                            }
                            if(elim!=true) {
                                Lancer_des.setEnabled(true);
                            }
                            if(joueurCourant.prison!=true && joueurCourant.nom!=null && compteurDouble==0) {
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon tirez les dés!");
                            }
                            else if(joueurCourant.prison!=true && joueurCourant.nom!=null) {
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, vous pouvez tirez les dés!");
                            }
                            else {
                                if(joueurCourant.nom!=null) {
                                    zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon essayer de quitter les rattrapages (vous pouvez payer 50 crédits, jouer une carte libération de rattrapages si vous en possédez une ou sinon tenter de faire un double)!");
                                }
                            }
                            plateauJeu.repaint();
                        }
                        plateau.plateaudejeu[24].construireMaison(darties);
                        plateau.plateaudejeu[24].changerLoyer();
                        credits1.setText("Crédits : " + tabJoueurs[0].credits + " ECTS");
                        credits2.setText("Crédits : " + tabJoueurs[1].credits + " ECTS");
                        credits3.setText("Crédits : " + tabJoueurs[2].credits + " ECTS");
                        credits4.setText("Crédits : " + tabJoueurs[3].credits + " ECTS");
                        if(plateau.plateaudejeu[24].maison==5) {
                            Construire3.setEnabled(false);
                        }
                        if(joueurCourant.credits<0) {
                            eliminationJoueur();
                        }
                        darties = false;
                        plateauJeu.repaint();
                    }
                });
                
                 Construire1.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        if (darties) {
                            Construire1.setEnabled(false);
                            Construire2.setEnabled(false);
                            Construire3.setEnabled(false);
                            if(joueurCourant.credits<0) {
                                eliminationJoueur();
                            }
                            else {
                                changerJoueur();
                                compteurDouble=0;
                            }
                            while(joueurCourant.droitdejouer==false) {
                                joueurCourant.droitdejouer=true;
                                changerJoueur();
                                compteurDouble=0;
                            }
                            if(joueurCourant.compteurTourPrison==3) {
                                joueurCourant.compteurTourPrison=0;
                                joueurCourant.prison=false;
                                joueurCourant.credits=joueurCourant.credits-50;
                                if(joueurCourant==tabJoueurs[0]) {
                                    tabJoueurs[0].pion.coordX=61;
                                    tabJoueurs[0].pion.coordY=2;
                                    tabJoueurs[0].pion.taille=24;
                                    plateauJeu.orientation1=plateauJeu.haut1;
                                    plateauJeu.repaint();
                                    credits1.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                else if(joueurCourant==tabJoueurs[1]) {
                                    tabJoueurs[1].pion.coordX=18;
                                    tabJoueurs[1].pion.coordY=2;
                                    tabJoueurs[1].pion.taille=24;
                                    plateauJeu.orientation2=plateauJeu.haut2;
                                    plateauJeu.repaint();
                                    credits2.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                else if(joueurCourant==tabJoueurs[2]) {
                                    tabJoueurs[2].pion.coordX=2;
                                    tabJoueurs[2].pion.coordY=20;
                                    tabJoueurs[2].pion.taille=24;
                                    plateauJeu.orientation3=plateauJeu.gauche3;
                                    plateauJeu.repaint();
                                    credits3.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                else if(joueurCourant==tabJoueurs[3]) {
                                    tabJoueurs[3].pion.coordX=2;
                                    tabJoueurs[3].pion.coordY=63;
                                    tabJoueurs[3].pion.taille=24;
                                    plateauJeu.orientation4=plateauJeu.gauche4;
                                    plateauJeu.repaint();
                                    credits4.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n" + joueurCourant.nom + " vous venez de rater vos 3 essais aux rattrapages, vous payez donc 50 crédits et réintégrez le parcours scolaire!");
                            }
                            if(joueurCourant==tabJoueurs[0] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere1.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[1] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere2.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[2] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere3.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[3] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere4.setVisible(true);
                            }
                            if(joueurCourant==tabJoueurs[0] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération1.setVisible(true);
                                }
                                PayerPrison1.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[1] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération2.setVisible(true);
                                }
                                PayerPrison2.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[2] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération3.setVisible(true);
                                }
                                PayerPrison3.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[3] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération4.setVisible(true);
                                }
                                PayerPrison4.setVisible(true);
                            }
                            if(elim!=true) {
                                Lancer_des.setEnabled(true);
                            }
                            if(joueurCourant.prison!=true && joueurCourant.nom!=null && compteurDouble==0) {
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon tirez les dés!");
                            }
                            else if(joueurCourant.prison!=true && joueurCourant.nom!=null) {
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, vous pouvez tirez les dés!");
                            }
                            else {
                                if(joueurCourant.nom!=null) {
                                    zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon essayer de quitter les rattrapages (vous pouvez payer 50 crédits, jouer une carte libération de rattrapages si vous en possédez une ou sinon tenter de faire un double)!");
                                }
                            }
                            plateauJeu.repaint();
                        }
                        plateau.plateaudejeu[21].construireMaison(darties);
                        plateau.plateaudejeu[21].changerLoyer();
                        credits1.setText("Crédits : " + tabJoueurs[0].credits + " ECTS");
                        credits2.setText("Crédits : " + tabJoueurs[1].credits + " ECTS");
                        credits3.setText("Crédits : " + tabJoueurs[2].credits + " ECTS");
                        credits4.setText("Crédits : " + tabJoueurs[3].credits + " ECTS");
                        if(plateau.plateaudejeu[21].maison==5) {
                            Construire1.setEnabled(false);
                        }
                        if(joueurCourant.credits<0) {
                            eliminationJoueur();
                        }
                        darties = false;
                        plateauJeu.repaint();
                    }
                });
                 
                 Construire2.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        if (darties) {
                            Construire1.setEnabled(false);
                            Construire2.setEnabled(false);
                            Construire3.setEnabled(false);
                            if(joueurCourant.credits<0) {
                                eliminationJoueur();
                            }
                            else {
                                changerJoueur();
                                compteurDouble=0;
                            }
                            while(joueurCourant.droitdejouer==false) {
                                joueurCourant.droitdejouer=true;
                                changerJoueur();
                                compteurDouble=0;
                            }
                            if(joueurCourant.compteurTourPrison==3) {
                                joueurCourant.compteurTourPrison=0;
                                joueurCourant.prison=false;
                                joueurCourant.credits=joueurCourant.credits-50;
                                if(joueurCourant==tabJoueurs[0]) {
                                    tabJoueurs[0].pion.coordX=61;
                                    tabJoueurs[0].pion.coordY=2;
                                    tabJoueurs[0].pion.taille=24;
                                    plateauJeu.orientation1=plateauJeu.haut1;
                                    plateauJeu.repaint();
                                    credits1.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                else if(joueurCourant==tabJoueurs[1]) {
                                    tabJoueurs[1].pion.coordX=18;
                                    tabJoueurs[1].pion.coordY=2;
                                    tabJoueurs[1].pion.taille=24;
                                    plateauJeu.orientation2=plateauJeu.haut2;
                                    plateauJeu.repaint();
                                    credits2.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                else if(joueurCourant==tabJoueurs[2]) {
                                    tabJoueurs[2].pion.coordX=2;
                                    tabJoueurs[2].pion.coordY=20;
                                    tabJoueurs[2].pion.taille=24;
                                    plateauJeu.orientation3=plateauJeu.gauche3;
                                    plateauJeu.repaint();
                                    credits3.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                else if(joueurCourant==tabJoueurs[3]) {
                                    tabJoueurs[3].pion.coordX=2;
                                    tabJoueurs[3].pion.coordY=63;
                                    tabJoueurs[3].pion.taille=24;
                                    plateauJeu.orientation4=plateauJeu.gauche4;
                                    plateauJeu.repaint();
                                    credits4.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n" + joueurCourant.nom + " vous venez de rater vos 3 essais aux rattrapages, vous payez donc 50 crédits et réintégrez le parcours scolaire!");
                            }
                            if(joueurCourant==tabJoueurs[0] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere1.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[1] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere2.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[2] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere3.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[3] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere4.setVisible(true);
                            }
                            if(joueurCourant==tabJoueurs[0] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération1.setVisible(true);
                                }
                                PayerPrison1.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[1] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération2.setVisible(true);
                                }
                                PayerPrison2.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[2] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération3.setVisible(true);
                                }
                                PayerPrison3.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[3] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération4.setVisible(true);
                                }
                                PayerPrison4.setVisible(true);
                            }
                            if(elim!=true) {
                                Lancer_des.setEnabled(true);
                            }
                            if(joueurCourant.prison!=true && joueurCourant.nom!=null && compteurDouble==0) {
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon tirez les dés!");
                            }
                            else if(joueurCourant.prison!=true && joueurCourant.nom!=null) {
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, vous pouvez tirez les dés!");
                            }
                            else {
                                if(joueurCourant.nom!=null) {
                                    zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon essayer de quitter les rattrapages (vous pouvez payer 50 crédits, jouer une carte libération de rattrapages si vous en possédez une ou sinon tenter de faire un double)!");
                                }
                            }
                            plateauJeu.repaint();
                        }
                        plateau.plateaudejeu[23].construireMaison(darties);
                        plateau.plateaudejeu[23].changerLoyer();
                        credits1.setText("Crédits : " + tabJoueurs[0].credits + " ECTS");
                        credits2.setText("Crédits : " + tabJoueurs[1].credits + " ECTS");
                        credits3.setText("Crédits : " + tabJoueurs[2].credits + " ECTS");
                        credits4.setText("Crédits : " + tabJoueurs[3].credits + " ECTS");
                        if(plateau.plateaudejeu[19].maison==5) {
                            Construire2.setEnabled(false);
                        }
                        if(joueurCourant.credits<0) {
                            eliminationJoueur();
                        }
                        darties = false;
                        plateauJeu.repaint();
                    }
                });
                 
                PanelRouge.add(Construire3, new org.netbeans.lib.awtextra.AbsoluteConstraints(450,300,154,30));
                
                
                PanelRouge.repaint();
                RougeFrame.repaint();
            }
        });
        
        vertV = new JButton();
        vertV.setBounds(0,0,40,40);
        vertV.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
        vertV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if (darties) {
                    marronV.setEnabled(false);
                    bleucielV.setEnabled(false);
                    violetV.setEnabled(false);
                    orangeV.setEnabled(false);
                    rougeV.setEnabled(false);
                    jauneV.setEnabled(false);
                    vertV.setEnabled(false);
                    bleufoncéV.setEnabled(false);
                }
                JFrame VertFrame = new JFrame("Validation dans une salle verte");
                VertFrame.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
                VertFrame.setVisible(true);
                VertFrame.setBounds(420,100,665,400);
                JPanel PanelVert = new JPanel();
                PanelVert.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
                PanelVert.setBounds(0,0,665,400);
                VertFrame.add(PanelVert, new org.netbeans.lib.awtextra.AbsoluteConstraints(0,0,-1,-1));
                JLabel K03 = new JLabel(new ImageIcon("src/salles/K03.png"));
                JLabel K04 = new JLabel(new ImageIcon("src/salles/K04.png"));
                JLabel K05 = new JLabel(new ImageIcon("src/salles/K05.png"));
                PanelVert.add(K03, new org.netbeans.lib.awtextra.AbsoluteConstraints(50,30,-1,-1));
                PanelVert.add(K04, new org.netbeans.lib.awtextra.AbsoluteConstraints(250,30, -1,-1));
                PanelVert.add(K05, new org.netbeans.lib.awtextra.AbsoluteConstraints(450,30,-1,-1));
                
                JButton Construire1 = new JButton("Valider");
                Construire1.setBounds(50,300,154,30);
                Construire1.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
                Construire1.setBackground(new Color(38,163,69));
                Construire1.setForeground(Color.white);
                if (plateau.plateaudejeu[31].proprietaire != joueurCourant) {
                    Construire1.setEnabled(false);
                }
                else {
                    Construire1.setEnabled(true);
                }
                PanelVert.add(Construire1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50,300,154,30));
                
                JButton Construire2 = new JButton("Valider");
                Construire2.setBounds(250,300,154,30);
                Construire2.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
                Construire2.setBackground(new Color(38,163,69));
                Construire2.setForeground(Color.white);
                if (plateau.plateaudejeu[32].proprietaire != joueurCourant) {
                    Construire2.setEnabled(false);
                }
                else {
                    Construire2.setEnabled(true);
                }
                
                PanelVert.add(Construire2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250,300,154,30));
                
                JButton Construire3 = new JButton("Valider");
                Construire3.setBounds(250,300,154,30);
                Construire3.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
                Construire3.setBackground(new Color(38,163,69));
                Construire3.setForeground(Color.white);
                if (plateau.plateaudejeu[34].proprietaire != joueurCourant) {
                    Construire3.setEnabled(false);
                }
                else {
                    Construire3.setEnabled(true);
                }
                
                Construire3.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        if (darties) {
                            Construire1.setEnabled(false);
                            Construire2.setEnabled(false);
                            Construire3.setEnabled(false);
                            if(joueurCourant.credits<0) {
                                eliminationJoueur();
                            }
                            else {
                                changerJoueur();
                                compteurDouble=0;
                            }
                            while(joueurCourant.droitdejouer==false) {
                                joueurCourant.droitdejouer=true;
                                changerJoueur();
                                compteurDouble=0;
                            }
                            if(joueurCourant.compteurTourPrison==3) {
                                joueurCourant.compteurTourPrison=0;
                                joueurCourant.prison=false;
                                joueurCourant.credits=joueurCourant.credits-50;
                                if(joueurCourant==tabJoueurs[0]) {
                                    tabJoueurs[0].pion.coordX=61;
                                    tabJoueurs[0].pion.coordY=2;
                                    tabJoueurs[0].pion.taille=24;
                                    plateauJeu.orientation1=plateauJeu.haut1;
                                    plateauJeu.repaint();
                                    credits1.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                else if(joueurCourant==tabJoueurs[1]) {
                                    tabJoueurs[1].pion.coordX=18;
                                    tabJoueurs[1].pion.coordY=2;
                                    tabJoueurs[1].pion.taille=24;
                                    plateauJeu.orientation2=plateauJeu.haut2;
                                    plateauJeu.repaint();
                                    credits2.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                else if(joueurCourant==tabJoueurs[2]) {
                                    tabJoueurs[2].pion.coordX=2;
                                    tabJoueurs[2].pion.coordY=20;
                                    tabJoueurs[2].pion.taille=24;
                                    plateauJeu.orientation3=plateauJeu.gauche3;
                                    plateauJeu.repaint();
                                    credits3.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                else if(joueurCourant==tabJoueurs[3]) {
                                    tabJoueurs[3].pion.coordX=2;
                                    tabJoueurs[3].pion.coordY=63;
                                    tabJoueurs[3].pion.taille=24;
                                    plateauJeu.orientation4=plateauJeu.gauche4;
                                    plateauJeu.repaint();
                                    credits4.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n" + joueurCourant.nom + " vous venez de rater vos 3 essais aux rattrapages, vous payez donc 50 crédits et réintégrez le parcours scolaire!");
                            }
                            if(joueurCourant==tabJoueurs[0] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere1.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[1] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere2.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[2] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere3.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[3] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere4.setVisible(true);
                            }
                            if(joueurCourant==tabJoueurs[0] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération1.setVisible(true);
                                }
                                PayerPrison1.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[1] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération2.setVisible(true);
                                }
                                PayerPrison2.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[2] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération3.setVisible(true);
                                }
                                PayerPrison3.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[3] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération4.setVisible(true);
                                }
                                PayerPrison4.setVisible(true);
                            }
                            if(elim!=true) {
                                Lancer_des.setEnabled(true);
                            }
                            if(joueurCourant.prison!=true && joueurCourant.nom!=null && compteurDouble==0) {
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon tirez les dés!");
                            }
                            else if(joueurCourant.prison!=true && joueurCourant.nom!=null) {
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, vous pouvez tirez les dés!");
                            }
                            else {
                                if(joueurCourant.nom!=null) {
                                    zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon essayer de quitter les rattrapages (vous pouvez payer 50 crédits, jouer une carte libération de rattrapages si vous en possédez une ou sinon tenter de faire un double)!");
                                }
                            }
                            plateauJeu.repaint();
                        }
                        plateau.plateaudejeu[34].construireMaison(darties);
                        plateau.plateaudejeu[34].changerLoyer();
                        credits1.setText("Crédits : " + tabJoueurs[0].credits + " ECTS");
                        credits2.setText("Crédits : " + tabJoueurs[1].credits + " ECTS");
                        credits3.setText("Crédits : " + tabJoueurs[2].credits + " ECTS");
                        credits4.setText("Crédits : " + tabJoueurs[3].credits + " ECTS");
                        if(plateau.plateaudejeu[34].maison==5) {
                            Construire3.setEnabled(false);
                        }
                        if(joueurCourant.credits<0) {
                            eliminationJoueur();
                        }
                        darties = false;
                        plateauJeu.repaint();
                    }
                });
                
                Construire1.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                    if (darties) {
                            Construire1.setEnabled(false);
                            Construire2.setEnabled(false);
                            Construire3.setEnabled(false);
                            if(joueurCourant.credits<0) {
                                eliminationJoueur();
                            }
                            else {
                                changerJoueur();
                                compteurDouble=0;
                            }
                            while(joueurCourant.droitdejouer==false) {
                                joueurCourant.droitdejouer=true;
                                changerJoueur();
                                compteurDouble=0;
                            }
                            if(joueurCourant.compteurTourPrison==3) {
                                joueurCourant.compteurTourPrison=0;
                                joueurCourant.prison=false;
                                joueurCourant.credits=joueurCourant.credits-50;
                                if(joueurCourant==tabJoueurs[0]) {
                                    tabJoueurs[0].pion.coordX=61;
                                    tabJoueurs[0].pion.coordY=2;
                                    tabJoueurs[0].pion.taille=24;
                                    plateauJeu.orientation1=plateauJeu.haut1;
                                    plateauJeu.repaint();
                                    credits1.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                else if(joueurCourant==tabJoueurs[1]) {
                                    tabJoueurs[1].pion.coordX=18;
                                    tabJoueurs[1].pion.coordY=2;
                                    tabJoueurs[1].pion.taille=24;
                                    plateauJeu.orientation2=plateauJeu.haut2;
                                    plateauJeu.repaint();
                                    credits2.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                else if(joueurCourant==tabJoueurs[2]) {
                                    tabJoueurs[2].pion.coordX=2;
                                    tabJoueurs[2].pion.coordY=20;
                                    tabJoueurs[2].pion.taille=24;
                                    plateauJeu.orientation3=plateauJeu.gauche3;
                                    plateauJeu.repaint();
                                    credits3.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                else if(joueurCourant==tabJoueurs[3]) {
                                    tabJoueurs[3].pion.coordX=2;
                                    tabJoueurs[3].pion.coordY=63;
                                    tabJoueurs[3].pion.taille=24;
                                    plateauJeu.orientation4=plateauJeu.gauche4;
                                    plateauJeu.repaint();
                                    credits4.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n" + joueurCourant.nom + " vous venez de rater vos 3 essais aux rattrapages, vous payez donc 50 crédits et réintégrez le parcours scolaire!");
                            }
                            if(joueurCourant==tabJoueurs[0] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere1.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[1] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere2.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[2] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere3.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[3] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere4.setVisible(true);
                            }
                            if(joueurCourant==tabJoueurs[0] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération1.setVisible(true);
                                }
                                PayerPrison1.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[1] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération2.setVisible(true);
                                }
                                PayerPrison2.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[2] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération3.setVisible(true);
                                }
                                PayerPrison3.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[3] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération4.setVisible(true);
                                }
                                PayerPrison4.setVisible(true);
                            }
                            if(elim!=true) {
                                Lancer_des.setEnabled(true);
                            }
                            if(joueurCourant.prison!=true && joueurCourant.nom!=null && compteurDouble==0) {
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon tirez les dés!");
                            }
                            else if(joueurCourant.prison!=true && joueurCourant.nom!=null) {
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, vous pouvez tirez les dés!");
                            }
                            else {
                                if(joueurCourant.nom!=null) {
                                    zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon essayer de quitter les rattrapages (vous pouvez payer 50 crédits, jouer une carte libération de rattrapages si vous en possédez une ou sinon tenter de faire un double)!");
                                }
                            }
                            plateauJeu.repaint();
                        }
                    plateau.plateaudejeu[31].construireMaison(darties);
                        plateau.plateaudejeu[31].changerLoyer();
                        credits1.setText("Crédits : " + tabJoueurs[0].credits + " ECTS");
                        credits2.setText("Crédits : " + tabJoueurs[1].credits + " ECTS");
                        credits3.setText("Crédits : " + tabJoueurs[2].credits + " ECTS");
                        credits4.setText("Crédits : " + tabJoueurs[3].credits + " ECTS");
                        if(plateau.plateaudejeu[31].maison==5) {
                            Construire1.setEnabled(false);
                        }
                        if(joueurCourant.credits<0) {
                            eliminationJoueur();
                        }
                        darties = false;
                        plateauJeu.repaint();
                    }
                });
                
                Construire2.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        if (darties) {
                            Construire1.setEnabled(false);
                            Construire2.setEnabled(false);
                            Construire3.setEnabled(false);
                            if(joueurCourant.credits<0) {
                                eliminationJoueur();
                            }
                            else {
                                changerJoueur();
                                compteurDouble=0;
                            }
                            while(joueurCourant.droitdejouer==false) {
                                joueurCourant.droitdejouer=true;
                                changerJoueur();
                                compteurDouble=0;
                            }
                            if(joueurCourant.compteurTourPrison==3) {
                                joueurCourant.compteurTourPrison=0;
                                joueurCourant.prison=false;
                                joueurCourant.credits=joueurCourant.credits-50;
                                if(joueurCourant==tabJoueurs[0]) {
                                    tabJoueurs[0].pion.coordX=61;
                                    tabJoueurs[0].pion.coordY=2;
                                    tabJoueurs[0].pion.taille=24;
                                    plateauJeu.orientation1=plateauJeu.haut1;
                                    plateauJeu.repaint();
                                    credits1.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                else if(joueurCourant==tabJoueurs[1]) {
                                    tabJoueurs[1].pion.coordX=18;
                                    tabJoueurs[1].pion.coordY=2;
                                    tabJoueurs[1].pion.taille=24;
                                    plateauJeu.orientation2=plateauJeu.haut2;
                                    plateauJeu.repaint();
                                    credits2.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                else if(joueurCourant==tabJoueurs[2]) {
                                    tabJoueurs[2].pion.coordX=2;
                                    tabJoueurs[2].pion.coordY=20;
                                    tabJoueurs[2].pion.taille=24;
                                    plateauJeu.orientation3=plateauJeu.gauche3;
                                    plateauJeu.repaint();
                                    credits3.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                else if(joueurCourant==tabJoueurs[3]) {
                                    tabJoueurs[3].pion.coordX=2;
                                    tabJoueurs[3].pion.coordY=63;
                                    tabJoueurs[3].pion.taille=24;
                                    plateauJeu.orientation4=plateauJeu.gauche4;
                                    plateauJeu.repaint();
                                    credits4.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n" + joueurCourant.nom + " vous venez de rater vos 3 essais aux rattrapages, vous payez donc 50 crédits et réintégrez le parcours scolaire!");
                            }
                            if(joueurCourant==tabJoueurs[0] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere1.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[1] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere2.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[2] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere3.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[3] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere4.setVisible(true);
                            }
                            if(joueurCourant==tabJoueurs[0] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération1.setVisible(true);
                                }
                                PayerPrison1.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[1] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération2.setVisible(true);
                                }
                                PayerPrison2.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[2] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération3.setVisible(true);
                                }
                                PayerPrison3.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[3] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération4.setVisible(true);
                                }
                                PayerPrison4.setVisible(true);
                            }
                            if(elim!=true) {
                                Lancer_des.setEnabled(true);
                            }
                            if(joueurCourant.prison!=true && joueurCourant.nom!=null && compteurDouble==0) {
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon tirez les dés!");
                            }
                            else if(joueurCourant.prison!=true && joueurCourant.nom!=null) {
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, vous pouvez tirez les dés!");
                            }
                            else {
                                if(joueurCourant.nom!=null) {
                                    zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon essayer de quitter les rattrapages (vous pouvez payer 50 crédits, jouer une carte libération de rattrapages si vous en possédez une ou sinon tenter de faire un double)!");
                                }
                            }
                            darties = false;
                            plateauJeu.repaint();
                        }
                        plateau.plateaudejeu[32].construireMaison(darties);
                        plateau.plateaudejeu[32].changerLoyer();
                        credits1.setText("Crédits : " + tabJoueurs[0].credits + " ECTS");
                        credits2.setText("Crédits : " + tabJoueurs[1].credits + " ECTS");
                        credits3.setText("Crédits : " + tabJoueurs[2].credits + " ECTS");
                        credits4.setText("Crédits : " + tabJoueurs[3].credits + " ECTS");
                        if(plateau.plateaudejeu[32].maison==5) {
                            Construire2.setEnabled(false);
                        }
                        if(joueurCourant.credits<0) {
                            eliminationJoueur();
                        }
                        plateauJeu.repaint();
                    }
                });
                
                PanelVert.add(Construire3, new org.netbeans.lib.awtextra.AbsoluteConstraints(450,300,154,30));
                
                
                PanelVert.repaint();
                VertFrame.repaint();
            }
        });
        
        violetV = new JButton();
        violetV.setBounds(0,0,40,40);
        violetV.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
        violetV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if (darties) {
                    marronV.setEnabled(false);
                    bleucielV.setEnabled(false);
                    violetV.setEnabled(false);
                    orangeV.setEnabled(false);
                    rougeV.setEnabled(false);
                    jauneV.setEnabled(false);
                    vertV.setEnabled(false);
                
                bleufoncéV.setEnabled(false);
                }
                JFrame VioletFrame = new JFrame("Validation dans une salle violette");
                VioletFrame.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
                VioletFrame.setVisible(true);
                VioletFrame.setBounds(420,100,665,400);
                JPanel PanelViolet = new JPanel();
                PanelViolet.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
                PanelViolet.setBounds(0,0,665,400);
                VioletFrame.add(PanelViolet, new org.netbeans.lib.awtextra.AbsoluteConstraints(0,0,-1,-1));
                JLabel JLABO04 = new JLabel(new ImageIcon("src/salles/JLABO04.png"));
                JLabel JLABO05 = new JLabel(new ImageIcon("src/salles/JLABO05.png"));
                JLabel JLABO06 = new JLabel(new ImageIcon("src/salles/JLABO06.png"));
                PanelViolet.add(JLABO04, new org.netbeans.lib.awtextra.AbsoluteConstraints(50,30,-1,-1));
                PanelViolet.add(JLABO05, new org.netbeans.lib.awtextra.AbsoluteConstraints(250,30, -1,-1));
                PanelViolet.add(JLABO06, new org.netbeans.lib.awtextra.AbsoluteConstraints(450,30,-1,-1));
                
                JButton Construire1 = new JButton("Valider");
                Construire1.setBounds(50,300,154,30);
                Construire1.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
                Construire1.setBackground(new Color(120,39,134));
                Construire1.setForeground(Color.white);
                if (plateau.plateaudejeu[11].proprietaire != joueurCourant) {
                    Construire1.setEnabled(false);
                }
                else {
                    Construire1.setEnabled(true);
                }
                PanelViolet.add(Construire1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50,300,154,30));
                
                JButton Construire2 = new JButton("Valider");
                Construire2.setBounds(250,300,154,30);
                Construire2.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
                Construire2.setBackground(new Color(120,39,134));
                Construire2.setForeground(Color.white);
                if (plateau.plateaudejeu[13].proprietaire != joueurCourant) {
                    Construire2.setEnabled(false);
                }
                else {
                    Construire2.setEnabled(true);
                }
                PanelViolet.add(Construire2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250,300,154,30));
                
                JButton Construire3 = new JButton("Valider");
                Construire3.setBounds(250,300,154,30);
                Construire3.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
                Construire3.setBackground(new Color(120,39,134));
                Construire3.setForeground(Color.white);
                if (plateau.plateaudejeu[14].proprietaire != joueurCourant) {
                    Construire3.setEnabled(false);
                }
                else {
                    Construire3.setEnabled(true);
                }
                Construire3.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        if (darties) {
                            Construire1.setEnabled(false);
                            Construire2.setEnabled(false);
                            Construire3.setEnabled(false);
                            if(joueurCourant.credits<0) {
                                eliminationJoueur();
                            }
                            else {
                                changerJoueur();
                                compteurDouble=0;
                            }
                            while(joueurCourant.droitdejouer==false) {
                                joueurCourant.droitdejouer=true;
                                changerJoueur();
                                compteurDouble=0;
                            }
                            if(joueurCourant.compteurTourPrison==3) {
                                joueurCourant.compteurTourPrison=0;
                                joueurCourant.prison=false;
                                joueurCourant.credits=joueurCourant.credits-50;
                                if(joueurCourant==tabJoueurs[0]) {
                                    tabJoueurs[0].pion.coordX=61;
                                    tabJoueurs[0].pion.coordY=2;
                                    tabJoueurs[0].pion.taille=24;
                                    plateauJeu.orientation1=plateauJeu.haut1;
                                    plateauJeu.repaint();
                                    credits1.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                else if(joueurCourant==tabJoueurs[1]) {
                                    tabJoueurs[1].pion.coordX=18;
                                    tabJoueurs[1].pion.coordY=2;
                                    tabJoueurs[1].pion.taille=24;
                                    plateauJeu.orientation2=plateauJeu.haut2;
                                    plateauJeu.repaint();
                                    credits2.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                else if(joueurCourant==tabJoueurs[2]) {
                                    tabJoueurs[2].pion.coordX=2;
                                    tabJoueurs[2].pion.coordY=20;
                                    tabJoueurs[2].pion.taille=24;
                                    plateauJeu.orientation3=plateauJeu.gauche3;
                                    plateauJeu.repaint();
                                    credits3.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                else if(joueurCourant==tabJoueurs[3]) {
                                    tabJoueurs[3].pion.coordX=2;
                                    tabJoueurs[3].pion.coordY=63;
                                    tabJoueurs[3].pion.taille=24;
                                    plateauJeu.orientation4=plateauJeu.gauche4;
                                    plateauJeu.repaint();
                                    credits4.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n" + joueurCourant.nom + " vous venez de rater vos 3 essais aux rattrapages, vous payez donc 50 crédits et réintégrez le parcours scolaire!");
                            }
                            if(joueurCourant==tabJoueurs[0] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere1.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[1] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere2.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[2] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere3.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[3] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere4.setVisible(true);
                            }
                            if(joueurCourant==tabJoueurs[0] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération1.setVisible(true);
                                }
                                PayerPrison1.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[1] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération2.setVisible(true);
                                }
                                PayerPrison2.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[2] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération3.setVisible(true);
                                }
                                PayerPrison3.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[3] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération4.setVisible(true);
                                }
                                PayerPrison4.setVisible(true);
                            }
                            if(elim!=true) {
                                Lancer_des.setEnabled(true);
                            }
                            if(joueurCourant.prison!=true && joueurCourant.nom!=null && compteurDouble==0) {
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon tirez les dés!");
                            }
                            else if(joueurCourant.prison!=true && joueurCourant.nom!=null) {
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, vous pouvez tirez les dés!");
                            }
                            else {
                                if(joueurCourant.nom!=null) {
                                    zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon essayer de quitter les rattrapages (vous pouvez payer 50 crédits, jouer une carte libération de rattrapages si vous en possédez une ou sinon tenter de faire un double)!");
                                }
                            }
                            plateauJeu.repaint();
                        }
                        plateau.plateaudejeu[14].construireMaison(darties);
                        plateau.plateaudejeu[14].changerLoyer();
                        credits1.setText("Crédits : " + tabJoueurs[0].credits + " ECTS");
                        credits2.setText("Crédits : " + tabJoueurs[1].credits + " ECTS");
                        credits3.setText("Crédits : " + tabJoueurs[2].credits + " ECTS");
                        credits4.setText("Crédits : " + tabJoueurs[3].credits + " ECTS");
                        if(plateau.plateaudejeu[14].maison==5) {
                            Construire3.setEnabled(false);
                        }
                        if(joueurCourant.credits<0) {
                            eliminationJoueur();
                        }
                        darties = false;
                        plateauJeu.repaint();
                    }
                });
                
                Construire1.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        if (darties) {
                            Construire1.setEnabled(false);
                            Construire2.setEnabled(false);
                            Construire3.setEnabled(false);
                            if(joueurCourant.credits<0) {
                                eliminationJoueur();
                            }
                            else {
                                changerJoueur();
                                compteurDouble=0;
                            }
                            while(joueurCourant.droitdejouer==false) {
                                joueurCourant.droitdejouer=true;
                                changerJoueur();
                                compteurDouble=0;
                            }
                            if(joueurCourant.compteurTourPrison==3) {
                                joueurCourant.compteurTourPrison=0;
                                joueurCourant.prison=false;
                                joueurCourant.credits=joueurCourant.credits-50;
                                if(joueurCourant==tabJoueurs[0]) {
                                    tabJoueurs[0].pion.coordX=61;
                                    tabJoueurs[0].pion.coordY=2;
                                    tabJoueurs[0].pion.taille=24;
                                    plateauJeu.orientation1=plateauJeu.haut1;
                                    plateauJeu.repaint();
                                    credits1.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                else if(joueurCourant==tabJoueurs[1]) {
                                    tabJoueurs[1].pion.coordX=18;
                                    tabJoueurs[1].pion.coordY=2;
                                    tabJoueurs[1].pion.taille=24;
                                    plateauJeu.orientation2=plateauJeu.haut2;
                                    plateauJeu.repaint();
                                    credits2.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                else if(joueurCourant==tabJoueurs[2]) {
                                    tabJoueurs[2].pion.coordX=2;
                                    tabJoueurs[2].pion.coordY=20;
                                    tabJoueurs[2].pion.taille=24;
                                    plateauJeu.orientation3=plateauJeu.gauche3;
                                    plateauJeu.repaint();
                                    credits3.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                else if(joueurCourant==tabJoueurs[3]) {
                                    tabJoueurs[3].pion.coordX=2;
                                    tabJoueurs[3].pion.coordY=63;
                                    tabJoueurs[3].pion.taille=24;
                                    plateauJeu.orientation4=plateauJeu.gauche4;
                                    plateauJeu.repaint();
                                    credits4.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n" + joueurCourant.nom + " vous venez de rater vos 3 essais aux rattrapages, vous payez donc 50 crédits et réintégrez le parcours scolaire!");
                            }
                            if(joueurCourant==tabJoueurs[0] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere1.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[1] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere2.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[2] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere3.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[3] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere4.setVisible(true);
                            }
                            if(joueurCourant==tabJoueurs[0] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération1.setVisible(true);
                                }
                                PayerPrison1.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[1] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération2.setVisible(true);
                                }
                                PayerPrison2.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[2] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération3.setVisible(true);
                                }
                                PayerPrison3.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[3] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération4.setVisible(true);
                                }
                                PayerPrison4.setVisible(true);
                            }
                            if(elim!=true) {
                                Lancer_des.setEnabled(true);
                            }
                            if(joueurCourant.prison!=true && joueurCourant.nom!=null && compteurDouble==0) {
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon tirez les dés!");
                            }
                            else if(joueurCourant.prison!=true && joueurCourant.nom!=null) {
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, vous pouvez tirez les dés!");
                            }
                            else {
                                if(joueurCourant.nom!=null) {
                                    zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon essayer de quitter les rattrapages (vous pouvez payer 50 crédits, jouer une carte libération de rattrapages si vous en possédez une ou sinon tenter de faire un double)!");
                                }
                            }
                            plateauJeu.repaint();
                        }
                        plateau.plateaudejeu[11].construireMaison(darties);
                        plateau.plateaudejeu[11].changerLoyer();
                        credits1.setText("Crédits : " + tabJoueurs[0].credits + " ECTS");
                        credits2.setText("Crédits : " + tabJoueurs[1].credits + " ECTS");
                        credits3.setText("Crédits : " + tabJoueurs[2].credits + " ECTS");
                        credits4.setText("Crédits : " + tabJoueurs[3].credits + " ECTS");
                        if(plateau.plateaudejeu[11].maison==5) {
                            Construire1.setEnabled(false);
                        }
                        if(joueurCourant.credits<0) {
                            eliminationJoueur();
                        }
                        darties = false;
                        plateauJeu.repaint();
                    
                    }
                });
                
                Construire2.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        if (darties) {
                            Construire1.setEnabled(false);
                            Construire2.setEnabled(false);
                            Construire3.setEnabled(false);
                            if(joueurCourant.credits<0) {
                                eliminationJoueur();
                            }
                            else {
                                changerJoueur();
                                compteurDouble=0;
                            }
                            while(joueurCourant.droitdejouer==false) {
                                joueurCourant.droitdejouer=true;
                                changerJoueur();
                                compteurDouble=0;
                            }
                            if(joueurCourant.compteurTourPrison==3) {
                                joueurCourant.compteurTourPrison=0;
                                joueurCourant.prison=false;
                                joueurCourant.credits=joueurCourant.credits-50;
                                if(joueurCourant==tabJoueurs[0]) {
                                    tabJoueurs[0].pion.coordX=61;
                                    tabJoueurs[0].pion.coordY=2;
                                    tabJoueurs[0].pion.taille=24;
                                    plateauJeu.orientation1=plateauJeu.haut1;
                                    plateauJeu.repaint();
                                    credits1.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                else if(joueurCourant==tabJoueurs[1]) {
                                    tabJoueurs[1].pion.coordX=18;
                                    tabJoueurs[1].pion.coordY=2;
                                    tabJoueurs[1].pion.taille=24;
                                    plateauJeu.orientation2=plateauJeu.haut2;
                                    plateauJeu.repaint();
                                    credits2.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                else if(joueurCourant==tabJoueurs[2]) {
                                    tabJoueurs[2].pion.coordX=2;
                                    tabJoueurs[2].pion.coordY=20;
                                    tabJoueurs[2].pion.taille=24;
                                    plateauJeu.orientation3=plateauJeu.gauche3;
                                    plateauJeu.repaint();
                                    credits3.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                else if(joueurCourant==tabJoueurs[3]) {
                                    tabJoueurs[3].pion.coordX=2;
                                    tabJoueurs[3].pion.coordY=63;
                                    tabJoueurs[3].pion.taille=24;
                                    plateauJeu.orientation4=plateauJeu.gauche4;
                                    plateauJeu.repaint();
                                    credits4.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n" + joueurCourant.nom + " vous venez de rater vos 3 essais aux rattrapages, vous payez donc 50 crédits et réintégrez le parcours scolaire!");
                            }
                            if(joueurCourant==tabJoueurs[0] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere1.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[1] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere2.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[2] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere3.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[3] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere4.setVisible(true);
                            }
                            if(joueurCourant==tabJoueurs[0] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération1.setVisible(true);
                                }
                                PayerPrison1.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[1] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération2.setVisible(true);
                                }
                                PayerPrison2.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[2] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération3.setVisible(true);
                                }
                                PayerPrison3.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[3] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération4.setVisible(true);
                                }
                                PayerPrison4.setVisible(true);
                            }
                            if(elim!=true) {
                                Lancer_des.setEnabled(true);
                            }
                            if(joueurCourant.prison!=true && joueurCourant.nom!=null && compteurDouble==0) {
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon tirez les dés!");
                            }
                            else if(joueurCourant.prison!=true && joueurCourant.nom!=null) {
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, vous pouvez tirez les dés!");
                            }
                            else {
                                if(joueurCourant.nom!=null) {
                                    zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon essayer de quitter les rattrapages (vous pouvez payer 50 crédits, jouer une carte libération de rattrapages si vous en possédez une ou sinon tenter de faire un double)!");
                                }
                            }
                            darties = false;
                            plateauJeu.repaint();
                        }
                        plateau.plateaudejeu[13].construireMaison(darties);
                        plateau.plateaudejeu[13].changerLoyer();
                        credits1.setText("Crédits : " + tabJoueurs[0].credits + " ECTS");
                        credits2.setText("Crédits : " + tabJoueurs[1].credits + " ECTS");
                        credits3.setText("Crédits : " + tabJoueurs[2].credits + " ECTS");
                        credits4.setText("Crédits : " + tabJoueurs[3].credits + " ECTS");
                        if(plateau.plateaudejeu[13].maison==5) {
                            Construire2.setEnabled(false);
                        }
                        if(joueurCourant.credits<0) {
                            eliminationJoueur();
                        }
                        plateauJeu.repaint();
                    }
                });
                
                PanelViolet.add(Construire3, new org.netbeans.lib.awtextra.AbsoluteConstraints(450,300,154,30));
                
                
                PanelViolet.repaint();
                VioletFrame.repaint();
            }
        });
        
        validerLabel = new JLabel("<html>Veuillez cliquer sur la couleur de la salle dans<br>laquelle vous voulez valider une matiere ou un module.</html>");
        validerLabel.setFont(new Font("Monopoly", Font.PLAIN, 18));
        
        ValiderMatiere1 = new JButton();
        ValiderMatiere1.setBounds(180, 60, 170, 50);
        ValiderMatiere1.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
        infos_joueurs1.add(ValiderMatiere1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 60, 170, 50));
        ValiderMatiere1.setBackground(Color.LIGHT_GRAY);
        ValiderMatiere1.setText("Valider");
        ValiderMatiere1.setFont(new Font("Valider", Font.BOLD, 20));
        ValiderMatiere1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JFrame FrameValider = new JFrame("Valider une matière");
                FrameValider.setBounds(420,300, 500,120);
                FrameValider.setVisible(true);
                FrameValider.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
                int nbM = tabJoueurs[0].compterMemesSallesJoueurInt("marron");
                int nbBC = tabJoueurs[0].compterMemesSallesJoueurInt("bleu ciel");
                int nbVi = tabJoueurs[0].compterMemesSallesJoueurInt("violet");
                int nbO = tabJoueurs[0].compterMemesSallesJoueurInt("orange");
                int nbR = tabJoueurs[0].compterMemesSallesJoueurInt("rouge");
                int nbJ = tabJoueurs[0].compterMemesSallesJoueurInt("jaune");
                int nbVe = tabJoueurs[0].compterMemesSallesJoueurInt("vert");
                int nbBF = tabJoueurs[0].compterMemesSallesJoueurInt("bleu foncé");
                if (nbM!=2) {
                    marronV.setBackground(Color.LIGHT_GRAY);
                    marronV.setEnabled(false);
                }
                else {
                    marronV.setBackground(new Color(148,74,37));
                    marronV.setEnabled(true);
                }
                if (nbBC!=3) {
                    bleucielV.setBackground(Color.LIGHT_GRAY);
                    bleucielV.setEnabled(false);
                }
                else {
                    bleucielV.setBackground(new Color(189,227,246));
                    bleucielV.setEnabled(true);
                }
                if (nbVi!=3) {
                    violetV.setBackground(Color.LIGHT_GRAY);
                    violetV.setEnabled(false);
                }
                else {
                    violetV.setBackground(new Color(120,39,134));
                    violetV.setEnabled(true);
                }
                if (nbO!=3) {
                    orangeV.setBackground(Color.LIGHT_GRAY);
                    orangeV.setEnabled(false);
                }
                else {
                    orangeV.setBackground(new Color(242,148,0));
                    orangeV.setEnabled(true);
                }
                if (nbR!=3) {
                    rougeV.setBackground(Color.LIGHT_GRAY);
                    rougeV.setEnabled(false);
                }
                else {
                    rougeV.setBackground(new Color(227,0,27));
                    rougeV.setEnabled(true);
                }
                if (nbJ!=3) {
                    jauneV.setBackground(Color.LIGHT_GRAY);
                    jauneV.setEnabled(false);
                }
                else {
                    jauneV.setBackground(new Color(255,238,2));
                    jauneV.setEnabled(true);
                }
                if (nbVe!=3) {
                    vertV.setBackground(Color.LIGHT_GRAY);
                    vertV.setEnabled(false);
                }
                else {
                    vertV.setBackground(new Color(38,163,69));
                    vertV.setEnabled(true);
                }
                if (nbBF!=2) {
                    bleufoncéV.setBackground(Color.LIGHT_GRAY);
                    bleufoncéV.setEnabled(false);
                }
                else {
                    bleufoncéV.setBackground(new Color(28,55,126));
                    bleufoncéV.setEnabled(true);
                }
                FrameValider.add(marronV, new org.netbeans.lib.awtextra.AbsoluteConstraints(80,15,30,30));
                FrameValider.add(bleucielV, new org.netbeans.lib.awtextra.AbsoluteConstraints(120,15,30,30));
                FrameValider.add(violetV, new org.netbeans.lib.awtextra.AbsoluteConstraints(160,15,30,30));
                FrameValider.add(orangeV, new org.netbeans.lib.awtextra.AbsoluteConstraints(200,15,30,30));
                FrameValider.add(rougeV, new org.netbeans.lib.awtextra.AbsoluteConstraints(240,15,30,30));
                FrameValider.add(jauneV, new org.netbeans.lib.awtextra.AbsoluteConstraints(280,15,30,30));
                FrameValider.add(vertV, new org.netbeans.lib.awtextra.AbsoluteConstraints(320,15,30,30));
                FrameValider.add(bleufoncéV, new org.netbeans.lib.awtextra.AbsoluteConstraints(360,15,30,30));
                FrameValider.add(validerLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(80,45,-1,-1));
                validerJoueur = new JLabel("Joueur concerne : "+tabJoueurs[0].nom);
                validerJoueur.setFont(new Font("Monopoly", Font.PLAIN, 10));
                FrameValider.add(validerJoueur, new org.netbeans.lib.awtextra.AbsoluteConstraints(10,5,-1,-1));
                FrameValider.repaint();
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
                JFrame FrameValider = new JFrame("Valider une matière");
                FrameValider.setBounds(420,300, 500,120);
                FrameValider.setVisible(true);
                FrameValider.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
                int nbM = tabJoueurs[1].compterMemesSallesJoueurInt("marron");
                int nbBC = tabJoueurs[1].compterMemesSallesJoueurInt("bleu ciel");
                int nbVi = tabJoueurs[1].compterMemesSallesJoueurInt("violet");
                int nbO = tabJoueurs[1].compterMemesSallesJoueurInt("orange");
                int nbR = tabJoueurs[1].compterMemesSallesJoueurInt("rouge");
                int nbJ = tabJoueurs[1].compterMemesSallesJoueurInt("jaune");
                int nbVe = tabJoueurs[1].compterMemesSallesJoueurInt("vert");
                int nbBF = tabJoueurs[1].compterMemesSallesJoueurInt("bleu foncé");
                if (nbM!=2) {
                    marronV.setBackground(Color.LIGHT_GRAY);
                    marronV.setEnabled(false);
                }
                else {
                    marronV.setBackground(new Color(148,74,37));
                    marronV.setEnabled(true);
                }
                if (nbBC!=3) {
                    bleucielV.setBackground(Color.LIGHT_GRAY);
                    bleucielV.setEnabled(false);
                }
                else {
                    bleucielV.setBackground(new Color(189,227,246));
                    bleucielV.setEnabled(true);
                }
                if (nbVi!=3) {
                    violetV.setBackground(Color.LIGHT_GRAY);
                    violetV.setEnabled(false);
                }
                else {
                    violetV.setBackground(new Color(120,39,134));
                    violetV.setEnabled(true);
                }
                if (nbO!=3) {
                    orangeV.setBackground(Color.LIGHT_GRAY);
                    orangeV.setEnabled(false);
                }
                else {
                    orangeV.setBackground(new Color(242,148,0));
                    orangeV.setEnabled(true);
                }
                if (nbR!=3) {
                    rougeV.setBackground(Color.LIGHT_GRAY);
                    rougeV.setEnabled(false);
                }
                else {
                    rougeV.setBackground(new Color(227,0,27));
                    rougeV.setEnabled(true);
                }
                if (nbJ!=3) {
                    jauneV.setBackground(Color.LIGHT_GRAY);
                    jauneV.setEnabled(false);
                }
                else {
                    jauneV.setBackground(new Color(255,238,2));
                    jauneV.setEnabled(true);
                }
                if (nbVe!=3) {
                    vertV.setBackground(Color.LIGHT_GRAY);
                    vertV.setEnabled(false);
                }
                else {
                    vertV.setBackground(new Color(38,163,69));
                    vertV.setEnabled(true);
                }
                if (nbBF!=2) {
                    bleufoncéV.setBackground(Color.LIGHT_GRAY);
                    bleufoncéV.setEnabled(false);
                }
                else {
                    bleufoncéV.setBackground(new Color(28,55,126));
                    bleufoncéV.setEnabled(true);
                }
                FrameValider.add(marronV, new org.netbeans.lib.awtextra.AbsoluteConstraints(80,15,30,30));
                FrameValider.add(bleucielV, new org.netbeans.lib.awtextra.AbsoluteConstraints(120,15,30,30));
                FrameValider.add(violetV, new org.netbeans.lib.awtextra.AbsoluteConstraints(160,15,30,30));
                FrameValider.add(orangeV, new org.netbeans.lib.awtextra.AbsoluteConstraints(200,15,30,30));
                FrameValider.add(rougeV, new org.netbeans.lib.awtextra.AbsoluteConstraints(240,15,30,30));
                FrameValider.add(jauneV, new org.netbeans.lib.awtextra.AbsoluteConstraints(280,15,30,30));
                FrameValider.add(vertV, new org.netbeans.lib.awtextra.AbsoluteConstraints(320,15,30,30));
                FrameValider.add(bleufoncéV, new org.netbeans.lib.awtextra.AbsoluteConstraints(360,15,30,30));
                FrameValider.add(validerLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(80,45,-1,-1));
                validerJoueur = new JLabel("Joueur concerne : "+tabJoueurs[1].nom);
                validerJoueur.setFont(new Font("Monopoly", Font.PLAIN, 10));
                FrameValider.add(validerJoueur, new org.netbeans.lib.awtextra.AbsoluteConstraints(10,5,-1,-1));
                FrameValider.repaint();
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
                JFrame FrameValider = new JFrame("Valider une matière");
                FrameValider.setBounds(420,300, 500,120);
                FrameValider.setVisible(true);
                FrameValider.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
                int nbM = tabJoueurs[2].compterMemesSallesJoueurInt("marron");
                int nbBC = tabJoueurs[2].compterMemesSallesJoueurInt("bleu ciel");
                int nbVi = tabJoueurs[2].compterMemesSallesJoueurInt("violet");
                int nbO = tabJoueurs[2].compterMemesSallesJoueurInt("orange");
                int nbR = tabJoueurs[2].compterMemesSallesJoueurInt("rouge");
                int nbJ = tabJoueurs[2].compterMemesSallesJoueurInt("jaune");
                int nbVe = tabJoueurs[2].compterMemesSallesJoueurInt("vert");
                int nbBF = tabJoueurs[2].compterMemesSallesJoueurInt("bleu foncé");
                if (nbM!=2) {
                    marronV.setBackground(Color.LIGHT_GRAY);
                    marronV.setEnabled(false);
                }
                else {
                    marronV.setBackground(new Color(148,74,37));
                    marronV.setEnabled(true);
                }
                if (nbBC!=3) {
                    bleucielV.setBackground(Color.LIGHT_GRAY);
                    bleucielV.setEnabled(false);
                }
                else {
                    bleucielV.setBackground(new Color(189,227,246));
                    bleucielV.setEnabled(true);
                }
                if (nbVi!=3) {
                    violetV.setBackground(Color.LIGHT_GRAY);
                    violetV.setEnabled(false);
                }
                else {
                    violetV.setBackground(new Color(120,39,134));
                    violetV.setEnabled(true);
                }
                if (nbO!=3) {
                    orangeV.setBackground(Color.LIGHT_GRAY);
                    orangeV.setEnabled(false);
                }
                else {
                    orangeV.setBackground(new Color(242,148,0));
                    orangeV.setEnabled(true);
                }
                if (nbR!=3) {
                    rougeV.setBackground(Color.LIGHT_GRAY);
                    rougeV.setEnabled(false);
                }
                else {
                    rougeV.setBackground(new Color(227,0,27));
                    rougeV.setEnabled(true);
                }
                if (nbJ!=3) {
                    jauneV.setBackground(Color.LIGHT_GRAY);
                    jauneV.setEnabled(false);
                }
                else {
                    jauneV.setBackground(new Color(255,238,2));
                    jauneV.setEnabled(true);
                    
                }
                if (nbVe!=3) {
                    vertV.setBackground(Color.LIGHT_GRAY);
                    vertV.setEnabled(false);
                }
                else {
                    vertV.setBackground(new Color(38,163,69));
                    vertV.setEnabled(true);
                }
                if (nbBF!=2) {
                    bleufoncéV.setBackground(Color.LIGHT_GRAY);
                    bleufoncéV.setEnabled(false);
                }
                else {
                    bleufoncéV.setBackground(new Color(28,55,126));
                    bleufoncéV.setEnabled(true);
                }
                FrameValider.add(marronV, new org.netbeans.lib.awtextra.AbsoluteConstraints(80,15,30,30));
                FrameValider.add(bleucielV, new org.netbeans.lib.awtextra.AbsoluteConstraints(120,15,30,30));
                FrameValider.add(violetV, new org.netbeans.lib.awtextra.AbsoluteConstraints(160,15,30,30));
                FrameValider.add(orangeV, new org.netbeans.lib.awtextra.AbsoluteConstraints(200,15,30,30));
                FrameValider.add(rougeV, new org.netbeans.lib.awtextra.AbsoluteConstraints(240,15,30,30));
                FrameValider.add(jauneV, new org.netbeans.lib.awtextra.AbsoluteConstraints(280,15,30,30));
                FrameValider.add(vertV, new org.netbeans.lib.awtextra.AbsoluteConstraints(320,15,30,30));
                FrameValider.add(bleufoncéV, new org.netbeans.lib.awtextra.AbsoluteConstraints(360,15,30,30));
                FrameValider.add(validerLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(80,45,-1,-1));
                validerJoueur = new JLabel("Joueur concerne : "+tabJoueurs[2].nom);
                validerJoueur.setFont(new Font("Monopoly", Font.PLAIN, 10));
                FrameValider.add(validerJoueur, new org.netbeans.lib.awtextra.AbsoluteConstraints(10,5,-1,-1));
                FrameValider.repaint();
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
                JFrame FrameValider = new JFrame("Valider une matière");
                FrameValider.setBounds(420,300, 500,120);
                FrameValider.setVisible(true);
                FrameValider.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
                int nbM = tabJoueurs[3].compterMemesSallesJoueurInt("marron");
                int nbBC = tabJoueurs[3].compterMemesSallesJoueurInt("bleu ciel");
                int nbVi = tabJoueurs[3].compterMemesSallesJoueurInt("violet");
                int nbO = tabJoueurs[3].compterMemesSallesJoueurInt("orange");
                int nbR = tabJoueurs[3].compterMemesSallesJoueurInt("rouge");
                int nbJ = tabJoueurs[3].compterMemesSallesJoueurInt("jaune");
                int nbVe = tabJoueurs[3].compterMemesSallesJoueurInt("vert");
                int nbBF = tabJoueurs[3].compterMemesSallesJoueurInt("bleu foncé");
                if (nbM!=2) {
                    marronV.setBackground(Color.LIGHT_GRAY);
                    marronV.setEnabled(false);
                }
                else {
                    marronV.setBackground(new Color(148,74,37));
                    marronV.setEnabled(true);
                }
                if (nbBC!=3) {
                    bleucielV.setBackground(Color.LIGHT_GRAY);
                    bleucielV.setEnabled(false);
                }
                else {
                    bleucielV.setBackground(new Color(189,227,246));
                    bleucielV.setEnabled(true);
                }
                if (nbVi!=3) {
                    violetV.setBackground(Color.LIGHT_GRAY);
                    violetV.setEnabled(false);
                }
                else {
                    violetV.setBackground(new Color(120,39,134));
                    violetV.setEnabled(true);
                }
                if (nbO!=3) {
                    orangeV.setBackground(Color.LIGHT_GRAY);
                    orangeV.setEnabled(false);
                }
                else {
                    orangeV.setBackground(new Color(242,148,0));
                    orangeV.setEnabled(true);
                }
                if (nbR!=3) {
                    rougeV.setBackground(Color.LIGHT_GRAY);
                    rougeV.setEnabled(false);
                }
                else {
                    rougeV.setBackground(new Color(227,0,27));
                    rougeV.setEnabled(true);
                }
                if (nbJ!=3) {
                    jauneV.setBackground(Color.LIGHT_GRAY);
                    jauneV.setEnabled(false);
                }
                else {
                    jauneV.setBackground(new Color(255,238,2));
                    jauneV.setEnabled(true);
                }
                if (nbVe!=3) {
                    vertV.setBackground(Color.LIGHT_GRAY);
                    vertV.setEnabled(false);
                }
                else {
                    vertV.setBackground(new Color(38,163,69));
                    vertV.setEnabled(true);
                }
                if (nbBF!=2) {
                    bleufoncéV.setBackground(Color.LIGHT_GRAY);
                    bleufoncéV.setEnabled(false);
                }
                else {
                    bleufoncéV.setBackground(new Color(28,55,126));
                    bleufoncéV.setEnabled(true);
                }
                FrameValider.add(marronV, new org.netbeans.lib.awtextra.AbsoluteConstraints(80,15,30,30));
                FrameValider.add(bleucielV, new org.netbeans.lib.awtextra.AbsoluteConstraints(120,15,30,30));
                FrameValider.add(violetV, new org.netbeans.lib.awtextra.AbsoluteConstraints(160,15,30,30));
                FrameValider.add(orangeV, new org.netbeans.lib.awtextra.AbsoluteConstraints(200,15,30,30));
                FrameValider.add(rougeV, new org.netbeans.lib.awtextra.AbsoluteConstraints(240,15,30,30));
                FrameValider.add(jauneV, new org.netbeans.lib.awtextra.AbsoluteConstraints(280,15,30,30));
                FrameValider.add(vertV, new org.netbeans.lib.awtextra.AbsoluteConstraints(320,15,30,30));
                FrameValider.add(bleufoncéV, new org.netbeans.lib.awtextra.AbsoluteConstraints(360,15,30,30));
                FrameValider.add(validerLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(80,45,-1,-1));
                validerJoueur = new JLabel("Joueur concerne : "+tabJoueurs[3].nom);
                validerJoueur.setFont(new Font("Monopoly", Font.PLAIN, 10));
                FrameValider.add(validerJoueur, new org.netbeans.lib.awtextra.AbsoluteConstraints(10,5,-1,-1));
                FrameValider.repaint();
            }
        });
        this.repaint();
        
        fin_de_tour1 = new JButton();
        fin_de_tour1.setBounds(180, 60, 170, 50);
        fin_de_tour1.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
        infos_joueurs1.add(fin_de_tour1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 60, 170, 50));
        fin_de_tour1.setBackground(Color.LIGHT_GRAY);
        fin_de_tour1.setText("Fin de tour");
        fin_de_tour1.setFont(new Font("Fin de tour", Font.BOLD, 20));
        fin_de_tour1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if(de1.valeur==de2.valeur && joueurCourant.droitdejouer==true) {
                    if(joueurCourant.credits<0) {
                        eliminationJoueur();
                    }
                    compteurDouble++;
                    if(compteurDouble==3) {
                        joueurCourant.prison=true;
                        if(joueurCourant.credits<0) {
                            eliminationJoueur();
                        }
                        else {
                            changerJoueur();
                            compteurDouble=0;
                        }
                    }
                }
                else {
                    if(joueurCourant.credits<0) {
                        eliminationJoueur();
                    }
                    else {
                        changerJoueur();
                        compteurDouble=0;
                    }
                }
                while(joueurCourant.droitdejouer==false) {
                    joueurCourant.droitdejouer=true;
                    changerJoueur();
                    compteurDouble=0;
                }
                if(joueurCourant.compteurTourPrison==3) {
                    joueurCourant.compteurTourPrison=0;
                    joueurCourant.prison=false;
                    joueurCourant.credits=joueurCourant.credits-50;
                    if(joueurCourant==tabJoueurs[0]) {
                        tabJoueurs[0].pion.coordX=61;
                        tabJoueurs[0].pion.coordY=2;
                        tabJoueurs[0].pion.taille=24;
                        plateauJeu.orientation1=plateauJeu.haut1;
                        plateauJeu.repaint();
                        credits1.setText("Crédits : " + joueurCourant.credits + " ECTS");
                    }
                    else if(joueurCourant==tabJoueurs[1]) {
                        tabJoueurs[1].pion.coordX=18;
                        tabJoueurs[1].pion.coordY=2;
                        tabJoueurs[1].pion.taille=24;
                        plateauJeu.orientation2=plateauJeu.haut2;
                        plateauJeu.repaint();
                        credits2.setText("Crédits : " + joueurCourant.credits + " ECTS");
                    }
                    else if(joueurCourant==tabJoueurs[2]) {
                        tabJoueurs[2].pion.coordX=2;
                        tabJoueurs[2].pion.coordY=20;
                        tabJoueurs[2].pion.taille=24;
                        plateauJeu.orientation3=plateauJeu.gauche3;
                        plateauJeu.repaint();
                        credits3.setText("Crédits : " + joueurCourant.credits + " ECTS");
                    }
                    else if(joueurCourant==tabJoueurs[3]) {
                        tabJoueurs[3].pion.coordX=2;
                        tabJoueurs[3].pion.coordY=63;
                        tabJoueurs[3].pion.taille=24;
                        plateauJeu.orientation4=plateauJeu.gauche4;
                        plateauJeu.repaint();
                        credits4.setText("Crédits : " + joueurCourant.credits + " ECTS");
                    }
                    zone_texte_infos.setText(zone_texte_infos.getText() + "\n" + joueurCourant.nom + " vous venez de rater vos 3 essais aux rattrapages, vous payez donc 50 crédits et réintégrez le parcours scolaire!");
                    }
                if(joueurCourant==tabJoueurs[0] && joueurCourant.PeutConstruire==true) {
                    ValiderMatiere1.setVisible(true);
                    }
                else if(joueurCourant==tabJoueurs[1] && joueurCourant.PeutConstruire==true) {
                    ValiderMatiere2.setVisible(true);
                }
                else if(joueurCourant==tabJoueurs[2] && joueurCourant.PeutConstruire==true) {
                    ValiderMatiere3.setVisible(true);
                }
                else if(joueurCourant==tabJoueurs[3] && joueurCourant.PeutConstruire==true) {
                    ValiderMatiere4.setVisible(true);
                }
                if(joueurCourant==tabJoueurs[0] && joueurCourant.prison==true) {
                    joueurCourant.compteurTourPrison++;
                    if(joueurCourant.nbCartes!=0) {
                        Libération1.setVisible(true);
                    }
                    PayerPrison1.setVisible(true);
                }
                else if(joueurCourant==tabJoueurs[1] && joueurCourant.prison==true) {
                    joueurCourant.compteurTourPrison++;
                    if(joueurCourant.nbCartes!=0) {
                        Libération2.setVisible(true);
                    }
                    PayerPrison2.setVisible(true);
                }
                else if(joueurCourant==tabJoueurs[2] && joueurCourant.prison==true) {
                    joueurCourant.compteurTourPrison++;
                    if(joueurCourant.nbCartes!=0) {
                        Libération3.setVisible(true);
                    }
                    PayerPrison3.setVisible(true);
                }
                else if(joueurCourant==tabJoueurs[3] && joueurCourant.prison==true) {
                    joueurCourant.compteurTourPrison++;
                    if(joueurCourant.nbCartes!=0) {
                        Libération4.setVisible(true);
                    }
                    PayerPrison4.setVisible(true);
                }
                if(elim!=true) {
                    Lancer_des.setEnabled(true);
                }
                fin_de_tour1.setVisible(false);
                Acheter1.setVisible(false);
                if(joueurCourant.prison!=true && joueurCourant.nom!=null && compteurDouble==0) {
                    zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon tirez les dés!");
                }
                else if(joueurCourant.prison!=true && joueurCourant.nom!=null) {
                    zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, vous pouvez tirez les dés!");
                }
                else {
                    if(joueurCourant.nom!=null) {
                        zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon essayer de quitter les rattrapages (vous pouvez payer 50 crédits, jouer une carte libération de rattrapages si vous en possédez une ou sinon tenter de faire un double)!");
                    }
                }
            }
        });
        this.repaint();
        
        fin_de_tour2 = new JButton();
        fin_de_tour2.setBounds(180, 60, 170, 50);
        fin_de_tour2.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
        infos_joueurs2.add(fin_de_tour2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 60, 170, 50));
        fin_de_tour2.setBackground(Color.LIGHT_GRAY);
        fin_de_tour2.setText("Fin de tour");
        fin_de_tour2.setFont(new Font("Fin de tour", Font.BOLD, 20));
        fin_de_tour2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if(de1.valeur==de2.valeur && joueurCourant.droitdejouer==true) {
                    if(joueurCourant.credits<0) {
                        eliminationJoueur();
                    }
                    compteurDouble++;
                    if(compteurDouble==3) {
                        joueurCourant.prison=true;
                        if(joueurCourant.credits<0) {
                            eliminationJoueur();
                        }
                        else {
                        changerJoueur();
                        compteurDouble=0;
                        }
                    }
                }
                else {
                    if(joueurCourant.credits<0) {
                        eliminationJoueur();
                    }
                    else {
                        changerJoueur();
                        compteurDouble=0;
                    }
                }
                while(joueurCourant.droitdejouer==false) {
                    joueurCourant.droitdejouer=true;
                    changerJoueur();
                    compteurDouble=0;
                }
                if(joueurCourant.compteurTourPrison==3) {
                    joueurCourant.compteurTourPrison=0;
                    joueurCourant.prison=false;
                    joueurCourant.credits=joueurCourant.credits-50;
                    if(joueurCourant==tabJoueurs[0]) {
                        tabJoueurs[0].pion.coordX=61;
                        tabJoueurs[0].pion.coordY=2;
                        tabJoueurs[0].pion.taille=24;
                        plateauJeu.orientation1=plateauJeu.haut1;
                        plateauJeu.repaint();
                        credits1.setText("Crédits : " + joueurCourant.credits + " ECTS");
                    }
                    else if(joueurCourant==tabJoueurs[1]) {
                        tabJoueurs[1].pion.coordX=18;
                        tabJoueurs[1].pion.coordY=2;
                        tabJoueurs[1].pion.taille=24;
                        plateauJeu.orientation2=plateauJeu.haut2;
                        plateauJeu.repaint();
                        credits2.setText("Crédits : " + joueurCourant.credits + " ECTS");
                    }
                    else if(joueurCourant==tabJoueurs[2]) {
                        tabJoueurs[2].pion.coordX=2;
                        tabJoueurs[2].pion.coordY=20;
                        tabJoueurs[2].pion.taille=24;
                        plateauJeu.orientation3=plateauJeu.gauche3;
                        plateauJeu.repaint();
                        credits3.setText("Crédits : " + joueurCourant.credits + " ECTS");
                    }
                    else if(joueurCourant==tabJoueurs[3]) {
                        tabJoueurs[3].pion.coordX=2;
                        tabJoueurs[3].pion.coordY=63;
                        tabJoueurs[3].pion.taille=24;
                        plateauJeu.orientation4=plateauJeu.gauche4;
                        plateauJeu.repaint();
                        credits4.setText("Crédits : " + joueurCourant.credits + " ECTS");
                    }
                    zone_texte_infos.setText(zone_texte_infos.getText() + "\n" + joueurCourant.nom + " vous venez de rater vos 3 essais aux rattrapages, vous payez donc 50 crédits et réintégrez le parcours scolaire!");
                    }
                if(joueurCourant==tabJoueurs[0] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                    ValiderMatiere1.setVisible(true);
                    }
                else if(joueurCourant==tabJoueurs[1] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                    ValiderMatiere2.setVisible(true);
                }
                else if(joueurCourant==tabJoueurs[2] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                    ValiderMatiere3.setVisible(true);
                }
                else if(joueurCourant==tabJoueurs[3] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                    ValiderMatiere4.setVisible(true);
                }
                if(joueurCourant==tabJoueurs[0] && joueurCourant.prison==true) {
                    joueurCourant.compteurTourPrison++;
                    if(joueurCourant.nbCartes!=0) {
                        Libération1.setVisible(true);
                    }
                    PayerPrison1.setVisible(true);
                }
                else if(joueurCourant==tabJoueurs[1] && joueurCourant.prison==true) {
                    joueurCourant.compteurTourPrison++;
                    if(joueurCourant.nbCartes!=0) {
                        Libération2.setVisible(true);
                    }
                    PayerPrison2.setVisible(true);
                }
                else if(joueurCourant==tabJoueurs[2] && joueurCourant.prison==true) {
                    joueurCourant.compteurTourPrison++;
                    if(joueurCourant.nbCartes!=0) {
                        Libération3.setVisible(true);
                    }
                    PayerPrison3.setVisible(true);
                }
                else if(joueurCourant==tabJoueurs[3] && joueurCourant.prison==true) {
                    joueurCourant.compteurTourPrison++;
                    if(joueurCourant.nbCartes!=0) {
                        Libération4.setVisible(true);
                    }
                    PayerPrison4.setVisible(true);
                }
                if(elim!=true) {
                    Lancer_des.setEnabled(true);
                }
                fin_de_tour2.setVisible(false);
                Acheter2.setVisible(false);
                if(joueurCourant.prison!=true && joueurCourant.nom!=null && compteurDouble==0) {
                    zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon tirez les dés!");
                }
                else if(joueurCourant.prison!=true && joueurCourant.nom!=null) {
                    zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, vous pouvez tirez les dés!");
                }
                else {
                    if(joueurCourant.nom!=null) {
                        zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon essayer de quitter les rattrapages (vous pouvez payer 50 crédits, jouer une carte libération de rattrapages si vous en possédez une ou sinon tenter de faire un double)!");
                    }
                }
            }
        });
        this.repaint();
        
        fin_de_tour3 = new JButton();
        fin_de_tour3.setBounds(180, 60, 170, 50);
        fin_de_tour3.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
        infos_joueurs3.add(fin_de_tour3, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 60, 170, 50));
        fin_de_tour3.setBackground(Color.LIGHT_GRAY);
        fin_de_tour3.setText("Fin de tour");
        fin_de_tour3.setFont(new Font("Fin de tour", Font.BOLD, 20));
        fin_de_tour3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if(de1.valeur==de2.valeur && joueurCourant.droitdejouer==true) {
                    if(joueurCourant.credits<0) {
                        eliminationJoueur();
                    }
                    compteurDouble++;
                    if(compteurDouble==3) {
                        joueurCourant.prison=true;
                        if(joueurCourant.credits<0) {
                            eliminationJoueur();
                        }
                        else {
                            changerJoueur();
                            compteurDouble=0;
                        }
                    }
                }
                else {
                    if(joueurCourant.credits<0) {
                        eliminationJoueur();
                    }
                    else {
                        changerJoueur();
                        compteurDouble=0;
                    }
                }
                while(joueurCourant.droitdejouer==false) {
                    joueurCourant.droitdejouer=true;
                    changerJoueur();
                    compteurDouble=0;
                }
                if(joueurCourant.compteurTourPrison==3) {
                    joueurCourant.compteurTourPrison=0;
                    joueurCourant.prison=false;
                    joueurCourant.credits=joueurCourant.credits-50;
                    if(joueurCourant==tabJoueurs[0]) {
                        tabJoueurs[0].pion.coordX=61;
                        tabJoueurs[0].pion.coordY=2;
                        tabJoueurs[0].pion.taille=24;
                        plateauJeu.orientation1=plateauJeu.haut1;
                        plateauJeu.repaint();
                        credits1.setText("Crédits : " + joueurCourant.credits + " ECTS");
                    }
                    else if(joueurCourant==tabJoueurs[1]) {
                        tabJoueurs[1].pion.coordX=18;
                        tabJoueurs[1].pion.coordY=2;
                        tabJoueurs[1].pion.taille=24;
                        plateauJeu.orientation2=plateauJeu.haut2;
                        plateauJeu.repaint();
                        credits2.setText("Crédits : " + joueurCourant.credits + " ECTS");
                    }
                    else if(joueurCourant==tabJoueurs[2]) {
                        tabJoueurs[2].pion.coordX=2;
                        tabJoueurs[2].pion.coordY=20;
                        tabJoueurs[2].pion.taille=24;
                        plateauJeu.orientation3=plateauJeu.gauche3;
                        plateauJeu.repaint();
                        credits3.setText("Crédits : " + joueurCourant.credits + " ECTS");
                    }
                    else if(joueurCourant==tabJoueurs[3]) {
                        tabJoueurs[3].pion.coordX=2;
                        tabJoueurs[3].pion.coordY=63;
                        tabJoueurs[3].pion.taille=24;
                        plateauJeu.orientation4=plateauJeu.gauche4;
                        plateauJeu.repaint();
                        credits4.setText("Crédits : " + joueurCourant.credits + " ECTS");
                    }
                    zone_texte_infos.setText(zone_texte_infos.getText() + "\n" + joueurCourant.nom + " vous venez de rater vos 3 essais aux rattrapages, vous payez donc 50 crédits et réintégrez le parcours scolaire!");
                    }
                if(joueurCourant==tabJoueurs[0] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                    ValiderMatiere1.setVisible(true);
                    }
                else if(joueurCourant==tabJoueurs[1] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                    ValiderMatiere2.setVisible(true);
                }
                else if(joueurCourant==tabJoueurs[2] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                    ValiderMatiere3.setVisible(true);
                }
                else if(joueurCourant==tabJoueurs[3] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                    ValiderMatiere4.setVisible(true);
                }
                if(joueurCourant==tabJoueurs[0] && joueurCourant.prison==true) {
                    joueurCourant.compteurTourPrison++;
                    if(joueurCourant.nbCartes!=0) {
                        Libération1.setVisible(true);
                    }
                    PayerPrison1.setVisible(true);
                }
                else if(joueurCourant==tabJoueurs[1] && joueurCourant.prison==true) {
                    joueurCourant.compteurTourPrison++;
                    if(joueurCourant.nbCartes!=0) {
                        Libération2.setVisible(true);
                    }
                    PayerPrison2.setVisible(true);
                }
                else if(joueurCourant==tabJoueurs[2] && joueurCourant.prison==true) {
                    joueurCourant.compteurTourPrison++;
                    if(joueurCourant.nbCartes!=0) {
                        Libération3.setVisible(true);
                    }
                    PayerPrison3.setVisible(true);
                }
                else if(joueurCourant==tabJoueurs[3] && joueurCourant.prison==true) {
                    joueurCourant.compteurTourPrison++;
                    if(joueurCourant.nbCartes!=0) {
                        Libération4.setVisible(true);
                    }
                    PayerPrison4.setVisible(true);
                }
                if(elim!=true) {
                    Lancer_des.setEnabled(true);
                }
                fin_de_tour3.setVisible(false);
                Acheter3.setVisible(false);
                if(joueurCourant.prison!=true && joueurCourant.nom!=null && compteurDouble==0) {
                    zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon tirez les dés!");
                }
                else if(joueurCourant.prison!=true && joueurCourant.nom!=null) {
                    zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, vous pouvez tirez les dés!");
                }
                else {
                    if(joueurCourant.nom!=null) {
                        zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon essayer de quitter les rattrapages (vous pouvez payer 50 crédits, jouer une carte libération de rattrapages si vous en possédez une ou sinon tenter de faire un double)!");
                    }
                }
            }
        });
        this.repaint();
        
        fin_de_tour4 = new JButton();
        fin_de_tour4.setBounds(180, 60, 170, 50);
        fin_de_tour4.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
        infos_joueurs4.add(fin_de_tour4, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 60, 170, 50));
        fin_de_tour4.setBackground(Color.LIGHT_GRAY);
        fin_de_tour4.setText("Fin de tour");
        fin_de_tour4.setFont(new Font("Fin de tour", Font.BOLD, 20));
        fin_de_tour4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if(de1.valeur==de2.valeur && joueurCourant.droitdejouer==true) {
                    if(joueurCourant.credits<0) {
                        eliminationJoueur();
                    }
                    compteurDouble++;
                    if(compteurDouble==3) {
                        joueurCourant.prison=true;
                        if(joueurCourant.credits<0) {
                            eliminationJoueur();
                        }
                        else {
                            changerJoueur();
                            compteurDouble=0;
                        }
                    }
                }
                else {
                    if(joueurCourant.credits<0) {
                        eliminationJoueur();
                    }
                    else {
                        changerJoueur();
                        compteurDouble=0;
                    }
                }
                while(joueurCourant.droitdejouer==false) {
                    joueurCourant.droitdejouer=true;
                    changerJoueur();
                    compteurDouble=0;
                }
                if(joueurCourant.compteurTourPrison==3) {
                    joueurCourant.compteurTourPrison=0;
                    joueurCourant.prison=false;
                    joueurCourant.credits=joueurCourant.credits-50;
                    if(joueurCourant==tabJoueurs[0]) {
                        tabJoueurs[0].pion.coordX=61;
                        tabJoueurs[0].pion.coordY=2;
                        tabJoueurs[0].pion.taille=24;
                        plateauJeu.orientation1=plateauJeu.haut1;
                        plateauJeu.repaint();
                        credits1.setText("Crédits : " + joueurCourant.credits + " ECTS");
                    }
                    else if(joueurCourant==tabJoueurs[1]) {
                        tabJoueurs[1].pion.coordX=18;
                        tabJoueurs[1].pion.coordY=2;
                        tabJoueurs[1].pion.taille=24;
                        plateauJeu.orientation2=plateauJeu.haut2;
                        plateauJeu.repaint();
                        credits2.setText("Crédits : " + joueurCourant.credits + " ECTS");
                    }
                    else if(joueurCourant==tabJoueurs[2]) {
                        tabJoueurs[2].pion.coordX=2;
                        tabJoueurs[2].pion.coordY=20;
                        tabJoueurs[2].pion.taille=24;
                        plateauJeu.orientation3=plateauJeu.gauche3;
                        plateauJeu.repaint();
                        credits3.setText("Crédits : " + joueurCourant.credits + " ECTS");
                    }
                    else if(joueurCourant==tabJoueurs[3]) {
                        tabJoueurs[3].pion.coordX=2;
                        tabJoueurs[3].pion.coordY=63;
                        tabJoueurs[3].pion.taille=24;
                        plateauJeu.orientation4=plateauJeu.gauche4;
                        plateauJeu.repaint();
                        credits4.setText("Crédits : " + joueurCourant.credits + " ECTS");
                    }
                    zone_texte_infos.setText(zone_texte_infos.getText() + "\n" + joueurCourant.nom + " vous venez de rater vos 3 essais aux rattrapages, vous payez donc 50 crédits et réintégrez le parcours scolaire!");
                    }
                if(joueurCourant==tabJoueurs[0] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                    ValiderMatiere1.setVisible(true);
                    }
                else if(joueurCourant==tabJoueurs[1] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                    ValiderMatiere2.setVisible(true);
                }
                else if(joueurCourant==tabJoueurs[2] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                    ValiderMatiere3.setVisible(true);
                }
                else if(joueurCourant==tabJoueurs[3] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                    ValiderMatiere4.setVisible(true);
                }
                if(joueurCourant==tabJoueurs[0] && joueurCourant.prison==true) {
                    joueurCourant.compteurTourPrison++;
                    if(joueurCourant.nbCartes!=0) {
                        Libération1.setVisible(true);
                    }
                    PayerPrison1.setVisible(true);
                }
                else if(joueurCourant==tabJoueurs[1] && joueurCourant.prison==true) {
                    joueurCourant.compteurTourPrison++;
                    if(joueurCourant.nbCartes!=0) {
                        Libération2.setVisible(true);
                    }
                    PayerPrison2.setVisible(true);
                }
                else if(joueurCourant==tabJoueurs[2] && joueurCourant.prison==true) {
                    joueurCourant.compteurTourPrison++;
                    if(joueurCourant.nbCartes!=0) {
                        Libération3.setVisible(true);
                    }
                    PayerPrison3.setVisible(true);
                }
                else if(joueurCourant==tabJoueurs[3] && joueurCourant.prison==true) {
                    joueurCourant.compteurTourPrison++;
                    if(joueurCourant.nbCartes!=0) {
                        Libération4.setVisible(true);
                    }
                    PayerPrison4.setVisible(true);
                }
                if(elim!=true) {
                    Lancer_des.setEnabled(true);
                }
                fin_de_tour4.setVisible(false);
                Acheter4.setVisible(false);
                if(joueurCourant.prison!=true && joueurCourant.nom!=null && compteurDouble==0) {
                    zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon tirez les dés!");
                }
                else if(joueurCourant.prison!=true && joueurCourant.nom!=null) {
                    zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, vous pouvez tirez les dés!");
                }
                else {
                    if(joueurCourant.nom!=null) {
                        zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon essayer de quitter les rattrapages (vous pouvez payer 50 crédits, jouer une carte libération de rattrapages si vous en possédez une ou sinon tenter de faire un double)!");
                    }
                }
            }
        });
        this.repaint();
        
        Lancer_des = new JButton();
        Lancer_des.setBounds(180, 5, 170, 60);
        Lancer_des.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
        panel_boutons.add(Lancer_des, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 5, 170, 60));
        Lancer_des.setBackground(new Color(226,252,251));
        Lancer_des.setText("Lancer les dés");
        Lancer_des.setFont(new Font("Lancer les dés", Font.BOLD, 16));
        Lancer_des.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Lancer_des.setEnabled(false);
                ValiderMatiere1.setVisible(false);
                ValiderMatiere2.setVisible(false);
                ValiderMatiere3.setVisible(false);
                ValiderMatiere4.setVisible(false);
                Libération1.setVisible(false);
                PayerPrison1.setVisible(false);
                Libération2.setVisible(false);
                PayerPrison2.setVisible(false);
                Libération3.setVisible(false);
                PayerPrison3.setVisible(false);
                Libération4.setVisible(false);
                PayerPrison4.setVisible(false);
                Acheter1.setVisible(false);
                Acheter2.setVisible(false);
                Acheter3.setVisible(false);
                Acheter4.setVisible(false);
                nbCartes1.setVisible(true);
                nbCartes2.setVisible(true);
                nbCartes3.setVisible(true);
                nbCartes4.setVisible(true);
                Thread thread = new Thread(){
                    public void run(){
                        lancerDes();
                        carte="";
                        cartespéciale="";
                        choixcom=2;
                        tripledouble=false;
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
                        if(joueurCourant.prison==false) {
                        zone_texte_infos.setText(joueurCourant.nom + " vous avancez de " + (de1.valeur+de2.valeur) + " cases");
                        }
                        else {
                            if(de1.valeur!=de2.valeur) {
                                zone_texte_infos.setText(joueurCourant.nom + " vos doubles ne vous permettent pas d'éviter les rattrapages cette fois-ci, rendez-vous à la prochaine kholle!");
                            }
                        }
                        int caseActuelle = 0;
                        for(int i=0;i<plateau.plateaudejeu.length;i++) {
                            if(joueurCourant.pion.caseassociee==plateau.plateaudejeu[i]) {
                                caseActuelle = i;
                            }
                        }
                        if(joueurCourant.prison!=true) {
                            DeplacerPion(joueurCourant.pion, plateau.plateaudejeu[(caseActuelle+(de1.valeur+de2.valeur))%40], "Normal");
                            deplacerPion(joueurCourant.pion,(de1.valeur+de2.valeur)%40,plateau.plateaudejeu[caseActuelle]);
                            /*DeplacerPion(joueurCourant.pion, plateau.plateaudejeu[(caseActuelle+1)%40], "Téléportation");
                            deplacerPion(joueurCourant.pion,1%40,plateau.plateaudejeu[caseActuelle]);*/
                            zone_texte_infos.setText(zone_texte_infos.getText() + " et arrivez à '" + joueurCourant.pion.caseassociee.nom + "'!");
                        }
                        else {
                            if(de1.valeur==de2.valeur) {
                                joueurCourant.compteurTourPrison=0;
                                joueurCourant.prison=false;
                                if(joueurCourant==tabJoueurs[0]) {
                                    tabJoueurs[0].pion.coordX=61;
                                    tabJoueurs[0].pion.coordY=2;
                                    tabJoueurs[0].pion.taille=24;
                                    plateauJeu.orientation1=plateauJeu.haut1;
                                    plateauJeu.repaint();
                                    credits1.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                else if(joueurCourant==tabJoueurs[1]) {
                                    tabJoueurs[1].pion.coordX=18;
                                    tabJoueurs[1].pion.coordY=2;
                                    tabJoueurs[1].pion.taille=24;
                                    plateauJeu.orientation2=plateauJeu.haut2;
                                    plateauJeu.repaint();
                                    credits2.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                else if(joueurCourant==tabJoueurs[2]) {
                                    tabJoueurs[2].pion.coordX=2;
                                    tabJoueurs[2].pion.coordY=20;
                                    tabJoueurs[2].pion.taille=24;
                                    plateauJeu.orientation3=plateauJeu.gauche3;
                                    plateauJeu.repaint();
                                    credits3.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                else if(joueurCourant==tabJoueurs[3]) {
                                    tabJoueurs[3].pion.coordX=2;
                                    tabJoueurs[3].pion.coordY=63;
                                    tabJoueurs[3].pion.taille=24;
                                    plateauJeu.orientation4=plateauJeu.gauche4;
                                    plateauJeu.repaint();
                                    credits4.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                if(elim!=true) {
                                    Lancer_des.setEnabled(true);
                                }
                                zone_texte_infos.setText(joueurCourant.nom + " vous échappez miraculeusement aux rattrapages en réalisant un double! Vous sortez donc des rattrapages et pouvez lancer les dés!");
                            }
                            else {
                                if(joueurCourant.credits<0) {
                                    eliminationJoueur();
                                }
                                else {
                                    changerJoueur();
                                    compteurDouble=0;
                                }
                                while(joueurCourant.droitdejouer==false) {
                                    joueurCourant.droitdejouer=true;
                                    changerJoueur();
                                    compteurDouble=0;
                                }
                                if(joueurCourant.compteurTourPrison==3) {
                                    joueurCourant.compteurTourPrison=0;
                                    joueurCourant.prison=false;
                                    joueurCourant.credits=joueurCourant.credits-50;
                                    if(joueurCourant==tabJoueurs[0]) {
                                        tabJoueurs[0].pion.coordX=61;
                                        tabJoueurs[0].pion.coordY=2;
                                        tabJoueurs[0].pion.taille=24;
                                        plateauJeu.orientation1=plateauJeu.haut1;
                                        plateauJeu.repaint();
                                        credits1.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                    }
                                    else if(joueurCourant==tabJoueurs[1]) {
                                        tabJoueurs[1].pion.coordX=18;
                                        tabJoueurs[1].pion.coordY=2;
                                        tabJoueurs[1].pion.taille=24;
                                        plateauJeu.orientation2=plateauJeu.haut2;
                                        plateauJeu.repaint();
                                        credits2.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                    }
                                    else if(joueurCourant==tabJoueurs[2]) {
                                        tabJoueurs[2].pion.coordX=2;
                                        tabJoueurs[2].pion.coordY=20;
                                        tabJoueurs[2].pion.taille=24;
                                        plateauJeu.orientation3=plateauJeu.gauche3;
                                        plateauJeu.repaint();
                                        credits3.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                    }
                                    else if(joueurCourant==tabJoueurs[3]) {
                                        tabJoueurs[3].pion.coordX=2;
                                        tabJoueurs[3].pion.coordY=63;
                                        tabJoueurs[3].pion.taille=24;
                                        plateauJeu.orientation4=plateauJeu.gauche4;
                                        plateauJeu.repaint();
                                        credits4.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                    }
                                    zone_texte_infos.setText(zone_texte_infos.getText() + "\n" + joueurCourant.nom + " vous venez de rater vos 3 essais aux rattrapages, vous payez donc 50 crédits et réintégrez le parcours scolaire!");
                                }
                                if(joueurCourant==tabJoueurs[0] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                    ValiderMatiere1.setVisible(true);
                                }
                                else if(joueurCourant==tabJoueurs[1] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                    ValiderMatiere2.setVisible(true);
                                }
                                else if(joueurCourant==tabJoueurs[2] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                    ValiderMatiere3.setVisible(true);
                                }
                                else if(joueurCourant==tabJoueurs[3] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                    ValiderMatiere4.setVisible(true);
                                }
                                if(joueurCourant==tabJoueurs[0] && joueurCourant.prison==true) {
                                    joueurCourant.compteurTourPrison++;
                                    if(joueurCourant.nbCartes!=0) {
                                        Libération1.setVisible(true);
                                    }
                                    PayerPrison1.setVisible(true);
                                }
                                else if(joueurCourant==tabJoueurs[1] && joueurCourant.prison==true) {
                                    joueurCourant.compteurTourPrison++;
                                    if(joueurCourant.nbCartes!=0) {
                                        Libération2.setVisible(true);
                                    }
                                    PayerPrison2.setVisible(true);
                                }
                                else if(joueurCourant==tabJoueurs[2] && joueurCourant.prison==true) {
                                    joueurCourant.compteurTourPrison++;
                                    if(joueurCourant.nbCartes!=0) {
                                        Libération3.setVisible(true);
                                    }
                                    PayerPrison3.setVisible(true);
                                }
                                else if(joueurCourant==tabJoueurs[3] && joueurCourant.prison==true) {
                                    joueurCourant.compteurTourPrison++;
                                    if(joueurCourant.nbCartes!=0) {
                                        Libération4.setVisible(true);
                                    }
                                    PayerPrison4.setVisible(true);
                                }
                                if(elim!=true) {
                                    Lancer_des.setEnabled(true);
                                }
                                if(joueurCourant.prison!=true && joueurCourant.nom!=null && compteurDouble==0) {
                                    zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon tirez les dés!");
                                }
                                else if(joueurCourant.prison!=true && joueurCourant.nom!=null) {
                                    zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, vous pouvez tirez les dés!");
                                }
                                else {
                                    if(joueurCourant.nom!=null) {
                                        zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon essayer de quitter les rattrapages (vous pouvez payer 50 crédits, jouer une carte libération de rattrapages si vous en possédez une ou sinon tenter de faire un double)!");
                                    }
                                }
                            }
                        }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                };
                thread.start();                
            } 
        });
        this.repaint();
        
        
        Panelsalle = new SallesPanel();
        Panelsalle.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        Panelsalle.setVisible(true);
        Panelsalle.setBackground(Color.YELLOW);
        Panelsalle.setPreferredSize(new Dimension(1030,734));
        
        LabelSalles = new JLabel("A gauche de chaque salle se trouve le pion du joueur proprietaire de la salle.");
        Dimension Labelsallesdim = LabelSalles.getPreferredSize();
        LabelSalles.setFont(new Font("Monopoly", Font.BOLD, 20));
        LabelSalles.setForeground(Color.red);
        
        Salles = new JButton();
        Salles.setBounds(180, 70, 170, 60);
        Salles.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
        panel_boutons.add(Salles, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 70, 170, 60));
        Salles.setBackground(new Color(226,252,251));
        Salles.setText("Salles");
        Salles.setFont(new Font("Salles", Font.BOLD, 16));
        Salles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JFrame FrameSalle = new JFrame("Salles");
                FrameSalle.setBounds(280, 30, 1100, 800);
                FrameSalle.setVisible(true);
                FrameSalle.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
                FrameSalle.add(Panelsalle,new org.netbeans.lib.awtextra.AbsoluteConstraints(0,0,-1,-1));
                FrameSalle.add(LabelSalles,new org.netbeans.lib.awtextra.AbsoluteConstraints(250,737,-1,-1));
                FrameSalle.repaint();
                int CoordX=0;
                int CoordY=0;
                JLabel imagePion = new JLabel("");
                for (int i=0; i<4; i++){
                    if(tabJoueurs[i].sallesPossedees != null) {
                       for(int j=0; j<tabJoueurs[i].sallesPossedees.size(); j++){
                           switch (i){
                               case 0:
                                   imagePion = new JLabel(new ImageIcon("PionCalculatrice3_gauche.png"));
                                   break;
                               case 1:
                                   imagePion = new JLabel(new ImageIcon("PionDiode3_gauche.png"));
                                   break;
                               case 2:
                                   imagePion = new JLabel(new ImageIcon("PionErlenmeyer3_gauche.png"));
                                   break;
                               case 3:
                                   imagePion = new JLabel(new ImageIcon("PionOlga3_gauche.png"));
                                   break;
                               default:
                                   imagePion = new JLabel("PAS DE JOUEUR");
                                   break;
                           }
                           int idCoord = tabJoueurs[i].sallesPossedees.get(j).idCase;
                           CoordX = idCoord%7*148;
                           CoordY = idCoord/7*200;
                           //Panelsalle.add(imagePion,new org.netbeans.lib.awtextra.AbsoluteConstraints(CoordX,CoordY,-1,-1));
                           imagePion.setVisible(true);
                           FrameSalle.repaint();
                       }
                    }
                    else {
                        imagePion = new JLabel("");
                        imagePion.setVisible(false);
                        FrameSalle.repaint();
                    }
                }
                Panelsalle.add(imagePion,new org.netbeans.lib.awtextra.AbsoluteConstraints(CoordX,CoordY,-1,-1));
                
            }});
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
        
        elimine1 = new JLabel("Joueur éliminé");
        Dimension J1_élim = elimine1.getPreferredSize();
        elimine1.setFont(new Font("Joueur éliminé", Font.BOLD, 25));
        elimine1.setForeground(Color.WHITE);
        infos_joueurs1.add(elimine1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90,1));
        this.repaint();
        
        elimine2 = new JLabel("Joueur éliminé");
        Dimension J2_élim = elimine2.getPreferredSize();
        elimine2.setFont(new Font("Joueur éliminé", Font.BOLD, 25));
        elimine2.setForeground(Color.WHITE);
        infos_joueurs2.add(elimine2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90,1));
        this.repaint();
        
        elimine3 = new JLabel("Joueur éliminé");
        Dimension J3_élim = elimine3.getPreferredSize();
        elimine3.setFont(new Font("Joueur éliminé", Font.BOLD, 25));
        elimine3.setForeground(Color.WHITE);
        infos_joueurs3.add(elimine3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90,1));
        this.repaint();
        
        elimine4 = new JLabel("Joueur éliminé");
        Dimension J4_élim = elimine4.getPreferredSize();
        elimine4.setFont(new Font("Joueur éliminé", Font.BOLD, 25));
        elimine4.setForeground(Color.WHITE);
        infos_joueurs4.add(elimine4, new org.netbeans.lib.awtextra.AbsoluteConstraints(90,1));
        this.repaint();
        
        Infos_partie = new JLabel("Infos partie:");
        Dimension text_infos_partie = Infos_partie.getPreferredSize();
        Infos_partie.setFont(new Font("Infos partie;", Font.BOLD, 15));
        infos_partie.add(Infos_partie, new org.netbeans.lib.awtextra.AbsoluteConstraints(5,5));
        this.repaint();
        
        pionC = new JLabel(new ImageIcon("PionCalculatrice3_gauche.png"));
        Dimension pionc = pionC.getPreferredSize();
        infos_joueurs1.add(pionC, new org.netbeans.lib.awtextra.AbsoluteConstraints(300,15));
        this.repaint();
        
        pionD = new JLabel(new ImageIcon("PionDiode3_gauche.png"));
        Dimension piond = pionD.getPreferredSize();
        infos_joueurs2.add(pionD, new org.netbeans.lib.awtextra.AbsoluteConstraints(300,15));
        this.repaint();
        
        pionE = new JLabel(new ImageIcon("PionErlenmeyer3_gauche.png"));
        Dimension pione = pionE.getPreferredSize();
        infos_joueurs3.add(pionE, new org.netbeans.lib.awtextra.AbsoluteConstraints(300,15));
        this.repaint();
        
        pionO = new JLabel(new ImageIcon("PionOlga3_gauche.png"));
        Dimension piono = pionO.getPreferredSize();
        infos_joueurs4.add(pionO, new org.netbeans.lib.awtextra.AbsoluteConstraints(300,15));
        this.repaint();
        
        credits1 = new JLabel("Crédits : " + "ECTS");
        Dimension text_credits1 = credits1.getPreferredSize();
        credits1.setFont(new Font("Crédits : " + "ECTS", Font.BOLD, 19));
        infos_joueurs1.add(credits1, new org.netbeans.lib.awtextra.AbsoluteConstraints(5,35));
        this.repaint();
        
        credits2 = new JLabel("Crédits : " + "ECTS");
        Dimension text_credits2 = credits2.getPreferredSize();
        credits2.setFont(new Font("Crédits : " + "ECTS", Font.BOLD, 19));
        infos_joueurs2.add(credits2, new org.netbeans.lib.awtextra.AbsoluteConstraints(5,35));
        this.repaint();
        
        credits3 = new JLabel("Crédits : " + "ECTS");
        Dimension text_credits3 = credits3.getPreferredSize();
        credits3.setFont(new Font("Crédits : " + "ECTS", Font.BOLD, 19));
        infos_joueurs3.add(credits3, new org.netbeans.lib.awtextra.AbsoluteConstraints(5,35));
        this.repaint();
        
        credits4 = new JLabel("Crédits : " + "ECTS");
        Dimension text_credits4 = credits4.getPreferredSize();
        credits4.setFont(new Font("Crédits : " + "ECTS", Font.BOLD, 19));
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
        Dimension text_nbCartes4 = nbCartes4.getPreferredSize();
        nbCartes4.setFont(new Font("Cartes libération de rattrapages : ", Font.BOLD, 9));
        infos_joueurs4.add(nbCartes4, new org.netbeans.lib.awtextra.AbsoluteConstraints(187,120));
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
                
        Autres1 = new JLabel("Autre(s) : 0");
        Dimension autres1_size = Autres1.getPreferredSize();
        Autres1.setFont(new Font("Autre(s) : 0", Font.BOLD, 10));
        infos_joueurs1.add(Autres1, new org.netbeans.lib.awtextra.AbsoluteConstraints(292,143));
        this.repaint();
        
        Autres2 = new JLabel("Autre(s) : 0");
        Dimension autres2_size = Autres2.getPreferredSize();
        Autres2.setFont(new Font("Autre(s) : 0", Font.BOLD, 10));
        infos_joueurs2.add(Autres2, new org.netbeans.lib.awtextra.AbsoluteConstraints(292,143));
        this.repaint();
        
        Autres3 = new JLabel("Autre(s) : 0");
        Dimension autres3_size = Autres3.getPreferredSize();
        Autres3.setFont(new Font("Autre(s) : 0", Font.BOLD, 10));
        infos_joueurs3.add(Autres3, new org.netbeans.lib.awtextra.AbsoluteConstraints(292,143));
        this.repaint();
        
        Autres4 = new JLabel("Autre(s) : 0");
        Dimension autres4_size = Autres4.getPreferredSize();
        Autres4.setFont(new Font("Autre(s) : 0", Font.BOLD, 10));
        infos_joueurs4.add(Autres4, new org.netbeans.lib.awtextra.AbsoluteConstraints(292,143));
        this.repaint();
        
        LabelParcGratuit = new JLabel("<html>Credits : <br/>"+argentParcGratuit+" ECTS");
        Dimension LabelParcGratuit_size = LabelParcGratuit.getPreferredSize();
        LabelParcGratuit.setFont(new Font("Monopoly", Font.PLAIN, 16));
        plateauJeu.add(LabelParcGratuit, new org.netbeans.lib.awtextra.AbsoluteConstraints(605,120));
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
    private JLabel elimine1;
    private JLabel elimine2;
    private JLabel elimine3;
    private JLabel elimine4;
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
    private JButton fin_de_tour1;
    private JButton fin_de_tour2;
    private JButton fin_de_tour3;
    private JButton fin_de_tour4;
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
    private JLabel LabelParcGratuit;
    SallesPanel Panelsalle;
    private JLabel LabelSalles;
    private JLabel pionC;
    private JLabel pionD;
    private JLabel pionE;
    private JLabel pionO;
    private JButton marronV;
    private JButton bleucielV;
    private JButton violetV;
    private JButton orangeV;
    private JButton rougeV;
    private JButton jauneV;
    private JButton vertV;
    private JButton bleufoncéV;
    private JLabel validerLabel;
    private JLabel validerJoueur;
    
    
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
        /*if(lireDouble()==true) {
            compteurDouble++;
        }*/
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
        if(joueurCourant==tabJoueurs[0]) {
            if(tabJoueurs[1].pion.caseassociee!=caseInitiale && tabJoueurs[2].pion.caseassociee!=caseInitiale && tabJoueurs[3].pion.caseassociee!=caseInitiale) {
                caseInitiale.occupant=false;
            }
        }
        else if(joueurCourant==tabJoueurs[1]) {
            if(tabJoueurs[0].pion.caseassociee!=caseInitiale && tabJoueurs[2].pion.caseassociee!=caseInitiale && tabJoueurs[3].pion.caseassociee!=caseInitiale) {
                caseInitiale.occupant=false;
            }
        }
        else if(joueurCourant==tabJoueurs[2]) {
            if(tabJoueurs[0].pion.caseassociee!=caseInitiale && tabJoueurs[1].pion.caseassociee!=caseInitiale && tabJoueurs[3].pion.caseassociee!=caseInitiale) {
                caseInitiale.occupant=false;
            }
        }
        else if(joueurCourant==tabJoueurs[3]) {
            if(tabJoueurs[0].pion.caseassociee!=caseInitiale && tabJoueurs[1].pion.caseassociee!=caseInitiale && tabJoueurs[2].pion.caseassociee!=caseInitiale) {
                caseInitiale.occupant=false;
            }
        }
        for(int i=0; i<plateau.plateaudejeu.length; i++) {
            if(caseInitiale==plateau.plateaudejeu[i]) {
                if (i+nbcases <= 39) {
                    pionAssocie.caseassociee=plateau.plateaudejeu[i+nbcases];
                }
                else {
                    pionAssocie.caseassociee=plateau.plateaudejeu[i+nbcases-39-1]; //tour de plateau
                    if (i+nbcases-39-1!=0) {
                        if(joueurCourant.prison!=true && caseInitiale!=plateau.plateaudejeu[30] && negatif!=true) {
                            joueurCourant.credits = joueurCourant.credits + 200; //200crédits quand on a fait un tour de plateau
                            zone_texte_infos.setText(zone_texte_infos.getText() + "\n" + joueurCourant.nom + " passez par le début d'année et touchez 200 ECTS!");
                        }
                        else {
                            zone_texte_infos.setText(zone_texte_infos.getText() + "\n" + joueurCourant.nom + " allez aux rattrapages!");
                        }
                    }
                    else {
                        if(carte!="chance3" && carte!="chance13") {
                            joueurCourant.credits = joueurCourant.credits + 400; //400crédits quand on tombe sur la case départ
                            zone_texte_infos.setText(zone_texte_infos.getText() + "\n" + joueurCourant.nom + " vous vous arrêtez au début d'année et touchez 400 ECTS!");
                        }
                        else {
                            if(carte=="chance3") {
                                joueurCourant.credits = joueurCourant.credits + 200;
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n" + joueurCourant.nom + " vous vous arrêtez au début d'année et touchez 200 ECTS!");
                            }
                            /*else {
                                if(pionAssocie.modele=="Calculette") {
                                    tabJoueurs[0].credits = tabJoueurs[0].credits + 200;
                                    zone_texte_infos.setText(zone_texte_infos.getText() + "\n" + tabJoueurs[0].nom + " vous vous arrêtez au début d'année et touchez 200 ECTS car c'est la JPO!");
                                }
                                else if(pionAssocie.modele=="Diode") {
                                    tabJoueurs[1].credits = tabJoueurs[1].credits + 200;
                                    zone_texte_infos.setText(zone_texte_infos.getText() + "\n" + tabJoueurs[1].nom + " vous vous arrêtez au début d'année et touchez 200 ECTS car c'est la JPO!");
                                }
                                if(pionAssocie.modele=="Erlenmeyer") {
                                    tabJoueurs[2].credits = tabJoueurs[2].credits + 200;
                                    zone_texte_infos.setText(zone_texte_infos.getText() + "\n" + tabJoueurs[2].nom + " vous vous arrêtez au début d'année et touchez 200 ECTS car c'est la JPO!");
                                }
                                if(pionAssocie.modele=="Olga") {
                                    tabJoueurs[3].credits = tabJoueurs[3].credits + 200;
                                    zone_texte_infos.setText(zone_texte_infos.getText() + "\n" + tabJoueurs[3].nom + " vous vous arrêtez au début d'année et touchez 200 ECTS car c'est la JPO!");
                                }
                            }*/
                        }
                    }
                    if(joueurCourant==tabJoueurs[0]) {
                        credits1.setText("Crédits : "+ joueurCourant.credits + " ECTS");
                    }
                    else if(joueurCourant==tabJoueurs[1]) {
                        credits2.setText("Crédits : "+ joueurCourant.credits + " ECTS");
                    }
                    else if(joueurCourant==tabJoueurs[2]) {
                        credits3.setText("Crédits : "+ joueurCourant.credits + " ECTS");
                    }
                    else if(joueurCourant==tabJoueurs[3]) {
                        credits4.setText("Crédits : "+ joueurCourant.credits + " ECTS");
                    }
                }
            }
        }
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
     
     
     public boolean faireActionCase(){ // A VERIFIER 
        Case caseDuJoueur = joueurCourant.pion.caseassociee;
        if (caseDuJoueur == plateau.plateaudejeu[2] || caseDuJoueur == plateau.plateaudejeu[17] || caseDuJoueur == plateau.plateaudejeu[33]) { //s'il est sur une case caisse de communauté
            Carte carteTiree = tirerCarte(paquetCommunaute);
                JFrame FrameCarte = new JFrame();
                FrameCarte.setVisible(true);
                FrameCarte.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
                JLabel carteLabel;
                switch (carteTiree.idCarte) {
                    case 0 : 
                        carteLabel = new JLabel(new ImageIcon("src/cartes/0com.png"));
                        FrameCarte.setBounds(450, 200, 615, 430);
                        break;
                    case 1 : 
                        carteLabel = new JLabel(new ImageIcon("src/cartes/1com.png"));
                        FrameCarte.setBounds(450, 200, 615, 430);
                        break;
                    case 2 : 
                        carteLabel = new JLabel(new ImageIcon("src/cartes/2com.png"));
                        FrameCarte.setBounds(450, 200, 615, 430);
                        break;
                    case 3 : 
                        carteLabel = new JLabel(new ImageIcon("src/cartes/3com.png"));
                        FrameCarte.setBounds(450, 200, 615, 430);
                        break;
                    case 4 : 
                        carteLabel = new JLabel(new ImageIcon("src/cartes/4com.png"));
                        FrameCarte.setBounds(450, 100, 615, 630);
                        break;
                    case 5 : 
                        carteLabel = new JLabel(new ImageIcon("src/cartes/5com.png"));
                        FrameCarte.setBounds(450, 200, 615, 430);
                        break;
                    case 6 : 
                        carteLabel = new JLabel(new ImageIcon("src/cartes/6com.png"));
                        FrameCarte.setBounds(450, 200, 615, 430);
                        break;
                    case 7 : 
                        carteLabel = new JLabel(new ImageIcon("src/cartes/7com.png"));
                        FrameCarte.setBounds(450, 200, 615, 430);
                        break;
                    case 8 : 
                        carteLabel = new JLabel(new ImageIcon("src/cartes/8com.png"));
                        FrameCarte.setBounds(450, 200, 615, 430);
                        break;
                    case 9 : 
                        carteLabel = new JLabel(new ImageIcon("src/cartes/9com.png"));
                        FrameCarte.setBounds(450, 200, 615, 430);
                        break;
                    case 10 : 
                        carteLabel = new JLabel(new ImageIcon("src/cartes/10com.png"));
                        FrameCarte.setBounds(450, 200, 615, 430);
                        break;
                    case 11 : 
                        carteLabel = new JLabel(new ImageIcon("src/cartes/11com.png"));
                        FrameCarte.setBounds(450, 200, 615, 430);
                        break;
                    case 12 : 
                        carteLabel = new JLabel(new ImageIcon("src/cartes/12com.png"));
                        FrameCarte.setBounds(450, 200, 615, 430);
                        break;
                    case 13 : 
                        carteLabel = new JLabel(new ImageIcon("src/cartes/13com.png"));
                        FrameCarte.setBounds(450, 200, 615, 430);
                        break;   
                    case 14 : 
                        carteLabel = new JLabel(new ImageIcon("src/cartes/14com.png"));
                        FrameCarte.setBounds(450, 200, 615, 430);
                        break;
                    case 15 :
                        carteLabel = new JLabel(new ImageIcon("src/cartes/15com.png"));
                        FrameCarte.setBounds(450, 200, 615, 430);
                        break;
                    default :
                        carteLabel = new JLabel("PAS DE CARTE");
                }
                FrameCarte.add(carteLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0,0));
                carteLabel.setVisible(true);
                FrameCarte.repaint();
            if (carteTiree.idCarte != 4) {
                faireActionCarte(carteTiree);
            }
            else {
                cartespéciale="com4";
                JButton Cartechance = new JButton("Tirer une carte chance");
                JButton credits10 = new JButton("Perdre 10 credits");
                FrameCarte.add(Cartechance, new org.netbeans.lib.awtextra.AbsoluteConstraints(100,450));
                FrameCarte.add(credits10, new org.netbeans.lib.awtextra.AbsoluteConstraints(350,450));
                Cartechance.setFont(new Font("Monopoly", Font.PLAIN, 20));
                credits10.setFont(new Font("Monopoly", Font.PLAIN, 20));
                Cartechance.setBackground(Color.YELLOW);
                credits10.setBackground(Color.YELLOW);
                credits10.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        choixcom = 0;
                        credits10.setEnabled(false);
                        Cartechance.setEnabled(false);
                        faireActionCarteSimple(choixcom);
                        credits1.setText("Crédits : " + tabJoueurs[0].credits + " ECTS");
                        credits2.setText("Crédits : " + tabJoueurs[1].credits + " ECTS");
                        credits3.setText("Crédits : " + tabJoueurs[2].credits + " ECTS");
                        credits4.setText("Crédits : " + tabJoueurs[3].credits + " ECTS");
                        if(de1.valeur==de2.valeur && joueurCourant.droitdejouer==true) {
                            if(joueurCourant.credits<0) {
                                eliminationJoueur();
                            }
                            compteurDouble++;
                            if(compteurDouble==3) {
                                joueurCourant.prison=true;
                                if(joueurCourant.credits<0) {
                                    eliminationJoueur();
                                }
                                else {
                                    changerJoueur();
                                    compteurDouble=0;
                                }
                            }
                        }
                        else {
                            if(joueurCourant.credits<0) {
                                eliminationJoueur();
                            }
                            else {
                                changerJoueur();
                                compteurDouble=0;
                            }
                        }
                        while(joueurCourant.droitdejouer==false) {
                            joueurCourant.droitdejouer=true;
                            changerJoueur();
                            compteurDouble=0;
                        }
                        if(joueurCourant.compteurTourPrison==3) {
                            joueurCourant.compteurTourPrison=0;
                            joueurCourant.prison=false;
                            joueurCourant.credits=joueurCourant.credits-50;
                            if(joueurCourant==tabJoueurs[0]) {
                                tabJoueurs[0].pion.coordX=61;
                                tabJoueurs[0].pion.coordY=2;
                                tabJoueurs[0].pion.taille=24;
                                plateauJeu.orientation1=plateauJeu.haut1;
                                plateauJeu.repaint();
                                credits1.setText("Crédits : " + joueurCourant.credits + " ECTS");
                            }
                            else if(joueurCourant==tabJoueurs[1]) {
                                tabJoueurs[1].pion.coordX=18;
                                tabJoueurs[1].pion.coordY=2;
                                tabJoueurs[1].pion.taille=24;
                                plateauJeu.orientation2=plateauJeu.haut2;
                                plateauJeu.repaint();
                                credits2.setText("Crédits : " + joueurCourant.credits + " ECTS");
                            }
                            else if(joueurCourant==tabJoueurs[2]) {
                                tabJoueurs[2].pion.coordX=2;
                                tabJoueurs[2].pion.coordY=20;
                                tabJoueurs[2].pion.taille=24;
                                plateauJeu.orientation3=plateauJeu.gauche3;
                                plateauJeu.repaint();
                                credits3.setText("Crédits : " + joueurCourant.credits + " ECTS");
                            }
                            else if(joueurCourant==tabJoueurs[3]) {
                                tabJoueurs[3].pion.coordX=2;
                                tabJoueurs[3].pion.coordY=63;
                                tabJoueurs[3].pion.taille=24;
                                plateauJeu.orientation4=plateauJeu.gauche4;
                                plateauJeu.repaint();
                                credits4.setText("Crédits : " + joueurCourant.credits + " ECTS");
                            }
                            zone_texte_infos.setText(zone_texte_infos.getText() + "\n" + joueurCourant.nom + " vous venez de rater vos 3 essais aux rattrapages, vous payez donc 50 crédits et réintégrez le parcours scolaire!");
                        }
                        if(joueurCourant==tabJoueurs[0] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                            ValiderMatiere1.setVisible(true);
                            }
                        else if(joueurCourant==tabJoueurs[1] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                            ValiderMatiere2.setVisible(true);
                        }
                        else if(joueurCourant==tabJoueurs[2] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                            ValiderMatiere3.setVisible(true);
                        }
                        else if(joueurCourant==tabJoueurs[3] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                            ValiderMatiere4.setVisible(true);
                        }
                        if(joueurCourant==tabJoueurs[0] && joueurCourant.prison==true) {
                            joueurCourant.compteurTourPrison++;
                            if(joueurCourant.nbCartes!=0) {
                                Libération1.setVisible(true);
                            }
                            PayerPrison1.setVisible(true);
                        }
                        else if(joueurCourant==tabJoueurs[1] && joueurCourant.prison==true) {
                            joueurCourant.compteurTourPrison++;
                            if(joueurCourant.nbCartes!=0) {
                                Libération2.setVisible(true);
                            }
                            PayerPrison2.setVisible(true);
                        }
                        else if(joueurCourant==tabJoueurs[2] && joueurCourant.prison==true) {
                            joueurCourant.compteurTourPrison++;
                            if(joueurCourant.nbCartes!=0) {
                                Libération3.setVisible(true);
                            }
                            PayerPrison3.setVisible(true);
                        }
                        else if(joueurCourant==tabJoueurs[3] && joueurCourant.prison==true) {
                            joueurCourant.compteurTourPrison++;
                            if(joueurCourant.nbCartes!=0) {
                                Libération4.setVisible(true);
                            }
                            PayerPrison4.setVisible(true);
                        }
                        fin_de_tour1.setVisible(false);
                        if(elim!=true) {
                            Lancer_des.setEnabled(true);
                        }
                        choixcom=2;
                        carte="";
                        cartespéciale="";
                        if(joueurCourant.prison!=true && joueurCourant.nom!=null && compteurDouble==0) {
                            zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon tirez les dés!");
                        }
                        else if(joueurCourant.prison!=true && joueurCourant.nom!=null) {
                            zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, vous pouvez tirez les dés!");
                        }
                        else {
                            if(joueurCourant.nom!=null) {
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon essayer de quitter les rattrapages (vous pouvez payer 50 crédits, jouer une carte libération de rattrapages si vous en possédez une ou sinon tenter de faire un double)!");
                            }
                        }
                    }
                });
                Cartechance.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        choixcom = 1;
                        credits10.setEnabled(false);
                        Cartechance.setEnabled(false);
                        Carte laCarte = faireActionCarteSimple(choixcom);
                        if (laCarte != null) {
                            JFrame FrameCarte2 = new JFrame();
                            FrameCarte2.setVisible(true);
                            FrameCarte2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
                            JLabel carteLabel2;
                            switch (laCarte.idCarte) {
                                case 0 : 
                                    carteLabel2 = new JLabel(new ImageIcon("src/cartes/0cha.png"));
                                    FrameCarte2.setBounds(450, 200, 615, 430);
                                    break;
                                case 1 : 
                                    carteLabel2 = new JLabel(new ImageIcon("src/cartes/1cha.png"));
                                    FrameCarte2.setBounds(450, 200, 615, 430);
                                    break;
                                case 2 : 
                                    carteLabel2 = new JLabel(new ImageIcon("src/cartes/2cha.png"));
                                    FrameCarte2.setBounds(450, 200, 615, 430);
                                    break;
                                case 3 : 
                                    carteLabel2 = new JLabel(new ImageIcon("src/cartes/3cha.png"));
                                    FrameCarte2.setBounds(450, 200, 615, 430);
                                    break;
                                case 4 : 
                                    carteLabel2 = new JLabel(new ImageIcon("src/cartes/4cha.png"));
                                    FrameCarte2.setBounds(450, 200, 615, 430);
                                    break;
                                case 5 : 
                                    carteLabel2 = new JLabel(new ImageIcon("src/cartes/5cha.png"));
                                    FrameCarte2.setBounds(450, 200, 615, 430);
                                    break;
                                case 6 :
                                    carteLabel2 = new JLabel(new ImageIcon("src/cartes/6cha.png"));
                                    FrameCarte2.setBounds(450, 200, 615, 430);
                                    break;
                                case 7 : 
                                    carteLabel2 = new JLabel(new ImageIcon("src/cartes/7cha.png"));
                                    FrameCarte2.setBounds(450, 200, 615, 430);
                                    break;
                                case 8 : 
                                    carteLabel2 = new JLabel(new ImageIcon("src/cartes/8cha.png"));
                                    FrameCarte2.setBounds(450, 200, 615, 430);
                                    break;
                                case 9 : 
                                    carteLabel2 = new JLabel(new ImageIcon("src/cartes/9cha.png"));
                                    FrameCarte2.setBounds(450, 200, 615, 430);
                                    break;
                                case 10 : 
                                    carteLabel2 = new JLabel(new ImageIcon("src/cartes/10cha.png"));
                                    FrameCarte2.setBounds(450, 200, 615, 430);
                                    break;
                                case 11 : 
                                    carteLabel2 = new JLabel(new ImageIcon("src/cartes/11cha.png"));
                                    FrameCarte2.setBounds(450, 200, 615, 430);
                                    break;
                                case 12 : 
                                    carteLabel2 = new JLabel(new ImageIcon("src/cartes/12cha.png"));
                                    FrameCarte2.setBounds(450, 200, 615, 430);
                                    break;
                                case 13 : 
                                    carteLabel2 = new JLabel(new ImageIcon("src/cartes/13cha.png"));
                                    FrameCarte2.setBounds(450, 200, 615, 430);
                                    break;   
                                case 14 : 
                                    carteLabel2 = new JLabel(new ImageIcon("src/cartes/14cha.png"));
                                    FrameCarte2.setBounds(450, 200, 615, 430);
                                    break;
                                case 15 :
                                    carteLabel2 = new JLabel(new ImageIcon("src/cartes/15cha.png"));
                                    FrameCarte2.setBounds(450, 10, 600, 700);
                                    break;
                                default :
                                    carteLabel2 = new JLabel("PAS DE CARTE");
                            }
                            FrameCarte2.add(carteLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0,0));
                            carteLabel2.setVisible(true);
                            FrameCarte2.repaint();
                            if (laCarte.idCarte != 15) {
                                faireActionCarte(laCarte);
                            }
                            else {
                                darties = true;
                                    int nbM = joueurCourant.compterMemesSallesJoueurInt("marron");
                                    int nbBC = joueurCourant.compterMemesSallesJoueurInt("bleu ciel");
                                    int nbVi = joueurCourant.compterMemesSallesJoueurInt("violet");
                                    int nbO = joueurCourant.compterMemesSallesJoueurInt("orange");
                                    int nbR = joueurCourant.compterMemesSallesJoueurInt("rouge");
                                    int nbJ = joueurCourant.compterMemesSallesJoueurInt("jaune");
                                    int nbVe = joueurCourant.compterMemesSallesJoueurInt("vert");
                                    int nbBF = joueurCourant.compterMemesSallesJoueurInt("bleu foncé");
                                    if (nbM==0 && nbBC==0 && nbVi==0 && nbO==0 && nbR==0 && nbJ==0 && nbVe==0 && nbBF==0) {
                                        darties = false;
                                        JLabel message = new JLabel("Vous n'avez aucune salle dans laquelle valider une matiere ou un module.");
                                        message.setFont(new Font("Monopoly", Font.PLAIN, 18));
                                        FrameCarte.add(message, new org.netbeans.lib.awtextra.AbsoluteConstraints(50,400));
                                    }
                                    else {
                                        JLabel consignes = new JLabel("<html>Veuillez choisir la couleur de la salle dans laquelle vous voulez valider<br>une matiere ou un module.<html/>");
                                        consignes.setFont(new Font("Monopoly", Font.PLAIN, 18));
                                        JLabel consignes2 = new JLabel ("Attention, vous devez obligatoirement valider une matiere ou un module.");
                                        consignes2.setFont(new Font("Monopoly", Font.BOLD, 18));
                                        consignes2.setForeground(Color.red);
                                        FrameCarte2.add(consignes, new org.netbeans.lib.awtextra.AbsoluteConstraints(10,400));
                                        FrameCarte2.add(consignes2, new org.netbeans.lib.awtextra.AbsoluteConstraints(25,450));
                                        
                                        panel_boutons.repaint();
                                    }
                                    if (nbM==0) {
                                        marronV.setBackground(Color.LIGHT_GRAY);
                                        marronV.setEnabled(false);
                                    }
                                    else {
                                        marronV.setBackground(new Color(148,74,37));
                                        marronV.setEnabled(true);
                                    }
                                    if (nbBC==0) {
                                        bleucielV.setBackground(Color.LIGHT_GRAY);
                                        bleucielV.setEnabled(false);
                                    }
                                    else {
                                        bleucielV.setBackground(new Color(189,227,246));
                                        bleucielV.setEnabled(true);
                                    }
                                    if (nbVi==0) {
                                        violetV.setBackground(Color.LIGHT_GRAY);
                                        violetV.setEnabled(false);
                                    }
                                    else {
                                        violetV.setBackground(new Color(120,39,134));
                                        violetV.setEnabled(true);
                                    }
                                    if (nbO==0) {
                                        orangeV.setBackground(Color.LIGHT_GRAY);
                                        orangeV.setEnabled(false);
                                    }
                                    else {
                                        orangeV.setBackground(new Color(242,148,0));
                                        orangeV.setEnabled(true);
                                    }
                                    if (nbR==0) {
                                        rougeV.setBackground(Color.LIGHT_GRAY);
                                        rougeV.setEnabled(false);
                                    }
                                    else {
                                        rougeV.setBackground(new Color(227,0,27));
                                        rougeV.setEnabled(true);
                                    }
                                    if (nbJ==0) {
                                        jauneV.setBackground(Color.LIGHT_GRAY);
                                        jauneV.setEnabled(false);
                                    }
                                    else {
                                        jauneV.setBackground(new Color(255,238,2));
                                        jauneV.setEnabled(true);
                                    }
                                    if (nbVe==0) {
                                        vertV.setBackground(Color.LIGHT_GRAY);
                                        vertV.setEnabled(false);
                                    }
                                    else {
                                        vertV.setBackground(new Color(38,163,69));
                                        vertV.setEnabled(true);
                                    }
                                    if (nbBF==0) {
                                        bleufoncéV.setBackground(Color.LIGHT_GRAY);
                                        bleufoncéV.setEnabled(false);
                                    }
                                    else {
                                        bleufoncéV.setBackground(new Color(28,55,126));
                                        bleufoncéV.setEnabled(true);
                                    }
                                    FrameCarte2.add(marronV, new org.netbeans.lib.awtextra.AbsoluteConstraints(100,500,30,30));
                                    FrameCarte2.add(bleucielV, new org.netbeans.lib.awtextra.AbsoluteConstraints(150,500,30,30));
                                    FrameCarte2.add(violetV, new org.netbeans.lib.awtextra.AbsoluteConstraints(200,500,30,30));
                                    FrameCarte2.add(orangeV, new org.netbeans.lib.awtextra.AbsoluteConstraints(250,500,30,30));
                                    FrameCarte2.add(rougeV, new org.netbeans.lib.awtextra.AbsoluteConstraints(300,500,30,30));
                                    FrameCarte2.add(jauneV, new org.netbeans.lib.awtextra.AbsoluteConstraints(350,500,30,30));
                                    FrameCarte2.add(vertV, new org.netbeans.lib.awtextra.AbsoluteConstraints(400,500,30,30));
                                    FrameCarte2.add(bleufoncéV, new org.netbeans.lib.awtextra.AbsoluteConstraints(450,500,30,30));
                                    validerJoueur = new JLabel("Joueur concerne : "+joueurCourant.nom);
                                    validerJoueur.setFont(new Font("Monopoly", Font.PLAIN, 10));
                                    
                                    FrameCarte2.add(validerJoueur, new org.netbeans.lib.awtextra.AbsoluteConstraints(10,600,-1,-1));
                                    FrameCarte2.repaint();
                            }
                            if(choixcom==1 && carte=="chance1" || carte=="chance2" || carte=="chance3" || carte=="chance4" || carte=="chance10" || carte=="chance13") {
                                Case caseoualler = null;
                                for(int i=0;i<plateau.plateaudejeu.length;i++) {
                                    if(joueurCourant.pion.caseassociee==plateau.plateaudejeu[i]) {
                                        caseoualler=plateau.plateaudejeu[i];
                                    }
                                }
                                if(caseoualler==plateau.plateaudejeu[2]) {
                                    caseoualler = plateau.plateaudejeu[3];
                                }
                                else if(caseoualler==plateau.plateaudejeu[17]) {
                                    caseoualler = plateau.plateaudejeu[18];
                                }
                                else if(caseoualler==plateau.plateaudejeu[33]) {
                                    caseoualler = plateau.plateaudejeu[34];
                                }
                                mini=true;
                                DeplacerPion(joueurCourant.pion,caseoualler,"Téléportation");
                            }
                            if(laCarte.idCarte!=1 && laCarte.idCarte!=2 && laCarte.idCarte!=3 && laCarte.idCarte!=4 && laCarte.idCarte!=10 && laCarte.idCarte!=13) {
                                if(joueurCourant.credits<0) {
                                    eliminationJoueur();
                                }
                                else {
                                    changerJoueur();
                                    compteurDouble=0;
                                }
                                while(joueurCourant.droitdejouer==false) {
                                    joueurCourant.droitdejouer=true;
                                    changerJoueur();
                                    compteurDouble=0;
                                }
                                if(joueurCourant.compteurTourPrison==3) {
                                    joueurCourant.compteurTourPrison=0;
                                    joueurCourant.prison=false;
                                    joueurCourant.credits=joueurCourant.credits-50;
                                    if(joueurCourant==tabJoueurs[0]) {
                                        tabJoueurs[0].pion.coordX=61;
                                        tabJoueurs[0].pion.coordY=2;
                                        tabJoueurs[0].pion.taille=24;
                                        plateauJeu.orientation1=plateauJeu.haut1;
                                        plateauJeu.repaint();
                                        credits1.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                    }
                                    else if(joueurCourant==tabJoueurs[1]) {
                                        tabJoueurs[1].pion.coordX=18;
                                        tabJoueurs[1].pion.coordY=2;
                                        tabJoueurs[1].pion.taille=24;
                                        plateauJeu.orientation2=plateauJeu.haut2;
                                        plateauJeu.repaint();
                                        credits2.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                    }
                                    else if(joueurCourant==tabJoueurs[2]) {
                                        tabJoueurs[2].pion.coordX=2;
                                        tabJoueurs[2].pion.coordY=20;
                                        tabJoueurs[2].pion.taille=24;
                                        plateauJeu.orientation3=plateauJeu.gauche3;
                                        plateauJeu.repaint();
                                        credits3.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                    }
                                    else if(joueurCourant==tabJoueurs[3]) {
                                        tabJoueurs[3].pion.coordX=2;
                                        tabJoueurs[3].pion.coordY=63;
                                        tabJoueurs[3].pion.taille=24;
                                        plateauJeu.orientation4=plateauJeu.gauche4;
                                        plateauJeu.repaint();
                                        credits4.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                    }
                                    zone_texte_infos.setText(zone_texte_infos.getText() + "\n" + joueurCourant.nom + " vous venez de rater vos 3 essais aux rattrapages, vous payez donc 50 crédits et réintégrez le parcours scolaire!");
                                }
                                if(joueurCourant==tabJoueurs[0] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                    ValiderMatiere1.setVisible(true);
                                }
                                else if(joueurCourant==tabJoueurs[1] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                    ValiderMatiere2.setVisible(true);
                                }
                                else if(joueurCourant==tabJoueurs[2] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                    ValiderMatiere3.setVisible(true);
                                }
                                else if(joueurCourant==tabJoueurs[3] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                    ValiderMatiere4.setVisible(true);
                                }
                                if(joueurCourant==tabJoueurs[0] && joueurCourant.prison==true) {
                                    joueurCourant.compteurTourPrison++;
                                    if(joueurCourant.nbCartes!=0) {
                                        Libération1.setVisible(true);
                                    }
                                    PayerPrison1.setVisible(true);
                                }
                                else if(joueurCourant==tabJoueurs[1] && joueurCourant.prison==true) {
                                    joueurCourant.compteurTourPrison++;
                                    if(joueurCourant.nbCartes!=0) {
                                        Libération2.setVisible(true);
                                    }
                                    PayerPrison2.setVisible(true);
                                }
                                else if(joueurCourant==tabJoueurs[2] && joueurCourant.prison==true) {
                                    joueurCourant.compteurTourPrison++;
                                    if(joueurCourant.nbCartes!=0) {
                                        Libération3.setVisible(true);
                                    }
                                    PayerPrison3.setVisible(true);
                                }
                                else if(joueurCourant==tabJoueurs[3] && joueurCourant.prison==true) {
                                    joueurCourant.compteurTourPrison++;
                                    if(joueurCourant.nbCartes!=0) {
                                        Libération4.setVisible(true);
                                    }
                                    PayerPrison4.setVisible(true);
                                }
                                if(elim!=true) {
                                    Lancer_des.setEnabled(true);
                                }
                                choixcom=2;
                                carte="";
                                cartespéciale="";
                                if(joueurCourant.prison!=true && joueurCourant.nom!=null && compteurDouble==0) {
                                    zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon tirez les dés!");
                                }
                                else if(joueurCourant.prison!=true && joueurCourant.nom!=null) {
                                    zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, vous pouvez tirez les dés!");
                                }
                                else {
                                    if(joueurCourant.nom!=null) {
                                        zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon essayer de quitter les rattrapages (vous pouvez payer 50 crédits, jouer une carte libération de rattrapages si vous en possédez une ou sinon tenter de faire un double)!");
                                    }
                                }
                                plateauJeu.repaint();
                            }
                        }
                    }
                });
            }
            return true;
        }
        else if (caseDuJoueur == plateau.plateaudejeu[7] || caseDuJoueur == plateau.plateaudejeu[22] || caseDuJoueur == plateau.plateaudejeu[36]) { //s'il est sur une case chance
            Carte carteTiree = tirerCarte(paquetChance);
                JFrame FrameCarte = new JFrame();
                FrameCarte.setVisible(true);
                FrameCarte.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
                JLabel carteLabel;
                switch (carteTiree.idCarte) {
                    case 0 : 
                        carteLabel = new JLabel(new ImageIcon("src/cartes/0cha.png"));
                        FrameCarte.setBounds(450, 200, 615, 430);
                        break;
                    case 1 : 
                        carteLabel = new JLabel(new ImageIcon("src/cartes/1cha.png"));
                        FrameCarte.setBounds(450, 200, 615, 430);
                        break;
                    case 2 : 
                        carteLabel = new JLabel(new ImageIcon("src/cartes/2cha.png"));
                        FrameCarte.setBounds(450, 200, 615, 430);
                        break;
                    case 3 : 
                        carteLabel = new JLabel(new ImageIcon("src/cartes/3cha.png"));
                        FrameCarte.setBounds(450, 200, 615, 430);
                        break;
                    case 4 : 
                        carteLabel = new JLabel(new ImageIcon("src/cartes/4cha.png"));
                        FrameCarte.setBounds(450, 200, 615, 430);
                        break;
                    case 5 : 
                        carteLabel = new JLabel(new ImageIcon("src/cartes/5cha.png"));
                        FrameCarte.setBounds(450, 200, 615, 430);
                        break;
                    case 6 : 
                        carteLabel = new JLabel(new ImageIcon("src/cartes/6cha.png"));
                        FrameCarte.setBounds(450, 200, 615, 430);
                        break;
                    case 7 : 
                        carteLabel = new JLabel(new ImageIcon("src/cartes/7cha.png"));
                        FrameCarte.setBounds(450, 200, 615, 430);
                        break;
                    case 8 : 
                        carteLabel = new JLabel(new ImageIcon("src/cartes/8cha.png"));
                        FrameCarte.setBounds(450, 200, 615, 430);
                        break;
                    case 9 : 
                        carteLabel = new JLabel(new ImageIcon("src/cartes/9cha.png"));
                        FrameCarte.setBounds(450, 200, 615, 430);
                        break;
                    case 10 : 
                        carteLabel = new JLabel(new ImageIcon("src/cartes/10cha.png"));
                        FrameCarte.setBounds(450, 200, 615, 430);
                        break;
                    case 11 : 
                        carteLabel = new JLabel(new ImageIcon("src/cartes/11cha.png"));
                        FrameCarte.setBounds(450, 200, 615, 430);
                        break;
                    case 12 : 
                        carteLabel = new JLabel(new ImageIcon("src/cartes/12cha.png"));
                        FrameCarte.setBounds(450, 200, 615, 430);
                        break;
                    case 13 : 
                        carteLabel = new JLabel(new ImageIcon("src/cartes/13cha.png"));
                        FrameCarte.setBounds(450, 200, 615, 430);
                        break;   
                    case 14 : 
                        carteLabel = new JLabel(new ImageIcon("src/cartes/14cha.png"));
                        FrameCarte.setBounds(450, 200, 615, 430);
                        break;
                    case 15 :
                        carteLabel = new JLabel(new ImageIcon("src/cartes/15cha.png"));
                        FrameCarte.setBounds(450, 10, 600, 700);
                        darties = true;
                                    int nbM = joueurCourant.compterMemesSallesJoueurInt("marron");
                                    int nbBC = joueurCourant.compterMemesSallesJoueurInt("bleu ciel");
                                    int nbVi = joueurCourant.compterMemesSallesJoueurInt("violet");
                                    int nbO = joueurCourant.compterMemesSallesJoueurInt("orange");
                                    int nbR = joueurCourant.compterMemesSallesJoueurInt("rouge");
                                    int nbJ = joueurCourant.compterMemesSallesJoueurInt("jaune");
                                    int nbVe = joueurCourant.compterMemesSallesJoueurInt("vert");
                                    int nbBF = joueurCourant.compterMemesSallesJoueurInt("bleu foncé");
                                    if (nbM==0 && nbBC==0 && nbVi==0 && nbO==0 && nbR==0 && nbJ==0 && nbVe==0 && nbBF==0) {
                                        darties = false;
                                        JLabel message = new JLabel("Vous n'avez aucune salle dans laquelle valider une matiere ou un module.");
                                        message.setFont(new Font("Monopoly", Font.PLAIN, 18));
                                        FrameCarte.add(message, new org.netbeans.lib.awtextra.AbsoluteConstraints(50,400));
                                    }
                                    else {
                                        JLabel consignes = new JLabel("<html>Veuillez choisir la couleur de la salle dans laquelle vous voulez valider<br>une matiere ou un module.<html/>");
                                        consignes.setFont(new Font("Monopoly", Font.PLAIN, 18));
                                        JLabel consignes2 = new JLabel ("Attention, vous devez obligatoirement valider une matiere ou un module.");
                                        consignes2.setFont(new Font("Monopoly", Font.BOLD, 18));
                                        consignes2.setForeground(Color.red);
                                        FrameCarte.add(consignes, new org.netbeans.lib.awtextra.AbsoluteConstraints(10,400));
                                        FrameCarte.add(consignes2, new org.netbeans.lib.awtextra.AbsoluteConstraints(25,450));
                                        
                                        panel_boutons.repaint();
                                    }
                                    if (nbM==0) {
                                        marronV.setBackground(Color.LIGHT_GRAY);
                                        marronV.setEnabled(false);
                                    }
                                    else {
                                        marronV.setBackground(new Color(148,74,37));
                                        marronV.setEnabled(true);
                                    }
                                    if (nbBC==0) {
                                        bleucielV.setBackground(Color.LIGHT_GRAY);
                                        bleucielV.setEnabled(false);
                                    }
                                    else {
                                        bleucielV.setBackground(new Color(189,227,246));
                                        bleucielV.setEnabled(true);
                                    }
                                    if (nbVi==0) {
                                        violetV.setBackground(Color.LIGHT_GRAY);
                                        violetV.setEnabled(false);
                                    }
                                    else {
                                        violetV.setBackground(new Color(120,39,134));
                                        violetV.setEnabled(true);
                                    }
                                    if (nbO==0) {
                                        orangeV.setBackground(Color.LIGHT_GRAY);
                                        orangeV.setEnabled(false);
                                    }
                                    else {
                                        orangeV.setBackground(new Color(242,148,0));
                                        orangeV.setEnabled(true);
                                    }
                                    if (nbR==0) {
                                        rougeV.setBackground(Color.LIGHT_GRAY);
                                        rougeV.setEnabled(false);
                                    }
                                    else {
                                        rougeV.setBackground(new Color(227,0,27));
                                        rougeV.setEnabled(true);
                                    }
                                    if (nbJ==0) {
                                        jauneV.setBackground(Color.LIGHT_GRAY);
                                        jauneV.setEnabled(false);
                                    }
                                    else {
                                        jauneV.setBackground(new Color(255,238,2));
                                        jauneV.setEnabled(true);
                                    }
                                    if (nbVe==0) {
                                        vertV.setBackground(Color.LIGHT_GRAY);
                                        vertV.setEnabled(false);
                                    }
                                    else {
                                        vertV.setBackground(new Color(38,163,69));
                                        vertV.setEnabled(true);
                                    }
                                    if (nbBF==0) {
                                        bleufoncéV.setBackground(Color.LIGHT_GRAY);
                                        bleufoncéV.setEnabled(false);
                                    }
                                    else {
                                        bleufoncéV.setBackground(new Color(28,55,126));
                                        bleufoncéV.setEnabled(true);
                                    }
                                    FrameCarte.add(marronV, new org.netbeans.lib.awtextra.AbsoluteConstraints(100,500,30,30));
                                    FrameCarte.add(bleucielV, new org.netbeans.lib.awtextra.AbsoluteConstraints(150,500,30,30));
                                    FrameCarte.add(violetV, new org.netbeans.lib.awtextra.AbsoluteConstraints(200,500,30,30));
                                    FrameCarte.add(orangeV, new org.netbeans.lib.awtextra.AbsoluteConstraints(250,500,30,30));
                                    FrameCarte.add(rougeV, new org.netbeans.lib.awtextra.AbsoluteConstraints(300,500,30,30));
                                    FrameCarte.add(jauneV, new org.netbeans.lib.awtextra.AbsoluteConstraints(350,500,30,30));
                                    FrameCarte.add(vertV, new org.netbeans.lib.awtextra.AbsoluteConstraints(400,500,30,30));
                                    FrameCarte.add(bleufoncéV, new org.netbeans.lib.awtextra.AbsoluteConstraints(450,500,30,30));
                                    validerJoueur = new JLabel("Joueur concerne : "+joueurCourant.nom);
                                    validerJoueur.setFont(new Font("Monopoly", Font.PLAIN, 10));
                                    
                                    FrameCarte.add(validerJoueur, new org.netbeans.lib.awtextra.AbsoluteConstraints(10,600,-1,-1));
                                    FrameCarte.repaint();
                        break;
                    default :
                        carteLabel = new JLabel("PAS DE CARTE");
                }
                FrameCarte.add(carteLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0,0));
                carteLabel.setVisible(true);
                FrameCarte.repaint();
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
            if(joueurCourant==tabJoueurs[0]) {
                credits1.setText("Crédits : " + joueurCourant.credits + " ECTS");
            }
            else if(joueurCourant==tabJoueurs[1]) {
                credits2.setText("Crédits : " + joueurCourant.credits + " ECTS");
            }
            else if(joueurCourant==tabJoueurs[2]) {
                credits3.setText("Crédits : " + joueurCourant.credits + " ECTS");
            }
            else if(joueurCourant==tabJoueurs[3]) {
                credits4.setText("Crédits : " + joueurCourant.credits + " ECTS");
            }
            argentParcGratuit = argentParcGratuit + 200;
            LabelParcGratuit.setText("<html>Credits : <br/>"+argentParcGratuit+" ECTS");
            zone_texte_infos.setText(joueurCourant.nom + ", vous avez triché!!! Vous payez 200 ECTS et repassez le CC en tête à tête avec François Stephan!");
            return true;
        }
        else if (caseDuJoueur == plateau.plateaudejeu[38]) { //deuxième taxe
            joueurCourant.credits = joueurCourant.credits-100;
            if(joueurCourant==tabJoueurs[0]) {
                credits1.setText("Crédits : " + joueurCourant.credits + " ECTS");
            }
            else if(joueurCourant==tabJoueurs[1]) {
                credits2.setText("Crédits : " + joueurCourant.credits + " ECTS");
            }
            else if(joueurCourant==tabJoueurs[2]) {
                credits3.setText("Crédits : " + joueurCourant.credits + " ECTS");
            }
            else if(joueurCourant==tabJoueurs[3]) {
                credits4.setText("Crédits : " + joueurCourant.credits + " ECTS");
            }
            argentParcGratuit = argentParcGratuit + 100;
            LabelParcGratuit.setText("<html>Credits : <br/>"+argentParcGratuit+" ECTS");
            zone_texte_infos.setText(joueurCourant.nom + ", vous cumulez trop d'absences ! Payez 100 ECTS et vennez en cours sans quoi une année de plus à suivre les CM's de Barandon vous attends!!!");
            return true;
        }
        else if (caseDuJoueur == plateau.plateaudejeu[20]) { //parc gratuit
            joueurCourant.credits= joueurCourant.credits + argentParcGratuit;
            if(joueurCourant==tabJoueurs[0]) {
                credits1.setText("Crédits : " + joueurCourant.credits + " ECTS");
            }
            else if(joueurCourant==tabJoueurs[1]) {
                credits2.setText("Crédits : " + joueurCourant.credits + " ECTS");
            }
            else if(joueurCourant==tabJoueurs[2]) {
                credits3.setText("Crédits : " + joueurCourant.credits + " ECTS");
            }
            else if(joueurCourant==tabJoueurs[3]) {
                credits4.setText("Crédits : " + joueurCourant.credits + " ECTS");
            }
            zone_texte_infos.setText(joueurCourant.nom + ", vous majorez le partiel de maths abs à 55%!!! Vous recevez " + argentParcGratuit + " ECTS et les félicitations de la direction!");
            argentParcGratuit = 0;
            LabelParcGratuit.setText("<html>Credits : <br/>"+argentParcGratuit+" ECTS");
            return true;
        }
        else if (caseDuJoueur == plateau.plateaudejeu[30]) { //prison
            joueurCourant.prison = true;
            carte="Jousset";
            zone_texte_infos.setText(zone_texte_infos.getText() + "\n" + joueurCourant.nom + ", vos notes sont trop faibles!\nAllez aux rattrapages et validez-les pour espérer passer l'année!");
            if(joueurCourant==tabJoueurs[0]) {
                Thread thread1 = new Thread(){
                    public void run(){
                        try {
                            Thread.sleep(1000);
                            for(int i=0;i<2;i++) {
                                joueurCourant.pion.taille-=5;
                                plateauJeu.orientation1=plateauJeu.haut1;
                                plateauJeu.repaint();
                                Thread.sleep(100);
                                joueurCourant.pion.taille-=5;
                                plateauJeu.orientation1=plateauJeu.droite1;
                                plateauJeu.repaint();
                                Thread.sleep(100);
                                joueurCourant.pion.taille-=5;
                                plateauJeu.orientation1=plateauJeu.bas1;
                                plateauJeu.repaint();
                                Thread.sleep(100);
                                joueurCourant.pion.taille-=5;
                                plateauJeu.orientation1=plateauJeu.gauche1;
                                plateauJeu.repaint();
                                Thread.sleep(100);
                            }
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Monopoly_EPF.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        joueurCourant.pion.coordX=31;
                        joueurCourant.pion.coordY=31;
                        joueurCourant.pion.taille=35;
                        plateauJeu.orientation1=plateauJeu.gauche1;
                        deplacerPion(joueurCourant.pion,20,plateau.plateaudejeu[30]);
                        if(joueurCourant.credits<0) {
                            eliminationJoueur();
                        }
                        else {
                            changerJoueur();
                            compteurDouble=0;
                        }
                        while(joueurCourant.droitdejouer==false) {
                            joueurCourant.droitdejouer=true;
                            changerJoueur();
                            compteurDouble=0;
                        }
                        if(joueurCourant.compteurTourPrison==3) {
                            joueurCourant.compteurTourPrison=0;
                            joueurCourant.prison=false;
                            joueurCourant.credits=joueurCourant.credits-50;
                            if(joueurCourant==tabJoueurs[0]) {
                                tabJoueurs[0].pion.coordX=61;
                                tabJoueurs[0].pion.coordY=2;
                                tabJoueurs[0].pion.taille=24;
                                plateauJeu.orientation1=plateauJeu.haut1;
                                plateauJeu.repaint();
                                credits1.setText("Crédits : " + joueurCourant.credits + " ECTS");
                            }
                            else if(joueurCourant==tabJoueurs[1]) {
                                tabJoueurs[1].pion.coordX=18;
                                tabJoueurs[1].pion.coordY=2;
                                tabJoueurs[1].pion.taille=24;
                                plateauJeu.orientation2=plateauJeu.haut2;
                                plateauJeu.repaint();
                                credits2.setText("Crédits : " + joueurCourant.credits + " ECTS");
                            }
                            else if(joueurCourant==tabJoueurs[2]) {
                                tabJoueurs[2].pion.coordX=2;
                                tabJoueurs[2].pion.coordY=20;
                                tabJoueurs[2].pion.taille=24;
                                plateauJeu.orientation3=plateauJeu.gauche3;
                                plateauJeu.repaint();
                                credits3.setText("Crédits : " + joueurCourant.credits + " ECTS");
                            }
                            else if(joueurCourant==tabJoueurs[3]) {
                                tabJoueurs[3].pion.coordX=2;
                                tabJoueurs[3].pion.coordY=63;
                                tabJoueurs[3].pion.taille=24;
                                plateauJeu.orientation4=plateauJeu.gauche4;
                                plateauJeu.repaint();
                                credits4.setText("Crédits : " + joueurCourant.credits + " ECTS");
                            }
                            zone_texte_infos.setText(zone_texte_infos.getText() + "\n" + joueurCourant.nom + " vous venez de rater vos 3 essais aux rattrapages, vous payez donc 50 crédits et réintégrez le parcours scolaire!");
                        }
                        if(joueurCourant==tabJoueurs[0] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                            ValiderMatiere1.setVisible(true);
                        }
                        else if(joueurCourant==tabJoueurs[1] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                            ValiderMatiere2.setVisible(true);
                        }
                        else if(joueurCourant==tabJoueurs[2] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                            ValiderMatiere3.setVisible(true);
                        }
                        else if(joueurCourant==tabJoueurs[3] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                            ValiderMatiere4.setVisible(true);
                        }
                        if(joueurCourant==tabJoueurs[0] && joueurCourant.prison==true) {
                            joueurCourant.compteurTourPrison++;
                            if(joueurCourant.nbCartes!=0) {
                                Libération1.setVisible(true);
                            }
                            PayerPrison1.setVisible(true);
                        }
                        else if(joueurCourant==tabJoueurs[1] && joueurCourant.prison==true) {
                            joueurCourant.compteurTourPrison++;
                            if(joueurCourant.nbCartes!=0) {
                                Libération2.setVisible(true);
                            }
                            PayerPrison2.setVisible(true);
                        }
                        else if(joueurCourant==tabJoueurs[2] && joueurCourant.prison==true) {
                            joueurCourant.compteurTourPrison++;
                            if(joueurCourant.nbCartes!=0) {
                                Libération3.setVisible(true);
                            }
                            PayerPrison3.setVisible(true);
                        }
                        else if(joueurCourant==tabJoueurs[3] && joueurCourant.prison==true) {
                            joueurCourant.compteurTourPrison++;
                            if(joueurCourant.nbCartes!=0) {
                                Libération4.setVisible(true);
                            }
                            PayerPrison4.setVisible(true);
                        }
                        if(elim!=true) {
                            Lancer_des.setEnabled(true);
                        }
                        if(joueurCourant.prison!=true && joueurCourant.nom!=null && compteurDouble==0) {
                            zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon tirez les dés!");
                        }
                        else if(joueurCourant.prison!=true && joueurCourant.nom!=null) {
                            zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, vous pouvez tirez les dés!");
                        }
                        else {
                            if(joueurCourant.nom!=null) {
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon essayer de quitter les rattrapages (vous pouvez payer 50 crédits, jouer une carte libération de rattrapages si vous en possédez une ou sinon tenter de faire un double)!");
                            }
                        }
                        plateauJeu.repaint();
                    }
                };
                thread1.start();
            }
            else if(joueurCourant==tabJoueurs[1]) {
                Thread thread2 = new Thread(){
                    public void run(){
                        try {
                            Thread.sleep(1000);
                            for(int i=0;i<2;i++) {
                                joueurCourant.pion.taille-=5;
                                plateauJeu.orientation2=plateauJeu.haut2;
                                plateauJeu.repaint();
                                Thread.sleep(100);
                                joueurCourant.pion.taille-=5;
                                plateauJeu.orientation2=plateauJeu.droite2;
                                plateauJeu.repaint();
                                Thread.sleep(100);
                                joueurCourant.pion.taille-=5;
                                plateauJeu.orientation2=plateauJeu.bas2;
                                plateauJeu.repaint();
                                Thread.sleep(100);
                                joueurCourant.pion.taille-=5;
                                plateauJeu.orientation2=plateauJeu.gauche2;
                                plateauJeu.repaint();
                                Thread.sleep(100);
                            }
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Monopoly_EPF.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        joueurCourant.pion.coordX=31;
                        joueurCourant.pion.coordY=68;
                        joueurCourant.pion.taille=35;
                        plateauJeu.orientation2=plateauJeu.gauche2;
                        deplacerPion(joueurCourant.pion,20,plateau.plateaudejeu[30]);
                        if(joueurCourant.credits<0) {
                            eliminationJoueur();
                        }
                        else {
                            changerJoueur();
                            compteurDouble=0;
                        }
                        while(joueurCourant.droitdejouer==false) {
                            joueurCourant.droitdejouer=true;
                            changerJoueur();
                            compteurDouble=0;
                        }
                        if(joueurCourant.compteurTourPrison==3) {
                            joueurCourant.compteurTourPrison=0;
                            joueurCourant.prison=false;
                            joueurCourant.credits=joueurCourant.credits-50;
                            if(joueurCourant==tabJoueurs[0]) {
                                tabJoueurs[0].pion.coordX=61;
                                tabJoueurs[0].pion.coordY=2;
                                tabJoueurs[0].pion.taille=24;
                                plateauJeu.orientation1=plateauJeu.haut1;
                                plateauJeu.repaint();
                                credits1.setText("Crédits : " + joueurCourant.credits + " ECTS");
                            }
                            else if(joueurCourant==tabJoueurs[1]) {
                                tabJoueurs[1].pion.coordX=18;
                                tabJoueurs[1].pion.coordY=2;
                                tabJoueurs[1].pion.taille=24;
                                plateauJeu.orientation2=plateauJeu.haut2;
                                plateauJeu.repaint();
                                credits2.setText("Crédits : " + joueurCourant.credits + " ECTS");
                            }
                            else if(joueurCourant==tabJoueurs[2]) {
                                tabJoueurs[2].pion.coordX=2;
                                tabJoueurs[2].pion.coordY=20;
                                tabJoueurs[2].pion.taille=24;
                                plateauJeu.orientation3=plateauJeu.gauche3;
                                plateauJeu.repaint();
                                credits3.setText("Crédits : " + joueurCourant.credits + " ECTS");
                            }
                            else if(joueurCourant==tabJoueurs[3]) {
                                tabJoueurs[3].pion.coordX=2;
                                tabJoueurs[3].pion.coordY=63;
                                tabJoueurs[3].pion.taille=24;
                                plateauJeu.orientation4=plateauJeu.gauche4;
                                plateauJeu.repaint();
                                credits4.setText("Crédits : " + joueurCourant.credits + " ECTS");
                            }
                            zone_texte_infos.setText(zone_texte_infos.getText() + "\n" + joueurCourant.nom + " vous venez de rater vos 3 essais aux rattrapages, vous payez donc 50 crédits et réintégrez le parcours scolaire!");
                        }
                        if(joueurCourant==tabJoueurs[0] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                            ValiderMatiere1.setVisible(true);
                        }
                        else if(joueurCourant==tabJoueurs[1] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                            ValiderMatiere2.setVisible(true);
                        }
                        else if(joueurCourant==tabJoueurs[2] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                            ValiderMatiere3.setVisible(true);
                        }
                        else if(joueurCourant==tabJoueurs[3] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                            ValiderMatiere4.setVisible(true);
                        }
                        if(joueurCourant==tabJoueurs[0] && joueurCourant.prison==true) {
                            joueurCourant.compteurTourPrison++;
                            if(joueurCourant.nbCartes!=0) {
                                Libération1.setVisible(true);
                            }
                            PayerPrison1.setVisible(true);
                        }
                        else if(joueurCourant==tabJoueurs[1] && joueurCourant.prison==true) {
                            joueurCourant.compteurTourPrison++;
                            if(joueurCourant.nbCartes!=0) {
                                Libération2.setVisible(true);
                            }
                            PayerPrison2.setVisible(true);
                        }
                        else if(joueurCourant==tabJoueurs[2] && joueurCourant.prison==true) {
                            joueurCourant.compteurTourPrison++;
                            if(joueurCourant.nbCartes!=0) {
                                Libération3.setVisible(true);
                            }
                            PayerPrison3.setVisible(true);
                        }
                        else if(joueurCourant==tabJoueurs[3] && joueurCourant.prison==true) {
                            joueurCourant.compteurTourPrison++;
                            if(joueurCourant.nbCartes!=0) {
                                Libération4.setVisible(true);
                            }
                            PayerPrison4.setVisible(true);
                        }
                        if(elim!=true) {
                            Lancer_des.setEnabled(true);
                        }
                        if(joueurCourant.prison!=true && joueurCourant.nom!=null && compteurDouble==0) {
                            zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon tirez les dés!");
                        }
                        else if(joueurCourant.prison!=true && joueurCourant.nom!=null) {
                            zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, vous pouvez tirez les dés!");
                        }
                        else {
                            if(joueurCourant.nom!=null) {
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon essayer de quitter les rattrapages (vous pouvez payer 50 crédits, jouer une carte libération de rattrapages si vous en possédez une ou sinon tenter de faire un double)!");
                            }
                        }
                        plateauJeu.repaint();
                    }
                };
                thread2.start();
            }
            else if(joueurCourant==tabJoueurs[2]) {
                Thread thread3 = new Thread(){
                    public void run(){
                        try {
                            Thread.sleep(1000);
                            for(int i=0;i<2;i++) {
                                joueurCourant.pion.taille-=5;
                                plateauJeu.orientation3=plateauJeu.haut3;
                                plateauJeu.repaint();
                                Thread.sleep(100);
                                joueurCourant.pion.taille-=5;
                                plateauJeu.orientation3=plateauJeu.droite3;
                                plateauJeu.repaint();
                                Thread.sleep(100);
                                joueurCourant.pion.taille-=5;
                                plateauJeu.orientation3=plateauJeu.bas3;
                                plateauJeu.repaint();
                                Thread.sleep(100);
                                joueurCourant.pion.taille-=5;
                                plateauJeu.orientation3=plateauJeu.gauche3;
                                plateauJeu.repaint();
                                Thread.sleep(100);
                            }
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Monopoly_EPF.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        joueurCourant.pion.coordX=68;
                        joueurCourant.pion.coordY=31;
                        joueurCourant.pion.taille=35;
                        plateauJeu.orientation3=plateauJeu.gauche3;
                        deplacerPion(joueurCourant.pion,20,plateau.plateaudejeu[30]);
                        if(joueurCourant.credits<0) {
                            eliminationJoueur();
                        }
                        else {
                            changerJoueur();
                            compteurDouble=0;
                        }
                        while(joueurCourant.droitdejouer==false) {
                            joueurCourant.droitdejouer=true;
                            changerJoueur();
                            compteurDouble=0;
                        }
                        if(joueurCourant.compteurTourPrison==3) {
                            joueurCourant.compteurTourPrison=0;
                            joueurCourant.prison=false;
                            joueurCourant.credits=joueurCourant.credits-50;
                            if(joueurCourant==tabJoueurs[0]) {
                                tabJoueurs[0].pion.coordX=61;
                                tabJoueurs[0].pion.coordY=2;
                                tabJoueurs[0].pion.taille=24;
                                plateauJeu.orientation1=plateauJeu.haut1;
                                plateauJeu.repaint();
                                credits1.setText("Crédits : " + joueurCourant.credits + " ECTS");
                            }
                            else if(joueurCourant==tabJoueurs[1]) {
                                tabJoueurs[1].pion.coordX=18;
                                tabJoueurs[1].pion.coordY=2;
                                tabJoueurs[1].pion.taille=24;
                                plateauJeu.orientation2=plateauJeu.haut2;
                                plateauJeu.repaint();
                                credits2.setText("Crédits : " + joueurCourant.credits + " ECTS");
                            }
                            else if(joueurCourant==tabJoueurs[2]) {
                                tabJoueurs[2].pion.coordX=2;
                                tabJoueurs[2].pion.coordY=20;
                                tabJoueurs[2].pion.taille=24;
                                plateauJeu.orientation3=plateauJeu.gauche3;
                                plateauJeu.repaint();
                                credits3.setText("Crédits : " + joueurCourant.credits + " ECTS");
                            }
                            else if(joueurCourant==tabJoueurs[3]) {
                                tabJoueurs[3].pion.coordX=2;
                                tabJoueurs[3].pion.coordY=63;
                                tabJoueurs[3].pion.taille=24;
                                plateauJeu.orientation4=plateauJeu.gauche4;
                                plateauJeu.repaint();
                                credits4.setText("Crédits : " + joueurCourant.credits + " ECTS");
                            }
                            zone_texte_infos.setText(zone_texte_infos.getText() + "\n" + joueurCourant.nom + " vous venez de rater vos 3 essais aux rattrapages, vous payez donc 50 crédits et réintégrez le parcours scolaire!");
                        }
                        if(joueurCourant==tabJoueurs[0] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                            ValiderMatiere1.setVisible(true);
                        }
                        else if(joueurCourant==tabJoueurs[1] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                            ValiderMatiere2.setVisible(true);
                        }
                        else if(joueurCourant==tabJoueurs[2] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                            ValiderMatiere3.setVisible(true);
                        }
                        else if(joueurCourant==tabJoueurs[3] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                            ValiderMatiere4.setVisible(true);
                        }
                        if(joueurCourant==tabJoueurs[0] && joueurCourant.prison==true) {
                            joueurCourant.compteurTourPrison++;
                            if(joueurCourant.nbCartes!=0) {
                                Libération1.setVisible(true);
                            }
                            PayerPrison1.setVisible(true);
                        }
                        else if(joueurCourant==tabJoueurs[1] && joueurCourant.prison==true) {
                            joueurCourant.compteurTourPrison++;
                            if(joueurCourant.nbCartes!=0) {
                                Libération2.setVisible(true);
                            }
                            PayerPrison2.setVisible(true);
                        }
                        else if(joueurCourant==tabJoueurs[2] && joueurCourant.prison==true) {
                            joueurCourant.compteurTourPrison++;
                            if(joueurCourant.nbCartes!=0) {
                                Libération3.setVisible(true);
                            }
                            PayerPrison3.setVisible(true);
                        }
                        else if(joueurCourant==tabJoueurs[3] && joueurCourant.prison==true) {
                            joueurCourant.compteurTourPrison++;
                            if(joueurCourant.nbCartes!=0) {
                                Libération4.setVisible(true);
                            }
                            PayerPrison4.setVisible(true);
                        }
                        if(elim!=true) {
                            Lancer_des.setEnabled(true);
                        }
                        if(joueurCourant.prison!=true && joueurCourant.nom!=null && compteurDouble==0) {
                            zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon tirez les dés!");
                        }
                        else if(joueurCourant.prison!=true && joueurCourant.nom!=null) {
                            zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, vous pouvez tirez les dés!");
                        }
                        else {
                            if(joueurCourant.nom!=null) {
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon essayer de quitter les rattrapages (vous pouvez payer 50 crédits, jouer une carte libération de rattrapages si vous en possédez une ou sinon tenter de faire un double)!");
                            }
                        }
                        plateauJeu.repaint();
                    }
                };
                thread3.start();
            }
            else if(joueurCourant==tabJoueurs[3]) {
                Thread thread4 = new Thread(){
                    public void run(){
                        try {
                            Thread.sleep(1000);
                            for(int i=0;i<2;i++) {
                                joueurCourant.pion.taille-=5;
                                plateauJeu.orientation4=plateauJeu.haut4;
                                plateauJeu.repaint();
                                Thread.sleep(100);
                                joueurCourant.pion.taille-=5;
                                plateauJeu.orientation4=plateauJeu.droite4;
                                plateauJeu.repaint();
                                Thread.sleep(100);
                                joueurCourant.pion.taille-=5;
                                plateauJeu.orientation4=plateauJeu.bas4;
                                plateauJeu.repaint();
                                Thread.sleep(100);
                                joueurCourant.pion.taille-=5;
                                plateauJeu.orientation4=plateauJeu.gauche4;
                                plateauJeu.repaint();
                                Thread.sleep(100);
                            }
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Monopoly_EPF.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        joueurCourant.pion.coordX=68;
                        joueurCourant.pion.coordY=68;
                        joueurCourant.pion.taille=35;
                        plateauJeu.orientation4=plateauJeu.gauche4;
                        deplacerPion(joueurCourant.pion,20,plateau.plateaudejeu[30]);
                        if(joueurCourant.credits<0) {
                            eliminationJoueur();
                        }
                        else {
                            changerJoueur();
                            compteurDouble=0;
                        }
                        while(joueurCourant.droitdejouer==false) {
                            joueurCourant.droitdejouer=true;
                            changerJoueur();
                            compteurDouble=0;
                        }
                        if(joueurCourant.compteurTourPrison==3) {
                            joueurCourant.compteurTourPrison=0;
                            joueurCourant.prison=false;
                            joueurCourant.credits=joueurCourant.credits-50;
                            if(joueurCourant==tabJoueurs[0]) {
                                tabJoueurs[0].pion.coordX=61;
                                tabJoueurs[0].pion.coordY=2;
                                tabJoueurs[0].pion.taille=24;
                                plateauJeu.orientation1=plateauJeu.haut1;
                                plateauJeu.repaint();
                                credits1.setText("Crédits : " + joueurCourant.credits + " ECTS");
                            }
                            else if(joueurCourant==tabJoueurs[1]) {
                                tabJoueurs[1].pion.coordX=18;
                                tabJoueurs[1].pion.coordY=2;
                                tabJoueurs[1].pion.taille=24;
                                plateauJeu.orientation2=plateauJeu.haut2;
                                plateauJeu.repaint();
                                credits2.setText("Crédits : " + joueurCourant.credits + " ECTS");
                            }
                            else if(joueurCourant==tabJoueurs[2]) {
                                tabJoueurs[2].pion.coordX=2;
                                tabJoueurs[2].pion.coordY=20;
                                tabJoueurs[2].pion.taille=24;
                                plateauJeu.orientation3=plateauJeu.gauche3;
                                plateauJeu.repaint();
                                credits3.setText("Crédits : " + joueurCourant.credits + " ECTS");
                            }
                            else if(joueurCourant==tabJoueurs[3]) {
                                tabJoueurs[3].pion.coordX=2;
                                tabJoueurs[3].pion.coordY=63;
                                tabJoueurs[3].pion.taille=24;
                                plateauJeu.orientation4=plateauJeu.gauche4;
                                plateauJeu.repaint();
                                credits4.setText("Crédits : " + joueurCourant.credits + " ECTS");
                            }
                            zone_texte_infos.setText(zone_texte_infos.getText() + "\n" + joueurCourant.nom + " vous venez de rater vos 3 essais aux rattrapages, vous payez donc 50 crédits et réintégrez le parcours scolaire!");
                        }
                        if(joueurCourant==tabJoueurs[0] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                            ValiderMatiere1.setVisible(true);
                        }
                        else if(joueurCourant==tabJoueurs[1] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                            ValiderMatiere2.setVisible(true);
                        }
                        else if(joueurCourant==tabJoueurs[2] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                            ValiderMatiere3.setVisible(true);
                        }
                        else if(joueurCourant==tabJoueurs[3] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                            ValiderMatiere4.setVisible(true);
                        }
                        if(joueurCourant==tabJoueurs[0] && joueurCourant.prison==true) {
                            joueurCourant.compteurTourPrison++;
                            if(joueurCourant.nbCartes!=0) {
                                Libération1.setVisible(true);
                            }
                            PayerPrison1.setVisible(true);
                        }
                        else if(joueurCourant==tabJoueurs[1] && joueurCourant.prison==true) {
                            joueurCourant.compteurTourPrison++;
                            if(joueurCourant.nbCartes!=0) {
                                Libération2.setVisible(true);
                            }
                            PayerPrison2.setVisible(true);
                        }
                        else if(joueurCourant==tabJoueurs[2] && joueurCourant.prison==true) {
                            joueurCourant.compteurTourPrison++;
                            if(joueurCourant.nbCartes!=0) {
                                Libération3.setVisible(true);
                            }
                            PayerPrison3.setVisible(true);
                        }
                        else if(joueurCourant==tabJoueurs[3] && joueurCourant.prison==true) {
                            joueurCourant.compteurTourPrison++;
                            if(joueurCourant.nbCartes!=0) {
                                Libération4.setVisible(true);
                            }
                            PayerPrison4.setVisible(true);
                        }
                        if(elim!=true) {
                            Lancer_des.setEnabled(true);
                        }
                        if(joueurCourant.prison!=true && joueurCourant.nom!=null && compteurDouble==0) {
                            zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon tirez les dés!");
                        }
                        else if(joueurCourant.prison!=true && joueurCourant.nom!=null) {
                            zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, vous pouvez tirez les dés!");
                        }
                        else {
                            if(joueurCourant.nom!=null) {
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon essayer de quitter les rattrapages (vous pouvez payer 50 crédits, jouer une carte libération de rattrapages si vous en possédez une ou sinon tenter de faire un double)!");
                            }
                        }
                        plateauJeu.repaint();
                    }
                };
                thread4.start();
            }
            carte="";
            return true;
        }
        else if (caseDuJoueur == plateau.plateaudejeu[12] && caseDuJoueur.proprietaire != null && caseDuJoueur.proprietaire!=joueurCourant) { //premiere compagnie déjà achetée
            if (caseDuJoueur.proprietaire == plateau.plateaudejeu[28].proprietaire) {
                joueurCourant.credits = joueurCourant.credits - (de1.valeur + de2.valeur)*10;
                caseDuJoueur.proprietaire.credits = caseDuJoueur.proprietaire.credits + (de1.valeur + de2.valeur)*10;
                credits1.setText("Crédits : " + tabJoueurs[0].credits + " ECTS");
                credits2.setText("Crédits : " + tabJoueurs[1].credits + " ECTS");
                credits3.setText("Crédits : " + tabJoueurs[2].credits + " ECTS");
                credits4.setText("Crédits : " + tabJoueurs[3].credits + " ECTS");
                zone_texte_infos.setText(joueurCourant.nom + ", vous devez payer la maintenance des micro-ondes à " + caseDuJoueur.proprietaire.nom + " qui en est le respo! Vous lui versez " + (de1.valeur + de2.valeur)*10 + " ECTS!");
                return true;
            }
            else {
                joueurCourant.credits = joueurCourant.credits - (de1.valeur + de2.valeur)*4;
                caseDuJoueur.proprietaire.credits = caseDuJoueur.proprietaire.credits + (de1.valeur + de2.valeur)*4;
                credits1.setText("Crédits : " + tabJoueurs[0].credits + " ECTS");
                credits2.setText("Crédits : " + tabJoueurs[1].credits + " ECTS");
                credits3.setText("Crédits : " + tabJoueurs[2].credits + " ECTS");
                credits4.setText("Crédits : " + tabJoueurs[3].credits + " ECTS");
                zone_texte_infos.setText(joueurCourant.nom + ", vous devez payer la maintenance des micro-ondes à " + caseDuJoueur.proprietaire.nom + " qui en est le respo! Vous lui versez " + (de1.valeur + de2.valeur)*4 + " ECTS!");
                return true;
            }
        }
        else if (caseDuJoueur == plateau.plateaudejeu[28] && caseDuJoueur.proprietaire != null && caseDuJoueur.proprietaire!=joueurCourant) { //deuxième compagnie déjà achetée
            if (caseDuJoueur.proprietaire == plateau.plateaudejeu[12].proprietaire) {
                joueurCourant.credits = joueurCourant.credits - (de1.valeur + de2.valeur)*10;
                caseDuJoueur.proprietaire.credits = caseDuJoueur.proprietaire.credits + (de1.valeur + de2.valeur)*10;
                credits1.setText("Crédits : " + tabJoueurs[0].credits + " ECTS");
                credits2.setText("Crédits : " + tabJoueurs[1].credits + " ECTS");
                credits3.setText("Crédits : " + tabJoueurs[2].credits + " ECTS");
                credits4.setText("Crédits : " + tabJoueurs[3].credits + " ECTS");
                zone_texte_infos.setText(joueurCourant.nom + ", vous devez payer la maintenace du chauffage à " + caseDuJoueur.proprietaire.nom + " qui en est le respo! Vous lui versez " + (de1.valeur + de2.valeur)*10 + " ECTS!");
                return true;
            }
            else {
                joueurCourant.credits = joueurCourant.credits - (de1.valeur + de2.valeur)*4;
                caseDuJoueur.proprietaire.credits = caseDuJoueur.proprietaire.credits + (de1.valeur + de2.valeur)*4;
                credits1.setText("Crédits : " + tabJoueurs[0].credits + " ECTS");
                credits2.setText("Crédits : " + tabJoueurs[1].credits + " ECTS");
                credits3.setText("Crédits : " + tabJoueurs[2].credits + " ECTS");
                credits4.setText("Crédits : " + tabJoueurs[3].credits + " ECTS");
                zone_texte_infos.setText(joueurCourant.nom + ", vous devez payer la maintenance du chauffage à " + caseDuJoueur.proprietaire.nom + " qui en est le respo! Vous lui versez " + (de1.valeur + de2.valeur)*4 + " ECTS!");
                return true;
            }
        }
        else if (caseDuJoueur == plateau.plateaudejeu[5] && caseDuJoueur.proprietaire != null && caseDuJoueur.proprietaire!=joueurCourant) {
            payerloyer(caseDuJoueur);
            zone_texte_infos.setText(joueurCourant.nom + ", vous tombez dans un des lieux iconiques préférés de " + caseDuJoueur.proprietaire.nom + "Vous lui versez " + caseDuJoueur.loyer + " ECTS pour vous excuser!");
        }
        else if (caseDuJoueur == plateau.plateaudejeu[15] && caseDuJoueur.proprietaire != null && caseDuJoueur.proprietaire!=joueurCourant) {
            payerloyer(caseDuJoueur);
            zone_texte_infos.setText(joueurCourant.nom + ", vous tombez dans un des lieux iconiques préférés de " + caseDuJoueur.proprietaire.nom + "Vous lui versez " + caseDuJoueur.loyer + " ECTS pour vous excuser!");
        }
        else if (caseDuJoueur == plateau.plateaudejeu[25] && caseDuJoueur.proprietaire != null && caseDuJoueur.proprietaire!=joueurCourant) {
            payerloyer(caseDuJoueur);
            zone_texte_infos.setText(joueurCourant.nom + ", vous tombez dans un des lieux iconiques préférés de " + caseDuJoueur.proprietaire.nom + "Vous lui versez " + caseDuJoueur.loyer + " ECTS pour vous excuser!");
        }
        else if (caseDuJoueur == plateau.plateaudejeu[35] && caseDuJoueur.proprietaire != null && caseDuJoueur.proprietaire!=joueurCourant) {
            payerloyer(caseDuJoueur);
            zone_texte_infos.setText(joueurCourant.nom + ", vous tombez dans un des lieux iconiques préférés de " + caseDuJoueur.proprietaire .nom+ "Vous lui versez " + caseDuJoueur.loyer + " ECTS pour vous excuser!");
        }
        
        else if (caseDuJoueur.proprietaire != null && caseDuJoueur.proprietaire!=joueurCourant) { //autre cases déjà achetées
            payerloyer(caseDuJoueur);
            zone_texte_infos.setText(joueurCourant.nom + ", vous entrez en " + joueurCourant.pion.caseassociee.nom + ", la salle où " + caseDuJoueur.proprietaire.nom + " est en train de réviser! Vous lui versez " + caseDuJoueur.loyer + " ECTS pour utiliser son matériel.");
            return true;
        }
        else if (caseDuJoueur.proprietaire != null && caseDuJoueur.proprietaire==joueurCourant) { //autre cases déjà achetées
            zone_texte_infos.setText(joueurCourant.nom + ", vous entrez dans votre salle " + joueurCourant.pion.caseassociee.nom + ", les partiels approchent, révisez bien.");
            return true;
        }
        return false;
    }
     
     
        public void eliminationJoueur() { //réinitialiser toutes ses cases
        elim=true;
        for (int i=0; i<joueurCourant.sallesPossedees.size(); i++) {
            Case c = joueurCourant.sallesPossedees.get(0);
            c.maison = 0;
            c.proprietaire = null;
            joueurCourant.sallesPossedees.remove(0);
            
        }
        String nom = joueurCourant.nom;
        joueurCourant.sallesPossedees=null;
        joueurCourant.tabCartes=null;
        boolean memecase=false;
        for(int i=0;i<tabJoueurs.length;i++) {
            if(tabJoueurs[i]!=joueurCourant && tabJoueurs[i].pion.caseassociee==joueurCourant.pion.caseassociee) {
                memecase=true;
            }
        }
        if(memecase==false) {
            joueurCourant.pion.caseassociee.occupant=false;
        }
        joueurCourant.pion.caseassociee=null;
        if(joueurCourant==tabJoueurs[0]) {
            Thread thread1 = new Thread(){
                public void run(){
                    try {
                        joueurCourant.nom=null;
                        Thread.sleep(1000);
                        for(int i=0;i<2;i++) {
                            joueurCourant.pion.taille-=5;
                            plateauJeu.orientation1=plateauJeu.haut1;
                            plateauJeu.repaint();
                            Thread.sleep(100);
                            joueurCourant.pion.taille-=5;
                            plateauJeu.orientation1=plateauJeu.droite1;
                            plateauJeu.repaint();
                            Thread.sleep(100);
                            joueurCourant.pion.taille-=5;
                            plateauJeu.orientation1=plateauJeu.bas1;
                            plateauJeu.repaint();
                            Thread.sleep(100);
                            joueurCourant.pion.taille-=5;
                            plateauJeu.orientation1=plateauJeu.gauche1;
                            plateauJeu.repaint();
                            Thread.sleep(100);
                        }
                        infos_joueurs1.setBackground(Color.DARK_GRAY);
                        infos_joueurs1.setEnabled(false);
                        Joueur1.setVisible(false);
                        credits1.setBackground(Color.DARK_GRAY);
                        elimine1.setVisible(true);
                        marron1.setEnabled(false);
                        bleuciel1.setEnabled(false);
                        violet1.setEnabled(false);
                        orange1.setEnabled(false);
                        rouge1.setEnabled(false);
                        jaune1.setEnabled(false);
                        vert1.setEnabled(false);
                        bleufoncé1.setEnabled(false);
                        ValiderMatiere1.setVisible(false);
                        PayerPrison1.setVisible(false);
                        Libération1.setVisible(false);
                        Acheter1.setVisible(false);
                        fin_de_tour1.setVisible(false);
                        tabJoueurs[0].pion.taille=0;
                        plateauJeu.repaint();
                        zone_texte_infos.setText(zone_texte_infos.getText() + "\n" + nom + " vous n'avez plus aucun crédit ECTS, le jury de fin d'année ne vous autorise donc pas à poursuivre la formation ! Jousset ne peut donc que vous conseiller de vous réinscrire sur Parcoursup !");
                        Thread.sleep(2000);
                        elim=false;
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Monopoly_EPF.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    for (int i=0; i<4; i++) {
                        if (joueurCourant == tabJoueurs[i]) { //enlever le joueur du tableau
                            tabJoueurs[i].nom = null;
                        }
                    }
                    int joueursrestants=0;
                    String nomwinner = "";
                    for(int j=0;j<tabJoueurs.length;j++) {
                        if(tabJoueurs[j].nom!=null) {
                            joueursrestants++;
                            nomwinner=tabJoueurs[j].nom;
                        }
                    }
                    Lancer_des.setEnabled(true);
                    if(joueursrestants==1) {
                        findePartie(nomwinner);
                        partiefinie=true;
                    }
                    changerJoueur();
                    compteurDouble=0;
                    while(joueurCourant.droitdejouer==false) {
                        joueurCourant.droitdejouer=true;
                        changerJoueur();
                        compteurDouble=0;
                    }
                    if(joueurCourant.compteurTourPrison==3) {
                        joueurCourant.compteurTourPrison=0;
                        joueurCourant.prison=false;
                        joueurCourant.credits=joueurCourant.credits-50;
                        if(joueurCourant==tabJoueurs[0]) {
                            tabJoueurs[0].pion.coordX=61;
                            tabJoueurs[0].pion.coordY=2;
                            tabJoueurs[0].pion.taille=24;
                            plateauJeu.orientation1=plateauJeu.haut1;
                            plateauJeu.repaint();
                            credits1.setText("Crédits : " + joueurCourant.credits + " ECTS");
                        }
                        else if(joueurCourant==tabJoueurs[1]) {
                            tabJoueurs[1].pion.coordX=18;
                            tabJoueurs[1].pion.coordY=2;
                            tabJoueurs[1].pion.taille=24;
                            plateauJeu.orientation2=plateauJeu.haut2;
                            plateauJeu.repaint();
                            credits2.setText("Crédits : " + joueurCourant.credits + " ECTS");
                        }
                        else if(joueurCourant==tabJoueurs[2]) {
                            tabJoueurs[2].pion.coordX=2;
                            tabJoueurs[2].pion.coordY=20;
                            tabJoueurs[2].pion.taille=24;
                            plateauJeu.orientation3=plateauJeu.gauche3;
                            plateauJeu.repaint();
                            credits3.setText("Crédits : " + joueurCourant.credits + " ECTS");
                        }
                        else if(joueurCourant==tabJoueurs[3]) {
                            tabJoueurs[3].pion.coordX=2;
                            tabJoueurs[3].pion.coordY=63;
                            tabJoueurs[3].pion.taille=24;
                            plateauJeu.orientation4=plateauJeu.gauche4;
                            plateauJeu.repaint();
                            credits4.setText("Crédits : " + joueurCourant.credits + " ECTS");
                        }
                        zone_texte_infos.setText(zone_texte_infos.getText() + "\n" + joueurCourant.nom + " vous venez de rater vos 3 essais aux rattrapages, vous payez donc 50 crédits et réintégrez le parcours scolaire!");
                    }
                    if(joueurCourant==tabJoueurs[0] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                        ValiderMatiere1.setVisible(true);
                    }
                    else if(joueurCourant==tabJoueurs[1] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                        ValiderMatiere2.setVisible(true);
                    }
                    else if(joueurCourant==tabJoueurs[2] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                        ValiderMatiere3.setVisible(true);
                    }
                    else if(joueurCourant==tabJoueurs[3] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                        ValiderMatiere4.setVisible(true);
                    }
                    if(joueurCourant==tabJoueurs[0] && joueurCourant.prison==true) {
                        joueurCourant.compteurTourPrison++;
                        if(joueurCourant.nbCartes!=0) {
                            Libération1.setVisible(true);
                        }
                        PayerPrison1.setVisible(true);
                    }
                    else if(joueurCourant==tabJoueurs[1] && joueurCourant.prison==true) {
                        joueurCourant.compteurTourPrison++;
                        if(joueurCourant.nbCartes!=0) {
                            Libération2.setVisible(true);
                        }
                        PayerPrison2.setVisible(true);
                    }
                    else if(joueurCourant==tabJoueurs[2] && joueurCourant.prison==true) {
                        joueurCourant.compteurTourPrison++;
                        if(joueurCourant.nbCartes!=0) {
                            Libération3.setVisible(true);
                        }
                        PayerPrison3.setVisible(true);
                    }
                    else if(joueurCourant==tabJoueurs[3] && joueurCourant.prison==true) {
                        joueurCourant.compteurTourPrison++;
                        if(joueurCourant.nbCartes!=0) {
                            Libération4.setVisible(true);
                        }
                        PayerPrison4.setVisible(true);
                    }
                    if(joueurCourant.prison!=true && joueurCourant.nom!=null && compteurDouble==0) {
                        zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon tirez les dés!");
                    }
                    else if(joueurCourant.prison!=true && joueurCourant.nom!=null) {
                        zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, vous pouvez tirez les dés!");
                    }
                    else {
                        if(joueurCourant.nom!=null && partiefinie!=true) {
                            zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon essayer de quitter les rattrapages (vous pouvez payer 50 crédits, jouer une carte libération de rattrapages si vous en possédez une ou sinon tenter de faire un double)!");
                        }
                    }
                    plateauJeu.repaint();
                }
            };
            thread1.start();
        }
        else if(joueurCourant==tabJoueurs[1]) {
            Thread thread2 = new Thread(){
                public void run(){
                    try {
                        joueurCourant.nom=null;
                        Thread.sleep(1000);
                        for(int i=0;i<2;i++) {
                            joueurCourant.pion.taille-=5;
                            plateauJeu.orientation2=plateauJeu.haut2;
                            plateauJeu.repaint();
                            Thread.sleep(100);
                            joueurCourant.pion.taille-=5;
                            plateauJeu.orientation2=plateauJeu.droite2;
                            plateauJeu.repaint();
                            Thread.sleep(100);
                            joueurCourant.pion.taille-=5;
                            plateauJeu.orientation2=plateauJeu.bas2;
                            plateauJeu.repaint();
                            Thread.sleep(100);
                            joueurCourant.pion.taille-=5;
                            plateauJeu.orientation2=plateauJeu.gauche2;
                            plateauJeu.repaint();
                            Thread.sleep(100);
                        }
                        infos_joueurs2.setBackground(Color.DARK_GRAY);
                        infos_joueurs2.setEnabled(false);
                        Joueur2.setVisible(false);
                        credits2.setBackground(Color.DARK_GRAY);
                        elimine2.setVisible(true);
                        marron2.setEnabled(false);
                        bleuciel2.setEnabled(false);
                        violet2.setEnabled(false);
                        orange2.setEnabled(false);
                        rouge2.setEnabled(false);
                        jaune2.setEnabled(false);
                        vert2.setEnabled(false);
                        bleufoncé2.setEnabled(false);
                        ValiderMatiere2.setVisible(false);
                        PayerPrison2.setVisible(false);
                        Libération2.setVisible(false);
                        Acheter2.setVisible(false);
                        fin_de_tour2.setVisible(false);
                        tabJoueurs[1].pion.taille=0;
                        plateauJeu.repaint();
                        zone_texte_infos.setText(zone_texte_infos.getText() + "\n" + nom + " vous n'avez plus aucun crédit ECTS, le jury de fin d'année ne vous autorise donc pas à poursuivre la formation ! Jousset ne peut donc que vous conseiller de vous réinscrire sur Parcoursup !");
                        Thread.sleep(2000);
                        elim=false;
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Monopoly_EPF.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    for (int i=0; i<4; i++) {
                        if (joueurCourant == tabJoueurs[i]) { //enlever le joueur du tableau
                            tabJoueurs[i].nom = null;
                        }
                    }
                    int joueursrestants=0;
                    String nomwinner = "";
                    for(int j=0;j<tabJoueurs.length;j++) {
                        if(tabJoueurs[j].nom!=null) {
                            joueursrestants++;
                            nomwinner=tabJoueurs[j].nom;
                        }
                    }
                    Lancer_des.setEnabled(true);
                    if(joueursrestants==1) {
                        findePartie(nomwinner);
                        partiefinie=true;
                    }
                    changerJoueur();
                    compteurDouble=0;
                    while(joueurCourant.droitdejouer==false) {
                        joueurCourant.droitdejouer=true;
                        changerJoueur();
                        compteurDouble=0;
                    }
                    if(joueurCourant.compteurTourPrison==3) {
                        joueurCourant.compteurTourPrison=0;
                        joueurCourant.prison=false;
                        joueurCourant.credits=joueurCourant.credits-50;
                        if(joueurCourant==tabJoueurs[0]) {
                            tabJoueurs[0].pion.coordX=61;
                            tabJoueurs[0].pion.coordY=2;
                            tabJoueurs[0].pion.taille=24;
                            plateauJeu.orientation1=plateauJeu.haut1;
                            plateauJeu.repaint();
                            credits1.setText("Crédits : " + joueurCourant.credits + " ECTS");
                        }
                        else if(joueurCourant==tabJoueurs[1]) {
                            tabJoueurs[1].pion.coordX=18;
                            tabJoueurs[1].pion.coordY=2;
                            tabJoueurs[1].pion.taille=24;
                            plateauJeu.orientation2=plateauJeu.haut2;
                            plateauJeu.repaint();
                            credits2.setText("Crédits : " + joueurCourant.credits + " ECTS");
                        }
                        else if(joueurCourant==tabJoueurs[2]) {
                            tabJoueurs[2].pion.coordX=2;
                            tabJoueurs[2].pion.coordY=20;
                            tabJoueurs[2].pion.taille=24;
                            plateauJeu.orientation3=plateauJeu.gauche3;
                            plateauJeu.repaint();
                            credits3.setText("Crédits : " + joueurCourant.credits + " ECTS");
                        }
                        else if(joueurCourant==tabJoueurs[3]) {
                            tabJoueurs[3].pion.coordX=2;
                            tabJoueurs[3].pion.coordY=63;
                            tabJoueurs[3].pion.taille=24;
                            plateauJeu.orientation4=plateauJeu.gauche4;
                            plateauJeu.repaint();
                            credits4.setText("Crédits : " + joueurCourant.credits + " ECTS");
                        }
                        zone_texte_infos.setText(zone_texte_infos.getText() + "\n" + joueurCourant.nom + " vous venez de rater vos 3 essais aux rattrapages, vous payez donc 50 crédits et réintégrez le parcours scolaire!");
                    }
                    if(joueurCourant==tabJoueurs[0] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                        ValiderMatiere1.setVisible(true);
                    }
                    else if(joueurCourant==tabJoueurs[1] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                        ValiderMatiere2.setVisible(true);
                    }
                    else if(joueurCourant==tabJoueurs[2] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                        ValiderMatiere3.setVisible(true);
                    }
                    else if(joueurCourant==tabJoueurs[3] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                        ValiderMatiere4.setVisible(true);
                    }
                    if(joueurCourant==tabJoueurs[0] && joueurCourant.prison==true) {
                        joueurCourant.compteurTourPrison++;
                        if(joueurCourant.nbCartes!=0) {
                            Libération1.setVisible(true);
                        }
                        PayerPrison1.setVisible(true);
                    }
                    else if(joueurCourant==tabJoueurs[1] && joueurCourant.prison==true) {
                        joueurCourant.compteurTourPrison++;
                        if(joueurCourant.nbCartes!=0) {
                            Libération2.setVisible(true);
                        }
                        PayerPrison2.setVisible(true);
                    }
                    else if(joueurCourant==tabJoueurs[2] && joueurCourant.prison==true) {
                        joueurCourant.compteurTourPrison++;
                        if(joueurCourant.nbCartes!=0) {
                            Libération3.setVisible(true);
                        }
                        PayerPrison3.setVisible(true);
                    }
                    else if(joueurCourant==tabJoueurs[3] && joueurCourant.prison==true) {
                        joueurCourant.compteurTourPrison++;
                        if(joueurCourant.nbCartes!=0) {
                            Libération4.setVisible(true);
                        }
                        PayerPrison4.setVisible(true);
                    }
                    if(joueurCourant.prison!=true && joueurCourant.nom!=null && compteurDouble==0) {
                        zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon tirez les dés!");
                    }
                    else if(joueurCourant.prison!=true && joueurCourant.nom!=null) {
                        zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, vous pouvez tirez les dés!");
                    }
                    else {
                        if(joueurCourant.nom!=null && partiefinie!=true) {
                            zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon essayer de quitter les rattrapages (vous pouvez payer 50 crédits, jouer une carte libération de rattrapages si vous en possédez une ou sinon tenter de faire un double)!");
                        }
                    }
                    plateauJeu.repaint();
                }
            };
            thread2.start();
        }
        if(joueurCourant==tabJoueurs[2]) {
            Thread thread3 = new Thread(){
                public void run(){
                    try {
                        joueurCourant.nom=null;
                        Thread.sleep(1000);
                        for(int i=0;i<2;i++) {
                            joueurCourant.pion.taille-=5;
                            plateauJeu.orientation3=plateauJeu.haut3;
                            plateauJeu.repaint();
                            Thread.sleep(100);
                            joueurCourant.pion.taille-=5;
                            plateauJeu.orientation3=plateauJeu.droite3;
                            plateauJeu.repaint();
                            Thread.sleep(100);
                            joueurCourant.pion.taille-=5;
                            plateauJeu.orientation3=plateauJeu.bas3;
                            plateauJeu.repaint();
                            Thread.sleep(100);
                            joueurCourant.pion.taille-=5;
                            plateauJeu.orientation3=plateauJeu.gauche3;
                            plateauJeu.repaint();
                            Thread.sleep(100);
                        }
                        infos_joueurs3.setBackground(Color.DARK_GRAY);
                        infos_joueurs3.setEnabled(false);
                        Joueur3.setVisible(false);
                        credits3.setBackground(Color.DARK_GRAY);
                        elimine3.setVisible(true);
                        marron3.setEnabled(false);
                        bleuciel3.setEnabled(false);
                        violet3.setEnabled(false);
                        orange3.setEnabled(false);
                        rouge3.setEnabled(false);
                        jaune3.setEnabled(false);
                        vert3.setEnabled(false);
                        bleufoncé3.setEnabled(false);
                        ValiderMatiere3.setVisible(false);
                        PayerPrison3.setVisible(false);
                        Libération3.setVisible(false);
                        Acheter3.setVisible(false);
                        fin_de_tour3.setVisible(false);
                        tabJoueurs[2].pion.taille=0;
                        plateauJeu.repaint();
                        zone_texte_infos.setText(zone_texte_infos.getText() + "\n" + nom + " vous n'avez plus aucun crédit ECTS, le jury de fin d'année ne vous autorise donc pas à poursuivre la formation ! Jousset ne peut donc que vous conseiller de vous réinscrire sur Parcoursup !");
                        Thread.sleep(2000);
                        elim=false;
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Monopoly_EPF.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    for (int i=0; i<4; i++) {
                        if (joueurCourant == tabJoueurs[i]) { //enlever le joueur du tableau
                            tabJoueurs[i].nom = null;
                        }
                    }
                    int joueursrestants=0;
                    String nomwinner = "";
                    for(int j=0;j<tabJoueurs.length;j++) {
                        if(tabJoueurs[j].nom!=null) {
                            joueursrestants++;
                            nomwinner=tabJoueurs[j].nom;
                        }
                    }
                    Lancer_des.setEnabled(true);
                    if(joueursrestants==1) {
                        findePartie(nomwinner);
                        partiefinie=true;
                    }
                    changerJoueur();
                    compteurDouble=0;
                    while(joueurCourant.droitdejouer==false) {
                        joueurCourant.droitdejouer=true;
                        changerJoueur();
                        compteurDouble=0;
                    }
                    if(joueurCourant.compteurTourPrison==3) {
                        joueurCourant.compteurTourPrison=0;
                        joueurCourant.prison=false;
                        joueurCourant.credits=joueurCourant.credits-50;
                        if(joueurCourant==tabJoueurs[0]) {
                            tabJoueurs[0].pion.coordX=61;
                            tabJoueurs[0].pion.coordY=2;
                            tabJoueurs[0].pion.taille=24;
                            plateauJeu.orientation1=plateauJeu.haut1;
                            plateauJeu.repaint();
                            credits1.setText("Crédits : " + joueurCourant.credits + " ECTS");
                        }
                        else if(joueurCourant==tabJoueurs[1]) {
                            tabJoueurs[1].pion.coordX=18;
                            tabJoueurs[1].pion.coordY=2;
                            tabJoueurs[1].pion.taille=24;
                            plateauJeu.orientation2=plateauJeu.haut2;
                            plateauJeu.repaint();
                            credits2.setText("Crédits : " + joueurCourant.credits + " ECTS");
                        }
                        else if(joueurCourant==tabJoueurs[2]) {
                            tabJoueurs[2].pion.coordX=2;
                            tabJoueurs[2].pion.coordY=20;
                            tabJoueurs[2].pion.taille=24;
                            plateauJeu.orientation3=plateauJeu.gauche3;
                            plateauJeu.repaint();
                            credits3.setText("Crédits : " + joueurCourant.credits + " ECTS");
                        }
                        else if(joueurCourant==tabJoueurs[3]) {
                            tabJoueurs[3].pion.coordX=2;
                            tabJoueurs[3].pion.coordY=63;
                            tabJoueurs[3].pion.taille=24;
                            plateauJeu.orientation4=plateauJeu.gauche4;
                            plateauJeu.repaint();
                            credits4.setText("Crédits : " + joueurCourant.credits + " ECTS");
                        }
                        zone_texte_infos.setText(zone_texte_infos.getText() + "\n" + joueurCourant.nom + " vous venez de rater vos 3 essais aux rattrapages, vous payez donc 50 crédits et réintégrez le parcours scolaire!");
                    }
                    if(joueurCourant==tabJoueurs[0] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                        ValiderMatiere1.setVisible(true);
                    }
                    else if(joueurCourant==tabJoueurs[1] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                        ValiderMatiere2.setVisible(true);
                    }
                    else if(joueurCourant==tabJoueurs[2] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                        ValiderMatiere3.setVisible(true);
                    }
                    else if(joueurCourant==tabJoueurs[3] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                        ValiderMatiere4.setVisible(true);
                    }
                    if(joueurCourant==tabJoueurs[0] && joueurCourant.prison==true) {
                        joueurCourant.compteurTourPrison++;
                        if(joueurCourant.nbCartes!=0) {
                            Libération1.setVisible(true);
                        }
                        PayerPrison1.setVisible(true);
                    }
                    else if(joueurCourant==tabJoueurs[1] && joueurCourant.prison==true) {
                        joueurCourant.compteurTourPrison++;
                        if(joueurCourant.nbCartes!=0) {
                            Libération2.setVisible(true);
                        }
                        PayerPrison2.setVisible(true);
                    }
                    else if(joueurCourant==tabJoueurs[2] && joueurCourant.prison==true) {
                        joueurCourant.compteurTourPrison++;
                        if(joueurCourant.nbCartes!=0) {
                            Libération3.setVisible(true);
                        }
                        PayerPrison3.setVisible(true);
                    }
                    else if(joueurCourant==tabJoueurs[3] && joueurCourant.prison==true) {
                        joueurCourant.compteurTourPrison++;
                        if(joueurCourant.nbCartes!=0) {
                            Libération4.setVisible(true);
                        }
                        PayerPrison4.setVisible(true);
                    }
                    if(joueurCourant.prison!=true && joueurCourant.nom!=null && compteurDouble==0) {
                        zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon tirez les dés!");
                    }
                    else if(joueurCourant.prison!=true && joueurCourant.nom!=null) {
                        zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, vous pouvez tirez les dés!");
                    }
                    else {
                        if(joueurCourant.nom!=null && partiefinie!=true) {
                            zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon essayer de quitter les rattrapages (vous pouvez payer 50 crédits, jouer une carte libération de rattrapages si vous en possédez une ou sinon tenter de faire un double)!");
                        }
                    }
                    plateauJeu.repaint();
                }
            };
            thread3.start();
        }
        if(joueurCourant==tabJoueurs[3]) {
            Thread thread4 = new Thread(){
                public void run(){
                    try {
                        joueurCourant.nom=null;
                        Thread.sleep(1000);
                        for(int i=0;i<2;i++) {
                            joueurCourant.pion.taille-=5;
                            plateauJeu.orientation4=plateauJeu.haut4;
                            plateauJeu.repaint();
                            Thread.sleep(100);
                           joueurCourant.pion.taille-=5;
                            plateauJeu.orientation4=plateauJeu.droite4;
                            plateauJeu.repaint();
                            Thread.sleep(100);
                            joueurCourant.pion.taille-=5;
                            plateauJeu.orientation4=plateauJeu.bas4;
                            plateauJeu.repaint();
                            Thread.sleep(100);
                            joueurCourant.pion.taille-=5;
                            plateauJeu.orientation4=plateauJeu.gauche4;
                            plateauJeu.repaint();
                            Thread.sleep(100);
                        }
                        infos_joueurs4.setBackground(Color.DARK_GRAY);
                        infos_joueurs4.setEnabled(false);
                        Joueur4.setVisible(false);
                        credits4.setBackground(Color.DARK_GRAY);
                        elimine4.setVisible(true);
                        marron4.setEnabled(false);
                        bleuciel4.setEnabled(false);
                        violet4.setEnabled(false);
                        orange4.setEnabled(false);
                        rouge4.setEnabled(false);
                        jaune4.setEnabled(false);
                        vert4.setEnabled(false);
                        bleufoncé4.setEnabled(false);
                        ValiderMatiere4.setVisible(false);
                        PayerPrison4.setVisible(false);
                        Libération4.setVisible(false);
                        Acheter4.setVisible(false);
                        fin_de_tour4.setVisible(false);
                        tabJoueurs[3].pion.taille=0;
                        plateauJeu.repaint();
                        zone_texte_infos.setText(zone_texte_infos.getText() + "\n" + nom + " vous n'avez plus aucun crédit ECTS, le jury de fin d'année ne vous autorise donc pas à poursuivre la formation ! Jousset ne peut donc que vous conseiller de vous réinscrire sur Parcoursup !");
                        Thread.sleep(2000);
                        elim=false;
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Monopoly_EPF.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    for (int i=0; i<4; i++) {
                        if (joueurCourant == tabJoueurs[i]) { //enlever le joueur du tableau
                            tabJoueurs[i].nom = null;
                        }
                    }
                    int joueursrestants=0;
                    String nomwinner="";
                    for(int j=0;j<tabJoueurs.length;j++) {
                        if(tabJoueurs[j].nom!=null) {
                            joueursrestants++;
                            nomwinner=tabJoueurs[j].nom;
                        }
                    }
                    Lancer_des.setEnabled(true);
                    if(joueursrestants==1) {
                        findePartie(nomwinner);
                        partiefinie=true;
                    }
                    changerJoueur();
                    compteurDouble=0;
                    while(joueurCourant.droitdejouer==false) {
                        joueurCourant.droitdejouer=true;
                        changerJoueur();
                        compteurDouble=0;
                    }
                    if(joueurCourant.compteurTourPrison==3) {
                        joueurCourant.compteurTourPrison=0;
                        joueurCourant.prison=false;
                        joueurCourant.credits=joueurCourant.credits-50;
                        if(joueurCourant==tabJoueurs[0]) {
                            tabJoueurs[0].pion.coordX=61;
                            tabJoueurs[0].pion.coordY=2;
                            tabJoueurs[0].pion.taille=24;
                            plateauJeu.orientation1=plateauJeu.haut1;
                            plateauJeu.repaint();
                            credits1.setText("Crédits : " + joueurCourant.credits + " ECTS");
                        }
                        else if(joueurCourant==tabJoueurs[1]) {
                            tabJoueurs[1].pion.coordX=18;
                            tabJoueurs[1].pion.coordY=2;
                            tabJoueurs[1].pion.taille=24;
                            plateauJeu.orientation2=plateauJeu.haut2;
                            plateauJeu.repaint();
                            credits2.setText("Crédits : " + joueurCourant.credits + " ECTS");
                        }
                        else if(joueurCourant==tabJoueurs[2]) {
                            tabJoueurs[2].pion.coordX=2;
                            tabJoueurs[2].pion.coordY=20;
                            tabJoueurs[2].pion.taille=24;
                            plateauJeu.orientation3=plateauJeu.gauche3;
                            plateauJeu.repaint();
                            credits3.setText("Crédits : " + joueurCourant.credits + " ECTS");
                        }
                        else if(joueurCourant==tabJoueurs[3]) {
                            tabJoueurs[3].pion.coordX=2;
                            tabJoueurs[3].pion.coordY=63;
                            tabJoueurs[3].pion.taille=24;
                            plateauJeu.orientation4=plateauJeu.gauche4;
                            plateauJeu.repaint();
                            credits4.setText("Crédits : " + joueurCourant.credits + " ECTS");
                        }
                        zone_texte_infos.setText(zone_texte_infos.getText() + "\n" + joueurCourant.nom + " vous venez de rater vos 3 essais aux rattrapages, vous payez donc 50 crédits et réintégrez le parcours scolaire!");
                    }
                    if(joueurCourant==tabJoueurs[0] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                        ValiderMatiere1.setVisible(true);
                    }
                    else if(joueurCourant==tabJoueurs[1] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                        ValiderMatiere2.setVisible(true);
                    }
                    else if(joueurCourant==tabJoueurs[2] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                        ValiderMatiere3.setVisible(true);
                    }
                    else if(joueurCourant==tabJoueurs[3] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                        ValiderMatiere4.setVisible(true);
                    }
                    if(joueurCourant==tabJoueurs[0] && joueurCourant.prison==true) {
                        joueurCourant.compteurTourPrison++;
                        if(joueurCourant.nbCartes!=0) {
                            Libération1.setVisible(true);
                        }
                        PayerPrison1.setVisible(true);
                    }
                    else if(joueurCourant==tabJoueurs[1] && joueurCourant.prison==true) {
                        joueurCourant.compteurTourPrison++;
                        if(joueurCourant.nbCartes!=0) {
                            Libération2.setVisible(true);
                        }
                        PayerPrison2.setVisible(true);
                    }
                    else if(joueurCourant==tabJoueurs[2] && joueurCourant.prison==true) {
                        joueurCourant.compteurTourPrison++;
                        if(joueurCourant.nbCartes!=0) {
                            Libération3.setVisible(true);
                        }
                        PayerPrison3.setVisible(true);
                    }
                    else if(joueurCourant==tabJoueurs[3] && joueurCourant.prison==true) {
                        joueurCourant.compteurTourPrison++;
                        if(joueurCourant.nbCartes!=0) {
                            Libération4.setVisible(true);
                        }
                        PayerPrison4.setVisible(true);
                    }
                    if(joueurCourant.prison!=true && joueurCourant.nom!=null && compteurDouble==0) {
                        zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon tirez les dés!");
                    }
                    else if(joueurCourant.prison!=true && joueurCourant.nom!=null) {
                        zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, vous pouvez tirez les dés!");
                    }
                    else {
                        if(joueurCourant.nom!=null && partiefinie!=true) {
                            zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon essayer de quitter les rattrapages (vous pouvez payer 50 crédits, jouer une carte libération de rattrapages si vous en possédez une ou sinon tenter de faire un double)!");
                        }
                    }
                    plateauJeu.repaint();
                }
            };
            thread4.start();
        }
    }
        
        
    public void findePartie(String nomgagnant) {
        Lancer_des.setEnabled(false);
        JFrame FrameFinPartie = new JFrame();
        FrameFinPartie.setVisible(true);
        FrameFinPartie.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        FrameFinPartie.setForeground(Color.RED);
        FrameFinPartie.setBounds(450, 200, 615, 400);
        
        JPanel PanelFinPartie = new JPanel();
        PanelFinPartie.setSize(615,430);
        PanelFinPartie.setBackground(Color.WHITE);
        FrameFinPartie.add(PanelFinPartie, new org.netbeans.lib.awtextra.AbsoluteConstraints(0,0,615,430));
        PanelFinPartie.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        this.repaint();
        
        JLabel FinPartieLabel2 = new JLabel("Vous obtenez votre diplôme ingénieur EPF !");
        Dimension dimension_fin_partie2 = FinPartieLabel2.getPreferredSize();
        FinPartieLabel2.setFont(new Font("Vous obtenez votre diplôme ingénieur EPF !", Font.BOLD, 10));
        PanelFinPartie.add(FinPartieLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200,230));
        PanelFinPartie.repaint();
        
        JLabel ImgFin = new JLabel(new ImageIcon("imagefin.png"));
        ImgFin.setBounds(182, 5, 250, 250);
        PanelFinPartie.add(ImgFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(182,5,250,250));
        ImgFin.setVisible(true);
        PanelFinPartie.repaint();
        
        JLabel FinPartieLabel = new JLabel(nomgagnant + " vous survolez la deuxième année du cursus ingénieur de l'EPF et validez l'année avec brio !");
        Dimension dimension_fin_partie = FinPartieLabel.getPreferredSize();
        FinPartieLabel.setFont(new Font(nomgagnant + " vous survolez la deuxième année du cursus ingénieur de l'EPF et validez l'année avec brio !", Font.BOLD, 10));
        PanelFinPartie.add(FinPartieLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50,220));
        PanelFinPartie.repaint();
        
        JLabel logoEPF = new JLabel(new ImageIcon("EPF Logo Q2.png"));
        logoEPF.setBounds(255, 250, 105, 80);
        PanelFinPartie.add(logoEPF, new org.netbeans.lib.awtextra.AbsoluteConstraints(255,250,105,80));
        PanelFinPartie.repaint();
        
        zone_texte_infos.setText(nomgagnant + " vous survolez la deuxième année du cursus ingénieur de l'EPF et validez l'année avec brio ! Vous obtenez votre diplôme ingénieur EPF !");
    } 
     
     public void changerJoueur() { // j'ai gardé ta fonction en commentaire et j'en ai écrite une autre parce que j'ai modifié elimination joueur en supprimant les joueurs du tableau
        int i=0;
        while(i<4 && joueurCourant!=tabJoueurs[i]) {
            i++;
        }
        i=(i+1)%4;
        while(tabJoueurs[i].nom==null) {
            i=(i+1)%4;
        }
        joueurCourant=tabJoueurs[i];
        compteurDouble = 0; //réinitialisation du compteur double à 0
    }
     
     public Carte tirerCarte(Paquet paquet) {
        for (int i=0; i<16; i++) {
            if(paquet.tabstatuts[i] == true) {
                paquet.tabstatuts[i] = false;
                if(paquet.paquetVide()==true) {
                    paquet.MAJtab();
                    if(paquet.communaute==true) {
                        Thread threadcom = new Thread(){
                            public void run(){    
                                try {
                                    plateauJeu.afficherCartes=true;
                                    for(int i=0;i<2;i++) {
                                        Thread.sleep(100);
                                        plateauJeu.orientationcom=plateauJeu.com2;
                                        plateauJeu.repaint();
                                        Thread.sleep(250);
                                        plateauJeu.orientationcom=plateauJeu.com3;
                                        plateauJeu.repaint();
                                        Thread.sleep(250);
                                        plateauJeu.orientationcom=plateauJeu.com4;
                                        plateauJeu.repaint();
                                        Thread.sleep(250);
                                        plateauJeu.orientationcom=plateauJeu.com1;
                                        plateauJeu.repaint();
                                    }
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(Monopoly_EPF.class.getName()).log(Level.SEVERE, null, ex);
                                }

                            }
                        };
                        threadcom.start();
                    }
                    else if(paquet.chance==true) {
                        Thread threadchance = new Thread(){
                            public void run(){    
                                try {
                                    plateauJeu.afficherCartes=true;
                                    for(int i=0;i<2;i++) {
                                        Thread.sleep(100);
                                        plateauJeu.orientationchance=plateauJeu.chance2;
                                        plateauJeu.repaint();
                                        Thread.sleep(250);
                                        plateauJeu.orientationchance=plateauJeu.chance3;
                                        plateauJeu.repaint();
                                        Thread.sleep(250);
                                        plateauJeu.orientationchance=plateauJeu.chance4;
                                        plateauJeu.repaint();
                                        Thread.sleep(250);
                                        plateauJeu.orientationchance=plateauJeu.chance1;
                                        plateauJeu.repaint();
                                    }
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(Monopoly_EPF.class.getName()).log(Level.SEVERE, null, ex);
                                }

                            }
                        };
                        threadchance.start();
                    }
                }
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
            else if (id==2) { //DEPLACER LE PION EN GRAPHIQUE
                carte="com2";
                joueurCourant.prison=true;
                zone_texte_infos.setText(joueurCourant.nom + " allez aux rattrapages!");
            }
            else if (id==3) {
                joueurCourant.recuperercarte(carteTiree);
                if(joueurCourant==tabJoueurs[0]) {
                    nbCartes1.setText("Cartes libération de rattrapages : " + tabJoueurs[0].nbCartes);
                }
                else if(joueurCourant==tabJoueurs[1]) {
                    nbCartes2.setText("Cartes libération de rattrapages : " + tabJoueurs[1].nbCartes);
                }
                else if(joueurCourant==tabJoueurs[2]) {
                    nbCartes3.setText("Cartes libération de rattrapages : " + tabJoueurs[2].nbCartes);
                }
                else if(joueurCourant==tabJoueurs[3]) {
                    nbCartes4.setText("Cartes libération de rattrapages : " + tabJoueurs[3].nbCartes);
                }
                zone_texte_infos.setText(joueurCourant.nom + " vous récupérez une carte Caisse de communauté vous libérant des rattrapages!");
            }
            else if (id==5) {
                joueurCourant.credits = joueurCourant.credits+25;
                zone_texte_infos.setText(joueurCourant.nom + " recevez 25 ECTS!");
            }
            else if (id==6) {
                joueurCourant.credits = joueurCourant.credits-25*joueurCourant.nbMaisonJoueur-100*joueurCourant.nbHotelJoueur;
                zone_texte_infos.setText(joueurCourant.nom + " vous payez " + 25*joueurCourant.nbMaisonJoueur + " ECTS pour vos matières et "+100*joueurCourant.nbHotelJoueur + " ECTS pour vos modules!");
            }
            else if (id==7) { // a verifier
                int nbJoueursRestants=0;
                for (int i=0; i<4; i++){ //calcul du nombre de joueurs restants
                    if (tabJoueurs[i] != null) nbJoueursRestants = nbJoueursRestants+1;
                }
                joueurCourant.credits = joueurCourant.credits-15*(nbJoueursRestants-1); //cagnotte du joueur courant qui s'actualise
                zone_texte_infos.setText(joueurCourant.nom + " payez " + 15*(nbJoueursRestants-1) + " ECTS!");
                for (int i=0; i<4; i++){
                    if (tabJoueurs[i] != null && tabJoueurs[i] != joueurCourant) {
                        tabJoueurs[i].credits = tabJoueurs[i].credits+15; //credits des autres joueurs qui d'actualisent
                        zone_texte_infos.setText(zone_texte_infos.getText() + "\n" + tabJoueurs[i].nom + " recevez 15 ECTS de la part de " + joueurCourant.nom + "!");
                    }
                }
            }
            else if (id==8) {
                carte="com8";
                zone_texte_infos.setText(zone_texte_infos.getText() + "\n" + joueurCourant.nom + " avancez de 8 cases (ça souffle)!");
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
                carte="com12";
                zone_texte_infos.setText(joueurCourant.nom + " allez en K04 (vive la chimie)!");
            }  //indice 32 du plateau A VOIR COMMENT ALLER A LA CASE QU4ON VEUT
            else if (id==13) {
                carte="com13";
                zone_texte_infos.setText(joueurCourant.nom + " allez au bureau des Ours");
            }
            else if (id==14) {
                joueurCourant.credits = joueurCourant.credits + 10;
                zone_texte_infos.setText(joueurCourant.nom + " recevez 10 ECTS (et les applaudissements de Jousset)!");
                
            }
            else if (id==15) {
                joueurCourant.credits = joueurCourant.credits + 100;
                zone_texte_infos.setText(joueurCourant.nom + " recevez 100 ECTS!");
            }
        }
        else {
            if (id==0) {
                int nbJoueursRestants=0;
                for (int i=0; i<4; i++){ //calcul du nombre de joueurs restants
                    if (tabJoueurs[i] != null) nbJoueursRestants = nbJoueursRestants+1;
                }
                joueurCourant.credits = joueurCourant.credits+10*(nbJoueursRestants-1); //cagnotte du joueur courant qui s'actualise
                zone_texte_infos.setText(joueurCourant.nom + " recevez " + 10*(nbJoueursRestants-1) + " ECTS (bon anniv)!");
                for (int i=0; i<4; i++){
                    if (tabJoueurs[i] != null && tabJoueurs[i] != joueurCourant) {
                        tabJoueurs[i].credits = tabJoueurs[i].credits-10; //credits des autres joueurs qui d'actualisent
                        zone_texte_infos.setText(zone_texte_infos.getText() + "\n" + tabJoueurs[i].nom + ", vous payez 10 ECTS pour le cadeau de " + joueurCourant.nom + "!");
                    }
                }
            }
            else if (id==1) {
                carte="chance1";
                zone_texte_infos.setText(joueurCourant.nom + " reculez de 3 cases!");
            }
            else if (id==2) {
                carte="chance2";
                joueurCourant.prison = true;
                zone_texte_infos.setText(joueurCourant.nom + " allez aux rattrapages!");
            }
            else if (id==3) {
                carte="chance3";
                zone_texte_infos.setText(zone_texte_infos.getText() + joueurCourant.nom + " allez au début d'année!");
            }
            else if (id==4) {
                carte="chance4";
                zone_texte_infos.setText(joueurCourant.nom + " allez en K03 (bonne chance pour le partiel)!");
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
                else if(joueurCourant==tabJoueurs[1]) {
                    nbCartes2.setText("Cartes libération de rattrapages : " + tabJoueurs[1].nbCartes);
                }
                else if(joueurCourant==tabJoueurs[2]) {
                    nbCartes3.setText("Cartes libération de rattrapages : " + tabJoueurs[2].nbCartes);
                }
                else if(joueurCourant==tabJoueurs[3]) {
                    nbCartes4.setText("Cartes libération de rattrapages : " + tabJoueurs[3].nbCartes);
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
                carte="chance10";
                zone_texte_infos.setText(joueurCourant.nom + " avancez en amphi (vite c'est Mahou qui fait le CM, pas Jousset)!");
            }
            else if (id==11) {
                joueurCourant.credits = joueurCourant.credits-25*joueurCourant.nbMaisonJoueur-100*joueurCourant.nbHotelJoueur;
                zone_texte_infos.setText(joueurCourant.nom + " payez " + 25*joueurCourant.nbMaisonJoueur + " ECTS pour vos matières et " + 100*joueurCourant.nbHotelJoueur + " ECTS pour vos modules!");
            }
            else if (id==12) {
                joueurCourant.credits = joueurCourant.credits-40;
                argentParcGratuit = argentParcGratuit+40;
                zone_texte_infos.setText(joueurCourant.nom + " payez 40 ECTS!");
            }
            else if (id==13) { //téléporter tous les joueurs à la case départ
                carte="chance13";
                for(int i=0;i<plateau.plateaudejeu.length;i++) {
                    if(tabJoueurs[0].pion.caseassociee==plateau.plateaudejeu[i] && tabJoueurs[0].nom!=null) {
                        if(plateau.plateaudejeu[i]!=plateau.plateaudejeu[0]) {
                            compteurlocal++;
                        }
                    }
                    if(tabJoueurs[1].pion.caseassociee==plateau.plateaudejeu[i] && tabJoueurs[1].nom!=null) {
                        if(plateau.plateaudejeu[i]!=plateau.plateaudejeu[0]) {
                            compteurlocal++;
                        }
                    }
                    if(tabJoueurs[2].pion.caseassociee==plateau.plateaudejeu[i] && tabJoueurs[2].nom!=null) {
                        if(plateau.plateaudejeu[i]!=plateau.plateaudejeu[0]) {
                            compteurlocal++;
                        }
                    }
                    if(tabJoueurs[3].pion.caseassociee==plateau.plateaudejeu[i] && tabJoueurs[3].nom!=null) {
                        if(plateau.plateaudejeu[i]!=plateau.plateaudejeu[0]) {
                            compteurlocal++;
                        }
                    }
                }
                zone_texte_infos.setText("Rendez-vous tous au début d'année pour la JPO!");
            }
            else if (id==14) {
                joueurCourant.credits = joueurCourant.credits-75;
                argentParcGratuit = argentParcGratuit+75;
                zone_texte_infos.setText(joueurCourant.nom + " payez 75 ECTS (un brelan fait pas le poids fâce au full du Jousset national)!");
            }
        }
        credits1.setText("Crédits : " + tabJoueurs[0].credits + " ECTS");
        credits2.setText("Crédits : " + tabJoueurs[1].credits + " ECTS");
        credits3.setText("Crédits : " + tabJoueurs[2].credits + " ECTS");
        credits4.setText("Crédits : " + tabJoueurs[3].credits + " ECTS");
        LabelParcGratuit.setText("<html>Credits : <br/>"+argentParcGratuit+" ECTS");
    }
     
    public void payerloyer(Case caseassociee) {
        joueurCourant.credits = joueurCourant.credits-caseassociee.loyer;
        caseassociee.proprietaire.credits = caseassociee.proprietaire.credits+caseassociee.loyer;
        credits1.setText("Crédits : " + tabJoueurs[0].credits + " ECTS"); //pour pas s'embêter on met tt à jour au moins c pas compliqué!
        credits2.setText("Crédits : " + tabJoueurs[1].credits + " ECTS");
        credits3.setText("Crédits : " + tabJoueurs[2].credits + " ECTS");
        credits4.setText("Crédits : " + tabJoueurs[3].credits + " ECTS");
        zone_texte_infos.setText(zone_texte_infos.getText() + "\n" + joueurCourant.nom + ", vous payez " + caseassociee.loyer + "ECTS  à " + caseassociee.proprietaire.nom + "!");
    }
    
    public void téléporterPion(Pion pionAssocie, Case caseOuAller) {
        pionAssocie.caseassociee=caseOuAller;
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
                    else if(joueurCourant==tabJoueurs[1]) {
                        marron2.setText("1");
                    }
                    else if(joueurCourant==tabJoueurs[2]) {
                        marron3.setText("1");
                    }
                    else {
                        marron4.setText("1");
                    }
                }
                else {
                    joueurCourant.PeutConstruire=true;
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
                    else if(joueurCourant==tabJoueurs[1]) {
                        marron2.setText("2");
                    }
                    else if(joueurCourant==tabJoueurs[2]) {
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
                    else if(joueurCourant==tabJoueurs[1]) {
                        bleuciel2.setText("1");
                    }
                    else if(joueurCourant==tabJoueurs[2]) {
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
                    else if(joueurCourant==tabJoueurs[1]) {
                        bleuciel2.setText("2");
                    }
                    else if(joueurCourant==tabJoueurs[2]) {
                        bleuciel3.setText("2");
                    }
                    else {
                        bleuciel4.setText("2");
                    }
                }
                else {
                    caseassociee.changerLoyer();
                    joueurCourant.PeutConstruire=true;
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
                    else if(joueurCourant==tabJoueurs[1]) {
                        bleuciel2.setText("3");
                    }
                    else if(joueurCourant==tabJoueurs[2]) {
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
                    else if(joueurCourant==tabJoueurs[1]) {
                        violet2.setText("1");
                    }
                    else if(joueurCourant==tabJoueurs[2]) {
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
                    else if(joueurCourant==tabJoueurs[1]) {
                        violet2.setText("2");
                    }
                    else if(joueurCourant==tabJoueurs[2]) {
                        violet3.setText("2");
                    }
                    else {
                        violet4.setText("2");
                    }
                }
                else {
                    caseassociee.changerLoyer();
                    joueurCourant.PeutConstruire=true;
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
                    else if(joueurCourant==tabJoueurs[1]) {
                        violet2.setText("3");
                    }
                    else if(joueurCourant==tabJoueurs[2]) {
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
                    else if(joueurCourant==tabJoueurs[1]) {
                        orange2.setText("1");
                    }
                    else if(joueurCourant==tabJoueurs[2]) {
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
                    else if(joueurCourant==tabJoueurs[1]) {
                        orange2.setText("2");
                    }
                    else if(joueurCourant==tabJoueurs[2]) {
                        orange3.setText("2");
                    }
                    else {
                        orange4.setText("2");
                    }
                }
                else {
                    caseassociee.changerLoyer();
                    joueurCourant.PeutConstruire=true;
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
                    else if(joueurCourant==tabJoueurs[1]) {
                        orange2.setText("3");
                    }
                    else if(joueurCourant==tabJoueurs[2]) {
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
                    else if(joueurCourant==tabJoueurs[1]) {
                        rouge2.setText("1");
                    }
                    else if(joueurCourant==tabJoueurs[2]) {
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
                    else if(joueurCourant==tabJoueurs[1]) {
                        rouge2.setText("2");
                    }
                    else if(joueurCourant==tabJoueurs[2]) {
                        rouge3.setText("2");
                    }
                    else {
                        rouge4.setText("2");
                    }
                }
                else {
                    caseassociee.changerLoyer();
                    joueurCourant.PeutConstruire=true;
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
                    else if(joueurCourant==tabJoueurs[1]) {
                        rouge2.setText("3");
                    }
                    else if(joueurCourant==tabJoueurs[2]) {
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
                    else if(joueurCourant==tabJoueurs[1]) {
                        jaune2.setText("1");
                    }
                    else if(joueurCourant==tabJoueurs[2]) {
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
                    else if(joueurCourant==tabJoueurs[1]) {
                        jaune2.setText("2");
                    }
                    else if(joueurCourant==tabJoueurs[2]) {
                        jaune3.setText("2");
                    }
                    else {
                        jaune4.setText("2");
                    }
                }
                else {
                    caseassociee.changerLoyer();
                    joueurCourant.PeutConstruire=true;
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
                    else if(joueurCourant==tabJoueurs[1]) {
                        jaune2.setText("3");
                    }
                    else if(joueurCourant==tabJoueurs[2]) {
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
                    else if(joueurCourant==tabJoueurs[1]) {
                        vert2.setText("1");
                    }
                    else if(joueurCourant==tabJoueurs[2]) {
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
                    else if(joueurCourant==tabJoueurs[1]) {
                        vert2.setText("2");
                    }
                    else if(joueurCourant==tabJoueurs[2]) {
                        vert3.setText("2");
                    }
                    else {
                        vert4.setText("2");
                    }
                }
                else {
                    caseassociee.changerLoyer();
                    joueurCourant.PeutConstruire=true;
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
                    else if(joueurCourant==tabJoueurs[1]) {
                        vert2.setText("3");
                    }
                    else if(joueurCourant==tabJoueurs[2]) {
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
                    else if(joueurCourant==tabJoueurs[1]) {
                        bleufoncé2.setText("1");
                    }
                    else if(joueurCourant==tabJoueurs[2]) {
                        bleufoncé3.setText("1");
                    }
                    else {
                        bleufoncé4.setText("1");
                    }
                }
                else {
                    caseassociee.changerLoyer();
                    joueurCourant.PeutConstruire=true;
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
                    else if(joueurCourant==tabJoueurs[1]) {
                        bleufoncé2.setText("2");
                    }
                    else if(joueurCourant==tabJoueurs[2]) {
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
        plateau = new Plateau();
        plateauJeu.plateauAssocie = plateau;
        plateau.plateaudejeu[0] = new Case(36);
        plateauJeu.plateauAssocie.plateaudejeu[0] = plateau.plateaudejeu[0];
        plateau.plateaudejeu[1] = new Case(0);
        plateauJeu.plateauAssocie.plateaudejeu[1] = plateau.plateaudejeu[1];
        plateau.plateaudejeu[2] = new Case(28);
        plateauJeu.plateauAssocie.plateaudejeu[2] = plateau.plateaudejeu[2];
        plateau.plateaudejeu[3] = new Case(1);
        plateauJeu.plateauAssocie.plateaudejeu[3] = plateau.plateaudejeu[3];
        plateau.plateaudejeu[4] = new Case(34);
        plateauJeu.plateauAssocie.plateaudejeu[4] = plateau.plateaudejeu[4];
        plateau.plateaudejeu[5] = new Case(22);
        plateauJeu.plateauAssocie.plateaudejeu[5] = plateau.plateaudejeu[5];
        plateau.plateaudejeu[6] = new Case(2);
        plateauJeu.plateauAssocie.plateaudejeu[6] = plateau.plateaudejeu[6];
        plateau.plateaudejeu[7] = new Case(31);
        plateauJeu.plateauAssocie.plateaudejeu[7] = plateau.plateaudejeu[7];
        plateau.plateaudejeu[8] = new Case(3);
        plateauJeu.plateauAssocie.plateaudejeu[8] = plateau.plateaudejeu[8];
        plateau.plateaudejeu[9] = new Case(4);
        plateauJeu.plateauAssocie.plateaudejeu[9] = plateau.plateaudejeu[9];
        plateau.plateaudejeu[10] = new Case(37);
        plateauJeu.plateauAssocie.plateaudejeu[10] = plateau.plateaudejeu[10];
        plateau.plateaudejeu[11] = new Case(5);
        plateauJeu.plateauAssocie.plateaudejeu[11] = plateau.plateaudejeu[11];
        plateau.plateaudejeu[12] = new Case(26);
        plateauJeu.plateauAssocie.plateaudejeu[12] = plateau.plateaudejeu[12];
        plateau.plateaudejeu[13] = new Case(6);
        plateauJeu.plateauAssocie.plateaudejeu[13] = plateau.plateaudejeu[13];
        plateau.plateaudejeu[14] = new Case(7);
        plateauJeu.plateauAssocie.plateaudejeu[14] = plateau.plateaudejeu[14];
        plateau.plateaudejeu[15] = new Case(23);
        plateauJeu.plateauAssocie.plateaudejeu[15] = plateau.plateaudejeu[15];
        plateau.plateaudejeu[16] = new Case(8);
        plateauJeu.plateauAssocie.plateaudejeu[16] = plateau.plateaudejeu[16];
        plateau.plateaudejeu[17] = new Case(29);
        plateauJeu.plateauAssocie.plateaudejeu[17] = plateau.plateaudejeu[17];
        plateau.plateaudejeu[18] = new Case(9);
        plateauJeu.plateauAssocie.plateaudejeu[18] = plateau.plateaudejeu[18];
        plateau.plateaudejeu[19] = new Case(10);
        plateauJeu.plateauAssocie.plateaudejeu[19] = plateau.plateaudejeu[19];
        plateau.plateaudejeu[20] = new Case(38);
        plateauJeu.plateauAssocie.plateaudejeu[20] = plateau.plateaudejeu[20];
        plateau.plateaudejeu[21] = new Case(11);
        plateauJeu.plateauAssocie.plateaudejeu[21] = plateau.plateaudejeu[21];
        plateau.plateaudejeu[22] = new Case(32);
        plateauJeu.plateauAssocie.plateaudejeu[22] = plateau.plateaudejeu[22];
        plateau.plateaudejeu[23] = new Case(12);
        plateauJeu.plateauAssocie.plateaudejeu[23] = plateau.plateaudejeu[23];
        plateau.plateaudejeu[24] = new Case(13);
        plateauJeu.plateauAssocie.plateaudejeu[24] = plateau.plateaudejeu[24];
        plateau.plateaudejeu[25] = new Case(24);
        plateauJeu.plateauAssocie.plateaudejeu[25] = plateau.plateaudejeu[25];
        plateau.plateaudejeu[26] = new Case(14);
        plateauJeu.plateauAssocie.plateaudejeu[26] = plateau.plateaudejeu[26];
        plateau.plateaudejeu[27] = new Case(15);
        plateauJeu.plateauAssocie.plateaudejeu[27] = plateau.plateaudejeu[27];
        plateau.plateaudejeu[28] = new Case(27);
        plateauJeu.plateauAssocie.plateaudejeu[28] = plateau.plateaudejeu[28];
        plateau.plateaudejeu[29] = new Case(16);
        plateauJeu.plateauAssocie.plateaudejeu[29] = plateau.plateaudejeu[29];
        plateau.plateaudejeu[30] = new Case(39);
        plateauJeu.plateauAssocie.plateaudejeu[30] = plateau.plateaudejeu[30];
        plateau.plateaudejeu[31] = new Case(17);
        plateauJeu.plateauAssocie.plateaudejeu[31] = plateau.plateaudejeu[31];
        plateau.plateaudejeu[32] = new Case(18);
        plateauJeu.plateauAssocie.plateaudejeu[32] = plateau.plateaudejeu[32];
        plateau.plateaudejeu[33] = new Case(30);
        plateauJeu.plateauAssocie.plateaudejeu[33] = plateau.plateaudejeu[33];
        plateau.plateaudejeu[34] = new Case(19);
        plateauJeu.plateauAssocie.plateaudejeu[34] = plateau.plateaudejeu[34];
        plateau.plateaudejeu[35] = new Case(25);
        plateauJeu.plateauAssocie.plateaudejeu[35] = plateau.plateaudejeu[35];
        plateau.plateaudejeu[36] = new Case(33);
        plateauJeu.plateauAssocie.plateaudejeu[36] = plateau.plateaudejeu[36];
        plateau.plateaudejeu[37] = new Case(20);
        plateauJeu.plateauAssocie.plateaudejeu[37] = plateau.plateaudejeu[37];
        plateau.plateaudejeu[38] = new Case(35);
        plateauJeu.plateauAssocie.plateaudejeu[38] = plateau.plateaudejeu[38];
        plateau.plateaudejeu[39] = new Case(21);
        plateauJeu.plateauAssocie.plateaudejeu[39] = plateau.plateaudejeu[39];
        tabJoueurs[0] = new Joueur();
        tabJoueurs[1] = new Joueur();
        tabJoueurs[2] = new Joueur();
        tabJoueurs[3] = new Joueur();
        de1 = new Des();
        de2 = new Des();
        tabJoueurs[0].pion = new Pion();
        tabJoueurs[1].pion = new Pion();
        tabJoueurs[2].pion = new Pion();
        tabJoueurs[3].pion = new Pion();
        tabJoueurs[0].pion.modele = "Calculette";
        tabJoueurs[1].pion.modele = "Diode";
        tabJoueurs[2].pion.modele = "Erlenmeyer";
        tabJoueurs[3].pion.modele = "Olga";
        tabJoueurs[0].pion.coordX = 0;
        tabJoueurs[0].pion.coordY = 700;
        tabJoueurs[1].pion.coordX = 0;
        tabJoueurs[1].pion.coordY = 750;
        tabJoueurs[2].pion.coordX = 50;
        tabJoueurs[2].pion.coordY = 700;
        tabJoueurs[3].pion.coordX = 50;
        tabJoueurs[3].pion.coordY = 750;
        tabJoueurs[0].pion.taille = 45;
        tabJoueurs[1].pion.taille = 45;
        tabJoueurs[2].pion.taille = 45;
        tabJoueurs[3].pion.taille = 45;
        tabJoueurs[0].pion.caseassociee = plateau.plateaudejeu[0];
        tabJoueurs[1].pion.caseassociee = plateau.plateaudejeu[0];
        tabJoueurs[2].pion.caseassociee = plateau.plateaudejeu[0];
        tabJoueurs[3].pion.caseassociee = plateau.plateaudejeu[0];
        elimine1.setVisible(false);
        elimine2.setVisible(false);
        elimine3.setVisible(false);
        elimine4.setVisible(false);
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
        Salles.setEnabled(true);
        credits1.setVisible(false);
        credits2.setVisible(false);
        credits3.setVisible(false);
        credits4.setVisible(false);
        nbCartes1.setVisible(false);
        nbCartes2.setVisible(false);
        nbCartes3.setVisible(false);
        nbCartes4.setVisible(false);
        tabJoueurs[0].credits = 500;
        tabJoueurs[1].credits = 500;
        tabJoueurs[2].credits = 500;
        tabJoueurs[3].credits = 500;
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
        fin_de_tour1.setVisible(false);
        fin_de_tour2.setVisible(false);
        fin_de_tour3.setVisible(false);
        fin_de_tour4.setVisible(false);
        Autres1.setVisible(false);
        Autres2.setVisible(false);
        Autres3.setVisible(false);
        Autres4.setVisible(false);
        plateauJeu.P1 = tabJoueurs[0].pion;
        plateauJeu.P2 = tabJoueurs[1].pion;
        plateauJeu.P3 = tabJoueurs[2].pion;
        plateauJeu.P4 = tabJoueurs[3].pion;
        paquetCommunaute = new Paquet(true);
        paquetChance = new Paquet(false);
        paquetCommunaute.MAJtab();
        Thread threadcom = new Thread(){
            public void run(){    
                try {
                    plateauJeu.afficherCartes=true;
                    for(int i=0;i<2;i++) {
                        Thread.sleep(100);
                        plateauJeu.orientationcom=plateauJeu.com2;
                        plateauJeu.repaint();
                        Thread.sleep(250);
                        plateauJeu.orientationcom=plateauJeu.com3;
                        plateauJeu.repaint();
                        Thread.sleep(250);
                        plateauJeu.orientationcom=plateauJeu.com4;
                        plateauJeu.repaint();
                        Thread.sleep(250);
                        plateauJeu.orientationcom=plateauJeu.com1;
                        plateauJeu.repaint();
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(Monopoly_EPF.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        };
        threadcom.start();
        paquetChance.MAJtab();
        Thread threadchance = new Thread(){
            public void run(){    
                try {
                    plateauJeu.afficherCartes=true;
                    for(int i=0;i<2;i++) {
                        Thread.sleep(100);
                        plateauJeu.orientationchance=plateauJeu.chance2;
                        plateauJeu.repaint();
                        Thread.sleep(250);
                        plateauJeu.orientationchance=plateauJeu.chance3;
                        plateauJeu.repaint();
                        Thread.sleep(250);
                        plateauJeu.orientationchance=plateauJeu.chance4;
                        plateauJeu.repaint();
                        Thread.sleep(250);
                        plateauJeu.orientationchance=plateauJeu.chance1;
                        plateauJeu.repaint();
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(Monopoly_EPF.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        };
        threadchance.start();
        plateau.plateaudejeu[0].occupant=true;
    }
    
    public void DeplacerPion(Pion pionCourant, Case caseouAller, String typeDeplacement) {
        negatif=false;
        if(carte=="com12" || carte=="chance1" || typeDeplacement=="Case départ") {
            negatif=true;
        }
        pionCourant.taille = 45;
        pionCourant.coordX = pionCourant.caseassociee.coordX;
        pionCourant.coordY = pionCourant.caseassociee.coordY;
        indiceouAller=0;
        indiceouOnEst=0;
        inc=0;
        nbFrames=0;
        for(int i=0;i<plateau.plateaudejeu.length;i++) {
            if(caseouAller==plateau.plateaudejeu[i]) {
                indiceouAller = i;
            }
        }
        for(int i=0;i<plateau.plateaudejeu.length;i++) {
            if(pionCourant.caseassociee==plateau.plateaudejeu[i]) {
                indiceouOnEst = i;
            }
        }
        if(typeDeplacement=="Normal") {
            inc = (de1.valeur+de2.valeur)*66;
            nbFrames = (de1.valeur+de2.valeur)*3;
        }
        else if(typeDeplacement=="Téléportation") {
            if(negatif==true && carte=="com12") {
                int déplacement=0;
                if(joueurCourant.pion.caseassociee==plateau.plateaudejeu[2]) {
                    déplacement=10;
                }
                else if(joueurCourant.pion.caseassociee==plateau.plateaudejeu[17]) {
                    déplacement=25;
                }
                else if(joueurCourant.pion.caseassociee==plateau.plateaudejeu[33]) {
                    déplacement=1;
                }
                if(déplacement==1) {
                    inc = déplacement;
                    nbFrames = déplacement*30;
                }
                else {
                    inc = déplacement*66;
                    nbFrames = déplacement*3;
                }
            }
            else if(negatif==true && carte=="chance1") {
                int déplacement=3;
                inc = déplacement*45;
                nbFrames = déplacement*3;
            }
            else{
                if(carte=="com13" && pionCourant.caseassociee==plateau.plateaudejeu[33]) {
                    inc = (indiceouAller-indiceouOnEst)*10;
                    nbFrames = (indiceouAller-indiceouOnEst)*6;
                }
                else {
                    if(indiceouAller<indiceouOnEst) {
                        inc = ((39-indiceouOnEst)+indiceouAller+1)*66;
                        nbFrames = ((39-indiceouOnEst)+indiceouAller+1)*3;
                    }
                    else {
                        inc = (indiceouAller-indiceouOnEst)*66;
                        nbFrames = (indiceouAller-indiceouOnEst)*3;
                    }
                }
            }
        }
        else if(typeDeplacement=="Case départ") {
            for(int i=0;i<plateau.plateaudejeu.length;i++) {
                if(pionCourant.caseassociee==plateau.plateaudejeu[i]) {
                    indiceouOnEst = i;
                }
            }
            inc=indiceouOnEst*70;
            nbFrames=indiceouOnEst*1;
        }
        currentFrame = 0;
        if(indiceouOnEst==0) {
            pionCourant.coordX = 29;
            pionCourant.coordY=724;
            inc+=5;
        }
        else if(indiceouOnEst==10) {
            pionCourant.coordX = 29;
            pionCourant.coordY=29;
            inc+=5;
        }
        else if(indiceouOnEst==20) {
            pionCourant.coordX=726;
            pionCourant.coordY=29;
            inc+=5;
        }
        else if(indiceouOnEst==30) {
            pionCourant.coordX=726;
            pionCourant.coordY=724;
            inc+=5;
        }
        
        if(indiceouAller==0) {
            inc+=5;
        }
        else if(indiceouAller==10) {
            inc+=5;
        }
        else if(indiceouAller==20) {
            inc+=5;
        }
        else if(indiceouAller==30) {
            inc+=5;
        }
        
        
        if(indiceouOnEst>=0 && indiceouAller>10 && indiceouAller<=20) {
            inc+=10;
        }
        else if(indiceouOnEst>=0 && indiceouAller>20 && indiceouAller<=30) {
            inc+=20;
        }
        else if(indiceouOnEst>=0 && indiceouAller>30 && indiceouAller<=39) {
            inc+=30;
        }
        else if(indiceouOnEst>=0 && indiceouAller>0 && indiceouAller<=indiceouOnEst) {
            inc+=40;
        }
        if(indiceouOnEst>=10 && indiceouAller>20 && indiceouAller<=30) {
            inc+=10;
        }
        else if(indiceouOnEst>=10 && indiceouAller>30 && indiceouAller<=39) {
            inc+=20;
        }
        else if(indiceouOnEst>=10 && indiceouAller>0 && indiceouAller<=10) {
            inc+=30;
        }
        else if(indiceouOnEst>=10 && indiceouAller>10 && indiceouAller<=indiceouOnEst) {
            inc+=40;
        }
        if(indiceouOnEst>=20 && indiceouAller>30 && indiceouAller<=39) {
            inc+=10;
        }
        else if(indiceouOnEst>=20 && indiceouAller>0 && indiceouAller<=10) {
            inc+=20;
        }
        else if(indiceouOnEst>=20 && indiceouAller>10 && indiceouAller<=20) {
            inc+=30;
        }
        else if(indiceouOnEst>=20 && indiceouAller>20 && indiceouAller<=indiceouOnEst) {
            inc+=40;
        }
        if(indiceouOnEst>=30 && indiceouAller>0 && indiceouAller<=10) {
            inc+=10;
        }
        else if(indiceouOnEst>=30 && indiceouAller>10 && indiceouAller<=20) {
            inc+=20;
        }
        else if(indiceouOnEst>=30 && indiceouAller>20 && indiceouAller<=30) {
            inc+=30;
        }
        else if(indiceouOnEst>=30 && indiceouAller>30 && indiceouAller<=indiceouOnEst) {
            inc+=40;
        }
        ActionListener tache_recurrente = new ActionListener() {

            public void actionPerformed(ActionEvent e1) {
                if(currentFrame < nbFrames && negatif==false && mini==false) {
                    if(pionCourant.coordY<=798 && pionCourant.coordY>=29 && pionCourant.coordX<=29) {
                        if(pionCourant==tabJoueurs[0].pion) {
                            plateauJeu.orientation1 = plateauJeu.gauche1;
                        }
                        else if(pionCourant==tabJoueurs[1].pion) {
                            plateauJeu.orientation2 = plateauJeu.gauche2;
                        }
                        else if(pionCourant==tabJoueurs[2].pion) {
                            plateauJeu.orientation3 = plateauJeu.gauche3;
                        }
                        else if(pionCourant==tabJoueurs[3].pion) {
                            plateauJeu.orientation4 = plateauJeu.gauche4;
                        }
                        pionCourant.coordY -= inc / nbFrames;
                        plateauJeu.repaint();
                        currentFrame++;
                    }
                    else if(pionCourant.coordX>=0 && pionCourant.coordX<=726 && pionCourant.coordY<=29) {
                        if(pionCourant==tabJoueurs[0].pion) {
                            plateauJeu.orientation1 = plateauJeu.haut1;
                        }
                        else if(pionCourant==tabJoueurs[1].pion) {
                            plateauJeu.orientation2 = plateauJeu.haut2;
                        }
                        else if(pionCourant==tabJoueurs[2].pion) {
                            plateauJeu.orientation3 = plateauJeu.haut3;
                        }
                        else if(pionCourant==tabJoueurs[3].pion) {
                            plateauJeu.orientation4 = plateauJeu.haut4;
                        }
                        pionCourant.coordX += inc / nbFrames;
                        plateauJeu.repaint();
                        currentFrame++;
                    }
                    else if(pionCourant.coordY>=0 && pionCourant.coordY<=724 && pionCourant.coordX>=726) {
                        if(pionCourant==tabJoueurs[0].pion) {
                            plateauJeu.orientation1 = plateauJeu.droite1;
                        }
                        else if(pionCourant==tabJoueurs[1].pion) {
                            plateauJeu.orientation2 = plateauJeu.droite2;
                        }
                        else if(pionCourant==tabJoueurs[2].pion) {
                            plateauJeu.orientation3 = plateauJeu.droite3;
                        }
                        else if(pionCourant==tabJoueurs[3].pion) {
                            plateauJeu.orientation4 = plateauJeu.droite4;
                        }
                        pionCourant.coordY += inc / nbFrames;
                        plateauJeu.repaint();
                        currentFrame++;
                    }
                    else if(pionCourant.coordX>=29 && pionCourant.coordX<=800 && pionCourant.coordY>=724) {
                        if(pionCourant==tabJoueurs[0].pion) {
                            plateauJeu.orientation1 = plateauJeu.bas1;
                        }
                        else if(pionCourant==tabJoueurs[1].pion) {
                            plateauJeu.orientation2 = plateauJeu.bas2;
                        }
                        else if(pionCourant==tabJoueurs[2].pion) {
                            plateauJeu.orientation3 = plateauJeu.bas3;
                        }
                        else if(pionCourant==tabJoueurs[3].pion) {
                            plateauJeu.orientation4 = plateauJeu.bas4;
                        }
                        pionCourant.coordX -= inc / nbFrames;
                        plateauJeu.repaint();
                        currentFrame++;
                    }
                }
                else if(currentFrame < nbFrames && negatif==true && mini==false) {
                    if(pionCourant.coordY<=726 && pionCourant.coordY>=0 && pionCourant.coordX<=29) {
                        if(typeDeplacement!="Case départ") {
                            if(joueurCourant==tabJoueurs[0]) {
                                plateauJeu.orientation1 = plateauJeu.gauche1;
                            }
                            else if(joueurCourant==tabJoueurs[1]) {
                                plateauJeu.orientation2 = plateauJeu.gauche2;
                            }
                            else if(joueurCourant==tabJoueurs[2]) {
                                plateauJeu.orientation3 = plateauJeu.gauche3;
                            }
                            else if(joueurCourant==tabJoueurs[3]) {
                                plateauJeu.orientation4 = plateauJeu.gauche4;
                            }
                        }
                        else {
                            if(joueurCourant==tabJoueurs[0]) {
                                plateauJeu.orientation1 = plateauJeu.gauche1;
                            }
                            else if(joueurCourant==tabJoueurs[1]) {
                                plateauJeu.orientation2 = plateauJeu.gauche2;
                            }
                            else if(joueurCourant==tabJoueurs[2]) {
                                plateauJeu.orientation3 = plateauJeu.gauche3;
                            }
                            else if(joueurCourant==tabJoueurs[3]) {
                                plateauJeu.orientation4 = plateauJeu.gauche4;
                            }
                        }
                        pionCourant.coordY += inc / nbFrames;
                        plateauJeu.repaint();
                        currentFrame++;
                    }
                    else if(pionCourant.coordX>=29 && pionCourant.coordX<=800 && pionCourant.coordY<=29) {
                        if(typeDeplacement!="Case départ") {
                            if(joueurCourant==tabJoueurs[0]) {
                                plateauJeu.orientation1 = plateauJeu.haut1;
                            }
                            else if(joueurCourant==tabJoueurs[1]) {
                                plateauJeu.orientation2 = plateauJeu.haut2;
                            }
                            else if(joueurCourant==tabJoueurs[2]) {
                                plateauJeu.orientation3 = plateauJeu.haut3;
                            }
                            else if(joueurCourant==tabJoueurs[3]) {
                                plateauJeu.orientation4 = plateauJeu.haut4;
                            }
                        }
                        else {
                            if(joueurCourant==tabJoueurs[0]) {
                                plateauJeu.orientation1 = plateauJeu.haut1;
                            }
                            else if(joueurCourant==tabJoueurs[1]) {
                                plateauJeu.orientation2 = plateauJeu.haut2;
                            }
                            else if(joueurCourant==tabJoueurs[2]) {
                                plateauJeu.orientation3 = plateauJeu.haut3;
                            }
                            else if(joueurCourant==tabJoueurs[3]) {
                                plateauJeu.orientation4 = plateauJeu.haut4;
                            }
                        }
                        pionCourant.coordX -= inc / nbFrames;
                        if(pionCourant.coordX<=28 && typeDeplacement=="Case départ") {
                            pionCourant.coordX=28;
                        }
                        plateauJeu.repaint();
                        currentFrame++;
                    }
                    else if(pionCourant.coordY>=29 && pionCourant.coordY<=798 && pionCourant.coordX>=726) {
                        if(typeDeplacement!="Case départ") {
                            if(joueurCourant==tabJoueurs[0]) {
                                plateauJeu.orientation1 = plateauJeu.droite1;
                            }
                            else if(joueurCourant==tabJoueurs[1]) {
                                plateauJeu.orientation2 = plateauJeu.droite2;
                            }
                            else if(joueurCourant==tabJoueurs[2]) {
                                plateauJeu.orientation3 = plateauJeu.droite3;
                            }
                            else if(joueurCourant==tabJoueurs[3]) {
                                plateauJeu.orientation4 = plateauJeu.droite4;
                            }
                        }
                        else {
                            if(joueurCourant==tabJoueurs[0]) {
                                plateauJeu.orientation1 = plateauJeu.droite1;
                            }
                            else if(joueurCourant==tabJoueurs[1]) {
                                plateauJeu.orientation2 = plateauJeu.droite2;
                            }
                            else if(joueurCourant==tabJoueurs[2]) {
                                plateauJeu.orientation3 = plateauJeu.droite3;
                            }
                            else if(joueurCourant==tabJoueurs[3]) {
                                plateauJeu.orientation4 = plateauJeu.droite4;
                            }
                        }
                        pionCourant.coordY -= inc / nbFrames;
                        if(pionCourant.coordY<=28 && typeDeplacement=="Case départ") {
                            pionCourant.coordY=28;
                        }
                        plateauJeu.repaint();
                        currentFrame++;
                    }
                    else if(pionCourant.coordX>=0 && pionCourant.coordX<=726 && pionCourant.coordY>=726) {
                        if(typeDeplacement!="Case départ") {
                            if(joueurCourant==tabJoueurs[0]) {
                                plateauJeu.orientation1 = plateauJeu.bas1;
                            }
                            else if(joueurCourant==tabJoueurs[1]) {
                                plateauJeu.orientation2 = plateauJeu.bas2;
                            }
                            else if(joueurCourant==tabJoueurs[2]) {
                                plateauJeu.orientation3 = plateauJeu.bas3;
                            }
                            else if(joueurCourant==tabJoueurs[3]) {
                                plateauJeu.orientation4 = plateauJeu.bas4;
                            }
                        }
                        else {
                            if(joueurCourant==tabJoueurs[0]) {
                                plateauJeu.orientation1 = plateauJeu.bas1;
                            }
                            else if(joueurCourant==tabJoueurs[1]) {
                                plateauJeu.orientation2 = plateauJeu.bas2;
                            }
                            else if(joueurCourant==tabJoueurs[2]) {
                                plateauJeu.orientation3 = plateauJeu.bas3;
                            }
                            else if(joueurCourant==tabJoueurs[3]) {
                                plateauJeu.orientation4 = plateauJeu.bas4;
                            }
                        }
                        pionCourant.coordX += inc / nbFrames;
                        if(pionCourant.coordX>=727 && typeDeplacement=="Case départ") {
                            pionCourant.coordX=727;
                        }
                        plateauJeu.repaint();
                        currentFrame++;
                    }
                }
                else {
                    if(mini==true) {
                        mini=false;
                    }
                    else {
                        pionCourant.coordX=caseouAller.coordX;
                        pionCourant.coordY=caseouAller.coordY;
                    }
                    if(indiceouAller>0 && indiceouAller<10){
                        if(joueurCourant==tabJoueurs[0]){
                            plateauJeu.orientation1=plateauJeu.gauche1;
                        }
                        else if(joueurCourant==tabJoueurs[1]){
                            plateauJeu.orientation2=plateauJeu.gauche2;
                        }
                        else if(joueurCourant==tabJoueurs[2]){
                            plateauJeu.orientation3=plateauJeu.gauche3;
                        }
                        else if(joueurCourant==tabJoueurs[3]){
                            plateauJeu.orientation4=plateauJeu.gauche4;
                        }
                    }
                    else if(indiceouAller>10 && indiceouAller<20){
                        if(joueurCourant==tabJoueurs[0]){
                            plateauJeu.orientation1=plateauJeu.haut1;
                        }
                        else if(joueurCourant==tabJoueurs[1]){
                            plateauJeu.orientation2=plateauJeu.haut2;
                        }
                        else if(joueurCourant==tabJoueurs[2]){
                            plateauJeu.orientation3=plateauJeu.haut3;
                        }
                        else if(joueurCourant==tabJoueurs[3]){
                            plateauJeu.orientation4=plateauJeu.haut4;
                        }
                    }
                    else if(indiceouAller>20 && indiceouAller<30){
                        if(joueurCourant==tabJoueurs[0]){
                            plateauJeu.orientation1=plateauJeu.droite1;
                        }
                        else if(joueurCourant==tabJoueurs[1]){
                            plateauJeu.orientation2=plateauJeu.droite2;
                        }
                        else if(joueurCourant==tabJoueurs[2]){
                            plateauJeu.orientation3=plateauJeu.droite3;
                        }
                        else if(joueurCourant==tabJoueurs[3]){
                            plateauJeu.orientation4=plateauJeu.droite4;
                        }
                    }
                    else if(indiceouAller>30 && indiceouAller<=39){
                        if(joueurCourant==tabJoueurs[0]){
                            plateauJeu.orientation1=plateauJeu.bas1;
                        }
                        else if(joueurCourant==tabJoueurs[1]){
                            plateauJeu.orientation2=plateauJeu.bas2;
                        }
                        else if(joueurCourant==tabJoueurs[2]){
                            plateauJeu.orientation3=plateauJeu.bas3;
                        }
                        else if(joueurCourant==tabJoueurs[3]){
                            plateauJeu.orientation4=plateauJeu.bas4;
                        }
                    }
                    else if(indiceouAller==10) {
                        if(joueurCourant.prison==false) {
                            if(joueurCourant==tabJoueurs[0]) {
                                pionCourant.coordX=61;
                                pionCourant.coordY=2;
                                pionCourant.taille=24;
                                plateauJeu.orientation1=plateauJeu.haut1;
                            }
                            else if(joueurCourant==tabJoueurs[1]) {
                                pionCourant.coordX=18;
                                pionCourant.coordY=2;
                                pionCourant.taille=24;
                                plateauJeu.orientation2=plateauJeu.haut2;
                            }
                            else if(joueurCourant==tabJoueurs[2]) {
                                pionCourant.coordX=2;
                                pionCourant.coordY=20;
                                pionCourant.taille=24;
                                plateauJeu.orientation3=plateauJeu.gauche3;
                            }
                            else if(joueurCourant==tabJoueurs[3]) {
                                pionCourant.coordX=2;
                                pionCourant.coordY=63;
                                pionCourant.taille=24;
                                plateauJeu.orientation4=plateauJeu.gauche4;
                            }
                        }
                        else if(joueurCourant.prison==true) {
                            if(joueurCourant==tabJoueurs[0]) {
                                joueurCourant.pion.coordX=31;
                                joueurCourant.pion.coordY=31;
                                joueurCourant.pion.taille=35;
                                plateauJeu.orientation1=plateauJeu.gauche1;
                            }
                            else if(joueurCourant==tabJoueurs[1]) {
                                joueurCourant.pion.coordX=31;
                                joueurCourant.pion.coordY=68;
                                joueurCourant.pion.taille=35;
                                plateauJeu.orientation2=plateauJeu.gauche2;
                            }
                            else if(joueurCourant==tabJoueurs[2]) {
                                joueurCourant.pion.coordX=68;
                                joueurCourant.pion.coordY=31;
                                joueurCourant.pion.taille=35;
                                plateauJeu.orientation3=plateauJeu.gauche3;
                            }
                            else if(joueurCourant==tabJoueurs[3]) {
                                joueurCourant.pion.coordX=68;
                                joueurCourant.pion.coordY=68;
                                joueurCourant.pion.taille=35;
                                plateauJeu.orientation4=plateauJeu.gauche4;
                            }
                        }
                    }
                    else if(indiceouAller==0) {
                        if(joueurCourant==tabJoueurs[0]) {
                            plateauJeu.orientation1=plateauJeu.gauche1;
                        }
                        else if(joueurCourant==tabJoueurs[1]) {
                            plateauJeu.orientation2=plateauJeu.gauche2;
                        }
                        else if(joueurCourant==tabJoueurs[2]) {
                            plateauJeu.orientation3=plateauJeu.gauche3;
                        }
                        else if(joueurCourant==tabJoueurs[3]) {
                            plateauJeu.orientation4=plateauJeu.gauche4;
                        }
                    }
                    else if(indiceouAller==20) {
                        if(joueurCourant==tabJoueurs[0]) {
                            plateauJeu.orientation1=plateauJeu.droite1;
                        }
                        else if(joueurCourant==tabJoueurs[1]) {
                            plateauJeu.orientation2=plateauJeu.droite2;
                        }
                        else if(joueurCourant==tabJoueurs[2]) {
                            plateauJeu.orientation3=plateauJeu.droite3;
                        }
                        else if(joueurCourant==tabJoueurs[3]) {
                            plateauJeu.orientation4=plateauJeu.droite4;
                        }
                    }
                    if(caseouAller.occupant==true) {
                        if(caseouAller.idCase!=36 && caseouAller.idCase!=37 && caseouAller.idCase!=38 && caseouAller.idCase!=39) {
                            if(indiceouAller>0 && indiceouAller<10){
                                for(int i=0;i<tabJoueurs.length;i++) {
                                    if(tabJoueurs[i].pion.caseassociee==caseouAller && tabJoueurs[i].nom!=null) {
                                        if(i==0){
                                            tabJoueurs[0].pion.coordX=caseouAller.coordX-3;
                                            tabJoueurs[0].pion.coordY=caseouAller.coordY+1;
                                            tabJoueurs[0].pion.taille=20;
                                        }
                                        else if(i==1) {
                                            tabJoueurs[1].pion.coordX=caseouAller.coordX-3;
                                            tabJoueurs[1].pion.coordY=caseouAller.coordY+31;
                                            tabJoueurs[1].pion.taille=20;
                                        }
                                        else if(i==2) {
                                            tabJoueurs[2].pion.coordX=caseouAller.coordX+22;
                                            tabJoueurs[2].pion.coordY=caseouAller.coordY+1;
                                            tabJoueurs[2].pion.taille=20;
                                        }
                                        else if(i==3) {
                                            tabJoueurs[3].pion.coordX=caseouAller.coordX+22;
                                            tabJoueurs[3].pion.coordY=caseouAller.coordY+31;
                                            tabJoueurs[3].pion.taille=20;
                                        }
                                    }
                                }
                            }
                            else if(indiceouAller>10 && indiceouAller<20){
                                for(int i=0;i<tabJoueurs.length;i++) {
                                    if(tabJoueurs[i].pion.caseassociee==caseouAller && tabJoueurs[i].nom!=null) {
                                        if(i==0){
                                            tabJoueurs[0].pion.coordX=caseouAller.coordX+25;
                                            tabJoueurs[0].pion.coordY=caseouAller.coordY-3;
                                            tabJoueurs[0].pion.taille=20;
                                        }
                                        else if(i==1) {
                                            tabJoueurs[1].pion.coordX=caseouAller.coordX;
                                            tabJoueurs[1].pion.coordY=caseouAller.coordY-3;
                                            tabJoueurs[1].pion.taille=20;
                                        }
                                        else if(i==2) {
                                            tabJoueurs[2].pion.coordX=caseouAller.coordX+25;
                                            tabJoueurs[2].pion.coordY=caseouAller.coordY+20;
                                            tabJoueurs[2].pion.taille=20;
                                        }
                                        else if(i==3) {
                                            tabJoueurs[3].pion.coordX=caseouAller.coordX;
                                            tabJoueurs[3].pion.coordY=caseouAller.coordY+20;
                                            tabJoueurs[3].pion.taille=20;
                                        }
                                    }
                                }
                            }
                            else if(indiceouAller>20 && indiceouAller<30){
                                for(int i=0;i<tabJoueurs.length;i++) {
                                    if(tabJoueurs[i].pion.caseassociee==caseouAller && tabJoueurs[i].nom!=null) {
                                        if(i==0){
                                            tabJoueurs[0].pion.coordX=caseouAller.coordX+27;
                                            tabJoueurs[0].pion.coordY=caseouAller.coordY+31;
                                            tabJoueurs[0].pion.taille=20;
                                        }
                                        else if(i==1) {
                                            tabJoueurs[1].pion.coordX=caseouAller.coordX+27;
                                            tabJoueurs[1].pion.coordY=caseouAller.coordY+1;
                                            tabJoueurs[1].pion.taille=20;
                                        }
                                        else if(i==2) {
                                            tabJoueurs[2].pion.coordX=caseouAller.coordX+5;
                                            tabJoueurs[2].pion.coordY=caseouAller.coordY+31;
                                            tabJoueurs[2].pion.taille=20;
                                        }
                                        else if(i==3) {
                                            tabJoueurs[3].pion.coordX=caseouAller.coordX+5;
                                            tabJoueurs[3].pion.coordY=caseouAller.coordY+1;
                                            tabJoueurs[3].pion.taille=20;
                                        }
                                    }
                                }
                            }
                            else if(indiceouAller>30 && indiceouAller<=39){
                                for(int i=0;i<tabJoueurs.length;i++) {
                                    if(tabJoueurs[i].pion.caseassociee==caseouAller && tabJoueurs[i].nom!=null) {
                                        if(i==0){
                                            tabJoueurs[0].pion.coordX=caseouAller.coordX;
                                            tabJoueurs[0].pion.coordY=caseouAller.coordY+25;
                                            tabJoueurs[0].pion.taille=20;
                                        }
                                        else if(i==1) {
                                            tabJoueurs[1].pion.coordX=caseouAller.coordX+25;
                                            tabJoueurs[1].pion.coordY=caseouAller.coordY+25;
                                            tabJoueurs[1].pion.taille=20;
                                        }
                                        else if(i==2) {
                                            tabJoueurs[2].pion.coordX=caseouAller.coordX;
                                            tabJoueurs[2].pion.coordY=caseouAller.coordY+4;
                                            tabJoueurs[2].pion.taille=20;
                                        }
                                        else if(i==3) {
                                            tabJoueurs[3].pion.coordX=caseouAller.coordX+25;
                                            tabJoueurs[3].pion.coordY=caseouAller.coordY+4;
                                            tabJoueurs[3].pion.taille=20;
                                        }
                                    }
                                }
                            }
                        }
                        else if(caseouAller.idCase==36) {
                            for(int i=0;i<tabJoueurs.length;i++) {
                                    if(tabJoueurs[i].pion.caseassociee==caseouAller && tabJoueurs[i].nom!=null) {
                                        if(i==0){
                                            tabJoueurs[0].pion.coordX=0;
                                            tabJoueurs[0].pion.coordY=700;
                                            plateauJeu.orientation1=plateauJeu.gauche1;
                                        }
                                        else if(i==1) {
                                            tabJoueurs[1].pion.coordX=0;
                                            tabJoueurs[1].pion.coordY=750;
                                            plateauJeu.orientation2=plateauJeu.gauche2;
                                        }
                                        else if(i==2) {
                                            tabJoueurs[2].pion.coordX=50;
                                            tabJoueurs[2].pion.coordY=700;
                                            plateauJeu.orientation3=plateauJeu.gauche3;
                                        }
                                        else if(i==3) {
                                            tabJoueurs[3].pion.coordX=50;
                                            tabJoueurs[3].pion.coordY=750;
                                            plateauJeu.orientation4=plateauJeu.gauche4;
                                        }
                                    }
                                }
                        }
                        else if(caseouAller.idCase==38) {
                            for(int i=0;i<tabJoueurs.length;i++) {
                                    if(tabJoueurs[i].pion.caseassociee==caseouAller && tabJoueurs[i].nom!=null) {
                                        if(i==0){
                                            tabJoueurs[0].pion.coordX=755;
                                            tabJoueurs[0].pion.coordY=55;
                                            plateauJeu.orientation1=plateauJeu.droite1;
                                        }
                                        else if(i==1) {
                                            tabJoueurs[1].pion.coordX=755;
                                            tabJoueurs[1].pion.coordY=5;
                                            plateauJeu.orientation2=plateauJeu.droite2;
                                        }
                                        else if(i==2) {
                                            tabJoueurs[2].pion.coordX=705;
                                            tabJoueurs[2].pion.coordY=55;
                                            plateauJeu.orientation3=plateauJeu.droite3;
                                        }
                                        else if(i==3) {
                                            tabJoueurs[3].pion.coordX=705;
                                            tabJoueurs[3].pion.coordY=5;
                                            plateauJeu.orientation4=plateauJeu.droite4;
                                        }
                                    }
                                }
                        }
                    }
                    if(compteurDouble+1==3 && de1.valeur==de2.valeur) {
                        tripledouble=true;
                        joueurCourant.prison=true;
                        zone_texte_infos.setText(zone_texte_infos.getText() + " vous réalisez un troisième double d'affilée ce qui vous envoie aux rattrapages. Bonne chance pour les kholles!");
                        compteurDouble++;
                        int ouonest=0;
                        for(int i=0;i<plateau.plateaudejeu.length;i++){
                            if(pionCourant.caseassociee==plateau.plateaudejeu[i]) {
                                ouonest=i;
                            }
                        }
                        if(10<ouonest) {
                            déplacement = (39-ouonest)+10+1; //10 étant le nouvel indiceouAller (prison)
                        }
                        else {
                            déplacement = 10-ouonest;
                        }
                        if(joueurCourant==tabJoueurs[0]) {
                            Thread thread1 = new Thread(){
                                public void run(){
                                    try {
                                        Thread.sleep(1000);
                                        for(int i=0;i<2;i++) {
                                            joueurCourant.pion.taille-=5;
                                            plateauJeu.orientation1=plateauJeu.haut1;
                                            plateauJeu.repaint();
                                            Thread.sleep(100);
                                            joueurCourant.pion.taille-=5;
                                            plateauJeu.orientation1=plateauJeu.droite1;
                                            plateauJeu.repaint();
                                            Thread.sleep(100);
                                            joueurCourant.pion.taille-=5;
                                            plateauJeu.orientation1=plateauJeu.bas1;
                                            plateauJeu.repaint();
                                            Thread.sleep(100);
                                            joueurCourant.pion.taille-=5;
                                            plateauJeu.orientation1=plateauJeu.gauche1;
                                            plateauJeu.repaint();
                                            Thread.sleep(100);
                                        }
                                    } catch (InterruptedException ex) {
                                    Logger.getLogger(Monopoly_EPF.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    joueurCourant.pion.coordX=31;
                                    joueurCourant.pion.coordY=31;
                                    joueurCourant.pion.taille=35;
                                    plateauJeu.orientation1=plateauJeu.gauche1;
                                    deplacerPion(joueurCourant.pion,déplacement,joueurCourant.pion.caseassociee);
                                    if(joueurCourant.credits<0) {
                                        eliminationJoueur();
                                    }
                                    else {
                                        changerJoueur();
                                        compteurDouble=0;
                                    }
                                    while(joueurCourant.droitdejouer==false) {
                                        joueurCourant.droitdejouer=true;
                                        changerJoueur();
                                        compteurDouble=0;
                                    }
                                    if(joueurCourant.compteurTourPrison==3) {
                                        joueurCourant.compteurTourPrison=0;
                                        joueurCourant.prison=false;
                                        joueurCourant.credits=joueurCourant.credits-50;
                                        if(joueurCourant==tabJoueurs[0]) {
                                            tabJoueurs[0].pion.coordX=61;
                                            tabJoueurs[0].pion.coordY=2;
                                            tabJoueurs[0].pion.taille=24;
                                            plateauJeu.orientation1=plateauJeu.haut1;
                                            plateauJeu.repaint();
                                            credits1.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                        }
                                        else if(joueurCourant==tabJoueurs[1]) {
                                            tabJoueurs[1].pion.coordX=18;
                                            tabJoueurs[1].pion.coordY=2;
                                            tabJoueurs[1].pion.taille=24;
                                            plateauJeu.orientation2=plateauJeu.haut2;
                                            plateauJeu.repaint();
                                            credits2.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                        }
                                        else if(joueurCourant==tabJoueurs[2]) {
                                            tabJoueurs[2].pion.coordX=2;
                                            tabJoueurs[2].pion.coordY=20;
                                            tabJoueurs[2].pion.taille=24;
                                            plateauJeu.orientation3=plateauJeu.gauche3;
                                            plateauJeu.repaint();
                                            credits3.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                        }
                                        else if(joueurCourant==tabJoueurs[3]) {
                                            tabJoueurs[3].pion.coordX=2;
                                            tabJoueurs[3].pion.coordY=63;
                                            tabJoueurs[3].pion.taille=24;
                                            plateauJeu.orientation4=plateauJeu.gauche4;
                                            plateauJeu.repaint();
                                            credits4.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                        }
                                        zone_texte_infos.setText(zone_texte_infos.getText() + "\n" + joueurCourant.nom + " vous venez de rater vos 3 essais aux rattrapages, vous payez donc 50 crédits et réintégrez le parcours scolaire!");
                                    }
                                    if(joueurCourant==tabJoueurs[0] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                        ValiderMatiere1.setVisible(true);
                                    }
                                    else if(joueurCourant==tabJoueurs[1] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                        ValiderMatiere2.setVisible(true);
                                    }
                                    else if(joueurCourant==tabJoueurs[2] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                        ValiderMatiere3.setVisible(true);
                                    }
                                    else if(joueurCourant==tabJoueurs[3] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                        ValiderMatiere4.setVisible(true);
                                    }
                                    if(joueurCourant==tabJoueurs[0] && joueurCourant.prison==true) {
                                        joueurCourant.compteurTourPrison++;
                                        if(joueurCourant.nbCartes!=0) {
                                            Libération1.setVisible(true);
                                        }
                                        PayerPrison1.setVisible(true);
                                    }
                                    else if(joueurCourant==tabJoueurs[1] && joueurCourant.prison==true) {
                                        joueurCourant.compteurTourPrison++;
                                        if(joueurCourant.nbCartes!=0) {
                                            Libération2.setVisible(true);
                                        }
                                        PayerPrison2.setVisible(true);
                                    }
                                    else if(joueurCourant==tabJoueurs[2] && joueurCourant.prison==true) {
                                        joueurCourant.compteurTourPrison++;
                                        if(joueurCourant.nbCartes!=0) {
                                            Libération3.setVisible(true);
                                        }
                                        PayerPrison3.setVisible(true);
                                    }
                                    else if(joueurCourant==tabJoueurs[3] && joueurCourant.prison==true) {
                                        joueurCourant.compteurTourPrison++;
                                        if(joueurCourant.nbCartes!=0) {
                                            Libération4.setVisible(true);
                                        }
                                        PayerPrison4.setVisible(true);
                                    }
                                    if(elim!=true) {
                                        Lancer_des.setEnabled(true);
                                    }
                                    if(joueurCourant.prison!=true && joueurCourant.nom!=null && compteurDouble==0) {
                                        zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon tirez les dés!");
                                    }
                                    else if(joueurCourant.prison!=true && joueurCourant.nom!=null) {
                                        zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, vous pouvez tirez les dés!");
                                    }
                                    else {
                                        if(joueurCourant.nom!=null) {
                                            zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon essayer de quitter les rattrapages (vous pouvez payer 50 crédits, jouer une carte libération de rattrapages si vous en possédez une ou sinon tenter de faire un double)!");
                                        }
                                    }
                                    plateauJeu.repaint();
                                }
                            };
                        thread1.start();
                    }
                else if(joueurCourant==tabJoueurs[1]) {
                    Thread thread2 = new Thread(){
                        public void run(){
                            try {
                                Thread.sleep(1000);
                                for(int i=0;i<2;i++) {
                                    joueurCourant.pion.taille-=5;
                                    plateauJeu.orientation2=plateauJeu.haut2;
                                    plateauJeu.repaint();
                                    Thread.sleep(100);
                                    joueurCourant.pion.taille-=5;
                                    plateauJeu.orientation2=plateauJeu.droite2;
                                    plateauJeu.repaint();
                                    Thread.sleep(100);
                                    joueurCourant.pion.taille-=5;
                                    plateauJeu.orientation2=plateauJeu.bas2;
                                    plateauJeu.repaint();
                                    Thread.sleep(100);
                                    joueurCourant.pion.taille-=5;
                                    plateauJeu.orientation2=plateauJeu.gauche2;
                                    plateauJeu.repaint();
                                    Thread.sleep(100);
                                }
                            } catch (InterruptedException ex) {
                                Logger.getLogger(Monopoly_EPF.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            joueurCourant.pion.coordX=31;
                            joueurCourant.pion.coordY=68;
                            joueurCourant.pion.taille=35;
                            plateauJeu.orientation2=plateauJeu.gauche2;
                            deplacerPion(joueurCourant.pion,déplacement,joueurCourant.pion.caseassociee);
                            if(joueurCourant.credits<0) {
                                eliminationJoueur();
                            }
                            else {
                                changerJoueur();
                                compteurDouble=0;
                            }
                            while(joueurCourant.droitdejouer==false) {
                                joueurCourant.droitdejouer=true;
                                changerJoueur();
                                compteurDouble=0;
                            }
                            if(joueurCourant.compteurTourPrison==3) {
                                joueurCourant.compteurTourPrison=0;
                                joueurCourant.prison=false;
                                joueurCourant.credits=joueurCourant.credits-50;
                                if(joueurCourant==tabJoueurs[0]) {
                                    tabJoueurs[0].pion.coordX=61;
                                    tabJoueurs[0].pion.coordY=2;
                                    tabJoueurs[0].pion.taille=24;
                                    plateauJeu.orientation1=plateauJeu.haut1;
                                    plateauJeu.repaint();
                                    credits1.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                else if(joueurCourant==tabJoueurs[1]) {
                                    tabJoueurs[1].pion.coordX=18;
                                    tabJoueurs[1].pion.coordY=2;
                                    tabJoueurs[1].pion.taille=24;
                                    plateauJeu.orientation2=plateauJeu.haut2;
                                    plateauJeu.repaint();
                                    credits2.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                else if(joueurCourant==tabJoueurs[2]) {
                                    tabJoueurs[2].pion.coordX=2;
                                    tabJoueurs[2].pion.coordY=20;
                                    tabJoueurs[2].pion.taille=24;
                                    plateauJeu.orientation3=plateauJeu.gauche3;
                                    plateauJeu.repaint();
                                    credits3.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                else if(joueurCourant==tabJoueurs[3]) {
                                    tabJoueurs[3].pion.coordX=2;
                                    tabJoueurs[3].pion.coordY=63;
                                    tabJoueurs[3].pion.taille=24;
                                    plateauJeu.orientation4=plateauJeu.gauche4;
                                    plateauJeu.repaint();
                                    credits4.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n" + joueurCourant.nom + " vous venez de rater vos 3 essais aux rattrapages, vous payez donc 50 crédits et réintégrez le parcours scolaire!");
                            }
                            if(joueurCourant==tabJoueurs[0] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere1.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[1] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere2.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[2] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere3.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[3] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere4.setVisible(true);
                            }
                            if(joueurCourant==tabJoueurs[0] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération1.setVisible(true);
                                }
                                PayerPrison1.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[1] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération2.setVisible(true);
                                }
                                PayerPrison2.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[2] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération3.setVisible(true);
                                }
                                PayerPrison3.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[3] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération4.setVisible(true);
                                }
                                PayerPrison4.setVisible(true);
                            }
                            if(elim!=true) {
                                Lancer_des.setEnabled(true);
                            }
                            if(joueurCourant.prison!=true && joueurCourant.nom!=null && compteurDouble==0) {
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon tirez les dés!");
                            }
                            else if(joueurCourant.prison!=true && joueurCourant.nom!=null) {
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, vous pouvez tirez les dés!");
                            }
                            else {
                                if(joueurCourant.nom!=null) {
                                    zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon essayer de quitter les rattrapages (vous pouvez payer 50 crédits, jouer une carte libération de rattrapages si vous en possédez une ou sinon tenter de faire un double)!");
                                }
                            }
                            plateauJeu.repaint();
                        }
                    };
                    thread2.start();
                }
                else if(joueurCourant==tabJoueurs[2]) {
                    Thread thread3 = new Thread(){
                        public void run(){
                            try {
                                Thread.sleep(1000);
                                for(int i=0;i<2;i++) {
                                    joueurCourant.pion.taille-=5;
                                    plateauJeu.orientation3=plateauJeu.haut3;
                                    plateauJeu.repaint();
                                    Thread.sleep(100);
                                    joueurCourant.pion.taille-=5;
                                    plateauJeu.orientation3=plateauJeu.droite3;
                                    plateauJeu.repaint();
                                    Thread.sleep(100);
                                    joueurCourant.pion.taille-=5;
                                    plateauJeu.orientation3=plateauJeu.bas3;
                                    plateauJeu.repaint();
                                    Thread.sleep(100);
                                    joueurCourant.pion.taille-=5;
                                    plateauJeu.orientation3=plateauJeu.gauche3;
                                    plateauJeu.repaint();
                                    Thread.sleep(100);
                                }
                            } catch (InterruptedException ex) {
                                Logger.getLogger(Monopoly_EPF.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            joueurCourant.pion.coordX=68;
                            joueurCourant.pion.coordY=31;
                            joueurCourant.pion.taille=35;
                            plateauJeu.orientation3=plateauJeu.gauche3;
                            deplacerPion(joueurCourant.pion,déplacement,joueurCourant.pion.caseassociee);
                            if(joueurCourant.credits<0) {
                                eliminationJoueur();
                            }
                            else {
                                changerJoueur();
                                compteurDouble=0;
                            }
                            while(joueurCourant.droitdejouer==false) {
                                joueurCourant.droitdejouer=true;
                                changerJoueur();
                                compteurDouble=0;
                            }
                            if(joueurCourant.compteurTourPrison==3) {
                                joueurCourant.compteurTourPrison=0;
                                joueurCourant.prison=false;
                                joueurCourant.credits=joueurCourant.credits-50;
                                if(joueurCourant==tabJoueurs[0]) {
                                    tabJoueurs[0].pion.coordX=61;
                                    tabJoueurs[0].pion.coordY=2;
                                    tabJoueurs[0].pion.taille=24;
                                    plateauJeu.orientation1=plateauJeu.haut1;
                                    plateauJeu.repaint();
                                    credits1.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                else if(joueurCourant==tabJoueurs[1]) {
                                    tabJoueurs[1].pion.coordX=18;
                                    tabJoueurs[1].pion.coordY=2;
                                    tabJoueurs[1].pion.taille=24;
                                    plateauJeu.orientation2=plateauJeu.haut2;
                                    plateauJeu.repaint();
                                    credits2.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                else if(joueurCourant==tabJoueurs[2]) {
                                    tabJoueurs[2].pion.coordX=2;
                                    tabJoueurs[2].pion.coordY=20;
                                    tabJoueurs[2].pion.taille=24;
                                    plateauJeu.orientation3=plateauJeu.gauche3;
                                    plateauJeu.repaint();
                                    credits3.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                else if(joueurCourant==tabJoueurs[3]) {
                                    tabJoueurs[3].pion.coordX=2;
                                    tabJoueurs[3].pion.coordY=63;
                                    tabJoueurs[3].pion.taille=24;
                                    plateauJeu.orientation4=plateauJeu.gauche4;
                                    plateauJeu.repaint();
                                    credits4.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n" + joueurCourant.nom + " vous venez de rater vos 3 essais aux rattrapages, vous payez donc 50 crédits et réintégrez le parcours scolaire!");
                            }
                            if(joueurCourant==tabJoueurs[0] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere1.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[1] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere2.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[2] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere3.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[3] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere4.setVisible(true);
                            }
                            if(joueurCourant==tabJoueurs[0] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération1.setVisible(true);
                                }
                                PayerPrison1.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[1] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération2.setVisible(true);
                                }
                                PayerPrison2.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[2] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération3.setVisible(true);
                                }
                                PayerPrison3.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[3] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération4.setVisible(true);
                                }
                                PayerPrison4.setVisible(true);
                            }
                            if(elim!=true) {
                                Lancer_des.setEnabled(true);
                            }
                            if(joueurCourant.prison!=true && joueurCourant.nom!=null && compteurDouble==0) {
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon tirez les dés!");
                            }
                            else if(joueurCourant.prison!=true && joueurCourant.nom!=null) {
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, vous pouvez tirez les dés!");
                            }
                            else {
                                if(joueurCourant.nom!=null) {
                                    zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon essayer de quitter les rattrapages (vous pouvez payer 50 crédits, jouer une carte libération de rattrapages si vous en possédez une ou sinon tenter de faire un double)!");
                                }
                            }
                            plateauJeu.repaint();
                        }
                    };
                    thread3.start();
                }
                else if(joueurCourant==tabJoueurs[3]) {
                    Thread thread4 = new Thread(){
                        public void run(){
                            try {
                                Thread.sleep(1000);
                                for(int i=0;i<2;i++) {
                                    joueurCourant.pion.taille-=5;
                                    plateauJeu.orientation4=plateauJeu.haut4;
                                    plateauJeu.repaint();
                                    Thread.sleep(100);
                                    joueurCourant.pion.taille-=5;
                                    plateauJeu.orientation4=plateauJeu.droite4;
                                    plateauJeu.repaint();
                                    Thread.sleep(100);
                                    joueurCourant.pion.taille-=5;
                                    plateauJeu.orientation4=plateauJeu.bas4;
                                    plateauJeu.repaint();
                                    Thread.sleep(100);
                                    joueurCourant.pion.taille-=5;
                                    plateauJeu.orientation4=plateauJeu.gauche4;
                                    plateauJeu.repaint();
                                    Thread.sleep(100);
                                }
                            } catch (InterruptedException ex) {
                                Logger.getLogger(Monopoly_EPF.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            joueurCourant.pion.coordX=68;
                            joueurCourant.pion.coordY=68;
                            joueurCourant.pion.taille=35;
                            plateauJeu.orientation4=plateauJeu.gauche4;
                            deplacerPion(joueurCourant.pion,déplacement,joueurCourant.pion.caseassociee);
                            if(joueurCourant.credits<0) {
                                eliminationJoueur();
                            }
                            else {
                                changerJoueur();
                                compteurDouble=0;
                            }
                            while(joueurCourant.droitdejouer==false) {
                                joueurCourant.droitdejouer=true;
                                changerJoueur();
                                compteurDouble=0;
                            }
                            if(joueurCourant.compteurTourPrison==3) {
                                joueurCourant.compteurTourPrison=0;
                                joueurCourant.prison=false;
                                joueurCourant.credits=joueurCourant.credits-50;
                                if(joueurCourant==tabJoueurs[0]) {
                                    tabJoueurs[0].pion.coordX=61;
                                    tabJoueurs[0].pion.coordY=2;
                                    tabJoueurs[0].pion.taille=24;
                                    plateauJeu.orientation1=plateauJeu.haut1;
                                    plateauJeu.repaint();
                                    credits1.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                else if(joueurCourant==tabJoueurs[1]) {
                                    tabJoueurs[1].pion.coordX=18;
                                    tabJoueurs[1].pion.coordY=2;
                                    tabJoueurs[1].pion.taille=24;
                                    plateauJeu.orientation2=plateauJeu.haut2;
                                    plateauJeu.repaint();
                                    credits2.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                else if(joueurCourant==tabJoueurs[2]) {
                                    tabJoueurs[2].pion.coordX=2;
                                    tabJoueurs[2].pion.coordY=20;
                                    tabJoueurs[2].pion.taille=24;
                                    plateauJeu.orientation3=plateauJeu.gauche3;
                                    plateauJeu.repaint();
                                    credits3.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                else if(joueurCourant==tabJoueurs[3]) {
                                    tabJoueurs[3].pion.coordX=2;
                                    tabJoueurs[3].pion.coordY=63;
                                    tabJoueurs[3].pion.taille=24;
                                    plateauJeu.orientation4=plateauJeu.gauche4;
                                    plateauJeu.repaint();
                                    credits4.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n" + joueurCourant.nom + " vous venez de rater vos 3 essais aux rattrapages, vous payez donc 50 crédits et réintégrez le parcours scolaire!");
                            }
                            if(joueurCourant==tabJoueurs[0] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere1.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[1] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere2.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[2] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere3.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[3] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere4.setVisible(true);
                            }
                            if(joueurCourant==tabJoueurs[0] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération1.setVisible(true);
                                }
                                PayerPrison1.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[1] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération2.setVisible(true);
                                }
                                PayerPrison2.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[2] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération3.setVisible(true);
                                }
                                PayerPrison3.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[3] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération4.setVisible(true);
                                }
                                PayerPrison4.setVisible(true);
                            }
                            if(elim!=true) {
                                Lancer_des.setEnabled(true);
                            }
                            if(joueurCourant.prison!=true && joueurCourant.nom!=null && compteurDouble==0) {
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon tirez les dés!");
                            }
                            else if(joueurCourant.prison!=true && joueurCourant.nom!=null) {
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, vous pouvez tirez les dés!");
                            }
                            else {
                                if(joueurCourant.nom!=null) {
                                    zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon essayer de quitter les rattrapages (vous pouvez payer 50 crédits, jouer une carte libération de rattrapages si vous en possédez une ou sinon tenter de faire un double)!");
                                }
                            }
                            plateauJeu.repaint();
                        }
                    };
                    thread4.start();
                }
                    }
                    if(joueurCourant.prison!=true && carte!="chance13" && choixcom==2) {
                        faireActionCase();
                    }
                    joueurCourant.pion.caseassociee.occupant=true;
                    if(joueurCourant.pion.caseassociee!=plateau.plateaudejeu[30] && joueurCourant.prison!=true && carte!="com8" && carte!="com12" && carte!="com13" && carte!="chance1" && carte!="chance3" && carte!="chance4"&& carte!="chance10" && carte!="chance13" && darties!=true && cartespéciale!="com4") {
                        if(joueurCourant.pion.caseassociee!=plateau.plateaudejeu[0] && joueurCourant.pion.caseassociee!=plateau.plateaudejeu[2] && joueurCourant.pion.caseassociee!=plateau.plateaudejeu[4] && joueurCourant.pion.caseassociee!=plateau.plateaudejeu[7] && joueurCourant.pion.caseassociee!=plateau.plateaudejeu[10] && joueurCourant.pion.caseassociee!=plateau.plateaudejeu[17] && joueurCourant.pion.caseassociee!=plateau.plateaudejeu[20] && joueurCourant.pion.caseassociee!=plateau.plateaudejeu[22] && joueurCourant.pion.caseassociee!=plateau.plateaudejeu[30] && joueurCourant.pion.caseassociee!=plateau.plateaudejeu[33] && joueurCourant.pion.caseassociee!=plateau.plateaudejeu[36] && joueurCourant.pion.caseassociee!=plateau.plateaudejeu[38] && joueurCourant.pion.caseassociee.proprietaire==null) {
                            if(joueurCourant==tabJoueurs[0]) {
                                Acheter1.setVisible(true);
                                fin_de_tour1.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[1]) {
                                Acheter2.setVisible(true);
                                fin_de_tour2.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[2]) {
                                Acheter3.setVisible(true);
                                fin_de_tour3.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[3]) {
                                Acheter4.setVisible(true);
                                fin_de_tour4.setVisible(true);
                            }
                        }
                        else{
                            if(de1.valeur==de2.valeur && joueurCourant.droitdejouer==true) {
                                if(joueurCourant.credits<0) {
                                    eliminationJoueur();
                                }
                                compteurDouble++;
                            }
                            else {
                                if(joueurCourant.credits<0) {
                                    eliminationJoueur();
                                }
                                else {
                                    changerJoueur();
                                    compteurDouble=0;
                                }
                            }
                            while(joueurCourant.droitdejouer==false) {
                                joueurCourant.droitdejouer=true;
                                changerJoueur();
                                compteurDouble=0;
                            }
                            if(joueurCourant.compteurTourPrison==3) {
                                joueurCourant.compteurTourPrison=0;
                                joueurCourant.prison=false;
                                joueurCourant.credits=joueurCourant.credits-50;
                                if(joueurCourant==tabJoueurs[0]) {
                                    tabJoueurs[0].pion.coordX=61;
                                    tabJoueurs[0].pion.coordY=2;
                                    tabJoueurs[0].pion.taille=24;
                                    plateauJeu.orientation1=plateauJeu.haut1;
                                    plateauJeu.repaint();
                                    credits1.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                else if(joueurCourant==tabJoueurs[1]) {
                                    tabJoueurs[1].pion.coordX=18;
                                    tabJoueurs[1].pion.coordY=2;
                                    tabJoueurs[1].pion.taille=24;
                                    plateauJeu.orientation2=plateauJeu.haut2;
                                    plateauJeu.repaint();
                                    credits2.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                else if(joueurCourant==tabJoueurs[2]) {
                                    tabJoueurs[2].pion.coordX=2;
                                    tabJoueurs[2].pion.coordY=20;
                                    tabJoueurs[2].pion.taille=24;
                                    plateauJeu.orientation3=plateauJeu.gauche3;
                                    plateauJeu.repaint();
                                    credits3.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                else if(joueurCourant==tabJoueurs[3]) {
                                    tabJoueurs[3].pion.coordX=2;
                                    tabJoueurs[3].pion.coordY=63;
                                    tabJoueurs[3].pion.taille=24;
                                    plateauJeu.orientation4=plateauJeu.gauche4;
                                    plateauJeu.repaint();
                                    credits4.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n" + joueurCourant.nom + " vous venez de rater vos 3 essais aux rattrapages, vous payez donc 50 crédits et réintégrez le parcours scolaire!");
                            }
                            if(joueurCourant==tabJoueurs[0] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere1.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[1] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere2.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[2] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere3.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[3] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere4.setVisible(true);
                            }
                            if(joueurCourant==tabJoueurs[0] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération1.setVisible(true);
                                }
                                PayerPrison1.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[1] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération2.setVisible(true);
                                }
                                PayerPrison2.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[2] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération3.setVisible(true);
                                }
                                PayerPrison3.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[3] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération4.setVisible(true);
                                }
                                PayerPrison4.setVisible(true);
                            }
                            if(elim!=true) {
                                Lancer_des.setEnabled(true);
                            }
                            if(joueurCourant.prison!=true && joueurCourant.nom!=null && compteurDouble==0) {
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon tirez les dés!");
                            }
                            else if(joueurCourant.prison!=true && joueurCourant.nom!=null) {
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, vous pouvez tirez les dés!");
                            }
                            else {
                                if(joueurCourant.nom!=null) {
                                    zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon essayer de quitter les rattrapages (vous pouvez payer 50 crédits, jouer une carte libération de rattrapages si vous en possédez une ou sinon tenter de faire un double)!");
                                }
                            }
                        }
                    }
                    else if(joueurCourant.pion.caseassociee==plateau.plateaudejeu[30]) {
                        carte="Jousset";
                    }
                    else if(joueurCourant.prison==true && carte!="com2" && carte!="Jousset" && carte!="com8" && carte!="com12" && carte!="com13" && carte!="chance1" && carte!="chance2" && carte!="chance3"  && carte!="chance4" && carte!="chance10" && carte!="chance13" && darties!=true && cartespéciale!="com4" && tripledouble==false) {
                        if(joueurCourant.credits<0) {
                            eliminationJoueur();
                        }
                        else {
                            changerJoueur();
                            compteurDouble=0;
                        }
                        while(joueurCourant.droitdejouer==false) {
                            joueurCourant.droitdejouer=true;
                            changerJoueur();
                            compteurDouble=0;
                        }
                        if(joueurCourant.compteurTourPrison==3) {
                            joueurCourant.compteurTourPrison=0;
                            joueurCourant.prison=false;
                            joueurCourant.credits=joueurCourant.credits-50;
                            if(joueurCourant==tabJoueurs[0]) {
                                tabJoueurs[0].pion.coordX=61;
                                tabJoueurs[0].pion.coordY=2;
                                tabJoueurs[0].pion.taille=24;
                                plateauJeu.orientation1=plateauJeu.haut1;
                                plateauJeu.repaint();
                                credits1.setText("Crédits : " + joueurCourant.credits + " ECTS");
                            }
                            else if(joueurCourant==tabJoueurs[1]) {
                                tabJoueurs[1].pion.coordX=18;
                                tabJoueurs[1].pion.coordY=2;
                                tabJoueurs[1].pion.taille=24;
                                plateauJeu.orientation2=plateauJeu.haut2;
                                plateauJeu.repaint();
                                credits2.setText("Crédits : " + joueurCourant.credits + " ECTS");
                            }
                            else if(joueurCourant==tabJoueurs[2]) {
                                tabJoueurs[2].pion.coordX=2;
                                tabJoueurs[2].pion.coordY=20;
                                tabJoueurs[2].pion.taille=24;
                                plateauJeu.orientation3=plateauJeu.gauche3;
                                plateauJeu.repaint();
                                credits3.setText("Crédits : " + joueurCourant.credits + " ECTS");
                            }
                            else if(joueurCourant==tabJoueurs[3]) {
                                tabJoueurs[3].pion.coordX=2;
                                tabJoueurs[3].pion.coordY=63;
                                tabJoueurs[3].pion.taille=24;
                                plateauJeu.orientation4=plateauJeu.gauche4;
                                plateauJeu.repaint();
                                credits4.setText("Crédits : " + joueurCourant.credits + " ECTS");
                            }
                            zone_texte_infos.setText(zone_texte_infos.getText() + "\n" + joueurCourant.nom + " vous venez de rater vos 3 essais aux rattrapages, vous payez donc 50 crédits et réintégrez le parcours scolaire!");
                        }
                        if(joueurCourant==tabJoueurs[0] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                            ValiderMatiere1.setVisible(true);
                        }
                        else if(joueurCourant==tabJoueurs[1] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                            ValiderMatiere2.setVisible(true);
                        }
                        else if(joueurCourant==tabJoueurs[2] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                            ValiderMatiere3.setVisible(true);
                        }
                        else if(joueurCourant==tabJoueurs[3] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                            ValiderMatiere4.setVisible(true);
                        }
                        if(joueurCourant==tabJoueurs[0] && joueurCourant.prison==true) {
                            joueurCourant.compteurTourPrison++;
                            if(joueurCourant.nbCartes!=0) {
                                Libération1.setVisible(true);
                            }
                            PayerPrison1.setVisible(true);
                        }
                        else if(joueurCourant==tabJoueurs[1] && joueurCourant.prison==true) {
                            joueurCourant.compteurTourPrison++;
                            if(joueurCourant.nbCartes!=0) {
                                Libération2.setVisible(true);
                            }
                            PayerPrison2.setVisible(true);
                        }
                        else if(joueurCourant==tabJoueurs[2] && joueurCourant.prison==true) {
                            joueurCourant.compteurTourPrison++;
                            if(joueurCourant.nbCartes!=0) {
                                Libération3.setVisible(true);
                            }
                            PayerPrison3.setVisible(true);
                        }
                        else if(joueurCourant==tabJoueurs[3] && joueurCourant.prison==true) {
                            joueurCourant.compteurTourPrison++;
                            if(joueurCourant.nbCartes!=0) {
                                Libération4.setVisible(true);
                            }
                            PayerPrison4.setVisible(true);
                        }
                        if(elim!=true) {
                            Lancer_des.setEnabled(true);
                        }
                        if(joueurCourant.prison!=true && joueurCourant.nom!=null && compteurDouble==0) {
                            zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon tirez les dés!");
                        }
                        else if(joueurCourant.prison!=true && joueurCourant.nom!=null) {
                            zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, vous pouvez tirez les dés!");
                        }
                        else {
                            if(joueurCourant.nom!=null) {
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon essayer de quitter les rattrapages (vous pouvez payer 50 crédits, jouer une carte libération de rattrapages si vous en possédez une ou sinon tenter de faire un double)!");
                            }
                        }
                        plateauJeu.repaint();
                    }
                    plateauJeu.repaint();
                    monChrono.stop();
                    if(carte=="com2") {
                        DeplacerPion(joueurCourant.pion, plateau.plateaudejeu[10], "Téléportation");
                        int déplacement=0;
                        if(joueurCourant.pion.caseassociee==plateau.plateaudejeu[2]) {
                            déplacement=8;
                        }
                        else if(joueurCourant.pion.caseassociee==plateau.plateaudejeu[17]) {
                            déplacement=33;
                        }
                        else if(joueurCourant.pion.caseassociee==plateau.plateaudejeu[33]) {
                            déplacement=17;
                        }
                        deplacerPion(joueurCourant.pion,déplacement,joueurCourant.pion.caseassociee);
                        carte="";
                    }
                    else if(carte=="com8") {
                        int ouonest=0;
                        for(int i=0;i<plateau.plateaudejeu.length;i++) {
                            if(pionCourant.caseassociee==plateau.plateaudejeu[i]) {
                                ouonest=i;
                            }
                        }
                        DeplacerPion(joueurCourant.pion, plateau.plateaudejeu[(ouonest+8)%40], "Téléportation");
                        deplacerPion(joueurCourant.pion,8,joueurCourant.pion.caseassociee);
                        carte="";
                    }
                    else if(carte=="com12") {
                        int ouonest=0;
                        for(int i=0;i<plateau.plateaudejeu.length;i++) {
                            if(pionCourant.caseassociee==plateau.plateaudejeu[i]) {
                                ouonest=i;
                            }
                        }
                        DeplacerPion(joueurCourant.pion, plateau.plateaudejeu[32], "Téléportation");
                        int déplacement=0;
                        if(joueurCourant.pion.caseassociee==plateau.plateaudejeu[2]) {
                            déplacement=30;
                        }
                        else if(joueurCourant.pion.caseassociee==plateau.plateaudejeu[17]) {
                            déplacement=15;
                        }
                        else if(joueurCourant.pion.caseassociee==plateau.plateaudejeu[33]) {
                            déplacement=39;
                        }
                        deplacerPion(joueurCourant.pion,déplacement,joueurCourant.pion.caseassociee);
                        carte="";
                    }
                    else if(carte=="com13") {
                        int ouonest=0;
                        for(int i=0;i<plateau.plateaudejeu.length;i++) {
                            if(pionCourant.caseassociee==plateau.plateaudejeu[i]) {
                                ouonest=i;
                            }
                        }
                        DeplacerPion(joueurCourant.pion, plateau.plateaudejeu[35], "Téléportation");
                        int déplacement=0;
                        if(joueurCourant.pion.caseassociee==plateau.plateaudejeu[2]) {
                            déplacement=33;
                        }
                        else if(joueurCourant.pion.caseassociee==plateau.plateaudejeu[17]) {
                            déplacement=18;
                        }
                        else if(joueurCourant.pion.caseassociee==plateau.plateaudejeu[33]) {
                            déplacement=2;
                        }
                        deplacerPion(joueurCourant.pion,déplacement,joueurCourant.pion.caseassociee);
                        carte="";
                    }
                    else if(carte=="chance1") {
                        int ouonest=0;
                        for(int i=0;i<plateau.plateaudejeu.length;i++) {
                            if(pionCourant.caseassociee==plateau.plateaudejeu[i]) {
                                ouonest=i;
                            }
                        }
                        if(ouonest-3==-1) {
                            ouonest=39;
                            DeplacerPion(joueurCourant.pion, plateau.plateaudejeu[ouonest], "Téléportation");
                        }
                        else {
                            DeplacerPion(joueurCourant.pion, plateau.plateaudejeu[ouonest-3], "Téléportation");
                        }
                        int déplacement=37;
                        deplacerPion(joueurCourant.pion,déplacement,joueurCourant.pion.caseassociee);
                        carte="";
                    }
                    else if(carte=="chance2") {
                        DeplacerPion(joueurCourant.pion, plateau.plateaudejeu[10], "Téléportation");
                        int déplacement=0;
                        if(joueurCourant.pion.caseassociee==plateau.plateaudejeu[7]) {
                            déplacement=3;
                        }
                        else if(joueurCourant.pion.caseassociee==plateau.plateaudejeu[22]) {
                            déplacement=28;
                        }
                        else if(joueurCourant.pion.caseassociee==plateau.plateaudejeu[36]) {
                            déplacement=14;
                        }
                        else if(choixcom!=2) {
                            if(joueurCourant.pion.caseassociee==plateau.plateaudejeu[2]) {
                                déplacement=8;
                            }
                            else if(joueurCourant.pion.caseassociee==plateau.plateaudejeu[17]) {
                                déplacement=33;
                            }
                            else if(joueurCourant.pion.caseassociee==plateau.plateaudejeu[33]) {
                                déplacement=17;
                            }
                        }
                        deplacerPion(joueurCourant.pion,déplacement,joueurCourant.pion.caseassociee);
                        carte="";
                    }
                    else if(carte=="chance3") {
                        DeplacerPion(joueurCourant.pion, plateau.plateaudejeu[0], "Téléportation");
                        int déplacement=0;
                        if(joueurCourant.pion.caseassociee==plateau.plateaudejeu[7]) {
                            déplacement=33;
                        }
                        else if(joueurCourant.pion.caseassociee==plateau.plateaudejeu[22]) {
                            déplacement=18;
                        }
                        else if(joueurCourant.pion.caseassociee==plateau.plateaudejeu[36]) {
                            déplacement=4;
                        }
                        else if(choixcom!=2) {
                            if(joueurCourant.pion.caseassociee==plateau.plateaudejeu[2]) {
                                déplacement=38;
                            }
                            else if(joueurCourant.pion.caseassociee==plateau.plateaudejeu[17]) {
                                déplacement=23;
                            }
                            else if(joueurCourant.pion.caseassociee==plateau.plateaudejeu[33]) {
                                déplacement=7;
                            }
                        }
                        deplacerPion(joueurCourant.pion,déplacement,joueurCourant.pion.caseassociee);
                        carte="";
                    }
                    else if(carte=="chance4") {
                        DeplacerPion(joueurCourant.pion, plateau.plateaudejeu[31], "Téléportation");
                        int déplacement=0;
                        if(joueurCourant.pion.caseassociee==plateau.plateaudejeu[7]) {
                            déplacement=24;
                        }
                        else if(joueurCourant.pion.caseassociee==plateau.plateaudejeu[22]) {
                            déplacement=9;
                        }
                        else if(joueurCourant.pion.caseassociee==plateau.plateaudejeu[36]) {
                            déplacement=35;
                        }
                        else if(choixcom!=2) {
                            if(joueurCourant.pion.caseassociee==plateau.plateaudejeu[2]) {
                                déplacement=29;
                            }
                            else if(joueurCourant.pion.caseassociee==plateau.plateaudejeu[17]) {
                                déplacement=14;
                            }
                            else if(joueurCourant.pion.caseassociee==plateau.plateaudejeu[33]) {
                                déplacement=38;
                            }
                        }
                        deplacerPion(joueurCourant.pion,déplacement,joueurCourant.pion.caseassociee);
                        carte="";
                    }
                    else if(carte=="chance10") {
                        DeplacerPion(joueurCourant.pion, plateau.plateaudejeu[15], "Téléportation");
                        int déplacement=0;
                        if(joueurCourant.pion.caseassociee==plateau.plateaudejeu[7]) {
                            déplacement=8;
                        }
                        else if(joueurCourant.pion.caseassociee==plateau.plateaudejeu[22]) {
                            déplacement=33;
                        }
                        else if(joueurCourant.pion.caseassociee==plateau.plateaudejeu[36]) {
                            déplacement=19;
                        }
                        else if(choixcom!=2) {
                            if(joueurCourant.pion.caseassociee==plateau.plateaudejeu[2]) {
                                déplacement=13;
                            }
                            else if(joueurCourant.pion.caseassociee==plateau.plateaudejeu[17]) {
                                déplacement=38;
                            }
                            else if(joueurCourant.pion.caseassociee==plateau.plateaudejeu[33]) {
                                déplacement=22;
                            }
                        }
                        deplacerPion(joueurCourant.pion,déplacement,joueurCourant.pion.caseassociee);
                        carte="";
                    }
                    else if(cartespéciale=="com4" && choixcom==1 && carte!="chance13") {
                        faireActionCase();
                        if(joueurCourant.pion.caseassociee!=plateau.plateaudejeu[30]) {
                            if(joueurCourant.credits<0) {
                                eliminationJoueur();
                            }
                            else {
                                changerJoueur();
                                compteurDouble=0;
                            }
                            while(joueurCourant.droitdejouer==false) {
                                joueurCourant.droitdejouer=true;
                                changerJoueur();
                                compteurDouble=0;
                            }
                            if(joueurCourant.compteurTourPrison==3) {
                                joueurCourant.compteurTourPrison=0;
                                joueurCourant.prison=false;
                                joueurCourant.credits=joueurCourant.credits-50;
                                if(joueurCourant==tabJoueurs[0]) {
                                    tabJoueurs[0].pion.coordX=61;
                                    tabJoueurs[0].pion.coordY=2;
                                    tabJoueurs[0].pion.taille=24;
                                    plateauJeu.orientation1=plateauJeu.haut1;
                                    plateauJeu.repaint();
                                    credits1.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                else if(joueurCourant==tabJoueurs[1]) {
                                    tabJoueurs[1].pion.coordX=18;
                                    tabJoueurs[1].pion.coordY=2;
                                    tabJoueurs[1].pion.taille=24;
                                    plateauJeu.orientation2=plateauJeu.haut2;
                                    plateauJeu.repaint();
                                    credits2.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                else if(joueurCourant==tabJoueurs[2]) {
                                    tabJoueurs[2].pion.coordX=2;
                                    tabJoueurs[2].pion.coordY=20;
                                    tabJoueurs[2].pion.taille=24;
                                    plateauJeu.orientation3=plateauJeu.gauche3;
                                    plateauJeu.repaint();
                                    credits3.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                else if(joueurCourant==tabJoueurs[3]) {
                                    tabJoueurs[3].pion.coordX=2;
                                    tabJoueurs[3].pion.coordY=63;
                                    tabJoueurs[3].pion.taille=24;
                                    plateauJeu.orientation4=plateauJeu.gauche4;
                                    plateauJeu.repaint();
                                    credits4.setText("Crédits : " + joueurCourant.credits + " ECTS");
                                }
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n" + joueurCourant.nom + " vous venez de rater vos 3 essais aux rattrapages, vous payez donc 50 crédits et réintégrez le parcours scolaire!");
                            }
                            if(joueurCourant==tabJoueurs[0] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere1.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[1] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere2.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[2] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere3.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[3] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                ValiderMatiere4.setVisible(true);
                            }
                            if(joueurCourant==tabJoueurs[0] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération1.setVisible(true);
                                }
                                PayerPrison1.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[1] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération2.setVisible(true);
                                }
                                PayerPrison2.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[2] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération3.setVisible(true);
                                }
                                PayerPrison3.setVisible(true);
                            }
                            else if(joueurCourant==tabJoueurs[3] && joueurCourant.prison==true) {
                                joueurCourant.compteurTourPrison++;
                                if(joueurCourant.nbCartes!=0) {
                                    Libération4.setVisible(true);
                                }
                                PayerPrison4.setVisible(true);
                            }
                            if(elim!=true) {
                                Lancer_des.setEnabled(true);
                            }
                            if(joueurCourant.prison!=true && joueurCourant.nom!=null && compteurDouble==0) {
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon tirez les dés!");
                            }
                            else if(joueurCourant.prison!=true && joueurCourant.nom!=null) {
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, vous pouvez tirez les dés!");
                            }
                            else {
                                if(joueurCourant.nom!=null) {
                                    zone_texte_infos.setText(zone_texte_infos.getText() + "\n\n" + joueurCourant.nom + " à vous de jouer, validez des matières si vous le pouvez, sinon essayer de quitter les rattrapages (vous pouvez payer 50 crédits, jouer une carte libération de rattrapages si vous en possédez une ou sinon tenter de faire un double)!");
                                }
                            }
                            plateauJeu.repaint();
                        }
                        carte="";
                        cartespéciale="";
                        choixcom=2;
                    }
                    else if(carte=="chance13") {
                        int J1=0;
                        int J2=0;
                        int J3=0;
                        int J4=0;
                        plateau.plateaudejeu[0].occupant=true;
                        for(int i=0;i<plateau.plateaudejeu.length;i++) {
                            if(tabJoueurs[0].pion.caseassociee==plateau.plateaudejeu[i] && tabJoueurs[0].pion.caseassociee!=plateau.plateaudejeu[0] && tabJoueurs[0].nom!=null) {
                                J1=i;
                                compteur++;
                                if(tabJoueurs[0].prison==true) {
                                    tabJoueurs[0].prison=false;
                                    tabJoueurs[0].compteurTourPrison=0;
                                    zone_texte_infos.setText(zone_texte_infos.getText() + "\n" + tabJoueurs[0].nom + " vous sortez de prison pour aller au début d'année!");
                                }
                                DeplacerPion(tabJoueurs[0].pion, plateau.plateaudejeu[0], "Case départ");
                                deplacerPion(tabJoueurs[0].pion,40-J1,plateau.plateaudejeu[J1]);
                                tabJoueurs[0].credits = tabJoueurs[0].credits + 200;
                                credits1.setText("Crédits : " + tabJoueurs[0].credits + " ECTS");
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n" + tabJoueurs[0].nom + " vous vous arrêtez au début d'année et touchez 200 ECTS car c'est la JPO!");
                                plateau.plateaudejeu[i].occupant=false;
                                break;
                            }
                            else if(tabJoueurs[1].pion.caseassociee==plateau.plateaudejeu[i] && tabJoueurs[1].pion.caseassociee!=plateau.plateaudejeu[0] && tabJoueurs[1].nom!=null) {
                                J2=i;
                                compteur++;
                                if(tabJoueurs[1].prison==true) {
                                    tabJoueurs[1].prison=false;
                                    tabJoueurs[1].compteurTourPrison=0;
                                    zone_texte_infos.setText(zone_texte_infos.getText() + "\n" + tabJoueurs[1].nom + " vous sortez de prison pour aller au début d'année!");
                                }
                                DeplacerPion(tabJoueurs[1].pion, plateau.plateaudejeu[0], "Case départ");
                                deplacerPion(tabJoueurs[1].pion,40-J2,plateau.plateaudejeu[J2]);
                                tabJoueurs[1].credits = tabJoueurs[1].credits + 200;
                                credits2.setText("Crédits : " + tabJoueurs[1].credits + " ECTS");
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n" + tabJoueurs[1].nom + " vous vous arrêtez au début d'année et touchez 200 ECTS car c'est la JPO!");
                                plateau.plateaudejeu[i].occupant=false;
                                break;
                            }
                            else if(tabJoueurs[2].pion.caseassociee==plateau.plateaudejeu[i] && tabJoueurs[2].pion.caseassociee!=plateau.plateaudejeu[0] && tabJoueurs[2].nom!=null) {
                                J3=i;
                                compteur++;
                                if(tabJoueurs[2].prison==true) {
                                    tabJoueurs[2].prison=false;
                                    tabJoueurs[2].compteurTourPrison=0;
                                    zone_texte_infos.setText(zone_texte_infos.getText() + "\n" + tabJoueurs[2].nom + " vous sortez de prison pour aller au début d'année!");
                                }
                                DeplacerPion(tabJoueurs[2].pion, plateau.plateaudejeu[0], "Case départ");
                                deplacerPion(tabJoueurs[2].pion,40-J3,plateau.plateaudejeu[J3]);
                                tabJoueurs[2].credits = tabJoueurs[2].credits + 200;
                                credits3.setText("Crédits : " + tabJoueurs[2].credits + " ECTS");
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n" + tabJoueurs[2].nom + " vous vous arrêtez au début d'année et touchez 200 ECTS car c'est la JPO!");
                                plateau.plateaudejeu[i].occupant=false;
                                break;
                            }
                            else if(tabJoueurs[3].pion.caseassociee==plateau.plateaudejeu[i] && tabJoueurs[3].pion.caseassociee!=plateau.plateaudejeu[0] && tabJoueurs[3].nom!=null) {
                                J4=i;
                                compteur++;
                                if(tabJoueurs[3].prison==true) {
                                    tabJoueurs[3].prison=false;
                                    tabJoueurs[3].compteurTourPrison=0;
                                    zone_texte_infos.setText(zone_texte_infos.getText() + "\n" + tabJoueurs[3].nom + " vous sortez de prison pour aller au début d'année!");
                                }
                                DeplacerPion(tabJoueurs[3].pion, plateau.plateaudejeu[0], "Case départ");
                                deplacerPion(tabJoueurs[3].pion,40-J4,plateau.plateaudejeu[J4]);
                                tabJoueurs[3].credits = tabJoueurs[3].credits + 200;
                                credits4.setText("Crédits : " + tabJoueurs[3].credits + " ECTS");
                                zone_texte_infos.setText(zone_texte_infos.getText() + "\n" + tabJoueurs[3].nom + " vous vous arrêtez au début d'année et touchez 200 ECTS car c'est la JPO!");
                                plateau.plateaudejeu[i].occupant=false;
                                break;
                            }
                            else if(compteur==compteurlocal) {
                                if(de1.valeur!=de2.valeur && joueurCourant.droitdejouer==true) {
                                    if(joueurCourant.credits<0) {
                                        eliminationJoueur();
                                    }
                                    else {
                                        changerJoueur();
                                        compteurDouble=0;
                                    }
                                }
                                while(joueurCourant.droitdejouer==false) {
                                    joueurCourant.droitdejouer=true;
                                    changerJoueur();
                                    compteurDouble=0;
                                }
                                if(joueurCourant==tabJoueurs[0] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                    ValiderMatiere1.setVisible(true);
                                }
                                else if(joueurCourant==tabJoueurs[1] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                    ValiderMatiere2.setVisible(true);
                                }
                                else if(joueurCourant==tabJoueurs[2] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                    ValiderMatiere3.setVisible(true);
                                }
                                else if(joueurCourant==tabJoueurs[3] && joueurCourant.PeutConstruire==true && compteurDouble==0) {
                                    ValiderMatiere4.setVisible(true);
                                }
                                compteur=0;
                                compteurlocal=0;
                                carte="";
                                if(elim!=true) {
                                    Lancer_des.setEnabled(true);
                                }
                                break;
                            }
                        }
                    }
                }
            }
        ;
        };
        monChrono = new Timer(1000/nbFrames, (ActionListener) tache_recurrente);
        monChrono.start();
        plateauJeu.repaint();
    }
}
