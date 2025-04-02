package twisk.vues;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;
import twisk.outils.TailleComposants;


public abstract class VueEtapeIG extends VBox implements Observateur  {
    protected MondeIG monde;
    protected EtapeIG etape;
    protected Label lab;
    protected boolean selection;
    protected Pane zoneclient;;
    protected HBox queue;
    protected boolean entree;
    protected boolean sortie;

    public VueEtapeIG(MondeIG monde, EtapeIG etape) {
        TailleComposants tc = TailleComposants.getInstance();
        this.monde = monde;
        this.etape = etape;
        this.zoneclient=new Pane();
        this.queue=new HBox();
        this.entree=false;
        this.sortie=false;
        lab = new Label(etape.getNom());
        this.getChildren().add(lab);
        this.setOnMouseClicked(event-> this.monde.selectionEtp(this.etape));
        if(this.monde.etpSelect(this.etape)){
            this.lab.setStyle("-fx-text-fill:#919190;-fx-font-family:'Quicksand Book';-fx-alignment:center;-fx-font-size: 17;");
            this.setStyle("-fx-border-radius:3px;-fx-border-width:1px;-fx-border-color:#FFFF;-fx-background-radius: 3px;-fx-background-color: #9be7ff");
        } else {
            this.setStyle(" -fx-fill:-fx-fill:-fx-fill:-fx-fill:;");

        }
    }

    @Override
    public void reagir() {

    }
}
