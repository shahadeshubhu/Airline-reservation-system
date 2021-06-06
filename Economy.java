/*
  Economy class mainly designed for implementing methods to economy seating class
 */

import java.util.ArrayList;

public class Economy {
    public static Passenger[][] economySeats;
    String seatPref;
    helper helper = new helper();

    Economy() {
        economySeats = new Passenger[20][6];
    }

    /**
     * Adds passenger reservations
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
            for (row = 0; row < economySeats.length; row++) {
                for (col = 0; col <= 5; col += 5) {
                    if (economySeats[row][col] == null) {
                        //Seat found.
                        economySeats[row][col] = new Passenger(name, "I", "I");
                        System.out.println("seat: " + (row + 10) + helper.letter(col));
                        seatAvail = true;
                        break loop;
                    }
                }
            }

        } else if (seatPref.equals("A")) {
            loop:
            for (row = 0; row < economySeats.length; row++) {
                for (col = 2; col <= 3; col++) {
                    if (economySeats[row][col] == null) {
                        //Seat found.
                        economySeats[row][col] = new Passenger(name, "I", "I");
                        System.out.println("seat: " + (row + 10) + helper.letter(col));
                        seatAvail = true;
                        break loop;
                    }
                }
            }

        } else if (seatPref.equals("C")) {
            loop:
            for (row = 0; row < economySeats.length; row++) {
                for (col = 1; col <= 4; col += 3) {
                    if (economySeats[row][col] == null) {
                        //Seat found.
                        economySeats[row][col] = new Passenger(name, "I", "I");
                        System.out.println("seat: " + (row + 10) + helper.letter(col));
                        seatAvail = true;
                        break loop;
                    }
                }
            }
        } else {
            System.out.println("Seat preference not available");
        }
        return seatAvail;
    }


    /**
     * Adds group reservation
     *
     * @param groupName    Name of group
     * @param Names        Names of members of group
     * @param serviceClass Members' preferred service class
     */
    public void addGroup(String groupName, String Names, String serviceClass) {
        String[] names;
        String comma = ", ";
        names = Names.split(comma);
        int totalPeople = names.length;
        ArrayList<Passenger> people = new ArrayList<>();

        for (int i = 0; i < names.length; i++) {
            people.add(new Passenger(names[i], "G", groupName));
        }

        if (totalPeople > this.seatCount()) {
            System.out.println("Enough Seats not available.");
            return;
        }

        int counter = 0;
        if (totalPeople <= 6) {
            loop:
            for (int i = 0; i < 20; i++) {
                counter = 0;
                for (int j = 0; j < 6; j++) {
                    if (economySeats[i][j] != null) {
                        counter = 0;
                    } else {
                        counter++;
                        if (counter == totalPeople) {
                            System.out.println("Seats assigned: ");
                            for (int k = 1; k <= totalPeople; k++) {
                                economySeats[i][k] = people.remove(0);
                                System.out.println((i + 10) + helper.letter(k) + ", " + economySeats[i][k].getName());
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

                for (int i = 0; i < 20; i++) {
                    counter = 0;
                    for (int j = 0; j < 6; j++) {
                        if (economySeats[i][j] != null) {
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
                System.out.println("Seats assigned: ");
                int p = maxCol + maxGroup - 1;
                if (maxGroup > people.size()) {
                    p = maxCol + people.size() - 1;
                }
                for (int j = maxCol; j <= p; j++) {
                    economySeats[maxRow][j] = people.remove(0);
                    System.out.println((maxRow + 10) + helper.letter(j) + ", " + economySeats[maxRow][j].getName());
                }
            }
        }
    }

    /**
     * Counts number of seats in economy class
     *
     * @return seat count
     */
    public int seatCount() {
        int count = 0;
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 6; j++) {
                if (economySeats[i][j] == null) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * @param reservationType Individual or group
     * @param name            name of passenger whose reservation to cancel
     */
    public void cancelReservations(String reservationType, String name) {
        if (reservationType.equals("I")) {
            for (int i = 0; i < 20; i++) {
                for (int j = 0; j < 6; j++) {
                    if (economySeats[i][j] == null) {
                    } else if (economySeats[i][j].getName().equalsIgnoreCase(name)) {
                        economySeats[i][j] = null;
                        System.out.println("Reservation Cancelled");
                    } else {
                    }
                }
            }
        } else if (reservationType.equals("G")) {
            for (int i = 0; i < 20; i++) {
                for (int j = 0; j < 6; j++) {
                    if (economySeats[i][j] == null) {
                    } else if (economySeats[i][j].getGroup().equals(name)) {
                        economySeats[i][j] = null;
                    } else {
                    }
                }
            }
            System.out.println("Reservations Cancelled");
        }
    }

    /**
     * prints available seats
     */
    public void availChart() {
        System.out.println();
        System.out.println("Economy Class: ");
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 6; j++) {
                if (economySeats[i][j] == null) {
                    System.out.println((i + 10) + helper.letter(j) + ": Available");
                }
            }
            System.out.println();
        }
    }

    /**
     * Prints manifest list
     */
    public void manifest() {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 6; j++) {
                if (economySeats[i][j] != null) {
                    System.out.print((i + 1) + helper.letter(j) + ": " + economySeats[i][j].getName());
                    System.out.println();
                }
            }
        }

    }
}

