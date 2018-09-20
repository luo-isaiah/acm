import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    /** virtual top value */
    private static final int VIRTUAL_TOP = 0;
    /** side size */
    private final int n;
    /** virtual bottom value */
    private final int virtualBottom;
    /** number of open sites */
    private int openSites;
    /** sites open flag */
    private final boolean[] grid;
    /** percolate flag */
    private boolean isPercolated;
    /** union-find set include all sites and virtual top and virtual bottom */
    private final WeightedQuickUnionUF unionInstance;
    /** union-find set include all sites and virtual top */
    private final WeightedQuickUnionUF backWash;
    
    /** top, left, right, bottom */
    private enum Pos {
        TOP(-1, 0),
        LEFT(0, -1),
        RIGHT(0, 1),
        BOTTOM(1, 0);
        
        /** row offset */
        private final int dx;
        /** column offset */
        private final int dy;
        
        private Pos(int dx, int dy) {
            this.dx = dx;
            this.dy = dy;
        }
    }
    
    /**
     * create n-by-n grid, with all sites blocked
     */
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Illegal Argument");
        }
        this.n = n;
        int gridLength = n * n + 2; // sites quantity plus virtual top and virtual bottom
        isPercolated = false;
        virtualBottom = n * n + 1;
        unionInstance = new WeightedQuickUnionUF(gridLength);
        backWash = new WeightedQuickUnionUF(gridLength - 1);
        grid = new boolean[gridLength];
    }
    
    /**
     * open site (row, col) if it is not open already
     */
    public void open(int row, int col) {
        isValidBounds(row, col);
        if (isOpen(row, col)) {
            return;
        }
        
        int gridId = xyTo1D(row, col);
        grid[gridId] = true;
        openSites++;
        
        if (1 == row) {
            unionInstance.union(VIRTUAL_TOP, gridId);
            backWash.union(VIRTUAL_TOP, gridId);
        }
        
        if (n == row) {
            unionInstance.union(virtualBottom, gridId);
        }
        
        /** top */
        int posX, posY;
        posX = row + Pos.TOP.dx;
        posY = col + Pos.TOP.dy;
        if (isPosValid(posX, posY) && isOpen(posX, posY)) {
            int posId = xyTo1D(posX, posY);
            unionInstance.union(gridId, posId);
            backWash.union(gridId, posId);
        }
        
        /** left */
        posX = row + Pos.LEFT.dx;
        posY = col + Pos.LEFT.dy;
        if (isPosValid(posX, posY) && isOpen(posX, posY)) {
            int posId = xyTo1D(posX, posY);
            unionInstance.union(gridId, posId);
            backWash.union(gridId, posId);
        }
        
        /** right */
        posX = row + Pos.RIGHT.dx;
        posY = col + Pos.RIGHT.dy;
        if (isPosValid(posX, posY) && isOpen(posX, posY)) {
            int posId = xyTo1D(posX, posY);
            unionInstance.union(gridId, posId);
            backWash.union(gridId, posId);
        }
        
        /** bottom */
        posX = row + Pos.BOTTOM.dx;
        posY = col + Pos.BOTTOM.dy;
        if (isPosValid(posX, posY) && isOpen(posX, posY)) {
            int posId = xyTo1D(posX, posY);
            unionInstance.union(gridId, posId);
            backWash.union(gridId, posId);
        }
    }
    
    /**
     * Convert 2d coordinates to 1d index
     */
    private int xyTo1D(int row, int col) {
        return (row - 1) * n + col;
    }
    
    /**
     * Check 2d coordinates whether out of bounds
     */
    private boolean isPosValid(int row, int col) {
        return row >= 1 && row <= n && col >= 1 && col <= n;
    }
    
    /**
     * Check 2d coordinates whether out of bounds
     */
    private void isValidBounds(int row, int col) {
        if (row < 1 || row > n) {
            throw new IllegalArgumentException("Row index out of bounds");
        }
        if (col < 1 || col > n) {
            throw new IllegalArgumentException("Column index out of bounds");
        }
    }
    
    /**
     * is site (row, col) open?
     */
    public boolean isOpen(int row, int col) {
        isValidBounds(row, col);
        return grid[xyTo1D(row, col)];
    }
    
    /**
     * is site (row, col) full?
     */
    public boolean isFull(int row, int col) {
        isValidBounds(row, col);
        return backWash.connected(VIRTUAL_TOP, xyTo1D(row, col));
    }
    
    /**
     * number of open sites
     */
    public int numberOfOpenSites() {
        return openSites;
    }
    
    /**
     * does the system percolate?
     */
    public boolean percolates() {
        if (isPercolated) {
            return true;
        }
        if (unionInstance.connected(VIRTUAL_TOP, virtualBottom)) {
            isPercolated = true;
            return true;
        }
        return false;
    }
    
    public static void main(String[] args) {
        Percolation perc = new Percolation(3);
        perc.open(1, 1);
        perc.open(1, 2);
        perc.open(2, 2);
        perc.open(2, 3);
        perc.open(3, 1);
        perc.open(3, 3);
        System.out.println(perc.percolates());
        System.out.println(perc.numberOfOpenSites());
    }
}