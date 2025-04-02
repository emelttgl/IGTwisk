package twisk.mondeIG;

import twisk.outils.TailleComposants;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public abstract class EtapeIG implements Iterable<PointDeControleIG>{
    protected String nom;
    protected String identifiant;
    protected int posX;
    protected int posY;
    protected int largeur;
    protected int hauteur;
    protected ArrayList<PointDeControleIG> pdcIG;
    protected int delai;
    protected int ecart;
    protected boolean entree;
    protected boolean sortie;


    public EtapeIG(String nom, String idf, int largeur, int haut) {
        this.nom = nom;
        this.identifiant = idf;
        this.largeur = largeur;
        this.hauteur = haut;
        this.entree = false;
        this.sortie = false;
        Random rnd = new Random();
        this.posX = rnd.nextInt(800);
        this.posY = rnd.nextInt(600);
        PointDeControleIG point1 = new PointDeControleIG(this.posX + (this.largeur / 2), this.posY, "id", this);
        PointDeControleIG point2 = new PointDeControleIG(this.posX + this.largeur, this.posY + (this.hauteur / 2), "id", this);
        PointDeControleIG point3 = new PointDeControleIG(this.posX + (this.largeur / 2), this.posY + this.hauteur, "id", this);
        PointDeControleIG point4 = new PointDeControleIG(this.posX, this.posY + (this.hauteur / 2), "id", this);
        this.pdcIG = new ArrayList<>(4);
        this.pdcIG.add(point1);
        this.pdcIG.add(point2);
        this.pdcIG.add(point3);
        this.pdcIG.add(point4);
        this.delai = 2;
        this.ecart = 2;
    }
    public ArrayList<PointDeControleIG> getPdcIG() {
        return pdcIG;
    }

    @Override
    public Iterator<PointDeControleIG> iterator(){
        ArrayList<PointDeControleIG> list=new ArrayList<>(this.pdcIG);
        return list.iterator();
    }
    public int getLargeur(){
        return this.largeur;
    }
    public int getHauteur(){
        return this.hauteur;
    }
    public int getPosX(){
        return this.posX;
    }
    public int getPosY(){
        return this.posY;
    }

    public String getNom(){
        return this.nom;
    }


    public void setDelai(String str) {
        this.delai = Integer.parseInt(str);
    }

    public void setEcart(String str) {
        this.ecart = Integer.parseInt(str);
    }
    public void estEntree(){
        this.sortie=false;
        this.entree=true;
    }

    public void estSortie(){
        this.entree=false;
        this.sortie=true;
    }
    public boolean isEntree(){return this.entree;}
    public boolean isSortie(){return this.sortie;}
    public String getIdentifiant(){return this.identifiant;}
    public int getDelai(){return delai;}
    public int getEcart(){return ecart;}
    public void setNom(String str){
        this.nom=str;
    }
    public void setPosX(int x){
        this.posX=x;
        PointDeControleIG p=this.pdcIG.get(0);
        p.setPosX(this.posX + (this.largeur / 2));
        PointDeControleIG pdc=this.pdcIG.get(1);
        pdc.setPosX(this.posX + this.largeur);
        PointDeControleIG pdc1=this.pdcIG.get(2);
        pdc1.setPosX(this.posX + (this.largeur / 2));
        PointDeControleIG pdc2 =this.pdcIG.get(3);
        pdc2.setPosX(this.posX);
    }
    public void setPosY(int y){
        this.posY=y-(TailleComposants.getInstance().getHauteurEtp()/2);

        PointDeControleIG pdc=this.pdcIG.get(0);
        pdc.setPosY(this.posY);
        PointDeControleIG pdc1 =this.pdcIG.get(1);
        pdc1.setPosY(this.posY + (this.hauteur / 2));
        PointDeControleIG pdc2=this.pdcIG.get(2);
        pdc2.setPosY(this.posY + this.hauteur+TailleComposants.getInstance().getHauteurZoneEtp()/50);
        PointDeControleIG pdc3=this.pdcIG.get(3);
        pdc3.setPosY(this.posY + (this.hauteur / 2));
    }
    public abstract boolean isAct();


    public abstract void setNbJetons(int i);

}
