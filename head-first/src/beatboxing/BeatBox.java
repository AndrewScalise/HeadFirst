package beatboxing;

import java.awt.*;
import javax.swing.*;
import javax.sound.midi.*;
import java.util.*;
import java.awt.event.*;

public class BeatBox {

		
		JPanel mainPanel;
		ArrayList<JCheckBox> checkboxList;
		Sequencer sequencer;
		Sequence sequence;
		Track track;
		JFrame theFrame;
		
		//drum instrument names for panel
		String[] instrumentNames = {"Bass Drum", "Close Hi-Hat", "Open Hi-Hat", "Acoustic Snare", "Crash Cymbal", "Hand Clap",
								"High Tom", "Hi Bongo", "Maracas", "Whistle", "Low Conga", "Cowbell", "Vibraslap", "Low-mid Tom", "High Agogo", "Open Hi Conga"};
		
		//keys for each drum instrument
		int[] instruments = {35,42,46,38,49,39,50,60,70,72,64,56,58,47,67,63};

	
		public static void main(String[] args){
			new BeatBox().buildGui();
		}
		
		public void buildGui(){
			
			//create frame
			theFrame = new JFrame("EZ Beat Boxer");
			theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			BorderLayout layout = new BorderLayout();
			JPanel background = new JPanel(layout);
			background.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));			//margin for border and components
			
			//make new list for drums on panel
			checkboxList = new ArrayList<JCheckBox>();
			Box buttonBox = new Box(BoxLayout.Y_AXIS);
			
			//add start button
			JButton start = new JButton("Start");
			start.addActionListener(new MyStartListener());
			buttonBox.add(start);
			
			//add stop button
			JButton stop = new JButton("Stop");
			stop.addActionListener(new MyStopListener());
			buttonBox.add(stop);
			
			//Button to increase tempo
			JButton upTempo = new JButton("Tempo Up");
			upTempo.addActionListener(new MyUpTempoListener());
			buttonBox.add(upTempo);
			
			//Button to slow down tempo
			JButton downTempo = new JButton("Tempo Down");
			downTempo.addActionListener(new MyDownTempoListener());
			buttonBox.add(downTempo);
			
			//put the names of the drums onto the box layout vertically
			Box nameBox = new Box(BoxLayout.Y_AXIS);
			for(int i = 0;i<16;i++){
				nameBox.add(new Label(instrumentNames[i]));
			}
			
			//add buttons and names on layout to specified location
			background.add(BorderLayout.EAST, buttonBox);
			background.add(BorderLayout.WEST, nameBox);
			
			theFrame.getContentPane().add(background);
			
			//make button grid in center of panel
			GridLayout grid = new GridLayout(16,16);
			grid.setVgap(1);
			grid.setHgap(1);
			mainPanel = new JPanel(grid);
			background.add(BorderLayout.CENTER, mainPanel);
			
			//make checkboxes false for unchecked, add to arraylist and gui panel
			for(int i = 0;i<256;i++){
				JCheckBox c = new JCheckBox();
				c.setSelected(false);
				checkboxList.add(c);
				mainPanel.add(c);
			}
			
			//setting up midi data
			setUpMidi();
			
			//complete the JFrame panel
			theFrame.setBounds(50,50,300,300);
			theFrame.pack();
			theFrame.setVisible(true);
			
		}
		
		//setting up the Midi with sequencer and tracks
		public void setUpMidi(){
			try{
				sequencer = MidiSystem.getSequencer();
				sequencer.open();
				sequence = new Sequence(Sequence.PPQ,4);
				track = sequence.createTrack();
				sequencer.setTempoInBPM(120);
			}catch(Exception e){e.printStackTrace();}
		}
		
		//build an array to hold values for one instrument
		public void buildTrackandStart(){
			int[] trackList = null;					//If instrument doesn't play at the beat put 0
			
			//delete old track, and make a new one
			sequence.deleteTrack(track);
			track = sequence.createTrack();
			
			//do this for each of the 16 rows
			for(int i = 0;i<16; i++){
				trackList = new int[16];
				
				int key = instruments[i];			//represents which instrument it is
				
				//each beat in the row
				for(int j = 0;j<16;j++){
					JCheckBox jc = (JCheckBox) checkboxList.get(j+(16*i));
					if(jc.isSelected()){
						trackList[j] = key;
					} else{
						trackList[j] = 0;
					}
				}
				
				//This instrument and all 16 beats, make events and add them to track
				makeTracks(trackList);
				track.add(makeEvent(176,1,127,0,16));
			}
			
			track.add(makeEvent(192,9,1,0,15));
			try{
				sequencer.setSequence(sequence);
				sequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
				sequencer.start();
				sequencer.setTempoInBPM(120);
			}catch(Exception e){e.printStackTrace();}
		}
		
		public class MyStartListener implements ActionListener{
			public void actionPerformed(ActionEvent a){
				buildTrackandStart();
			}
		}
		
		public class MyStopListener implements ActionListener{
			public void actionPerformed(ActionEvent a){
				sequencer.stop();
			}
		}
		
		public class MyUpTempoListener implements ActionListener{
			public void actionPerformed(ActionEvent a){
				float tempoFactor = sequencer.getTempoFactor();
				sequencer.setTempoFactor((float)(tempoFactor*1.03));
			}
		}
		
		public class MyDownTempoListener implements ActionListener{
			public void actionPerformed(ActionEvent a){
				float tempoFactor = sequencer.getTempoFactor();
				sequencer.setTempoFactor((float)(tempoFactor * .97));
			}
		}
		
		public void makeTracks(int[] list){
			for(int i = 0;i<16;i++){
				int key = list[i];
				
				if(key!=0){
					track.add(makeEvent(144,9,key,100,i));
					track.add(makeEvent(128,9,key,100,i+1));
				}
			}
		}
		
		public MidiEvent makeEvent(int comd, int chan, int one, int two, int tick){
			MidiEvent event = null;
			try{
				ShortMessage a = new ShortMessage();
				a.setMessage(comd, chan, one, two);
				event = new MidiEvent(a,tick);
			}catch(Exception e){e.printStackTrace();}
			
			return event;
		}

}
