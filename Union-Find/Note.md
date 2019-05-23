1. Dynamic connectivity.

	Give a set of N objects.
	-  Union command: connect two objects.
	-  find/connected query: is there a path connecting the two objects.

	union(4,3), union(2,6)
	connected(2,3)

	application: pixels in digital photo...


  modeling the connection.

  Find the query: check the two objects are in the same component.
  Union command: 

  Goal: design efficient data strcuture for union-find.

 	class UF {

 	}

 Quick-find 

 Data struture. 
 1. Integer array id[] of size N.
 2. Interpretation: p and q are connected iff(if and only if) they have the same id.

	public class QuickFindUF {
		private int[] id;
		public boolean connected(int p, int q) {
			return id[p]==id[q];
		}
		public void union(int p, int q) {
		 int pid = id[p];
		 int qid = id[q];
		 for(int i=0;i<id.length;i++) {
		 	if(id[i]==pid) id[i]=qid;
		 }
	}

	}

	Quick-find cost model: init -> N   union -> N find -> 1


Quick-Union
	1.
	2. Interpretation: id[i] is parent of i.
	Root of i is id[id[id[i]]]

weighted quick-union.
 . modify quick-union to avoid tall trees.
 . keep track of size of each tree(number of objects).
 . Balance by linking root of smaller tree to root of large tree.	 


Path compression. 
  . use in find function, flat the tree.

