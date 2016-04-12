import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
				
			}
		});
			
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
					
				//add startPanel components to startPanel
					startPanel.add(empty);
					startPanel.add(SL1);
					startPanel.add(SL3);
					startPanel.add(SL5);
					startPanel.add(SL7);
					
					startPanel.add(startLabel);
					startPanel.add(signalButton1);
					startPanel.add(signalButton3);
					startPanel.add(signalButton5);
					startPanel.add(signalButton7);
					
					startPanel.add(EDLabel);
					startPanel.add(CB1);
					startPanel.add(CB3);
					startPanel.add(CB5);
					startPanel.add(CB7);
					
					signalButton1.addActionListener(new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent event){
							
						}
					});
					signalButton3.addActionListener(new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent event){
							
						}
					});
					signalButton5.addActionListener(new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent event){
							
						}
					});
					signalButton7.addActionListener(new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent event){
							
						}
					});
					CB1.addActionListener(new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent event){
							
						}
					});
					CB3.addActionListener(new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent event){
							
						}
					});
					CB5.addActionListener(new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent event){
							
						}
					});
					CB7.addActionListener(new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent event){
							
						}
					});
				
				//add start panel to signal panel
					signalPanel.add(startPanel);
					
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
					
				//add components to finishPanel
					finishPanel.add(empty2);
					finishPanel.add(SL2);
					finishPanel.add(SL4);
					finishPanel.add(SL6);
					finishPanel.add(SL8);
				
					finishPanel.add(finishLabel);
					finishPanel.add(signalButton2);
					finishPanel.add(signalButton4);
					finishPanel.add(signalButton6);
					finishPanel.add(signalButton8);
				
					finishPanel.add(EDLabel2);
					finishPanel.add(CB2);
					finishPanel.add(CB4);
					finishPanel.add(CB6);
					finishPanel.add(CB8);
					
					signalButton2.addActionListener(new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent event){
							
						}
					});
					signalButton4.addActionListener(new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent event){
							
						}
					});
					signalButton6.addActionListener(new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent event){
							
						}
					});
					signalButton8.addActionListener(new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent event){
							
						}
					});
					CB2.addActionListener(new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent event){
							
						}
					});
					CB4.addActionListener(new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent event){
							
						}
					});
					CB6.addActionListener(new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent event){
							
						}
					});
					CB8.addActionListener(new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent event){
							
						}
					});
			
				//add finish panel to signal Panel
					signalPanel.add(finishPanel);
				
		//create printer
		JPanel printerPanel = new JPanel(new GridLayout(2,1));
			JButton printButton = new JButton("Printer Pwr");
			JTextArea printer = new JTextArea(80,100);
			
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
		
		//add components to numpadPanel
			numpadPanel.add(numpadButton1);
			numpadPanel.add(numpadButton2);
			numpadPanel.add(numpadButton3);
			numpadPanel.add(numpadButton4);
			numpadPanel.add(numpadButton5);
			numpadPanel.add(numpadButton6);
			numpadPanel.add(numpadButton7);
			numpadPanel.add(numpadButton8);
			numpadPanel.add(numpadButton9);
			numpadPanel.add(numpadButtonStar);
			numpadPanel.add(numpadButton0);
			numpadPanel.add(numpadButtonHash);
			
			numpadButton1.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent event){
					
				}
			});
			numpadButton2.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent event){
					
				}
			});
			numpadButton3.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent event){
					
				}
			});
			numpadButton4.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent event){
					
				}
			});
			numpadButton5.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent event){
					
				}
			});
			numpadButton6.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent event){
					
				}
			});
			numpadButton7.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent event){
					
				}
			});
			numpadButton8.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent event){
					
				}
			});
			numpadButton9.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent event){
					
				}
			});
			numpadButton0.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent event){
					
				}
			});
			numpadButtonStar.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent event){
					
				}
			});
			numpadButtonHash.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent event){
					
				}
			});
			
		//create backview's ports
		JPanel backViewPanel = new JPanel(new GridLayout(4,1));
		JLabel backViewLabel = new JLabel("Sensor Connetcion");
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
			
			inpCB1.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent event){
					
				}
			});
			inpCB3.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent event){
					
				}
			});
			inpCB5.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent event){
					
				}
			});
			inpCB7.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent event){
					
				}
			});
			inpCB2.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent event){
					
				}
			});
			inpCB4.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent event){
					
				}
			});
			inpCB6.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent event){
					
				}
			});
			inpCB8.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent event){
					
				}
			});
			
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
		
		//add components to screenPanel
			screenPanel.add(screenLabel);
			screenPanel.add(screen);
			
		//add to window and display
			window.setSize(750, 500);
			window.add(functionPanel);
			window.add(signalPanel);
			window.add(printerPanel);
			window.add(numpadPanel);
			window.add(backViewPanel);
			window.add(screenPanel);
			window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			window.setVisible(true);
	}
	
	public static void main(String[] args){
		CTGUI gui = new CTGUI();	
	}
	
	
	
}
