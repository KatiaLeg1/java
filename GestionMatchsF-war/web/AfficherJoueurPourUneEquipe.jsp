<%-- 
    Document   : AfficherJoueurPourUneEquipe
    Created on : Dec 11, 2018, 2:52:11 PM
    Author     : domitille
--%>
 <%@page import="java.util.List"%>
<%@page import="entites.Joueur"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:useBean id="lesJoueurs" scope="request" class="List<Joueur>"></jsp:useBean> <%-- attention aux noms + Dans scope ça devient Session--%>
         <title>Afficher la liste des joueurs d'une équipe</title>
    </head>
    <body>
        <h1>Afficher la liste des joueurs d'une équipe</h1>
         
        <table>
           </tr>   
                <%-- en tête de tes colonnes --%>
            <td Width =15%>Nom equipe</td>
            <td Width =15%>Date début</td>
            <td Width =15%>Date fin</td>
         </tr> 
       <%  
           for(Joueur j : lesJoueurs){%> <%--tu parcours la liste--%>
        
           
    </tr>   
            <td ><%= j.getNomPersonne() %></td>
            <td ><%=j.getPrenomPersonne() %></td>
         </tr> </table><%}%>
        <td>
            </br>
            <TABLE><tr>
                <a href="MenuTous.jsp"> Retour Menu</a></tr>
            </TABLE>
    </body>
</html>