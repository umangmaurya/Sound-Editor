import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import sun.audio.*;

import javax.swing.JFrame;



public class AudioPlayer {

	/**
	 * @wbp.parser.entryPoint
	 */
	static JFrame jf = new JFrame("AudioPlayer");
	 static InputStream in = null;
	 static AudioStream audioStream  = null ;
	
	public static void playAudio(File fileToPlay){
		System.out.println("reached");
		
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(false);
		
		
		try {
			in = new FileInputStream(fileToPlay.getPath());
			// create an audiostream from the inputstream
			audioStream  = new AudioStream(in);
		 // play the audio clip with the audioplayer class
		   
		    sun.audio.AudioPlayer.player.start(audioStream);
		    
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		    

		    
		    
		
	}
	public static void stopAudio(){
		sun.audio.AudioPlayer.player.stop(audioStream);
	}
}
