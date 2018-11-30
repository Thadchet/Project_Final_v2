package Drawing;



import Logic.Background;
import Logic.Wizard;
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
	public static GraphicsContext gc ;
	public String image_path = ClassLoader.getSystemResource("image/").toString();
	public String sound_path = ClassLoader.getSystemResource("sound/").toString();
	private AnimationTimer animation ;
	private AnimationTimer soundanimation ;
	private AnimationTimer frameanimation ;
	private Stage primarystage;
	private Canvas canvas;
	public AudioClip intro ;
	public Scene scene;
	private Wizard wizard ;
	private Background bg ;
	private int currentTime;
	private long lastTimeTriggered;
	
	public StartWindow(Stage primarystage) {
		canvas = new Canvas(550,750);
		this.primarystage = primarystage;
		gc = canvas.getGraphicsContext2D();
		intro = new AudioClip(sound_path+"intro.mp3");
		wizard = new Wizard(300,500,200,200);
		bg = new Background(0,0);
		
	}
	public void setBackground() {
		//gc.setFill(Color.BLUE)
		GraphicsContext gc = canvas.getGraphicsContext2D();
		bg.render(gc);
		wizard.render(gc);
		gc.setLineWidth(2);
		gc.setFill(Color.WHITE);
		gc.setStroke(Color.YELLOW);
		Font thefont = Font.font("Times New Roman", FontWeight.BOLD,60);
		gc.setFont(thefont);
		gc.strokeText("Word Fantacy",100,70);
	}
	
	public void setText(GraphicsContext gc) {
		Font thefont = Font.font("Times New Roman", FontWeight.BOLD,30);
		gc.setFont(thefont);
		gc.setFill(Color.YELLOW);
		gc.fillText("PLAY", 240, 230);
		
		gc.fillText("QUIT", 240, 300);
	}
	public void drawFrame(GraphicsContext gc) {
		gc.setLineWidth(4);
		gc.setStroke(Color.WHITE);
		gc.strokeRect(230,195,100,50);
		
	}
	
	
	public void draw(GraphicsContext gc) {
		StackPane pane = new StackPane();
		pane.setPrefSize(550, 750);
		setBackground();
		pane.getChildren().addAll(canvas);
		scene = new Scene(pane);
		addEvent();
		
		primarystage.setScene(scene);
		primarystage.setTitle("Word Fantacy");
		animation = new AnimationTimer() {
			
			@Override
			public void handle(long arg0) {
				// TODO Auto-generated method stub
				setBackground();
				setText(gc);

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
		
		this.currentTime = 0;
		this.lastTimeTriggered = -1 ;
		frameanimation = new AnimationTimer() {
			
			@Override
			public void handle(long now) {
				// TODO Auto-generated method stub
				lastTimeTriggered = (lastTimeTriggered < 0 ? now : lastTimeTriggered);
				
				if (now - lastTimeTriggered >= 500000000)
				{
					drawFrame(gc);
					lastTimeTriggered = now;
					frameanimation.stop();
				}
				frameanimation.start();
			}
		};
		frameanimation.start();
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
		frameanimation.stop();
		GameWindow gm = new GameWindow(primarystage);
	}
	
	
	
	public void addEvent() {
		scene.setOnKeyPressed((KeyEvent e) -> {
			System.out.println(e.getCode());
			if(e.getCode().equals(KeyCode.UP)) {
				next();
			}
		});
	}
	public GraphicsContext getGc() {
		return gc;
	}
	public String getImage_path() {
		return image_path;
	}
	public String getSound_path() {
		return sound_path;
	}
	public AnimationTimer getAnimation() {
		return animation;
	}
	public AnimationTimer getSoundanimation() {
		return soundanimation;
	}
	public Stage getPrimarystage() {
		return primarystage;
	}
	public Canvas getCanvas() {
		return canvas;
	}
	public AudioClip getIntro() {
		return intro;
	}
	public Scene getScene() {
		return scene;
	}
	
}
