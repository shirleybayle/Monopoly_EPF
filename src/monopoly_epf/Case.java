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
    int loyerIn;
    int loyer;
    String action;
    int prixMaison;
    int idCase;
    int nbSallesMemeCouleur;
    
    
    public Case(int id) {
        switch(id) { //GERER LES LOYERS
            case(0) :
                nom = "L104";
                prixTerrain = 60;
                couleur = "marron";
                loyerIn = 2; //changer loyer
                prixMaison = 50;
                idCase = 0;
                break;
            case(1) : 
                nom = "L102";
                prixTerrain = 60;
                couleur = "marron";
                loyerIn = 4;
                prixMaison = 50;
                idCase = 1;
                break;
            case(2) :
                nom = "M01";
                prixTerrain = 100;
                couleur = "bleu ciel";
                loyerIn = 6;
                prixMaison = 50;
                idCase = 2;
                break;
            case(3) : 
                nom = "M02";
                prixTerrain = 100;
                couleur = "bleu ciel";
                loyerIn = 6;
                prixMaison = 50;
                idCase = 3;
                break;
            case(4) :
                nom = "M03";
                prixTerrain = 120;
                couleur = "bleu ciel";
                loyerIn = 8;
                prixMaison = 50;
                idCase = 4;
                break;
            case (5) :
                nom = "JLAB4";
                prixTerrain = 140;
                couleur = "violet";
                loyerIn = 10;
                prixMaison = 100;
                idCase = 5;
                break;
            case(6) :
                nom = "JLAB5";
                prixTerrain = 140;
                loyerIn = 10;
                couleur = "violet";
                prixMaison = 100;
                idCase = 6;
                break;
            case(7) : 
                nom = "JLAB6";
                prixTerrain = 160;
                couleur = "violet";
                loyerIn = 12;
                prixMaison = 100;
                idCase = 7;
                break;
            case(8) : 
                nom = "M101";
                prixTerrain = 180;
                couleur = "orange";
                loyerIn = 14;
                prixMaison = 100;
                idCase = 8;
                break;
            case(9) :
                nom = "M102";
                prixTerrain = 180;
                couleur = "orange";
                loyerIn = 14;
                prixMaison = 100;
                idCase = 9;
                break;
            case(10) :
                nom = "M103";
                prixTerrain = 200;
                couleur = "orange";
                loyerIn = 16;
                prixMaison = 100;
                idCase = 10;
                break;
            case(11) :
                nom = "bulle1";
                prixTerrain = 220;
                couleur = "rouge";
                loyerIn = 18;
                prixMaison = 150;
                idCase = 11;
                break;
            case(12) :
                nom = "bulle2";
                prixTerrain = 220;
                couleur = "rouge";
                loyerIn = 18;
                prixMaison = 150;
                idCase = 12;
                break;
            case(13) :
                nom = "bulle3";
                prixTerrain = 240;
                couleur = "rouge";
                loyerIn = 20;
                prixMaison = 150;
                idCase = 13;
                break;
            case(14) :
                nom = "H03";
                prixTerrain = 260;
                couleur = "jaune";
                loyerIn = 22;
                prixMaison = 150;
                idCase = 14;
                break;
            case(15) :
                nom = "H05";
                prixTerrain = 260;
                couleur = "jaune";
                loyerIn = 22;
                prixMaison = 150;
                idCase = 15;
                break;
            case(16) :
                nom = "H08";
                prixTerrain = 280;
                couleur = "jaune";
                loyerIn = 24;
                prixMaison = 150;
                idCase = 16;
                break;
            case(17) : 
                nom = "K03";
                prixTerrain = 300;
                couleur = "vert";
                loyerIn = 26;
                prixMaison = 200;
                idCase = 17;
                break;
            case(18) :
                nom = "K04";
                prixTerrain = 300;
                couleur = "vert";
                loyerIn = 26;
                prixMaison = 200;
                idCase = 18;
                break;
            case(19) :
                nom = "K05";
                prixTerrain = 320;
                couleur = "vert";
                loyerIn = 28;
                prixMaison = 200;
                idCase = 19;
                break;
            case(20) :
                nom = "I01";
                prixTerrain = 350;
                couleur = "bleu foncé";
                loyerIn = 35;
                prixMaison = 200;
                idCase = 20;
                break;
            case(21) :
                nom = "energy lab";
                prixTerrain = 400;
                couleur = "bleu foncé";
                loyerIn = 50;
                prixMaison = 200;
                idCase = 21;
                break;
                //Il manque les gares et les compagnies
            
                
        }
        loyer = loyerIn;
    }
    
    
    public int lirePrixTerrain(){
        return prixTerrain;
    }
    
    public int lireLoyer() {
        return loyer;
    }
    
    public void changerLoyer() {
        if (proprietaire == null) {
            loyer = loyerIn;
        }
        
        else if (maison==0) {
            int compteurCouleur =0;
            if (couleur == "marron" || couleur == "bleu foncé") {
                if (nbSallesMemeCouleur == 2) {
                    loyer = loyerIn*2;                }
            }
            else if (couleur != null) {
                if (nbSallesMemeCouleur == 3) {
                    loyer = loyerIn*2;
                }
            }
        }
        
        else if (maison == 1) {
            switch (idCase) {
                case(0) : loyer = 10;
                case(1) : loyer = 20;
                case(2) : loyer = 30;
                case(3) : loyer = 30;
                case(4) : loyer = 40;
                case(5) : loyer = 50;
                case(6) : loyer = 50;
                case(7) : loyer = 60;
                case(8) : loyer = 70;
                case(9) : loyer = 70;
                case(10) : loyer = 80;
                case(11) : loyer = 90;
                case(12) : loyer = 90;
                case(13) : loyer = 100;
                case(14) : loyer = 110;
                case(15) : loyer = 110;
                case(16) : loyer = 120;
                case(17) : loyer = 130;
                case(18) : loyer = 130;
                case(19) : loyer = 150;
                case(20) : loyer = 175;
                case(21) : loyer = 200;
            }
        }
        
        else if (maison == 2) {
            switch (idCase) {
                case(0) : loyer = 30;
                case(1) : loyer = 60;
                case(2) : loyer = 80;
                case(3) : loyer = 80;
                case(4) : loyer = 100;
                case(5) : loyer = 150;
                case(6) : loyer = 150;
                case(7) : loyer = 180;
                case(8) : loyer = 200;
                case(9) : loyer = 200;
                case(10) : loyer = 220;
                case(11) : loyer = 250;
                case(12) : loyer = 250;
                case(13) : loyer = 300;
                case(14) : loyer = 330;
                case(15) : loyer = 330;
                case(16) : loyer = 360;
                case(17) : loyer = 390;
                case(18) : loyer = 390;
                case(19) : loyer = 450;
                case(20) : loyer = 500;
                case(21) : loyer = 600;
            }
        }
        
        else if (maison == 3) {
            switch (idCase) {
                case(0) : loyer = 90;
                case(1) : loyer = 180;
                case(2) : loyer = 270;
                case(3) : loyer = 270;
                case(4) : loyer = 300;
                case(5) : loyer = 450;
                case(6) : loyer = 450;
                case(7) : loyer = 500;
                case(8) : loyer = 550;
                case(9) : loyer = 550;
                case(10) : loyer = 600;
                case(11) : loyer = 700;
                case(12) : loyer = 700;
                case(13) : loyer = 750;
                case(14) : loyer = 800;
                case(15) : loyer = 800;
                case(16) : loyer = 850;
                case(17) : loyer = 900;
                case(18) : loyer = 900;
                case(19) : loyer = 1000;
                case(20) : loyer = 1100;
                case(21) : loyer = 1400;
            }
        }
        
        else if (maison == 4) {
            switch (idCase) {
                case(0) : loyer = 160;
                case(1) : loyer = 320;
                case(2) : loyer = 400;
                case(3) : loyer = 400;
                case(4) : loyer = 450;
                case(5) : loyer = 625;
                case(6) : loyer = 625;
                case(7) : loyer = 700;
                case(8) : loyer = 750;
                case(9) : loyer = 750;
                case(10) : loyer = 800;
                case(11) : loyer = 875;
                case(12) : loyer = 875;
                case(13) : loyer = 925;
                case(14) : loyer = 975;
                case(15) : loyer = 975;
                case(16) : loyer = 1025;
                case(17) : loyer = 1100;
                case(18) : loyer = 1100;
                case(19) : loyer = 1200;
                case(20) : loyer = 1300;
                case(21) : loyer = 1700;
            }
        }
        
        else if (maison == 5) {
            switch (idCase) {
                case(0) : loyer = 250;
                case(1) : loyer = 450;
                case(2) : loyer = 550;
                case(3) : loyer = 550;
                case(4) : loyer = 600;
                case(5) : loyer = 750;
                case(6) : loyer = 750;
                case(7) : loyer = 900;
                case(8) : loyer = 950;
                case(9) : loyer = 950;
                case(10) : loyer = 1000;
                case(11) : loyer = 1050;
                case(12) : loyer = 1050;
                case(13) : loyer = 1100;
                case(14) : loyer = 1150;
                case(15) : loyer = 1150;
                case(16) : loyer = 1200;
                case(17) : loyer = 1275;
                case(18) : loyer = 1275;
                case(19) : loyer = 1400;
                case(20) : loyer = 1500;
                case(21) : loyer = 2000;
            }
        }
    }
    
    public int lirePrixMaison(){
        return prixMaison;
    }
    
    public boolean construireMaison() {
        if (maison == 5){
            return false;
        }
        else {
            maison = maison++;
            prixMaison = prixMaison + prixMaison;
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
