/**
 * Temporary file to test and rewrite the CTGUI.java class so that the components all display correctly
 * This will be destroyed after changes are tested, and transfered to the CTGUI.java file
 */


import java.awt.*;

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
		window.setLayout(new GridLayout(2, 1, 10, 10));
		window.setSize(720, 500);
		
		//create the panels and components that will go into the window
		//Top Row
		
		//Power Panel (1,1)
		JPanel powerPanel = new JPanel();
			JButton powerButton = new JButton("POWER");
			powerPanel.add(powerButton);
		
		//Signal Panel (1,2)
		JPanel signalPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
			JLabel chonoLabel = new JLabel("CHRONOTIMER");
			JPanel startFinishTable = new JPanel(new GridLayout(6, 5, 5, 5));
			JLabel numLabels[] = new JLabel[8];
			JLabel titles[] = {new JLabel(""), new JLabel("Start"), new JLabel("Enable/Disable"), new JLabel(""), new JLabel("Finish"), new JLabel("Enable/Disable")};
			
			JButton signals[] = new JButton[8];
			JCheckBox enable[] = new JCheckBox[8];
			
			//initialize arrays
			int odd = 1, even = 2;
			for(int i = 0; i < numLabels.length; i++) {
				if(i < numLabels.length/2) {
					numLabels[i] = new JLabel(Integer.toString(odd));
					odd+=2;
				}
				else {
					numLabels[i] = new JLabel(Integer.toString(even));
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
		
		//Printer panel (1, 3)
		JPanel printerPanel = new JPanel();
			JButton printButton = new JButton("Printer Pwr");
			JTextArea printer = new JTextArea(80,100);
		
			//add components to printer panel
			printerPanel.add(printButton);
			printerPanel.add(printer);
		
		//Bottom row
			
		//function panel (2,1)
		JPanel functionPanel = new JPanel(new GridLayout(3,1));
			JButton functionButton = new JButton("FUNCTION");
			JPanel buttonPanel = new JPanel(new GridLayout(4,1));
				JButton directions[] = {new JButton("left <"), new JButton("right >"), new JButton("down V"), new JButton("up ^")};
			JButton swapButton = new JButton("SWAP");
		
			//add components to function panel
			for(JButton b : directions) buttonPanel.add(b);
			functionPanel.add(functionButton);
			functionPanel.add(buttonPanel);
			functionPanel.add(swapButton);
			
		//screen panel (2,2)
		JPanel screenPanel = new JPanel(new GridLayout(2,1));
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

		/*	
		//create backview's ports
		JPanel backViewPanel = new JPanel(new GridLayout(3,1));
		JPanel inputGrid = new JPanel(new GridLayout(4,4));
			JLabel inpLabel1=new JLabel("1");
			JLabel inpLabel3=new JLabel("3");
			JLabel inpLabel5=new JLabel("5");
			JLabel inpLabel7=new JLabel("7");
			
			JCheckBox inpCB1 = new JCheckBox();
			JCheckBox inpCB3 = new JCheckBox();
			JCheckBox inpCB5 = new JCheckBox();
			JCheckBox inpCB7 = new JCheckBox();
			
			JLabel inpLabel2=new JLabel("2");
			JLabel inpLabel4=new JLabel("4");
			JLabel inpLabel6=new JLabel("6");
			JLabel inpLabel8=new JLabel("8");
			
			JCheckBox inpCB2 = new JCheckBox();
			JCheckBox inpCB4 = new JCheckBox();
			JCheckBox inpCB6 = new JCheckBox();
			JCheckBox inpCB8 = new JCheckBox();
			
			//Add components to inputGrid panel
			inputGrid.add(inpLabel1);
			inputGrid.add(inpLabel3);
			inputGrid.add(inpLabel5);
			inputGrid.add(inpLabel7);
			
			inputGrid.add(inpCB1);
			inputGrid.add(inpCB3);
			inputGrid.add(inpCB5);
			inputGrid.add(inpCB7);
			
			inputGrid.add(inpLabel2);
			inputGrid.add(inpLabel4);
			inputGrid.add(inpLabel6);
			inputGrid.add(inpLabel8);
			
			inputGrid.add(inpCB2);
			inputGrid.add(inpCB4);
			inputGrid.add(inpCB6);
			inputGrid.add(inpCB8);
			
			//add inputGrid to backViewPanel
			backViewPanel.add(inputGrid);
			
		JLabel usbLabel = new JLabel("USB Port");
		JCheckBox usbPort = new JCheckBox();
		
		backViewPanel.add(usbLabel);
		backViewPanel.add(usbPort);
		*/
		//create screen to show result
			
		//add to window and display
			
			window.add(powerPanel);
			window.add(signalPanel);
			window.add(printerPanel);
			window.add(functionPanel);
			window.add(screenPanel);
			window.add(numpadPanel);
			
			
			
//			window.add(backViewPanel);
			window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			window.pack();
			window.setVisible(true);
	}
	
	public static void main(String[] args){
		guiRework gui = new guiRework();	
	}
}
