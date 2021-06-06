/**
 * Helper class mainly designed to convert seating columns to corresponding letters
 */
public class helper {
    /**
     * @param seatColumn converts seat column to number
     */
    public static int column(String seatColumn) {
        int column = 0;

        if (seatColumn.equals("A")) {
            column = 0;
        } else if (seatColumn.equals("B")) {
            column = 1;
        } else if (seatColumn.equals("C")) {
            column = 2;
        } else if (seatColumn.equals("D")) {
            column = 3;
        } else if (seatColumn.equals("E")) {
            column = 4;
        } else if (seatColumn.equals("F")) {
            column = 5;
        } else {
            System.out.println("Invalid seat designation letter, cannot convert to column index.");
        }

        return column;
    }

    /**
     * @param seatColumn converts integer to corresponding seating column
     */
    public static String letter(int seatColumn) {
        String column = " ";
        if (seatColumn == 0) {
            column = "A";
        } else if (seatColumn == 1) {
            column = "B";
        } else if (seatColumn == 2) {
            column = "C";
        } else if (seatColumn == 3) {
            column = "D";
        } else if (seatColumn == 4) {
            column = "E";
        } else if (seatColumn == 5) {
            column = "F";
        } else {
            System.out.println("Column invalid");
        }
        return column;
    }
}
