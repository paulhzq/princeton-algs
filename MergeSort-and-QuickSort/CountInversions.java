import java.util.Arrays;

/*
SORT-AND-COUNT(L)
Divide the list into two halves A and B.
(rA, A) ← SORT-AND-COUNT(A).
(rB, B) ← SORT-AND-COUNT(B).
(rAB, L) ← MERGE-AND-COUNT(A, B).
RETURN (rA + rB + rAB, L).
*/

public class CountInversions {
    private static int count;

    public static int countInversions(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        sort(a,aux,0,a.length-1);
        return count;
    }

    public static void sort(Comparable[] a, Comparable[] aux, int low, int high) {
        if (high <= low) return;
        int mid = low + (high -low)/2;
        sort(a, aux,low,mid);
        sort(a, aux,mid+1,high);
        merge(a, aux, low, mid, high);
    }

    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void merge(Comparable[] a, Comparable[] aux, int low, int mid, int high) {
        for (int k=low; k <= high; k++) {
            aux[k] = a[k];
        }
        int i = low, j= mid + 1;
        for (int k = low; k <= high; k++) {
            if (i > mid) a[k] = aux[j++]; // aux left half are all in the a, so just put the right half into a.
            else if (j > high) a[k] = aux[i++]; // aux right half are all in the a, so just put the left half into a.
            else if (less(aux[j],aux[i])) {
                count +=mid+1-i; // a[j] are inversions with a[i] to a[mid]
                a[k] = aux[j++];
            }
            else a[k] = aux[i++];
        }
        //System.out.println(Arrays.toString(a));
    }

    public static void main(String[] args) {
        Integer[] a = {6,5,2,3,1,4};
        System.out.println(countInversions(a));
        System.out.println(Arrays.toString(a));
    }

}