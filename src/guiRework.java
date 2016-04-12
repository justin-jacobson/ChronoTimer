/**
 * Temporary file to test and rewrite the CTGUI.java class so that the components all display correctly
 * This will be destroyed after changes are tested, and transfered to the CTGUI.java file
 */


import java.awt.*;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class guiRework {
	
	public guiRework() {
		
		//create the window
		JFrame window = new JFrame("Chrono Timer 1009 (by Hello, cs361!) ");
		window.setLayout(new GridLayout(3,3));
		/*JPanel top = new JPanel();
			top.setLayout(new BoxLayout(top, BoxLayout.X_AXIS));
		JPanel bottom = new JPanel();
			bottom.setLayout(new BoxLayout(bottom, BoxLayout.X_AXIS));*/
		
		//create the panels and components that will go into the window
		//Top Row
		
		//Power Panel
		JPanel powerPanel = new JPanel();
			JButton powerButton = new JButton("POWER");
			powerPanel.add(powerButton);
		
		//Signal Panel
		JPanel signalPanel = new JPanel();
		//signalPanel.setLayout(new BoxLayout(signalPanel, BoxLayout.Y_AXIS));
		
			JLabel chonoLabel = new JLabel("CHRONOTIMER");
			JPanel startFinishTable = new JPanel(new GridLayout(6, 5, 5, 5));
			JLabel numLabels[] = new JLabel[8];
			JLabel numLabels2[] = new JLabel[8]; //copy for back panel
			JLabel titles[] = {new JLabel(""), new JLabel("Start"), new JLabel("Enable/Disable"), new JLabel(""), new JLabel("Finish"), new JLabel("Enable/Disable")};
			JButton signals[] = new JButton[8];
			JCheckBox enable[] = new JCheckBox[8];
			
			//initialize arrays
			int odd = 1, even = 2;
			for(int i = 0; i < numLabels.length; i++) {
				if(i < numLabels.length/2) {
					numLabels[i] = new JLabel(Integer.toString(odd));
					numLabels2[i] = new JLabel(Integer.toString(odd));
					odd+=2;
				}
				else {
					numLabels[i] = new JLabel(Integer.toString(even));
					numLabels2[i] = new JLabel(Integer.toString(even));
					even+=2;
				}
				signals[i] = new JButton();
				enable[i] = new JCheckBox();
			}
			
			//add components to the startFinishTable
			for(int y = 0; y < 6; y++) {
				for(int x = 0; x < 5; x++) {
					if(x==0) startFinishTable.add(titles[y]);
					else {
						if(y==0) startFinishTable.add(numLabels[x-1]);
						else if(y==1) startFinishTable.add(signals[x-1]);
						else if(y==2) startFinishTable.add(enable[x-1]);
						else if(y==3) startFinishTable.add(numLabels[x+3]);
						else if(y==4) startFinishTable.add(signals[x+3]);
						else if(y==5) startFinishTable.add(enable[x+3]);
					}
				}
			}
			
			//add components to signal panel
			signalPanel.add(chonoLabel);
			signalPanel.add(startFinishTable);
		
		//Printer panel
		JPanel printerPanel = new JPanel();
		printerPanel.setLayout(new BoxLayout(printerPanel, BoxLayout.Y_AXIS));
			JButton printButton = new JButton("Printer Pwr");
			JTextArea printer = new JTextArea(25,25);
		
			//add components to printer panel
			printerPanel.add(printButton);
			printerPanel.add(printer);
		
		//Bottom row
			
		//function panel
		JPanel functionPanel = new JPanel(new BorderLayout());
			JButton functionButton = new JButton("FUNCTION");
			JPanel buttonPanel = new JPanel(new GridLayout(1,4));
				JButton directions[] = {new JButton("left <"), new JButton("right >"), new JButton("down V"), new JButton("up ^")};
			JButton swapButton = new JButton("SWAP");
		
			//add components to function panel
			for(JButton b : directions) buttonPanel.add(b);
			functionPanel.add(functionButton, BorderLayout.PAGE_START);
			functionPanel.add(buttonPanel, BorderLayout.CENTER);
			functionPanel.add(swapButton, BorderLayout.PAGE_END);
			
		//screen panel (2,2)
		JPanel screenPanel = new JPanel();
		screenPanel.setLayout(new BoxLayout(screenPanel, BoxLayout.Y_AXIS));
			JLabel screenLabel = new JLabel("Queue/Running/Final Time");
			JTextArea screen = new JTextArea(100,100);
		
			//add components to screenPanel
			screenPanel.add(screenLabel);
			screenPanel.add(screen);
		
		JPanel numpadPanel = new JPanel(new GridLayout(4,3));
			
		//numpad panel(2,3)
		JButton numpad[] = new JButton[12];
			for(int i = 1; i < 10; i++) numpad[i-1] = new JButton(Integer.toString(i));
			numpad[9] = new JButton("*");
			numpad[10] = new JButton("0");
			numpad[11] = new JButton("#");
		
		//add components to numpadPanel
			for(int i = 0; i < numpad.length; i++) 
					numpadPanel.add(numpad[i]);

		//create backview's ports
		JPanel backViewPanel = new JPanel();
		backViewPanel.setLayout(new BoxLayout(backViewPanel, BoxLayout.X_AXIS));
		JPanel inputGrid = new JPanel(new GridLayout(4,4));
			JCheckBox inputs[] = new JCheckBox[8];
			for(int i = 0; i < inputs.length; i++) inputs[i] = new JCheckBox();
			//Add components to inputGrid panel
			 for(int y = 0; y < 4; y++) {
			 	for(int x = 0; x < 4; x++) {
			 		if(y==0) inputGrid.add(numLabels2[x]);
					else if(y==1) inputGrid.add(inputs[x]);
					else if(y==2) inputGrid.add(numLabels2[x+3]);
					else if(y==3) inputGrid.add(inputs[x+3]);
			 	}
			 }
			
			//add inputGrid to backViewPanel
			backViewPanel.add(inputGrid);
		
		//usb panel
		JPanel usbPanel = new JPanel();
		JLabel usbLabel = new JLabel("USB Port");
		JCheckBox usbPort = new JCheckBox();
		usbPanel.add(usbLabel);
		usbPanel.add(usbPort);
			
			window.add(powerPanel);
			window.add(signalPanel);
			window.add(printerPanel);
			
			window.add(functionPanel);
			window.add(screenPanel);
			window.add(numpadPanel);
			
			window.add(backViewPanel);
			window.add(usbPanel);
			window.setSize(750,400);
			window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			window.setVisible(true);
	}
	
	public static void main(String[] args){
		guiRework gui = new guiRework();	
	}
}
