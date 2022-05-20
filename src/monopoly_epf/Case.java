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
    boolean occupant = false;
    int loyerIn;
    int loyer;
    String action;
    int prixMaison;
    int idCase;
    int nbSallesMemeCouleur;
    int coordX;
    int coordY;
    int coordX_premiertick;
    int coordY_premiertick;
    
    
    public Case(int id) {
        switch(id) { //GERER LES LOYERS
            case(0) :
                nom = "L102";
                prixTerrain = 60;
                couleur = "marron";
                loyerIn = 2; //changer loyer
                prixMaison = 50;
                idCase = 0;
                coordX = 17;
                coordY = 639;
                coordX_premiertick=84;
                coordY_premiertick=630;
                break;
            case(1) : 
                nom = "L104";
                prixTerrain = 60;
                couleur = "marron";
                loyerIn = 4;
                prixMaison = 50;
                idCase = 1;
                coordX = 17;
                coordY = 508;
                coordX_premiertick=84;
                coordY_premiertick=499;
                break;
            case(2) :
                nom = "M01";
                prixTerrain = 100;
                couleur = "bleu ciel";
                loyerIn = 6;
                prixMaison = 50;
                idCase = 2;
                coordX = 17;
                coordY = 310;
                coordX_premiertick=84;
                coordY_premiertick=300;
                break;
            case(3) : 
                nom = "M02";
                prixTerrain = 100;
                couleur = "bleu ciel";
                loyerIn = 6;
                prixMaison = 50;
                idCase = 3;
                coordX = 17;
                coordY = 179;
                coordX_premiertick=84;
                coordY_premiertick=169;
                break;
            case(4) :
                nom = "M03";
                prixTerrain = 120;
                couleur = "bleu ciel";
                loyerIn = 8;
                prixMaison = 50;
                idCase = 4;
                coordX = 17;
                coordY = 113;
                coordX_premiertick=84;
                coordY_premiertick=105;
                break;
            case (5) :
                nom = "JLAB04";
                prixTerrain = 140;
                couleur = "violet";
                loyerIn = 10;
                prixMaison = 100;
                idCase = 5;
                coordX = 114;
                coordY = 17;
                coordX_premiertick=153;
                coordY_premiertick=84;
                break;
            case(6) :
                nom = "JLAB05";
                prixTerrain = 140;
                loyerIn = 10;
                couleur = "violet";
                prixMaison = 100;
                idCase = 6;
                coordX = 246;
                coordY = 17;
                coordX_premiertick=284;
                coordY_premiertick=84;
                break;
            case(7) : 
                nom = "JLAB06";
                prixTerrain = 160;
                couleur = "violet";
                loyerIn = 12;
                prixMaison = 100;
                idCase = 7;
                coordX = 312;
                coordY = 17;
                coordX_premiertick=350;
                coordY_premiertick=84;
                break;
            case(8) : 
                nom = "M101";
                prixTerrain = 180;
                couleur = "orange";
                loyerIn = 14;
                prixMaison = 100;
                idCase = 8;
                coordX = 444;
                coordY = 17;
                coordX_premiertick=482;
                coordY_premiertick=84;
                break;
            case(9) :
                nom = "M102";
                prixTerrain = 180;
                couleur = "orange";
                loyerIn = 14;
                prixMaison = 100;
                idCase = 9;
                coordX = 575;
                coordY = 17;
                coordX_premiertick=613;
                coordY_premiertick=84;
                break;
            case(10) :
                nom = "M103";
                prixTerrain = 200;
                couleur = "orange";
                loyerIn = 16;
                prixMaison = 100;
                idCase = 10;
                coordX = 641;
                coordY = 17;
                coordX_premiertick=679;
                coordY_premiertick=84;
                break;
            case(11) :
                nom = "bulle1";
                prixTerrain = 220;
                couleur = "rouge";
                loyerIn = 18;
                prixMaison = 150;
                idCase = 11;
                coordX = 738;
                coordY = 113;
                coordX_premiertick=700;
                coordY_premiertick=153;
                break;
            case(12) :
                nom = "bulle2";
                prixTerrain = 220;
                couleur = "rouge";
                loyerIn = 18;
                prixMaison = 150;
                idCase = 12;
                coordX = 738;
                coordY = 244;
                coordX_premiertick=700;
                coordY_premiertick=284;
                break;
            case(13) :
                nom = "bulle3";
                prixTerrain = 240;
                couleur = "rouge";
                loyerIn = 20;
                prixMaison = 150;
                idCase = 13;
                coordX = 738;
                coordY = 310;
                coordX_premiertick=700;
                coordY_premiertick=350;
                break;
            case(14) :
                nom = "H03";
                prixTerrain = 260;
                couleur = "jaune";
                loyerIn = 22;
                prixMaison = 150;
                idCase = 14;
                coordX = 738;
                coordY = 442;
                coordX_premiertick=700;
                coordY_premiertick=482;
                break;
            case(15) :
                nom = "H05";
                prixTerrain = 260;
                couleur = "jaune";
                loyerIn = 22;
                prixMaison = 150;
                idCase = 15;
                coordX = 738;
                coordY = 508;
                coordX_premiertick=700;
                coordY_premiertick=548;
                break;
            case(16) :
                nom = "H08";
                prixTerrain = 280;
                couleur = "jaune";
                loyerIn = 24;
                prixMaison = 150;
                idCase = 16;
                coordX = 738;
                coordY = 640;
                coordX_premiertick=700;
                coordY_premiertick=679;
                break;
            case(17) : 
                nom = "K03";
                prixTerrain = 300;
                couleur = "vert";
                loyerIn = 26;
                prixMaison = 200;
                idCase = 17;
                coordX = 641;
                coordY = 738;
                coordX_premiertick=631;
                coordY_premiertick=698;
                break;
            case(18) :
                nom = "K04";
                prixTerrain = 300;
                couleur = "vert";
                loyerIn = 26;
                prixMaison = 200;
                idCase = 18;
                coordX = 575;
                coordY = 738;
                coordX_premiertick=565;
                coordY_premiertick=698;
                break;
            case(19) :
                nom = "K05";
                prixTerrain = 320;
                couleur = "vert";
                loyerIn = 28;
                prixMaison = 200;
                idCase = 19;
                coordX = 444;
                coordY = 738;
                coordX_premiertick=434;
                coordY_premiertick=698;
                break;
            case(20) :
                nom = "I01";
                prixTerrain = 350;
                couleur = "bleu foncé";
                loyerIn = 35;
                prixMaison = 200;
                idCase = 20;
                coordX = 246;
                coordY = 738;
                coordX_premiertick=237;
                coordY_premiertick=698;
                break;
            case(21) :
                nom = "energy lab";
                prixTerrain = 400;
                couleur = "bleu foncé";
                loyerIn = 50;
                prixMaison = 200;
                idCase = 21;
                coordX = 114;
                coordY = 738;
                coordX_premiertick=105;
                coordY_premiertick=698;
                break;
            case(22) : //Il manque les gares et les compagnies --> Gares et compagnie à la suite des autres
                nom = "Cour";
                prixTerrain = 200;
                couleur = "gare";
                loyerIn = 25;
                prixMaison = 0; //à confirmer que c'est bien comme ça
                idCase = 22;
                coordX = 17;
                coordY = 376;
                break;
            case(23) :
                nom = "Amphithéâtre";
                prixTerrain = 200;
                couleur = "gare";
                loyerIn = 25;
                prixMaison = 0;
                idCase = 23;
                coordX = 378;
                coordY = 17;
                break;
            case(24) :
                nom = "Foyer";
                prixTerrain = 200;
                couleur = "gare";
                loyerIn = 25;
                prixMaison = 0;
                idCase = 24;
                coordX = 738;
                coordY = 376;
                break;
            case(25) :
                nom = "Bureau des Ours";
                prixTerrain = 200;
                couleur = "gare";
                loyerIn = 25;
                prixMaison = 0;
                idCase = 25;
                coordX = 378;
                coordY = 738;
                break;
            case(26) :
                nom = "Maintenance des micro-ondes";
                prixTerrain = 150;
                couleur = "Compagnie";
                loyerIn = 8; //c le plus petit qu'on puisse faire, pareil est-ce que c'est la bonne chose à mettre icià voir...
                prixMaison = 0;
                idCase = 26;
                coordX = 180;
                coordY = 17;
                break;
            case(27) :
                nom = "Maintenance des chauffages";
                prixTerrain = 150;
                couleur = "Compagnie";
                loyerIn = 8;
                prixMaison = 0;
                idCase = 27;
                coordX = 738;
                coordY = 574;
                break;
            case(28) :
                nom = "Caisse de communauté";
                prixTerrain = 0;
                couleur = "CC";
                loyerIn = 0;
                prixMaison = 0;
                idCase = 28;
                coordX = 17;
                coordY = 574;
                break;
            case(29) :
                nom = "Caisse de communauté";
                prixTerrain = 0;
                couleur = "CC";
                loyerIn = 0;
                prixMaison = 0;
                idCase = 29;
                coordX = 509;
                coordY = 17;
                break;
            case(30) :
                nom = "Caisse de communauté";
                prixTerrain = 0;
                couleur = "CC";
                loyerIn = 0;
                prixMaison = 0;
                idCase = 30;
                coordX = 509;
                coordY = 738;
                break;
            case(31) :
                nom = "Chance";
                prixTerrain = 0;
                couleur = "Chance";
                loyerIn = 0;
                prixMaison = 0;
                idCase = 31;
                coordX = 17;
                coordY = 244;
                break;
            case(32) :
                nom = "Chance";
                prixTerrain = 0;
                couleur = "Chance";
                loyerIn = 0;
                prixMaison = 0;
                idCase = 32;
                coordX = 738;
                coordY = 178;
                break;
            case(33) :
                nom = "Chance";
                prixTerrain = 0;
                couleur = "Chance";
                loyerIn = 0;
                prixMaison = 0;
                idCase = 33;
                coordX = 312;
                coordY = 738;
                break;
            case(34) :
                nom = "Tricherie";
                prixTerrain = 200;
                couleur = "Triche";
                loyerIn = 0;
                prixMaison = 0;
                idCase = 34;
                coordX = 17;
                coordY = 442;
                break;
            case(35) :
                nom = "Absence";
                prixTerrain = 100;
                couleur = "Absence";
                loyerIn = 0;
                prixMaison = 0;
                idCase = 35;
                coordX = 180;
                coordY = 738;
                break;
            case(36) :
                nom = "Début d'année";
                prixTerrain = 0;
                couleur = "Début d'année";
                loyerIn = 0;
                prixMaison = 0;
                idCase = 36;
                coordX = 29;
                coordY = 724;
                break;
            case(37) :
                nom = "Rattrapages";
                prixTerrain = 0;
                couleur = "Prison";
                loyerIn = 0;
                prixMaison = 0;
                idCase = 37;
                coordX = 29;
                coordY = 29;
                break;
            case(38) :
                nom = "Majoration";
                prixTerrain = 0;
                couleur = "Parc gratuit";
                loyerIn = 0;
                prixMaison = 0;
                idCase = 38;
                coordX = 726;
                coordY = 29;
                break;
            case(39) :
                nom = "Allez aux rattrapages";
                prixTerrain = 0;
                couleur = "Allez aux rattrapages";
                loyerIn = 0;
                prixMaison = 0;
                idCase = 39;
                coordX = 726;
                coordY = 738;
                break;
                
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
                break;
                case(1) : loyer = 20;
                break;
                case(2) : loyer = 30;
                break;
                case(3) : loyer = 30;
                break;
                case(4) : loyer = 40;
                break;
                case(5) : loyer = 50;
                break;
                case(6) : loyer = 50;
                break;
                case(7) : loyer = 60;
                break;
                case(8) : loyer = 70;
                break;
                case(9) : loyer = 70;
                break;
                case(10) : loyer = 80;
                break;
                case(11) : loyer = 90;
                break;
                case(12) : loyer = 90;
                break;
                case(13) : loyer = 100;
                break;
                case(14) : loyer = 110;
                break;
                case(15) : loyer = 110;
                break;
                case(16) : loyer = 120;
                break;
                case(17) : loyer = 130;
                break;
                case(18) : loyer = 130;
                break;
                case(19) : loyer = 150;
                break;
                case(20) : loyer = 175;
                break;
                case(21) : loyer = 200;
                break;
            }
        }
        
        else if (maison == 2) {
            switch (idCase) {
                case(0) : loyer = 30;
                break;
                case(1) : loyer = 60;
                break;
                case(2) : loyer = 80;
                break;
                case(3) : loyer = 80;
                break;
                case(4) : loyer = 100;
                break;
                case(5) : loyer = 150;
                break;
                case(6) : loyer = 150;
                break;
                case(7) : loyer = 180;
                break;
                case(8) : loyer = 200;
                break;
                case(9) : loyer = 200;
                break;
                case(10) : loyer = 220;
                break;
                case(11) : loyer = 250;
                break;
                case(12) : loyer = 250;
                break;
                case(13) : loyer = 300;
                break;
                case(14) : loyer = 330;
                break;
                case(15) : loyer = 330;
                break;
                case(16) : loyer = 360;
                break;
                case(17) : loyer = 390;
                break;
                case(18) : loyer = 390;
                break;
                case(19) : loyer = 450;
                break;
                case(20) : loyer = 500;
                break;
                case(21) : loyer = 600;
                break;
            }
        }
        
        else if (maison == 3) {
            switch (idCase) {
                case(0) : loyer = 90;
                break;
                case(1) : loyer = 180;
                break;
                case(2) : loyer = 270;
                break;
                case(3) : loyer = 270;
                break;
                case(4) : loyer = 300;
                break;
                case(5) : loyer = 450;
                break;
                case(6) : loyer = 450;
                break;
                case(7) : loyer = 500;
                break;
                case(8) : loyer = 550;
                break;
                case(9) : loyer = 550;
                break;
                case(10) : loyer = 600;
                break;
                case(11) : loyer = 700;
                break;
                case(12) : loyer = 700;
                break;
                case(13) : loyer = 750;
                break;
                case(14) : loyer = 800;
                break;
                case(15) : loyer = 800;
                break;
                case(16) : loyer = 850;
                break;
                case(17) : loyer = 900;
                break;
                case(18) : loyer = 900;
                break;
                case(19) : loyer = 1000;
                break;
                case(20) : loyer = 1100;
                break;
                case(21) : loyer = 1400;
                break;
            }
        }
        
        else if (maison == 4) {
            switch (idCase) {
                case(0) : loyer = 160;
                break;
                case(1) : loyer = 320;
                break;
                case(2) : loyer = 400;
                break;
                case(3) : loyer = 400;
                break;
                case(4) : loyer = 450;
                break;
                case(5) : loyer = 625;
                break;
                case(6) : loyer = 625;
                break;
                case(7) : loyer = 700;
                break;
                case(8) : loyer = 750;
                break;
                case(9) : loyer = 750;
                break;
                case(10) : loyer = 800;
                break;
                case(11) : loyer = 875;
                break;
                case(12) : loyer = 875;
                break;
                case(13) : loyer = 925;
                break;
                case(14) : loyer = 975;
                break;
                case(15) : loyer = 975;
                break;
                case(16) : loyer = 1025;
                break;
                case(17) : loyer = 1100;
                break;
                case(18) : loyer = 1100;
                break;
                case(19) : loyer = 1200;
                break;
                case(20) : loyer = 1300;
                break;
                case(21) : loyer = 1700;
                break;
            }
        }
        
        else if (maison == 5) {
            switch (idCase) {
                case(0) : loyer = 250;
                break;
                case(1) : loyer = 450;
                break;
                case(2) : loyer = 550;
                break;
                case(3) : loyer = 550;
                break;
                case(4) : loyer = 600;
                break;
                case(5) : loyer = 750;
                break;
                case(6) : loyer = 750;
                break;
                case(7) : loyer = 900;
                break;
                case(8) : loyer = 950;
                break;
                case(9) : loyer = 950;
                break;
                case(10) : loyer = 1000;
                break;
                case(11) : loyer = 1050;
                break;
                case(12) : loyer = 1050;
                break;
                case(13) : loyer = 1100;
                break;
                case(14) : loyer = 1150;
                break;
                case(15) : loyer = 1150;
                break;
                case(16) : loyer = 1200;
                break;
                case(17) : loyer = 1275;
                break;
                case(18) : loyer = 1275;
                break;
                case(19) : loyer = 1400;
                break;
                case(20) : loyer = 1500;
                break;
                case(21) : loyer = 2000;
                break;
            }
        }
    }
    
    public int lirePrixMaison(){
        return prixMaison;
    }
    
    public boolean construireMaison(boolean Darties) {
        if (maison == 5){
            return false;
        }
        else {
            maison+=1;
            if(maison==5) {
                proprietaire.nbHotelJoueur++;
            }
            else {
                proprietaire.nbMaisonJoueur++;
            }
            //prixMaison = prixMaison + prixMaison; //pas capté à quoi ça servait ça
            if(Darties!=true) {
                proprietaire.credits = proprietaire.credits-prixMaison; //vérifier que c'est pas déjà fait qq part ça aussi
            }
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
