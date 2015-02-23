import gnu.io.CommPortIdentifier;

import java.io.File; 

import java.awt.EventQueue;
import java.util.Enumeration;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.border.TitledBorder;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JToggleButton;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SerialGUI {
	
	private int checkSpline;
	
	public int Baud_rate = 9600;
	public int Data_Bits = 8;
	public int Parity  = 1;
	
	public int Upper_Bound = 500;
	public int Lower_Bound = 0;
	public int Threshold_Limit  = 2500 ;
	
	private JFrame frmSunyk;
	private JTextField upperBound;
	private JTextField lowerBound;
	private JTextField threshold;
	private JTextField textPath;
	
		
	//SerialChart chart1 = new SerialChart("Test");
	//chart1.
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		 EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SerialGUI window = new SerialGUI();
					window.frmSunyk.setVisible(true);
					//window.frame.setTitle("SUNYK 2015");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SerialGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSunyk = new JFrame();
		frmSunyk.setResizable(false);
		frmSunyk.setTitle("RadioActive Curie-Osity 2015");
		frmSunyk.setBounds(100, 100, 606, 345);
		frmSunyk.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSunyk.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Arduino properties", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 273, 240);
		frmSunyk.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblPort = new JLabel("Port : ");
		lblPort.setBounds(10, 23, 46, 14);
		panel.add(lblPort);
		
		JLabel lblBaudrate = new JLabel("BaudRate :");
		lblBaudrate.setBounds(10, 48, 82, 14);
		panel.add(lblBaudrate);
		
		JLabel lblDatabits = new JLabel("DataBits :");
		lblDatabits.setBounds(10, 73, 75, 14);
		panel.add(lblDatabits);
		
		JLabel lblParity = new JLabel("Parity : ");
		lblParity.setBounds(10, 98, 55, 14);
		panel.add(lblParity);
		
		JComboBox portBox = new JComboBox();
		portBox.setBounds(90, 20, 99, 20);
		Enumeration portList = CommPortIdentifier.getPortIdentifiers();
        while(portList.hasMoreElements()){
               CommPortIdentifier portId = (CommPortIdentifier) portList.nextElement();
               portBox.addItem(portId.getName().toString());
        }
		panel.add(portBox);
		
		JComboBox baudBox = new JComboBox();
		baudBox.setBounds(90, 45, 99, 20);
		baudBox.addItem("300");
		baudBox.addItem("600");
		baudBox.addItem("1200");
		baudBox.addItem("2400");
		baudBox.addItem("4800");
		baudBox.addItem("9600");
		baudBox.addItem("14400");
		baudBox.addItem("19200");
		baudBox.addItem("28800");
		baudBox.addItem("38400");
		baudBox.addItem("57600");
		baudBox.addItem("115200");
		baudBox.setSelectedItem("9600");
		panel.add(baudBox);
		
		JComboBox dataBitsBox = new JComboBox();
		dataBitsBox.setBounds(90, 70, 99, 20);
		dataBitsBox.addItem("4");
		dataBitsBox.addItem("8");
		dataBitsBox.addItem("16");
		dataBitsBox.setSelectedItem("8");
		panel.add(dataBitsBox);
		
		JComboBox parityBox = new JComboBox();
		parityBox.setBounds(90, 95, 99, 20);
		parityBox.addItem("1");
		parityBox.addItem("0");
		parityBox.setSelectedItem("1");
		panel.add(parityBox);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Graph properties", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		panel_1.setBounds(293, 11, 297, 240);
		frmSunyk.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblOrdinateRange = new JLabel("Ordinate Upper Bound :");
		lblOrdinateRange.setBounds(10, 23, 170, 14);
		panel_1.add(lblOrdinateRange);
		
		JLabel lblOridinateLowerBound = new JLabel("Oridinate Lower Bound :");
		lblOridinateLowerBound.setBounds(10, 48, 177, 14);
		panel_1.add(lblOridinateLowerBound);
		
		JLabel lblThreshold = new JLabel("Threshold :");
		lblThreshold.setBounds(20, 285, 82, 14);
		frmSunyk.getContentPane().add(lblThreshold);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Graph type", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		panel_3.setBounds(10, 128, 277, 101);
		panel_1.add(panel_3);
		panel_3.setLayout(null);
		
		JCheckBox chkDT = new JCheckBox("Distance vs. Time");
		chkDT.setBounds(56, 20, 155, 23);
		chkDT.setSelected(true);
		panel_3.add(chkDT);
		
		JCheckBox chkVT = new JCheckBox("Velocity vs. Time");
		chkVT.setBounds(56, 46, 155, 23);
		panel_3.add(chkVT);
		
		JCheckBox chkAT = new JCheckBox("Acceleration vs. Time");
		chkAT.setBounds(56, 72, 190, 23);
		panel_3.add(chkAT);
		
		upperBound = new JTextField();
		upperBound.setText("500");
		upperBound.setBounds(201, 20, 86, 20);
		panel_1.add(upperBound);
		upperBound.setColumns(10);
		
		lowerBound = new JTextField();
		lowerBound.setText("0");
		lowerBound.setBounds(201, 45, 86, 20);
		panel_1.add(lowerBound);
		lowerBound.setColumns(10);
		
		threshold = new JTextField();
		threshold.setText("2500");
		threshold.setBounds(105, 285, 86, 20);
		frmSunyk.getContentPane().add(threshold);
		threshold.setColumns(10);
		
		JLabel lblSpline = new JLabel("Spline : ");
		lblSpline.setBounds(10, 103, 60, 14);
		panel_1.add(lblSpline);
		
		JToggleButton tglSpline = new JToggleButton("Off");
		tglSpline.setSelected(false);
		tglSpline.setBounds(201, 101, 86, 23);
		tglSpline.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				if (tglSpline.isSelected() == true) {
					tglSpline.setLabel("On");
					checkSpline = 1;
				}
				else {
					tglSpline.setLabel("Off");
					checkSpline = 0;
				}
			}
		});
		panel_1.add(tglSpline);
		
		JLabel lblLogDataTo = new JLabel("Log :");
		lblLogDataTo.setBounds(20, 262, 65, 20);
		frmSunyk.getContentPane().add(lblLogDataTo);
		
		textPath = new JTextField();
		textPath.setBounds(105, 259, 485, 20);
		textPath.setEditable(false);
		java.net.URL location = SerialGUI.class.getProtectionDomain().getCodeSource().getLocation();
        textPath.setText((location.getFile())+"plot_data.txt");
        //textPath.enable(false);
		frmSunyk.getContentPane().add(textPath);
		textPath.setColumns(10);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				System.exit(0);
			}
		});
		btnCancel.setBounds(501, 310, 89, 23);
		frmSunyk.getContentPane().add(btnCancel);
		
		JButton btnAbout = new JButton("About");
		btnAbout.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				JOptionPane.showMessageDialog(frmSunyk, "Developed for Radio-Active Curie-Osity.\n\n"
						+ "SUNY KOREA 2014 Winter Project.\n","About",JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnAbout.setBounds(11, 310, 89, 23);
		frmSunyk.getContentPane().add(btnAbout);
		
		JButton btnPlot = new JButton("Plot");
		btnPlot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String[] args = null;
				SerialChart st = new SerialChart("Ra-Cu");
				
				st.DataBits = Integer.parseInt(dataBitsBox.getSelectedItem().toString());
				st.BaudRate = Integer.parseInt(baudBox.getSelectedItem().toString());
				st.parity = Integer.parseInt(parityBox.getSelectedItem().toString());
				
				if(checkSpline == 1) st.Spline = 1;
				else st.Spline = 0;
				
				if(chkDT.isSelected() == true) st.dt = 1;
				else st.dt = 0;
				
				if(chkVT.isSelected() == true) st.vt = 1;
				else st.vt = 0;
				
				if(chkAT.isSelected() == true) st.at = 1;
				else st.at = 0;
				
				if(portBox.getSelectedItem() != null){ st.port = portBox.getSelectedItem().toString();
					st.main(args);
					System.out.printf("%s\n",st.port);
				}
				else  JOptionPane.showMessageDialog(frmSunyk,"No ports detected. Cannot proceed.","Error"
						,JOptionPane.ERROR_MESSAGE);
				
			}
		});
		
		btnPlot.setBounds(402, 310, 89, 23);
		frmSunyk.getContentPane().add(btnPlot);
	}
}
