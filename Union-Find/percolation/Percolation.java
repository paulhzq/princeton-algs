import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    private final int len; 
	private final WeightedQuickUnionUF uf;
	private final WeightedQuickUnionUF full;
	private boolean[] checkOpen;
	private int countOpenSites;

	public Percolation(int n) {
		if (n <= 0) throw new java.lang.IllegalArgumentException("Index out of bound.");
		len = n;
		countOpenSites = 0;
		checkOpen = new boolean[n*n+2];
		checkOpen[0] = true;
		checkOpen[n*n+1] = true;
		uf = new WeightedQuickUnionUF(n*n+2);
		full = new WeightedQuickUnionUF(n*n+1);
	}

	public void open(int row, int col) {
		validate(row, col);
		int cell = index(row, col);
		if (checkOpen[cell]) return;
		checkOpen[cell] = true;
		countOpenSites++;
		if (row != 1 && isOpen(row-1, col)) {
			uf.union(index(row-1, col), cell);
			full.union(index(row-1, col), cell);
		} else if (row == 1) {
			uf.union(cell, 0);
			full.union(cell,0);
		}
		if (row != len && isOpen(row+1, col)) {
			uf.union(index(row+1, col), cell);
			full.union(index(row+1, col), cell);
		} else if (row == len) {
			uf.union(cell, len*len+1);
		}
		if (col != 1 && isOpen(row, col-1)) {
			uf.union(index(row, col-1), cell);
			full.union(index(row, col-1), cell);
		}
		if (col != len && isOpen(row, col+1)) {
			uf.union(index(row, col+1), cell);
			full.union(index(row, col+1), cell);
		}
	}

	public boolean isOpen(int row, int col) {
		validate(row, col);
		return checkOpen[index(row, col)];
	}

	public boolean isFull(int row, int col) {
		validate(row, col);
		return full.connected(index(row, col), 0);
	}

	public int numberOfOpenSites() {
		return countOpenSites;
	}

	public boolean percolates() {
		return uf.connected(0, len*len+1);
	}

	private void validate(int row, int col) {
		if (row <= 0 || row > len || col <= 0 || col > len) {
			throw new java.lang.IllegalArgumentException("Index out of bound.");
		}
	}

	private int index(int row, int col) {
		return (row-1) * len + col; 
	}

}