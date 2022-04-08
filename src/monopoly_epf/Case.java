/*
 * BOCAHUT Manon - FEVRIER Titouan - CHAUVET Ylan - BAYLE Shirley
 */
package monopoly_epf;

/**
 *
 * @author shirl
 */
public class Case {
    String nom;
    int prixTerrain;
    String couleur;
    int maison;
    Joueur proprietaire;
    Pion occupant;
    int loyer;
    String action;
    int prixMaison;
    int idCase;
    
    public Case(int id) {
        switch(id) { //GERER LES LOYERS
            case(0) :
                nom = "L104";
                prixTerrain = 60;
                couleur = "marron";
                loyer = 2; //changer loyer
                prixMaison = 50;
                idCase = 0;
                break;
            case(1) : 
                nom = "L102";
                prixTerrain = 60;
                couleur = "marron";
                loyer = 4;
                prixMaison = 50;
                idCase = 1;
                break;
            case(2) :
                nom = "M01";
                prixTerrain = 100;
                couleur = "bleu ciel";
                loyer = 6;
                prixMaison = 50;
                idCase = 2;
                break;
            case(3) : 
                nom = "M02";
                prixTerrain = 100;
                couleur = "bleu ciel";
                loyer = 6;
                prixMaison = 50;
                idCase = 3;
                break;
            case(4) :
                nom = "M03";
                prixTerrain = 120;
                couleur = "bleu ciel";
                loyer = 8;
                prixMaison = 50;
                idCase = 4;
                break;
            case (5) :
                nom = "JLAB4";
                prixTerrain = 140;
                couleur = "violet";
                loyer = 10;
                prixMaison = 100;
                idCase = 5;
                break;
            case(6) :
                nom = "JLAB5";
                prixTerrain = 140;
                loyer = 10;
                couleur = "violet";
                prixMaison = 100;
                idCase = 6;
                break;
            case(7) : 
                nom = "JLAB6";
                prixTerrain = 160;
                couleur = "violet";
                loyer = 12;
                prixMaison = 100;
                idCase = 7;
                break;
            case(8) : 
                nom = "M101";
                prixTerrain = 180;
                couleur = "orange";
                loyer = 14;
                prixMaison = 100;
                idCase = 8;
                break;
            case(9) :
                nom = "M102";
                prixTerrain = 180;
                couleur = "orange";
                loyer = 14;
                prixMaison = 100;
                idCase = 9;
                break;
            case(10) :
                nom = "M103";
                prixTerrain = 200;
                couleur = "orange";
                loyer = 16;
                prixMaison = 100;
                idCase = 10;
                break;
            case(11) :
                nom = "bulle1";
                prixTerrain = 220;
                couleur = "rouge";
                loyer = 18;
                prixMaison = 150;
                idCase = 11;
                break;
            case(12) :
                nom = "bulle2";
                prixTerrain = 220;
                couleur = "rouge";
                loyer = 18;
                prixMaison = 150;
                idCase = 12;
                break;
            case(13) :
                nom = "bulle3";
                prixTerrain = 240;
                couleur = "rouge";
                loyer = 20;
                prixMaison = 150;
                idCase = 13;
                break;
            case(14) :
                nom = "H03";
                prixTerrain = 260;
                couleur = "jaune";
                loyer = 22;
                prixMaison = 150;
                idCase = 14;
                break;
            case(15) :
                nom = "H05";
                prixTerrain = 260;
                couleur = "jaune";
                loyer = 22;
                prixMaison = 150;
                idCase = 15;
                break;
            case(16) :
                nom = "H08";
                prixTerrain = 280;
                couleur = "jaune";
                loyer = 24;
                prixMaison = 150;
                idCase = 16;
                break;
            case(17) : 
                nom = "K03";
                prixTerrain = 300;
                couleur = "vert";
                loyer = 26;
                prixMaison = 200;
                idCase = 17;
                break;
            case(18) :
                nom = "K04";
                prixTerrain = 300;
                couleur = "vert";
                loyer = 26;
                prixMaison = 200;
                idCase = 18;
                break;
            case(19) :
                nom = "K05";
                prixTerrain = 320;
                couleur = "vert";
                loyer = 28;
                prixMaison = 200;
                idCase = 19;
                break;
            case(20) :
                nom = "I01";
                prixTerrain = 350;
                couleur = "bleu foncé";
                loyer = 35;
                prixMaison = 200;
                idCase = 20;
                break;
            case(21) :
                nom = "energy lab";
                prixTerrain = 400;
                couleur = "bleu foncé";
                loyer = 50;
                prixMaison = 200;
                idCase = 21;
                break;
                //Il manque les gares et les compagnies
            
                
        }
    }
    
    
    public int lirePrixTerrain(){
        return prixTerrain;
    }
    
    public int lireLoyer() {
        return loyer;
    }
    
    public void changerLoyer(int nouveauloyer) {
        loyer = nouveauloyer;
    }
    
    public int lirePrixMaison(){
        return prixMaison;
    }
    
    public boolean construireMaison(int nbMaison) {
        if (maison+nbMaison > 5){
            return false;
        }
        else {
            maison = maison+nbMaison;
            return true;
        }
    }
    
    public Joueur lireProprio(){
        return proprietaire;
    }
    
    public String lireAction(){
        return action;
    }
}
