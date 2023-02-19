/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.commentaire;
import java.util.List;

/**
 *
 * @author Eya
 */
public interface InterfaceCommentaire {
public void Ajouter_commentaire(commentaire c);
public void Modifier_commentaire(commentaire c);
public void Supprimer_commentaire(int id);
public List<commentaire> Consulter_commentaires();
    
}
