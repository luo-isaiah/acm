import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Fred Mapper is considering purchasing some land in Louisiana to build his
 * house on. In the process of investigating the land, he learned that the state
 * of Louisiana is actually shrinking by 50 square miles each year, due to
 * erosion caused by the Mississippi River. Since Fred is hoping to live in this
 * house the rest of his life, he needs to know if his land is going to be lost
 * to erosion.
 * <p>
 * After doing more research, Fred has learned that the land that is being lost
 * forms a semicircle. This semicircle is part of a circle centered at (0,0),
 * with the line that bisects the circle being the X axis. Locations below the X
 * axis are in the water. The semicircle has an area of 0 at the beginning of
 * year 1. (Semicircle illustrated in the Figure.)
 * <p>
 * <img src="http://poj.org/images/1005/semicircle.GIF"/>
 * 
 * @author Luo Yinzhuo
 * @date 2013-7-4
 */
public class Main {

	/** The shrinking areas. */
	private static final List<Semicircle> AREAS = new ArrayList<Semicircle>();

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
			for (int i = 0; i < count; i++) {
				Point point = new Point(scanner.nextLine());

				for (int j = 1;; j++) {
					if (j > AREAS.size()) {
						AREAS.add(new Semicircle(j));
					}

					if (AREAS.get(j - 1).isInside(point)) {
						System.out
								.println(String
										.format("Property %d: This property will begin eroding in year %d.",
												i + 1, j));
						break;
					}
				}
			}
			System.out.println("END OF OUTPUT.");
		}
		scanner.close();
	}
}

/**
 * Represent the shrinking area.
 * 
 * @author Luo Yinzhuo
 * 
 */
class Semicircle {
	/** The shrinking area per year. */
	private static final int SHRINK = 50;

	/** The shrinking semicircle's radius. */
	private final double mRadius;

	/**
	 * Construct a new instance.
	 * 
	 * @param year
	 *            The shrinking year.
	 */
	public Semicircle(int year) {
		mRadius = Math.sqrt(SHRINK * year * 2 / Math.PI);
	}

	/**
	 * Check if the point is inside the shrinking area.
	 * 
	 * @param point
	 *            The property point.
	 * @return True if the point is inside the shrinking area, otherwise false.
	 */
	public boolean isInside(Point point) {
		return point.getDistance() < mRadius;
	}
}

/**
 * Represent the property point.
 * 
 * @author Luo Yinzhuo
 * 
 */
class Point {
	/** The distance between the point and the origin. */
	private final double mDistance;

	/**
	 * Construct a new instance.
	 * 
	 * @param point
	 *            The point in string format.
	 */
	public Point(String point) {
		String[] coordinates = point.split(" ");
		final float x = Float.valueOf(coordinates[0]);
		final float y = Float.valueOf(coordinates[1]);
		mDistance = Math.hypot(x, y);
	}

	/**
	 * Get the distance between the point and the origin.
	 * 
	 * @return The distance between the point and the origin.
	 */
	public double getDistance() {
		return mDistance;
	}
}