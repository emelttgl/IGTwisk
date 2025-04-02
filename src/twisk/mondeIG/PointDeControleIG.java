package twisk.mondeIG;

public class PointDeControleIG {
    protected String idf;
    protected EtapeIG etape;
    protected int posX;
    protected int posY;
    protected boolean isSelectionner;
    protected ArcIG arc;

    public PointDeControleIG(int posX,int posY, String idf, EtapeIG etape){
        this.idf=idf;
        this.etape=etape;
        this.posX=posX;
        this.posY=posY;
    }

    public int getPosX(){
        return posX;
    }
    public int getPosY(){
        return posY;
    }
    public EtapeIG getEtape() {
        return etape;
    }
    public void setPosX(int posX) {
        this.posX = posX;
    }
    public void setPosY(int posY) {
        this.posY = posY;
    }
    public boolean rattacheEtpA(){
        return this.etape.isAct();
    }
}
