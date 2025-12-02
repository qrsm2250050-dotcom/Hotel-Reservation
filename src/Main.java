import java.util.Scanner;

public class Main {
    public static Scanner kbd = new Scanner(System.in);

    //room type and catalog array(?)
    int[][] roomCatalog = {{1, 15}, {2, 10}, {3, 5}}; //1-standard 2-deluxe 3-suite

    //guest reservations array
    public static String[][] standard = new String[15][10]; //rooms and days
    public static String[][] deluxe = new String[10][10];
    public static String[][] suite = new String[5][10];

    public static void main(String[] args) {


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
                    checkRoomAvailability();
                }
                case 2 -> {
                    newReservation();
                }
                case 3 -> {
                    walkIn3();
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
        for (int i = 0; i < rooms.length; i++) { //i = room index, loop stops at the last number of room
            for (int j = 0; j < rooms[i].length; j++) { //j = day index, looping for each day of the rooms
                rooms[i][j] = "Available"; //setting current room at current day as available
            }
        }
    }

    //check room availability
    static void checkRoomAvailability() {

        int type = 0;
        while (type < 1 || type > 3) {
            System.out.print("Input Room Type: (1. Standard, 2. Deluxe, 3. Suite): ");
            type = Integer.parseInt(kbd.nextLine());
            if (type < 1 || type > 3) {
                System.out.println("Invalid. Please enter 1, 2, or 3 only.");
            }
        }
        System.out.println();
        System.out.println("Room Availability Status");
        char room; //declaration of variables
        int available;
        switch (type) {
            case 1:
                available = countAvailable(standard); //count available rooms
                System.out.println("Room Type: Standard");
                System.out.println("Total Rooms: 15");
                System.out.println("Available Rooms: " + available);
                System.out.println("Price per Night: ₱2,500");
                room = 'S'; //for printing room numbers in table
                printTable(standard, room); //print table to check room availability
                break;
            case 2:
                available = countAvailable(deluxe); //count available rooms
                System.out.println("Room Type: Deluxe");
                System.out.println("Total Rooms: 10");
                System.out.println("Available Rooms: " + available);
                System.out.println("Price per Night: ₱4,000");
                room = 'D';//for printing room numbers in table
                printTable(deluxe, room);//print table to check room availability
                break;
            case 3:
                available = countAvailable(suite); //count available rooms
                System.out.println("Room Type: Suite");
                System.out.println("Total Rooms: 5");
                System.out.println("Available Rooms: " + available);
                System.out.println("Price per Night: ₱7,500");
                room = 'T';//for printing room numbers in table
                printTable(suite, room);//print table to check room availability
                break;
            default:
                System.out.println("Invalid choice! Please try again.");
        }
    }

    //print table of rooms (rows) and days (columns)
    static void printTable(String[][] rooms, char roomNum) {

        // print header (Day 1 to Day 10)
        System.out.printf("%-10s", "    "); // print a string, left-aligned, padded to 10 spaces
        for (int day = 1; day <= rooms[0].length; day++) { //loop for printing day labels, starts with day 1 ends with day 10
            System.out.printf("Day%-12d", day); //print an integer, left-aligned, padded to 12 spaces
        }
        System.out.println();

        // print each room's status
        for (int i = 0; i < rooms.length; i++) { //i = number of rows, stops as number of rooms
            System.out.printf("%-10s", roomNum + "" + (i + 101)); //print room number
            for (int j = 0; j < rooms[i].length; j++) { //loop for each day for current room, j = day, stops at length of days
                System.out.printf("%-15s", rooms[i][j]); // print availability status
            }
            System.out.println();
        }
    }

    //count available rooms
    static int countAvailable(String[][] rooms) {
        int count = 0; //number of available rooms
        for (int i = 0; i < rooms.length; i++) { //loop through each room, checks each room
            boolean roomAvailable = false; //assume room is unavailable until checked
            for (int j = 0; j < rooms[i].length; j++) { //loop through each day, checks each day in current room
                if (rooms[i][j].equals("Available")) { //checks if room is available in this particular day
                    roomAvailable = true; //mark room as available
                    break; //stop checking days for this room
                }
            }
            if (roomAvailable) count++; //if at least one day was available, count room as available
        }
        return count;
    }

