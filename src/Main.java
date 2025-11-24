import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner kbd = new Scanner(System.in);
        String[][] deluxe = new String[10][10]
        String[][] standard = new String[15][10]
        String[][] suite = new String[5][10]
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
                    checkRoomAvailability();
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
    static void checkRoomAvailability() {
        //
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
    static double payment(double amount, String type) {
        Scanner kbd = new Scanner(System.in);
        do {
            System.out.println("Input FInal Payment Amount: ");
            double tendered = Double.parseDouble(kbd.nextLine());
            if (amount < tendered) {
                System.out.println("Invalid Payment received!");
            }
        } while (amount < tendered);
        return result;
    }
}