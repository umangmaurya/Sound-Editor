import javazoom.jl.converter.Converter;
import javazoom.jl.decoder.JavaLayerException;

public class Mp3ToWav {

	public void convert(String filePath) {
		// TODO Auto-generated method stub
		Converter c = new Converter();
		try {
			String pathToSave = filePath.replace(".mp3", "");
			pathToSave = pathToSave + "-Converted.wav";
			c.convert(filePath, pathToSave);
		} catch (JavaLayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}

}
}
