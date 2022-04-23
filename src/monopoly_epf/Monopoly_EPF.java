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
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JButton;
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
        plateauJeu.setBackground(Color.red);
        add(plateauJeu, new org.netbeans.lib.awtextra.AbsoluteConstraints(367,0,-1,-1));
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
        this.repaint();
        
        infos_joueurs2 = new JPanel();
        infos_joueurs2.setSize(355,200);
        infos_joueurs2.setBackground(Color.red);
        infos_joueurs2.setBorder(BorderFactory.createMatteBorder(2,2,2,2, Color.BLACK));
        add(infos_joueurs2, new org.netbeans.lib.awtextra.AbsoluteConstraints(5,460,355,200));
        this.repaint();
        
        infos_joueurs3 = new JPanel();
        infos_joueurs3.setSize(300,500);
        infos_joueurs3.setBackground(Color.red);
        infos_joueurs3.setBorder(BorderFactory.createMatteBorder(2,2,2,2, Color.BLACK));
        add(infos_joueurs3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220,200,370,200));
        this.repaint();
        
        infos_joueurs4 = new JPanel();
        infos_joueurs4.setSize(300,500);
        infos_joueurs4.setBackground(Color.red);
        infos_joueurs4.setBorder(BorderFactory.createMatteBorder(2,2,2,2, Color.BLACK));
        add(infos_joueurs4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220,460,370,200));
        this.repaint();
        
        panel_boutons = new JPanel();
        panel_boutons.setSize(355,140);
        panel_boutons.setBackground(Color.LIGHT_GRAY);
        panel_boutons.setBorder(BorderFactory.createMatteBorder(2,2,2,2, Color.BLACK));
        add(panel_boutons, new org.netbeans.lib.awtextra.AbsoluteConstraints(5,58,355,135));
        panel_boutons.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        this.repaint();
        
        infos_partie = new JPanel();
        infos_partie.setSize(370,170);
        infos_partie.setBackground(Color.WHITE);
        infos_partie.setBorder(BorderFactory.createMatteBorder(2,2,2,2, Color.BLACK));
        add(infos_partie, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220,8,370,170));
        this.repaint();
        
        Regles = new JButton();
        Regles.setBounds(5, 5, 170, 60);
        panel_boutons.add(Regles, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 5, 170, 60));
        Regles.setBackground(Color.BLUE);
        Regles.setText("Règles");
        Regles.setFont(new Font("Règles", Font.BOLD, 16));
        this.repaint();
        
        Démarrer = new JButton();
        Démarrer.setBounds(5, 70, 170, 60);
        panel_boutons.add(Démarrer, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 70, 170, 60));
        Démarrer.setBackground(Color.BLUE);
        Démarrer.setText("Démarrer la partie");
        Démarrer.setFont(new Font("Démarrer la partie", Font.BOLD, 16));
        this.repaint();
        
        Lancer_des = new JButton();
        Lancer_des.setBounds(180, 5, 170, 60);
        panel_boutons.add(Lancer_des, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 5, 170, 60));
        Lancer_des.setBackground(Color.BLUE);
        Lancer_des.setText("Lancer les dés");
        Lancer_des.setFont(new Font("Lancer les dés", Font.BOLD, 16));
        this.repaint();
        
        Salles = new JButton();
        Salles.setBounds(180, 70, 170, 60);
        panel_boutons.add(Salles, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 70, 170, 60));
        Salles.setBackground(Color.BLUE);
        Salles.setText("Salles");
        Salles.setFont(new Font("Salles", Font.BOLD, 16));
        this.repaint();
        
        Payer_Jousset = new JButton();
        Payer_Jousset.setBounds(5,650,355,150);
        add(Payer_Jousset, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 650, 355, 150));
        Payer_Jousset.setBackground(Color.BLUE);
        Payer_Jousset.setText("Payer Jousset");
        Payer_Jousset.setFont(new Font("Payer Jousset", Font.BOLD, 20));
        this.repaint();
        
        Joueur1 = new JLabel("Joueur 1");
        Dimension text_joueur1 = Joueur1.getPreferredSize();
        Joueur1.setFont(new Font("Joueur 1", Font.BOLD, 25));
        infos_joueurs1.add(Joueur1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30,8));
        this.repaint();
        
        Joueur2 = new JLabel("Joueur 2");
        Dimension text_joueur2 = Joueur2.getPreferredSize();
        Joueur2.setFont(new Font("Joueur 2", Font.BOLD, 25));
        infos_joueurs2.add(Joueur2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30,8));
        this.repaint();
        
        Joueur3 = new JLabel("Joueur 3");
        Dimension text_joueur3 = Joueur3.getPreferredSize();
        Joueur3.setFont(new Font("Joueur 3", Font.BOLD, 25));
        infos_joueurs3.add(Joueur3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30,8));
        this.repaint();
        
        Joueur4 = new JLabel("Joueur 4");
        Dimension text_joueur4 = Joueur4.getPreferredSize();
        Joueur4.setFont(new Font("Joueur 4", Font.BOLD, 25));
        infos_joueurs4.add(Joueur4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30,8));
        this.repaint();
        
        Infos_partie = new JLabel("Infos partie:");
        Dimension text_infos_partie = Infos_partie.getPreferredSize();
        Infos_partie.setFont(new Font("Infos partie;", Font.BOLD, 15));
        infos_partie.add(Infos_partie, new org.netbeans.lib.awtextra.AbsoluteConstraints(5,5));
        this.repaint();
        
        
                
    }
    
    PlateauPanel plateauJeu;
    private JLabel titre;
    private JLabel Joueur1;
    private JLabel Joueur2;
    private JLabel Joueur3;
    private JLabel Joueur4;
    private JLabel Infos_partie;
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
     
     
     public int lancerDes() { //lire double et initialiser compteur
        Random lancerdes = new Random();
        de1.valeur = lancerdes.nextInt(6); 
        de2.valeur = lancerdes.nextInt(6);
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
            return true;
        }
        else if (caseDuJoueur == plateau.plateaudejeu[38]) { //deuxième taxe
            joueurCourant.credits = joueurCourant.credits-100;
            argentParcGratuit = argentParcGratuit + 100;
            return true;
        }
        else if (caseDuJoueur == plateau.plateaudejeu[20]) { //parc gratuit
            joueurCourant.credits= joueurCourant.credits + argentParcGratuit;
            argentParcGratuit = 0;
            return true;
        }
        else if (caseDuJoueur == plateau.plateaudejeu[30]) { //prison
            joueurCourant.prison = true;
            return true;
        }
        else if (caseDuJoueur == plateau.plateaudejeu[12] && caseDuJoueur.proprietaire != null) { //premiere compagnie déjà achetée
            if (caseDuJoueur.proprietaire == plateau.plateaudejeu[28].proprietaire) {
                joueurCourant.credits = joueurCourant.credits - (de1.valeur + de2.valeur)*10;
                caseDuJoueur.proprietaire.credits = caseDuJoueur.proprietaire.credits + (de1.valeur + de2.valeur)*10;
                return true;
            }
            else {
                joueurCourant.credits = joueurCourant.credits - (de1.valeur + de2.valeur)*4;
                caseDuJoueur.proprietaire.credits = caseDuJoueur.proprietaire.credits + (de1.valeur + de2.valeur)*4;
                return true;
            }
        }
        else if (caseDuJoueur == plateau.plateaudejeu[28] && caseDuJoueur.proprietaire != null) { //deuxième compagnie déjà achetée
            if (caseDuJoueur.proprietaire == plateau.plateaudejeu[12].proprietaire) {
                joueurCourant.credits = joueurCourant.credits - (de1.valeur + de2.valeur)*10;
                caseDuJoueur.proprietaire.credits = caseDuJoueur.proprietaire.credits + (de1.valeur + de2.valeur)*10;
                return true;
            }
            else {
                joueurCourant.credits = joueurCourant.credits - (de1.valeur + de2.valeur)*4;
                caseDuJoueur.proprietaire.credits = caseDuJoueur.proprietaire.credits + (de1.valeur + de2.valeur)*4;
                return true;
            }
        }
        else if (caseDuJoueur == plateau.plateaudejeu[5] && caseDuJoueur.proprietaire != null) {
            int nbgares = 0;
            for(int i=0; i<caseDuJoueur.proprietaire.sallesPossedees.size(); i++) {
                if(caseDuJoueur.proprietaire.sallesPossedees.get(i) == plateau.plateaudejeu[15] ) {
                    nbgares++;
                }
                else if(caseDuJoueur.proprietaire.sallesPossedees.get(i) == plateau.plateaudejeu[25] ) {
                    nbgares++;
                }
                else if(caseDuJoueur.proprietaire.sallesPossedees.get(i) == plateau.plateaudejeu[35] ) {
                    nbgares++;
                }
            }
            if(nbgares == 0) {
                joueurCourant.credits = joueurCourant.credits - 25;
            }
            else if(nbgares == 1) {
                joueurCourant.credits = joueurCourant.credits - 50;
            }
            else if(nbgares == 2) {
                joueurCourant.credits = joueurCourant.credits - 100;
            }
            else if(nbgares == 3) {
                joueurCourant.credits = joueurCourant.credits - 200;
            }
        }
        else if (caseDuJoueur == plateau.plateaudejeu[15] && caseDuJoueur.proprietaire != null) {
            int nbgares = 0;
            for(int i=0; i<caseDuJoueur.proprietaire.sallesPossedees.size(); i++) {
                if(caseDuJoueur.proprietaire.sallesPossedees.get(i) == plateau.plateaudejeu[5] ) {
                    nbgares++;
                }
                else if(caseDuJoueur.proprietaire.sallesPossedees.get(i) == plateau.plateaudejeu[25] ) {
                    nbgares++;
                }
                else if(caseDuJoueur.proprietaire.sallesPossedees.get(i) == plateau.plateaudejeu[35] ) {
                    nbgares++;
                }
            }
            if(nbgares == 0) {
                joueurCourant.credits = joueurCourant.credits - 25;
            }
            else if(nbgares == 1) {
                joueurCourant.credits = joueurCourant.credits - 50;
            }
            else if(nbgares == 2) {
                joueurCourant.credits = joueurCourant.credits - 100;
            }
            else if(nbgares == 3) {
                joueurCourant.credits = joueurCourant.credits - 200;
            }
        }
        else if (caseDuJoueur == plateau.plateaudejeu[25] && caseDuJoueur.proprietaire != null) {
            int nbgares = 0;
            for(int i=0; i<caseDuJoueur.proprietaire.sallesPossedees.size(); i++) { 
                if(caseDuJoueur.proprietaire.sallesPossedees.get(i) == plateau.plateaudejeu[5] ) {
                    nbgares++;
                }
                else if(caseDuJoueur.proprietaire.sallesPossedees.get(i) == plateau.plateaudejeu[15] ) {
                    nbgares++;
                }
                else if(caseDuJoueur.proprietaire.sallesPossedees.get(i) == plateau.plateaudejeu[35] ) {
                    nbgares++;
                }
            }
            if(nbgares == 0) {
                joueurCourant.credits = joueurCourant.credits - 25;
            }
            else if(nbgares == 1) {
                joueurCourant.credits = joueurCourant.credits - 50;
            }
            else if(nbgares == 2) {
                joueurCourant.credits = joueurCourant.credits - 100;
            }
            else if(nbgares == 3) {
                joueurCourant.credits = joueurCourant.credits - 200;
            }
        }
        else if (caseDuJoueur == plateau.plateaudejeu[35] && caseDuJoueur.proprietaire != null) {
            int nbgares = 0;
            for(int i=0; i<caseDuJoueur.proprietaire.sallesPossedees.size(); i++) {
                if(caseDuJoueur.proprietaire.sallesPossedees.get(i) == plateau.plateaudejeu[5] ) {
                    nbgares++;
                }
                else if(caseDuJoueur.proprietaire.sallesPossedees.get(i) == plateau.plateaudejeu[15] ) {
                    nbgares++;
                }
                else if(caseDuJoueur.proprietaire.sallesPossedees.get(i) == plateau.plateaudejeu[25] ) {
                    nbgares++;
                }
            }
            if(nbgares == 0) {
                joueurCourant.credits = joueurCourant.credits - 25;
            }
            else if(nbgares == 1) {
                joueurCourant.credits = joueurCourant.credits - 50;
            }
            else if(nbgares == 2) {
                joueurCourant.credits = joueurCourant.credits - 100;
            }
            else if(nbgares == 3) {
                joueurCourant.credits = joueurCourant.credits - 200;
            }
        }
        
        else if (caseDuJoueur.proprietaire != null) { //autre cases déjà achetées
            payerloyer(caseDuJoueur);
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
            }
            else if (id==1) {
                joueurCourant.credits = joueurCourant.credits-50;
                argentParcGratuit = argentParcGratuit + 50;
            }
            else if (id==2) {
                joueurCourant.pion.caseassociee = plateau.plateaudejeu[10];
                joueurCourant.prison = true;
            }
            else if (id==3) joueurCourant.recuperercarte(carteTiree);
            else if (id==5) joueurCourant.credits = joueurCourant.credits+25;
            else if (id==6) joueurCourant.credits = joueurCourant.credits-25*joueurCourant.nbMaisonJoueur-100*joueurCourant.nbHotelJoueur;
            else if (id==7) { // a verifier
                int nbJoueursRestants=0;
                for (int i=0; i<4; i++){ //calcul du nombre de joueurs restants
                    if (tabJoueurs[i] != null) nbJoueursRestants = nbJoueursRestants+1;
                }
                joueurCourant.credits = joueurCourant.credits-15*nbJoueursRestants; //cagnotte du joueur courant qui s'actualise
                for (int i=0; i<4; i++){
                    if (tabJoueurs[i] != null && tabJoueurs[i] != joueurCourant) {
                        tabJoueurs[i].credits = tabJoueurs[i].credits+15; //credits des autres joueurs qui d'actualisent
                    }
                }
            }
            else if (id==8) joueurCourant.pion.avancer(8);
            else if (id==9) {
                joueurCourant.droitdejouer = false;
            }
            else if (id==10) joueurCourant.credits = joueurCourant.credits + 150;
            else if (id==11) joueurCourant.credits = joueurCourant.credits + 100;
            else if (id==12) joueurCourant.pion.téléportation(32);  //indice 32 du plateau
            else if (id==13) { 
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
            else if (id==14) joueurCourant.credits = joueurCourant.credits + 100;
            else if (id==15) joueurCourant.credits = joueurCourant.credits + 10;
        }
        else {
            if (id==0) {
                int nbJoueursRestants=0;
                for (int i=0; i<4; i++){ //calcul du nombre de joueurs restants
                    if (tabJoueurs[i] != null) nbJoueursRestants = nbJoueursRestants+1;
                }
                joueurCourant.credits = joueurCourant.credits-10*nbJoueursRestants; //cagnotte du joueur courant qui s'actualise
                for (int i=0; i<4; i++){
                    if (tabJoueurs[i] != null && tabJoueurs[i] != joueurCourant) {
                        tabJoueurs[i].credits = tabJoueurs[i].credits+10; //credits des autres joueurs qui d'actualisent
                    }
                }
            }
            else if (id==1) joueurCourant.pion.reculer(3);
            else if (id==2) {
                joueurCourant.pion.caseassociee = plateau.plateaudejeu[10];
                joueurCourant.prison = true;
            }
            else if (id==3) {
                joueurCourant.pion.téléportation(0);
                // la bourse n'est pas doublée
            }
            else if (id==4) {
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
            else if (id==5) joueurCourant.credits = joueurCourant.credits+100;
            else if (id==6) joueurCourant.credits = joueurCourant.credits+50;
            else if (id==7) joueurCourant.recuperercarte(carteTiree);
            else if (id==8) {
                joueurCourant.credits = joueurCourant.credits-20;
                argentParcGratuit = argentParcGratuit+20;
            }
            else if (id==9) joueurCourant.credits = joueurCourant.credits+25;
            else if (id==10) {
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
                }
            }
            else if (id==11) joueurCourant.credits = joueurCourant.credits-25*joueurCourant.nbMaisonJoueur-100*joueurCourant.nbHotelJoueur;
            else if (id==12) {
                joueurCourant.credits = joueurCourant.credits-40;
                argentParcGratuit = argentParcGratuit+40;
            }
            else if (id==13) { //téléporter tous les joueurs à la case départ
                for (int i=0; i<4; i++){ 
                    if (tabJoueurs[i] != null) tabJoueurs[i].pion.téléportation(0);
                }
            }
            else if (id==14) {
                joueurCourant.credits = joueurCourant.credits-75;
                argentParcGratuit = argentParcGratuit+75;
            }
        }
    }
     
    public void payerloyer(Case caseassociee) {
        joueurCourant.credits = joueurCourant.credits-caseassociee.loyer;
        caseassociee.proprietaire.credits = caseassociee.proprietaire.credits+caseassociee.loyer;
    }
    
    public void téléporterPion(Pion pionAssocie, Case caseOuAller) {
        pionAssocie.caseassociee=caseOuAller;
    }
    
    public Carte faireActionCarteSimple(int choix) { // A VERIFIER
        if (choix == 0) { //choix=0 --> perdre des crédits
            joueurCourant.credits = joueurCourant.credits - 10;
            return null;
        }
        else { //choix=1 tirer une carte chance
            Carte laCarte = tirerCarte(paquetChance);
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
            return true;
        }
        else {
            return false;
        }
    }
}
