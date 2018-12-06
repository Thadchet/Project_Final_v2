package Drawing;

import Logic.Wizard;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import sharedObject.IRenderable;

public class GameScreen implements IRenderable {
	private String image_path = ClassLoader.getSystemResource("image/").toString();
	private int life;
	private int score;
	private Image background;
	private Image Imlife;
	private Image laser;
	private Image skill1;
	private Image skill2;
	private Image skillused;
	private boolean isSkillused1 = false;
	private boolean isSkillused2 = false;

	public GameScreen() {
		// TODO Auto-generated constructor stub
		setImage();
	}

	public void setImage() {
		background = new Image(image_path + "background.gif");
		Imlife = new Image(image_path + "life.gif");
		laser = new Image(image_path + "laser.gif");
		skill2 = new Image(image_path + "skill1.jpg");
		skill1 = new Image(image_path + "skill2.jpg");
		skillused = new Image(image_path + "skillused.png");
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(background, 0, 0);
		Font font = new Font("Agency FB", 30);
		gc.setFont(font);
		gc.fillText("Score : " + score, 50, 50);
		Font typing = new Font("Agency FB", 35);
		gc.setFont(typing);
		gc.setFill(Color.YELLOW);
		gc.fillText("TPYING : " + GameWindow.temp, 50, 740);
		gc.drawImage(Imlife, 430, 20, 40, 40);
		gc.drawImage(skill1, 480, 120, 40, 40);
		gc.drawImage(skill2, 480, 70, 40, 40);
		if (isSkillused1) {
			gc.drawImage(skillused, 480, 70, 40, 40);
		}
		if (isSkillused2) {
			gc.drawImage(skillused, 480, 120, 40, 40);
		}

		gc.fillText(" x " + life, 470, 50);
		gc.drawImage(laser, 0, 630, 710, 120);
	}

	@Override
	public boolean isDestroyed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return true;
	}

	public void setStatus(int life, int score) {
		this.life = life;
		this.score = score;
	}

	public boolean isSkillused1() {
		return isSkillused1;
	}

	public void setisSkillused1(boolean isSkillused1) {
		this.isSkillused1 = isSkillused1;
	}

	public boolean isSkillused2() {
		return isSkillused2;
	}

	public void setisSkillused2(boolean isSkillused2) {
		this.isSkillused2 = isSkillused2;
	}

	public void addScore() {
		this.score += 1;
	}

	public String getImage_path() {
		return image_path;
	}

	public int getLife() {
		return life;
	}

	public int getScore() {
		return score;
	}

	public Image getBackground() {
		return background;
	}

	public Image getImlife() {
		return Imlife;
	}

	public Image getLaser() {
		return laser;
	}

	public Image getSkill1() {
		return skill1;
	}

	public Image getSkill2() {
		return skill2;
	}

	public Image getSkillused() {
		return skillused;
	}

	public void decreaseLife() {
		this.life -= 1;
	}

	public void increaseLife() {
		this.life += 2;
	}
}
