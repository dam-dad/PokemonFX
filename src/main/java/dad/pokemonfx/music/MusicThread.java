package dad.pokemonfx.music;

import java.net.URISyntaxException;
import java.net.URL;

import javafx.animation.Transition;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MusicThread extends Thread {
	
	private String file;
	private MediaPlayer player;
	
	/**
	 * Constructor que recibe el nombre del archivo de musica
	 * @param el fichero de musica
	 */
	public MusicThread(String file) {
		this.file = file;
	}
	
	/**
	 * Método para iniciar musica
	 */
	public void play() {
		URL path = getClass().getResource("/music/" + file + ".mp3");
		Media media;
		try {
			media = new Media(path.toURI().toString());
			player = new MediaPlayer(media);
			player.setVolume(0.05);
			player.setCycleCount(Transition.INDEFINITE);
			player.play();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Método para pausar la música
	 */
	public void pause() {
		try {
			player.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
