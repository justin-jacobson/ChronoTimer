import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import team.ChronoTimer;
import team.TChronoTimer;

public class CTGUI {
	
	protected ChronoTimer timer;
	
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
				timer.togglePower();
				
			}
		});
		functionButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				
			}
		});
		leftButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				
			}
		});
		rightButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				
			}
		});
		downButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				
			}
		});
		upButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				
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
			inputCB[i] = new BackViewBox();
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
				
			}
		});
		
		//create screen to show result
		JPanel screenPanel = new JPanel(new GridLayout(2,1));
		JLabel screenLabel = new JLabel("Queue/Running/Final Time");
			JTextArea screen = new JTextArea(100,100);
			screen.setEditable(false);
		//add components to screenPanel
			screenPanel.add(screenLabel);
			screenPanel.add(screen);
			
		//add to window and display
			window.setSize(1000, 600);
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
		private final String[] sensorTypes = {"EYE","GATE","PAD","NONE"};
		
		BackViewBox(){
			for(int i=0; i<sensorTypes.length; i++)
				this.addItem(sensorTypes[i]);
			this.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					
				}
			});	
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
					timer.trigger(id);
				}
			});
		}
	}
	public static void main(String[] args){
		CTGUI gui = new CTGUI();	
	}
}
