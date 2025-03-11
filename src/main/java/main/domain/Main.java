package main.domain;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.Font;

public class Main extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		
		//Stage stage = new Stage();
		Group root = new Group();
		Scene scene = new Scene(root,Color.LIGHTSKYBLUE);
		
		stage.setTitle("Pet Shop Application");
		stage.setWidth(600);
		stage.setHeight(600);
		stage.setResizable(true);
		
		Text text = new Text();
		text.setText("Welcome back!!");
		//text.setX(50);
		//text.setY(50);
		text.setFont(Font.font("Verdana",30));
		text.setFill(Color.BLACK);
		root.getChildren().add(text);
	
		//stage.setX(50);
		//stage.setY(50);
		//stage.setFullScreen(true);
		
		stage.setScene(scene);
		stage.show();
	}
}

