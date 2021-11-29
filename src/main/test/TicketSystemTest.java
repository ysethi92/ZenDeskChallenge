package src.main.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import src.main.java.TicketSystem;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static java.lang.System.in;


class TicketSystemTest {

    @Test
    protected void testInputTicketID() {
        InputStream sysInBackup = in;
        ByteArrayInputStream in = new ByteArrayInputStream("12".getBytes());
        System.setIn(in);
        Assertions.assertEquals(12, TicketSystem.inputTicketID(120));
        System.setIn(sysInBackup);

//        List<String> inputs = new ArrayList<String>(
//                Arrays.asList(
//                        "12"));
//        List<Object> outputs = Collections.singletonList(new ArrayList<String>(
//                Arrays.asList("12")));
//        for(int i=0; i<inputs.size(); i++) {
//            ByteArrayInputStream in = new ByteArrayInputStream(inputs.get(i).getBytes());
//            System.setIn(in);
//            Assertions.assertEquals(outputs.get(i), TicketSystem.inputTicketID(120));
//        }
//        System.setIn(sysInBackup);
    }


}