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

	@Test
	public void testOutput() {
		Assert.assertEquals("888-4567", new PhoneNumber("TUT-GLOP").output());
		Assert.assertEquals("487-3279", new PhoneNumber("4873279").output());
		Assert.assertEquals("487-3279", new PhoneNumber("ITS-EASY").output());
		Assert.assertEquals("310-4466", new PhoneNumber("310-GINO").output());
		Assert.assertEquals("310-1010", new PhoneNumber("3-10-10-10").output());
		Assert.assertEquals("310-0010", new PhoneNumber("3-10-00-10").output());
		Assert.assertEquals("000-0000", new PhoneNumber("0-00-00-00").output());
	}
}
