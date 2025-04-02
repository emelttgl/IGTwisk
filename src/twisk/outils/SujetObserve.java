package twisk.outils;

import twisk.vues.Observateur;

import java.util.ArrayList;

public abstract class SujetObserve {
    protected ArrayList<Observateur>obs;
    public SujetObserve(){
        this.obs= new ArrayList<>();
    }
    public abstract void ajouterObservateur(Observateur obs);
    public abstract void notifierObservateur();
    public abstract void reagir();
}
