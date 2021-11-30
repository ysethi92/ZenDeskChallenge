package src.main.java;

import org.json.JSONObject;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TicketSystem {

    final static String ALL_TICKETS = "https://zccysethi92.zendesk.com/api/v2/tickets.json";
    final static String COUNT_TICKETS = "https://zccysethi92.zendesk.com/api/v2/tickets/count.json";
    final static String SINGLE_TICKET = "https://zccysethi92.zendesk.com/api/v2/tickets/";
    final static String CREATE_TICKETS = "https://zccysethi92.zendesk.com/api/v2/imports/tickets/create_many.json";

    // contains the main flow of the Ticket Viewer
    public static void main(String[] args) {
        System.out.println("-".repeat(30));
        System.out.println("Welcome to the Ticket Viewer");
        System.out.println("-".repeat(30));
        boolean exit = false;
        ConnectToZenDesk connectToZenDesk = new ConnectToZenDesk();
        TicketsDisplay ticketsDisplay = new TicketsDisplay();
        while (!exit) {
            Scanner sc = new Scanner(System.in);
            int option = 0;

            System.out.println("\nPlease enter 1 to view all the tickets");
            System.out.println("Please enter 2 to view a ticket");
            System.out.println("Please enter 3 to exit.\n");

            do {
                try {
                    System.out.println("Please select an option : ");
                    option = sc.nextInt();
                    String data;
                    switch (option) {
                        // displays all tickets
                        case 1:
                            data = connectToZenDesk.getDataFromAPI(ALL_TICKETS, "GET");
                            ticketsDisplay.displayAllTickets(data);
                            break;

                        // displays the ticket, which user asks for.
                        case 2:
                            String ticketCount = connectToZenDesk.getDataFromAPI(COUNT_TICKETS, "GET");
                            JSONObject jsonObject = new JSONObject(ticketCount);
                            int totalTickets = jsonObject.getJSONObject("count").getInt("value");
                            System.out.println("\nTicket ID's are available from 1 to " + totalTickets + "\n");
                            while (true) {
                                int ticketID = inputTicketID(totalTickets);

                                data = connectToZenDesk.getDataFromAPI(SINGLE_TICKET + ticketID + ".json", "GET");
                                ticketsDisplay.displaySingleTicket(data, ticketID);
                                System.out.println("Want to see more tickets?(Enter Y(Yes) or anything else to exit)");
                                char c = sc.next().charAt(0);
                                if (Character.toUpperCase(c) != 'Y') {
                                    break;
                                }
                            }
                            break;

                        // close connection and exits the ticket viewer.
                        default:
                            connectToZenDesk.closeConnection();
                            exit = true;
                            break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid choice. Please choose again");
                    sc.next();
                }
            } while (option <= 0);
        }
        System.out.println("\n Thankyou for using ZenDesk!!");
    }

    // inputs ticket id till a valid one is entered and then returns it.
    public static int inputTicketID(int totalTickets) {
        Scanner sc = new Scanner(System.in);
        int ticketID;
        do {
            System.out.println("Enter a valid ticket ID : ");
            while (!sc.hasNextInt()) {
                System.out.println("Invalid ticket ID.");
                sc.next();
            }
            ticketID = sc.nextInt();
        } while (ticketID <= 0 || ticketID > totalTickets);
        return ticketID;
    }
}
