<%-- 
    Document   : AfficherJoueurs
    Created on : 4 déc. 2018, 15:19:18
    Author     : Utilisateur
--%>

<%@page import="entites.HistoriqueJoueur"%>
<%@page import="entites.Joueur"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="cssfede.css" rel="stylesheet" type="text/css">

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:useBean id="listejoueurs" scope="request" class="java.util.List"></jsp:useBean>
        <%--<jsp:useBean id="histojoueurs" scope="request" class="java.util.List"></jsp:useBean>--%>
             <title>Joueurs</title>
    </head>
    <body>
        <h1>Afficher tous les joueurs</h1>
        
        <div style="overflow-x:auto;">       
        <table border width="50%">
            <tr> <td>Numéro</td>
                 <td>Nom</td>
                 <td>Prénom</td>
                 <%--<td> Equipe actuelle </td>--%>
                 <% List <Joueur> lesJoueurs = listejoueurs;
                 for(Joueur jo : lesJoueurs){%>
            <tr> <td Width=15%><%=jo.getId()%></td>
                 <td Width=15%><%=jo.getNomPersonne()%></td>
                 <td Width=15%><%=jo.getPrenomPersonne()%></td>
                <%--<%List <HistoriqueJoueur> HistoJoueurs = histojoueurs;
                 for (HistoriqueJoueur hj : HistoJoueurs) {%>
                 <td Width=15%><%=hj.getEquipeJoueur().getNomequipe()%></td>--%>
            </tr>       
              <%}%>
        </table></hr>
        <div class='retour'>
            <a href ="${pageContext.request.contextPath}/MenuEntraineur.jsp"> Retour Menu </a>
        </div>
    </body>
</html>

