class MergeWithSmallAuxArray {
    public static void merge(Comparable[] a, int lo, int mid, int hi) {
        Comparable[] aux = new Comparable[mid-lo];
        for(int k=lo;k<=mid;k++) {
            aux[k] = a[k]; // copy the left half sort array,
        }
        int i = lo, j = mid+1;
        for (int k = lo ; k <= hi; k++) {
            if (i > mid) break; // left half already used, then right half don't need to do anything
            else if (j> hi) a[k] = aux[i++]; // right half already used, then put the aux element into array right half.
            else if (less(a[j],aux[i])) a[k] = aux[j++]; // compare a[j] with aux[i]
            else a[k] = a[i++];
        }
    }
}