import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.File;


public class FileHelper
{
	/**
	 * This function takes an arraylist of arrays as input and writes its data out
	 * to a file. This function assumes that there are 3 data points per line.
	 * @param data The ArrayList of data points
	 * @param filename The desired filename of the target file. Note, this is 
	 * expressed as the full pathname.
	 * @param del The delimeter used to seperate datum in a line.
	 * @author Delvison Castillo
	 */
	public static boolean write(ArrayList<double[]> data, String filename, 
	String del)
	{
		try
		{
			// create buffered writer
			BufferedWriter bw = new BufferedWriter(
			new OutputStreamWriter(
			new FileOutputStream( 
			new File(filename) ))) ;

			// iterate through data and write
			for (double[] vector : data)
			{
				bw.write(vector[0]+del+vector[1]+del+vector[2]+"\n");
			}
			// close buffered writer
			bw.close();
		} catch (Exception e){
			//TODO: Specify exact exceptions
			System.out.println("Error");
		}
		return false;	
	}

/*
	public static void main(String[] args)
	{
		// test write function
		ArrayList<double[]> testdata = new ArrayList<double[]>();
		double[] a = {14.456, 0.002, 17.003};
		double[] b = {1.456, 10.002, 7.003};
		double[] c = {4.456, 1.002, 19.003};

		testdata.add(a);
		testdata.add(b);
		testdata.add(c);

		write(testdata, "/home/delvison/Desktop/testdata.txt");
	}
*/
}
