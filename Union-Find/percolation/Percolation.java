import edu.princeton.cs.algs4.WeightedQuickUnionUF;


class Percolation {
	private int ROW;
	private int COL;
	private WeightedQuickUnionUF uf;
	private boolean[] checkOpen;
	private int countOpenSites;

	public Percolation(int n) {
		ROW = n;
		COL = n;
		countOpenSites = 0;
		checkOpen = new boolean[n*n];
		uf = new WeightedQuickUnionUF(n*n);
	}

	public void open(int row, int col) {
		validate(row,col);
		int cell = index(row,col);
		if(checkOpen[cell]) return;
		checkOpen[cell] = true;
		countOpenSites++;
		if(row!=1 && isOpen(row-1,col)) {
			uf.union(index(row-1,col),cell);
		} else if(row==1) {
			uf.union(cell,0);
		}
		if(row!=ROW && isOpen(row+1,col)) {
			uf.union(index(row+1,col),cell);
		} else if(row==ROW) {
			uf.union(cell,ROW*COL-1);
		}
		if(col!=1 && isOpen(row,col-1)) {
			uf.union(index(row,col-1),cell);
		}
		if(col!=COL && isOpen(row,col+1)) {
			uf.union(index(row,col+1),cell);
		}
	}

	public boolean isOpen(int row, int col) {
		validate(row,col);
		return checkOpen[index(row,col)];
	}

	public boolean isFull(int row, int col) {
		validate(row,col);
		if(index(row,col)==0) return isOpen(row,col);
		return uf.connected(index(row,col),0);
	}

	public int numberOfOpenSites() {
		return countOpenSites;
	}

	public boolean percolates() {
		return uf.connected(0,ROW*COL-1);
	}

	private void validate(int row,int col) {
		if(row<=0 || row>ROW || col<=0 || col>COL) {
			throw new java.lang.IndexOutOfBoundsException("Index out of bound.");
		}
	}

	private int index(int row, int col) {
		return (row-1) * COL + col - 1;
	}

	public static void main(String[] args) {

	}
}