package src.main.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class ConnectToZenDesk {

    HttpURLConnection http = null;

    protected void addTestTickets() {
        try {
            URL url = new URL("https://zccysethi92.zendesk.com/api/v2/imports/tickets/create_many.json");
            http = (HttpURLConnection)url.openConnection();
            http.setRequestMethod("POST");
            http.setDoOutput(true);
            http.setRequestProperty("Content-Type", "application/json");

            Authenticator.setDefault(new BasicAuthenticator());

            ReadJsonAsString readJsonAsString = new ReadJsonAsString();
            String data = readJsonAsString.readFileAsString("src/main/java/tickets.json");
            byte[] out = data.getBytes(StandardCharsets.UTF_8);

            OutputStream stream = http.getOutputStream();
            stream.write(out);
//            String user = "ysethi92@gmail.com";
//            String password = "Uzimo@1234";
//            String auth = user + ":" + password;
//            String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
//
//            String authHeaderValue = "Basic " + encodedAuth;
//            http.setRequestProperty("Authorization", authHeaderValue);

            BufferedReader br = null;
            if (http.getResponseCode() == 200) {
//                JSONParser jsonParser = new JSONParser();
//                JSONObject jsonObject = (JSONObject)jsonParser.parse(
//                        new InputStreamReader(http.getInputStream(), StandardCharsets.UTF_8));
                br = new BufferedReader(new InputStreamReader(http.getInputStream()));
                String strCurrentLine;
                while ((strCurrentLine = br.readLine()) != null) {
                    System.out.println(strCurrentLine);
                }
            } else {
                System.out.println("Bad Response");
                br = new BufferedReader(new InputStreamReader(http.getErrorStream()));
                String strCurrentLine;
                while ((strCurrentLine = br.readLine()) != null) {
                    System.out.println(strCurrentLine);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected String getDataFromAPI(String link, String requestType) {
        try {
            URL url = new URL(link);
            http = (HttpURLConnection)url.openConnection();
            http.setRequestMethod(requestType);
            http.setDoOutput(true);
            http.setRequestProperty("Content-Type", "application/json");

            Authenticator.setDefault(new BasicAuthenticator());

            BufferedReader br = null;
            if (http.getResponseCode() == 200) {

                return new BufferedReader(
                        new InputStreamReader(http.getInputStream(), StandardCharsets.UTF_8))
                        .lines()
                        .collect(Collectors.joining("\n"));
            } else {
                System.out.println("Bad Response, try again after sometime");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }





    protected String listTickets() {
        try {
            URL url = new URL("https://zccysethi92.zendesk.com/api/v2/tickets.json");
            http = (HttpURLConnection)url.openConnection();
            http.setRequestMethod("GET");
            http.setDoOutput(true);
            http.setRequestProperty("Content-Type", "application/json");

            Authenticator.setDefault(new BasicAuthenticator());

//            String user = "ysethi92@gmail.com";
//            String password = "Uzimo@1234";
//            String auth = user + ":" + password;
//            String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
//
//            String authHeaderValue = "Basic " + encodedAuth;
//            http.setRequestProperty("Authorization", authHeaderValue);

            BufferedReader br = null;
            if (http.getResponseCode() == 200) {
                String data = new BufferedReader(
                        new InputStreamReader(http.getInputStream(), StandardCharsets.UTF_8))
                        .lines()
                        .collect(Collectors.joining("\n"));

                TicketsDisplay td = new TicketsDisplay();
                td.displayAllTickets(data);

//                br = new BufferedReader(new InputStreamReader(http.getInputStream()));
//                String strCurrentLine;
//                while ((strCurrentLine = br.readLine()) != null) {
//                    System.out.println(strCurrentLine);
//                }
                return data;
            } else {
                System.out.println("Bad Response");
                br = new BufferedReader(new InputStreamReader(http.getErrorStream()));
                String strCurrentLine;
                while ((strCurrentLine = br.readLine()) != null) {
                    System.out.println(strCurrentLine);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected String countTickets() {
        try {
            URL url = new URL("https://zccysethi92.zendesk.com/api/v2/tickets/count.json");
            http = (HttpURLConnection)url.openConnection();
            http.setRequestMethod("GET");
            http.setDoOutput(true);
            http.setRequestProperty("Content-Type", "application/json");

            Authenticator.setDefault(new BasicAuthenticator());

//            String user = "ysethi92@gmail.com";
//            String password = "Uzimo@1234";
//            String auth = user + ":" + password;
//            String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
//
//            String authHeaderValue = "Basic " + encodedAuth;
//            http.setRequestProperty("Authorization", authHeaderValue);

            BufferedReader br = null;
            if (http.getResponseCode() == 200) {

//                TicketsDisplayer td = new TicketsDisplayer();
//                td.displayallTickets(count);

//                br = new BufferedReader(new InputStreamReader(http.getInputStream()));
//                String strCurrentLine;
//                while ((strCurrentLine = br.readLine()) != null) {
//                    System.out.println(strCurrentLine);
//                }
                return new BufferedReader(
                        new InputStreamReader(http.getInputStream(), StandardCharsets.UTF_8))
                        .lines()
                        .collect(Collectors.joining("\n"));
            } else {
                System.out.println("Bad Response");
                br = new BufferedReader(new InputStreamReader(http.getErrorStream()));
                String strCurrentLine;
                while ((strCurrentLine = br.readLine()) != null) {
                    System.out.println(strCurrentLine);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    protected void getSingleTicket(int id) {
//        String count = countTickets();
        String count = "2";
        String data = "{\"ticket\":{\"subject\":\"velit eiusmod reprehenderit officia cupidatat\",\"email_cc_ids\":[],\"created_at\":\"2021-11-26T22:54:53Z\",\"description\":\"Aute ex sunt culpa ex ea esse sint cupidatat aliqua ex consequat sit reprehenderit. Velit labore proident quis culpa ad duis adipisicing laboris voluptate velit incididunt minim consequat nulla. Laboris adipisicing reprehenderit minim tempor officia ullamco occaecat ut laborum.\\n\\nAliquip velit adipisicing exercitation irure aliqua qui. Commodo eu laborum cillum nostrud eu. Mollit duis qui non ea deserunt est est et officia ut excepteur Lorem pariatur deserunt.\",\"external_id\":null,\"type\":null,\"via\":{\"channel\":\"api\",\"source\":{\"rel\":null,\"from\":{},\"to\":{}}},\"allow_attachments\":true,\"updated_at\":\"2021-11-26T22:54:53Z\",\"problem_id\":null,\"follower_ids\":[],\"due_at\":null,\"id\":2,\"assignee_id\":1902315701684,\"raw_subject\":\"velit eiusmod reprehenderit officia cupidatat\",\"forum_topic_id\":null,\"custom_fields\":[],\"allow_channelback\":false,\"satisfaction_rating\":null,\"submitter_id\":1902315701684,\"priority\":null,\"collaborator_ids\":[],\"url\":\"https://zccysethi92.zendesk.com/api/v2/tickets/2.json\",\"tags\":[\"est\",\"incididunt\",\"nisi\"],\"brand_id\":1260803279430,\"ticket_form_id\":1260815093470,\"sharing_agreement_ids\":[],\"group_id\":4411137032859,\"organization_id\":1260919336310,\"followup_ids\":[],\"recipient\":null,\"is_public\":true,\"has_incidents\":false,\"fields\":[],\"status\":\"open\",\"requester_id\":1902315701684}}\n";
        TicketsDisplay td = new TicketsDisplay();
//        td.displaySingleTicket(count, data, id);

//        try {
//            URL url = new URL("https://zccysethi92.zendesk.com/api/v2/tickets/"+id+".json");
//            http = (HttpURLConnection)url.openConnection();
//            http.setRequestMethod("GET");
//            http.setDoOutput(true);
//            http.setRequestProperty("Content-Type", "application/json");
//
//            Authenticator.setDefault(new BasicAuthenticator());
//
////            String user = "ysethi92@gmail.com";
////            String password = "Uzimo@1234";
////            String auth = user + ":" + password;
////            String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
////
////            String authHeaderValue = "Basic " + encodedAuth;
////            http.setRequestProperty("Authorization", authHeaderValue);
//
//            BufferedReader br = null;
//            if (http.getResponseCode() == 200) {
//                String data = new BufferedReader(
//                        new InputStreamReader(http.getInputStream(), StandardCharsets.UTF_8))
//                        .lines()
//                        .collect(Collectors.joining("\n"));
//
//                if(!Objects.equals(count, "")) {
//                    TicketsDisplayer td = new TicketsDisplayer();
//                    td.displaySingleTicket(count, data, id);
//                }
////                br = new BufferedReader(new InputStreamReader(http.getInputStream()));
////                String strCurrentLine;
////                while ((strCurrentLine = br.readLine()) != null) {
////                    System.out.println(strCurrentLine);
////                }
//            } else {
//                System.out.println("Bad Response");
//                br = new BufferedReader(new InputStreamReader(http.getErrorStream()));
//                String strCurrentLine;
//                while ((strCurrentLine = br.readLine()) != null) {
//                    System.out.println(strCurrentLine);
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    protected void closeConnection() {
        http.disconnect();
    }

    private static final class BasicAuthenticator extends Authenticator {
        protected PasswordAuthentication getPasswordAuthentication() {
            String user = "ysethi92@gmail.com";
            String password = "Uzimo@1234";
            return new PasswordAuthentication(user, password.toCharArray());
        }
    }
}
