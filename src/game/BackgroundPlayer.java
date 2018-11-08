package game;

import java.net.URL;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

/**
 * MP3 audio player adapted from
 * http://thiscouldbebetter.wordpress.com/2011/06/14/playing-an-mp3-from-java-using-jlayer/
 *
 */
public class BackgroundPlayer extends PlaybackListener implements Runnable {
	private static final String RESOURCE_PATH = "resources/sounds/background.mp3";
	
	private AdvancedPlayer player;
	private URL url;
	/**
	 * Creates a new BackgroundPlayer from the file at <tt>RESOURCE_PATH</tt>
	 */
	public BackgroundPlayer() {
		url = this.getClass().getClassLoader().getResource(RESOURCE_PATH);
		try {
			this.player = new AdvancedPlayer(
			    this.url.openStream(),
			    javazoom.jl.player.FactoryRegistry.systemRegistry().createAudioDevice()
			);
				
			this.player.setPlayBackListener(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Causes the <tt>player</tt> to play 
	 */
	public void play() {
			try {
				this.player.play();
			} catch (JavaLayerException e) {
				e.printStackTrace();
			}
    }
	
	public void playbackStarted(PlaybackEvent playbackEvent) {
	}
	
	public void playbackFinished(PlaybackEvent playbackEvent) {
		new Thread(new BackgroundPlayer()).start();
	}
	
	@Override
	public void run() {
		this.play();
	}
}