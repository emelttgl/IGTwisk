package twisk.Ecouteurs;

import javafx.event.EventHandler;
import twisk.Exception.TwiskException;
import twisk.mondeIG.MondeIG;
import twisk.mondeIG.PointDeControleIG;
import twisk.vues.VueAlert;
import twisk.vues.VuePointDeControleIG;

import javafx.scene.input.MouseEvent;


public class EcouteurVuePointDeControleIG implements EventHandler<MouseEvent> {
    protected MondeIG monde;
    protected PointDeControleIG pdc;

    public EcouteurVuePointDeControleIG(MondeIG monde, PointDeControleIG pdc){
        this.monde=monde;
        this.pdc=pdc;
    }

    @Override
    public void handle(MouseEvent mouseEvent){
        try{
            this.monde.ajouterArc(this.pdc);
        } catch (TwiskException e){
            VuePointDeControleIG v=new VuePointDeControleIG(this.monde,this.pdc);
           VueAlert va=new VueAlert(this.monde, e.getMessage());
           this.monde.ajouterObservateur(va);
        }
    }
    }

