/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entites.Arbitre;
import entites.Equipe;
import entites.Faute;
import entites.Joueur;
import entites.Matchs;
import java.util.ArrayList;
import java.util.Date;
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
public class MatchFacade extends AbstractFacade<Matchs> implements MatchFacadeLocal {

    @PersistenceContext(unitName = "GestionMatchsF-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MatchFacade() {
        super(Matchs.class);
    }

    @Override
    public void CreerMatch(Date date, String heure, Equipe equipeUn, Equipe equipeDeux, Arbitre aribtre) {
        Matchs m = new Matchs();
        int butUn = 0;
        int butDeux = 0;
        
        m.setButEquipeUn(butUn);
        m.setButEquipeDeux(butDeux);
        m.setDateMatch(date);
        m.setHeure(heure);
        m.setEquipeUn(equipeUn);
        m.setEquipeDeux(equipeDeux);
        m.setArbitre(aribtre);
     
        em.persist(m);

    }

    @Override
    public void ModifierPoint(int butEquipeUn, int butEquipeDeux, Matchs m) {
        m.setButEquipeUn(butEquipeUn);
        m.setButEquipeDeux(butEquipeDeux);
        em.merge(m);
    }

    @Override
    public boolean ArbitreLibre(Arbitre arbitre, Date dateMatch) {
        boolean b=false;
        Query requete = em.createQuery("SELECT m from Matchs as m where m.arbitre=:arbitre");
        requete.setParameter("arbitre", arbitre);     
        List<Matchs> liste =  requete.getResultList();
        if (!(liste.isEmpty()))
        {
            for (Matchs m : liste)
            {
                if (m.getDateMatch().equals(dateMatch))
                {
                    b=  false;
                }
                else b=true;
            }
        }
        else
            b= true;
        return b;
    }

    @Override
    public boolean EquipeLibre(Equipe equipe, Date dateMatch) {
        boolean b = false;
        String text ="SELECT m from Matchs as m where m.equipeUn=:equipe union SELECT m from Matchs as m where m.EquipeDeux=:equipe ";
        Query req = getEntityManager().createQuery(text);
        req.setParameter("equipe", equipe);     
        List<Matchs> liste =  req.getResultList();
        if (!(liste.isEmpty()))
        {
            for (Matchs m : liste)
            {
                if (m.getDateMatch().equals(dateMatch))
                {
                  b=  false;
                }
                else b=true;
            }
        }
        else
         b= true;
        return b;
        
    }

    @Override
    public void ModifierMatch(Matchs m, Date date, String heure) {
        m.setDateMatch(date);
        m.setHeure(heure);
        em.merge(m);
    }

    @Override
    public Matchs RechercherMatch(Equipe nomEq1, Equipe nomEq2, Date date) {
        Matchs m =null;
        Query req = em.createQuery("select m from Matchs as m where m.equipeUn=:nomEq1 and m.EquipeDeux=:nomEq2 and m.dateMatch=:date");
        req.setParameter("nomEq1", nomEq1);     
        req.setParameter("nomEq2", nomEq2);     
        req.setParameter("date", date);   
        m=(Matchs)req.getSingleResult();
        return m;
    }
    @Override
     public Matchs MatchEquipedate(Long ident, Date dateMatch) {
        Matchs m=null;
        Query requete = em.createQuery("SELECT m from Matchs as m where (m.equipeUn.id=:equipe OR m.EquipeDeux.id=:equipe) And m.dateMatch=:dateMatch");
        requete.setParameter("equipe", ident);     
        requete.setParameter("dateMatch", dateMatch);  
        List<Matchs> liste =  requete.getResultList();
        if (!liste.isEmpty())
        {
           m=liste.get(0);
        }
        return m;    
    }
     @Override
    public List<Matchs> RechercherTousLesMatchs() {
         List<Matchs> m;
        String text ="SELECT m FROM Matchs AS m";
        Query req = getEntityManager().createQuery(text);
        m = req.getResultList();
        return m;
    }

    @Override
    public Matchs rechercherMatchID(int id) {
        // pout toi sophie
        Matchs m ;
        String tx= "select m from Matchs as m where m.id=:id";
        Query req = getEntityManager().createQuery(tx);
        req.setParameter("id", id);     
        List<Matchs> liste =  req.getResultList();
        if (!liste.isEmpty())
            return liste.get(0);
        else return null;    

    }

    @Override
    public List<Matchs> Matchdate(Date date) {
        String tex ="SELECT m from Matchs as m where m.dateMatch=:date";
                Query req = getEntityManager().createQuery(tex);
        req.setParameter("date", date);     
        List<Matchs> liste =  req.getResultList();
        return liste;
    }
    

    @Override
    public List<Matchs> MatchdateInt(Date date1,Date date2) {
        String tex ="SELECT m from Matchs as m where m.dateMatch>=:date1 and m.dateMatch<=:date2";
                Query req = getEntityManager().createQuery(tex);
        req.setParameter("date1", date1); 
                req.setParameter("date2", date2);     

        List<Matchs> liste =  req.getResultList();
        return liste;
}
    
@Override
    public List<Matchs> AfficherMatchsEq(int ide) {
        List<Matchs> m;
        String text ="SELECT m FROM Matchs AS m WHERE m.equipeUn.id=:ide union SELECT m FROM Matchs AS m WHERE m.EquipeDeux.id=:ide";
        Query req = getEntityManager().createQuery(text);
        req.setParameter("ide", ide);  
        m = req.getResultList();
        return m;
    }
    @Override
     public List<Matchs> Matcharb(Arbitre a) {
        String tex ="SELECT m from Matchs as m where m.arbitre=:a";
        Query req = getEntityManager().createQuery(tex);
        req.setParameter("a", a);     
        List<Matchs> liste =  req.getResultList();
        return liste;
    }
     
    @Override
    public void CreerComposition1(Matchs ma, Equipe eq, List<Joueur> jou) {
        Equipe eq1 = ma.getEquipeUn();
        Equipe eq2 = ma.getEquipeDeux();
        int i =0;
        Joueur x = null;
        if (eq1==eq || eq2==eq)
        {
            ma.setComposition1(jou);
            em.merge(ma);  
        }
        
    }
    @Override
    public List<Matchs> AfficherMatchsEQj(Equipe eq) {
        List<Matchs> m;
        String text ="SELECT m FROM Matchs AS m WHERE m.equipeUn=:eq union SELECT m FROM Matchs AS m WHERE m.EquipeDeux=:eq";
        Query req = getEntityManager().createQuery(text);
        req.setParameter("eq", eq);  
        m = req.getResultList();
        return m;
    }
    
    }