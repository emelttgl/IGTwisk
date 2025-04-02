package twisk.vues;

import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import twisk.mondeIG.*;

import java.util.ArrayList;

public class VueMondeIG extends Pane implements Observateur {
    protected MondeIG monde;

    public VueMondeIG(MondeIG monde) {
        super();
        this.monde = monde;

        this.setOnDragOver(event -> {
            event.acceptTransferModes(TransferMode.MOVE);
            event.consume();
        });

        this.setOnDragDropped(event -> {
            Dragboard drag = event.getDragboard();
            String str = drag.getString();
            EtapeIG etp = this.monde.getEtapeId(str);
            etp.setPosX((int) event.getX() - etp.getLargeur() / 2);
            etp.setPosY((int) event.getY() - etp.getHauteur() / 2);
            event.setDropCompleted(true);
            event.consume();
            this.reagir();
        });
        this.setStyle("-fx-background-color: deeppink");
        this.monde.ajouterObservateur(this);
    }


    @Override
    public void reagir() {
        MondeIG monde = this.monde;
        this.getChildren().clear();
        ArrayList<ArcIG> arc = this.monde.getArc();
        for (ArcIG arcs : arc) {
            VueArcIG vueArc = new VueArcIG(monde, arcs);
            this.getChildren().add(vueArc);
        }
        for (EtapeIG etape : monde) {
            for (PointDeControleIG pdc : etape) {
                VuePointDeControleIG vuePdc = new VuePointDeControleIG(monde, pdc);
                this.getChildren().add(vuePdc);

                VueEtapeIG vueEtapeIG = new VueActiviteIG(monde, etape);
                this.getChildren().add(vueEtapeIG);
            }
        }

    }
}


