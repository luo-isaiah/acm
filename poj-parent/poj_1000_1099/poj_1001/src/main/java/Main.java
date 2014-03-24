import java.util.Scanner;

/**
 * Compute the exact value of R^n where R is a real number ( 0.0 < R < 99.999 )
 * and n is an integer such that 0 < n <= 25.
 * 
 * @author Luo Yinzhuo
 * @date 2013-7-4
 */
public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			Fraction result = exponentiation(line);
			System.out.println(result.output());
		}
		scanner.close();
	}

	/**
	 * Calculate the input's exponentiation.
	 * 
	 * @param input The input will consist of a set of pairs of values for R and
	 *        n. The R value will occupy columns 1 through 6, and the n value
	 *        will be in columns 8 and 9.
	 * @return The result.
	 * @author Luo Yinzhuo
	 * @date 2013-7-4
	 */
	private static Fraction exponentiation(String input) {
		Fraction fraction = new Fraction(input.substring(0, 6));
		int n = Integer.valueOf(input.substring(7, 9).trim());
		return fraction.exponent(n);
	}
}

/**
 * Represent a fraction.
 * 
 * @author Luo Yinzhuo
 * @date 2013-7-4
 */
class Fraction {
	/** The value byte array with "." removed and in reverse order. */
	private final byte[] mValue;
	/** The decimal count. */
	private final int mCount;

	/** The point mark ".". */
	private static final char MARK_POINT = '.';
	/** The zero mark "0". */
	private static final char MARK_ZERO = '0';

	/**
	 * Construct a new instance.
	 * 
	 * @param fraction The fraction in string format.
	 */
	Fraction(String fraction) {
		int count = 0;
		boolean hasPoint = false;

		StringBuilder sb = new StringBuilder();
		for (int i = fraction.length() - 1; i >= 0; i--) {
			char c = fraction.charAt(i);

			if (c == MARK_POINT) {
				hasPoint = true;
			} else {
				if (!hasPoint) {
					count++;
				}
				sb.append(c);
			}
		}

		for (; count > 0; count--) {
			if (sb.charAt(0) == MARK_ZERO) {
				sb.deleteCharAt(0);
			} else {
				break;
			}
		}

		if (count == 0) {
			hasPoint = false;
		}

		if (hasPoint) {
			mCount = count;
		} else {
			mCount = 0;
		}

		String value = sb.toString();
		mValue = new byte[value.length()];
		for (int i = value.length() - 1; i >= 0; i--) {
			mValue[i] = (byte) (value.charAt(i) - MARK_ZERO);
		}
	}

	/**
	 * Construct a new instance.
	 * 
	 * @param value The value.
	 * @param count The count.
	 */
	private Fraction(byte[] value, int count) {
		mValue = value;
		mCount = count;
	}

	/**
	 * The output will consist of one line for each line of input giving the
	 * exact value of R^n. Leading zeros should be suppressed in the output.
	 * Insignificant trailing zeros must not be printed. Don't print the decimal
	 * point if the result is an integer.
	 * 
	 * @return The fraction in string format.
	 * @author Luo Yinzhuo
	 * @date 2013-7-4
	 */
	String output() {
		boolean start = false;
		StringBuilder sb = new StringBuilder();
		for (int i = mValue.length - 1; i >= 0; i--) {
			byte c = mValue[i];
			if (!start && c != 0) {
				start = true;
			}

			if (start) {
				sb.append(c);
			}
		}

		if (mCount > 0) {
			while (sb.length() < mCount) {
				sb.insert(0, MARK_ZERO);
			}

			sb.insert(sb.length() - mCount, MARK_POINT);
		}
		return sb.toString();
	}

	/**
	 * Calculate the fraction's exponentiation.
	 * 
	 * @param n The exponentiation times.
	 * @return The result.
	 * @author Luo Yinzhuo
	 * @date 2013-7-4
	 */
	Fraction exponent(int n) {
		if (n == 0) {
			byte[] value = {1 };
			int count = 0;
			return new Fraction(value, count);
		} else if (n == 1) {
			return this;
		} else if (n == 2) {
			return this.multiply(this);
		} else {
			if (n % 2 == 0) {
				return this.multiply(this).exponent(n / 2);
			} else {
				return this.multiply(this).exponent(n / 2).multiply(this);
			}
		}
	}

	/**
	 * Calculate the fraction's multiplication.
	 * 
	 * @param fraction The multiply factor.
	 * @return The result.
	 * @author Luo Yinzhuo
	 * @date 2013-7-4
	 */
	Fraction multiply(Fraction fraction) {
		byte[] result = new byte[this.mValue.length + fraction.mValue.length];

		for (int i = result.length - 1; i >= 0; i--) {
			result[i] = 0;
		}

		for (int i = 0; i < fraction.mValue.length; i++) {
			int increment = 0;
			for (int j = 0; j < this.mValue.length; j++) {
				int multiply = this.mValue[j] * fraction.mValue[i] + result[i + j] + increment;
				result[i + j] = (byte) (multiply % 10);
				increment = multiply / 10;
			}
			result[i + this.mValue.length] = (byte) increment;
		}

		return new Fraction(result, this.mCount + fraction.mCount);
	}
}
