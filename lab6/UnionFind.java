import java.util.ArrayDeque;
import java.util.Deque;

public class UnionFind {

    // TODO - Add instance variables?
    private int[] parent;

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        // TODO
        parent = new int[n];
        for(int i=0; i<n; i++){
            parent[i] = -1;
        }
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) throws Exception {
        // TODO
        if(vertex<0 || vertex>=parent.length){
            throw new Exception("vertex is not a valid index");
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        // TODO
        while(parent(v1)>=0){
            v1 = parent(v1);
        }
        return -parent(v1);
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        // TODO
        return parent[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) throws Exception {
        // TODO
        return find(v1)==find(v2);
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) throws Exception {
        // TODO
        if(connected(v1, v2)){
            return;
        }
        int v1r=find(v1);
        int v2r=find(v2);
        int v1s=sizeOf(v1);
        int v2s=sizeOf(v2);
        if(v1s>v2s){
            parent[v2r]=v1r;
            parent[v1r]=-v1s-v2s;
        }else{
            parent[v1r]=v2r;
            parent[v2r]=-v1s-v2s;
        }
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) throws Exception {
        // TODO
        validate(vertex);
        if(parent(vertex)<0){
            return vertex;
        }else if(parent(parent(vertex))<0){
            return parent(vertex);
        }
        Deque<Integer> d = new ArrayDeque<>();
        while(parent(vertex)>=0){
            d.addLast(vertex);
            vertex = parent(vertex);
        }
        while(!d.isEmpty()){
            int p = d.removeFirst();
            parent[p] = vertex;
        }
        return vertex;
    }

    public static void main(String[] args) throws Exception {
        UnionFind uf=new UnionFind(10);
        uf.union(4,3);
        uf.union(3,8);
        uf.union(6,5);
        uf.union(9,4);
        uf.union(2,1);
        uf.union(8,9);
        uf.union(5,0);
        uf.union(7,2);
        uf.union(6,1);
        uf.union(1,0);
        uf.union(6,7);
    }
}
