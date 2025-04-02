package twisk.vues;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import twisk.mondeIG.ArcIG;
import twisk.mondeIG.MondeIG;
import twisk.outils.TailleComposants;



public class VueArcIG extends Pane implements Observateur {
    protected MondeIG mondeIg;
    protected ArcIG arcIg;
    protected Line line;
    protected Polyline polyLine;
    protected boolean selected;

    public VueArcIG(MondeIG monde, ArcIG arc) {
        super();
        this.mondeIg = monde;
        this.arcIg = arc;
        this.selected=false;
        this.line = new Line();
        line.setStartX(arcIg.getPointA().getPosX());
        line.setStartY(arcIg.getPointA().getPosY());
        line.setEndX(arcIg.getPointB().getPosX());
        line.setEndY(arcIg.getPointB().getPosY());
        double startX = line.getStartX();
        double startY = line.getStartY();
        double endX = line.getEndX();
        double endY = line.getEndY();
        this.polyLine = new Polyline();
        double angle=Math.atan2((endY-startY),(endX-startX))-Math.PI/2.0;
        double sin=Math.sin(angle);
        double cos=Math.cos(angle);
        double flX1=(-1.0/2.0*cos+Math.sqrt(3)/2*sin)*TailleComposants.getInstance().getFleche()+endX;
        double flX2 =(1.0/2.0*cos+Math.sqrt(3)/2*sin)*TailleComposants.getInstance().getFleche()+endX;
        double flY1=(-1.0/2.0*sin-Math.sqrt(3)/2*cos)*TailleComposants.getInstance().getFleche()+endY;
        double flY2=(1.0/2.0*sin-Math.sqrt(3)/2*cos)*TailleComposants.getInstance().getFleche()+endY;
        this.polyLine.getPoints().addAll(flX1,flY1,endX, endY,flX2,flY2);
        this.line.setStrokeWidth(TailleComposants.getInstance().getLine());
        this.polyLine.setStrokeWidth(TailleComposants.getInstance().getFleche());
        this.getChildren().addAll(this.polyLine,this.line);
        this.line.setOnMouseClicked(event->selectionArc(event));
        this.polyLine.setOnMouseClicked(event->selectionArc(event));
        this.setPickOnBounds(false);
    }


    public void selectionArc(MouseEvent event){
        this.selected=!this.selected;
        if(this.selected){
            this.line.setStyle("-fx-stroke:#76d275;-fx-stroke:#76d275;");
            this.polyLine.setStyle("-fx-fill:#aee571;-fx-stroke:#aee571;");
            this.arcIg.selectionner();
            this.mondeIg.ajouterArcSelection(this.arcIg);
        } else {
            this.line.setStyle("-fx-stroke:black");
            this.polyLine.setStyle("-fx-stroke:black");
            this.arcIg.unSelectionner();
            this.mondeIg.retirerArcSelection(this.arcIg);
        }
        event.consume();
    }


    @Override
    public void reagir() {

    }
}

