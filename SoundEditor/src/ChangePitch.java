import java.awt.Color;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioFormat.Encoding;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

import wavCrop.DataInputStreamLittleEndian;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangePitch {
	public JTextField textPitchValue;
	static int pitchVal=0;

	/**
	 * @throws FileNotFoundException 
	 * @wbp.parser.entryPoint
	 */
	public void change(File selectedFile) throws FileNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("pitch");
		JFrame f = new JFrame("Change Pitch");
		f.getContentPane().setBackground(Color.WHITE);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setResizable(false);
		f.setAlwaysOnTop(true);
		
		f.setForeground(Color.WHITE);
		f.setSize(400,250); 
		f.getContentPane().setLayout(null);
		
		JTextPane textStatus = new JTextPane();
		textStatus.setBounds(34, 21, 249, 20);
		f.getContentPane().add(textStatus);
		
		textPitchValue = new JTextField();
		textPitchValue.setBounds(234, 52, 86, 20);
		f.getContentPane().add(textPitchValue);
		textPitchValue.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Enter new Value for Pitch");
		lblNewLabel.setBounds(44, 55, 161, 14);
		f.getContentPane().add(lblNewLabel);
		
		JButton btnChange = new JButton("Change");
		btnChange.setBounds(142, 91, 89, 33);
		f.getContentPane().add(btnChange);
		
		JTextPane txtpnWarningIncorrect = new JTextPane();
		txtpnWarningIncorrect.setForeground(new Color(255, 0, 0));
		txtpnWarningIncorrect.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtpnWarningIncorrect.setText("Warning : Incorrect value may lead to output with incorrect audio.");
		txtpnWarningIncorrect.setBounds(48, 140, 273, 58);
		f.getContentPane().add(txtpnWarningIncorrect);
		f.setVisible(true);
		
		JLabel lblErrorMsg = new JLabel("");
        lblErrorMsg.setBounds(241, 95, 106, 29);
        f.getContentPane().add(lblErrorMsg);
        
		DataInputStreamLittleEndian r = new DataInputStreamLittleEndian(new DataInputStream(new BufferedInputStream(new FileInputStream(selectedFile.getAbsolutePath()))));
		
		try {
			byte[] riff = new byte[4];
	        r.read(riff);
			
	        for (int i = 0; i < 5; i++) {
	        	r.readInt();	
			}
			
	        textStatus.setText("Pitch Of Selected file = "+r.readInt()+"Hz");
	        
	        
	        
	        
	        
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		btnChange.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				pitchVal = Integer.parseInt(textPitchValue.getText());
				try {
					if (pitchVal == 0) {
						lblErrorMsg.setText("Pitch value Empty or 0");
					} else {
						
						new ChangePitch().changePitch(selectedFile);

					}
					
				} catch (IOException | UnsupportedAudioFileException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			
		});
		
	}

	protected void changePitch(File selectedFile) throws IOException, UnsupportedAudioFileException {
		// TODO Auto-generated method stub
		
		final File file1 = selectedFile;
		//destination file
		String Fname = selectedFile.getPath().replace(".wav", "");
		final File file2 = new File(Fname+"-PitchChanged.wav");
		
		final AudioInputStream in1 = AudioSystem.getAudioInputStream(file1);
		
		
		final AudioFormat inFormat = getOutFormat(in1.getFormat());
		
		final AudioInputStream in2 = AudioSystem.getAudioInputStream(inFormat,in1);
		
		AudioSystem.write(in2, AudioFileFormat.Type.WAVE, file2);
		
	}

	private static AudioFormat getOutFormat(javax.sound.sampled.AudioFormat format) {
		int ch = format.getChannels();
		float rate = format.getSampleRate();
		
		
		
		return new AudioFormat(Encoding.PCM_SIGNED, pitchVal, 16, ch, ch * 2, rate,
				format.isBigEndian());
	}
	
		
	}


