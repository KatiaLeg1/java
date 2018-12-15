/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entites.Entraineur;
import entites.Equipe;
import entites.HistoriqueEntraineur;
import entites.HistoriqueJoueur;
import entites.Joueur;
import facade.EntraineurFacadeLocal;
import facade.EquipesFacadeLocal;
import facade.HistoriqueEntraineurFacadeLocal;
import facade.HistoriqueJoueurFacadeLocal;
import javax.ejb.Stateless;
import facade.JoueursFacade;
import facade.JoueursFacadeLocal;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author katia
 */
@Stateless
public class SessionEnt implements gestionEntraineurLocal {

    @EJB
    private HistoriqueEntraineurFacadeLocal historiqueEntraineurFacade;

    @EJB
    private EntraineurFacadeLocal entraineurFacade;

    @EJB
    private EquipesFacadeLocal equipesFacade;

    @EJB
    private HistoriqueJoueurFacadeLocal historiqueJoueurFacade;

    @EJB
    private JoueursFacadeLocal joueursFacade;
    
    
    @Override
    public void affectationJoueur(long id, Entraineur e, Date dateDebutHJ) {
        Joueur j = joueursFacade.rechercherJoueurId(id);
        HistoriqueJoueur hj = historiqueJoueurFacade.rechercherHistorique(j);
        Equipe eq = historiqueEntraineurFacade.EqActuelleEnt(e);
        if(j!=null){
        HistoriqueJoueur h = historiqueJoueurFacade.rechercherHistorique(j);
            if(h!=null){
                historiqueJoueurFacade.ModifHistoJ(hj, dateDebutHJ);
                historiqueJoueurFacade.creerHJoueur(dateDebutHJ, j, eq);   
            }
            else
            {
                historiqueJoueurFacade.creerHJoueur(dateDebutHJ, j, eq);   
            }
        }
        else{
            System.out.println("Joueur sous contrat");
                }
        /*if(h!=null){
                Equipe eq;
                eq = h.getEquipeJoueur();
                Date dateDebut;
                dateDebut = h.getDateDebutEq();
                joueursFacade.affecterJoueur(h, eq, dateDebut);
            
        }*/   
    }

    @Override
    public Equipe rechercheEquipeParEntraineur(Entraineur e) {
        return null;
    }
        
    
    @Override
    public void transfertJoueur(String nom, String prenom) {
        Joueur j = null;
        joueursFacade.RechercherJoueur(nom, prenom);
    
        if(j!=null){
        HistoriqueJoueur h = null;
        historiqueJoueurFacade.rechercherHistorique(j);
        
            if(h!=null){
                Equipe eq;
                eq = h.getEquipeJoueur();
                Date dateDebut;
                dateDebut = h.getDateDebutEq();
                Date dateFin;
                dateFin = h.getDateFinEq();
                joueursFacade.transfererJoueur(h, eq, dateDebut, dateFin);
            }
        }
    }

    @Override
    public Joueur rechercherJoueurId(long id) {
        Joueur j = null;
        j = joueursFacade.rechercherJoueurId(id);
        return j;
    }

    @Override
    public List<Joueur> affichageJoueurs() {
        return joueursFacade.recupJoueur();
    }

    @Override
    public Entraineur AuthEnt(String log, String mdp) {
        return entraineurFacade.AuthEnt(log, mdp);
    }
   
    
   
}