import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;

import gnu.io.*;

import java.util.Enumeration;



public class SerialTest implements SerialPortEventListener {
SerialPort serialPort;
    /** The port we're normally going to use. */
private static final String PORT_NAMES[] = {                  "/dev/tty.usbserial-A9007UX1", // Mac OS X
        "/dev/ttyACM0", // Linux
        "COM35", // Windows
};
private BufferedReader input;
private OutputStream output;
private static final int TIME_OUT = 2000;
private static final int DATA_RATE = 9600;

public String inputLine=null;

public void initialize() {
    CommPortIdentifier portId = null;
    Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

    //First, Find an instance of serial port as set in PORT_NAMES.
    while (portEnum.hasMoreElements()) {
        CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
        for (String portName : PORT_NAMES) {
            if (currPortId.getName().equals(portName)) {
                portId = currPortId;
                break;
            }
        }
    }
    if (portId == null) {
        System.out.println("Could not find COM port.");
        return;
    }

    try {
        serialPort = (SerialPort) portId.open(this.getClass().getName(),
                TIME_OUT);
        serialPort.setSerialPortParams(DATA_RATE,
                SerialPort.DATABITS_8,
                SerialPort.STOPBITS_1,
                SerialPort.PARITY_NONE);

        // open the streams
        input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
        output = serialPort.getOutputStream();

        serialPort.addEventListener(this);
        serialPort.notifyOnDataAvailable(true);
    } catch (Exception e) {
        System.err.println(e.toString());
    }
}


public synchronized void close() {
    if (serialPort != null) {
        serialPort.removeEventListener();
        serialPort.close();
    }
}

public synchronized void serialEvent(SerialPortEvent oEvent) {
    if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
        try {
           // String inputLine=null; // inputline was here
            if (input.ready()) {
                inputLine = input.readLine();
                //System.out.println(inputLine);
                
                /* Graphing Functions should go here. tallying with inputline */
                System.out.printf("Distance : %d\n ", getDistance());
                System.out.printf("Time : %d\n", getTime());
            }

        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }
    //Ignore all the other eventTypes, but you should consider the other ones.
}

public int getDistance(){
	int temp = 0;	
    try {
       // String inputLine=null; // inputline was here
        //if (input.ready()) {
            String inLine = input.readLine();
            temp = Integer.parseInt(inLine.substring(0,inLine.indexOf(',')));
        //	return temp;
       // }
       // else return 10;

    } 
    catch (Exception e) {
        System.err.println(e.toString());
    }
    System.out.printf("%d", temp);
	return temp; 
}

public synchronized int getTime(){
	int temp = 0;	
        try {
           // String inputLine=null; // inputline was here
           // if (input.ready()) {
                String inLine = input.readLine();
                temp = Integer.parseInt(inLine.substring(inLine.indexOf(',')+1,inLine.length()));
            	//return temp;
            //}
           // else return 10;

        } 
        catch (Exception e) {
            System.err.println("getTime Error");
        }
		return temp; 	
}

public static void main(String[] args) throws Exception {
    SerialTest main = new SerialTest();
    main.initialize();
    Thread t=new Thread() {
        public void run() {
            //the following line will keep this app alive for 1000    seconds,
            //waiting for events to occur and responding to them    (printing incoming messages to console).
            try {Thread.sleep(1000000);} catch (InterruptedException    ie) {}
        }
    };
    t.start();
    System.out.println("Started");
}
}