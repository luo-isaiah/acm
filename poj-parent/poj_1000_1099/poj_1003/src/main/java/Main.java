import java.util.Scanner;

/**
 * How far can you make a stack of cards overhang a table? If you have one card,
 * you can create a maximum overhang of half a card length. (We're assuming that
 * the cards must be perpendicular to the table.) With two cards you can make
 * the top card overhang the bottom one by half a card length, and the bottom
 * one overhang the table by a third of a card length, for a total maximum
 * overhang of 1/2 + 1/3 = 5/6 card lengths. In general you can make n cards
 * overhang by 1/2 + 1/3 + 1/4 + ... + 1/(n + 1) card lengths, where the top
 * card overhangs the second by 1/2, the second overhangs tha third by 1/3, the
 * third overhangs the fourth by 1/4, etc., and the bottom card overhangs the
 * table by 1/(n + 1). This is illustrated in the figure below.
 * <p>
 * <img src="http://poj.org/images/1003/hangover.jpg"/>
 * 
 * @author Luo Yinzhuo
 * @date 2013-7-4
 * @see <a href="http://poj.org/problem?id=1003">Hangover</a>
 */
public class Main {

	/** The end of input. */
	private static final String MARK_END = "0.00";

	/**
	 * The program entrance.
	 * 
	 * @param args
	 *            The input arguments.
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNextLine()) {
			String input = scanner.nextLine();

			if (input.equals(MARK_END)) {
				break;
			} else {
				System.out.println(new Overhang(input).output());
			}
		}
		scanner.close();
	}
}

/**
 * Represent the cards overhang.
 * 
 * @author Luo Yinzhuo
 */
class Overhang {

	/** The minimum number of cards necessary to achieve the overhang. */
	private final int mCards;

	/**
	 * Construct a new instance.
	 * 
	 * @param input
	 *            The overhang needs to reach.
	 */
	public Overhang(String input) {
		float overhang = Float.valueOf(input);

		for (int cards = 1;; cards++) {
			overhang -= (1 / (cards + 1.0f));

			if (overhang < 0) {
				mCards = cards;
				break;
			}
		}
	}

	/**
	 * Output the minimum number of cards necessary to achieve the overhang.
	 * 
	 * @return The minimum number of cards.
	 */
	public String output() {
		return String.format("%d card(s)", mCards);
	}
}
