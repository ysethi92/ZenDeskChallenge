package src.main.java;

import org.json.JSONObject;

import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class TicketSystem {

    final static String ALL_TICKETS = "https://zccysethi92.zendesk.com/api/v2/tickets.json";
    final static String COUNT_TICKETS = "https://zccysethi92.zendesk.com/api/v2/tickets/count.json";
    final static String SINGLE_TICKET = "https://zccysethi92.zendesk.com/api/v2/tickets/";
    final static String CREATE_TICKETS = "https://zccysethi92.zendesk.com/api/v2/imports/tickets/create_many.json";

    public static void main(String[] args) {
        System.out.println("Welcome to the Ticket Viewer");
        boolean exit = false;
        while(!exit) {
            System.out.println("\nEnter -help to view list of commands. Happy querying");
            System.out.println("To exit the Ticket Viewer enter exit");
            Scanner sc = new Scanner(System.in);
            String help = sc.nextLine();
            if (Objects.equals(help, "-help")) {
                int option = 0;
                System.out.println("\nPlease enter 1 to view all the tickets");
                System.out.println("Please enter 2 to view a ticket");
                System.out.println("Please enter 3 to go back to main screen");
                System.out.println("Please enter 4 to exit.\n");
                ConnectToZenDesk connectToZenDesk = new ConnectToZenDesk();
                TicketsDisplay ticketsDisplayer = new TicketsDisplay();
                do {
                    try {
                        System.out.println("Please select an option : ");
                        option = sc.nextInt();
                        String data;
                        switch (option) {
                            case 1:
                                data = connectToZenDesk.getDataFromAPI(ALL_TICKETS, "GET");
                                ticketsDisplayer.displayAllTickets(data);
                                break;

                            case 2:
                                String ticketCount = connectToZenDesk.getDataFromAPI(COUNT_TICKETS, "GET");
                                JSONObject jsonObject = new JSONObject(ticketCount);
                                int totalTickets = jsonObject.getJSONObject("count").getInt("value");
                                System.out.println("\nTicket ID's are available from 1 to " + totalTickets + "\n");
                                while (true) {
                                    int ticketID = inputTicketID(totalTickets);

                                    data = connectToZenDesk.getDataFromAPI(SINGLE_TICKET + ticketID + ".json", "GET");
                                    ticketsDisplayer.displaySingleTicket(data, ticketID);
                                    System.out.println("Want to see more tickets?(Y/N)");
                                    char c = sc.next().charAt(0);
                                    if (c != 'Y') {
                                        break;
                                    }
                                }
                                break;

                            case 3:
                                break;

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
            } else if (Objects.equals(help, "exit")) {
                break;
            } else {
                System.out.println("Wrong input, you may want to type -help");
            }
        }
        System.out.println("\n Thankyou for using ZenDesk!!");
    }

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