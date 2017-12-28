package wavCrop;

public class WavFormatTest
{
	static int startP,stopP;
	
    public static void crop(String filePath, int start, int stop)
    {
    	startP = start;
        stopP = stop;
        readAndRewriteWav(filePath);
        
        
        
        
    }    

    private static void readAndRewriteWav(String filePathToReadFromMinusExtension)
    {
        String fileExtension = ".wav";

        String filePathToReadFrom =
            filePathToReadFromMinusExtension;
        
        System.out.println(startP +" " +stopP);

        WavFile wavFileToTest = WavFile.readFromFilePath(filePathToReadFrom,startP,stopP);

        filePathToReadFromMinusExtension = filePathToReadFromMinusExtension.replace(".wav", "");
        String filePathToWriteTo =
            filePathToReadFromMinusExtension
            + "-Cropped"
            + fileExtension;

        wavFileToTest.filePath = filePathToWriteTo;

        wavFileToTest.writeToFilePath();
    }    
}