    static void newReservation() {
        System.out.print("Input Guest Name: ");
        String guest = kbd.nextLine();

        int type = 0;

        while (type < 1 || type > 3) {
            System.out.print("Input Room Type: (1. Standard, 2. Deluxe, 3. Suite): ");
            type = Integer.parseInt(kbd.nextLine());

            if (type < 1 || type > 3) {
                System.out.println("Invalid. Please enter 1, 2, or 3 only.");
            }
        }

        String[][] selectedArray;
        String roomPrefix = "";
        int price = 0;

        switch (type) {
            case 1 -> {
                selectedArray = standard;
                roomPrefix = "S";
                price = 2500;
            }
            case 2 -> {
                selectedArray = deluxe;
                roomPrefix = "D";
                price = 4000;
            }
            case 3 -> {
                selectedArray = suite;
                roomPrefix = "T";
                price = 8000;
            }
            default -> {
                System.out.println("Invalid Room Type.");
                return;
            }
        }

        System.out.println();
        printTable(selectedArray, roomPrefix.charAt(0));

        int nights = 0;

        while (nights < 1 || nights > 10) {
            System.out.print("\nInput number of days (1–10): ");
            nights = Integer.parseInt(kbd.nextLine());

            if (nights < 1 || nights > 10) {
                System.out.println("Invalid. Please enter a number between 1 and 10.");
            }
        }

        System.out.println("\nProcessing Reservation...");

        int assignedRoomIndex = -1;
        int startDay = -1;


        for (int i = 0; i < selectedArray.length; i++) {

            for (int start = 0; start <= 10 - nights; start++) {
                boolean free = true;


                for (int d = 0; d < nights; d++) {
                    if (!selectedArray[i][start + d].equals("Available")) {
                        free = false;
                        break;
                    }
                }

                if (free) {
                    assignedRoomIndex = i;
                    startDay = start + 1;

                    // Print the dates
                    for (int dayNum = 1; dayNum <= nights; dayNum++) {
                        System.out.println("Date " + dayNum + ": Day " + (startDay + dayNum - 1));
                    }

                    // Mark as booked
                    for (int d = 0; d < nights; d++) {
                        selectedArray[i][start + d] = "Booked";
                    }
                    break;
                }
            }
            if (assignedRoomIndex != -1) break;
        }

        if (assignedRoomIndex == -1) {
            System.out.println("No available rooms for this duration.");
            return;
        }

        int roomNumber = 101 + assignedRoomIndex;
        String fullRoomName = roomPrefix + roomNumber;
        int fee = price * nights;

        System.out.println("Found: " + fullRoomName + ".");
        System.out.println("Reservation Fee (Room Rate Only): ₱" + price + " / night * " + nights + " nights = ₱" + fee);

        System.out.println("\n--- Reservation Summary ---");
        System.out.println("Guest Name: " + guest);

        String roomTypeName = "";
        if (type == 1) {
            roomTypeName = "Standard";
        } else if (type == 2) {
            roomTypeName = "Deluxe";
        } else {
            roomTypeName = "Suite";
        }
        System.out.println("Room Type: " + roomTypeName);

        System.out.println("Room Number Assigned: " + fullRoomName);
        System.out.println("Nights Booked: " + nights);
        System.out.println("Total Reservation Fee Due Now: ₱" + fee);
        System.out.println("Update Status: Room " + fullRoomName + " is now set to 'Booked' by " + guest + ".");
        System.out.println();
    }

    static String[] walkIn(String[][] standard, String[][] deluxe, String[][] suite) {
        Scanner kbd = new Scanner(System.in);
        int RoomType = 0;
        do {
            System.out.print("Input Room Type (1. Standard, 2. Deluxe, 3. Suite): ");
            RoomType = Integer.parseInt(kbd.nextLine());
            if (RoomType < 1 || RoomType > 3) {System.out.println("Invalid. Please enter 1, 2, or 3 only.");}
        }while(RoomType > 3 || RoomType < 1);
        double unitPrice = 0;
        String WordRoomType = "";
        String[] data = new String[5];
        String ChosenRoom = "";
        String typenumd = "";
        String[] roominfo;
        switch (RoomType) { //set roomprice based on type already
            case 1:
                WordRoomType = "Standard";
                ChosenRoom = walkIn2(standard, unitPrice, WordRoomType);
                System.out.println("chooseroomdone");
                data[0] = "Standard";
                System.out.println("assigning to data broke works");
                roominfo = ChosenRoom.split("#");
                data[1] = roominfo[0];
                data[2] = roominfo[1];
                return data;
            case 2:
                WordRoomType = "Deluxe";
                ChosenRoom = walkIn2(deluxe, unitPrice, WordRoomType);
                data[0] = "Deluxe";
                roominfo = ChosenRoom.split("#");
                data[1] = roominfo[0];
                data[2] = roominfo[1];
                return data;
            case 3:
                WordRoomType = "Suite";
                ChosenRoom = walkIn2(suite, unitPrice, WordRoomType);
                roominfo = ChosenRoom.split("#");
                data[0] = "Tuite";
                data[1] = roominfo[0];
                data[2] = roominfo[1];
                return data;
            default:
                System.out.println("Something went wrong"); //TEMP, ALSO REMEMBER TO ADD THE ERROR LOOP
                break;
        }
        return data;
    }

