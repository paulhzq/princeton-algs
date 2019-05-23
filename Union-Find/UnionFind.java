class UnionFind {
	public int[] parent; // root of each node
	public int[] rank;  // rank of each node, weight by height.
	public int count;  // number of components.
	public UnionFind(int n) {
		parent = new int[n];
		rank = new int[n];
		for(int i=0;i<n;i++) {
			parent[i] = i;
			rank[i] = 0;
		}
		count = n;
	}

	public void find(int p) {
		// while(p!=parents[p]) {
		// 	parents[p] = parents[parents[p]];
		// 	p = parents[p];
		// }
		if(p!=parent[p]) {  // path compress
			parent[p] = find(parent[p]);
		}
		return parent[p];
	}

	public void union(int p, int q) {
		int rootP = find(p);
		int rootQ = find(q);
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
		count--; // suceessfully union will decrease the components by 1.
	}
}