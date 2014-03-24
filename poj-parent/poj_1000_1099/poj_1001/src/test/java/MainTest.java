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
				Assert.assertEquals(String.format("Line%d", i), expected, actual);
				expected = expectedBr.readLine();
				actual = actualBr.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testFraction() {
		Assert.assertEquals("95.123", new Fraction("95.123").output());
		Assert.assertEquals(".4321", new Fraction("0.4321").output());
		Assert.assertEquals("5.1234", new Fraction("5.1234").output());
		Assert.assertEquals("6.7592", new Fraction("6.7592").output());
		Assert.assertEquals("98.999", new Fraction("98.999").output());
		Assert.assertEquals("1.01", new Fraction("1.0100").output());
	}

	@Test
	public void testMultiply() {
		Assert.assertEquals("1.21", new Fraction("1.1").multiply(new Fraction("1.1")).output());
	}
	
	@Test
	public void testExponent() {
		Assert.assertEquals(".01", new Fraction("0.1").exponent(2).output());
		Assert.assertEquals("1", new Fraction("1.00").exponent(10).output());
	}
}
