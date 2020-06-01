import java.util.*;

/**
 * Businesses like to have memorable telephone numbers. One way to make a
 * telephone number memorable is to have it spell a memorable word or phrase.
 * For example, you can call the University of Waterloo by dialing the memorable
 * TUT-GLOP. Sometimes only part of the number is used to spell a word. When you
 * get back to your hotel tonight you can order a pizza from Gino's by dialing
 * 310-GINO. Another way to make a telephone number memorable is to group the
 * digits in a memorable way. You could order your pizza from Pizza Hut by
 * calling their ``three tens'' number 3-10-10-10.
 * <p>
 * The standard form of a telephone number is seven decimal digits with a hyphen
 * between the third and fourth digits (e.g. 888-1200). The keypad of a phone
 * supplies the mapping of letters to numbers, as follows:
 * <p>
 * A, B, and C map to 2 <br/>
 * D, E, and F map to 3 <br/>
 * G, H, and I map to 4 <br/>
 * J, K, and L map to 5 <br/>
 * M, N, and O map to 6 <br/>
 * P, R, and S map to 7 <br/>
 * T, U, and V map to 8 <br/>
 * W, X, and Y map to 9 <br/>
 * <p>
 * There is no mapping for Q or Z. Hyphens are not dialed, and can be added and
 * removed as necessary. The standard form of TUT-GLOP is 888-4567, the standard
 * form of 310-GINO is 310-4466, and the standard form of 3-10-10-10 is
 * 310-1010.
 * <p>
 * Two telephone numbers are equivalent if they have the same standard form.
 * (They dial the same number.)
 * <p>
 * Your company is compiling a directory of telephone numbers from local
 * businesses. As part of the quality control process you want to check that no
 * two (or more) businesses in the directory have the same telephone number.
 * 
 * 
 * @author Luo Yinzhuo
 * @date 2013-7-4
 * @see <a href="http://poj.org/problem?id=1002&amp;lang=zh-CN">487-3279</a>
 */
public class Main {

	/**
	 * The program entrance.
	 * 
	 * @param args
	 *            The input arguments.
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNextLine()) {
			final int count = Integer.valueOf(scanner.nextLine());
			final List<String> numbers = new ArrayList<String>(count);

			for (int i = 0; i < count; i++) {
				numbers.add(scanner.nextLine());
			}
			checkDuplicate(numbers);
		}
		scanner.close();
	}

	/**
	 * Check the input numbers has any duplicate or not.
	 * 
	 * @param numbers
	 *            The phone numbers in raw format.
	 */
	static void checkDuplicate(List<String> numbers) {
		final List<PhoneNumber> numberList = new ArrayList<PhoneNumber>(
				numbers.size());

		for (String number : numbers) {
			numberList.add(new PhoneNumber(number));
		}

		Collections.sort(numberList, PhoneNumber.COMPARATOR);
		boolean noDuplicates = true;

		for (int i = 0; i < numberList.size(); i++) {
			PhoneNumber number = numberList.get(i);

			for (int j = i + 1; j < numberList.size(); j++) {
				if (number.equals(numberList.get(j))) {
					if (noDuplicates) {
						noDuplicates = false;
					}

					if (j == numberList.size() - 1) {
						System.out.println(String.format("%s %d",
								number.output(), j - i + 1));
						return;
					}
				} else {
					if (j - i > 1) {
						System.out.println(String.format("%s %d",
								number.output(), j - i));
						i = j - 1;
					}
					break;
				}
			}
		}

		if (noDuplicates) {
			System.out.println("No duplicates.");
		}
	}
}

/**
 * Represent a phone number.
 * 
 * @author Luo Yinzhuo
 * @date 2013-7-4
 */
class PhoneNumber {

	/** The mark "0". */
	private static final char MARK_ZERO = '0';
	/** The mark "A". */
	private static final char MARK_A = 'A';
	/** The keypad of phone. */
	private static final String[] KEYPAD = { "2", "2", "2", "3", "3", "3", "4",
			"4", "4", "5", "5", "5", "6", "6", "6", "7", "", "7", "7", "8",
			"8", "8", "9", "9", "9", "" };

	/** The number in int format. */
	private final int mNumber;

	/**
	 * Construct a new instance.
	 * 
	 * @param number
	 *            The raw number data.
	 */
	public PhoneNumber(String number) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < number.length(); i++) {
			final char c = number.charAt(i);

			if (c >= MARK_ZERO && c - MARK_ZERO < 10) {
				sb.append(c);
			} else if (c >= MARK_A && c - MARK_A < 26) {
				sb.append(KEYPAD[c - MARK_A]);
			}
		}
		mNumber = Integer.valueOf(sb.toString());
	}

	/**
	 * Output the phone number in 123-4567 format.
	 * 
	 * @return The phone number in 123-4567 format.
	 */
	public String output() {
		return String.format("%03d-%04d", mNumber / 10000, mNumber % 10000);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + mNumber;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof PhoneNumber)) {
			return false;
		}
		PhoneNumber other = (PhoneNumber) obj;
		return mNumber == other.mNumber;
	}

	public static final Comparator<PhoneNumber> COMPARATOR = new Comparator<PhoneNumber>() {
		@Override
		public int compare(PhoneNumber arg0, PhoneNumber arg1) {
			return arg0.mNumber - arg1.mNumber;
		}
	};
}
