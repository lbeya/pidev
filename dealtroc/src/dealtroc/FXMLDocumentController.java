/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dealtroc;

import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations.SentimentClass;
import edu.stanford.nlp.util.CoreMap;
import entities.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import services.CRUDcommentaire;
import static services.CRUDcommentaire.estUneChaineSansChiffres;
import utils.MyConnection;
import javax.mail.*;
/**
 *
 * @author Eya
 */
public class FXMLDocumentController implements Initializable {
    commentaire c;
    public int cid;
    public String cidS;
    Statement ste;
    Connection conn = MyConnection.getInstance().getConnection();
    
    commentaire selectedCommentaire;

    @FXML
    private Label label;
    
    @FXML
    private TextField texte;
    
    
    @FXML
    private TextField textM;


    @FXML
    private TableView<commentaire> tablecommentaire;
    
    
    @FXML
    private TableColumn<commentaire,Integer> id;
     
    @FXML
    private TableColumn<commentaire,String> commentaire;
    
    @FXML
    private TableColumn<commentaire,Integer> iduser;
    
  
    @FXML
    private TableColumn<commentaire,LocalDateTime> date;
    
        @FXML
    private TableColumn<commentaire, String> type;

    
    public ObservableList<commentaire> data = FXCollections.observableArrayList();
    
    @FXML
    private Label labelerror;
    
    @FXML
    private Label labelvalide;
    
    
    @FXML
    private Label AnalyseComm;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   
             
    } 
        @FXML
    void Ajouter_commentaire(ActionEvent event) throws SQLException, MessagingException {

        System.out.println(texte.getText());
        if (estUneChaineSansChiffres(texte.getText())==false){
            labelvalide.setText("");
            labelerror.setText("invalide format: le commentaire ne doit pas être vide ou contenir des nombres >=8");
       
        }else{
                    //Analyse du commentaire
Properties props = new Properties();
props.setProperty("annotators", "tokenize, ssplit, parse, sentiment");
StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
Annotation document = new Annotation(texte.getText());
pipeline.annotate(document);
CoreMap sentence = document.get(SentencesAnnotation.class).get(0);
String sentiment = sentence.get(SentimentClass.class);
System.out.println(sentiment);
            labelvalide.setText(texte.getText()+" a été ajouté");
            labelerror.setText("");
                   // c=new commentaire();
        c = new commentaire(0, texte.getText(),1, LocalDateTime.now(),sentiment);
        CRUDcommentaire cc = new CRUDcommentaire();
        cc.Ajouter_commentaire(c);
        }        

               
        ////////////////////////////// récuperer id from base 
//try{
//    Statement stmt = conn.createStatement();
//ResultSet rs = stmt.executeQuery("SELECT id FROM commentaire WHERE commentaire ='"+texte.getText()+"'");
//if (rs.next()) {
//    cid = rs.getInt("id");
//    System.out.println(cid);
//    
//    //c.setId(cid);
//    System.out.println("id du commentaire est devenu="+c.getId());
//
//}
//}catch(SQLException ex) {
//         System.out.println(ex);
//                 }

//    String to = "eya_labidi@hotmail.fr";
//    String subject = "Test Email";
//    String body = "This is a test email sent from Java.";
//
//    MailSender.sendMail(to, subject, body);

    }
    ////supprimer en ecrivant le chaine de caractére
//    @FXML
//    void Supprimer_commentaire(ActionEvent event) {
//
////        try{
////    Statement stmt = conn.createStatement();
    ////recuperer id from base
////ResultSet rs = stmt.executeQuery("SELECT id FROM commentaire WHERE commentaire ='"+texte.getText()+"'");
////if (rs.next()) {
////    cid = rs.getInt("id");
////    System.out.println(cid);
////    
////c.setId(cid);
////    System.out.println("id du commentaire est devenu="+c.getId());
////
////}
////}catch(SQLException ex) {
////         System.out.println(ex);
////                 }
////        
//        CRUDcommentaire cc = new CRUDcommentaire();
//        cc.Supprimer_commentaire(c.getId());
//        labelvalide.setText(texte.getText()+" a été supprimé");
//
//    }
    
        @FXML
    void Supprimer_commentaire(ActionEvent event) {
         labelerror.setText("");
selectedCommentaire = tablecommentaire.getSelectionModel(). getSelectedItem();
            System.out.println(selectedCommentaire.getId());      
        CRUDcommentaire cc = new CRUDcommentaire();
        cc.Supprimer_commentaire(selectedCommentaire.getId());
        labelvalide.setText(selectedCommentaire.getCommentaire()+" a été supprimé");

    }
    
    
