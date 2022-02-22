/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package monopoly_epf;

/**
 *
 * @author shirl
 */
public class Partie {
    Plateau plateau;
    Joueur joueurCourant;
    Paquet paquetChance;
    Paquet paquetCommunaute;
    Joueur [] tabJoueurs = new Joueur[4];
    Des de1;
    Des de2;
    int compteurDouble;
    
    public void tourJoueur() {
        
    }
    
    public void changerJoueur() { //penser à réinitialiser le compteur double
        
    }
    
    public int lancerDes() { //lire double et initialiser compteur
        
    }
    
    public boolean lireDouble() {
        if (de1.valeur == de2.valeur) {
            return true;
        }
        else {
            return false;
        }
    }
    
    public void deplacerPion(Pion pionAssocie, int nbcases, Case caseInitiale) {
        
    }
    
    public boolean faireAction(){
        
    }
    
    public boolean acheter(){
        
    }
    
    public boolean payer() {
        
    }
    
    public Carte tirerCarte() {
        
    }
    
    public void faireActionCarte(Carte carteTiree) {
        
    }
    
    public void eliminationJoueur() {
        
    }
    
    public void finDePartie() {
        
    }
    
}
