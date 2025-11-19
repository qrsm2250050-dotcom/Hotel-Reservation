import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner kbd = new Scanner(System.in);
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
    static double paymentCalculation(double x) {
        double result = 0; // placeholder code
        return result;
    }
}