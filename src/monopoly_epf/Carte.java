/*
 * BOCAHUT Manon - FEVRIER Titouan - CHAUVET Ylan - BAYLE Shirley
 */
package monopoly_epf;

/**
 *
 * @author shirl
 */
public class Carte {
    boolean chance;
    boolean communaute;
    String texte;
    int idCarte;
    
    public Carte(int id, boolean com){
        idCarte = id;
        if (com) {
            communaute = true;
            chance = false;
            if (id == 0) texte="Payez 20 crédits pour votre place de vélo.";
            else if (id == 1) texte="Vous cotisez au BDE, payez 50 crédits.";
            else if (id == 2) texte="Allez aux rattrapages, ne passez pas par la case départ, vous ne recevez pas votre bourse.";
            else if (id == 3) texte="Vous êtes libéré de rattrapages, cette carte peut être conservée jusqu’à ce qu’elle soit utilisée.";
            else if (id == 4) texte="Perdez 10 crédits ou tirez une carte chance.";
            else if (id == 5) texte="Vous gagnez EPF got’s talents recevez 25 crédit.";
            else if (id == 6) texte="Vos matières validées sont trop justes, la commission a évalué votre dossier, payez 25 crédits par matière validée et 100 crédits par module.";
            else if (id == 7) texte="Vous offrez une fleur pour la saint valentin à tous les joueurs du plateau, payez 15 crédits par personne. Les autres joueurs reçoivent chacun 15 crédits.";
            else if (id == 8) texte="Vous faites partie des Tyrans d’eau, le vent vous pousse de 8 cases.";
            else if (id == 9) texte="Vous dormez en amphi, loupez un tour.";
            else if (id == 10) texte="Quelqu’un vous a donné CATIA, recevez 150 crédits.";
            else if (id == 11) texte="Vous assistez à la remise à niveau de début d’année, touchez 100 crédits.";
            else if (id == 12) texte="Monsieur Mahou rajoute un cours, reculez en K04. Vous ne touchez pas votre bourse.";
            else if (id == 13) texte="Vous venez vous plaindre de Ballester, avancez jusqu’au Bureau Des Ours, si vous passez par le début d’année touchez votre bourse.";
            else if (id == 14) texte="Jousset vous voit réussir un trick-shot avec une brosse, il vous donne 10 crédits.";
            else if (id == 15) texte="Recevez votre bourse annuelle, 100 crédits";
        }
        else {
            communaute = false;
            chance = true;
            if (id == 0) texte="C’est votre anniversaire, chaque joueur vous cède 10 crédits.";
            else if (id == 1) texte="Reculez de 3 cases.";
            else if (id == 2) texte="Allez aux rattrapages, ne passez pas par la case début d’année, vous ne recevez pas votre bourse.";
            else if (id == 3) texte="Avancez jusqu’à la case Début d’année.";
            else if (id == 4) texte="Vous allez aux partiels en K03, avancez jusqu’à la salle, si vous passez par la case Début d’année recevez votre bourse.";
            else if (id == 5) texte="Vous majorez au BE, recevez 100 crédits";
            else if (id == 6) texte="Jousset vous octroie 50 crédits";
            else if (id == 7) texte="Vous êtes libéré de rattrapages, cette carte peut être conservée jusqu’à ce qu’elle soit utilisée.";
            else if (id == 8) texte="Vous payez une tournée au bar, payez 20 crédits";
            else if (id == 9) texte="Vous aidez au tutorat, recevez 25 crédits.";
            else if (id == 10) texte="Rendez-vous en amphi, si vous passez par la case Début d’année recevez votre bourse.";
            else if (id == 11) texte="Vos matières validées sont trop justes, la commission a évalué votre dossier, payez 25 crédits par matière validée et 100 crédits par module";
            else if (id == 12) texte="Mail de la scolarité, votre comportement a été jugé irrespectueux, payez 40 crédits.";
            else if (id == 13) texte="Tous les joueurs participent à la JPO, ils retournent sur la case Début d’année.";
            else if (id == 14) texte="Vous perdez contre Jousset au poker, perdez 75 crédits.";
            else if (id == 15) texte="Darties adore votre Monopoly il vous donne un cheatcode pour valider une matière dans la salle que vous voulez.";
        }
    }
    
    public String lireCarte(){
        return texte;
    }
    
}
