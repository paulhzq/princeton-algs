/* Union-find with specific canonical element. Add a method 𝚏𝚒𝚗𝚍() to the union-find 
data type so that 𝚏𝚒𝚗𝚍(𝚒) returns the largest element in the connected component 
containing i. The operations, 𝚞𝚗𝚒𝚘𝚗(), 𝚌𝚘𝚗𝚗𝚎𝚌𝚝𝚎𝚍(), and 𝚏𝚒𝚗𝚍() should all take 
logarithmic time or better.
For example, if one of the connected components is {1,2,6,9}, then the 𝚏𝚒𝚗𝚍() 
method should return 9 for each of the four elements in the connected components.
*/
class UFWithHighestElement {
	public int[] parent;
	public int[] rank;
	public int[] maxEle;
	int count;
	public UFWithHighestElement(int n) {
		parent = new int[n];
		rank = new int[n];
		maxEle = new int[n];
		for(int i=0;i<n;i++) {
			parent[i] = i;
			rank[i] = 0;
			maxEle[i] = i;
		}
		count = n;
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

	public boolean connected(int p , int q) {
		return root(p)==root(q);
	}
	// when union p and q, also set maxEle[rootP] and maxEle[rootQ] to the bigger one.
	public void union(int p, int q) {
		int rootP = root(p), rootQ = root(q);
		if(rootP==rootQ) return;
		int rankP = rank[rootP], rankQ = rank[rootQ];
		if(rankP>rankQ) {
			parent[rootQ] = rootP;
		} else if (rankP<rankQ) {
			parent[rootQ] = rootP;
		} else {
			parent[rootP] = rootQ;
			rank[rootQ]++;
		}
		maxEle[rootP] = Math.max(maxEle[rootP],maxEle[rootQ]);
		maxEle[rootQ] = maxEle[rootP];
	}
	public static void main(String[] args) {
		UFWithHighestElement ufHigh = new UFWithHighestElement(10);
		ufHigh.union(1,2);
		ufHigh.union(2,6);
		ufHigh.union(6,9);
		System.out.println(ufHigh.find(1));
		System.out.println(ufHigh.find(2));
		System.out.println(ufHigh.find(6));
		System.out.println(ufHigh.find(9));
	}
}