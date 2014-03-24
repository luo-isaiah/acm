import java.util.Scanner;

/**
 * Some people believe that there are three cycles in a person's life that start
 * the day he or she is born. These three cycles are the physical, emotional,
 * and intellectual cycles, and they have periods of lengths 23, 28, and 33
 * days, respectively. There is one peak in each period of a cycle. At the peak
 * of a cycle, a person performs at his or her best in the corresponding field
 * (physical, emotional or mental). For example, if it is the mental curve,
 * thought processes will be sharper and concentration will be easier.
 * <p>
 * Since the three cycles have different periods, the peaks of the three cycles
 * generally occur at different times. We would like to determine when a triple
 * peak occurs (the peaks of all three cycles occur in the same day) for any
 * person. For each cycle, you will be given the number of days from the
 * beginning of the current year at which one of its peaks (not necessarily the
 * first) occurs. You will also be given a date expressed as the number of days
 * from the beginning of the current year. You task is to determine the number
 * of days from the given date to the next triple peak. The given date is not
 * counted. For example, if the given date is 10 and the next triple peak occurs
 * on day 12, the answer is 2, not 3. If a triple peak occurs on the given date,
 * you should give the number of days to the next occurrence of a triple peak.
 * 
 * @author Luo Yinzhuo
 * @date 2013-7-4
 */
public class Main {
	/** The physical cycle. */
	private static final int PHYSICAL_CYCLE = 23;
	/** The emotional cycle. */
	private static final int EMOTIONAL_CYCLE = 28;
	/** The intellectual cycle. */
	private static final int INTELLECTUAL_CYCLE = 33;
	/** The triple peak cycle. */
	private static final int TRIPLE_PEAK_CYCLE = PHYSICAL_CYCLE * EMOTIONAL_CYCLE
	        * INTELLECTUAL_CYCLE;

	/** The remainder cycle for physical. */
	private static final int PHYSICAL_REMAINDER;
	/** The remainder cycle for emotional. */
	private static final int EMOTIONAL_REMAINDER;
	/** The remainder cycle for intellectual. */
	private static final int INTELLECTUAL_REMAINDER;

	static {
		for (int i = 1, factor = EMOTIONAL_CYCLE * INTELLECTUAL_CYCLE;; i++) {
			if (i * factor % PHYSICAL_CYCLE == 1) {
				PHYSICAL_REMAINDER = i * factor;
				break;
			}
		}

		for (int i = 1, factor = PHYSICAL_CYCLE * INTELLECTUAL_CYCLE;; i++) {
			if (i * factor % EMOTIONAL_CYCLE == 1) {
				EMOTIONAL_REMAINDER = i * factor;
				break;
			}
		}

		for (int i = 1, factor = PHYSICAL_CYCLE * EMOTIONAL_CYCLE;; i++) {
			if (i * factor % INTELLECTUAL_CYCLE == 1) {
				INTELLECTUAL_REMAINDER = i * factor;
				break;
			}
		}
	}

	/**
	 * The program entrance.
	 * 
	 * @param args
	 *        The input arguments.
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int i = 1;
		while (scanner.hasNextLine()) {
			final int nextPeak = nextPeak(scanner.nextLine());
			if (nextPeak != -1) {
				System.out.println(String.format(
				        "Case %d: the next triple peak occurs in %d days.", i, nextPeak));
			} else {
				break;
			}
			i++;
		}
		scanner.close();
	}

	/**
	 * Calculate the next peak.
	 * 
	 * @param input The input for each case consists of one line of four
	 *        integers p, e, i, and d. The values p, e, and i are the number of
	 *        days from the beginning of the current year at which the physical,
	 *        emotional, and intellectual cycles peak, respectively. The value d
	 *        is the given date and may be smaller than any of p, e, or i. All
	 *        values are non-negative and at most 365, and you may assume that a
	 *        triple peak will occur within 21252 days of the given date. The
	 *        end of input is indicated by a line in which p = e = i = d = -1.
	 * @return The next peak.
	 * @author Luo Yinzhuo
	 * @date 2013-7-8
	 */
	static int nextPeak(String input) {
		final String[] cycles = input.split(" ");
		final int physical = Integer.valueOf(cycles[0]);
		final int emotional = Integer.valueOf(cycles[1]);
		final int intellectual = Integer.valueOf(cycles[2]);
		final int date = Integer.valueOf(cycles[3]);

		if (physical == -1 && emotional == -1 && intellectual == -1 && date == -1) {
			return -1;
		}

		final int seed = (physical % PHYSICAL_CYCLE * PHYSICAL_REMAINDER + emotional
		        % EMOTIONAL_CYCLE * EMOTIONAL_REMAINDER + intellectual % INTELLECTUAL_CYCLE
		        * INTELLECTUAL_REMAINDER);

		int nextPeak = (seed - date + TRIPLE_PEAK_CYCLE) % TRIPLE_PEAK_CYCLE;

		if (nextPeak == 0) {
			return TRIPLE_PEAK_CYCLE;
		} else {
			return nextPeak;
		}
	}
}
