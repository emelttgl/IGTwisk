package twisk;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import twisk.mondeIG.MondeIG;
import twisk.vues.VueMenu;
import twisk.vues.VueMondeIG;
import twisk.vues.VueOutils;

public class MainTwisk extends Application {

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
        MondeIG monde = new MondeIG();
        VueMondeIG vueM =new VueMondeIG(monde);
        VueOutils vueO = new VueOutils(monde);
        VueMenu vueMenu = new VueMenu(monde);
        BorderPane root = new BorderPane();
        root.setBottom(vueO);
        root.setCenter(vueM);
        root.setTop(vueMenu);
        Scene scene = new Scene(root, 1000, 800);
        root.setStyle("-fx-background-color: deeppink;");
        primaryStage.setScene(scene);
        primaryStage.setTitle("MondeIG");
        primaryStage.show();
    }
}
