/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entites.Entraineur;
import entites.Equipe;
import entites.HistoriqueJoueur;
import entites.Joueur;
import entites.Matchs;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author katia
 */
@Stateless
public class EquipesFacade extends AbstractFacade<Equipe> implements EquipesFacadeLocal {

    @PersistenceContext(unitName = "GestionMatchsF-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EquipesFacade() {
        super(Equipe.class);
    }

    @Override
    public void CreerEquipe(String nomEquipe) {
        Equipe eq = new Equipe();
        int point = 0 ;
      //  List JoueurHListe = new ArrayList<Joueur>();
        //List EntraineurHListe = new ArrayList<Entraineur>();
        eq.setNomequipe(nomEquipe);
        eq.setPointEquipe(point);
      //  eq.setHistoriqueEntraineurs(EntraineurHListe);
       // eq.setHistoriqueJoueurs(EntraineurHListe);
        System.out.println("ok facade");
        em.persist(eq);
    }

    @Override
    public Collection<Equipe> ToutesLesEquipes() {
        List<Equipe> e;
        String text ="SELECT e FROM Equipe AS e";
        Query req = getEntityManager().createQuery(text);
        e = req.getResultList();
        return e;
    }
    
    @Override
    public Collection<Equipe> Classement() {
        
        String text ="SELECT e FROM Equipe AS e ORDER BY e.pointEquipe DESC";
        Query req = getEntityManager().createQuery(text);
        List<Equipe> eq = req.getResultList();
        return eq;
    }
    @Override
    public Equipe RechercherEquipe(String nomequipe) {
        Equipe eq;
        String tx= "SELECT eq from Equipe as eq where eq.nomequipe=:nomequipe";
        Query req = getEntityManager().createQuery(tx);
        req.setParameter("nomequipe", nomequipe);       
        eq=(Equipe)req.getSingleResult();
        if (!(eq==null)) {
                return eq;
        } else {
            return null ;
        }    }
    
      @Override
    public boolean RechercherEquipeB(String nomequipe) {
        List<Equipe> eq;
        boolean a;
        String tx= "SELECT eq from Equipe as eq where eq.nomequipe=:nomequipe";
        Query req = getEntityManager().createQuery(tx);
        req.setParameter("nomequipe", nomequipe);       
        eq=req.getResultList();
        if (eq.isEmpty()) 
        {
            a=true;
        } else {
            a=false;
        }    
        return a;
    }  

       
         @Override
     public Equipe RechercherEquipeid(int idequipe) {
        Equipe eq;
        String tx= "SELECT eq from Equipe as eq where eq.id =:idequipe";
        Query req = getEntityManager().createQuery(tx);
        req.setParameter("idequipe", idequipe);       
        eq=(Equipe)req.getSingleResult();
        if (!(eq==null)) {
                return eq;
        } else {
            return null ;
        }    }

    @Override
    public void AjoutPt(Equipe e, int pt) {
        e.setPointEquipe(e.getPointEquipe()+pt);
        em.merge(e);
    }

    @Override
    public Equipe rechercherEquipeJoueur(Joueur joueur) {
        Equipe e;
        String text = "SELECT hj FROM HistoriqueJoueur AS hj WHERE hj.joueur=:joueur AND hj.dateFinEq IS NULL";
        Query requete = em.createQuery(text);
        requete.setParameter("joueur", joueur);
        List<HistoriqueJoueur> liste = requete.getResultList();
        if(!liste.isEmpty()){
            return e = liste.get(0).getEquipeJoueur();
        }
        else{
        return null;
    }
    }
    
}
