package presentacio;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.SwingUtilities;

import domini.controladores.CtrlPresentacio;


public class VistaInici extends SuperVista {
	
	public boolean HaApretat;
	
	  protected void inicializar_frameVista() {
		    frameVista.setMinimumSize(new Dimension(500,100));
		    frameVista.setPreferredSize(frameVista.getMinimumSize());
		    frameVista.setResizable(false);
		    // Posicion y operaciones por defecto
		    frameVista.setLocationRelativeTo(null);
		    frameVista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    contentPane = (JPanel) frameVista.getContentPane();
		    contentPane.add(panelOpcions);
	}

	    public VistaInici(CtrlPresentacio pCtrlPresentacion) {
	    	cp = pCtrlPresentacion;
	       // inicializar_frameVista();
	        //activar();
	        //hacerVisible();
	    	 HaApretat = false;

		        JButton jugar = new JButton("Jugar");
		        JButton sortir = new JButton("Sortir");
	            jugar.addActionListener(new ActionListener() {
		            public void actionPerformed(ActionEvent e)
		            {
		            	 HaApretat = true;

		               
		            }	            
		        });
	            sortir.addActionListener(new ActionListener() {
		            public void actionPerformed(ActionEvent e) {
		            	HaApretat = true;

		               
		            }
		            
		        });
	            
		        JFrame frame = new JFrame();
		        frame.add(jugar);
		        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		        frame.setLocationRelativeTo(null);
		        frame.setSize(500, 300);
		        frame.add(sortir);
		        frame.setVisible(true);
	        try {
	            play( (System.getProperty("user.dir")+"/so/so.au"),jugar);
	                }
	        catch (Exception ex) {
	                    ex.printStackTrace();
	                };
	                
	            
	             
	        
	    }

	    public void play(String file, JButton jugar) throws LineUnavailableException, UnsupportedAudioFileException, IOException {


	        
	    	System.out.println(file);
	    	File audioFile = new File(file);
	    	 
	    	AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
	    	 
	    	AudioFormat format = audioStream.getFormat();
	    	 
	    	DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
	    	 
	    	SourceDataLine audioLine = (SourceDataLine) AudioSystem.getLine(info);
	    	audioLine.open(format);
	    	 
	        audioLine.start();
	        byte[] bytesBuffer = new byte[4096];
            int bytesRead = -1;
            int count = 0;
            while ((bytesRead = audioStream.read(bytesBuffer)) != -1 && !HaApretat) {
            	++ count;
                audioLine.write(bytesBuffer, 0, bytesRead);
            }
            System.out.println(count);
            audioLine.drain();
            audioLine.close();
            audioStream.close();
            cp.ini();


	        
	        
	    }

	        
	        };