/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entites.Arbitre;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author katia
 */
@Local
public interface ArbitreFacadeLocal {

    void create(Arbitre arbitre);

    void edit(Arbitre arbitre);

    void remove(Arbitre arbitre);

    Arbitre find(Object id);

    List<Arbitre> findAll();

    List<Arbitre> findRange(int[] range);

    int count();

    void CreerArbitre(String nom, String prenom, String login, String mdp);

    Collection<Arbitre> TouslesArbitres();

    Arbitre RechercherArbitre(String nomPersonne, String prenomPersonne);

    Arbitre rechercherArbitreId(int ida);

    Arbitre AuthArbitre(String log, String mdp);



    
}
