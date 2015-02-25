import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

// External libraries contained in json-simple-1.1.1.jar
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;

public class FileHelper
{

	/**
	 * This function takes an arraylist of arrays as input and writes its data out
	 * to a file.
	 * @param distance The Arraylist of datapoints for distance
	 * @param velocity Arraylist of datapoints for velocity
	 * @param acceleration The Arraylist of datapoints for acceleration
	 * @param filename The desired filename of the target file. Note, this is 
	 * expressed as the full pathname.
	 * @author Delvison Castillo
	 */
	public static boolean writeCSV(ArrayList<double[]> distance,
	ArrayList<double[]> velocity, ArrayList<double[]> acceleration, 
	String filename) throws IOException
	{
		String csv_data = "";
		FileWriter file = new FileWriter(filename);

		csv_data += pointsToString(distance, "distance");
		csv_data += pointsToString(velocity, "velocity");
		csv_data += pointsToString(acceleration, "acceleration");
		
		try
		{
			file.write(csv_data);
			System.out.println("\n"+csv_data);
			return true;
		} catch (IOException e){
			e.printStackTrace();
			return false;
		} finally {
			file.flush();
			file.close();
		}
	}

	/**
	 * This functions takes an arraylist composed of arrays that each represent a
	 * point in a line plot. The points are extracted and added to a String
	 * meant for a csv file. The string is then returned.
	 * @param set The arraylist composed of arrays that represent a line plot
	 * @param hdr String to tag the data set. Header.
	 * @author Delvison Castillo
	 */
	public static String pointsToString(ArrayList<double[]> set, String hdr)
	{
		// create json array for set
		String ret ="\n"+hdr+":\n";

		// iterate through data and write
		for (double[] vector : set)
		{
			ret += vector[0]+" , "+vector[1]+"\n";
		}
		ret += "\n";
		return ret;
	}

	/**
	 * This function takes an arraylist of arrays as input and writes its data out
	 * to a file. This function assumes that there are 3 data points per line.
	 * @param distance The Arraylist of datapoints for distance
	 * @param velocity Arraylist of datapoints for velocity
	 * @param acceleration The Arraylist of datapoints for acceleration
	 * @param filename The desired filename of the target file. Note, this is 
	 * expressed as the full pathname.
	 * @param del The delimeter used to seperate datum in a line.
	 * @author Delvison Castillo
	 */
/*
	public static boolean write(ArrayList<double[]> distance,
	ArrayList<double[]> velocity, ArrayList<double[]> acceleration, 
	String filename) throws IOException
	{
		// create json object that represents whole file
		JSONObject all = new JSONObject();

		// create json arrat for set1
		JSONArray ps1 = pointsToJSON(distance);
		JSONArray ps2 = pointsToJSON(acceleration);
		JSONArray ps3 = pointsToJSON(velocity);

		all.put("Distance",ps1);
		all.put("Acceleration",ps2);
		all.put("Velocity",ps3);
		FileWriter file = new FileWriter(filename);
		
		try
		{
			file.write(all.toJSONString());
			System.out.println("Successfully Copied JSON Object to File...");
			System.out.println("\nJSON Object: " + all);
			return true;
		} catch (IOException e){
			e.printStackTrace();
			return false;
		} finally {
			file.flush();
			file.close();
		}
	}
*/

	/**
	 * This functions takes an arraylist composed of arrays that each represent a
	 * point in a line plot. The points are extracted and added to a JSONArray
	 * object. The JSONArray object is then returned.
	 * @param set The arraylist composed of arrays that represent a line plot
	 * @author Delvison Castillo
	 */
/*
	public static JSONArray pointsToJSON(ArrayList<double[]> set)
	{
		// create json array for set
		JSONArray ps = new JSONArray();

		// iterate through data and write
		for (double[] vector : set)
		{
			JSONArray point = new JSONArray();
			point.add("x:"+vector[0]);
			point.add("y:"+vector[1]);
			ps.add(point);
		}
		return ps;
	}
*/


/*
	public static void main(String[] args) throws IOException
	{
		// test write function
		ArrayList<double[]> testdata = new ArrayList<double[]>();
		double[] a = {14.456, 0.002};
		double[] b = {1.456, 10.002};
		double[] c = {4.456, 1.002};

		testdata.add(a);
		testdata.add(b);
		testdata.add(c);

		writeCSV(testdata,testdata,testdata, "/home/delvison/Desktop/testdata.txt");
	}
*/

}
