import java.util.Arrays;
import java.util.NoSuchElementException;

import static java.lang.System.out;

public class UnionFind
{
    /**
     * parent[i] points to parent of element i or to self.
     */
    private int[] parent;

    /**
     * rank[i] holds the rank (cardinality) of root element i.
     */
    private int[] rank;

    /**
     * The number of disjoint sets
     */
    private int num;

    /**
     * Create n disjoint sets containing a single element numbered from 0 to n - 1.
     *
     * @param n
     */
    public UnionFind(int n)
    {
        if (n <= 0)
            throw new IllegalArgumentException("Expected n > 0");

        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; ++i) {
            parent[i] = i; // root of self
            rank[i] = 1; // contains only self
        }

        num = n;
    }

    /**
     * Find representative element (i.e root of tree) for element i
     *
     * @param i
     * @return
     */
    public int find(int i)
    {
        if (i < 0 || i > parent.length)
            throw new NoSuchElementException("Invalid element");

        return root(i);
    }

    /**
     * Merge set containing u with the one containing u.
     *
     * @param u
     * @param v
     * @return the representative of union
     */
    public int union(int u, int v)
    {
        // Replace elements by representatives

        u = find(u);
        v = find(v);

        if (u == v)
            return u; // no-op

        // Make smaller tree u point to v

        if (rank[v] < rank[u]) {
            int t = v; v = u; u = t; // swap u, v
        }

        parent[u] = v;
        rank[v] += rank[u];
        rank[u] = -1;

        num--;

        return v;
    }

    public int numberOfSets()
    {
        return num;
    }

    /**
     * Find representative (root) of element u
     */
    private int root(int u)
    {
        while (parent[u] != u)
            u = parent[u];
        return u;
    }

    /**
     * Find root of element u, while compressing path of visited nodes.
     * <p>
     * This is an optimized version of {@link UnionFind#root(int)} which modifies the
     * internal tree as it traverses it (moving from u to root).
     */
    @SuppressWarnings("unused")
    private int root1(int u)
    {
        int p = parent[u];
        if (p == u)
            return u;

        // So, u is a non-root node with parent p
        do {
            int p1 = parent[p];
            if (p == p1) {
                // The root is found at p
                u = p;
                break;
            } else {
                // Must move 1 level up
                parent[u] = p1; // compress path for u (minus 1)
                u = p;
                p = p1;
            }
        } while (true);

        return u;
    }

    /**
     * Get rank (i.e. cardinality) of the set containing element u
     * @param u
     * @return
     */
    public int rank(int u)
    {
        u = root(u);
        return rank[u];
    }
}
