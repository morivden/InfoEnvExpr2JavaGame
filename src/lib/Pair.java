package lib;

public class Pair<A, B> {
    public final A _1;
    public final B _2;

    public Pair(A _1_, B _2_) { _1 = _1_; _2 = _2_; }

    private static boolean eq(Object o1, Object o2) { return o1 == null ? o2 == null : o1.equals(o2); }

    private static int hc(Object o) { return o == null ? 0 : o.hashCode(); }

    @Override
    public boolean equals(Object o) {
        if ( !(o instanceof Pair) ) { return false; }
        Pair<?, ?> rhs = (Pair<?, ?>) o;
        return eq(_1, rhs._1) && eq(_2, rhs._2);
    }

    @Override
    public int hashCode() { return hc(_1) ^ hc(_2); }
}
