package Drawing;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class StartWindow {
	private GraphicsContext gc ;
	public String image_path = ClassLoader.getSystemResource("image/").toString();
	public String sound_path = ClassLoader.getSystemResource("sound/").toString();
	private AnimationTimer animation ;
	private AnimationTimer soundanimation ;
	private Stage primarystage;
	private Canvas canvas;
	public AudioClip intro ;
	private Scene scene;
	
	public StartWindow(Stage primarystage) {
		canvas = new Canvas(550,750);
		this.primarystage = primarystage;
		gc = canvas.getGraphicsContext2D();
		intro = new AudioClip(sound_path+"intro.mp3");
		
	}
	public void setBackground() {
		//gc.setFill(Color.BLUE)
		GraphicsContext gc = canvas.getGraphicsContext2D();
		Image bg = new Image(image_path+"3.jpg");
		//gc.fillRect(0, 0, 300, 300);
		Image wizard = new Image(image_path+"wizard1.gif");
		gc.drawImage(bg, 0, 0);
		gc.drawImage(wizard, 100, 150);
		setText(gc);
	}
	
	public void setText(GraphicsContext gc) {
		Font thefont = Font.font("Times New Roman", FontWeight.BOLD,40);
		gc.setFont(thefont);
		gc.setFill(Color.YELLOW);
		gc.fillText("GAME", 220, 50);
		gc.fillText("Enter Your Name : ", 20, 230);
	}
	
	public void draw(GraphicsContext gc) {
		StackPane pane = new StackPane();
		pane.setPrefSize(550, 750);
		setBackground();
		pane.getChildren().addAll(canvas);
		scene = new Scene(pane);
		addEvent();
		
		primarystage.setScene(scene);
		primarystage.setTitle("########");
		animation = new AnimationTimer() {
			
			@Override
			public void handle(long arg0) {
				// TODO Auto-generated method stub
				setBackground();
			}
		};
		animation.start();
		
		soundanimation = new AnimationTimer() {
			
			@Override
			public void handle(long now) {
				// TODO Auto-generated method stub
				if(intro.isPlaying() == false) playsong();
			}
		};
		soundanimation.start();
	}
	
	public void startAnimation() {
		draw(gc);
	}
	
	public void playsong() {
		intro = new AudioClip(sound_path+"intro.mp3");
		intro.play();
	}
	public void next() {
		animation.stop();
		soundanimation.stop();
		intro.stop();
		
	}
	
	
	
	public void addEvent() {
		scene.setOnKeyPressed((KeyEvent e) -> {
			System.out.println(e.getCode());
			if(e.getCode().equals(KeyCode.UP)) {
				next();
			}
		});
	}
	
}
