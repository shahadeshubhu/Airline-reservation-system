/**
 * Airline class designed to mainly make changes in methods for file
 */

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class airline extends First {
    public void addFromFile(Passenger passenger, int row, int col) {
        if (row > 1) {
            this.economySeats[row - 9][col] = passenger;
        } else {
            this.firstSeats[row][col] = passenger;
            System.out.println(passenger);
        }
    }

    /**
     * Saves reservation in file
     *
     * @param flightName flight number as entered in command line argument (no extension).
     */
    public void saveInFile(String flightName) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(new FileOutputStream(flightName + ".txt"));
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                if (firstSeats[i][j] != null) {
                    Passenger passenger = firstSeats[i][j];

                    writer.print((i + 1));
                    writer.print(helper.letter(j) + ", ");
                    writer.print(passenger.getType() + ", ");
                    if (passenger.getType().equalsIgnoreCase("G")) {
                        writer.print(passenger.getGroup() + ", ");
                    }
                    writer.print(passenger.getName());
                    writer.println();
                }
            }
        }

        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 6; j++) {
                if (economySeats[i][j] != null) {
                    Passenger passenger = economySeats[i][j];

                    writer.print((i + 10)); //row number as 1-2 (starting at 1)
                    writer.print(helper.letter(j) + ", "); //seat letter A,B,C,D
                    writer.print(passenger.getType() + ", "); //type, I or G.
                    if (passenger.getType().equalsIgnoreCase("G"))  //group name, if applicabl
                    {
                        writer.print(passenger.getGroup() + ", ");
                    }
                    writer.print(passenger.getName());
                    writer.println();
                }
            }
        }
        writer.close();
    }
}
