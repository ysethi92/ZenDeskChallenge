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

            if (http.getResponseCode() == 200) {

                return new BufferedReader(
                        new InputStreamReader(http.getInputStream(), StandardCharsets.UTF_8))
                        .lines()
                        .collect(Collectors.joining("\n"));
            } else {
                System.out.println("Bad Response");
                BufferedReader br = new BufferedReader(new InputStreamReader(http.getErrorStream()));
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
            String password = "Yash@1234";
            return new PasswordAuthentication(user, password.toCharArray());
        }
    }
}
