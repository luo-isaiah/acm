import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

import org.junit.Assert;
import org.junit.Test;

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
				Assert.assertEquals(String.format("Line%d", i), expected,
						actual);
				expected = expectedBr.readLine();
				actual = actualBr.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
