import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Date;
import java.util.Enumeration;

import gnu.io.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;//
import org.jfree.chart.axis.NumberAxis;//
import org.jfree.chart.axis.NumberTickUnit;//
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYSplineRenderer;//
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

  

public class SerialChart extends ApplicationFrame implements SerialPortEventListener {

public String port = "/dev/ttyACM0" ;  // This variable holds the port name
public int BaudRate = 9600; // Holds BaudRate
public int DataBits ; // Holds Databit value
public int parity ; //Holds parity value
public int Spline = 0;
public int dt  = 1 ; // distance time graph
public int vt = 1; // velocity time graph
public int at = 1; // acceleration time graph

	
private XYSeriesCollection xyCollection; // Collection of time series data  
private XYDataset xyDataset; // dataset that will be used for the chart  
private XYSeries seriesX; // X series data  
private XYSeries seriesY; // Y series data
private XYSeries seriesZ; // Z series data

private  int x_time_prev = 0,y_dist_prev = 0; 
private float y_speed, y_speed_prev = 0f;
private float y_acc = 0f;

private BufferedReader input; // input reader  
private OutputStream output; //output reader  
private SerialPort serialPort; // serial port object  
  
private String [] PORT_NAMES = {this.port}; // available ports
	
	public static void main(String[] args) {

		 SerialChart serialChartDemo = new SerialChart("SUNYK - RadioActive Curie-Osity 2015");  
		 serialChartDemo.pack();  
		 RefineryUtilities.centerFrameOnScreen(serialChartDemo);  
		 serialChartDemo.setVisible(true); 
		
	}

	private void initializeSerial()  
	{  
	  CommPortIdentifier portId = null;  
	  Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();  
	  System.out.printf("PortNames___ %s\n",PORT_NAMES);  
	  while(portEnum.hasMoreElements())  
	  {  
	   CommPortIdentifier currentPortIdentifier = (CommPortIdentifier)portEnum.nextElement();  
	   for(String portName : PORT_NAMES)  
	   {  
	    if(currentPortIdentifier.getName().equals(portName))  
	    {  
	     portId = currentPortIdentifier;  
	     break;  
	    }  
	   }     
	  }  
	    
	  if(portId == null)  
	  {  
	   System.out.println("Port not found");  
	   return;  
	  }  
	    
	  try {  
	     
	   serialPort = (SerialPort)portId.open(this.getClass().getName(), 2000);  
	   serialPort.setSerialPortParams(BaudRate,SerialPort.DATABITS_8, SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);  
	     
	   input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));  
	   output = serialPort.getOutputStream();  
	     
	   serialPort.addEventListener(this);  
	   serialPort.notifyOnDataAvailable(true);  
	     
	  } catch (Exception e) {  
	   System.err.println("Initialization failed : " + e.toString());  
	  }  
	}  	
	
	private JFreeChart createChart() {  
		  
	   JFreeChart chart = ChartFactory.createTimeSeriesChart(  
	           "Distance, Velocity, Acc. vs. Time",  	// title  
	           "Time(s)",             	// x-axis label  
	           "Distance(cm): Velocity(cm/s) : Acc.(cm/s^2)",   		// y-axis label  
	           xyCollection,            // data  
	           true,               		// create legend?  
	           true,               		// generate tooltips?  
	           false              		// generate URLs?  
	   );  
	   
	   chart.setBackgroundPaint(Color.white);  
	   
	  // if (Spline == 1 ){
		 XYSplineRenderer renderer = new XYSplineRenderer();
		 renderer.setShapesVisible(false);
		 chart.getXYPlot().setRenderer(renderer); // make smooth curves			     
	  // }
	  // else chart.getXYPlot().setRenderer(new XYItemRenderer);
	  
	   XYPlot plot = (XYPlot) chart.getPlot();  
	  
	   DateAxis axis = (DateAxis) plot.getDomainAxis();  
	   axis.setLowerBound(0);
       axis.setAutoRange(true);
       axis.setFixedAutoRange(30000.0);
       
       plot.getRangeAxis().setRange(-300 , 300);
       
       plot.getRenderer().setSeriesPaint(2, Color.YELLOW);
	
	   return chart;  
	} 
	
	public SerialChart(String title) {  
	  super(title);  
		    
	  initializeSerial();  
		    
	  xyCollection = new XYSeriesCollection();  
	  seriesX = new XYSeries("Distance"); 
	  seriesY = new XYSeries("Velocity");
	  seriesZ = new XYSeries("Acceleration");
	  
	  xyCollection.addSeries(seriesX);
	  xyCollection.addSeries(seriesY);
	  xyCollection.addSeries(seriesZ);
	  	  		    
	  JFreeChart chart = createChart();  
	  ChartPanel chartPanel = new ChartPanel(chart); 
	  chartPanel.setFillZoomRectangle(true);  
	  chartPanel.setMouseWheelEnabled(true);  
	  chartPanel.setPreferredSize(new java.awt.Dimension(1000,500));  
	  setContentPane(chartPanel);  
    }  
	
	@Override  
	public synchronized void serialEvent(SerialPortEvent event) {  
	 if(event.getEventType() == SerialPortEvent.DATA_AVAILABLE)  
	 {  
	  try  
	  {  
		  Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {

		        public void run() {
		            try {
						input.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        }
		    }));
		  
	   String inputLine = input.readLine();  
	  
	   int x_time = Integer.parseInt(inputLine.substring(inputLine.indexOf(',')+1,inputLine.length()));
	   int y_dist = Integer.parseInt(inputLine.substring(0,inputLine.indexOf(',')));
	   
	  
	 
	   /* Calculate average data point for 3 sets */
	   
	   for(int i=0; i<=5 ; i++){
		   x_time = x_time + Integer.parseInt(inputLine.substring(inputLine.indexOf(',')+1,inputLine.length()));
		   y_dist = y_dist + Integer.parseInt(inputLine.substring(0,inputLine.indexOf(',')));
	   }
	   
	   x_time = x_time/5;
	   y_dist = y_dist/5;
	   
	    /* Calculate velocity 
	    * 
	    * This algorithm simply calculates the difference between
	    * initial and final distance values and divides by the the time elapsed
	    * 
	    */ 
	  
	    if (x_time > 0) y_speed = (float)((y_dist-y_dist_prev)/((x_time-x_time_prev)/1000f));
	   
	    /* Calculate acceleration
	     * 
	     *  This algorithm simply calculates the difference between
	     *  initial and final speed values and divides by the time elapsed
	     */
	    
	    if (x_time > 0) y_acc = (float)((y_speed-y_speed_prev)/((x_time-x_time_prev)/1000f));
	  
	    if(dt == 1)  this.xyCollection.getSeries(0).add(x_time,y_dist);
	    if(vt == 1)  this.xyCollection.getSeries(1).add(x_time-2,y_speed);
	    if(at == 1)  this.xyCollection.getSeries(2).add(x_time-3,y_acc);
	  
	  System.out.printf("%s : %f\n",inputLine,(x_time-x_time_prev)/1000f);   
	  x_time_prev = x_time;
	  y_dist_prev = y_dist;
	  y_speed_prev = y_speed;
	    	     
	 
	  }  
	  catch(Exception ex)  
	  {  
	   ex.printStackTrace();  
	  }  
	 }  
	}  
	
	public synchronized void close()  
	 {  
	  if(serialPort != null)  
	  {  
	   serialPort.removeEventListener();  
	   serialPort.close();  
	  }  
	 } 
		
}


