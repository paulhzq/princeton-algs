class Successor {
	private int[] parent;
	private int[] rank;
	private boolean[] isRemove;
	private int[] maxEle;
	private int num;
	public Successor(int n) {
		num = n;
		parent = new int[n];
		rank = new int[n];
		isRemove = new boolean[n];
		maxEle = new int[n];
		for(int i=0;i<n;i++) {
			parent[i] = i;
			rank[i] = 0;
			isRemove[i] = false;
			maxEle[i] = i;
		}
	}

	public int find(int p) {
		int rootP = root(p);
		return maxEle[rootP];
	}

	public int root(int p) {
		if(p!=parent[p]) {
			parent[p] = root(parent[p]);
		}
		return parent[p];
	}

	public void union(int p, int q) {
		int rootP = root(p), rootQ = root(q);
		if(rootP==rootQ) return;
		int rankP = rank[rootP], rankQ = rank[rootQ];
		if(rankP>rankQ) {
			parent[rootQ] = rootP;
		} else if (rankP<rankQ) {
			parent[rootP] = rootQ;
		} else {
			parent[rootP] = rootQ;
			rank[rootQ]++;
		}
		maxEle[rootP] = Math.max(maxEle[rootP],maxEle[rootQ]);
		maxEle[rootQ] = maxEle[rootP];
	}

	// remove is just union p and p's neighour.
	public void remove(int p) {
		isRemove[p] = true;
		if(p>0 && isRemove[p-1]) {
			union(p,p-1);
		}
		if(p<num-1 && isRemove[p+1]) {
			union(p,p+1);
		}
	}

	public int getSuccessor(int p) {
		if(p<0 || p>=num) {
			throw new IllegalArgumentException("not a validate p");
		} else {
			if(!isRemove[p]) {
				return p;
			} else {
				int res = find(p)+1;
				if(res>=num) {
					return -1;
				} else {
					return res;
				}
			}
		}
	}

	public static void main(String[] args) {
		Successor successor = new Successor(10);
		successor.remove(2);
		successor.remove(4);
		successor.remove(3);
		System.out.println(successor.root(2));
		System.out.println(successor.root(3));
		System.out.println(successor.root(4));
		System.out.println(successor.find(2));
		System.out.println(successor.find(3));
		System.out.println(successor.find(4));
		System.out.println(successor.getSuccessor(2));
		System.out.println(successor.getSuccessor(3));
		System.out.println(successor.getSuccessor(4));
		successor.remove(7);
		successor.remove(9);
		System.out.println(successor.getSuccessor(9));
	}

}