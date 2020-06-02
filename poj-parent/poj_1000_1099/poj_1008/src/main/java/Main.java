import java.util.Scanner;

/**
 * During his last sabbatical, professor M. A. Ya made a surprising discovery
 * about the old Maya calendar. From an old knotted message, professor
 * discovered that the Maya civilization used a 365 day long year, called Haab,
 * which had 19 months. Each of the first 18 months was 20 days long, and the
 * names of the months were pop, no, zip, zotz, tzec, xul, yoxkin, mol, chen,
 * yax, zac, ceh, mac, kankin, muan, pax, koyab, cumhu. Instead of having names,
 * the days of the months were denoted by numbers starting from 0 to 19. The
 * last month of Haab was called uayet and had 5 days denoted by numbers 0, 1,
 * 2, 3, 4. The Maya believed that this month was unlucky, the court of justice
 * was not in session, the trade stopped, people did not even sweep the floor.
 * <p>
 * For religious purposes, the Maya used another calendar in which the year was
 * called Tzolkin (holly year). The year was divided into thirteen periods, each
 * 20 days long. Each day was denoted by a pair consisting of a number and the
 * name of the day. They used 20 names: imix, ik, akbal, kan, chicchan, cimi,
 * manik, lamat, muluk, ok, chuen, eb, ben, ix, mem, cib, caban, eznab, canac,
 * ahau and 13 numbers; both in cycles.
 * <p>
 * Notice that each day has an unambiguous description. For example, at the
 * beginning of the year the days were described as follows:
 * <p>
 * 1 imix, 2 ik, 3 akbal, 4 kan, 5 chicchan, 6 cimi, 7 manik, 8 lamat, 9 muluk,
 * 10 ok, 11 chuen, 12 eb, 13 ben, 1 ix, 2 mem, 3 cib, 4 caban, 5 eznab, 6
 * canac, 7 ahau, and again in the next period 8 imix, 9 ik, 10 akbal . . .
 * <p>
 * Years (both Haab and Tzolkin) were denoted by numbers 0, 1, : : : , where the
 * number 0 was the beginning of the world. Thus, the first day was:
 * <p>
 * Haab: 0. pop 0
 * <p>
 * Tzolkin: 1 imix 0 Help professor M. A. Ya and write a program for him to
 * convert the dates from the Haab calendar to the Tzolkin calendar.
 * 
 * @author Luo Yinzhuo
 * @date 2014-3-24
 * @see <a href="http://poj.org/problem?id=1008&amp;lang=zh-CN">玛雅历</a>
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
		// Read the first line.
		System.out.println(scanner.nextLine());

		while (scanner.hasNextLine()) {
			System.out.println(haab2Tzolkin(scanner.nextLine()));
		}
		scanner.close();
	}

	/** The input delimiter. */
	private static final String DELIMITER = " ";

	/** The month name in Haab. */
	private enum HaabMonth {
		pop, no, zip, zotz, tzec, xul, yoxkin, mol, chen, yax, zac, ceh, mac, kankin, muan, pax, koyab, cumhu, uayet
	}

	/** The month name in TzolkinMonth. */
	private enum TzolkinMonth {
		imix, ik, akbal, kan, chicchan, cimi, manik, lamat, muluk, ok, chuen, eb, ben, ix, mem, cib, caban, eznab, canac, ahau
	}

	private static String haab2Tzolkin(String haab) {
		String[] haabDate = haab.split(DELIMITER);
		final int haabDay = Integer.valueOf(haabDate[0].substring(0,
				haabDate[0].length() - 1));
		final HaabMonth haabMonth = HaabMonth.valueOf(haabDate[1]);
		final int haabYear = Integer.valueOf(haabDate[2]);

		final int time = haabDay + 1 + (haabMonth.ordinal()) * 20 + haabYear
				* 365;

		final int tzolkinYear = time % 260 == 0 ? time / 260 - 1 : time / 260;
		final TzolkinMonth tzolkinMonth = TzolkinMonth.values()[time % 20 == 0 ? 19
				: time % 20 - 1];
		final int tzolkinDay = time % 13 == 0 ? 13 : time % 13;

		return String.format("%d %s %d", tzolkinDay, tzolkinMonth, tzolkinYear);
	}
}
