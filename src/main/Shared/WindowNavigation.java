package main.Shared;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.controller.IController;

import java.io.IOException;

/**
 * Created by Dora on 4/23/2017.
 */
public class WindowNavigation {

    private IController controller;

    public WindowNavigation() {

    }

    public IController navigateToWindow(String title, String path) {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(path));
        Stage stage = new Stage();
        try {
            Parent root = loader.load();
            stage.setTitle(title);

            controller = loader.getController();
            controller.setStage(stage);

            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return controller;
    };
}
