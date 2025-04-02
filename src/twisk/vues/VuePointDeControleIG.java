package twisk.vues;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import twisk.Exception.TwiskException;
import twisk.mondeIG.MondeIG;
import twisk.mondeIG.PointDeControleIG;
import twisk.outils.TailleComposants;

public class VuePointDeControleIG extends Circle implements Observateur {
    protected MondeIG monde;
    protected PointDeControleIG pdc;

    public VuePointDeControleIG(MondeIG monde, PointDeControleIG pdc) {
        super();
        this.monde = monde;
        this.pdc = pdc;
        this.setCenterX(this.pdc.getPosX());
        this.setCenterY(this.pdc.getPosY());
        this.setRadius(TailleComposants.getInstance().getPointRond());
        this.setFill(Color.PINK);
        if (this.pdc.rattacheEtpA()) {
            this.setStyle("-fx-fill: white;");

        }
        this.setOnMouseClicked(event -> {
            try {
                this.monde.ajouterArc(this.pdc);
            } catch (TwiskException e) {
                Alert alert=new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.CLOSE);
                alert.showAndWait();
            }
        });
    }

    @Override
    public void reagir() {

    }

}