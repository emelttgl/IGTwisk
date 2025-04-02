package twisk.vues;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.WritableImage;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;
import twisk.outils.TailleComposants;

public class VueActiviteIG extends VueEtapeIG implements Observateur{

    protected HBox hbox;
    protected MondeIG monde;
    protected Label lab;

    public VueActiviteIG(MondeIG monde, EtapeIG etape) {
        super(monde, etape);
        TailleComposants tc =TailleComposants.getInstance();
        this.monde = monde;
        this.selection=false;
        hbox=new HBox();
        lab = new Label(""+etape.getNom());
        getChildren().add(hbox);
        this.relocate(etape.getPosX(), etape.getPosY());
        hbox.setStyle(" -fx-border-color: pink; -fx-background-insets: 0 0 -1 0, 0, 1, 2; -fx-background-radius: 3px, 3px, 2px, 1px;");
        this.setStyle("-fx-border-color: black; -fx-background-color: lightblue;"+"-fx-font-size:20");
        this.setPrefSize(TailleComposants.getInstance().getLargeurEtp(), TailleComposants.getInstance().getHauteurEtp());
        super.setAlignment(Pos.TOP_CENTER);
        this.setOnDragDetected(event->{
            Dragboard drag=startDragAndDrop(TransferMode.MOVE);
            ClipboardContent clip=new ClipboardContent();
            clip.putString(this.etape.getIdentifiant());
            WritableImage img=this.snapshot(null,null);
            clip.putImage(img);
            drag.setContent(clip);
            event.consume();
        });
    }


    @Override
    public void reagir() {

    }
}
