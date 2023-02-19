/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.time.LocalDateTime;
/**
 *
 * @author Eya
 */
public class commentaire {
        
    private int id;    
    private String commentaire;
    private int id_utilisateur;
    private LocalDateTime Date;

    public commentaire() {
    }



    public commentaire(int id ,String commentaire, int id_utilisateur, LocalDateTime Date) {
        this.commentaire = commentaire;
        this.id_utilisateur = id_utilisateur;
        this.id = id;
        this.Date=Date;
    }

    public LocalDateTime getDate() {
        return Date;
    }

    public void setDate(LocalDateTime Date) {
        this.Date = Date;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "commentaire{" + "id=" + id + ", commentaire=" + commentaire + ", id_utilisateur=" + id_utilisateur + ", Date=" + Date + '}';
    }
    

}
