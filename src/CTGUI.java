import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class CTGUI {
	
	public CTGUI(){
		//create the window
		JFrame window = new JFrame("Chrono Timer 1009 (by Hello, cs361!) ");
		window.setLayout(new GridLayout(2, 3));
		
		//create function buttons
		JPanel functionPanel = new JPanel();
		JButton powerButton = new JButton("POWER");
		JButton functionButton = new JButton("FUNCTION");
		JPanel buttonPanel = new JPanel();
			JButton leftButton = new JButton("left <");
			JButton rightButton = new JButton("right >");
			JButton downButton = new JButton("down V");
			JButton upButton = new JButton("up ^");
		JButton swapButton = new JButton("SWAP");
		
		
		//create signal buttons
		JPanel signalPanel = new JPanel(new GridLayout(2,1));
			JPanel startPanel = new JPanel(new GridLayout(3,5));
			
				JLabel empty=new JLabel("     ");
					JLabel SL1=new JLabel("1");
					JLabel SL3=new JLabel("3");
					JLabel SL5=new JLabel("5");
					JLabel SL7=new JLabel("7");
				
				JLabel startLabel=new JLabel("Start");
					JButton signalButton1 = new JButton("1");
					JButton signalButton3 = new JButton("3");
					JButton signalButton5 = new JButton("5");
					JButton signalButton7 = new JButton("7");
				
				JLabel EDLabel = new JLabel("Enable/Disable");
					JCheckBox CB1 = new JCheckBox();
					JCheckBox CB3 = new JCheckBox();
					JCheckBox CB5 = new JCheckBox();
					JCheckBox CB7 = new JCheckBox();
				
			JPanel finishPanel = new JPanel(new GridLayout(3,5));

				JLabel empty2=new JLabel("     ");
					JLabel SL2=new JLabel("2");
					JLabel SL4=new JLabel("4");
					JLabel SL6=new JLabel("6");
					JLabel SL8=new JLabel("8");
			
				JLabel finishLabel=new JLabel("Finish");
					JButton signalButton2 = new JButton("2");
					JButton signalButton4 = new JButton("4");
					JButton signalButton6 = new JButton("6");
					JButton signalButton8 = new JButton("8");
			
				JLabel EDLabel2 = new JLabel("Enable/Disable");
					JCheckBox CB2 = new JCheckBox();
					JCheckBox CB4 = new JCheckBox();
					JCheckBox CB6 = new JCheckBox();
					JCheckBox CB8 = new JCheckBox();
				
		//create printer
		JPanel printerPanel = new JPanel(new GridLayout(2,1));
			JButton printButton = new JButton("Printer Pwr");
			JTextArea printer = new JTextArea(80,100);
			
		//create numpad input
		JPanel numpadPanel = new JPanel(new GridLayout(4,3));
		JButton numpadButton1 = new JButton("1");
		JButton numpadButton2 = new JButton("2");
		JButton numpadButton3 = new JButton("3");
		
		JButton numpadButton4 = new JButton("4");
		JButton numpadButton5 = new JButton("5");
		JButton numpadButton6 = new JButton("6");
		
		JButton numpadButton7 = new JButton("7");
		JButton numpadButton8 = new JButton("8");
		JButton numpadButton9 = new JButton("9");
		
		JButton numpadButtonStar = new JButton("*");
		JButton numpadButton0 = new JButton("0");
		JButton numpadButtonHash = new JButton("#");
			
		//create backview's ports
		JPanel backviewPanel = new JPanel(new GridLayout(3,1));
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
			
		JLabel usbLabel = new JLabel("USB Port");
		JCheckBox usbPort = new JCheckBox();
		
		//create screen to show result
		JPanel screenPanel = new JPanel(new GridLayout(2,1));
		JLabel screenLabel = new JLabel("Queue/Running/Final Time");
			JTextArea screen = new JTextArea(100,100);
		
	}
	
	public static void main(String[] args){
		CTGUI gui = new CTGUI();	
	}
}
