import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * Program draw the map of Turkey.
 * There are cities and districts in the map. 
 * Blue circle cities, districts are shown with gray circles.
 * @author Nur Imece - 041601003
 * @since 05.8.2020
 */

public class Nur_Imece {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StdDraw.setCanvasSize(1000,600); 

		StdDraw.setXscale(25,46); // Turkey is located in the eastern meridian 26-45
		StdDraw.setYscale(35, 43); // Turkey is located in the north parallels 36-42

		String filename = "src/Cities.txt"; // the file we will receive the data

		File file = new File(filename);
		Scanner input = null;
		try {
			input = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println(filename + ": Input file can not be found!\nExiting program...");
			System.exit(1);
		}
		/**
		 * We created a string to use in if-statement that it is admin or minor.
		 */
		String sAdmin = new String("admin"); 
		String sMinor = new String("minor");
		/**
		 *I created arrays based on the data in the txt file
		 */
		String line[]= new String[2000];
		String City[] = new String[2000];
		int Latitude[] = new int[2000];  
		int Longitude[] = new int[2000];
		String Admin[] = new String[2000];
		String City_or_Minor[] = new String[2000];
		String Population[] = new String[2000];
		int radius[] = new int[2000];

		int lineCounter =0; 
	// we need this counter to connect the values to each other and we will use it to index the arrays

		input.nextLine();
		while(input.hasNext()) {
			line[lineCounter] = input.nextLine();
			String[] tokens = line[lineCounter].split(";");
			City[lineCounter] = tokens[0]; // Our first value after split is city
			Latitude[lineCounter] = Integer.parseInt(tokens[1]); // Our 2. value after split is latitude
			Longitude[lineCounter] = Integer.parseInt(tokens[2]); //Our 3. value after split is longitude
			Admin[lineCounter] = tokens[3]; // Our 4. value after split is  admin - city name
			City_or_Minor[lineCounter] = tokens[4]; // Our 5. value after split is  minor or admin
			Population[lineCounter]= tokens[5]; // Our 6. value after split is population
			radius[lineCounter] = 4*(int) Math.sqrt((Integer.parseInt(tokens[5])/50000));
			// calculating radius value using population
			lineCounter++;
		}


		//create 2 dimensional arrays to the number of minor and city
		double [][] circles = new double [City_or_Minor.length][3];

// Drawn a circle according to whether it is admin or minor.
 
		for(int i=0;i<City_or_Minor.length;i++) {
			if(City_or_Minor[i] != null) {

				if(City_or_Minor[i].equals(sAdmin)) {
					/**
					 * If City_or_Minor value is admin
					*  take the values x, y, r, 
					*  firstly we draw black circles
					*  then we draw the blue circle according to the r value
					 */

					double r = radius[i];
					double x = Latitude[i];
					double y = Longitude[i];
					circles[i][0] = x;
					circles[i][1] = y;
					circles[i][2] = r;

					StdDraw.circle(y/1000,x/1000,0.03); // coordinates of the circle
					StdDraw.setPenColor(StdDraw.BLACK);// determines the color of the circle
					StdDraw.filledCircle(y/1000,x/1000,0.03); // which circle should we paint

					StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
					StdDraw.filledCircle(y/1000,x/1000,r/100);


					Font font = new Font("Arial", Font.ITALIC, 10); // edits text style
					StdDraw.setFont(font);
					StdDraw.setPenColor(StdDraw.BLACK);
					StdDraw.text(y/1000,x/1000, Admin[i]); // writes the names of cities


				}
				if(City_or_Minor[i].equals(sMinor)) {
					/**
					 *  If City_or_Minor value is minor, 
					 *  we divide the x and y coordinates by 1000 
					 *  and draw the gray circles with a radius of 0.025.
					 */

					double x = Latitude[i];
					double y = Longitude[i];
					StdDraw.setPenColor(StdDraw.GRAY);
					StdDraw.filledCircle(y/1000,x/1000,0.025);

				}
			}

		}

	}
}