    static String walkIn2(String[][] checker, double unitPrice, String WordRoomType) {
        Scanner kbd = new Scanner(System.in);
        System.out.print("Input Nights Booked: ");
        int days = 0;
        do {
            days = Integer.parseInt(kbd.nextLine());
            if (days < 1 || days > 10) {System.out.println("Please Input from 1-10 days");}
        }while (days <= 0 || days > 10);
        boolean terminate = false;
        System.out.println("Processing Walk-in Check-In... Checking for available " + WordRoomType + " rooms");
        String ChosenRoom = "";
        for (int i = 0; i < checker.length; i++) { //loop through each room, checks each room
            int streak = 0;
            for (int j = 0; j < checker[i].length; j++) { //loop through each day, checks each day in current room
                System.out.println("Begin if check");
                if (checker[i][j].equals("Available")) { //checks if room is available in this particular day
                    streak++;
                    System.out.println("Streak added");
                    if (streak >= days) {
                        System.out.println("Enough Days found");
                        //0st one is room number 1nd one is for how many days
                        ChosenRoom = String.valueOf(i) + "#" + String.valueOf(days);
                        return ChosenRoom;
                    }
                }
                if (checker[i][0].equals("Occupied")) {
                    break;
                }
                if (checker[i][j].equals("Booked")) { //checks if room is available in this particular day
                    break;//stops checking for available days for this room because someone else is occupying
                    //starts checking next room with the break
                }
            }
        }
        return "0#0#0";
    }
    static void walkIn3() {
        String[] data = new String[5];
        System.out.print("Input Guest Name:");
        String name = kbd.nextLine();
        data = walkIn(standard, deluxe, suite);
        int days = Integer.parseInt(data[2]);
        int type = 0;
        int unitPrice = 0;
        int slot = 0;
        //data 0 is type 1 is number 2 is duration
        switch (data[0].charAt(0)){
            case 'S'://standard
                System.out.println("Standard room");
                unitPrice = 2500;
                do {
                    standard[Integer.parseInt(data[1])][slot] = "Occupied";
                    slot++;
                }while(slot < days);
                data [2] = String.valueOf(Integer.parseInt(data[2]) + 1);
                break;
            case 'D'://deluxe
                unitPrice = 4000;
                do {
                    deluxe[Integer.parseInt(data[1])][slot] = "Occupied";
                    slot++;
                }while(slot < days);
                data [2] = String.valueOf(Integer.parseInt(data[2]) + 1);
                break;
            case 'T'://suite
                unitPrice = 8000;
                do {
                    suite[Integer.parseInt(data[1])][slot] = "Occupied";
                    slot++;
                }while(slot < days);
                data [2] = String.valueOf(Integer.parseInt(data[2]) + 1);
                break;
            default:
                System.out.println("Sorry, there are no available rooms for your chosen length of stay and room type");
                break;
        }
        double amount = days * unitPrice;
        double tendered;
        do{
            System.out.println("Input Payment, Room Only" + unitPrice + " * " + days + " = " + amount);
            tendered = Double.parseDouble(kbd.nextLine());
            if (tendered != amount){
                System.out.println("Please Pay Exact");
            }
        }while(tendered != amount);
        System.out.println("Payment Successful");
        int roomnum = 101 + Integer.parseInt(data[1]);
        System.out.println("Update Status Room " + data[0].charAt(0) + roomnum + " is now set to Occupied by " + name + " for " + days + "day(s)");

    }
        static void checkOut() {
        // Print Bill
        // Check Room Number
        // Check If Paid
        // Print Bill
        Scanner kbd = new Scanner(System.in);
        String roomPrint;
        int roomDays, validation;
        //room name for type and number, then days for calculations, and validations
        //guidelines say I only need to input room number, I need to grab days from either check-in/reserve or availability
        System.out.println("Please type your room name and days checked in.");
        System.out.print("Room Name : ");
        do {
            roomPrint = kbd.nextLine();
            validation = roomPrint.length();
            if (validation > 4) {
                System.out.println("Invalid room number, please try again.");
            }
        } while (validation > 4);
        System.out.print("Days Checked In : ");
        do {
            roomDays = Integer.parseInt(kbd.nextLine());
            if (roomDays > 10) {
                System.out.println("Invalid days checked in, please try again.");
            }
        } while (roomDays > 10);
        //Call method, and split returned array into subtotal, tax, total, and room number
        double[] billPrint = billCalc(roomPrint, roomDays);
        double stotal = billPrint[0];
        double taxCost = billPrint[1];
        double amount = billPrint[2];
        int rmNum = (int) billPrint[3];
        //Call payment method, convert returned values into tendered and change
        double[] paymentCalc = payment(amount);
        double tendered = paymentCalc[1];
        double change = paymentCalc[0];
        System.out.println("Your bill is \n" +
                "Subtotal : " + stotal + "\n" +
                "Service Fee : ₱250 \n" +
                "Taxes : " + taxCost + "\n" +
                "Total Amount Due : " + billPrint + "\n" +
                "Amount Paid : " + tendered + "\n" +
                "Change Due : " + change);
        System.out.println("Check-Out Complete. Room Number " + rmNum + " is now available.");
    }

