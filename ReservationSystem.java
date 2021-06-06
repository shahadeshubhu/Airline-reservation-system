/**
 * Main class where program runs
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReservationSystem {
    /**
     * @param args file name
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        airline flightName = new airline();

        fileToData(args[0], flightName); //importing data from file (if existing) to data structure.

        String name, serviceClass, seatPref;
        Economy economy = new Economy();
        First first = new First();
        boolean prompt = true;
        Scanner input = new Scanner(System.in);

        while (prompt) {
            System.out.println("Add [P]assenger; Add [G]roup; [C]ancel Reservations; Print Seating [A]vailibility; Print [M]anifest; [Q]uit ");
            String userInput = input.nextLine();
            if (userInput.equals("P")) {
                System.out.println("name: ");
                name = input.nextLine();
                System.out.println("Service class: ");
                serviceClass = input.nextLine();
                System.out.println("Seat Preference: ");
                seatPref = input.nextLine();
                if (serviceClass.equals("Economy")) {
                    Economy individual = new Economy();
                    boolean seatAssigned = individual.addPassenger(name, seatPref);
                    while (!seatAssigned) {
                        System.out.println("Preference not available. Enter other preference");
                        seatPref = input.nextLine();
                        individual.addPassenger(name, seatPref);
                    }

                } else if (serviceClass.equals("First")) {
                    First individual = new First();
                    boolean seatAssigned = individual.addPassenger(name, seatPref);
                    while (!seatAssigned) {
                        System.out.println("Preference not available. Enter other preference");
                        seatPref = input.nextLine();
                        individual.addPassenger(name, seatPref);
                    }
                }

            } else if (userInput.equals("G")) {
                String gName, names;
                System.out.println("group name");
                gName = input.nextLine();
                System.out.println("names");
                names = input.nextLine();
                System.out.println("service class");
                serviceClass = input.nextLine();

                if (serviceClass.equals("Economy")) {
                    economy.addGroup(gName, names, serviceClass);

                } else if (serviceClass.equals("First")) {
                    first.addGroup(gName, names, serviceClass);
                }

            } else if (userInput.equals("C")) {
                String reservationType = "";
                String cancelName = "";

                System.out.print("Cancel [I]ndividual or [G]roup? ");
                reservationType = input.nextLine();
                if (reservationType.equals("I")) {
                    System.out.println("Name: ");
                    input.nextLine();
                    cancelName = input.nextLine();
                } else if (reservationType.equals("G")) {
                    System.out.println("Group name: ");
                    input.nextLine();
                    cancelName = input.nextLine();
                } else {
                    System.out.println(" Individual or Group?");
                }
                System.out.println("Enter service class: ");
                String Class = input.nextLine();
                if (Class.equals("Economy")) {
                    economy.cancelReservations(reservationType, cancelName);
                } else if (Class.equals("First")) {
                    economy.cancelReservations(reservationType, cancelName);
                }

            } else if (userInput.equals("A")) {
                String serviceclass;
                System.out.print("Service Class:");
                serviceclass = input.nextLine();
                if (serviceclass.equals("Economy")) {
                    economy.availChart();
                } else if (serviceclass.equals("First")) {
                    first.availChart();
                }
                System.out.println();

            } else if (userInput.equals("M")) {
                String serviceclass;
                System.out.print("Service Class:");
                serviceclass = input.nextLine();
                if (serviceclass.equals("Economy")) {
                    economy.manifest();
                } else if (serviceclass.equals("First")) {
                    first.manifest();
                }
                System.out.println();
            } else if (userInput.equals("Q")) {
                flightName.saveInFile(args[0]);
                prompt = false;
            } else {
                System.out.println("Please try again");
            }
        }
    }

    /**
     * Helper method designed to read file from given file name.
     *
     * @param fileName name of the file without the extension (.txt).
     * @param plane    specific Plane object to which the data imported will be stored.
     */
    public static void fileToData(String fileName, airline plane) throws FileNotFoundException {
        airline thisPlane = plane;
        fileName = fileName + ".txt";
        File file = new File(fileName);


        if (file.exists()) {
            Scanner fileInput = new Scanner(new FileInputStream(fileName));
            fileInput.nextLine();

            while (fileInput.hasNextLine()) {

                String textRow = fileInput.nextLine();
                String[] strings;
                String splitter = ", ";
                strings = textRow.split(splitter);
                String s1 = " ";
                String s2 = " ";

                if (strings[0].length() > 2) {
                    s1 = strings[0].substring(0, 2);
                    s2 = strings[0].substring(2, 3);
                } else {
                    s1 = strings[0].substring(0, 1);
                    s2 = strings[0].substring(1, 2);
                }

                int col = 0;
                if (s2.equals("A")) {
                    col = 0;
                } else if (s2.equals("B")) {
                    col = 1;
                } else if (s2.equals("C")) {
                    col = 2;
                } else if (s2.equals("D")) {
                    col = 3;
                } else if (s2.equals("E")) {
                    col = 4;
                } else if (s2.equals("F")) {
                    col = 5;
                } else {
                    System.out.println("invalid column");
                }

                int x;
                x = Integer.parseInt(s1);
                if (strings[1].equals("I")) {
                    Passenger prevPass = new Passenger(strings[2], strings[1], "n/a,indv");
                    thisPlane.addFromFile(prevPass, (x - 1), col);
                } else if (strings[1].equals("G")) {
                    Passenger prevPass = new Passenger(strings[3], strings[1], strings[2]);
                    thisPlane.addFromFile(prevPass, (x - 1), col);
                } else {
                    System.out.println("invalid type. Need to be I or G");
                }
            }
            fileInput.close();
        }
    }
}
