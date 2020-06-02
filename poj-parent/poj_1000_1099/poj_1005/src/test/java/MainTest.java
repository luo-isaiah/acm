import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {

    @Test
    public void testMain() {
        InputStream is = MainTest.class.getResourceAsStream("in.txt");
        System.setIn(is);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        Main.main(null);

        BufferedReader expectedBr = new BufferedReader(new InputStreamReader(
                MainTest.class.getResourceAsStream("out.txt")));
        BufferedReader actualBr = new BufferedReader(new InputStreamReader(
                new ByteArrayInputStream(baos.toByteArray())));

        try {
            String expected = expectedBr.readLine();
            String actual = actualBr.readLine();
            for (int i = 1; expected != null || actual != null; i++) {
                assertEquals(expected, actual, String.format("Line%d", i));
                expected = expectedBr.readLine();
                actual = actualBr.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
