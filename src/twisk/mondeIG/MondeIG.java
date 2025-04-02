package twisk.mondeIG;

import twisk.Exception.*;
import twisk.outils.*;
import twisk.vues.Observateur;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MondeIG extends SujetObserve implements Iterable<EtapeIG>{

    protected HashMap<String, EtapeIG> hmEtape;
    protected ArrayList<Observateur>obs;
    protected ArrayList<ArcIG> arc;
    protected PointDeControleIG pdc;
    protected ArrayList<ArcIG> selectionArc;
    protected ArrayList<EtapeIG> selectionEtp;
    protected ArrayList<EtapeIG> sortie;
    protected ArrayList<EtapeIG> entree;
    protected int nbclients;
    protected int cpt=1;

    public MondeIG(){
        super();
        this.hmEtape = new HashMap<>();
        String identifiant = FabriqueIdentifiant.getInstance().getIdentifiantEtapes();
        this.hmEtape.put(identifiant, new ActiviteIG("Activité", identifiant, TailleComposants.getInstance().getLargeurEtp(), TailleComposants.getInstance().getHauteurEtp()));
        this.obs = new ArrayList<>();
        this.arc = new ArrayList<>();
        this.selectionArc = new ArrayList<>();
        this.sortie= new ArrayList<>();
        this.entree= new ArrayList<>();
        this.selectionEtp = new ArrayList<>();
    }

    public void checkMondeIG() throws ESException {
        if (this.entree.isEmpty()) {
            throw new ESException("Il n'y a pas d'entrée");
        } else if (this.sortie.isEmpty()) {
            throw new ESException("il n'y a pas de sortie");
        }
    }
    @Override
    public void ajouterObservateur(Observateur o) {
        this.obs.add(o);
    }


    public void ajouter(String type) {
        String identifiant = FabriqueIdentifiant.getInstance().getIdentifiantEtapes();
        if (type.equals("Activité")) {
            this.hmEtape.put(identifiant, new ActiviteIG("Activité " + cpt++, identifiant, TailleComposants.getInstance().getLargeurEtp(), TailleComposants.getInstance().getHauteurEtp()));
            this.notifierObservateur();
        }
    }
    public void ajouterArc(PointDeControleIG p) throws TwiskException {
        if (this.pdc != null) {
            if (this.pdc == p) {
                this.pdc = null;
                throw new MemePoints("Attention tu as selectionnés deux mêmes points ! ");
            } else if (this.pdc.getEtape() == p.getEtape()) {
                this.pdc = null;
                throw new MemeClass("Attention tu as selectionnés deux points de la même étape ! ");
            } else {
                this.arc.add(new ArcIG(this.pdc, p, this.pdc.getEtape(), p.getEtape()));
            }
            this.pdc = null;
            this.notifierObservateur();
        } else {
            this.pdc = p;
        }
    }

    public void renameAct(String s) {
        if (this.selectionEtp.size() == 1) {
            EtapeIG e = this.selectionEtp.get(0);
            e.setNom(s);
            this.selectionEtp.clear();
        }
        this.notifierObservateur();
    }

    public EtapeIG getEtapeId(String id) {
        return this.hmEtape.get(id);
    }

    public void ajouterSortie() throws ESException{
        this.sortie.clear();
        for(EtapeIG e: this.selectionEtp){
            e.estSortie();
            this.sortie.add(e);
        }
        this.selectionEtp.clear();
        notifierObservateur();
    }

    public void setDelai(String str) throws TwiskException{
        try{
            int delai=Integer.parseInt(str);
            for(EtapeIG etp:this.selectionEtp){
                if(delai<etp.getEcart()){
                    throw new DelaiException("Attention ecart > delai ! ");
                } else {
                    etp.setDelai(str);
                }
            }
        } catch (NumberFormatException e){
            throw new DelaiException("Attention entrer un nombre valide ! ");
        }
    }

    public void setEcart(String str) throws TwiskException{
        try{
            int ecart=Integer.parseInt(str);
            for(EtapeIG etp:this.selectionEtp){
                if(ecart>etp.getDelai()){
                    throw new DelaiException("Attention ecart > delai ! ");
                } else {
                    etp.setEcart(str);
                }
            }
        } catch (NumberFormatException e){
            throw new DelaiException("Attention entrer un nombre valide ! ");
        }
    }

    public void setNbClients(int n){
        this.nbclients=n;
    }


    public void setNbJetons(String str) throws TwiskException{
        try{
            int nbJetons=Integer.parseInt(str);
            if(nbJetons<0){
                throw new DelaiException("Attention le nombre de jetons  doit être >0 !");
            }
            for(EtapeIG etp:this.selectionEtp){
                etp.setNbJetons(nbJetons);
            }
        } catch (NumberFormatException e){
            throw new DelaiException("Attention entrer un nombre valide ! ");
        }
    }

    public void ajouterEntree(){
        this.entree.clear();
        this.entree.addAll(this.selectionEtp);
        for(EtapeIG e:this.entree){
            e.estEntree();
        }
        this.selectionEtp.clear();
        notifierObservateur();
    }

    public void supprSelect() {
        Iterator<Map.Entry<String, EtapeIG>> iterator = this.hmEtape.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, EtapeIG> entry = iterator.next();
            for (EtapeIG etp : this.selectionEtp) {
                if (entry.getKey().equals(etp.getIdentifiant())) {
                    iterator.remove();
                }
            }
        }

        this.selectionEtp.clear();
        this.selectionArc.clear();
        this.notifierObservateur();
    }

    public ArrayList<ArcIG> getSelectionArc(){
        return this.selectionArc;
    }

    public void effacerSelect(){
        this.selectionEtp.clear();
        this.selectionArc.clear();
        this.notifierObservateur();
    }

    public boolean etpSelect(EtapeIG etape){
        return this.selectionEtp.contains(etape);
    }

    public void selectionEtp(EtapeIG etape){
        boolean estPresent = false;
        for (EtapeIG etp : this.selectionEtp){
            if (etp == etape) {
                estPresent = true;
                break;
            }
        }
        if (estPresent){
            selectionEtp.remove(etape);
        } else {
            selectionEtp.add(etape);
        }
        notifierObservateur();
    }

    public void ajouterArcSelection(ArcIG aIG) {
        this.selectionArc.add(aIG);
    }

    public void retirerArcSelection(ArcIG aIG){
        this.selectionArc.remove(aIG);
    }


    public ArrayList<EtapeIG> getSelectionEtp() {
        return this.selectionEtp;
    }

    public ArrayList<ArcIG> getArc(){
        return this.arc;
    }

    @Override
    public void notifierObservateur() {
        for (Observateur o : this.obs) {
            o.reagir();
        }

    }
    @Override
    public Iterator<EtapeIG> iterator() {
        return this.hmEtape.values().iterator();
    }

    public Iterator<ArcIG> iteratorArc(){
        return this.selectionArc.iterator();
    }

    public String toString(){
        StringBuilder s=new StringBuilder();
        for(EtapeIG e : this){
            s.append(e.toString()).append("\n");
        }
        return s.toString();
    }

    @Override
    public void reagir() {
        notifierObservateur();
    }
}