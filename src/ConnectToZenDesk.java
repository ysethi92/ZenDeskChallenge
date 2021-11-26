package src;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

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
            String data = readJsonAsString.readFileAsString("src/tickets.json");
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
                JSONParser jsonParser = new JSONParser();
                JSONObject jsonObject = (JSONObject)jsonParser.parse(
                        new InputStreamReader(http.getInputStream(), StandardCharsets.UTF_8));
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

                br = new BufferedReader(new InputStreamReader(http.getInputStream()));
                String strCurrentLine;
                while ((strCurrentLine = br.readLine()) != null) {
                    System.out.println(strCurrentLine);
                }
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
