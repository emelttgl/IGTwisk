package twisk.mondeIG;

public class ActiviteIG extends EtapeIG {
        boolean actRestreinte;
    public ActiviteIG(String nom, String idf, int largeur, int haut) {
        super(nom,idf,largeur,haut);
        this.actRestreinte=false;
    }

    @Override
    public boolean isAct() {
        return !this.actRestreinte;
    }
    public void setNbJetons(int i){
    }
}
