import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseMotionAdapter;

public class Main {
	static JFrame f = new JFrame();
	static JButton btnPlay = new JButton("Play Audio File");
	static JButton btnCrop = new JButton("Crop Audio File");
	static JButton btnChangePitch = new JButton("Change Sample Rate");
	static JButton btnConvert = new JButton("MP3 TO WAV");
	static JTextPane txtpnToStart = new JTextPane();
	static File selectedFile;
	static String filetype = "";
	
	static int PLAY_FLAG = 0;

	public static void main(String[] args) {
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// TODO Auto-generated method stub
		
		f.setResizable(false);
		f.setTitle("Sound Editor");
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setForeground(new Color(255, 69, 0));
		lblStatus.setBounds(79, 342, 320, 14);
		JButton b=new JButton("Open File");//creating instance of JButton  
		
		
		b.addMouseListener(new MouseAdapter() {
			
			
			
			
			public void mouseClicked(MouseEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Audio FILES", "wav", "mp3");
				
				fileChooser.setFileFilter(filter);
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
				int result = fileChooser.showOpenDialog(f);
				if (result == JFileChooser.APPROVE_OPTION) {
					selectedFile = fileChooser.getSelectedFile();
				    System.out.println("Selected file: " + selectedFile.getAbsolutePath());
				    System.out.println(selectedFile.getName());
				    
				   
				    	 Main m = new Main();
							m.setBtnVisible();
							lblStatus.setText(selectedFile.getName() +" selected");
					
				    
				   
				    
				}
				
			}
		});
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
			}

			
		});
		b.setBounds(196,152,95, 40);//x axis, y axis, width, height  
		
		
		
		f.getContentPane().add(b);        
		f.setSize(500,500); 
		f.getContentPane().setLayout(null);
		
		
		f.getContentPane().add(lblStatus);
		
		JTextPane txtpnHhh = new JTextPane();
		txtpnHhh.setFont(new Font("Tw Cen MT", Font.PLAIN, 16));
		txtpnHhh.setBackground(UIManager.getColor("Slider.background"));
		txtpnHhh.setText("Sound Editor : This Application can \r\n\r\n1.Play Audio Files\r\n2.Crop a selection(copy selected part of audio)\r\n3.Change Pitch\r\n4.Convert Audio Files");
		txtpnHhh.setBounds(21, 22, 335, 119);
		f.getContentPane().add(txtpnHhh);
		
		//AudioPlayer au = new AudioPlayer();
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// play wav file
				if (selectedFile.getPath().endsWith(".wav")) {
					play();
				} else {
					lblStatus.setText("This Feature only Supports WAV format");

				}
				
				
				
				//AudioPlayer.playAudio(selectedFile);
				
			}

			private int play() {
				
				if (PLAY_FLAG == 0) {
					System.out.println("1");
					AudioPlayer.playAudio(selectedFile);
					PLAY_FLAG = 1;
					btnPlay.setText("Stop");
					return 0;
				} else if(PLAY_FLAG == 1) {
					System.out.println("2");
					AudioPlayer.stopAudio();
					btnPlay.setText("Play");
					PLAY_FLAG = 0;

				}
				return 0;
				// TODO Auto-generated method stub
				
			}
		});
		btnPlay.setBounds(65, 210, 159, 40);
		f.getContentPane().add(btnPlay);
		btnCrop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// crop audio file
				
				if (selectedFile.getPath().endsWith(".wav")) {
					System.out.println("Selected file = " +selectedFile.getPath());
					try {
						AudioCrop.crop(selectedFile);
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					lblStatus.setText("This Feature Only Supports WAV format");

				}
				
				
			}
		});
		
		
		btnCrop.setBounds(273, 210, 142, 40);
		f.getContentPane().add(btnCrop);
		btnChangePitch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//change pitch of audio
				if (selectedFile.getPath().endsWith(".wav")) {
					try {
						new ChangePitch().change(selectedFile);
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					lblStatus.setText("This Feature only Supports WAV format");

				}
				
				
			}
		});
		
		
		btnChangePitch.setBounds(65, 271, 159, 40);
		f.getContentPane().add(btnChangePitch);
		
		
		btnConvert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String filePath = selectedFile.getPath();
					if (filePath.endsWith(".mp3")) {
						new Mp3ToWav().convert(filePath);
					} else {
						lblStatus.setText("Already wav file.Onle Mp3 to Wav Supported");

					}
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		btnConvert.setBounds(273, 271, 142, 40);
		f.getContentPane().add(btnConvert);
		
		JTextPane txtpnByUmangMaurya = new JTextPane();
		txtpnByUmangMaurya.setBackground(UIManager.getColor("Slider.background"));
		txtpnByUmangMaurya.setText("By-\r\nUmang Maurya\r\nMCA-3\r\nCCE IPS UoA");
		txtpnByUmangMaurya.setBounds(300, 367, 103, 71);
		f.getContentPane().add(txtpnByUmangMaurya);
		
		
		txtpnToStart.setBackground(UIManager.getColor("ScrollBar.trackForeground"));
		txtpnToStart.setText("To Start Editing , Click on Open File Button");
		txtpnToStart.setBounds(107, 236, 214, 52);
		f.getContentPane().add(txtpnToStart);
		f.setVisible(true);//
		
		btnPlay.setVisible(false);
		btnChangePitch.setVisible(false);
		btnConvert.setVisible(false);
		btnCrop.setVisible(false);
		
		

	}

	protected void setBtnVisible() {
		// TODO Auto-generated method stub
		txtpnToStart.setVisible(false);
		btnPlay.setVisible(true);
		btnChangePitch.setVisible(true);
		btnConvert.setVisible(true);
		btnCrop.setVisible(true);
		
	}
}
