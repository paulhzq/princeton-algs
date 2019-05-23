/* 
Given a social network containing n members and a log file containing m 
timestamps at which times pairs of members formed friendships, design an 
algorithm to determine the earliest time at which all members are connected
(i.e., every member is a friend of a friend of a friend ... of a friend). 
Assume that the log file is sorted by timestamp and that friendship is 
an equivalence relation. The running time of your algorithm should be mlogn
or better and use extra space proportional to n.
*/
class UF {
	public int[] parent;
	public int[] rank;
	public int count;
	public UF(int n) {
		parent = new int[n+1];
		rank = new int[n+1];
		for(int i=0;i<=n;i++) {
			parent[i] = i;
			rank[i] = 0;
		}
		count = n;
	}
	public int find(int p) {
		if(p!=parent[p]) {
			parent[p]=find(parent[p]);
		}
		return parent[p];
	}
	public void union(int p, int q) {
		int rootP = find(p);
		int rootQ = find(q);
		if(rootP==rootQ) return;
		int rankP = rank[rootP], rankQ = rank[rootQ];
		if(rankP==rankQ) {
			parent[rootP] = rootQ;
			rank[rootQ]++;
		} else if (rankP>rankQ) {
			parent[rootQ] = rootP; 
		} else {
			parent[rootP] = rootQ;
		}
		count--;
	}
}

class SocialNetworkConn {
	public static void main(String[] args) {
		int n = 5, m = 6;
		// first element is time, second and third element are pairs of members.
		int[][] timestamps = new int[][]{{1,1,2},{2,2,3},{3,3,4},{4,1,4},{5,4,5},{6,2,5}};

		UF uf = new UF(n);
		for(int i=0;i<m;i++) {
			uf.union(timestamps[i][1],timestamps[i][2]);
			if(uf.count==1) {
				System.out.println(i+1);
				break;
			}
		}
	}

}