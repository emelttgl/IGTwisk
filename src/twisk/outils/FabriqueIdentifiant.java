package twisk.outils;

public class FabriqueIdentifiant  {
    protected int noEtape;
    private static  FabriqueIdentifiant instance = new FabriqueIdentifiant();

    public FabriqueIdentifiant(){noEtape=0;

    }
    public static FabriqueIdentifiant getInstance(){
        return instance;
    }

    public String getIdentifiantEtapes(){
        int comp= noEtape;
        noEtape++;
        return "Etape "+comp;

}}
