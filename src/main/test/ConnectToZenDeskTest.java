package src.main.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import src.main.java.ConnectToZenDesk;

class ConnectToZenDeskTest {

    @Test
    private void testGetDataFromAPI() {
        ConnectToZenDesk connectToZenDesk = new ConnectToZenDesk();
        String link = "https://zccysethi92.zendesk.com/api/v2/tickets.json";
        Assertions.assertNotEquals("", connectToZenDesk.getDataFromAPI(link, "GET"));
        Assertions.assertEquals("", connectToZenDesk.getDataFromAPI(link, "GT")); // wrong request type
        Assertions.assertEquals("", connectToZenDesk.getDataFromAPI("https://zccysethi92.zendesk.com/api/v2/tickets.jso", "GET")); // broken link
    }
}