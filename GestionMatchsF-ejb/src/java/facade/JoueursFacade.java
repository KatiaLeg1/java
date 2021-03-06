/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entites.Equipe;
import entites.HistoriqueJoueur;
import entites.Joueur;
import entites.Matchs;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;


/**
 *
 * @author katia
 */
@Stateless
public class JoueursFacade extends AbstractFacade<Joueur> implements JoueursFacadeLocal {

    @PersistenceContext(unitName = "GestionMatchsF-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public JoueursFacade() {
        super(Joueur.class);
    }


    @Override
    public void CreerJoueur(String nomJ, String prenomJ) {
        Joueur j = new Joueur();
        Date dateInterdiction =null;
        j.setNomPersonne(nomJ);
        j.setPrenomPersonne(prenomJ);
        j.setDateInterdiction(dateInterdiction);
        em.persist(j);

    }

    @Override
    public Joueur SupprimerJoueur(long id) {
        Joueur j = null;
        String txt = "SELECT jo FROM Joueur AS jo WHERE jo.id=:id";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("id", id);        
        List<Joueur> res = req.getResultList();
        if (res.size() >= 1)
        {
              j = (Joueur) res.get(0); 
              em.remove(j);
        }
        return j;
    }

    @Override
    public List<Joueur> recupJoueur() {
        String req = "SELECT j from Joueur as j";
        Query requete = em.createQuery(req);
        List<Joueur> liste = requete.getResultList();
        return liste;
    }


    @Override
    public boolean RechercherJoueur(String nomPersonne, String prenomPersonne) {
        Joueur j = null;
        boolean a ;
        String txt = "SELECT j FROM Joueur AS j WHERE j.nomPersonne=:nomPersonne and j.prenomPersonne=:prenomPersonne";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("nomPersonne", nomPersonne);
        req = req.setParameter("prenomPersonne", prenomPersonne);
        j=(Joueur)req.getSingleResult();
        if (!(j==null)) 
        {
            a = true;
        } else {
            a=false;
        } 
         return a;
}
    @Override
    public void affecterJoueur(HistoriqueJoueur histo, Equipe eq, Date dateDebut) {
        histo.setEquipeJoueur(eq);
        histo.setDateDebutEq(dateDebut);
        em.merge(histo);
    }

    @Override
    public void transfererJoueur(HistoriqueJoueur histo, Equipe eq, Date dateDebut, Date dateFin) {
        histo.setDateFinEq(dateFin);
        em.merge(histo);
        
        affecterJoueur(histo, eq, dateDebut);
    }

    @Override
    public Joueur rechercherJoueurId(Long id) {
        Joueur j = null;
        String txt = "SELECT j FROM Joueur AS j WHERE j.id=:id";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("id", id);
        j=(Joueur)req.getSingleResult();
        if (!(j==null)) 
        {
                return j;
        }
        
        else {
            return null;
        }
    }
    @Override
    public List<Joueur> AfficherTousLesJoueurs() {
        List<Joueur> j;
        String text ="SELECT j FROM Joueur AS j";
        Query req = getEntityManager().createQuery(text);
        j = req.getResultList();
        return j;
    }
    @Override
    public void SanctionJ(int id, Date dateI) {
        
        String txt = "SELECT j FROM Joueur AS j WHERE j.id=:id";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("id", id);
        Joueur j =(Joueur)req.getSingleResult();
        j.setDateInterdiction(dateI);
        em.merge(j);
    }
    
    @Override
    public List<Joueur> TousLesJou() {
        List<Joueur> j;
        String text ="SELECT j FROM Joueur AS j";
        Query req = getEntityManager().createQuery(text);
        j = req.getResultList();
        return j;
    }
     @Override
    public List<Joueur> TousLesJouEq(int eq) {
        String req = "SELECT j from Joueur as j where j.historiqueJoueurs.equipeJoueur.id=:eq ";
        Query requete = em.createQuery(req);
        requete.setParameter("eq",eq);
        List<Joueur> liste = requete.getResultList();
        return liste; 
            }
    
     @Override
    public List<Joueur> TousLesJouLibres() {
        String req = "select j from Joueur as j where not exists (select hj from HistoriqueJoueur hj where j.id = hj.joueur.id)";
        Query requete = em.createQuery(req);
        List<Joueur> liste = requete.getResultList();
        return liste; 
    }

    @Override
    public List<Joueur> joueurMatch(Matchs m) {
        String requ= " SELECT j from Joueur as j where j.matchsJoueur1=:m ";
        Query req = em.createQuery(requ);
        req.setParameter("m",m);
        List<Joueur> liste = req.getResultList();
        return liste;
    }

   




}