//    @FXML
//    void Modifier_commentaire(ActionEvent event) {
//       // iduser.setEditable(true);
//            if (estUneChaineSansChiffres(texte.getText())){
//            labelvalide.setText("votre commentaire a été modifié");
//        }
//        commentaire c1 = new commentaire(c.getId(), texte.getText(), 1, LocalDateTime.now());
//        c1.toString();
//        CRUDcommentaire cc = new CRUDcommentaire();
//        cc.Modifier_commentaire(c1);
//    }
    
        @FXML
        void Modifier_commentaire(ActionEvent event) {
            Properties props = new Properties();
props.setProperty("annotators", "tokenize, ssplit, parse, sentiment");
StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
Annotation document = new Annotation(texte.getText());
pipeline.annotate(document);
CoreMap sentence = document.get(SentencesAnnotation.class).get(0);
String sentiment = sentence.get(SentimentClass.class);
System.out.println(sentiment);
              if (estUneChaineSansChiffres(textM.getText())==false){
            labelerror.setText("invalide format: le commentaire ne doit pas contenir des nombres >8");
            labelvalide.setText("");
       
        }else{
            labelvalide.setText("valide format");
            labelerror.setText("");
        } 
        selectedCommentaire = tablecommentaire.getSelectionModel(). getSelectedItem();

        commentaire c1 = new commentaire(selectedCommentaire.getId(), textM.getText(), 1, LocalDateTime.now(),sentiment);
        c1.toString();
        CRUDcommentaire cc = new CRUDcommentaire();
        cc.Modifier_commentaire(c1);
                    if (estUneChaineSansChiffres(textM.getText())){
            labelvalide.setText(selectedCommentaire.getCommentaire() +"a été modifié à "+ textM.getText());
        }
    }
    
    
    @FXML
    void Consulter_commentaires(ActionEvent event) {
//        CRUDcommentaire cc = new CRUDcommentaire();
//        System.out.println(cc.Consulter_commentaires());
try{
    String sql = "SELECT * FROM commentaire";

Statement stmt = conn.createStatement();
ResultSet rs = stmt.executeQuery(sql);
while (rs.next()) {
    //commentaire resultCommentaire = new commentaire(rs.getInt("id"), rs.getString("commentaire"), rs.getInt("id_utilisateur"), rs.getTimestamp("Date").toLocalDateTime());
    System.out.println("commentaire{"+rs.getInt(1)+","+rs.getNString(2)+","+rs.getInt(3)+","+rs.getDate(4)+rs.getNString(5)+"}");
    

}
    }catch(SQLException ex) {
         System.out.println(ex);
                 }
    
}
    
   @FXML
   void view_commentaires(ActionEvent event){
       data.clear();
       tablecommentaire.setItems(data);
       
       try{
           Statement stmt = conn.createStatement();
    String sql = "SELECT * FROM commentaire";

ResultSet rs = stmt.executeQuery(sql);
while (rs.next()) {
 //commentaire resultCommentaire = new commentaire(rs.getInt("id"), rs.getString("commentaire"), rs.getInt("id_utilisateur"), rs.getTimestamp("Date").toLocalDateTime());
//data.add(new commentaire(rs.getInt("id"), rs.getString("commentaire"), rs.getInt("id_utilisateur"), rs.getTimestamp("Date").toLocalDateTime()));
data.add(new commentaire(rs.getInt(1),rs.getNString(2),rs.getInt(3),rs.getTimestamp("Date").toLocalDateTime(),rs.getNString(5)));
} 
    }catch(SQLException ex) { 
         System.out.println(ex);
                 }
    id.setCellValueFactory(new PropertyValueFactory<commentaire,Integer>("id") );
    commentaire.setCellValueFactory(new PropertyValueFactory<commentaire,String>("commentaire") );
    iduser.setCellValueFactory(new PropertyValueFactory<commentaire,Integer>("id_utilisateur") );
    date.setCellValueFactory(new PropertyValueFactory<commentaire,LocalDateTime>("Date") );
    type.setCellValueFactory(new PropertyValueFactory<commentaire,String>("type") );
    tablecommentaire.setItems(data);

}

   
   
   } 


