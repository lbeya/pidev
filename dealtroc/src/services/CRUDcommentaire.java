/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.commentaire;
import static java.lang.System.in;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import utils.MyConnection;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Eya
 */
public class CRUDcommentaire implements InterfaceCommentaire {
    Statement ste;
Connection conn = MyConnection.getInstance().getConnection();

public static boolean estUneChaineSansChiffres(String chaine) {
    int i=0;
    int s=0;
    boolean nbr;

    ////une chaine ne contient pas des chiffre
   // String regex = "^[a-zA-Z\\s]+$";
   
        for ( i = 0; i < chaine.length();i++) {
            if (Character.isDigit(chaine.charAt(i))){
                s++;
        }
    }
    if (s<8){
        nbr=true;
    }else{
        nbr=false;
    }
    // Vérifier si la chaîne correspond à l'expression régulière
  //return chaine.matches(regex);
          return nbr;
}
    @Override
    
    public void Ajouter_commentaire(commentaire c) {
        if (estUneChaineSansChiffres(c.getCommentaire())){
        try {
        ste = conn.createStatement();
        
        String datePublicationString = c.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        
        String req = "Insert into commentaire values(0,'"+c.getCommentaire()+"','"+c.getId_utilisateur()+"','"+datePublicationString+"')";
                System.out.println(req);
        ste.executeUpdate(req);
        System.out.println("Commentaire ajouté");
    } catch (SQLException ex) {
//            System.out.println("Commentaire non ajouté!!!!");  
        System.out.println(ex.getMessage());
    }
              } else System.out.println("format invalide");
    }

    @Override
    public void Modifier_commentaire(commentaire c) {
            if (estUneChaineSansChiffres(c.getCommentaire())){
   try {
        Statement st = conn.createStatement();
        String req = "UPDATE `commentaire` SET `commentaire` = '" + c.getCommentaire() + "', `id_utilisateur` = '" + c.getId_utilisateur() + "', `Date` = '" + c.getDate() + "' WHERE `Commentaire`.`id` = " +c.getId();
            System.out.println(req);

        st.executeUpdate(req);
            System.out.println("commentaire updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }else{System.out.println("format invalise");
            }}
    @Override
    public void Supprimer_commentaire(int id) {
        try {
            String req = "DELETE FROM `commentaire` WHERE id = " + id;
            System.out.println(req);
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Commentaire deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<commentaire> Consulter_commentaires() {
    List<commentaire> comms = new ArrayList<commentaire>();
        try {
        String req = "SELECT * FROM `commentaire`";
        ResultSet result = ste.executeQuery(req);
        
        while (result.next()) {
            commentaire resultCommentaire = new commentaire(result.getInt("id"), result.getString("commentaire"), result.getInt("id_utilisateur"), result.getTimestamp("Date").toLocalDateTime());
            comms.add(resultCommentaire);
        }
        System.out.println(comms);
      
    } catch (SQLException ex) {
         System.out.println(ex);   
    }
   return comms;
 }
}
