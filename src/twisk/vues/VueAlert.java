package twisk.vues;

import javafx.scene.control.Alert;
import twisk.mondeIG.MondeIG;

public class VueAlert extends Alert implements Observateur{
    protected MondeIG monde;
    public String str;

    public VueAlert(MondeIG monde, String message){
        super(AlertType.ERROR);
        this.monde=monde;
        this.str=str;
        this.setTitle("Error");
        this.setContentText(this.str);
    }

    @Override
    public void reagir() {

    }
}
