import java.util.ArrayList;

/**
 * first class designed mainly to implement methods in in First seating class
 */

public class First extends Economy {
    public static Passenger[][] firstSeats;
    String seatPref;
    helper helper = new helper();

    First() {
        firstSeats = new Passenger[2][4];
    }

    /**
     * Adds passengers to First class
     *
     * @param name     Name of passenger
     * @param seatPref Passenger's seating preference
     * @return Returns whether passenger is assigned seat of not
     */
    public boolean addPassenger(String name, String seatPref) {
        int row = 0, col = 0;
        boolean seatAvail = false;

        if (this.seatCount() == 0) {
            System.out.println("class booked.");
            return true;
        }
        if (seatPref.equals("W")) {
            loop:
            for (row = 0; row < firstSeats.length; row++) {
                for (col = 0; col <= 3; col += 3) {
                    if (firstSeats[row][col] == null) {
                        firstSeats[row][col] = new Passenger(name, "I", "I");
                        System.out.println("seat: " + (row + 1) + helper.letter(col));
                        seatAvail = true;
                        break loop;

                    }
                }
            }

        } else if (seatPref.equals("A")) {
            System.out.println("User entered A for preference");
            loop:
            for (row = 0; row < firstSeats.length; row++) {
                for (col = 1; col <= 2; col++) {
                    if (firstSeats[row][col] == null) {
                        firstSeats[row][col] = new Passenger(name, "I", "I");
                        // System.out.println(firstSeats[row][col]);
                        System.out.println(" seat: " + (row + 1) + helper.letter(col));
                        seatAvail = true;
                        break loop;
                    }
                }
            }
        } else {
            System.out.println("Invalid seat preference selection.");
        }
        return seatAvail;
    }

    /**
     * Adds group of passengers to class
     *
     * @param groupName    Name of group
     * @param Names        Names of members of group
     * @param serviceClass Members' preferred service class
     */
    public void addGroup(String groupName, String Names, String serviceClass) {
        String[] names;
        String split = ", ";
        names = Names.split(split);
        int totalPeople = names.length;
        ArrayList<Passenger> people = new ArrayList<>();


        for (int i = 0; i < names.length; i++) {
            people.add(new Passenger(names[i], "G", groupName));
        }

        if (totalPeople > this.seatCount()) {
            System.out.println("Enough seats not available");
            return;
        }

        int counter = 0;
        if (totalPeople <= 4) {
            loop:
            for (int i = 0; i < 2; i++) {
                counter = 0;
                for (int j = 0; j < 4; j++) {
                    if (firstSeats[i][j] != null) {
                        counter = 0;
                    } else {
                        counter++;
                        if (counter == totalPeople) {
                            System.out.println("Seats assigned:");
                            for (int k = 1; k <= totalPeople; k++) {
                                firstSeats[i][k] = people.remove(0);
                                System.out.println((i + 1) + helper.letter(k) + ", " + firstSeats[i][k].getName());
                            }
                            break loop;
                        }
                    }
                }
            }
        } else {
            while (people.size() != 0) {
                int maxGroup = 0;
                int maxRow = 0;
                int maxCol = 0;

                for (int i = 0; i < 2; i++) {
                    counter = 0;
                    for (int j = 0; j < 4; j++) {
                        if (firstSeats[i][j] != null) {
                            counter = 0;
                        } else {
                            counter++;
                            if (counter > maxGroup) {
                                maxGroup = counter;
                                maxRow = i;
                                maxCol = j - counter + 1;
                            }
                        }
                    }
                }

                int p = maxCol + maxGroup - 1;
                if (maxGroup > people.size()) {
                    p = maxCol + people.size() - 1;
                }
                System.out.println("Seats assigned: ");
                for (int j = maxCol; j <= p; j++) {
                    firstSeats[maxRow][j] = people.remove(0);
                    System.out.println((maxRow + 1) + helper.letter(j) + ", " + firstSeats[maxRow][j].getName());
                }
            }
        }
    }

    /**
     * @param type Individual or group
     * @param name Name of passenger whose reservation to cancel
     */
    public void cancelReservations(String type, String name) {
        if (type.equals("I")) {
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 4; j++) {
                    if (firstSeats[i][j] == null) {/*do nothing. Seat is empty already.*/
                    } else if (firstSeats[i][j].getName().equals(name)) {
                        firstSeats[i][j] = null;
                        System.out.println("Reservation Cancelled");
                    } else {
                    }
                }
            }
        } else if (type.equals("G")) {
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 4; j++) {
                    if (firstSeats[i][j] == null) {/*do nothing. Seat is empty already.*/
                    } else if (firstSeats[i][j].getGroup().equals(name)) {
                        firstSeats[i][j] = null;
                    } else {
                    }
                }
            }
            System.out.println("Reservations Cancelled");
        } else {
            System.out.println("Invalid type entered. Please enter I or G.");
        }
    }

    /**
     * prints available seats
     */
    public void availChart() {
        System.out.println("First Class: ");

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                if (firstSeats[i][j] == null) {
                    System.out.println((i + 1) + helper.letter(j) + ": Available");
                }
            }
            System.out.println();
        }

    }

    /**
     * Prints manifest list
     */
    public void manifest() {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                if (firstSeats[i][j] != null) {
                    System.out.print((i + 1) + helper.letter(j) + ": " + firstSeats[i][j].getName());
                    System.out.println();
                }
            }
        }
    }

    /**
     * count number of seats in First class
     *
     * @return seat count
     */
    public int seatCount() {
        int count = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                if (firstSeats[i][j] == null) {
                    count++;
                }
            }
        }
        return count;
    }
}