    static double[] billCalc(String rPrt, int rDays) {
        // Billing Calculations
        // Check Room Type and Days
        // Add Serv
        int roomNum = Integer.parseInt(rPrt);
        char roomType = rPrt.toUpperCase().charAt(0);
        double subTot = 0.00;
        // Check room type and days, calc subTotal
        switch (roomType) {
            case 'S':
                subTot = 2500.00 * rDays;
                break;
            case 'D':
                subTot = 4000.00 * rDays;
                break;
            case 'T':
                subTot = 8000.00 * rDays;
                break;
            default:
        }
        //calc tax, then combine subtot with tax for final amount
        double taxBill = (subTot + 250.00) * 0.1;
        double fullBill = (subTot + 250.00) + taxBill;
        //compile double variables into array and return array
        double[] listBill = new double[4];
        listBill[0] = subTot;
        listBill[1] = taxBill;
        listBill[2] = fullBill;
        listBill[3] = roomNum;
        return listBill;
    }

    // payment system for bill checkout
    static double[] payment(double amount) {
        //Turned into array so I can get both payment and change
        Scanner kbd = new Scanner(System.in);
        double tendered;
        do {
            System.out.println("Input Final Payment Amount: ");
            tendered = Double.parseDouble(kbd.nextLine());
            if (amount < tendered) {
                System.out.println("Payment failed! Amount tendered less than required amount to be paid.");
            }
        } while (amount < tendered);
        System.out.println("Payment: ₱" + tendered + " received.");
        double change = tendered - amount;
        System.out.println("Change Calculation: ₱" + tendered + " - ₱" + amount + " = ₱" + change);
        double[] paid = new double[2];
        paid[0] = change;
        paid[1] = tendered;
        return paid;
    }

    // payment system for walk-in
    static void payment(double amount, int day, double unitPrice) {
        Scanner kbd = new Scanner(System.in);
        double tendered;
        do {
            System.out.println("Input Payment (Room Only, ₱" + unitPrice + " * " + day + "): ");
            tendered = Double.parseDouble(kbd.nextLine());
            double change = tendered - amount;
            if (amount < tendered) {
                System.out.println("Payment failed! Amount tendered less than required amount to pay.");
            } else {
                System.out.println("Payment Successful!");
                System.out.println("Change Calculation: ₱" + tendered + " - ₱" + amount + " = ₱" + change);
            }
        } while (amount < tendered);
    }

    // change calculation for general use
    static double payment(double amount, double tendered) {
        double change = tendered - amount;
        return change;
    }
}