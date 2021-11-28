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
import java.util.Objects;
import java.util.stream.Collectors;

public class ConnectToZenDesk {

    HttpURLConnection http = null;

    // used to add test tickets to the account.
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

    public String getDataFromAPI(String link, String requestType) {
        try {
            URL url = new URL(link);
            http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod(requestType);
            http.setDoOutput(true);
            http.setRequestProperty("Content-Type", "application/json");

            Authenticator.setDefault(new BasicAuthenticator());

            // For adding test tickets to Zendesk dashboard.
            if (Objects.equals(requestType, "POST")) {
                ReadJsonAsString readJsonAsString = new ReadJsonAsString();
                String data = readJsonAsString.readFileAsString("src/main/java/tickets.json");
                byte[] out = data.getBytes(StandardCharsets.UTF_8);

                OutputStream stream = http.getOutputStream();
                stream.write(out);
            }
            BufferedReader br = null;
            if (http.getResponseCode() == 200) {

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
            System.out.println("Something is wrong with the input/output stream.");
        } catch (Exception e) {
            System.out.println("Something went wrong while reading the data");
        }
        return "";
    }

    protected void closeConnection() {
        if (http != null)
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
