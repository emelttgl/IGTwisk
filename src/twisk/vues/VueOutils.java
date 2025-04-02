package twisk.vues;

import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import twisk.mondeIG.MondeIG;
import twisk.outils.TailleComposants;

public class VueOutils extends TilePane implements Observateur{
    protected MondeIG monde;
    protected Button bouton;

    public VueOutils(MondeIG monde) {
        this.monde = monde;
        TailleComposants tc = TailleComposants.getInstance();
        Tooltip toolA = new Tooltip(("Ajouter une activité"));
        bouton = new Button("Activité");
        bouton.setTooltip(toolA);
        bouton.setMinSize(tc.getLargeurEtp(),tc.getHauteurEtp());
        bouton.setStyle("-fx-background-color:pink;-fx-background-radius:0;-fx-border-color:black;-fx-border-width: 3 6 6 3;");
        bouton.setOnAction(event -> this.monde.ajouter("Activité"));
        this.getChildren().add(bouton);
    }

    @Override
    public void reagir() {

    }
}
