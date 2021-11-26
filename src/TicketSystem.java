package src;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

public class TicketSystem {

    public static void main(String[] args) {
        fetchTickets();
    }

    private static void fetchTickets() {
        ConnectToZenDesk connectToZenDesk = new ConnectToZenDesk();
        String json = connectToZenDesk.listTickets();
        System.out.println("hello");
//        JSONObject jsonObject = new JSONObject(json);
        System.out.println(json);
//        JSONObject countObject = (JSONObject) jsonObject.;
//        System.out.println(countObject.get("value"));
//        try {

//            URL url = new URL("https://zccysethi92.zendesk.com/api/v2/imports/tickets/create_many.json");
//            URL url = new URL("https://zccysethi92.zendesk.com/api/v2/tickets/count.json");
//            URL url = new URL("https://zccysethi92.zendesk.com/api/v2/tickets.json");
//            HttpURLConnection http = (HttpURLConnection)url.openConnection();
//            http.setRequestMethod("GET");
//            http.setDoOutput(true);
//            http.setRequestProperty("Content-Type", "application/json");
//            Authenticator.setDefault(new BasicAuthenticator());


//            String user = "ysethi92@gmail.com";
//            String password = "Uzimo@1234";
//            String auth = user + ":" + password;
//            String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
//
//            String authHeaderValue = "Basic " + encodedAuth;
//            http.setRequestProperty("Authorization", authHeaderValue);

//            ReadJsonAsString readJsonAsString = new ReadJsonAsString();
//            String data = readJsonAsString.readFileAsString("src/tickets.json");
//            byte[] out = data.getBytes(StandardCharsets.UTF_8);
//
//            OutputStream stream = http.getOutputStream();
//            stream.write(out);
//            String s = (String) http.getContent();
//            System.out.println(s);
//            System.out.println(http.getResponseCode() + "\n" + http.getResponseMessage());
//            BufferedReader br = null;
//            if (http.getResponseCode() == 200) {
//                br = new BufferedReader(new InputStreamReader(http.getInputStream()));
//                String strCurrentLine;
//                while ((strCurrentLine = br.readLine()) != null) {
//                    System.out.println(strCurrentLine);
//                }
//            } else {
//                System.out.println("Bad Response");
//                br = new BufferedReader(new InputStreamReader(http.getErrorStream()));
//                String strCurrentLine;
//                while ((strCurrentLine = br.readLine()) != null) {
//                    System.out.println(strCurrentLine);
//                }
//            }
//
//
//            http.disconnect();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


//        String command = "curl https://{zccysethi92}.zendesk.com/api/v2/imports/tickets/create_many.json -v -u\n" +
//                "{ysethi92@gmail.com}:{Uzimo@1234} -X POST -d @tickets.json -H \"Content-Type:\n" +
//                "application/json";
//
//        try {
//            Process process = Runtime.getRuntime().exec(command);
//
//            process.getInputStream();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


    }

    private static final class BasicAuthenticator extends Authenticator {
        protected PasswordAuthentication getPasswordAuthentication() {
            String user = "ysethi92@gmail.com";
            String password = "Uzimo@1234";
            return new PasswordAuthentication(user, password.toCharArray());
        }
    }
}
