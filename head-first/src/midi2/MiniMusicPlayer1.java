package midi2;

import javax.sound.midi.*;

public class MiniMusicPlayer1 {

	public static void main(String[] args){
		
	try{
		//make and open a sequencer
		Sequencer sequencer = MidiSystem.getSequencer();
		sequencer.open();
		
		//make a sequence and a track
		Sequence seq = new Sequence(Sequence.PPQ, 4);
		Track track = seq.createTrack();
		
		//make a bunch of events to make the notes keep going up(piano note 5 to piano note 61)
		for(int i = 5; i < 61; i+=4){
			track.add(makeEvent(144,1,i,100,i));
			track.add(makeEvent(192,1,i,0,i));
			track.add(makeEvent(128,1,i,100, i+2));
		}
		
		sequencer.setSequence(seq);
		sequencer.setTempoFactor(1);
		sequencer.start();
	}
	catch(Exception ex){
		ex.printStackTrace();
	}
	}
	
	public static MidiEvent makeEvent(int comd, int chan, int one, int two, int tick){
		MidiEvent event = null;
		try{
			ShortMessage a = new ShortMessage();
			a.setMessage(comd, chan, one, two);
			event = new MidiEvent(a, tick);
		}
		catch(Exception e){}
		return event;
	}
}
