import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Iterator;
import java.util.List;
import java.awt.ItemSelectable;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import team.ChronoTimer;
import team.EventType;
import team.PlaceHolderRecord;
import team.RacerRecord;
import team.Record;
import team.Run;
import team.SensorType;
import team.TChronoTimer;
import team.TRun;

public class CTGUI {
	
	protected ChronoTimer timer;
	protected String inputCmd = "";
	private JTextArea screen;
	private String[] functions;
	
	
	public CTGUI(){
		//create the window
		timer = new TChronoTimer();
		JFrame window = new JFrame("Chrono Timer 1009 (by Hello, cs361!) ");
		window.setLayout(new GridLayout(2, 3));
		//create function buttons
		JPanel functionPanel = new JPanel(new GridLayout(2,1));
			JButton powerButton = new JButton("POWER");
			JPanel buttonPanel = new JPanel(new GridLayout(3,1));
				JButton functionButton = new JButton("FUNCTION");
				JPanel arrowButtonPanel = new JPanel(new GridLayout(1,4));
					JButton leftButton = new JButton("left <");
					JButton rightButton = new JButton("right >");
					JButton downButton = new JButton("down V");
					JButton upButton = new JButton("up ^");
				JButton swapButton = new JButton("SWAP");
		functionPanel.add(powerButton);
		functionPanel.add(buttonPanel);
			buttonPanel.add(functionButton);
			buttonPanel.add(arrowButtonPanel);
				arrowButtonPanel.add(leftButton);
				arrowButtonPanel.add(rightButton);
				arrowButtonPanel.add(downButton);
				arrowButtonPanel.add(upButton);
			buttonPanel.add(swapButton);

		powerButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				System.out.println("Toggle Power!");
				timer.togglePower();
			}
		});
		
		functions = new String[]{
				"time <HH*MM*SS>- Sets the current time.\n",
				"reset - Resets the chrono timer back to initial state.\n",
				"num <number> - Sets <number> as the next competetor to start.\n",
				"clear <number> - Clear <number> as the next competetor.\n",
				"dnf - The next competetor to finish will not finish.\n",
				"event <type> - Sets the current run with the give event type.\n(IND:1, PARIND:2, GRP:3)\n",
				"newrun - Creates a new run.(Must end a run first)\n",
				"endrun - Done with the current run.\n",
				"print <run> - Prints the given run.\n",
				"export <run> - Exports the given run.\n"};
		functionButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				if(inputCmd.contains("#")){
					processCmd();
				}else{
					inputCmd = "";
					screen.setText("<Function List>\n"
							+ "1) time - Sets the current time.(1#HH*MM*SS)\n"
							+ "2) reset - Resets the chrono timer back to initial state.(2#)\n"
							+ "3) num <number> - Sets <number> as the next competetor to start.(3#n)\n"
							+ "4) clear <number> - Clear <number> as the next competetor.(4#n)\n"
							+ "5) dnf - The next competetor to finish will not finish.(5#)\n"
							+ "6) event <type> - Sets the current run with the give event type.(6#type)\n(IND:1, PARIND:2, GRP:3)\n"
							+ "7) newrun - Creates a new run.(Must end a run first)\n"
							+ "8) endrun - Done with the current run.\n"
							+ "9) print <run> - Prints the given run.\n"
							+ "10) export <run> - Exports the given run.\n"
							+ "Enter command to execute : "
					);
				}
			}
		});
		leftButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				System.out.println("Deprecated!");
			}
		});
		rightButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				System.out.println("Deprecated!");
			}
		});
		downButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				System.out.println("Deprecated!");
			}
		});
		upButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				System.out.println("Deprecated!");
			}
		});
		swapButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				timer.swap();
			}
		});
			
		//create signal buttons
		JPanel signalPanel = new JPanel(new GridLayout(2,1));
			JPanel startPanel = new JPanel(new GridLayout(3,5));
			JPanel finishPanel = new JPanel(new GridLayout(3,5));
			
				JLabel empty=new JLabel("     ");
				JLabel empty2=new JLabel("     ");
				// creating signal's Label
				JLabel[] signalLabel = new JLabel[9];
				for(int i=1; i<=8; i++){
					signalLabel[i] = new JLabel(Integer.toString(i));
				}
				
				JLabel startLabel=new JLabel("Start");
				JLabel finishLabel=new JLabel("Finish");
				JButton[] signalButton = new TriggerButton[9];
				for(int i=1;i<=8;i++){
					signalButton[i] = new TriggerButton(i);
				}
				
				JLabel EDLabel = new JLabel("Enable/Disable");
				JLabel EDLabel2 = new JLabel("Enable/Disable");
				JCheckBox[] toggleChannel = new ChannelButton[9];
				for(int i=1;i<=8;i++){
					toggleChannel[i] = new ChannelButton(i);
				}
				//add startPanel components to startPanel
					startPanel.add(empty);
					startPanel.add(signalLabel[1]);
					startPanel.add(signalLabel[3]);
					startPanel.add(signalLabel[5]);
					startPanel.add(signalLabel[7]);
					
					startPanel.add(startLabel);
					startPanel.add(signalButton[1]);
					startPanel.add(signalButton[3]);
					startPanel.add(signalButton[5]);
					startPanel.add(signalButton[7]);
					
					startPanel.add(EDLabel);
					startPanel.add(toggleChannel[1]);
					startPanel.add(toggleChannel[3]);
					startPanel.add(toggleChannel[5]);
					startPanel.add(toggleChannel[7]);
				
				//add start panel to signal panel
					signalPanel.add(startPanel);
					
				//add components to finishPanel
					finishPanel.add(empty2);
					finishPanel.add(signalLabel[2]);
					finishPanel.add(signalLabel[4]);
					finishPanel.add(signalLabel[6]);
					finishPanel.add(signalLabel[8]);
				
					finishPanel.add(finishLabel);
					finishPanel.add(signalButton[2]);
					finishPanel.add(signalButton[4]);
					finishPanel.add(signalButton[6]);
					finishPanel.add(signalButton[8]);
				
					finishPanel.add(EDLabel2);
					finishPanel.add(toggleChannel[2]);
					finishPanel.add(toggleChannel[4]);
					finishPanel.add(toggleChannel[6]);
					finishPanel.add(toggleChannel[8]);
			
				//add finish panel to signal Panel
					signalPanel.add(finishPanel);
				
		//create printer
		JPanel printerPanel = new JPanel(new GridLayout(2,1));
			JButton printButton = new JButton("Printer Button");
			JTextArea printer = new JTextArea(80,100);
			printer.setEditable(false);
			
		//add components to printer panel
			printerPanel.add(printButton);
			printerPanel.add(printer);
			
			printButton.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent event){
					// print <run> - Prints the given run.
					printer.setText("");
					List<Run> runs = timer.getRuns();
					Iterator<Run> it = runs.iterator();
					while(it.hasNext()){
						Run run = it.next();
						printer.append("Run " + run.getID()+"\n");
						printer.append("===== ID : START - FINISH =====\n");
						for(Record r : run.getRecords()) {
							if(r instanceof RacerRecord) {
								RacerRecord rec = (RacerRecord) r;
								printer.append("Racer " + rec.getRacer().getID() + ": ");
							} else if(r instanceof PlaceHolderRecord) {
								PlaceHolderRecord rec = (PlaceHolderRecord) r;
								printer.append("PlaceHolder " + rec.getPlaceHolder() + ": ");
							}
							if(r.didNotFinish()) {
								printer.append(timer.getTimeManager().formatTime(r.getStartTime()) + " - DNF\n");
							} else {
								printer.append(timer.getTimeManager().formatTime(r.getStartTime()) + " - " + timer.getTimeManager().formatTime(r.getFinishTime())+"\n");
							}
						}
					}
				}
			});
			
		//create numpad input
		JPanel numpadPanel = new JPanel(new GridLayout(4,3));
		JButton[] numpadButton = new NumberPadButton[10];
			for(int i=0;i<10;i++){
				numpadButton[i] = new NumberPadButton(Integer.toString(i));
			}
			JButton numpadButtonStar = new NumberPadButton("*");
			JButton numpadButtonHash = new NumberPadButton("#");
		
		//add components to numpadPanel
			numpadPanel.add(numpadButton[1]);
			numpadPanel.add(numpadButton[2]);
			numpadPanel.add(numpadButton[3]);
			numpadPanel.add(numpadButton[4]);
			numpadPanel.add(numpadButton[5]);
			numpadPanel.add(numpadButton[6]);
			numpadPanel.add(numpadButton[7]);
			numpadPanel.add(numpadButton[8]);
			numpadPanel.add(numpadButton[9]);
			numpadPanel.add(numpadButtonStar);
			numpadPanel.add(numpadButton[0]);
			numpadPanel.add(numpadButtonHash);
			
		//create backview's ports
		JPanel backViewPanel = new JPanel(new GridLayout(4,1));
		JLabel backViewLabel = new JLabel("Sensor Connetcion");
		JPanel inputGrid = new JPanel(new GridLayout(4,4));
		JLabel[] inputLabel = new JLabel[9];
		for(int i=1; i<=8; i++){
			inputLabel[i] = new JLabel(Integer.toString(i));
		}
		JComboBox[] inputCB = new BackViewBox[9];
		for(int i=1;i<=8; i++){
			inputCB[i] = new BackViewBox(i);
		}
			
			//Add components to inputGrid panel
			inputGrid.add(inputLabel[1]);
			inputGrid.add(inputLabel[3]);
			inputGrid.add(inputLabel[5]);
			inputGrid.add(inputLabel[7]);
			
			inputGrid.add(inputCB[1]);
			inputGrid.add(inputCB[3]);
			inputGrid.add(inputCB[5]);
			inputGrid.add(inputCB[7]);
			
			inputGrid.add(inputLabel[2]);
			inputGrid.add(inputLabel[4]);
			inputGrid.add(inputLabel[6]);
			inputGrid.add(inputLabel[8]);
			
			inputGrid.add(inputCB[2]);
			inputGrid.add(inputCB[4]);
			inputGrid.add(inputCB[6]);
			inputGrid.add(inputCB[8]);
			
			//add inputGrid to backViewPanel
			backViewPanel.add(backViewLabel);
			backViewPanel.add(inputGrid);
			
		JLabel usbLabel = new JLabel("USB Port");
		JCheckBox usbPort = new JCheckBox();
		
		backViewPanel.add(usbLabel);
		backViewPanel.add(usbPort);
		
		usbPort.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				//What should do with usbPort?
			}
		});
		
		//create screen to show result
		JPanel screenPanel = new JPanel(new GridLayout(2,1));
		JLabel screenLabel = new JLabel("Queue/Running/Final Time");
			screen = new JTextArea(300,300);
			screen.setEditable(false);
		//add components to screenPanel
			screenPanel.add(screenLabel);
			screenPanel.add(screen);
			
		//add to window and display
			window.setSize(1500, 800);
			window.add(functionPanel);
			window.add(signalPanel);
			window.add(printerPanel);
			window.add(numpadPanel);
			window.add(backViewPanel);
			window.add(screenPanel);
			window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			window.setVisible(true);
	}
	
	protected class BackViewBox extends JComboBox{
		private static final long serialVersionUID = -1061884623532972956L;
		private final String[] sensorTypes = {"NONE","EYE","GATE","PAD"};
		private final int id;
		
		BackViewBox(int id){
			this.id = id;
			for(int i=0; i<sensorTypes.length; i++)
				this.addItem(sensorTypes[i]);
			ItemListener itemListener = new ItemListener(){
				public void itemStateChanged(ItemEvent itemEvent){
		          int state = itemEvent.getStateChange();
		          String selectedSensor = "NONE";
		          if(state == ItemEvent.SELECTED){
		        	  selectedSensor = (String)itemEvent.getItem();
		        	  SensorType s= SensorType.NONE;
		        	  if(selectedSensor=="EYE") s = SensorType.EYE;
		        	  else if(selectedSensor=="GATE") s=SensorType.GATE;
		        	  else if(selectedSensor=="PAD") s=SensorType.PAD;
		        	  timer.connect(s, id);
		          }
				}
			};
			this.addItemListener(itemListener);	
		}
	}
	protected class ChannelButton extends JCheckBox {
		private static final long serialVersionUID = 2412443805772599043L;
		private final int id;
		ChannelButton(int id) {
			this.id = id;
			this.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("Toggling chan " + id);
					timer.getChannel(id).toggle();
				}
			});
		}
		
	}
	protected class NumberPadButton extends JButton {
		private static final long serialVersionUID = 2412443805772599043L;
		private final String id;
		NumberPadButton(String id) {
			this.id = id;
			this.setText(id);
			this.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					inputCmd+=id;
					screen.append(id);
				}
			});
		}
		
	}
	protected class TriggerButton extends JButton {
		
		private static final long serialVersionUID = 2412443805772599043L;
		private final int id;
		
		TriggerButton(int id) {
			super(Integer.toString(id));
			this.id = id;
			this.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("Triggering chan " + id);
					if(!timer.trigger(id))
						System.out.println("Failed to trigger channel " + id);
					else
						System.out.println("Successfully triggered channel " + id);
				}
			});
		}
	}
	public void processCmd(){
		screen.setText("\nExecuting : "+ inputCmd +"\n");
		int number;
		switch(inputCmd.substring(0, inputCmd.indexOf("#"))){
			case"": screen.append("no selected command!\n"); break;
			case"1":
				// time - Sets the current time.
				String[] t=inputCmd.substring(2).split("\\*");
				timer.getTimeManager().setTime(t[0]+":"+t[1]+":"+t[2]);
				screen.append("Set time to " + t[0]+":"+t[1]+":"+t[2] + "\n");
				break;
			case"2":
				// reset - Resets the chrono timer back to initial state.
				timer.reset();
				screen.append("Reset done\n");
				break;
			case"3":
				// num <number> - Sets <number> as the next competetor to start.
				number = Integer.parseInt(inputCmd.substring(2));
				if(timer.getLatestRun().addRacer(number) != null)
					screen.append("Successfully added racer " + number+"\n");
				else
					screen.append("[!!]Failed to add racer " + number+"\n");
				break;
			case"4":
				// clear <number> - Clear <number> as the next competetor.
				number = Integer.parseInt(inputCmd.substring(2));
				if(timer.getLatestRun().removeRacer(number))
					screen.append("Successfully removed racer " + number+"\n");
				else
					screen.append("Failed to remove racer " + number+"\n");
				break;
			case"5":
				// dnf - The next competetor to finish will not finish.
				timer.doNotFinish();
				screen.append("DNF done\n");
				break;
			case"6":
				// event <type> - Sets the current run with the give event type.
				// (EYE:1, GATE:2,PAD:3,NONE:4)
				number = Integer.parseInt(inputCmd.substring(2));
				EventType event = EventType.valueOf("IND");
				if(number == 1){event = EventType.valueOf("IND");}
				else if(number == 2){event = EventType.valueOf("PARIND");}
				else if(number == 3){event = EventType.valueOf("GRP");}
				else{screen.append("[!!] Invalid event type!");}
				if(timer.setEvent(event))
					screen.append("Successfully set event to " + event + "\n");
				else
					screen.append("Failed to set event to " + event + "\n");
				break;
			case"7":
				// newrun - Creates a new run.(Must end a run first)
				timer.newRun();
				screen.append("add new run done\n");
				break;
			case"8":
				// endrun - Done with the current run.
				timer.endRun();
				screen.append("end run done\n");
				break;
			case"9":
				// print <run> - Prints the given run.
				int rid = Integer.parseInt(inputCmd.substring(2))-1;
				if(rid < 0 || rid >= timer.getRuns().size()) {
					screen.append("[!!]No run found.\n");
					break;
				}
				Run run = timer.getRuns().get(rid);
				if(run == null) {
					screen.append("No run found with id of " + rid+"\n");
					break;
				}
				screen.append("Run " + run.getID()+"\n");
				screen.append("===== ID : START - FINISH =====\n");
				for(Record r : run.getRecords()) {
					if(r instanceof RacerRecord) {
						RacerRecord rec = (RacerRecord) r;
						screen.append("Racer " + rec.getRacer().getID() + ": ");
					} else if(r instanceof PlaceHolderRecord) {
						PlaceHolderRecord rec = (PlaceHolderRecord) r;
						screen.append("PlaceHolder " + rec.getPlaceHolder() + ": ");
					}
					if(r.didNotFinish()) {
						screen.append(timer.getTimeManager().formatTime(r.getStartTime()) + " - DNF\n");
					} else {
						screen.append(timer.getTimeManager().formatTime(r.getStartTime()) + " - " + timer.getTimeManager().formatTime(r.getFinishTime())+"\n");
					}
				}
				break;
			case"10":
				// export <run> - Exports the given run.
				number = Integer.parseInt(inputCmd.substring(3));
				if(number < 1 || number > timer.getRuns().size()) {
					screen.append("No run found with that id.\n");
					break;
				}
				timer.getExporter().export((TRun) timer.getRuns().get(number-1));
				screen.append("export run done\n");
				break;
			default:
				screen.append("\n[!!]Invalid command number!");
				break;
		}
		inputCmd="";
	}
	public static void main(String[] args){
		CTGUI gui = new CTGUI();
	}
}
