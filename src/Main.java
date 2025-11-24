import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner kbd = new Scanner(System.in);

        //room type and catalog array(?)
        int[][] roomCatalog = {{1, 15}, {2, 10}, {3, 5}}; //1-standard 2-deluxe 3-suite

        //guest reservations array
        String[][] standard = new String[15][10]; //rooms x days
        String[][] deluxe = new String[10][10];
        String[][] suite = new String[5][10];

        //initialize all rooms as Available
        initializeRooms(standard);
        initializeRooms(deluxe);
        initializeRooms(suite);

        System.out.println("Welcome to the Grand Hotel System");
        while (true) {
            System.out.println("Please select an option: ");
            System.out.println("1. Check Room Availability");
            System.out.println("2. Make New Reservation");
            System.out.println("3. Check-In Guest (Walk-in)");
            System.out.println("4. Check-Out Guest / Generate Bill");
            System.out.print("Enter your choice (1-4): ");
            int choice = Integer.parseInt(kbd.nextLine());
            switch (choice) {
                case 1 -> {
                    checkRoomAvailability(kbd, standard, deluxe, suite);
                }
                case 2 -> {
                    newReservation();
                }
                case 3 -> {
                    walkIn();
                }
                case 4 -> {
                    checkOut();
                }
                default ->{
                    System.out.println("Invalid choice! Please try again.");
                }
            }
        }
    }
    // all methods are in void type. replace if necessary. ~rav

    //initialize all rooms as available
    static void initializeRooms(String[][] rooms) {
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[i].length; j++) {
                rooms[i][j] = "Available";
            }
        }
    }

    //check room availability
    static void checkRoomAvailability(Scanner kbd, String[][] standard, String[][] deluxe, String[][] suite) {
        System.out.print("Input Guest Name: ");
        String name = kbd.nextLine();
        System.out.print("Input Room Type: (1. Standard, 2. Deluxe, 3. Suite): ");
        int type = Integer.parseInt(kbd.nextLine());
        System.out.println("Room Availability Status");

        int available;
        switch (type) {
            case 1:
                available = countAvailable(standard); //count available rooms
                System.out.println("Room Type: Standard");
                System.out.println("Total Rooms: 15");
                System.out.println("Available Rooms: " + available);
                System.out.println("Price per Night: ₱2,500");
                //print table
                break;
            case 2:
                available = countAvailable(deluxe); //count available rooms
                System.out.println("Room Type: Deluxe");
                System.out.println("Total Rooms: 10");
                System.out.println("Available Rooms: " + available);
                System.out.println("Price per Night: ₱4,000");
                //print table
                break;
            case 3:
                available = countAvailable(suite); //count available rooms
                System.out.println("Room Type: Suite");
                System.out.println("Total Rooms: 5");
                System.out.println("Available Rooms: " + available);
                System.out.println("Price per Night: ₱7,500");
                //print table
                break;
            default:
                System.out.println("Invalid choice! Please try again.");
        }
    }

    //count available rooms
    static int countAvailable(String[][] rooms) {
        int count = 0;
        for (int i = 0; i < rooms.length; i++) { //rooms
            boolean roomAvailable = false;
            for (int j = 0; j < rooms[i].length; j++) { //days
                if (rooms[i][j].equals("Available")) {
                    roomAvailable = true;
                    break;
                }
            }
            if (roomAvailable) count++;
        }
        return count;
    }
    static void newReservation() {
        // code here
    }
    static void walkIn() {
        // code here
    }
    static void checkOut() {
        // code here
    }
    // payment system for bill checkout
    static void payment(double amount) {
        Scanner kbd = new Scanner(System.in);
        do {
            System.out.println("Input Final Payment Amount: ");
            double tendered = Double.parseDouble(kbd.nextLine());
            if (amount < tendered) {
                System.out.println("Payment failed! Amount tendered less than required amount to be paid.");
            }
        } while (amount < tendered);
        System.out.println("Payment: ₱"+tendered+" received.");
        double change = tendered - amount;
        System.out.println("Change Calculation: ₱"+tendered+" - ₱"+amount+" = ₱"+change);
    }
    // payment system for walk-in
    static void payment(double amount, int day, double unitPrice) {
        Scanner kbd = new Scanner(System.in);
        do {
            System.out.println("Input Payment (Room Only, ₱"+unitPrice+" * "+day+"): ");
            double tendered = Double.parseDouble(kbd.nextLine());
            double change = tendered - amount
            if (amount < tendered) {
                System.out.println("Payment failed! Amount tendered less than required amount to pay.");
            } else {
                System.out.println("Payment Successful!");
                System.out.println("Change Calculation: ₱"+tendered+" - ₱"+amount+" = ₱"+change);
            }
        } while (amount < tendered);
    }
    // change calculation for general use
    static double payment(double amount, double tendered) {
        double change = tendered - amount;
        return change;
    }
}