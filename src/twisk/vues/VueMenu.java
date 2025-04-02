package twisk.vues;

import javafx.application.Platform;
import twisk.Exception.ESException;
import twisk.Exception.InException;
import twisk.Exception.TwiskException;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;

import javafx.scene.control.*;

import java.util.ArrayList;

public class VueMenu extends MenuBar implements Observateur {
    protected MondeIG monde;
    protected Menu fichier;
    protected MenuItem exit;
    protected MenuItem changerNbJetons;
    protected Menu edition;
    protected MenuItem supprSelection;
    protected MenuItem rename;
    protected MenuItem deleteSelection;
    protected Menu menu;
    protected MenuItem entree;
    protected MenuItem sortie;
    protected Menu parametre;
    protected MenuItem delai;
    protected MenuItem ecart;
    protected MenuItem nbclient;

    public VueMenu(MondeIG monde) {
        super();
        this.monde = monde;
        this.monde.ajouterObservateur(this);
        this.fichier = new Menu("Fichier");
        this.exit = new MenuItem("Quitter");
        this.exit.setOnAction(event -> Platform.exit());
        this.fichier.getItems().add(this.exit);
        this.edition = new Menu("Edition");
        this.edition.setOnShowing(event -> this.reagir());
        this.supprSelection = new MenuItem("Supprimer la sélection");
        this.deleteSelection = new MenuItem("Effacer la sélection");
        this.rename = new MenuItem("Renommer la sélection");
        this.supprSelection.setOnAction(event -> this.monde.supprSelect());
        this.deleteSelection.setOnAction(event -> this.monde.effacerSelect());
        this.rename.setOnAction(event -> {
            TextInputDialog textInputDialog = new TextInputDialog();
            textInputDialog.setTitle("Renommer une activité");
            textInputDialog.setHeaderText("Entrez un nouveau nom ");
            textInputDialog.showAndWait();
            this.monde.renameAct(textInputDialog.getEditor().getText());
        });

        this.menu = new Menu("Monde");
        this.entree = new MenuItem("Entrée");
        this.entree.setOnAction(e -> {
            this.monde.ajouterEntree();
        });
        this.sortie = new MenuItem("Sortie");
        this.sortie.setOnAction(e -> {
            try {
                this.monde.ajouterSortie();
            } catch (ESException exception) {
                Alert a = new Alert(Alert.AlertType.ERROR, exception.getMessage(), ButtonType.CLOSE);
                a.showAndWait();
            }
        });
        this.menu.getItems().addAll(this.entree, this.sortie);

        this.parametre = new Menu("Paramètres");
        this.delai = new MenuItem("Délais");
        this.delai.setOnAction(e -> {
            try {
                TextInputDialog textInputDialog = new TextInputDialog();
                textInputDialog.setTitle("Délais");
                textInputDialog.setHeaderText("Définir le délais");
                textInputDialog.showAndWait();
                if (!textInputDialog.getEditor().getText().equals("")) {
                    this.monde.setDelai(textInputDialog.getEditor().getText());
                }
            } catch (TwiskException exc) {
                Alert a = new Alert(Alert.AlertType.ERROR, exc.getMessage(), ButtonType.CLOSE);
                a.showAndWait();
            }
        });
        this.ecart = new MenuItem("Ecart");
        this.ecart.setOnAction(e -> {
            try {
                TextInputDialog t = new TextInputDialog();
                t.setTitle("Ecart");
                t.setHeaderText("Définir un écart");
                t.showAndWait();
                if (!t.getEditor().getText().equals("")) {
                    this.monde.setEcart(t.getEditor().getText());
                }
            } catch (TwiskException exc) {
                Alert a = new Alert(Alert.AlertType.ERROR, exc.getMessage(), ButtonType.CLOSE);
                a.showAndWait();
            }
        });
        this.changerNbJetons = new MenuItem("Modifier le nombre de Jetons");
        this.changerNbJetons.setOnAction(actionEvent -> {
            try {
                TextInputDialog t = new TextInputDialog();
                t.setTitle("Nombre de jetons");
                t.setHeaderText("Définir le nombre de jetons");
                t.showAndWait();
                if (!t.getEditor().getText().equals("")) {
                    this.monde.setNbJetons(t.getEditor().getText());
                }
            } catch (TwiskException exc) {
                Alert a = new Alert(Alert.AlertType.ERROR, exc.getMessage(), ButtonType.CLOSE);
                a.showAndWait();
            }
        });
        this.nbclient = new MenuItem("Modifier le nombre de client");
        this.nbclient.setOnAction(actionEvent -> {
            try {
                TextInputDialog t = new TextInputDialog();
                t.setTitle("Nombre de jetons");
                t.setHeaderText("Définir le nombre de jetons");
                t.showAndWait();
                int nbclients;
                try {
                    nbclients = Integer.parseInt(t.getEditor().getText());
                    if (nbclients <= 0) {
                        throw new InException("Attention le nombre doit être strictement positif ! ");
                    }
                } catch (NumberFormatException e) {
                    throw new InException("Impossible le nombre n'est pas valide ! ");
                }
                this.monde.setNbClients(nbclients);
            } catch (TwiskException e) {
                Alert a = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.CLOSE);
                a.showAndWait();
            }
        });
        this.setStyle("-fx-background-color:#CCCCCC;-fx-font-family: 'Quicksand Bold';-fx-font-size:18;-fx-text-fill : red;");
        this.menu.setStyle("-fx-text-inner-color: red");
        this.parametre.getItems().addAll(this.delai, this.ecart, this.changerNbJetons, this.nbclient);
        this.edition.getItems().addAll(this.supprSelection, this.rename, this.deleteSelection);
        this.getMenus().addAll(this.fichier, this.edition, this.menu, this.parametre);
    }




    @Override
    public void reagir() {
        ArrayList<EtapeIG> etp=this.monde.getSelectionEtp();
        if(etp.size()==0 && this.monde.getSelectionEtp().size()==0){
            this.supprSelection.setDisable(true);
            this.rename.setDisable(true);
            this.deleteSelection.setDisable(true);
            this.entree.setDisable(true);
            this.sortie.setDisable(true);
            this.delai.setDisable(true);
            this.ecart.setDisable(true);
            this.changerNbJetons.setDisable(true);
        } else {
            this.supprSelection.setDisable(false);
            this.rename.setDisable(false);
            this.deleteSelection.setDisable(false);
            this.entree.setDisable(false);
            this.sortie.setDisable(false);
            this.delai.setDisable(false);
            this.ecart.setDisable(false);
            this.changerNbJetons.setDisable(false);
            for(EtapeIG e:etp){
                if(e.isAct()){
                    this.changerNbJetons.setDisable(true);
                }
            }
        }
    }
}
