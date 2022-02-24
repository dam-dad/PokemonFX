package dad.pokemonfx.gameloop;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javafx.scene.image.Image;

/**
 * Clase que realiza las animaciones de un player o cualquier tipo de animacion que quisieramos implementar
 */
public class Animation {
		
	private long duration = 200000;
	private long timeAcc = 0;
	private List<Image> frames;
	private int counter = 0;

	public Animation(long duration, String ... images) {
		this.duration = duration;
		this.frames = Arrays.asList(images).stream().map(Image::new).collect(Collectors.toList());
	}

	public Animation(String ... images) {
		this(200000, images);
	}
	
	public List<Image> getFrames() {
		return frames;
	}
	
	public Image getFrame() {
		return frames.get(counter);
	}


	/**
	 * El metodo update es llamado por cada frame
	 * @param diferencia de tiempo para que sea frame independiente
	 */
	public void update(long timeDifference) {
		if (timeAcc > duration) {
			counter++;
			if (counter >= frames.size()) {
				counter = 0;
			}
			timeAcc = 0;
		}
		timeAcc += timeDifference;
	}
	
}
