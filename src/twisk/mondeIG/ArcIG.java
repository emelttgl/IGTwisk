package twisk.mondeIG;

import twisk.outils.FabriqueIdentifiant;

public class ArcIG {
    protected PointDeControleIG pointA;
    protected PointDeControleIG pointB;
    protected String idf;
    protected EtapeIG etpA;
    protected EtapeIG etpB;
    protected boolean isSelectionner;

    public ArcIG(PointDeControleIG pointA, PointDeControleIG pointB,EtapeIG etpA, EtapeIG etpB){
        this.pointA=pointA;
        this.pointB=pointB;
        this.idf= FabriqueIdentifiant.getInstance().getIdentifiantEtapes();
        this.etpA=etpA;
        this.etpB=etpB;
        this.isSelectionner=false;
    }

    public PointDeControleIG getPointA(){
        return this.pointA;
    }
    public PointDeControleIG getPointB(){
        return this.pointB;
    }
    public void selectionner(){
        this.isSelectionner=true;
    }
    public void unSelectionner(){this.isSelectionner=false;}
    public String toString(){return this.idf;}
}
