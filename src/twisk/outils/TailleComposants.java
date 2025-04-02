package twisk.outils;

public class TailleComposants {
    private static  TailleComposants instance = new TailleComposants();
    int largeurEtp;
    int hauteurEtp;
    int largeurGui;
    int largeurLab;
    int hauteurLab;
    int hauteurVueO;
    int hauteurZoneCl;
    int largeurZoneCl;
    int largeurZoneEtp;
    int hauteurZoneEtp;
    int pointRond;
    int line;
    int fleche;



    private TailleComposants(){
        this.hauteurZoneCl=70;
        this.largeurZoneCl=170;
        this.hauteurEtp=80;
        this.largeurEtp=150;
        this.largeurGui = this.largeurEtp;
        this.hauteurLab=30;
        this.largeurLab=this.largeurEtp;
        this.hauteurVueO=70;
        this.largeurZoneEtp=this.largeurEtp;
        this.hauteurZoneEtp=this.hauteurZoneCl+this.hauteurEtp;
        this.pointRond=8;
        this.line=5;
        this.fleche=12;

    }

    public static TailleComposants getInstance() {
        return instance;
    }
    public int getHauteurEtp(){
        return this.hauteurEtp;
    }

    public int getLargeurEtp(){
        return this.largeurEtp;
    }

    public int getHauteurZoneEtp() {
        return hauteurZoneEtp;
    }
    public int getPointRond(){
        return this.pointRond;
    }
    public int getLine(){return this.line;}
    public int getFleche(){
        return this.fleche;
    }


}
