package src.main.java;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class TicketsDisplay {

    protected void displayAllTickets(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray tickets = jsonObject.getJSONArray("tickets");
            int numPages = (int) Math.ceil((float) tickets.length()/25);
            System.out.format("%3s %20s%60s%20s%n", "ID", "Subject", "Type", "Priority");
            for(int i = 0; i<(Math.min(tickets.length(), 25)); i++) {
                JSONObject ticketDetails = tickets.getJSONObject(i);
                System.out.format("%3d       %-70s  %-18s %5s%n",
                        ticketDetails.getInt("id"),
                        ticketDetails.getString("subject"),
                        ((Objects.equals(ticketDetails.get("type").toString(), "null")) ? "-" : ticketDetails.get("type").toString()),
                        ((Objects.equals(ticketDetails.get("priority").toString(), "null")) ? "-" : ticketDetails.get("priority").toString()));
            }
            System.out.println("\nTotal " + tickets.length()+ " tickets are available in " + numPages + " pages");
            if(numPages > 1) {
                System.out.println("\nWant to see more tickets(Y/N)?");
                Scanner sc = new Scanner(System.in);

                char decision = sc.next().charAt(0);
                if(decision == 'Y') {
                    while(true) {
                        try {
                            System.out.println("Please enter Page number :");
                            int pageNumber = sc.nextInt();
                            if (pageNumber <= numPages && pageNumber > 0) {
                                System.out.format("%3s %20s%60s%20s%n", "ID", "Subject", "Type", "Priority");
                                for(int i = (pageNumber-1)*25; i<tickets.length() && i<pageNumber*25; i++) {
                                    JSONObject ticketDetails = tickets.getJSONObject(i);
                                    System.out.format("%3d       %-70s  %-18s %5s%n",
                                            ticketDetails.getInt("id"),
                                            ticketDetails.getString("subject"),
                                            ((Objects.equals(ticketDetails.get("type").toString(), "null")) ? "-" : ticketDetails.get("type").toString()),
                                            ((Objects.equals(ticketDetails.get("priority").toString(), "null")) ? "-" : ticketDetails.get("priority").toString()));
                                }
                                System.out.println("\nWant to continue(Y/N)?");
                                char con = sc.next().charAt(0);
                                if(con != 'Y') {
                                    break;
                                }
                            } else {
                                System.out.println("Please enter a available page number.\n");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Please enter a valid page number.\n");
                            sc.next();
                        }
                    }
                }
            }
        } catch (JSONException e) {
            System.out.println("Something went wrong with the data received from the server.");
        }
    }

    protected void displaySingleTicket(String ticket, int id) {
        JSONObject jsonObject = new JSONObject(ticket);
        JSONObject ticketObject = jsonObject.getJSONObject("ticket");

        System.out.format("\n%s %80s%n",
                ("Subject : " + ticketObject.getString("subject")),
                ("Creation data and time : " + ticketObject.getString("created_at").substring(0, 10) + " "
                        +ticketObject.getString("created_at").substring(11, 16)));

        System.out.println("Via "+ ticketObject.getJSONObject("via").getString("channel").toUpperCase());
        System.out.println("-".repeat(140));
        System.out.println(ticketObject.getString("description"));
        System.out.println("-".repeat(140));
//        System.out.println("\n");
    }
}
