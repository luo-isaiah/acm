import java.util.Scanner;

/**
 * Larry graduated this year and finally has a job. He's making a lot of money,
 * but somehow never seems to have enough. Larry has decided that he needs to
 * grab hold of his financial portfolio and solve his financing problems. The
 * first step is to figure out what's been going on with his money. Larry has
 * his bank account statements and wants to see how much money he has. Help
 * Larry by writing a program to take his closing balance from each of the past
 * twelve months and calculate his average account balance.
 * 
 * @author Luo Yinzhuo
 * @date 2013-7-4
 * @see <a href="http://poj.org/problem?id=1004">Financial Management</a>
 */
public class Main {

	/** The month count. */
	private static final int MONTH = 12;

	/**
	 * The program entrance.
	 * 
	 * @param args
	 *            The input arguments.
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		float sum = 0;
		
		for (int i = 0; i < MONTH; i++) {
			sum += scanner.nextFloat();
		}
		
		System.out.println(String.format("$%.2f", sum / MONTH));
		scanner.close();
	}
}