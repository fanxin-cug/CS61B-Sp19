package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.ArrayList;
import java.util.List;

public class Percolation {
    private int size;
    private boolean[] grid;
    private WeightedQuickUnionUF uf;
    private int openCnt;

    private int top,bottom;

    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        size = N;
        grid = new boolean[N*N];
        for(int i = 0; i < N*N; i++){
            grid[i] = false;
        }
        uf = new WeightedQuickUnionUF(N*N+2); // top & bottom
        openCnt = 0;
        top = N*N;
        bottom = N*N+1;
    }               // create N-by-N grid, with all sites initially blocked

    public void open(int row, int col) {
        if(!checkvalid(row, col)) {
            throw new IndexOutOfBoundsException();
        }
        if(isOpen(row, col)) return;
        int id = xyTo1D(row, col);
        grid[id] = true;
        openCnt += 1;
        if(row == 0) uf.union(id,top);
        if(row == size-1) uf.union(id, bottom);
        List<Integer> neighbors = findNeighbors(row, col);
        for (int n : neighbors) {
            if (grid[n]) {
                uf.union(id, n);
            }
        }
    }      // open the site (row, col) if it is not open already

    public boolean isOpen(int row, int col) {
        if(!checkvalid(row, col)) {
            throw new IndexOutOfBoundsException();
        }
        int id = xyTo1D(row, col);
        return isOpen(id);
    } // is the site (row, col) open?

    private boolean isOpen(int id) {
        return grid[id];
    }

    public boolean isFull(int row, int col) {
        if(!checkvalid(row, col)) {
            throw new IndexOutOfBoundsException();
        }
        if(!isOpen(row, col)) return false;
        int id = xyTo1D(row, col);
        /*
        for(int i=0; i<size; i++){
            if(uf.connected(id,i)){
                return true;
            }
        }
        return false;
        */
        return isFull(id);
    } // is the site (row, col) full?

    private boolean isFull(int id) {
        return uf.connected(id,top);
    }

    public int numberOfOpenSites() {
        return openCnt;
    }          // number of open sites

    public boolean percolates() {
        /*
        for(int i=0; i<size; i++){
            if(isFull(size-1, i)){
                return true;
            }
        }
        return false;
        */
        return uf.connected(top, bottom);
    }             // does the system percolate?

    public static void main(String[] args) {

    }  // use for unit testing (not required, but keep this here for the autograder)

    private boolean checkvalid(int row, int col) {
        return row >= 0 && row < size && col >= 0 && col < size;
    }

    private int xyTo1D(int r, int c) {
        return c + (r * size);
    }

    private List<Integer> findNeighbors(int r, int c) {
        List<Integer> al = new ArrayList<>();
        if(checkvalid(r-1, c)){
            al.add(xyTo1D(r-1,c));
        }
        if(checkvalid(r+1, c)){
            al.add(xyTo1D(r+1,c));
        }
        if(checkvalid(r, c-1)){
            al.add(xyTo1D(r, c-1));
        }
        if(checkvalid(r, c+1)){
            al.add(xyTo1D(r, c+1));
        }
        return al;
    }
}
