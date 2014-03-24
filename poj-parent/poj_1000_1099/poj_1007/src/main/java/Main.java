import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * One measure of ``unsortedness'' in a sequence is the number of pairs of
 * entries that are out of order with respect to each other. For instance, in
 * the letter sequence ``DAABEC'', this measure is 5, since D is greater than
 * four letters to its right and E is greater than one letter to its right. This
 * measure is called the number of inversions in the sequence. The sequence
 * ``AACEDGG'' has only one inversion (E and D)---it is nearly sorted---while
 * the sequence ``ZWQM'' has 6 inversions (it is as unsorted as can be---exactly
 * the reverse of sorted).
 * 
 * You are responsible for cataloguing a sequence of DNA strings (sequences
 * containing only the four letters A, C, G, and T). However, you want to
 * catalog them, not in alphabetical order, but rather in order of
 * ``sortedness'', from ``most sorted'' to ``least sorted''. All the strings are
 * of the same length.
 * 
 * @author Luo Yinzhuo
 * @date 2014-3-24
 */
public class Main {
	/**
	 * Represent a DNA string.
	 * 
	 * @author Luo Yinzhuo
	 */
	private static class DNAString {
		private final String mDNA;
		private final int mInversion;

		/**
		 * Construct a new instance.
		 * 
		 * @param DNA
		 *            The DNA string.
		 */
		public DNAString(String DNA) {
			mDNA = DNA;
			mInversion = inversion(mDNA);
		}

		/**
		 * Calculate the DNA string's inversion.
		 * 
		 * @param DNA
		 *            The DNA string;
		 * @return The DNA string's inversion.
		 */
		private static int inversion(String DNA) {
			int a, c, g, inversion;
			a = c = g = inversion = 0;
			for (int i = DNA.length() - 1; i >= 0; i--) {
				switch (DNA.charAt(i)) {
				case 'A':
					a++;
					break;
				case 'C':
					c++;
					inversion += a;
					break;
				case 'G':
					g++;
					inversion += a;
					inversion += c;
					break;
				case 'T':
					inversion += a;
					inversion += c;
					inversion += g;
					break;
				}
			}
			return inversion;
		}
	}

	/**
	 * The program entrance.
	 * 
	 * @param args
	 *            The input arguments.
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		// Read the first line.
		scanner.nextLine();

		List<DNAString> DNAStrings = new ArrayList<DNAString>();
		while (scanner.hasNextLine()) {
			DNAStrings.add(new DNAString(scanner.nextLine()));
		}
		scanner.close();

		if (!DNAStrings.isEmpty()) {
			while (DNAStrings.size() != 1) {
				int i = DNAStrings.size() - 1;
				for (int j = i - 1;  j >= 0; j--) {
					if (DNAStrings.get(j).mInversion < DNAStrings.get(i).mInversion) {
						i = j;
					}
				}
				System.out.println(DNAStrings.remove(i).mDNA);
			}
			System.out.println(DNAStrings.remove(0).mDNA);
		}
	}
}
